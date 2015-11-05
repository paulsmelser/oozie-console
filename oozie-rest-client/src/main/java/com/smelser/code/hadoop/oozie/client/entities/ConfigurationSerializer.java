package com.smelser.code.hadoop.oozie.client.entities;

import com.thoughtworks.xstream.XStream;

public class ConfigurationSerializer {
	private static XStream xStream;
	
	static {
		xStream = new XStream();
		xStream.alias("property", Property.class);
		xStream.alias("configuration", Configuration.class);
		xStream.addImplicitArray(Configuration.class, "properties");
	}
	public static Configuration fromXml(String xmlString){
			return (Configuration)  xStream.fromXML(xmlString);
	}
	
	public static String toXml(Configuration conf){
		return xStream.toXML(conf);
	}
}
