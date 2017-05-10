/*
 * Copyright 2004-2016 (C) B2EN, Inc. All Rights Reserved.
 * This software is the proprietary information of B2EN.
 */

package com.b2en.framework.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.b2en.framework.exception.extend.ConfigurationException;


/**
 * ConfigFactory
 * 
 * @author ej.park
 *
 */
public class ConfigFactory {

	private volatile static ConfigFactory me;
	private Properties properties;
	
	private ConfigFactory(){
		setConfiguration();
	}
	
	/**
	 * @return
	 */
	public static ConfigFactory getInstance(){
		if(me==null){
			synchronized (ConfigFactory.class){
				if(me==null){
					me = new ConfigFactory();
				}
			}
		}
		return me;
	}
	
	private void setConfiguration(){
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(System.getProperty("config.sysenv.file", "systemenv.properties"));
		this.properties = System.getProperties();
		try {
			this.properties.load(is);
			
		} catch (IOException e) {
			throw new ConfigurationException("File Not Found. Path>>systemenv.properties", e);
		}finally{
			if(is!=null){
				try {
					is.close();
				} catch (IOException e) {
					//ignore
				}
			}
		}
	}
	
	/**
	 * @return Properties
	 */
	public Properties getProperty(){
		return this.properties;
	}
	
}
