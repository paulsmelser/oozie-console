package com.smelser.code.hadoop.oozie.client.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;

public class Resource {

	private BufferedReader reader;
	private Scanner scan;
	
	public Resource(String filePath) throws IOException{
		URL f = Resource.class.getClassLoader().getResource(filePath);
		reader = new BufferedReader(
		        new InputStreamReader(f.openStream()));
		scan = new Scanner(reader);
    	scan.useDelimiter("\\r\\n");
	}

	public Resource(String filePath, String delimiter) throws IOException{
		URL f = Resource.class.getClassLoader().getResource(filePath);
		reader = new BufferedReader(
		        new InputStreamReader(f.openStream()));
		scan = new Scanner(reader);
    	scan.useDelimiter(delimiter);
	}
	public String getAsString() throws java.io.IOException {
	    try {
	    	String content = "";
	    	while(scan.hasNext()){
	    		content+=scan.next();
	    	}
	    	return content;
	    } finally {
	        reader.close();
	    }
	}
}
