package net.zhuoyue.vo;

import java.io.Serializable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SkuReviewer {

	private static Logger logger = LogManager.getLogger(SkuReviewer.class.getName());
	
	private String skuid;
	private String reviewerid;
	private String startext;
	private int starnum;
	private String title;
	private String content;
	private String reviewtime;
	
	public SkuReviewer(String skuid, String reviewerid, String startext,
			int starnum, String title, String content, String reviewtime) {
		this.skuid = skuid;
		this.reviewerid = reviewerid;
		this.startext = startext;
		this.starnum = starnum;
		this.title = title;
		this.content = content;
		this.reviewtime = reviewtime;
	}

	public String getSkuid() {
		return skuid;
	}

	public void setSkuid(String skuid) {
		this.skuid = skuid;
	}

	public String getReviewerid() {
		return reviewerid;
	}

	public void setReviewerid(String reviewerid) {
		this.reviewerid = reviewerid;
	}

	public String getStartext() {
		return startext;
	}

	public void setStartext(String startext) {
		this.startext = startext;
	}

	public int getStarnum() {
		return starnum;
	}

	public void setStarnum(int starnum) {
		this.starnum = starnum;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getReviewtime() {
		return reviewtime;
	}

	public void setReviewtime(String reviewtime) {
		this.reviewtime = reviewtime;
	}
	
	public void printstr() {
		logger.info(String.format("%s\t%s\t%s", this.skuid, this.reviewerid, this.title));
	}
	
}
