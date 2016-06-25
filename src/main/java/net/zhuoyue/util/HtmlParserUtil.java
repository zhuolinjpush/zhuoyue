package net.zhuoyue.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

import net.zhuoyue.vo.Reviewer;
import net.zhuoyue.vo.Sku;
import net.zhuoyue.vo.SkuReviewer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlParserUtil {
	
	private static Logger LOG = LogManager.getLogger(HtmlParserUtil.class.getName());
	public static Pattern pattern = Pattern.compile("rev-dpReviewsMostHelpfulAUI-[0-9a-zA-Z]+");
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	public static SimpleDateFormat mmSdf = new SimpleDateFormat("MMMM dd, yyyy", Locale.US);
	
	public static Sku getSKUInfo(String mainurl) {
	    Sku sku = null;
	    try {
	        String data = HttpClientUtil.getHtmlResponse(mainurl);
	        Document doc = Jsoup.parse(data);
	        Element cm_cr_dpwidget = doc.getElementById("cm_cr_dpwidget");
	        Elements alinkemphasis = cm_cr_dpwidget.getElementsByClass("a-link-emphasis");
	        String allreviewurl = alinkemphasis.get(1).attr("href");
	        String reviewtext = alinkemphasis.get(1).text();
	        String[] arr = allreviewurl.split("product-reviews/");
	        String[] textarr = reviewtext.split(" ");
	        if (arr.length >= 2) {
	            String skuid = arr[1].split("/", 2)[0];
	            int reviewscount = Integer.parseInt(textarr[2].replace(",", ""));
	            sku = new Sku(skuid, mainurl, allreviewurl, reviewtext, reviewscount);
	        }
	        
	    } catch (Exception e) {
	        LOG.error("get sku info error", e);
	    }
	    return sku;
	}
	
	public static List<String> getReviewsPage(String mainurl, Sku sku) {
	    List<String> urlList = new ArrayList<String>();
	    String suffix = "/ref=cm_cr_getr_d_paging_btm_%s?ie=UTF8&showViewpoints=1&sortBy=recent&pageNumber=%s";
	    try {
	        String[] arr = mainurl.split("/", 5);
	        int page = sku.getReviewscount() / 10;
	        if (sku.getReviewscount() % 10 != 0) {
	            page += 1;
	        }
	        for (int i = 1; i <= page; i++) {
	            String url = mainurl.replace(arr[4], "") + "product-reviews/" + sku.getSkuid() + String.format(suffix, i, i);
	            urlList.add(url);
	        }   
	    } catch (Exception e) {
	        LOG.error("get reviews page error", e);
	    }
	    return urlList;
	}
	
	public static void getPageReviewsInfo(String url, String skuid, List<Reviewer> reviewers, List<SkuReviewer> skuRevs) {
	    try {
	        String data = HttpClientUtil.getHtmlResponse(url);
	        Document doc = Jsoup.parse(data);
            Element review_list = doc.getElementById("cm_cr-review_list");
            //get reviewid
            Elements reviewIdEle = review_list.children();
            for (int i = 0; i < reviewIdEle.size(); i++) {
                Element ele = reviewIdEle.get(i);
                String id = ele.attr("id");
                if (null != id && !"".equals(id.trim())) {
                    LOG.info(String.format("--------------- revMHRL_id=%s -----------", id));
                    Element dpReview = review_list.getElementById(id);
                    Elements starEle = dpReview.getElementsByClass("a-icon-alt").eq(0);
                    Elements titleEle = dpReview.getElementsByClass("a-text-bold").eq(0);
                    Elements usernameEle = dpReview.getElementsByClass("author").eq(0);
                    String refurl = Common.Amazon_BaseUrl + dpReview.getElementsByClass("author").first().attr("href");
                    Elements reviewtimeEle = dpReview.getElementsByClass("review-date").eq(0);
                    Elements contentEle = dpReview.getElementsByClass("review-text").eq(0);
                    String startext = starEle.text();
                    int starnum = 5;
                    try {
                    	starnum = (int)Double.parseDouble(startext.split(" ")[0]);
                    } catch (Exception e) {
                        LOG.error("format star error," + startext, e);
                    }
                    String title = titleEle.text();
                    String username = usernameEle.text();
                    String reviewtime = reviewtimeEle.text().replace("on ", "");
                    String formattime = "";
                    try {
                        formattime = sdf.format(mmSdf.parse(reviewtime));
                    } catch (Exception e) {
                        LOG.error("format date error," + reviewtime, e);
                    }
                    String content = contentEle.text();
                    String reviewerid = refurl.split("profile/")[1].split("/")[0];
                    if (null == reviewerid || "".equals(reviewerid)) {
                    	LOG.info("reviewerid error," + refurl);
                    	continue;
                    }
                    SkuReviewer skuRev = new SkuReviewer(skuid, reviewerid, startext, starnum, title, content, formattime);
                    skuRevs.add(skuRev);
                    skuRev.printstr();
                    //reviewer info
                    Reviewer rev = getReviewer(reviewerid, username, refurl);
                    if (null != rev) {
                    	reviewers.add(rev);
                    	rev.printstr();
                    }
                }
            }
	    } catch (Exception e) {
	        LOG.error("get all reviews error", e);
	    }
	}
	
	public static Reviewer getReviewer(String reviewerid, String username, String refurl) {
		Reviewer rev = null;
		try {
			//reviewer info
            String refData = HttpClientUtil.getHtmlResponse(refurl);
            Document refDoc = Jsoup.parse(refData);
            Elements expander = refDoc.getElementsByClass("bio-expander");
            String votestext = "";
            int votesnum = 0;
            String rankingtext = "";
            int rankingnum = 0;
            if (expander.size() > 0) {
                Elements base = expander.get(0).getElementsByClass("a-size-base");
                Elements small = expander.get(0).getElementsByClass("a-size-small");
                votestext = small.get(0).text(); 
                
                try {
                	votesnum = Integer.parseInt(votestext);
                } catch (Exception e) {
                    LOG.error("format helpfulvotes error," + votestext, e);
                }
                rankingtext = base.get(2).text();
                if (rankingtext.contains("ranking")) {
                	rankingtext = base.get(3).text();
                }
                rankingnum = Integer.parseInt(rankingtext.replace(",", "").replace("#", ""));
            } else {
                LOG.warn("not review info," + refurl);
            }
            String recentreview = "";
            rev = new Reviewer(reviewerid, username, votestext, votesnum, rankingtext, rankingnum, recentreview, refurl);      
		} catch (Exception e) {
			LOG.error("get reviewer error", e);
		}
		return rev;
	}
	
	//test
	public static void getReviewerRef(String data) {
	    try {
	        Document doc = Jsoup.parse(data);
	        Elements expander = doc.getElementsByClass("bio-expander");
	        Elements base = expander.get(0).getElementsByClass("a-size-base");
	        Elements small = expander.get(0).getElementsByClass("a-size-small");
	        System.out.println(small.get(0).text());
	        System.out.println(Long.parseLong(base.get(2).text()));
	    } catch (Exception e) {
	        LOG.error("get reviewer ref error," + data, e);
	    }
	}

    public static void main(String[] args) {
//		getSKUInfo("https://www.amazon.com/iPhone-Screen-Protector-amFilm-Tempered/dp/B014EB532U/");
//		String html = HttpClientUtil.getHtmlResponse("https://www.amazon.com/gp/pdp/profile/AWJKQAC0XO5NI/ref=cm_cr_arp_d_pdp?ie=UTF8");
//		getReviewerRef(html);
//		getPageReviewsInfo("https://www.amazon.com/iPhone-Screen-Protector-amFilm-Tempered/product-reviews/B014EB532U/ref=cm_cr_getr_d_paging_btm_1?ie=UTF8&showViewpoints=1&sortBy=recent&pageNumber=1");
//		String mainurl = "https://www.amazon.com/iPhone-Screen-Protector-amFilm-Tempered/dp/B014EB532U/";
//		getReviewsPage(mainurl, getSKUInfo(mainurl));
		getReviewer("123", "A2I2V9MNT3Y545", "https://www.amazon.com/gp/pdp/profile/A2I2V9MNT3Y545/ref=cm_cr_arp_d_pdp?ie=UTF8");
    }

}
