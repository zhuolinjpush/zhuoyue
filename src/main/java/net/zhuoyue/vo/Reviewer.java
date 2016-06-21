package net.zhuoyue.vo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Reviewer {
    
    private static Logger LOG = LogManager.getLogger(Reviewer.class.getName());
    
    private String star;
    private double formatstar;
    private String username;
    private String title;
    private String content;
    private String reviewtime;
    private String formattime;
    private String refurl;
    private String helpfulvotes;
    private int formatvotes;
    private String ranking;
    private long formatranking;
    
    public Reviewer(String star, double formatstar, String username, String title, String content,
            String reviewtime, String formattime, String refurl,
            String helpfulvotes, int formatvotes, String ranking, long formatranking) {
        this.star = star;
        this.formatstar = formatstar;
        this.username = username;
        this.title = title;
        this.content = content;
        this.reviewtime = reviewtime;
        this.formattime = formattime;
        this.refurl = refurl;
        this.helpfulvotes = helpfulvotes;
        this.formatvotes = formatvotes;
        this.ranking = ranking;
        this.formatranking = formatranking;
    }
    
    public int getFormatvotes() {
        return formatvotes;
    }

    public void setFormatvotes(int formatvotes) {
        this.formatvotes = formatvotes;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }
    
    public double getFormatstar() {
        return formatstar;
    }

    public void setFormatstar(double formatstar) {
        this.formatstar = formatstar;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getFormattime() {
        return formattime;
    }

    public void setFormattime(String formattime) {
        this.formattime = formattime;
    }

    public String getRefurl() {
        return refurl;
    }

    public void setRefurl(String refurl) {
        this.refurl = refurl;
    }

    public String getHelpfulvotes() {
        return helpfulvotes;
    }

    public void setHelpfulvotes(String helpfulvotes) {
        this.helpfulvotes = helpfulvotes;
    }

    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }

    public long getFormatranking() {
        return formatranking;
    }

    public void setFormatranking(long formatranking) {
        this.formatranking = formatranking;
    }

    public void printInfo() {
        LOG.info(String.format("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s", star, formatstar, username, title, content, reviewtime, formattime, refurl, helpfulvotes, formatvotes, ranking, formatranking));
    }

}
