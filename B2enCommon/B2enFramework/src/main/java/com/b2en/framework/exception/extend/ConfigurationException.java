/*
 * Copyright 2004-2016 (C) B2EN, Inc. All Rights Reserved.
 * This software is the proprietary information of B2EN.
 */
package com.b2en.framework.exception.extend;

import com.b2en.framework.exception.AbstractSystemException;

/**
 * ConfigurationException
 * 
 * @author ej.park
 *
 */
public class ConfigurationException extends AbstractSystemException {

	private static final long serialVersionUID = 1L;

	public ConfigurationException(String errorMessage, Throwable throwable) {
		super("SYS_CFG001", errorMessage, throwable);
	}
}
