package com.b2en.rpm.viewpart.vo;

import java.io.Serializable;

import com.b2en.common.vo.AbstractCommonVO;

public class AshActivitySqlSearchParamVO extends AbstractCommonVO implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sampleTime;
	private String instId;
	private String minute;

	public String getSampleTime() {
		return sampleTime;
	}
	public void setSampleTime(String sampleTime) {
		this.sampleTime = sampleTime;
	}
	
	
	public String getInstId() {
		return instId;
	}
	public void setInstId(String instId) {
		this.instId = instId;
	}
	
	public String getMinute() {
		return minute;
	}
	public void setMinute(String minute) {
		this.minute = minute;
	}
	
}
