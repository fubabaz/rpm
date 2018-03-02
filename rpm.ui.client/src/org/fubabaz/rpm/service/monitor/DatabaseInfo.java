package org.fubabaz.rpm.service.monitor;

import java.util.ArrayList;
import java.util.List;

public class DatabaseInfo {

	private String dbId;
	private String dbName;
	private String dbUniqueName;
	private String logMode;
	private String platformName;
	private String createDate;
	private int cpuCnt;
	private int cpuCoreCnt;
	private int cpuSocketCnt;
	private double physicalMemory;

	private List<InstanceInfo> instanceInfoList = new ArrayList<InstanceInfo>();

	/**
	 * @return the dbId
	 */
	public String getDbId() {
		return dbId;
	}
	/**
	 * @param dbId the dbId to set
	 */
	public void setDbId(String dbId) {
		this.dbId = dbId;
	}
	/**
	 * @return the dbName
	 */
	public String getDbName() {
		return dbName;
	}
	/**
	 * @param dbName the dbName to set
	 */
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}
	/**
	 * @return the dbUniqueName
	 */
	public String getDbUniqueName() {
		return dbUniqueName;
	}
	/**
	 * @param dbUniqueName the dbUniqueName to set
	 */
	public void setDbUniqueName(String dbUniqueName) {
		this.dbUniqueName = dbUniqueName;
	}
	/**
	 * @return the logMode
	 */
	public String getLogMode() {
		return logMode;
	}
	/**
	 * @param logMode the logMode to set
	 */
	public void setLogMode(String logMode) {
		this.logMode = logMode;
	}
	/**
	 * @return the platformName
	 */
	public String getPlatformName() {
		return platformName;
	}
	/**
	 * @param platformName the platformName to set
	 */
	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}
	/**
	 * @return the createDate
	 */
	public String getCreateDate() {
		return createDate;
	}
	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return the cpuCnt
	 */
	public int getCpuCnt() {
		return cpuCnt;
	}
	/**
	 * @param cpuCnt the cpuCnt to set
	 */
	public void setCpuCnt(int cpuCnt) {
		this.cpuCnt = cpuCnt;
	}
	/**
	 * @return the cpuCoreCnt
	 */
	public int getCpuCoreCnt() {
		return cpuCoreCnt;
	}
	/**
	 * @param cpuCoreCnt the cpuCoreCnt to set
	 */
	public void setCpuCoreCnt(int cpuCoreCnt) {
		this.cpuCoreCnt = cpuCoreCnt;
	}
	/**
	 * @return the cpuSocketCnt
	 */
	public int getCpuSocketCnt() {
		return cpuSocketCnt;
	}
	/**
	 * @param cpuSocketCnt the cpuSocketCnt to set
	 */
	public void setCpuSocketCnt(int cpuSocketCnt) {
		this.cpuSocketCnt = cpuSocketCnt;
	}
	/**
	 * @return the physicalMemory
	 */
	public double getPhysicalMemory() {
		return physicalMemory;
	}
	/**
	 * @param physicalMemory the physicalMemory to set
	 */
	public void setPhysicalMemory(double physicalMemory) {
		this.physicalMemory = physicalMemory;
	}
	/**
	 * @return the instanceInfoList
	 */
	public List<InstanceInfo> getInstanceInfoList() {
		return instanceInfoList;
	}
	/**
	 * @param instanceInfoList the instanceInfoList to set
	 */
	public void setInstanceInfoList(List<InstanceInfo> instanceInfoList) {
		this.instanceInfoList = instanceInfoList;
	}
}
