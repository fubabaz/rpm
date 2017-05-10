/*
 * Copyright 2004-2016 (C) B2EN, Inc. All Rights Reserved.
 * This software is the proprietary information of B2EN.
 */
package com.b2en.common.service;

/**
 * IBizServiceFactory
 * 
 * @author ej.park
 *
 */
public interface IBizServiceFactory {
	
	public Object getService(Class<?> interfaceClass);
}
