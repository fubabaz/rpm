package com.b2en.rpm.viewpart.vo;

import java.io.Serializable;

import com.b2en.common.vo.AbstractCommonVO;

public class AshSysmetricHistorySearchParamVO extends AbstractCommonVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String instId;
	private String instCn;
	private String beginIntervalTime;
	private String beginTime;
	private String hour;
	private String groupId;
	private String metricId;
	private String interval;
	
	
	public String getInstId() {
		return instId;
	}
	public void setInstId(String instId) {
		this.instId = instId;
	}
	public String getInstCn() {
		return instCn;
	}
	public void setInstCn(String instCn) {
		this.instCn = instCn;
	}
	public String getBeginIntervalTime() {
		return beginIntervalTime;
	}
	public void setBeginIntervalTime(String beginIntervalTime) {
		this.beginIntervalTime = beginIntervalTime;
	}
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getHour() {
		return hour;
	}
	public void setHour(String hour) {
		this.hour = hour;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getMetricId() {
		return metricId;
	}
	public void setMetricId(String metricId) {
		this.metricId = metricId;
	}
	
	
	public String getInterval() {
		return interval;
	}
	public void setInterval(String interval) {
		this.interval = interval;
	}

}
