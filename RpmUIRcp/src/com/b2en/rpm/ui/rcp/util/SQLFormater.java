package com.b2en.rpm.ui.rcp.util;

import kry.sql.format.ISqlFormat;
import kry.sql.format.SqlFormat;

import org.apache.log4j.Logger;

/**
 *
 */
public class SQLFormater {

	private static final Logger logger = Logger.getLogger(SQLFormater.class);
	
	/**
	 * @param strOriginalSQL
	 * @return
	 * @throws Exception
	 */
	public static String format(String strOriginalSQL) {
		
		try {
			ISqlFormat formatter = new SqlFormat(SQLFormatRule.getSqlFormatRule());
			return formatter.format(strOriginalSQL, 0);
		} catch (Exception e) {
			logger.error("sql format exception", e);
			return strOriginalSQL;
		}
	}
}
