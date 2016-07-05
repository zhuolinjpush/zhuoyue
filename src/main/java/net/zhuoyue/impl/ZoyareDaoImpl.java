package net.zhuoyue.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.zhuoyue.dao.IZoyareDao;
import net.zhuoyue.vo.ZoyareSetting;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class ZoyareDaoImpl extends JdbcDaoSupport implements IZoyareDao {
    
    private static Logger logger = LogManager.getLogger(ZoyareDaoImpl.class.getName());
    
    private static String Save_ZoyareSetting_SQL = "insert into zoyare_settings(setid, note, content) values(?,?,?) on duplicate key update note=?, content=?";
    private static String Query_All_Settings = "select setid, note, content from zoyare_settings";

    public void addSetting(ZoyareSetting zoy) {
        long start = System.currentTimeMillis();
        try {
            Connection conn = this.getJdbcTemplate().getDataSource().getConnection();
            conn.setAutoCommit(true);
            PreparedStatement pstat = conn.prepareStatement(Save_ZoyareSetting_SQL);
            pstat.setString(1, zoy.getSetid());
            pstat.setString(2, zoy.getNote());
            pstat.setString(3, zoy.getContent());
            pstat.setString(4, zoy.getNote());
            pstat.setString(5, zoy.getContent());
            pstat.execute();
            pstat.close();
        } catch (Exception e) {
            logger.error("save error", e);
        }
        logger.info("addSetting cost time=" + (System.currentTimeMillis() - start));
    }

    public void addSetting(List<ZoyareSetting> zList) {
        long start = System.currentTimeMillis();
        try {
            Connection conn = this.getJdbcTemplate().getDataSource().getConnection();
            conn.setAutoCommit(false);
            PreparedStatement pstat = conn.prepareStatement(Save_ZoyareSetting_SQL);
            for (ZoyareSetting zoy : zList) {
                pstat.setString(1, zoy.getSetid());
                pstat.setString(2, zoy.getNote());
                pstat.setString(3, zoy.getContent());
                pstat.setString(4, zoy.getNote());
                pstat.setString(5, zoy.getContent());
                pstat.addBatch();
            }
            pstat.executeBatch();
            conn.commit();
            pstat.close();
            conn.setAutoCommit(true);
        } catch (Exception e) {
            logger.error("save error", e);
        }
        logger.info("addSetting list " + zList.size() + " cost time=" + (System.currentTimeMillis() - start));
    }

    public List<ZoyareSetting> findAllSettings() {
        List<ZoyareSetting> data = new ArrayList<ZoyareSetting>();
        long start = System.currentTimeMillis();
        try {
            Connection conn = this.getJdbcTemplate().getDataSource().getConnection();
            PreparedStatement pstat = conn.prepareStatement(Query_All_Settings);
            ResultSet rs = pstat.executeQuery();
            ZoyareSetting zoy = null;
            while (rs.next()) {
                String setid = rs.getString("setid");
                String note = rs.getString("note");
                String content = rs.getString("content");
                zoy = new ZoyareSetting(setid, note, content);
                data.add(zoy);
            }
            rs.close();
            pstat.close();
        } catch (Exception e) {
            logger.error("save error", e);
        }
        logger.info("findAllSettings cost time=" + (System.currentTimeMillis() - start));
        return data;
    }

    public Map<String, String> findAllSettingsMap() {
        Map<String, String> data = new HashMap<String, String>();
        long start = System.currentTimeMillis();
        try {
            Connection conn = this.getJdbcTemplate().getDataSource().getConnection();
            PreparedStatement pstat = conn.prepareStatement(Query_All_Settings);
            ResultSet rs = pstat.executeQuery();
            while (rs.next()) {
                String setid = rs.getString("setid");
                String content = rs.getString("content");
                data.put(setid, content);
            }
            rs.close();
            pstat.close();
        } catch (Exception e) {
            logger.error("save error", e);
        }
        logger.info("findAllSettings cost time=" + (System.currentTimeMillis() - start));
        return data;
    }

}
