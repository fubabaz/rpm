package com.b2en.rpm.viewpart.vo;

import java.io.Serializable;

import com.b2en.common.vo.AbstractCommonVO;

public class AshActivitySessionSearchResultVO extends AbstractCommonVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String instId;
	private String sessionId;
	private String sessionSerial;
	private String rt;
	private String cpu;
	private String wait;
	private String tmDeltaCpuTime;
	private String tmDeltaDbTime;
	private String deltaReadIoBytes;
	private String deltaWriteIoBytes;
	private String deltaInterconnectIoBytes;
	private String deltaReadMemBytes;
	public String getInstId() {
		return instId;
	}
	public void setInstId(String instId) {
		this.instId = instId;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getSessionSerial() {
		return sessionSerial;
	}
	public void setSessionSerial(String sessionSerial) {
		this.sessionSerial = sessionSerial;
	}
	public String getRt() {
		return rt;
	}
	public void setRt(String rt) {
		this.rt = rt;
	}
	public String getCpu() {
		return cpu;
	}
	public void setCpu(String cpu) {
		this.cpu = cpu;
	}
	public String getWait() {
		return wait;
	}
	public void setWait(String wait) {
		this.wait = wait;
	}
	public String getTmDeltaCpuTime() {
		return tmDeltaCpuTime;
	}
	public void setTmDeltaCpuTime(String tmDeltaCpuTime) {
		this.tmDeltaCpuTime = tmDeltaCpuTime;
	}
	public String getTmDeltaDbTime() {
		return tmDeltaDbTime;
	}
	public void setTmDeltaDbTime(String tmDeltaDbTime) {
		this.tmDeltaDbTime = tmDeltaDbTime;
	}
	public String getDeltaReadIoBytes() {
		return deltaReadIoBytes;
	}
	public void setDeltaReadIoBytes(String deltaReadIoBytes) {
		this.deltaReadIoBytes = deltaReadIoBytes;
	}
	public String getDeltaWriteIoBytes() {
		return deltaWriteIoBytes;
	}
	public void setDeltaWriteIoBytes(String deltaWriteIoBytes) {
		this.deltaWriteIoBytes = deltaWriteIoBytes;
	}
	public String getDeltaInterconnectIoBytes() {
		return deltaInterconnectIoBytes;
	}
	public void setDeltaInterconnectIoBytes(String deltaInterconnectIoBytes) {
		this.deltaInterconnectIoBytes = deltaInterconnectIoBytes;
	}
	public String getDeltaReadMemBytes() {
		return deltaReadMemBytes;
	}
	public void setDeltaReadMemBytes(String deltaReadMemBytes) {
		this.deltaReadMemBytes = deltaReadMemBytes;
	}
	
	

}
