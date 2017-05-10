package com.b2en.rpm.viewpart.vo;

import java.io.Serializable;

import com.b2en.common.vo.AbstractCommonVO;

public class AshActivityDetailSearchResultVO extends AbstractCommonVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String beginTime;
	private Double other;
	private Double application;
	private Double configuration;
	private Double administrative;
	private Double concurrency;
	private Double commit;
	private Double Idle;
	private Double network;
	private Double userIo;
	private Double systemIo;
	private Double scheduler;
	private Double clust;
	private Double queueing;
	private Double cpu;
	private Double bcpu;
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public Double getOther() {
		return other;
	}
	public void setOther(Double other) {
		this.other = other;
	}
	public Double getApplication() {
		return application;
	}
	public void setApplication(Double application) {
		this.application = application;
	}
	public Double getConfiguration() {
		return configuration;
	}
	public void setConfiguration(Double configuration) {
		this.configuration = configuration;
	}
	public Double getAdministrative() {
		return administrative;
	}
	public void setAdministrative(Double administrative) {
		this.administrative = administrative;
	}
	public Double getConcurrency() {
		return concurrency;
	}
	public void setConcurrency(Double concurrency) {
		this.concurrency = concurrency;
	}
	public Double getCommit() {
		return commit;
	}
	public void setCommit(Double commit) {
		this.commit = commit;
	}
	public Double getIdle() {
		return Idle;
	}
	public void setIdle(Double idle) {
		Idle = idle;
	}
	public Double getNetwork() {
		return network;
	}
	public void setNetwork(Double network) {
		this.network = network;
	}
	public Double getUserIo() {
		return userIo;
	}
	public void setUserIo(Double userIo) {
		this.userIo = userIo;
	}
	public Double getSystemIo() {
		return systemIo;
	}
	public void setSystemIo(Double systemIo) {
		this.systemIo = systemIo;
	}
	public Double getScheduler() {
		return scheduler;
	}
	public void setScheduler(Double scheduler) {
		this.scheduler = scheduler;
	}
	public Double getClust() {
		return clust;
	}
	public void setClust(Double clust) {
		this.clust = clust;
	}
	public Double getQueueing() {
		return queueing;
	}
	public void setQueueing(Double queueing) {
		this.queueing = queueing;
	}
	public Double getCpu() {
		return cpu;
	}
	public void setCpu(Double cpu) {
		this.cpu = cpu;
	}
	public Double getBcpu() {
		return bcpu;
	}
	public void setBcpu(Double bcpu) {
		this.bcpu = bcpu;
	}
	
	
	
}


