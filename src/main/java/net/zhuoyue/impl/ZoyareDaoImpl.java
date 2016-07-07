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
    
    private static String Save_ZoyareSetting_SQL = "insert into zoyare_settings(setid, pageid, note, content) values(?,?,?,?) on duplicate key update note=?, content=?";
    private static String Query_Settings_Pageid = "select setid, note, content, updatetime from zoyare_settings where pageid='%s' order by updatetime desc";

    public boolean addSetting(ZoyareSetting zoy) {
        long start = System.currentTimeMillis();
        try {
            Connection conn = this.getJdbcTemplate().getDataSource().getConnection();
            conn.setAutoCommit(true);
            PreparedStatement pstat = conn.prepareStatement(Save_ZoyareSetting_SQL);
            pstat.setString(1, zoy.getSetid());
            pstat.setString(2, zoy.getPageid());
            pstat.setString(3, zoy.getNote());
            pstat.setString(4, zoy.getContent());
            pstat.setString(5, zoy.getNote());
            pstat.setString(6, zoy.getContent());
            pstat.execute();
            pstat.close();
        } catch (Exception e) {
            logger.error("save error", e);
            return false;
        }
        logger.info("addSetting cost time=" + (System.currentTimeMillis() - start));
        return true;
    }

    public void addSetting(List<ZoyareSetting> zList) {
        long start = System.currentTimeMillis();
        try {
            Connection conn = this.getJdbcTemplate().getDataSource().getConnection();
            conn.setAutoCommit(false);
            PreparedStatement pstat = conn.prepareStatement(Save_ZoyareSetting_SQL);
            for (ZoyareSetting zoy : zList) {
                pstat.setString(1, zoy.getSetid());
                pstat.setString(2, zoy.getPageid());
                pstat.setString(3, zoy.getNote());
                pstat.setString(4, zoy.getContent());
                pstat.setString(5, zoy.getNote());
                pstat.setString(6, zoy.getContent());
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

    public List<ZoyareSetting> findAllSettings(String pageid) {
        List<ZoyareSetting> data = new ArrayList<ZoyareSetting>();
        long start = System.currentTimeMillis();
        try {
            Connection conn = this.getJdbcTemplate().getDataSource().getConnection();
            PreparedStatement pstat = conn.prepareStatement(String.format(Query_Settings_Pageid, pageid));
            ResultSet rs = pstat.executeQuery();
            ZoyareSetting zoy = null;
            while (rs.next()) {
                String setid = rs.getString("setid");
                String note = rs.getString("note");
                String content = rs.getString("content");
                String updatetime = rs.getString("updatetime");
                zoy = new ZoyareSetting(setid, pageid, note, content, updatetime);
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

    public Map<String, String> findAllSettingsMap(String pageid) {
        Map<String, String> data = new HashMap<String, String>();
        long start = System.currentTimeMillis();
        try {
            Connection conn = this.getJdbcTemplate().getDataSource().getConnection();
            PreparedStatement pstat = conn.prepareStatement(String.format(Query_Settings_Pageid, pageid));
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
