/*
 * Copyright 2004-2016 (C) B2EN, Inc. All Rights Reserved.
 * This software is the proprietary information of B2EN.
 */
package com.b2en.common.util;

import com.b2en.framework.conn.PingTestRunner;
import com.b2en.framework.conn.PingTestRunner.PING_RST;

/**
 * PingTestUtil
 * 
 * @author ej.park
 *
 */
public class PingTestUtil {
	
	/**
	 * @param host
	 * @param port
	 * @return
	 */
	public static boolean isPing(String host, int port){
		
		PING_RST result = PingTestRunner.ping(host, port, 3000);
		return result.isSuccess();
	}
	
}
