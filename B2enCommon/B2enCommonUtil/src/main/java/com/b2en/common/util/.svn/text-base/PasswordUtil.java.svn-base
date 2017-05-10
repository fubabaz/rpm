/*
 * Copyright 2004-2016 (C) B2EN, Inc. All Rights Reserved.
 * This software is the proprietary information of B2EN.
 */
package com.b2en.common.util;

import com.b2en.framework.cipher.EncryptDecryptFactory;

/**
 * PasswordUtil
 * 
 * @author ej.park
 *
 */
public class PasswordUtil {
	
	public static String getEncPassword(String algorithm, String password){
		return EncryptDecryptFactory.getInstance().getEncrypt(algorithm, password);
	}
	
	public static String getDecPassword(String algorithm, String password){
		return EncryptDecryptFactory.getInstance().getDecrypt(algorithm, password);
	}
}
