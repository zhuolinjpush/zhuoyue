package net.zhuoyue.helper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import net.zhuoyue.vo.Reviewer;
import net.zhuoyue.vo.Sku;
import net.zhuoyue.vo.SkuReviewer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class DBHelper {

	private static Logger logger = LogManager.getLogger(DBHelper.class.getName());
	
	private static String Save_Sku_SQL = "insert into amazon_sku(skuid,mainurl,allreviewurl,reviewtext,reviewscount) values(?,?,?,?,?)" +
			" on duplicate key update reviewtext=?,reviewscount=?";
	private static String Save_Review_SQL = "insert into amazon_reviewers(reviewerid,username,votestext,votesnum,rankingtext,rankingnum," +
			"recentreview,refurl) values(?,?,?,?,?,?,?,?) on duplicate key update username=?,votestext=?,votesnum=?,rankingtext=?," +
			"rankingnum=?,recentreview=?,refurl=?";
	private static String Save_SkuRev_SQL = "insert into amazon_sku_reviewer(skuid,reviewerid,startext,starnum,title,content,reviewtime)" +
			"  values(?,?,?,?,?,?,?) on duplicate key update startext=?,starnum=?,title=?,content=?";
	private static String Query_ReviewMaxTime_SQL = "select reviewerid,max(reviewtime) as recentreview from amazon_sku_reviewer group by reviewerid";
	private static String Update_RecentTime_SQL = "update amazon_reviewers set recentreview=? where reviewerid=?";

	public static void saveSku(List<Sku> data) {
		long start = System.currentTimeMillis();
		try {
			Connection conn = DBPool.getInstance().getConnection(); 
			conn.setAutoCommit(false);
			PreparedStatement pstat = conn.prepareStatement(Save_Sku_SQL);
			for (Sku sku : data) {
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
		logger.info("save " + data.size() + " cost time=" + (System.currentTimeMillis() - start));
	}
	
	public static void saveReviewer(List<Reviewer> data) {
		long start = System.currentTimeMillis();
		try {
			Connection conn = DBPool.getInstance().getConnection(); 
			conn.setAutoCommit(false);
			PreparedStatement pstat = conn.prepareStatement(Save_Review_SQL);
			for (Reviewer rev : data) {
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
		} catch (Exception e) {
			logger.error("save error", e);
		}
		logger.info("save " + data.size() + " cost time=" + (System.currentTimeMillis() - start));
	}
	
	public static void saveSkuReview(List<SkuReviewer> data) {
		long start = System.currentTimeMillis();
		try {
			Connection conn = DBPool.getInstance().getConnection(); 
			conn.setAutoCommit(false);
			PreparedStatement pstat = conn.prepareStatement(Save_SkuRev_SQL);
			for (SkuReviewer sr : data) {
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
		} catch (Exception e) {
			logger.error("save error", e);
		}
		logger.info("save " + data.size() + " cost time=" + (System.currentTimeMillis() - start));
		updateRecentTime();
	}

	public static void updateRecentTime() {
		long start = System.currentTimeMillis();
		try {
			Connection conn = DBPool.getInstance().getConnection();
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
			rs.close();
			pstat.close();
			updatePstat.executeBatch();
			conn.commit();
			updatePstat.close();
		} catch (Exception e) {
			logger.error("save error", e);
		}
		logger.info("update cost time=" + (System.currentTimeMillis() - start));
	}
}
