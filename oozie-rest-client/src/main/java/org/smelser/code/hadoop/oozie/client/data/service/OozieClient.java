package org.smelser.code.hadoop.oozie.client.data.service;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Map;

import org.smelser.code.hadoop.oozie.client.dto.GetStatusResponse;
import org.smelser.code.hadoop.oozie.client.entities.Configuration;
import org.smelser.code.hadoop.oozie.client.entities.Coordinator;
import org.smelser.code.hadoop.oozie.client.entities.CoordinatorAction;
import org.smelser.code.hadoop.oozie.client.entities.Workflow;
import org.smelser.code.hadoop.oozie.client.entities.WorkflowAction;
import simplemapper.MapperException;

public interface OozieClient {

    public Collection<Workflow> getWorklows(int len) throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, NoSuchFieldException, SecurityException, InvocationTargetException,
			NoSuchMethodException, MapperException;

    public Collection<Coordinator> getRunningCoordinators(int len) throws InstantiationException,
			IllegalAccessException, IllegalArgumentException, NoSuchFieldException, SecurityException,
			InvocationTargetException, NoSuchMethodException, MapperException;

    public Workflow getWorkflow(String id, int len) throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, NoSuchFieldException, SecurityException, InvocationTargetException,
			NoSuchMethodException, MapperException;

    public Coordinator getCoordinator(String id, int len) throws InstantiationException,
			IllegalAccessException, IllegalArgumentException, NoSuchFieldException, SecurityException,
			InvocationTargetException, NoSuchMethodException, MapperException;

    public WorkflowAction getWorkflowAction(String id, String name) throws InstantiationException,
			IllegalAccessException, IllegalArgumentException, NoSuchFieldException, SecurityException,
			InvocationTargetException, NoSuchMethodException, MapperException;

    public CoordinatorAction getCoordinatorAction(String id, int index) throws InstantiationException,
			IllegalAccessException, IllegalArgumentException, NoSuchFieldException, SecurityException,
			InvocationTargetException, NoSuchMethodException, MapperException;

    public void kill(String id);

    public void rerun(String id) throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, NoSuchFieldException, SecurityException, InvocationTargetException,
			NoSuchMethodException, MapperException;

    public void rerun(String id, String skipNodes) throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, NoSuchFieldException, SecurityException, InvocationTargetException,
			NoSuchMethodException, MapperException;

    public void rerun(String id, String skipNodes, Configuration config) throws InstantiationException,
	    IllegalAccessException, IllegalArgumentException, NoSuchFieldException, SecurityException,
	    InvocationTargetException, NoSuchMethodException;

    public void suspend(String id);

    public void resume(String id);

    public void start(String id);

    public void submit(Map<String, String> conf);

    public GetStatusResponse getStatus();
}
