package org.smelser.code.hadoop.oozie.client.data.service;

import org.smelser.code.hadoop.oozie.client.data.service.stubs.OozieGatewayStubImpl;
import org.smelser.code.hadoop.oozie.client.dto.GetStatusResponse;
import org.smelser.code.hadoop.oozie.client.entities.*;
import simplemapper.Mapper;
import simplemapper.MapperException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

public class OozieClientStub implements OozieClient {

    private OozieGatewayStubImpl gateway = new OozieGatewayStubImpl();

    public Collection<Workflow> getWorkflows(int len)  {
	// TODO Auto-generated method stub
	return null;
    }

    public Collection<Coordinator> getRunningCoordinators(int len) {
	    // TODO Auto-generated method stub
	    Coordinator coord = new Coordinator();
        return new ArrayList<>(Arrays.asList(coord));
    }

    public Workflow getWorkflow(String id, int len) throws MapperException {
	return Mapper.map(gateway.getWorkflow(id, len), Workflow.class);
    }

    public Coordinator getCoordinator(String id, int len)  {
	// TODO Auto-generated method stub
	return null;
    }

    public WorkflowAction getWorkflowAction(String id, String name) {
	// TODO Auto-generated method stub
	return null;
    }

    public CoordinatorAction getCoordinatorAction(String id, int index){
	// TODO Auto-generated method stub
	return null;
    }

    public void kill(String id) {
	// TODO Auto-generated method stub

    }

    public void rerun(String id)  {
	// TODO Auto-generated method stub

    }

    public void rerun(String id, String skipNodes)  {
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

    public void rerun(String id, String skipNodes, Configuration config) {
	// TODO Auto-generated method stub

    }

    public GetStatusResponse getStatus() {
	// TODO Auto-generated method stub
	return null;
    }

}
