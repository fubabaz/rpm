package com.b2en.rpm.session.vo;

import java.io.Serializable;

import com.b2en.common.vo.AbstractCommonVO;

public class SessionViewInfo extends AbstractCommonVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String username;
	private String depth;
	private String instId;
	private String sid;
	private String serial;
	private String logonTime;
	private String status;
	private String serviceName;
	private String module;
	private String action;
	private String clientInfo;
	private String machine;
	private String osuser;
	private String program;
	private String sqlId;
	private String sqlChildNumber;
	private String sqlExecStart;
	private String sqlExecId;
	private String pxDop;
	private String saddr;
	private String paddr;
	private String taddr;
	private String pid;
	private String pserial;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getDepth() {
		return depth;
	}
	public void setDepth(String depth) {
		this.depth = depth;
	}
	public String getInstId() {
		return instId;
	}
	public void setInstId(String instId) {
		this.instId = instId;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getSerial() {
		return serial;
	}
	public void setSerial(String serial) {
		this.serial = serial;
	}
	public String getLogonTime() {
		return logonTime;
	}
	public void setLogonTime(String logonTime) {
		this.logonTime = logonTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getClientInfo() {
		return clientInfo;
	}
	public void setClientInfo(String clientInfo) {
		this.clientInfo = clientInfo;
	}
	public String getMachine() {
		return machine;
	}
	public void setMachine(String machine) {
		this.machine = machine;
	}
	public String getOsuser() {
		return osuser;
	}
	public void setOsuser(String osuser) {
		this.osuser = osuser;
	}
	public String getProgram() {
		return program;
	}
	public void setProgram(String program) {
		this.program = program;
	}
	public String getSqlId() {
		return sqlId;
	}
	public void setSqlId(String sqlId) {
		this.sqlId = sqlId;
	}
	public String getSqlChildNumber() {
		return sqlChildNumber;
	}
	public void setSqlChildNumber(String sqlChildNumber) {
		this.sqlChildNumber = sqlChildNumber;
	}
	public String getSqlExecStart() {
		return sqlExecStart;
	}
	public void setSqlExecStart(String sqlExecStart) {
		this.sqlExecStart = sqlExecStart;
	}
	public String getSqlExecId() {
		return sqlExecId;
	}
	public void setSqlExecId(String sqlExecId) {
		this.sqlExecId = sqlExecId;
	}
	public String getPxDop() {
		return pxDop;
	}
	public void setPxDop(String pxDop) {
		this.pxDop = pxDop;
	}
	public String getSaddr() {
		return saddr;
	}
	public void setSaddr(String saddr) {
		this.saddr = saddr;
	}
	public String getPaddr() {
		return paddr;
	}
	public void setPaddr(String paddr) {
		this.paddr = paddr;
	}
	public String getTaddr() {
		return taddr;
	}
	public void setTaddr(String taddr) {
		this.taddr = taddr;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getPserial() {
		return pserial;
	}
	public void setPserial(String pserial) {
		this.pserial = pserial;
	}
	
}
