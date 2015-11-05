package com.smelser.code.hadoop.oozie.client.entities;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("property")
public class Property {
	private String name;
	private String value;
	
	public Property(String name, String value){
		this.name = name;
		this.value = value;	
	}
	public Property(){}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}
