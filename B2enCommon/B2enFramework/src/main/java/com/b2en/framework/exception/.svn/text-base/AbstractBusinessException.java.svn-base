/*
 * Copyright 2004-2016 (C) B2EN, Inc. All Rights Reserved.
 * This software is the proprietary information of B2EN.
 */

package com.b2en.framework.exception;

/**
 * AbstractBusinessException
 * 
 * @author ej.park
 *
 */
public abstract class AbstractBusinessException extends RuntimeException {
	
	private static final long serialVersionUID = -2343342974968418298L;
	
	public AbstractBusinessException(String errorCode, String errorMessage) {
		super(errorMessage);
		exceptionProcess(errorCode, errorMessage, null);
	}
	
	public AbstractBusinessException(String errorCode, String errorMessage, Throwable throwable) {
		super(errorMessage, throwable);
		exceptionProcess(errorCode, errorMessage, throwable);
	}
	
	private void exceptionProcess(String errorCode, String errorMessage, Throwable throwable){
		ExceptionManager.run(errorCode, errorMessage, throwable);
	}
	
}
