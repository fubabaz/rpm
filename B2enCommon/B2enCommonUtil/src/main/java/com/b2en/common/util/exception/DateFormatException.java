/*
 * Copyright 2004-2016 (C) B2EN, Inc. All Rights Reserved.
 * This software is the proprietary information of B2EN.
 */
package com.b2en.common.util.exception;

import com.b2en.framework.exception.AbstractSystemException;

/**
 * DateFormatException
 * 
 * @author ej.park
 *
 */
public class DateFormatException extends AbstractSystemException {

	private static final long serialVersionUID = 1L;

	public DateFormatException(String errorMessage) {
		super("SYS_FMT001", errorMessage);
	}
	
	public DateFormatException(String errorMessage, Throwable throwable) {
		super("SYS_FMT001", errorMessage, throwable);
	}

}
