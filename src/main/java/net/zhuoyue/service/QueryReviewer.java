package net.zhuoyue.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import net.zhuoyue.dao.DaoHelper;
import net.zhuoyue.util.ExcelUtil;
import net.zhuoyue.util.HtmlParserUtil;
import net.zhuoyue.vo.Reviewer;
import net.zhuoyue.vo.Sku;
import net.zhuoyue.vo.SkuReviewer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class QueryReviewer {

    private static Logger logger = LogManager.getLogger(QueryReviewer.class.getName());
    public static int ACCESS_REVIEWPAGE_SLEEP = 2000;//ms
    
    public static void queryOneSKURReviews(String mainurl) {
        try {
            Sku sku = HtmlParserUtil.getSKUInfo(mainurl);
            if (null == sku) {
            	logger.error("sku is null," + mainurl);
                return;
            }
            sku.printstr();
            List<String> pageList = HtmlParserUtil.getReviewsPage(mainurl, sku);
            List<Reviewer> reviewers = new ArrayList<Reviewer>();
            List<SkuReviewer> skuRevs = new ArrayList<SkuReviewer>();
            for (String url : pageList) {
                HtmlParserUtil.getPageReviewsInfo(url, sku.getSkuid(), reviewers, skuRevs);
                logger.info(reviewers.size() + " " + skuRevs.size());
                //Thread.sleep(ACCESS_REVIEWPAGE_SLEEP);
                break;
            }
            for(Reviewer r : reviewers) {
            	r.printstr();
            }
            //save 
            DaoHelper.saveSku(sku);
            DaoHelper.saveReviewer(reviewers);
            DaoHelper.saveSkuReviewer(skuRevs);
        } catch (Exception e) {
        	logger.error("query one sku reviews error", e);
        }
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

            }
            ExcelUtil.createXlsTable(sheetName, title, className, values, output);            
        } catch (Exception e) {
        	logger.error("create review xls error", e);
        }
    }
    
    public static void main(String[] args) {
        String mainurl = "https://www.amazon.com/iPhone-Screen-Protector-amFilm-Tempered/dp/B014EB532U/";
        queryOneSKURReviews(mainurl);
        
    }

}
