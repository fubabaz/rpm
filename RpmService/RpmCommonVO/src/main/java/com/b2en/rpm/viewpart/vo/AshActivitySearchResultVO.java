package com.b2en.rpm.viewpart.vo;

import java.io.Serializable;

import com.b2en.common.vo.AbstractCommonVO;

public class AshActivitySearchResultVO extends AbstractCommonVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sampleTime;
	private Double application;
	private Double concurrency;
	private Double userIo;
	private Double otherWait;
	private Double cpu;
	private String AshSysmetricHistorySearchResultVO;
	
	public String getSampleTime() {
		return sampleTime;
	}
	public void setSampleTime(String sampleTime) {
		this.sampleTime = sampleTime;
	}
	public Double getApplication() {
		return application;
	}
	public void setApplication(Double application) {
		this.application = application;
	}
	public Double getConcurrency() {
		return concurrency;
	}
	public void setConcurrency(Double concurrency) {
		this.concurrency = concurrency;
	}
	public Double getUserIo() {
		return userIo;
	}
	public void setUserIo(Double userIo) {
		this.userIo = userIo;
	}
	
	public Double getOtherWait() {
		return otherWait;
	}
	public void setOtherWait(Double otherWait) {
		this.otherWait = otherWait;
	}
	
	public Double getCpu() {
		return cpu;
	}
	public void setCpu(Double cpu) {
		this.cpu = cpu;
	}
	public String getAshSysmetricHistorySearchResultVO() {
		return AshSysmetricHistorySearchResultVO;
	}
	public void setAshSysmetricHistorySearchResultVO(
			String ashSysmetricHistorySearchResultVO) {
		AshSysmetricHistorySearchResultVO = ashSysmetricHistorySearchResultVO;
	}
}
