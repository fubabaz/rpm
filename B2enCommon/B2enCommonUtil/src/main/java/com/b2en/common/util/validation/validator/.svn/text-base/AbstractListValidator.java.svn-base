package com.b2en.common.util.validation.validator;

import java.util.List;

/**
 * AbstractListValidator.java 
 *
 * @author ej.park
 * @created 2016. 3. 15.
 *
 */
public abstract class AbstractListValidator implements IListValidator {

	protected String name;
	protected List<String[]> dataList;
	protected int startIdx;
	protected String errMsg;
	
	public String getName(){
		return this.name;
	}
	
	/**
	 * @param name
	 */
	public void setName(String name){
		this.name = name;
	}
	
	/**
	 * @param values
	 */
	public void setDataList(List<String[]> dataList){
		this.dataList = dataList;
	}
	
	/**
	 * @param startIdx
	 */
	public void setStartIdx(int startIdx){
		this.startIdx = startIdx;
	}
	
	@Override
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	
	public abstract boolean execute();
	
	public abstract String getErrMessage();
}
