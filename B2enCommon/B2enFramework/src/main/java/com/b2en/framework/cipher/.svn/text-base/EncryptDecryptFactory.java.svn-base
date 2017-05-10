/*
 * Copyright 2004-2016 (C) B2EN, Inc. All Rights Reserved.
 * This software is the proprietary information of B2EN.
 */
package com.b2en.framework.cipher;

/**
 * EncryptDecryptFactory
 * 
 * @author ej.park
 *
 */
public class EncryptDecryptFactory {

	private volatile static EncryptDecryptFactory me = null;
	private static EncryptDecryptManager encryptDecryptManager = null;
	
	private EncryptDecryptFactory(){}
	
	public static EncryptDecryptFactory getInstance(){
		if(me==null){
			synchronized (EncryptDecryptFactory.class){
				if(me==null){
					me = new EncryptDecryptFactory();
					encryptDecryptManager = new EncryptDecryptManager();
				}
			}
		}
		return me;
	}
	
	/**
	 * @param algorithmStr
	 * @param value
	 * @return
	 */
	public String getEncrypt(String algorithmStr, String value) {
		return encryptDecryptManager.getEncrypt(algorithmStr, value);
	}
	
	/**
	 * @param algorithmStr
	 * @param value
	 * @return
	 */
	public String getDecrypt(String algorithmStr, String value){
		return encryptDecryptManager.getDecrypt(algorithmStr, value);
	}
}
