package com.b2en.common.util.validation.cons;

/**
 * RegExpValConst.java 
 *
 * @author ej.park
 * @created 2016. 3. 21.
 *
 */
public enum RegExpValConst {

	BLANK_YN("\\p{Space}|Y|N*")
	,BLANK_NUM("\\p{Space}|^[0-9]*$")
	,ENG_NUM("^[A-Za-z0-9]*$")
	,BLANK_ENG_NUM("\\p{Space}|^[A-Za-z0-9]*$")
	,COLUMN_NAME("^[a-zA-Z0-9_]*$")
	,NOT_KOREAN("^[^가-힣|]*$")
	;
	
	private String regExp;
	private RegExpValConst(String regExp){
		this.regExp = regExp;
	}
	
	public String getRegExp(){
		return this.regExp;
	}
}
