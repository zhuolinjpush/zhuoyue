package net.zhuoyue.helper;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DBPool {
	
	private static Logger logger = LogManager.getLogger(DBPool.class.getName());
	
	private static String DBKEY = "statsdb";
	private static DBPool pool;
	private ComboPooledDataSource dataSource;
	
	static {
		pool = new DBPool();
	}
	
	public final static DBPool getInstance() {
		return pool;
	}
	
	public DBPool() {
		initDB();
	}

	public void initDB() {
		try {
			this.dataSource = new ComboPooledDataSource(DBKEY);
		} catch (Exception e) {
			logger.error("init db error", e);
		}
	}

	public final Connection getConnection() {
		try {
			return this.dataSource.getConnection();
		} catch (SQLException e){
			logger.error("sql conn error", e);
			initDB();
		} catch (Exception e){
			logger.error("get conn error", e);
		}
		return null;
	}
}
