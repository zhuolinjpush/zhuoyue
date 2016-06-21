package net.zhuoyue.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import net.zhuoyue.util.ExcelUtil;
import net.zhuoyue.util.HtmlParserUtil;
import net.zhuoyue.vo.Reviewer;
import net.zhuoyue.vo.SKU;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class QueryReviewer {

    private static Logger LOG = LogManager.getLogger(QueryReviewer.class.getName());
    public static int ACCESS_REVIEWPAGE_SLEEP = 2000;//ms
    
    public static List<Reviewer> queryOneSKURReviews(String mainurl) {
        List<Reviewer> reviews = new ArrayList<Reviewer>();
        try {
            SKU sku = HtmlParserUtil.getSKUInfo(mainurl);
            if (null == sku) {
                LOG.error("sku is null," + mainurl);
                return reviews;
            }
            List<String> pageList = HtmlParserUtil.getReviewsPage(mainurl, sku);
            for (String url : pageList) {
                reviews.addAll(HtmlParserUtil.getPageReviewsInfo(url));
                Thread.sleep(ACCESS_REVIEWPAGE_SLEEP);
                break;
            }
        } catch (Exception e) {
            LOG.error("query one sku reviews error", e);
        }
        return reviews;
    }
    
    public static void createReviewsXLS(List<Reviewer> reviews, String output) {
        try {
            String sheetName = "SKU Reviews 信息";
            LinkedList<String> title = new LinkedList<String>();
            title.add("用户名");
            title.add("评分");
            title.add("评论标题");
            title.add("评论时间");
            title.add("个人信息网址");
            title.add("评论帮助人数");
            title.add("review排名");
            title.add("评论内容");
            LinkedList<String> className = new LinkedList<String>();
            className.add("string");
            className.add("double");
            className.add("string");
            className.add("string");
            className.add("string");
            className.add("int");
            className.add("long");
            className.add("string");
            LinkedList<LinkedList<Object>> values = new LinkedList<LinkedList<Object>>();
            LinkedList<Object> vo = null;
            for (Reviewer r : reviews) {
                vo = new LinkedList<Object>();
                vo.add(r.getUsername());
                vo.add(r.getFormatstar());
                vo.add(r.getTitle());
                vo.add(r.getFormattime());
                vo.add(r.getRefurl());
                vo.add(r.getFormatvotes());
                vo.add(r.getFormatranking());
                vo.add(r.getContent());
                values.add(vo);
            }
            ExcelUtil.createXlsTable(sheetName, title, className, values, output);            
        } catch (Exception e) {
            LOG.error("create review xls error", e);
        }
    }
    
    public static void main(String[] args) {
        String mainurl = "https://www.amazon.com/iPhone-Screen-Protector-amFilm-Tempered/dp/B014EB532U/";
        List<Reviewer> reviews = queryOneSKURReviews(mainurl);
        createReviewsXLS(reviews, "E:/zhuolin/sku.xls");
        
    }

}
