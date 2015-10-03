package org.smelser.code.hadoop.oozie.client;

import org.smelser.code.hadoop.oozie.client.data.service.SimpleOozieClient;

public class HadoopAccount {
	
	private String clusterUri;
	private String username;
	private String password;
	
	public HadoopAccount(String clusterUri, String username, String password) {
		super();
		this.username = username;
		this.password = password;
		this.clusterUri = clusterUri;
	}
	
	public SimpleOozieClient getOozieClient(){
		return new SimpleOozieClient(this);
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword(){
		return password;
	}

	public String getClusterUri() {
		return clusterUri;
	}


	public void setClusterUri(String clusterUri) {
		this.clusterUri = clusterUri;
	}
}
