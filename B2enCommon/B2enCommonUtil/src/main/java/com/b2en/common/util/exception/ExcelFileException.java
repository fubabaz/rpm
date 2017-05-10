/*
 * Copyright 2004-2016 (C) B2EN, Inc. All Rights Reserved.
 * This software is the proprietary information of B2EN.
 */
package com.b2en.common.util.exception;

import com.b2en.framework.exception.AbstractSystemException;

/**
 * ExcelFileException.java 
 *
 * @author ej.park
 * @created 2016. 3. 2.
 *
 */
public class ExcelFileException extends AbstractSystemException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExcelFileException(String errorMessage) {
		super("SYS_FILE001", errorMessage);		
	}
	
	public ExcelFileException(String errorMessage, Throwable throwable) {
		super("SYS_FILE001", errorMessage, throwable);		
	}
}
