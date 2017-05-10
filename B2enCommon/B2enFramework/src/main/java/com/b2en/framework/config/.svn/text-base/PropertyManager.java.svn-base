/*
 * Copyright 2004-2016 (C) B2EN, Inc. All Rights Reserved.
 * This software is the proprietary information of B2EN.
 */

package com.b2en.framework.config;

import java.util.Properties;

/**
 * PropertyManager
 * 
 * @author ej.park
 *
 */
public class PropertyManager {
	
	private static Properties properties = ConfigFactory.getInstance().getProperty();
	
	/**
	 * @param key
	 * @return
	 */
	public static String getProperty(String key){
		return properties.getProperty(key);
	}
	
	/**
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static String getProperty(String key, String defaultValue){
		return properties.getProperty(key, defaultValue);
	}
	
	/**
	 * @param key
	 * @param value
	 */
	public static void setProperty(String key, String value){
		properties.setProperty(key, value);
	}
	
	/**
	 * @return
	 */
	public static Properties getProperties(){
		return properties;
	}
	
}
