/*
 * Copyright 2004-2016 (C) B2EN, Inc. All Rights Reserved.
 * This software is the proprietary information of B2EN.
 */
package com.b2en.common.util;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Map;

/**
 * ReflectionUtil
 * 
 * @author ej.park
 *
 */
public class ReflectionUtil {

	public static void setModelValue(Map<String,Object> valueMap, Object targetVOObj){
		Class<?> clazz = targetVOObj.getClass();
		Iterator<String> keyItr = valueMap.keySet().iterator();
		while(keyItr.hasNext()){
			String key = keyItr.next();
			Object value = valueMap.get(key);
			Field field = findField(clazz, key);
			if(field!=null){
				setField(field, targetVOObj, value);				
			}
		}
	}
	
	public static Field findField(Class<?> clazz, String fieldName) {
		Field field = null;
		try {
			field = clazz.getDeclaredField(fieldName);
		} catch (NoSuchFieldException | SecurityException e) {
			System.out.println("findFiled Error Class : "+clazz.getName());
			System.out.println("findFiled Error Field : "+fieldName);
		}
		return field;
	}
	
	public static void setField(Field fleld, Object targetVOObj, Object value) {
		try {
			fleld.setAccessible(true);
			fleld.set(targetVOObj, value);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			System.out.println("setField Error Field : "+fleld);
			System.out.println("setField Error Target : "+targetVOObj);
			System.out.println("setField Error Value : "+value);
		}
	}
	
}
