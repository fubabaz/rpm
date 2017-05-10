package com.b2en.common.util.validation.validator;

import com.b2en.common.util.validation.result.IValidatorResult;

public abstract class AbstractValidator implements IValidator, IValidatorResult {

	protected String name;
	protected String fieldName;
	protected int rowIdx;
	protected int colIdx;
	protected String value;
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
	
	public String getFieldName(){
		return this.fieldName;
	}
	
	/**
	 * @param fieldName the fieldName to set
	 */
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public int getRowIdx(){
		return this.rowIdx;
	}

	/**
	 * @param rowIdx the rowIdx to set
	 */
	public void setRowIdx(int rowIdx) {
		this.rowIdx = rowIdx;
	}

	public int getColIdx(){
		return this.colIdx;
	}
	
	/**
	 * @param colIdx the colIdx to set
	 */
	public void setColIdx(int colIdx) {
		this.colIdx = colIdx;
	}

	public String getValue(){
		return this.value;
	}
	
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @param errMsg the errMsg to set
	 */
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	
	public abstract boolean execute();
	
	public abstract String getErrMessage();
	
}
