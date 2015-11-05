package com.smelser.oozie.controller;

public class UserLogin {

    private String url;
    private String username;
    private String password;

    public UserLogin() {
    }

    public UserLogin(String url, String username, String password) {
	super();
	this.url = url;
	this.username = username;
	this.password = password;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public String getUrl() {
	return url;
    }

    public void setUrl(String url) {
	this.url = url;
    }
}
