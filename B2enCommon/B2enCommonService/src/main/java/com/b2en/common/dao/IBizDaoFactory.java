/*
 * Copyright 2004-2016 (C) B2EN, Inc. All Rights Reserved.
 * This software is the proprietary information of B2EN.
 */
package com.b2en.common.dao;

/**
 * IBizDaoInfos
 * 
 * @author ej.park
 *
 */
public interface IBizDaoFactory {

	public Object getDao(Class<?> interfaceClass);
}
