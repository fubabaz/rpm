package com.b2en.framework.cipher;

import org.junit.Test;

import com.b2en.framework.cipher.EncryptDecryptFactory;

public class EncryptiDecryptTest {

	@Test
	public void test() {
		String algorithmStr = "B@ENSDQ";
		String value = "admin1234";
		String encryptedVal = EncryptDecryptFactory.getInstance().getEncrypt(algorithmStr, value);
		System.out.println(encryptedVal);
		System.out.println(EncryptDecryptFactory.getInstance().getDecrypt(algorithmStr, encryptedVal));
	}

}
