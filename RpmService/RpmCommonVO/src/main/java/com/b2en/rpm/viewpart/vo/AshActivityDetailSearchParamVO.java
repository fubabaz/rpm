package com.b2en.rpm.viewpart.vo;

import java.io.Serializable;

import com.b2en.common.vo.AbstractCommonVO;

public class AshActivityDetailSearchParamVO extends AbstractCommonVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String sampleTime;
	private String minute;
	private String second;
	private String instId;
	
	private AshActivitySqlSearchParamVO ashActivitySqlSearchParamVO;
	private AshActivitySessionSearchParamVO ashActivitySessionSearchParamVO;
	
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
	
	
	public String getSecond() {
		return second;
	}
	public void setSecond(String second) {
		this.second = second;
	}
	public String getInstId() {
		return instId;
	}
	public void setInstId(String instId) {
		this.instId = instId;
	}
	public AshActivitySqlSearchParamVO getAshActivitySqlSearchParamVO() {
		return ashActivitySqlSearchParamVO;
	}
	public void setAshActivitySqlSearchParamVO(
			AshActivitySqlSearchParamVO ashActivitySqlSearchParamVO) {
		this.ashActivitySqlSearchParamVO = ashActivitySqlSearchParamVO;
	}
	public AshActivitySessionSearchParamVO getAshActivitySessionSearchParamVO() {
		return ashActivitySessionSearchParamVO;
	}
	public void setAshActivitySessionSearchParamVO(
			AshActivitySessionSearchParamVO ashActivitySessionSearchParamVO) {
		this.ashActivitySessionSearchParamVO = ashActivitySessionSearchParamVO;
	}
	
	

}
