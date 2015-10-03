package org.smelser.code.hadoop.oozie.client.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class Coordinator {
    private String total;
    private Date pauseTime;
    private String coordJobName;
    private String coordJobPath;
    private String timeZone;
    private int frequency;
    private Configuration conf;
    private Date endTime;
    private String executionPolicy;
    private Date startTime;
    private String timeUnit;
    private String concurrency;
    private String coordJobId;
    private String lastAction;
    private String status;
    private String acl;
    private String mat_throttling;
    private int timeOut;
    private String nextMaterializedTime;
    private String bundleId;
    private String toString;
    private String coordExternalId;
    private String group;
    private String user;
    private String consoleUrl;
    private Collection<CoordinatorAction> actions = new ArrayList<CoordinatorAction>();

    public String getTotal() {
	return total;
    }

    public void setTotal(String total) {
	this.total = total;
    }

    public Date getPauseTime() {
	return pauseTime;
    }

    public void setPauseTime(Date pauseTime) {
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

    public Configuration getConf() {
	return conf;
    }

    public void setConf(Configuration conf) {
	this.conf = conf;
    }

    public Date getEndTime() {
	return endTime;
    }

    public void setEndTime(Date endTime) {
	this.endTime = endTime;
    }

    public String getExecutionPolicy() {
	return executionPolicy;
    }

    public void setExecutionPolicy(String executionPolicy) {
	this.executionPolicy = executionPolicy;
    }

    public Date getStartTime() {
	return startTime;
    }

    public void setStartTime(Date startTime) {
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

    public Collection<CoordinatorAction> getActions() {
	return actions;
    }

    public void setActions(Collection<CoordinatorAction> actions) {
	this.actions = actions;
    }
}
