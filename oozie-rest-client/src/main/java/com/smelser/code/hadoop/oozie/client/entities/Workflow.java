package com.smelser.code.hadoop.oozie.client.entities;

import java.util.Collection;
import java.util.Date;

public class Workflow {
	private String appPath;
	private String acl;
	private String status;
	private Date createdTime;
	private Configuration conf;
	private Date lastModTime;
	private String run;
	private Date endTime;
	private String externalId;
	private String appName;
	private String id;
	private Date startTime;
	private String parentId;
	private String toString;
	private String group;
	private String consoleUrl;
	private String user;
	private Collection<WorkflowAction> actions;
	
	public String getAppPath() {
		return appPath;
	}
	public void setAppPath(String appPath) {
		this.appPath = appPath;
	}
	public String getAcl() {
		return acl;
	}
	public void setAcl(String acl) {
		this.acl = acl;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public Configuration getConf() {
		return conf;
	}
	public void setConf(Configuration conf) {
		this.conf = conf;
	}
	public Date getLastModTime() {
		return lastModTime;
	}
	public void setLastModTime(Date lastModTime) {
		this.lastModTime = lastModTime;
	}
	public String getRun() {
		return run;
	}
	public void setRun(String run) {
		this.run = run;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getExternalId() {
		return externalId;
	}
	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getToString() {
		return toString;
	}
	public void setToString(String toString) {
		this.toString = toString;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getConsoleUrl() {
		return consoleUrl;
	}
	public void setConsoleUrl(String consoleUrl) {
		this.consoleUrl = consoleUrl;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public Collection<WorkflowAction> getActions() {
		return actions;
	}
	public void setActions(Collection<WorkflowAction> actions) {
		this.actions = actions;
	}
}
