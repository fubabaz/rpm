package com.b2en.rpm.viewpart.vo;

import java.io.Serializable;

import com.b2en.common.vo.AbstractCommonVO;

public class AshMetricNameResultVO extends AbstractCommonVO implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    
	private String groupId;		
	
	private String metricId;
	
	private String metricName;
	
	private String metricUnit;

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

	public String getMetricName() {
		return metricName;
	}

	public void setMetricName(String metricName) {
		this.metricName = metricName;
	}

	public String getMetricUnit() {
		return metricUnit;
	}

	public void setMetricUnit(String metricUnit) {
		this.metricUnit = metricUnit;
	}
	
	
}
