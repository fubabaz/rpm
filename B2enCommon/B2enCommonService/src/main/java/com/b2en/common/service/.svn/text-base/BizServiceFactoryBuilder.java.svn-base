/*
 * Copyright 2004-2016 (C) B2EN, Inc. All Rights Reserved.
 * This software is the proprietary information of B2EN.
 */
package com.b2en.common.service;

import com.b2en.common.exception.BizCommonException;
import com.b2en.framework.config.PropertyManager;

/**
 * BizServiceFactoryBuilder
 * 
 * @author ej.park
 *
 */
public class BizServiceFactoryBuilder {
	private volatile static BizServiceFactoryBuilder me;
	private IBizServiceFactory serviceFactory;
	
	private BizServiceFactoryBuilder(){
		try {
			serviceFactory = (IBizServiceFactory)Class.forName(PropertyManager.getProperty("biz.service.factory")).newInstance();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			throw new BizCommonException("biz.service.factory ==>"+PropertyManager.getProperty("biz.service.factory"), e);
		}
	}
	
	public static BizServiceFactoryBuilder getInstance(){
		if(me==null){
			synchronized(BizServiceFactoryBuilder.class){
				if(me==null){
					me = new BizServiceFactoryBuilder();
				}
			}
		}
		return me;
	}
	
	public Object getService(Class<?> interfaceClass){
		return serviceFactory.getService(interfaceClass);
	}
}
