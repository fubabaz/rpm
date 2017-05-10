/*
 * Copyright 2004-2016 (C) B2EN, Inc. All Rights Reserved.
 * This software is the proprietary information of B2EN.
 */

package com.b2en.framework.exception;

import java.util.ArrayList;
import java.util.List;

/**
 * ExceptionManager
 * 
 * @author ej.park
 *
 */
public class ExceptionManager {
	private static List<IExceptionProcessor> exceptionProcessorList = new ArrayList<IExceptionProcessor>();
	
	public static void addExceptionProcessor(IExceptionProcessor exceptionProcessor){
		exceptionProcessorList.add(exceptionProcessor);
	}
	
	public static void run(String errorCode, String errorMessage, Throwable throwable){
		for(IExceptionProcessor exceptionProcessor : exceptionProcessorList){
			if(throwable==null){
				exceptionProcessor.run(errorCode, errorMessage);
			}else{
				exceptionProcessor.run(errorCode, errorMessage, throwable);
			}
		}
	}
}
