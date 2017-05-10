package com.b2en.rpm.viewpart.vo;

import java.io.Serializable;

import com.b2en.common.vo.AbstractCommonVO;

public class AshSysmetricHistorySearchResultVO extends AbstractCommonVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String instanceNumber;
	
	private String beginTime;
	
	private String value;
	

	public String getInstanceNumber() {
		return instanceNumber;
	}

	public void setInstanceNumber(String instanceNumber) {
		this.instanceNumber = instanceNumber;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
