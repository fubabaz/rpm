package com.b2en.rpm.sqlviewer.vo;

import java.io.Serializable;

import com.b2en.common.vo.AbstractCommonVO;

public class SqlViewerOverViewInfo extends AbstractCommonVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String valueString;
	private String datatypeString;
	private String datatype;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValueString() {
		return valueString;
	}
	public void setValueString(String valueString) {
		this.valueString = valueString;
	}
	public String getDatatypeString() {
		return datatypeString;
	}
	public void setDatatypeString(String datatypeString) {
		this.datatypeString = datatypeString;
	}
	public String getDatatype() {
		return datatype;
	}
	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}
	
}
