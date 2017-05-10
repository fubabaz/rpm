/*
 * Copyright 2004-2016 (C) B2EN, Inc. All Rights Reserved.
 * This software is the proprietary information of B2EN.
 */
package com.b2en.framework.dao;

import com.b2en.framework.config.PropertyManager;
import com.b2en.framework.dao.ifs.ICommonDao;
import com.b2en.framework.dao.ifs.ICommonDaoFactory;
import com.b2en.framework.exception.extend.CommonDaoException;

/**
 * CommonDaoFactoryBuilder
 * 
 * @author ej.park
 *
 */
public class CommonDaoFactoryBuilder implements ICommonDaoFactoryBuilder, IDaoFactoryInitializer {
	
	private volatile static ICommonDaoFactoryBuilder me;
	private ICommonDao bizDao;
	
	private CommonDaoFactoryBuilder(){
		initialize();
	}
	
	public static ICommonDaoFactoryBuilder getInstance(){
		if(me==null){
			synchronized(CommonDaoFactoryBuilder.class){
				if(me==null){
					me = new CommonDaoFactoryBuilder();
				}
			}
		}
		return me;
	}
	
	@Override
	public ICommonDao getDao(){
		return this.bizDao;
	}

	@Override
	public void initialize() {
		try {
			ICommonDaoFactory commonDaoFactory = (ICommonDaoFactory)Class.forName(PropertyManager.getProperty("common.dao.factory")).newInstance();
			this.bizDao = commonDaoFactory.getDao();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			throw new CommonDaoException("common.dao.factory ==>"+PropertyManager.getProperty("common.dao.factory"), e);
		}
	}
	
}
