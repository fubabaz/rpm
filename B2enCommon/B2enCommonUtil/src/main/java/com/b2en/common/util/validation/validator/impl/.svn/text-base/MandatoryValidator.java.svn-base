package com.b2en.common.util.validation.validator.impl;

import com.b2en.common.util.validation.validator.AbstractValidator;

/**
 * MandatoryValidator.java 
 *
 * @author ej.park
 * @created 2016. 3. 15.
 *
 */
public class MandatoryValidator extends AbstractValidator {
	
	@Override
	public boolean execute() {
		if(this.value==null || this.value.isEmpty()){
			return false;
		}
		return true;
	}

	@Override
	public String getErrMessage() {
		if(this.errMsg == null){
			this.errMsg = "필수값이 누락되었습니다.";
		}
		return this.errMsg;
	}

}
