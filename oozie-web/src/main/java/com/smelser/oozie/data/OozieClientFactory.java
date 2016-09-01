package com.smelser.oozie.data;

import com.smelser.code.hadoop.oozie.client.data.service.OozieClient;

public interface OozieClientFactory {

	OozieClient create(String clusterUri, String username, String password);
}
