package org.smelser.code.hadoop.oozie.client.data.service;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Map;

import org.smelser.code.hadoop.oozie.client.data.service.stubs.OozieGatewayStubImpl;
import org.smelser.code.hadoop.oozie.client.dto.GetStatusResponse;
import org.smelser.code.hadoop.oozie.client.entities.Configuration;
import org.smelser.code.hadoop.oozie.client.entities.Coordinator;
import org.smelser.code.hadoop.oozie.client.entities.CoordinatorAction;
import org.smelser.code.hadoop.oozie.client.entities.Workflow;
import org.smelser.code.hadoop.oozie.client.entities.WorkflowAction;

import simplemapper.Mapper;
import simplemapper.MapperException;

public class OozieClientStub implements OozieClient {

    private OozieGatewayStubImpl gateway = new OozieGatewayStubImpl();

    public Collection<Workflow> getWorklows(int len) throws InstantiationException, IllegalAccessException,
	    IllegalArgumentException, NoSuchFieldException, SecurityException, InvocationTargetException,
	    NoSuchMethodException {
	// TODO Auto-generated method stub
	return null;
    }

    public Collection<Coordinator> getRunningCoordinators(int len) throws InstantiationException,
	    IllegalAccessException, IllegalArgumentException, NoSuchFieldException, SecurityException,
	    InvocationTargetException, NoSuchMethodException {
	// TODO Auto-generated method stub
	return null;
    }

    public Workflow getWorkflow(String id, int len) throws InstantiationException, IllegalAccessException,
            IllegalArgumentException, NoSuchFieldException, SecurityException, InvocationTargetException,
            NoSuchMethodException, MapperException {
	return Mapper.map(gateway.getWorkflow(id, len), Workflow.class);
    }

    public Coordinator getCoordinator(String id, int len) throws InstantiationException,
	    IllegalAccessException, IllegalArgumentException, NoSuchFieldException, SecurityException,
	    InvocationTargetException, NoSuchMethodException {
	// TODO Auto-generated method stub
	return null;
    }

    public WorkflowAction getWorkflowAction(String id, String name) throws InstantiationException,
	    IllegalAccessException, IllegalArgumentException, NoSuchFieldException, SecurityException,
	    InvocationTargetException, NoSuchMethodException {
	// TODO Auto-generated method stub
	return null;
    }

    public CoordinatorAction getCoordinatorAction(String id, int index) throws InstantiationException,
	    IllegalAccessException, IllegalArgumentException, NoSuchFieldException, SecurityException,
	    InvocationTargetException, NoSuchMethodException {
	// TODO Auto-generated method stub
	return null;
    }

    public void kill(String id) {
	// TODO Auto-generated method stub

    }

    public void rerun(String id) throws InstantiationException, IllegalAccessException,
	    IllegalArgumentException, NoSuchFieldException, SecurityException, InvocationTargetException,
	    NoSuchMethodException {
	// TODO Auto-generated method stub

    }

    public void rerun(String id, String skipNodes) throws InstantiationException, IllegalAccessException,
	    IllegalArgumentException, NoSuchFieldException, SecurityException, InvocationTargetException,
	    NoSuchMethodException {
	// TODO Auto-generated method stub

    }

    public void suspend(String id) {
	// TODO Auto-generated method stub

    }

    public void resume(String id) {
	// TODO Auto-generated method stub

    }

    public void start(String id) {
	// TODO Auto-generated method stub

    }

    public void submit(Map<String, String> conf) {
	// TODO Auto-generated method stub

    }

    public void rerun(String id, String skipNodes, Configuration config) throws InstantiationException,
	    IllegalAccessException, IllegalArgumentException, NoSuchFieldException, SecurityException,
	    InvocationTargetException, NoSuchMethodException {
	// TODO Auto-generated method stub

    }

    public GetStatusResponse getStatus() {
	// TODO Auto-generated method stub
	return null;
    }

}
