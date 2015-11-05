package com.smelser.oozie.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;

public class Registry {

	private static Properties properties = null;
	private static final String file = "application.properties";
	private static URL resourceUrl = null;
	private static File actualFile = null;
	private static OutputStream output = null;
	private static InputStream input = null;
	
	private Registry() throws URISyntaxException{
		try {
			input =  Thread.currentThread().getContextClassLoader().getResourceAsStream(file);
			properties = new Properties();
			properties.load(input);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getProperty(String name){
		if (properties == null){
			try {
				new Registry();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
		}
		return properties.getProperty(name);
	}
	
	public static void setProperty(String key, String value, String comments) throws URISyntaxException{
		if (properties == null){
			try {
				new Registry();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
		}
		try {
			if (resourceUrl == null){
				resourceUrl = Thread.currentThread().getContextClassLoader().getResource(file);
				actualFile = new File(resourceUrl.toURI());
				output = new FileOutputStream(actualFile);
			}
			properties.setProperty(key, value);
			properties.store(output, comments);			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}