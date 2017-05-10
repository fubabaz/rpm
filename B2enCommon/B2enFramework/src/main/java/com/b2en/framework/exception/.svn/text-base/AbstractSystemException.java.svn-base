/*
 * Copyright 2004-2016 (C) B2EN, Inc. All Rights Reserved.
 * This software is the proprietary information of B2EN.
 */

package com.b2en.framework.exception;

/**
 * AbstractSystemException
 * 
 * @author ej.park
 *
 */
public abstract class AbstractSystemException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public AbstractSystemException(String errorCode, String errorMessage) {
		super(errorMessage);
		exceptionProcess(errorCode, errorMessage, null);
	}
	
	public AbstractSystemException(String errorCode, String errorMessage, Throwable throwable) {
		super(errorMessage, throwable);
		exceptionProcess(errorCode, errorMessage, throwable);
	}
	
	private void exceptionProcess(String errorCode, String errorMessage, Throwable throwable){
		ExceptionManager.run(errorCode, errorMessage, throwable);
	}
}
