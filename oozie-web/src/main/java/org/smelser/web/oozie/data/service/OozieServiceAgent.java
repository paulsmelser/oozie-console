package org.smelser.web.oozie.data.service;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

import org.smelser.code.hadoop.oozie.client.data.service.OozieGatewayImpl;
import org.smelser.code.hadoop.oozie.client.entities.Coordinator;
import simplemapper.MapperException;

public interface OozieServiceAgent {

	OozieGatewayImpl gateway = new OozieGatewayImpl("https://whatsnexxhd2.azurehdinsight.net", "admin", "m3Od_w45Gd");
	
	public Collection<Coordinator> getRunningCoordinators() throws InstantiationException, IllegalAccessException, IllegalArgumentException, NoSuchFieldException, SecurityException, InvocationTargetException, NoSuchMethodException, MapperException;
}
