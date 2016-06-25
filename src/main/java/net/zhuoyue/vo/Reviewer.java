package net.zhuoyue.vo;

import java.io.Serializable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Reviewer implements Serializable {

	private static final long serialVersionUID = 1L;

	private static Logger logger = LogManager.getLogger(Reviewer.class.getName());
    
    private String reviewerid;
    private String username;
    private String votestext;
    private int votesnum;
    private String rankingtext;
    private int rankingnum;
    private String recentreview;
    private String refurl;
    
	public Reviewer(String reviewerid, String username, String votestext,
			int votesnum, String rankingtext, int rankingnum,
			String recentreview, String refurl) {
		this.reviewerid = reviewerid;
		this.username = username;
		this.votestext = votestext;
		this.votesnum = votesnum;
		this.rankingtext = rankingtext;
		this.rankingnum = rankingnum;
		this.recentreview = recentreview;
		this.refurl = refurl;
	}

	public String getReviewerid() {
		return reviewerid;
	}

	public void setReviewerid(String reviewerid) {
		this.reviewerid = reviewerid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getVotestext() {
		return votestext;
	}

	public void setVotestext(String votestext) {
		this.votestext = votestext;
	}

	public int getVotesnum() {
		return votesnum;
	}

	public void setVotesnum(int votesnum) {
		this.votesnum = votesnum;
	}

	public String getRankingtext() {
		return rankingtext;
	}

	public void setRankingtext(String rankingtext) {
		this.rankingtext = rankingtext;
	}

	public int getRankingnum() {
		return rankingnum;
	}

	public void setRankingnum(int rankingnum) {
		this.rankingnum = rankingnum;
	}

	public String getRecentreview() {
		return recentreview;
	}

	public void setRecentreview(String recentreview) {
		this.recentreview = recentreview;
	}

	public String getRefurl() {
		return refurl;
	}

	public void setRefurl(String refurl) {
		this.refurl = refurl;
	}

	public void printstr() {
		logger.info(String.format("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s", this.reviewerid, this.username, this.votestext,
				this.votestext, this.rankingtext, this.rankingnum, this.recentreview, this.refurl));
	}
}
