package org.smelser.code.hadoop.oozie.client.data.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import org.smelser.code.hadoop.oozie.client.HadoopAccount;
import org.smelser.code.hadoop.oozie.client.dto.CoordinatorActionDto;
import org.smelser.code.hadoop.oozie.client.dto.CoordinatorDto;
import org.smelser.code.hadoop.oozie.client.dto.GetRunningCoordinatorsResponse;
import org.smelser.code.hadoop.oozie.client.dto.GetStatusResponse;
import org.smelser.code.hadoop.oozie.client.dto.GetWorkflowListResponse;
import org.smelser.code.hadoop.oozie.client.dto.WorkflowActionDto;
import org.smelser.code.hadoop.oozie.client.dto.WorkflowDto;
import org.smelser.code.hadoop.oozie.client.dto.mapper.CoordinatorMapperConfiguration;
import org.smelser.code.hadoop.oozie.client.dto.mapper.WorkflowMapperConfiguration;
import org.smelser.code.hadoop.oozie.client.entities.Configuration;
import org.smelser.code.hadoop.oozie.client.entities.Coordinator;
import org.smelser.code.hadoop.oozie.client.entities.CoordinatorAction;
import org.smelser.code.hadoop.oozie.client.entities.Workflow;
import org.smelser.code.hadoop.oozie.client.entities.WorkflowAction;

import simplemapper.Mapper;
import simplemapper.MapperException;

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

    public Collection<Workflow> getWorkflows(int len) throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, NoSuchFieldException, SecurityException, InvocationTargetException,
			NoSuchMethodException, MapperException {
	GetWorkflowListResponse response = gateway.getWorkflows();
	Collection<Workflow> list = new ArrayList<Workflow>();
	for (WorkflowDto w : response.getWorkflows()) {
	    list.add(Mapper.map(w, Workflow.class));
	}
	return list;
    }

    public Collection<Coordinator> getRunningCoordinators(int len) throws InstantiationException,
			IllegalAccessException, IllegalArgumentException, NoSuchFieldException, SecurityException,
			InvocationTargetException, NoSuchMethodException, MapperException {
	GetRunningCoordinatorsResponse coords = gateway.getRunningCoordinators();
	Collection<Coordinator> result = new ArrayList<Coordinator>();
	for (CoordinatorDto dto : coords.getCoordinatorjobs()) {
	    result.add(Mapper.map(dto, Coordinator.class));
	}
	return result;
    }

    public Workflow getWorkflow(String id, int len) throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, NoSuchFieldException, SecurityException, InvocationTargetException,
			NoSuchMethodException, MapperException {
	return Mapper.map(gateway.getWorkflow(id, len), Workflow.class);
    }

    public Coordinator getCoordinator(String id, int len) throws InstantiationException,
			IllegalAccessException, IllegalArgumentException, NoSuchFieldException, SecurityException,
			InvocationTargetException, NoSuchMethodException, MapperException {
	return Mapper.map(gateway.getCoordinator(id, len), Coordinator.class);
    }

    public WorkflowAction getWorkflowAction(String id, String name) throws InstantiationException,
			IllegalAccessException, IllegalArgumentException, NoSuchFieldException, SecurityException,
			InvocationTargetException, NoSuchMethodException, MapperException {
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

    public CoordinatorAction getCoordinatorAction(String id, int index) throws InstantiationException,
			IllegalAccessException, IllegalArgumentException, NoSuchFieldException, SecurityException,
			InvocationTargetException, NoSuchMethodException, MapperException {
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

    public void rerun(String id) throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, NoSuchFieldException, SecurityException, InvocationTargetException,
			NoSuchMethodException, MapperException {
	gateway.reRun(id);

    }

    public void rerun(String id, String skipNodes) throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, NoSuchFieldException, SecurityException, InvocationTargetException,
			NoSuchMethodException, MapperException {
	gateway.reRun(id, skipNodes);

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

    public void rerun(String id, String skipNodes, Configuration config) throws InstantiationException,
	    IllegalAccessException, IllegalArgumentException, NoSuchFieldException, SecurityException,
	    InvocationTargetException, NoSuchMethodException {
	gateway.reRun(id, skipNodes, config);

    }

    public GetStatusResponse getStatus() {
	return gateway.getStatus();
    }

}
