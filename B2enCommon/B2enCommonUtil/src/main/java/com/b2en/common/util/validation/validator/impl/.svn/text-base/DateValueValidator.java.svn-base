package com.b2en.common.util.validation.validator.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.b2en.common.util.validation.validator.AbstractValidator;


/**
 * DateValueValidator.java 
 *
 * @author ej.park
 * @created 2016. 3. 15.
 *
 */
public class DateValueValidator extends AbstractValidator {
	
	private String pattern;
	private boolean allowBlank;
	private SimpleDateFormat sdf;
	
	public DateValueValidator(String pattern){
		this(pattern, false);
	}
	
	public DateValueValidator(String pattern, boolean allowBlank){
		this.pattern = pattern;
		this.allowBlank = allowBlank;
		this.sdf = new SimpleDateFormat(pattern);
	}
	
	
	@Override
	public boolean execute() {
		if(allowBlank && (this.value==null || this.value.isEmpty())){
			return true;
		}
		try {
			sdf.parse(this.value);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}

	@Override
	public String getErrMessage() {
		if(this.errMsg == null){
			this.errMsg = "날짜형식이 맞지 않습니다.["+pattern+"]";
		}
		return this.errMsg;
	}

}
