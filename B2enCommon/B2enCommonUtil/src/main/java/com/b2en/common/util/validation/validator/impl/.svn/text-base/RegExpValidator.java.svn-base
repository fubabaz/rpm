package com.b2en.common.util.validation.validator.impl;

import java.util.regex.Pattern;

import com.b2en.common.util.validation.validator.AbstractValidator;

public class RegExpValidator extends AbstractValidator {

	private String regExpStr;
	private boolean withTrim;
	
	public RegExpValidator(String regExpStr){
		this.regExpStr = regExpStr;
		this.withTrim = false;
	}
	
	public RegExpValidator(String regExpStr, boolean withTrim){
		this.regExpStr = regExpStr;
		this.withTrim = withTrim;
	}
	
	@Override
	public boolean execute() {
		String value = this.value;
		if(this.withTrim){
			if(value!=null){
				value = value.trim();
			}
		}
		return Pattern.matches(regExpStr, value);
	}

	@Override
	public String getErrMessage() {
		if(this.errMsg == null){
			this.errMsg = "형식이 올바르지 않습니다.";
		}
		return this.errMsg+"["+this.value+"]";
	}

}
