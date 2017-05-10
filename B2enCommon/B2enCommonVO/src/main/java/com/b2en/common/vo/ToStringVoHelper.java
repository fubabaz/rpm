/*
 * Copyright 2004-2016 (C) B2EN, Inc. All Rights Reserved.
 * This software is the proprietary information of B2EN.
 */
package com.b2en.common.vo;

import java.lang.reflect.Field;

/**
 * ToStringVoHelper.java 
 *
 * Common toString Logging.
 * StringBuffer - reflection
 * 
 * @author ej.park
 * @created 2016. 2. 26.
 *
 */
public class ToStringVoHelper {
	
	/**
	 * @param object
	 * @return
	 */
	public static String toString(Object object){
		StringBuffer buffer = new StringBuffer();
		Class<? extends Object> clazz = object.getClass();
		Field[] fields = clazz.getDeclaredFields();
		buffer.append("[").append(clazz.getName()).append("]");
		buffer.append("{");
		for(int i=0; fields!=null && i<fields.length; i++){
			try {
				fields[i].setAccessible(true);
				if(i==0){
					buffer.append(fields[i].getName()).append("=").append(String.valueOf(fields[i].get(object)));
				}else{
					buffer.append(", ").append(fields[i].getName()).append("=").append(String.valueOf(fields[i].get(object)));
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				//Ignore.
				buffer.append("==>IllegalArgumentException/IllegalAccessException");
			}
		}
		buffer.append("}");
		return buffer.toString();
	}
}
