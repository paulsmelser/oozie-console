package org.smelser.code.hadoop.oozie.client.utils;

import java.io.IOException;

import com.smelser.code.hadoop.oozie.client.utils.Resource;
import org.junit.Test;

public class ResourceTests {

	@Test
	public void testResource() throws IOException {
		Resource r = new Resource("worklow.json");
		System.out.println(r.getAsString());
	}

}
