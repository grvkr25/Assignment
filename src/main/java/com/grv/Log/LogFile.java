package com.grv.Log;

public class LogFile {
	
	private String id;
	private String state;
	private String type;
	private String host;
	private long timestamp;
	private String alertFlag;
	private int processedTime;
	 public String getAlertFlag() {
		return alertFlag;
	}
	public void setAlertFlag(String alertFlag) {
		this.alertFlag = alertFlag;
	}
	public int getProcessedTime() {
		return processedTime;
	}
	public void setProcessedTime(int processedTime) {
		this.processedTime = processedTime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	@Override
	public String toString() {
		return "LogFile [id=" + id + ", state=" + state + ", type=" + type + ", host=" + host + ", timestamp="
				+ timestamp + ", alertFlag=" + alertFlag + ", processedTime=" + processedTime + ", isAlertFlag()="
				+ getAlertFlag() + ", getProcessedTime()=" + getProcessedTime() + ", getId()=" + getId()
				+ ", getState()=" + getState() + ", getType()=" + getType() + ", getHost()=" + getHost()
				+ ", getTimestamp()=" + getTimestamp() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

	
}

