/*
 * Copyright 2004-2016 (C) B2EN, Inc. All Rights Reserved.
 * This software is the proprietary information of B2EN.
 */
package com.b2en.common.dao;

import com.b2en.common.exception.BizCommonException;
import com.b2en.framework.config.PropertyManager;

/**
 * BizDaoFactoryBuilder
 * 
 * @author ej.park
 *
 */
public class BizDaoFactoryBuilder {
	private volatile static BizDaoFactoryBuilder me;
	private IBizDaoFactory bizDaoFactory;
	
	private BizDaoFactoryBuilder(){
		try {
			bizDaoFactory = (IBizDaoFactory)Class.forName(PropertyManager.getProperty("biz.dao.factory")).newInstance();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			throw new BizCommonException("biz.dao.factory ==>"+PropertyManager.getProperty("biz.dao.factory"), e);
		}
	}
	
	public static BizDaoFactoryBuilder getInstance(){
		if(me==null){
			synchronized(BizDaoFactoryBuilder.class){
				if(me==null){
					me = new BizDaoFactoryBuilder();
				}
			}
		}
		return me;
	}
	
	public Object getDao(Class<?> interfaceClass){
		return bizDaoFactory.getDao(interfaceClass);
	}
}
