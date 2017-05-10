package com.b2en.rpm.viewpart.vo;

import java.io.Serializable;

import com.b2en.common.vo.AbstractCommonVO;

public class AshActivitySessionSearchParamVO extends AbstractCommonVO implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sampleTime;
	private String minute;

	public String getSampleTime() {
		return sampleTime;
	}
	public void setSampleTime(String sampleTime) {
		this.sampleTime = sampleTime;
	}
	public String getMinute() {
		return minute;
	}
	public void setMinute(String minute) {
		this.minute = minute;
	}
	
}
