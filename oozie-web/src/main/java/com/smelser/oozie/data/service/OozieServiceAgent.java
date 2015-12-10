package com.smelser.oozie.data.service;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

import com.smelser.code.hadoop.oozie.client.HadoopAccount;
import com.smelser.code.hadoop.oozie.client.data.service.DefaultOozieGateway;
import com.smelser.code.hadoop.oozie.client.entities.Coordinator;
import simplemapper.MapperException;

public interface OozieServiceAgent {

	DefaultOozieGateway gateway = new DefaultOozieGateway(new HadoopAccount("https://whatsnexxhd2.azurehdinsight.net", "admin", "m3Od_w45Gd"));
	
	public Collection<Coordinator> getRunningCoordinators() throws InstantiationException, IllegalAccessException, IllegalArgumentException, NoSuchFieldException, SecurityException, InvocationTargetException, NoSuchMethodException, MapperException;
}
