package net.zhuoyue.vo;

import java.io.Serializable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Sku implements Serializable {

	private static final long serialVersionUID = 1L;

	private static Logger logger = LogManager.getLogger(Sku.class.getName());
	
    private String skuid;
    private String mainurl;
    private String allreviewurl;
    private String reviewtext;
    private int reviewscount;
    
    public Sku(String skuid, String mainurl, String allreviewurl,
            String reviewtext, int reviewscount) {
        this.skuid = skuid;
        this.mainurl = mainurl;
        this.allreviewurl = allreviewurl;
        this.reviewtext = reviewtext;
        this.reviewscount = reviewscount;
    }

    public String getSkuid() {
		return skuid;
	}

	public void setSkuid(String skuid) {
		this.skuid = skuid;
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
    
    public void printstr() {
		logger.info(String.format("%s\t%s\t%s", this.skuid, this.mainurl, this.reviewscount));
	}
}
