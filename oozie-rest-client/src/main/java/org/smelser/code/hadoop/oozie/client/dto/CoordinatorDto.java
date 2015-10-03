package org.smelser.code.hadoop.oozie.client.dto;

import java.util.Collection;

public class CoordinatorDto {
	public String total;
	public String pauseTime;
	public String coordJobName;
	public String coordJobPath;
	public String timeZone;
	public int frequency;
	public String conf;
	public String endTime;
	public String executionPolicy;
	public String startTime;
	public String timeUnit;
	public String concurrency;
	public String coordJobId;
	public String lastAction;
	public String status;
	public String acl;
	public String mat_throttling;
	public int timeOut;
	public String nextMaterializedTime;
	public String bundleId;
	public String toString;
	public String coordExternalId;
	public String group;
	public String user;
	public String consoleUrl;
	public Collection<CoordinatorActionDto> actions;
	
	public CoordinatorDto(){
	}
	public CoordinatorDto(String coordJobName, String coordJobId, String status) {
		super();
		this.coordJobName = coordJobName;
		this.coordJobId = coordJobId;
		this.status = status;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getPauseTime() {
		return pauseTime;
	}
	public void setPauseTime(String pauseTime) {
		this.pauseTime = pauseTime;
	}
	public String getCoordJobName() {
		return coordJobName;
	}
	public void setCoordJobName(String coordJobName) {
		this.coordJobName = coordJobName;
	}
	public String getCoordJobPath() {
		return coordJobPath;
	}
	public void setCoordJobPath(String coordJobPath) {
		this.coordJobPath = coordJobPath;
	}
	public String getTimeZone() {
		return timeZone;
	}
	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}
	public int getFrequency() {
		return frequency;
	}
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	public String getConf() {
		return conf;
	}
	public void setConf(String conf) {
		this.conf = conf;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getExecutionPolicy() {
		return executionPolicy;
	}
	public void setExecutionPolicy(String executionPolicy) {
		this.executionPolicy = executionPolicy;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getTimeUnit() {
		return timeUnit;
	}
	public void setTimeUnit(String timeUnit) {
		this.timeUnit = timeUnit;
	}
	public String getConcurrency() {
		return concurrency;
	}
	public void setConcurrency(String concurrency) {
		this.concurrency = concurrency;
	}
	public String getCoordJobId() {
		return coordJobId;
	}
	public void setCoordJobId(String coordJobId) {
		this.coordJobId = coordJobId;
	}
	public String getLastAction() {
		return lastAction;
	}
	public void setLastAction(String lastAction) {
		this.lastAction = lastAction;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAcl() {
		return acl;
	}
	public void setAcl(String acl) {
		this.acl = acl;
	}
	public String getMat_throttling() {
		return mat_throttling;
	}
	public void setMat_throttling(String mat_throttling) {
		this.mat_throttling = mat_throttling;
	}
	public int getTimeOut() {
		return timeOut;
	}
	public void setTimeOut(int timeOut) {
		this.timeOut = timeOut;
	}
	public String getNextMaterializedTime() {
		return nextMaterializedTime;
	}
	public void setNextMaterializedTime(String nextMaterializedTime) {
		this.nextMaterializedTime = nextMaterializedTime;
	}
	public String getBundleId() {
		return bundleId;
	}
	public void setBundleId(String bundleId) {
		this.bundleId = bundleId;
	}
	public String getToString() {
		return toString;
	}
	public void setToString(String toString) {
		this.toString = toString;
	}
	public String getCoordExternalId() {
		return coordExternalId;
	}
	public void setCoordExternalId(String coordExternalId) {
		this.coordExternalId = coordExternalId;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getConsoleUrl() {
		return consoleUrl;
	}
	public void setConsoleUrl(String consoleUrl) {
		this.consoleUrl = consoleUrl;
	}
	public Collection<CoordinatorActionDto> getActions() {
		return actions;
	}
	public void setActions(Collection<CoordinatorActionDto> actions) {
		this.actions = actions;
	}
}
