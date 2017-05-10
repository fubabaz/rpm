package com.b2en.common.util;

import org.junit.Test;

public class PasswordUtilTest {

	@Test
	public void test() {
		String algorithm = "scott";
		String password = "tiger";
		String encPassword = PasswordUtil.getEncPassword(algorithm, password);
		System.out.println(encPassword);
		System.out.println(PasswordUtil.getDecPassword(algorithm, encPassword));
	}

}
