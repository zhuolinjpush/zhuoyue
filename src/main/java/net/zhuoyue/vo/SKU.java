package net.zhuoyue.vo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SKU {
    
    private static Logger LOG = LogManager.getLogger(SKU.class.getName());
    
    private String id;
    private String mainurl;
    private String allreviewurl;
    private String reviewtext;
    private int reviewscount;
    
    public SKU(String id, String mainurl, String allreviewurl,
            String reviewtext, int reviewscount) {
        this.id = id;
        this.mainurl = mainurl;
        this.allreviewurl = allreviewurl;
        this.reviewtext = reviewtext;
        this.reviewscount = reviewscount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMainurl() {
        return mainurl;
    }

    public void setMainurl(String mainurl) {
        this.mainurl = mainurl;
    }

    public String getAllreviewurl() {
        return allreviewurl;
    }

    public void setAllreviewurl(String allreviewurl) {
        this.allreviewurl = allreviewurl;
    }

    public String getReviewtext() {
        return reviewtext;
    }

    public void setReviewtext(String reviewtext) {
        this.reviewtext = reviewtext;
    }

    public int getReviewscount() {
        return reviewscount;
    }

    public void setReviewscount(int reviewscount) {
        this.reviewscount = reviewscount;
    }
    
    public void printInfo() {
        LOG.info(String.format("%s\t%s\t%s\t%s\t%s", id, mainurl, allreviewurl, reviewtext, reviewscount));
    }
}
