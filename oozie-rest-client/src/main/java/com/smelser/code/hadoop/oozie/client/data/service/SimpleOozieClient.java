package com.smelser.code.hadoop.oozie.client.data.service;

import com.smelser.code.hadoop.oozie.client.HadoopAccount;
import com.smelser.code.hadoop.oozie.client.dto.*;
import com.smelser.code.hadoop.oozie.client.dto.mapper.CoordinatorMapperConfiguration;
import com.smelser.code.hadoop.oozie.client.dto.mapper.WorkflowMapperConfiguration;
import com.smelser.code.hadoop.oozie.client.entities.*;
import simplemapper.Mapper;
import simplemapper.MapperException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

public class SimpleOozieClient implements OozieClient {

    private OozieGateway gateway;

    static {
	Mapper.createMap(CoordinatorDto.class, Coordinator.class, new CoordinatorMapperConfiguration());
	Mapper.createMap(CoordinatorActionDto.class, CoordinatorAction.class);
	Mapper.createMap(WorkflowDto.class, Workflow.class, new WorkflowMapperConfiguration());
	Mapper.createMap(WorkflowActionDto.class, WorkflowAction.class);
    }

    public SimpleOozieClient(HadoopAccount account) {
	gateway = new OozieGatewayImpl(account.getClusterUri(), account.getUsername(), account.getPassword());
    }

    public Collection<Workflow> getWorkflows(int len) throws MapperException {
	GetWorkflowListResponse response = gateway.getWorkflows();
	Collection<Workflow> list = new ArrayList<Workflow>();
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

    public Workflow getWorkflow(String id, int len) throws  MapperException {
	return Mapper.map(gateway.getWorkflow(id, len), Workflow.class);
    }

    public Coordinator getCoordinator(String id, int len) throws MapperException {
	return Mapper.map(gateway.getCoordinator(id, len), Coordinator.class);
    }

    public WorkflowAction getWorkflowAction(String id, String name) throws MapperException {
	Workflow response = Mapper.map(gateway.getWorkflow(id, 100), Workflow.class);

	WorkflowAction action = null;
	for (WorkflowAction a : response.getActions()) {
	    if (a.getName().equals(name)) {
		action = a;
		break;
	    }
	}
	return action;
    }

    public CoordinatorAction getCoordinatorAction(String id, int index) throws  MapperException {
	Coordinator coord = Mapper.map(gateway.getCoordinator(id, 100), Coordinator.class);
	Iterator<CoordinatorAction> it = coord.getActions().iterator();

	for (int i = 0; i != index; i++) {
	    it.next();
	}
	return it.next();
    }

    public void kill(String id) {
	gateway.kill(id);
    }

    public void rerun(String id) throws OozieException {
		try {
			gateway.reRun(id);
		} catch (Exception e) {
			throw new OozieException(e);
		}
	}

    public void rerun(String id, String skipNodes) throws  OozieException {
		try {
			gateway.reRun(id, skipNodes);
		} catch (Exception e) {
			throw new OozieException(e);
		}
	}

    public void suspend(String id) {
	gateway.suspend(id);
    }

    public void resume(String id) {
	gateway.resume(id);
    }

    public void start(String id) {
	gateway.start(id);
    }

    public void submit(Map<String, String> conf) {
	gateway.submit(conf);
    }

    public void rerun(String id, String skipNodes, Configuration config) throws OozieException {
		try {
			gateway.reRun(id, skipNodes, config);
		} catch (Exception e) {
			throw new OozieException(e);
		}
	}

    public GetStatusResponse getStatus() {
	return gateway.getStatus();
    }

}
