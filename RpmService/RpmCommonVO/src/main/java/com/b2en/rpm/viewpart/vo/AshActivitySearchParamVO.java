package com.b2en.rpm.viewpart.vo;

import java.io.Serializable;

import com.b2en.common.vo.AbstractCommonVO;

public class AshActivitySearchParamVO extends AbstractCommonVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sampleTime;
	private String second;
	private String hour;
	private String minute;
	private String instId;
	private String interval;
		
	AshSysmetricHistorySearchParamVO ashSysmetricHistorySearchParamVO;
	

	public String getSampleTime() {
		return sampleTime;
	}
	public void setSampleTime(String sampleTime) {
		this.sampleTime = sampleTime;
	}
	public String getSecond() {
		return second;
	}
	public void setSecond(String second) {
		this.second = second;
	}
	public String getHour() {
		return hour;
	}
	public void setHour(String hour) {
		this.hour = hour;
	}
	public String getInstId() {
		return instId;
	}
	public void setInstId(String instId) {
		this.instId = instId;
	}
	
	
	
	public String getInterval() {
		return interval;
	}
	public void setInterval(String interval) {
		this.interval = interval;
	}
	public String getMinute() {
		return minute;
	}
	public void setMinute(String minute) {
		this.minute = minute;
	}
	public AshSysmetricHistorySearchParamVO getAshSysmetricHistorySearchParamVO() {
		return ashSysmetricHistorySearchParamVO;
	}
	public void setAshSysmetricHistorySearchParamVO(
			AshSysmetricHistorySearchParamVO ashSysmetricHistorySearchParamVO) {
		this.ashSysmetricHistorySearchParamVO = ashSysmetricHistorySearchParamVO;
	}

}
