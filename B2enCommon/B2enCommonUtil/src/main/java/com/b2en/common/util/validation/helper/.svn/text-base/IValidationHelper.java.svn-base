package com.b2en.common.util.validation.helper;

import java.util.List;

import com.b2en.common.util.validation.result.IValidatorResult;
import com.b2en.common.util.validation.validator.IListValidator;
import com.b2en.common.util.validation.validator.IValidator;

/**
 * IValidationHelper.java 
 *
 * @author ej.park
 * @created 2016. 3. 15.
 *
 */
public interface IValidationHelper {

	/**
	 * @param title
	 */
	public void setFieldNames(String... title);
	
	/**
	 * @param listValidator
	 */
	public void addListValidator(IListValidator listValidator);
	
	/**
	 * @param colIdx
	 * @param validator
	 */
	public void addValidator(int colIdx, IValidator validator);
	
	/**
	 * 
	 */
	public void execute();
	
	/**
	 * @return
	 */
	public boolean hasError();
	
	/**
	 * @return
	 */
	public List<IValidatorResult> getErrList();
}
