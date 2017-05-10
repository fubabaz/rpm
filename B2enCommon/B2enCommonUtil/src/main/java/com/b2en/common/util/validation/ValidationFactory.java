package com.b2en.common.util.validation;

import java.util.List;

import com.b2en.common.util.validation.helper.IValidationHelper;
import com.b2en.common.util.validation.helper.ValidationManager;

/**
 * ValidationFactory.java 
 *
 * @author ej.park
 * @created 2016. 3. 15.
 *
 */
public class ValidationFactory {

	/**
	 * @param dataList
	 * @return
	 */
	public static IValidationHelper getValidationManager(List<String[]> dataList){
		return getValidationManager("", dataList, 0);
	}
	
	/**
	 * @param dataList
	 * @param startIdx
	 * @return
	 */
	public static IValidationHelper getValidationManager(List<String[]> dataList, int startIdx){
		return getValidationManager("", dataList, startIdx);
	}
	
	/**
	 * @param name
	 * @param dataList
	 * @return
	 */
	public static IValidationHelper getValidationManager(String name, List<String[]> dataList){
		return getValidationManager(name, dataList, 0);
	}
	
	/**
	 * @param name
	 * @param dataList
	 * @param startIdx
	 * @return
	 */
	public static IValidationHelper getValidationManager(String name, List<String[]> dataList, int startIdx){
		ValidationManager validationMgr = new ValidationManager();
		validationMgr.setName(name);
		validationMgr.setDataList(dataList);
		validationMgr.setStartIdx(startIdx);
		return validationMgr;
	}
}
