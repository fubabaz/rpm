package com.b2en.rpm.ui.rcp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.b2en.common.util.PingTestUtil;
import com.b2en.framework.config.PropertyManager;
import com.b2en.framework.dao.CommonDaoFactoryBuilder;
import com.b2en.framework.dao.IDaoFactoryInitializer;
import com.b2en.rpm.ui.rcp.Activator;
import com.b2en.rpm.ui.rcp.cons.RpmKeyStore;

public class DBInfoUtil {
	
	private static Logger logger = Logger.getLogger(DBInfoUtil.class);
	
	public static String getOwner(){
		return PropertyManager.getProperty(RpmKeyStore.DB_OWNER);
	}
	
	public static String getUser(){
		return PropertyManager.getProperty(RpmKeyStore.DB_USERNAME);
	}
	
	public enum Conn_Result {
		SUCCESS(true, "Connection Success.")
		,FAIL(false, "")
		;
		
		private boolean result;
		private String message;
		private Conn_Result(boolean result, String message){
			this.result = result;
			this.message = message;
		}
		
		public boolean isSuccess(){
			return this.result;
		}
		
		public void setMessage(String message){
			this.message = message;
		}
		
		public String getMessage(){
			return this.message;
		}
	}
	public static Conn_Result isConnected(String host, String port, String sid, String username, String password){
		Connection conn = null; 
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(getUrl(host,port,sid), username, password);   
			return Conn_Result.SUCCESS;
		} catch (ClassNotFoundException e) {
			//Ignore;
			if(logger.isDebugEnabled()){
				logger.debug(e.getMessage());
			}
			Conn_Result.FAIL.setMessage(e.getMessage());
			return Conn_Result.FAIL;
		} catch (SQLException e){
			//Ignore;
			if(logger.isDebugEnabled()){
				logger.debug(e.getMessage());
			}
			Conn_Result.FAIL.setMessage(e.getMessage());
			return Conn_Result.FAIL;
		}finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// ignore
				}
			}
		}
	}
	
	public static String getUrl(String host, String port, String sid){
		StringBuffer sb = new StringBuffer();
		sb.append("jdbc:oracle:thin:@//").append(host).append(":").append(port).append("/").append(sid);
		return sb.toString();
	}
	
	public static void updateDBInfoByPref(){
		String host = Activator.getDefault().getPreferenceStore().getString(RpmKeyStore.DB_HOST);
		String port = Activator.getDefault().getPreferenceStore().getString(RpmKeyStore.DB_PORT);
		String sid = Activator.getDefault().getPreferenceStore().getString(RpmKeyStore.DB_SID);
		PropertyManager.setProperty(RpmKeyStore.DB_URL, getUrl(host,port,sid));
		
		String username = Activator.getDefault().getPreferenceStore().getString(RpmKeyStore.DB_USERNAME);
		String password = Activator.getDefault().getPreferenceStore().getString(RpmKeyStore.DB_PASSWORD);
		PropertyManager.setProperty(RpmKeyStore.DB_USERNAME, username);
		PropertyManager.setProperty(RpmKeyStore.DB_PASSWORD, password);
		
		String owner = Activator.getDefault().getPreferenceStore().getString(RpmKeyStore.DB_OWNER);
		PropertyManager.setProperty(RpmKeyStore.DB_OWNER, owner);
		PropertyManager.setProperty(RpmKeyStore.DB_DRIVER, "oracle.jdbc.OracleDriver");
		
		((IDaoFactoryInitializer)CommonDaoFactoryBuilder.getInstance()).initialize();
	}
	
	public static boolean checkDBConnectionByPref(){
		String host = Activator.getDefault().getPreferenceStore().getString(RpmKeyStore.DB_HOST);
		String port = Activator.getDefault().getPreferenceStore().getString(RpmKeyStore.DB_PORT);
		String sid = Activator.getDefault().getPreferenceStore().getString(RpmKeyStore.DB_SID);
		String username = Activator.getDefault().getPreferenceStore().getString(RpmKeyStore.DB_USERNAME);
		String password = Activator.getDefault().getPreferenceStore().getString(RpmKeyStore.DB_PASSWORD);
		
		if(!RpmRcpUtil.isIp(host)){
			return false;
		}
		if(!RpmRcpUtil.isSimpleNumber(port)){
			return false;
		}
		if(!PingTestUtil.isPing(host, Integer.parseInt(port))){
			return false;
		}
		Conn_Result result = isConnected(host, port, sid, username, password);
		if(!result.isSuccess()){
			return false;
		}
		return true;
	}
}
