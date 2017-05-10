/*
 * Copyright 2004-2016 (C) B2EN, Inc. All Rights Reserved.
 * This software is the proprietary information of B2EN.
 */
package com.b2en.framework.cipher;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

/**
 * EncryptDecryptManager
 * 
 * @author ej.park
 *
 */
public class EncryptDecryptManager {
	
	/**
	 * @param algorithmStr
	 * @param value
	 * @return
	 */
	public String getEncrypt(String algorithmStr, String value) {
		String rtnVal = "";
		StandardPBEStringEncryptor textEncryptor = new StandardPBEStringEncryptor();
		textEncryptor.setPassword(algorithmStr);
		rtnVal = textEncryptor.encrypt(value);
		return rtnVal;
	}
	
	/**
	 * @param algorithmStr
	 * @param value
	 * @return
	 */
	public String getDecrypt(String algorithmStr, String value){
		String rtnVal = "";
		StandardPBEStringEncryptor textEncryptor = new StandardPBEStringEncryptor();
		textEncryptor.setPassword(algorithmStr);
		rtnVal = textEncryptor.decrypt(value);
		return rtnVal;
	}
}
