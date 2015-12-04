package com.smelser.code.hadoop.oozie.client.data.service.stubs;

import com.smelser.code.hadoop.oozie.SpringProfiles;
import com.smelser.code.hadoop.oozie.client.data.service.OozieClient;
import com.smelser.code.hadoop.oozie.client.data.service.stubs.OozieGatewayStub;
import com.smelser.code.hadoop.oozie.client.dto.*;
import com.smelser.code.hadoop.oozie.client.entities.*;
import org.springframework.context.annotation.Profile;
import simplemapper.Mapper;
import simplemapper.MapperException;

import java.util.*;

import static java.util.Collections.*;

@Profile({SpringProfiles.DEVELOPMENT, SpringProfiles.DEVELOPMENT})
public class OozieClientStub implements OozieClient {

    private OozieGatewayStub gateway = new OozieGatewayStub();

    public Collection<Workflow> getWorkflows(int len) throws MapperException {
	    GetWorkflowListResponse response = gateway.getWorkflows();
        Collection<Workflow> list = new ArrayList<>();
        for (WorkflowDto w : response.getWorkflows()) {
            list.add(Mapper.map(w, Workflow.class));
        }
        return list;
    }

    public Collection<Coordinator> getRunningCoordinators(int len) throws MapperException {
        GetRunningCoordinatorsResponse coords = gateway.getRunningCoordinators();
        Collection<Coordinator> result = new ArrayList<Coordinator>();
        for (CoordinatorDto dto : coords.getCoordinatorjobs()) {
            result.add(Mapper.map(dto, Coordinator.class));
        }
        return result;
    }

    public Workflow getWorkflow(String id, int len) throws MapperException {
	    return Mapper.map(gateway.getWorkflow(id, len), Workflow.class);
    }

    public Coordinator getCoordinator(String id, int len) throws MapperException {
	    return Mapper.map(gateway.getCoordinator(id, len), Coordinator.class);
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
