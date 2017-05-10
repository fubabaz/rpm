/*
 * Copyright 2004-2016 (C) B2EN, Inc. All Rights Reserved.
 * This software is the proprietary information of B2EN.
 */
package com.b2en.common.service;

import com.b2en.common.dao.BizDaoFactoryBuilder;

/**
 * AbstractCommonService
 * 
 * @author ej.park
 *
 */
public abstract class AbstractCommonService {

	protected Object getDao(Class<?> daoClass){
		return BizDaoFactoryBuilder.getInstance().getDao(daoClass);
	}
}
