package com.b2en.common.util.validation.result;

import java.util.ArrayList;
import java.util.List;

import com.b2en.common.util.validation.validator.AbstractListValidator;
import com.b2en.common.util.validation.validator.AbstractValidator;

public class ValidationResult implements IValidatorResult {
	
	private String name;
	private String fieldName;
	private int rowIdx;
	private int colIdx;
	private String value;
	private List<String> errMsgList = new ArrayList<String>();
	
	public ValidationResult(AbstractListValidator validator){
		this.name = validator.getName();
		this.errMsgList.add(validator.getErrMessage());
	}
	
	public ValidationResult(AbstractValidator validator){
		this.name = validator.getName();
		this.fieldName = validator.getFieldName();
		this.rowIdx = validator.getRowIdx();
		this.colIdx = validator.getColIdx();
		this.value = validator.getValue();
		this.errMsgList.add(validator.getErrMessage());
	}
	
	public void addErrMsg(String errMsg){
		this.errMsgList.add(errMsg);
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getFieldName(){
		return this.fieldName;
	}
	
	public int getRowIdx(){
		return this.rowIdx;
	}
	
	public int getColIdx(){
		return this.colIdx;
	}
	
	public String getValue(){
		return this.value;
	}
	
	public String getErrMessage(){
		StringBuffer sb = new StringBuffer();
		for(int i=0; i<this.errMsgList.size(); i++){
			if(i==0){
				sb.append(this.errMsgList.get(i));
			}else{
				sb.append("\n");
				sb.append(this.errMsgList.get(i));
			}
		}
		return sb.toString();
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append(" name:");
		sb.append(name);
		sb.append(" fieldName:");
		sb.append(fieldName);
		sb.append(" rowIdx:");
		sb.append(rowIdx);
		sb.append(" colIdx:");
		sb.append(colIdx);
		sb.append(" value:");
		sb.append(value);
		sb.append(" message:");
		sb.append(getErrMessage());
		return sb.toString();
	}
}
