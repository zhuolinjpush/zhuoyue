package net.zhuoyue.service;

public class Reviewer {
    
    private String star;
    private String username;
    private String title;
    private String content;
    private String reviewtime;
    private String refurl;
    private String helpfulvotes;
    private long ranking;
    
    public Reviewer(String star, String username, String title, String content,
            String reviewtime, String refurl, String helpfulvotes, long ranking) {
        this.star = star;
        this.username = username;
        this.title = title;
        this.content = content;
        this.reviewtime = reviewtime;
        this.refurl = refurl;
        this.helpfulvotes = helpfulvotes;
        this.ranking = ranking;
    }
    public String getStar() {
        return star;
    }
    public void setStar(String star) {
        this.star = star;
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
    public long getRanking() {
        return ranking;
    }
    public void setRanking(long ranking) {
        this.ranking = ranking;
    }
    
    
    

}
