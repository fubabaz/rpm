package com.b2en.common.util.validation.validator.impl;

import java.util.List;

import com.b2en.common.util.validation.validator.AbstractValidator;


/**
 * ContainsValueValidator.java 
 *
 * @author ej.park
 * @created 2016. 3. 15.
 *
 */
public class ContainsValueValidator extends AbstractValidator {
	
	private List<String> list;
	
	public ContainsValueValidator(List<String> list){
		this(list, false);
	}
	
	public ContainsValueValidator(List<String> list, boolean allowBlank){
		this.list = list;
		if(allowBlank){
			this.list.add("");
		}
	}
	
	@Override
	public boolean execute() {
		if(this.list.contains(this.value)){
			return true;
		}
		return false;
	}

	@Override
	public String getErrMessage() {
		if(this.errMsg == null){
			this.errMsg = "허용값이 아닙니다.";
		}
		return this.errMsg;
	}

}
