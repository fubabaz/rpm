package com.b2en.common.util.validation.result;

/**
 * IValidatorResult.java 
 *
 * @author ej.park
 * @created 2016. 3. 15.
 *
 */
public interface IValidatorResult {

	/**
	 * @return
	 */
	public String getName();
	
	/**
	 * @return
	 */
	public String getFieldName();
	
	/**
	 * @return
	 */
	public int getRowIdx();
	
	/**
	 * @return
	 */
	public int getColIdx();
	
	/**
	 * @return
	 */
	public String getValue();
	
	/**
	 * @return
	 */
	public String getErrMessage();

}
