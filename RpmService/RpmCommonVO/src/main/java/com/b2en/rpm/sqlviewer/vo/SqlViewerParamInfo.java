package com.b2en.rpm.sqlviewer.vo;

import java.io.Serializable;

import com.b2en.common.vo.AbstractCommonVO;

public class SqlViewerParamInfo extends AbstractCommonVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String instId;
	private String sqlId;
	private String childNumber;
	
	public String getInstId() {
		return instId;
	}
	public void setInstId(String instId) {
		this.instId = instId;
	}
	public String getSqlId() {
		return sqlId;
	}
	public void setSqlId(String sqlId) {
		this.sqlId = sqlId;
	}
	public String getChildNumber() {
		return childNumber;
	}
	public void setChildNumber(String childNumber) {
		this.childNumber = childNumber;
	}
	
}
