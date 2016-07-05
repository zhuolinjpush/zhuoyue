package net.zhuoyue.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import net.zhuoyue.dao.ISkuReviewDao;
import net.zhuoyue.vo.Reviewer;
import net.zhuoyue.vo.Sku;
import net.zhuoyue.vo.SkuReviewer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class SkuReviewDAOImpl extends JdbcDaoSupport implements ISkuReviewDao {
    
    public static Logger logger = LogManager.getLogger(SkuReviewDAOImpl.class);
    
    private static String Save_Sku_SQL = "insert into amazon_sku(skuid,mainurl,allreviewurl,reviewtext,reviewscount) values(?,?,?,?,?)" +
            " on duplicate key update reviewtext=?,reviewscount=?";
    private static String Save_Review_SQL = "insert into amazon_reviewers(reviewerid,username,votestext,votesnum,rankingtext,rankingnum," +
            "recentreview,refurl) values(?,?,?,?,?,?,?,?) on duplicate key update username=?,votestext=?,votesnum=?,rankingtext=?," +
            "rankingnum=?,recentreview=?,refurl=?";
    private static String Save_SkuRev_SQL = "insert into amazon_sku_reviewer(skuid,reviewerid,startext,starnum,title,content,reviewtime)" +
            "  values(?,?,?,?,?,?,?) on duplicate key update startext=?,starnum=?,title=?,content=?";
    private static String Query_ReviewMaxTime_SQL = "select reviewerid,max(reviewtime) as recentreview from amazon_sku_reviewer group by reviewerid";
    private static String Update_RecentTime_SQL = "update amazon_reviewers set recentreview=? where reviewerid=?";
    
    private static String Query_All_Sku = "select * from amazon_sku";
    private static String Query_All_Reviewer = "select * from amazon_reviewers";
    private static String Query_All_SkuRew = "select * from amazon_sku_reviewer where skuid='%s'";

    public void addSku(Sku sku) {
        long start = System.currentTimeMillis();
        try {
            Connection conn = this.getJdbcTemplate().getDataSource().getConnection();
            conn.setAutoCommit(true);
            PreparedStatement pstat = conn.prepareStatement(Save_Sku_SQL);
            pstat.setString(1, sku.getSkuid());
            pstat.setString(2, sku.getMainurl());
            pstat.setString(3, sku.getAllreviewurl());
            pstat.setString(4, sku.getReviewtext());
            pstat.setInt(5, sku.getReviewscount());
            pstat.setString(6, sku.getReviewtext());
            pstat.setInt(7, sku.getReviewscount());
            pstat.execute();
            pstat.close();
        } catch (Exception e) {
            logger.error("save error", e);
        }
        logger.info("addSku cost time=" + (System.currentTimeMillis() - start));
    }

    public void addSku(List<Sku> skuList) {
        long start = System.currentTimeMillis();
        try {
            Connection conn = this.getJdbcTemplate().getDataSource().getConnection();
            conn.setAutoCommit(false);
            PreparedStatement pstat = conn.prepareStatement(Save_Sku_SQL);
            for (Sku sku : skuList) {
                pstat.setString(1, sku.getSkuid());
                pstat.setString(2, sku.getMainurl());
                pstat.setString(3, sku.getAllreviewurl());
                pstat.setString(4, sku.getReviewtext());
                pstat.setInt(5, sku.getReviewscount());
                pstat.setString(6, sku.getReviewtext());
                pstat.setInt(7, sku.getReviewscount());
                pstat.addBatch();
            }
            pstat.executeBatch();
            conn.commit();
            pstat.close();
        } catch (Exception e) {
            logger.error("save error", e);
        }
        logger.info("addSku list " + skuList.size() + " cost time=" + (System.currentTimeMillis() - start));
    }

    public void addReviewer(List<Reviewer> reviewerList) {
        long start = System.currentTimeMillis();
        try {
            Connection conn = this.getJdbcTemplate().getDataSource().getConnection();
            conn.setAutoCommit(false);
            PreparedStatement pstat = conn.prepareStatement(Save_Review_SQL);
            for (Reviewer rev : reviewerList) {
                pstat.setString(1, rev.getReviewerid());
                pstat.setString(2, rev.getUsername());
                pstat.setString(3, rev.getVotestext());
                pstat.setInt(4, rev.getVotesnum());
                pstat.setString(5, rev.getRankingtext());
                pstat.setInt(6, rev.getRankingnum());
                pstat.setString(7, rev.getRecentreview());
                pstat.setString(8, rev.getRefurl());
                pstat.setString(9, rev.getUsername());
                pstat.setString(10, rev.getVotestext());
                pstat.setInt(11, rev.getVotesnum());
                pstat.setString(12, rev.getRankingtext());
                pstat.setInt(13, rev.getRankingnum());
                pstat.setString(14, rev.getRecentreview());
                pstat.setString(15, rev.getRefurl());
                pstat.addBatch();
            }
            pstat.executeBatch();
            conn.commit();
            pstat.close();
            conn.setAutoCommit(true);
        } catch (Exception e) {
            logger.error("save error", e);
        }
        logger.info("addReviewer list " + reviewerList.size() + " cost time=" + (System.currentTimeMillis() - start));
    }

    public void addSkuReviewer(List<SkuReviewer> skuRevList) {
        long start = System.currentTimeMillis();
        try {
            Connection conn = this.getJdbcTemplate().getDataSource().getConnection();
            conn.setAutoCommit(false);
            PreparedStatement pstat = conn.prepareStatement(Save_SkuRev_SQL);
            for (SkuReviewer sr : skuRevList) {
                pstat.setString(1, sr.getSkuid());
                pstat.setString(2, sr.getReviewerid());
                pstat.setString(3, sr.getStartext());
                pstat.setInt(4, sr.getStarnum());
                pstat.setString(5, sr.getTitle());
                pstat.setString(6, sr.getContent());
                pstat.setString(7, sr.getReviewtime());
                pstat.setString(8, sr.getStartext());
                pstat.setInt(9, sr.getStarnum());
                pstat.setString(10, sr.getTitle());
                pstat.setString(11, sr.getContent());
                pstat.addBatch();
            }
            pstat.executeBatch();
            conn.commit();
            pstat.close();
            conn.setAutoCommit(true);
        } catch (Exception e) {
            logger.error("save error", e);
        }
        logger.info("addSkuReviewer list " + skuRevList.size() + " cost time=" + (System.currentTimeMillis() - start));
        updateRecentTime();
        
    }
    
    public void updateRecentTime() {
        long start = System.currentTimeMillis();
        try {
            Connection conn = this.getJdbcTemplate().getDataSource().getConnection();
            conn.setAutoCommit(false);
            PreparedStatement pstat = conn.prepareStatement(Query_ReviewMaxTime_SQL);
            PreparedStatement updatePstat = conn.prepareStatement(Update_RecentTime_SQL);
            ResultSet rs = pstat.executeQuery();
            while (rs.next()) {
                String reviewerid = rs.getString("reviewerid");
                String recentreview = rs.getString("recentreview");
                updatePstat.setString(1, recentreview);
                updatePstat.setString(2, reviewerid);
                updatePstat.addBatch();
            }
            updatePstat.executeBatch();
            rs.close();
            pstat.close();
            conn.commit();
            updatePstat.close();
            conn.setAutoCommit(true);
        } catch (Exception e) {
            logger.error("save error", e);
        }
        logger.info("updateRecentTime cost time=" + (System.currentTimeMillis() - start));
    }

    public List<Sku> findAllSku() {
        List<Sku> data = new ArrayList<Sku>();
        long start = System.currentTimeMillis();
        try {
            Connection conn = this.getJdbcTemplate().getDataSource().getConnection();
            PreparedStatement pstat = conn.prepareStatement(Query_All_Sku);
            ResultSet rs = pstat.executeQuery();
            Sku sku = null;
            while (rs.next()) {
                String skuid = rs.getString("skuid");
                String mainurl = rs.getString("mainurl");
                String allreviewurl = rs.getString("allreviewurl");
                String reviewtext = rs.getString("reviewtext");
                int reviewscount = rs.getInt("reviewscount");
                sku = new Sku(skuid, mainurl, allreviewurl, reviewtext, reviewscount);
                data.add(sku);
            }
            rs.close();
            pstat.close();
        } catch (Exception e) {
            logger.error("save error", e);
        }
        logger.info("findAllSku cost time=" + (System.currentTimeMillis() - start));
        return data;
    }

    public List<Reviewer> findAllReviewer() {
        List<Reviewer> data = new ArrayList<Reviewer>();
        long start = System.currentTimeMillis();
        try {
            Connection conn = this.getJdbcTemplate().getDataSource().getConnection();
            PreparedStatement pstat = conn.prepareStatement(Query_All_Reviewer);
            ResultSet rs = pstat.executeQuery();
            Reviewer rev = null;
            while (rs.next()) {
                String reviewerid = rs.getString("reviewerid");
                String username = rs.getString("username");
                String votestext = rs.getString("votestext");
                int votesnum = rs.getInt("votesnum");
                String rankingtext = rs.getString("rankingtext");
                int rankingnum = rs.getInt("rankingnum");
                String recentreview = rs.getString("recentreview");
                String refurl = rs.getString("refurl");
                rev = new Reviewer(reviewerid, username, votestext, votesnum, rankingtext, rankingnum, recentreview, refurl);
                data.add(rev);
            }
            rs.close();
            pstat.close();
        } catch (Exception e) {
            logger.error("save error", e);
        }
        logger.info("findAllReviewer cost time=" + (System.currentTimeMillis() - start));
        return data;
    }

    public List<SkuReviewer> findSkuReviewer(String skuid) {
        List<SkuReviewer> data = new ArrayList<SkuReviewer>();
        long start = System.currentTimeMillis();
        try {
            Connection conn = this.getJdbcTemplate().getDataSource().getConnection();
            PreparedStatement pstat = conn.prepareStatement(String.format(Query_All_SkuRew, skuid));
            ResultSet rs = pstat.executeQuery();
            SkuReviewer rev = null;
            while (rs.next()) {
                String reviewerid = rs.getString("reviewerid");
                String startext = rs.getString("startext");
                int starnum = rs.getInt("starnum");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String reviewtime = rs.getString("reviewtime");
                rev = new SkuReviewer(skuid, reviewerid, startext, starnum, title, content, reviewtime);
                data.add(rev);
            }
            rs.close();
            pstat.close();
        } catch (Exception e) {
            logger.error("save error", e);
        }
        logger.info("findSkuReviewer cost time=" + (System.currentTimeMillis() - start));
        return data;
    }


}
