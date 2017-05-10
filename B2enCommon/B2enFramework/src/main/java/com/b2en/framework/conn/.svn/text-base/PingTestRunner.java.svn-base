/*
 * Copyright 2004-2016 (C) B2EN, Inc. All Rights Reserved.
 * This software is the proprietary information of B2EN.
 */
package com.b2en.framework.conn;

import java.net.InetAddress;
import java.net.Socket;

/**
 * PingTestRunner
 * 
 * @author ej.park
 *
 */
public class PingTestRunner implements Runnable {
	
	private String host;
	private int port;
	private PING_RST result;
	
	public enum PING_RST {
		HOST_ERR("Host Error.", false),
		CONNECT_ERR("Connection Error.", false),
		SUCCESS("Success Ping Test.", true)
		;
		
		private String msg;
		private boolean isSuccess;
		
		PING_RST(String msg, boolean isSuccess){
			this.msg = msg;
			this.isSuccess = isSuccess;
		}
		
		public String getMsg(){
			return this.msg;
		}
		
		public boolean isSuccess(){
			return this.isSuccess;
		}
	}
	
	private PingTestRunner(String host, int port){
		this.host = host;
		this.port = port;
	}
	
	@Override
	public void run() {
		try {
			result = PING_RST.HOST_ERR;
			InetAddress addr = InetAddress.getByName(host);
			
			result = PING_RST.CONNECT_ERR;
			Socket s = new Socket(addr, port);
			
			result = PING_RST.SUCCESS;
			s.close();
		} catch (Exception e) {
		}
	}
	
	public static PING_RST ping(String host, int port, long maxWait) {
		PingTestRunner pingRunner = new PingTestRunner(host, port);
		try {
			Thread t = new Thread(pingRunner);
			t.setDaemon(true);
			t.start();
			t.join(maxWait);
			
		} catch (InterruptedException ie) {
			
		}
		return pingRunner.result;
	}
}
