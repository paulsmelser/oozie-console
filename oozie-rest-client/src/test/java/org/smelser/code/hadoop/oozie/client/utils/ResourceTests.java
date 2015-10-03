package org.smelser.code.hadoop.oozie.client.utils;

import java.io.IOException;

import junit.framework.TestCase;

public class ResourceTests extends TestCase {

	public void testResource() throws IOException {
		Resource r = new Resource("./resources/worklow.json");
		System.out.println(r.getAsString());
	}

	public void testGetAsString() {
		fail("Not yet implemented");
	}

}
