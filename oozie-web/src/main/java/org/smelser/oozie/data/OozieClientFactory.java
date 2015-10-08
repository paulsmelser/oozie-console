package org.smelser.oozie.data;

import org.smelser.code.hadoop.oozie.client.HadoopAccount;
import org.smelser.code.hadoop.oozie.client.data.service.OozieClient;
import org.smelser.code.hadoop.oozie.client.data.service.OozieClientStub;
import org.smelser.code.hadoop.oozie.client.data.service.SimpleOozieClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class OozieClientFactory {

	@Value(value = "${environment}")
	private int environment;
	
	public OozieClient create(String clusterUri, String username, String password){
		if (environment == 0){
		return new SimpleOozieClient(new HadoopAccount(clusterUri, username, password));
		}
		return new OozieClientStub();
	}
}
