/*
 * Copyright 2004-2016 (C) B2EN, Inc. All Rights Reserved.
 * This software is the proprietary information of B2EN.
 */

package com.b2en.framework.exception;

public interface IExceptionProcessor {
	
	/**
	 * Run ExceptionProcessor
	 * 
	 * @param errorCode
	 * @param errorMessage
	 */
	public void run(String errorCode, String errorMessage);
	
	/**
	 * Run ExceptionProcessor
	 * 
	 * @param errorCode
	 * @param errorMessage
	 * @param throwable
	 */
	public void run(String errorCode, String errorMessage, Throwable throwable);
	
}
