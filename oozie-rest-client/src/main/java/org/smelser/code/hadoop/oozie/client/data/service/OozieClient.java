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

    Collection<Workflow> getWorkflows(int len) throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, NoSuchFieldException, SecurityException, InvocationTargetException,
			NoSuchMethodException, MapperException;

    Collection<Coordinator> getRunningCoordinators(int len) throws InstantiationException,
			IllegalAccessException, IllegalArgumentException, NoSuchFieldException, SecurityException,
			InvocationTargetException, NoSuchMethodException, MapperException;

    Workflow getWorkflow(String id, int len) throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, NoSuchFieldException, SecurityException, InvocationTargetException,
			NoSuchMethodException, MapperException;

    Coordinator getCoordinator(String id, int len) throws InstantiationException,
			IllegalAccessException, IllegalArgumentException, NoSuchFieldException, SecurityException,
			InvocationTargetException, NoSuchMethodException, MapperException;

    WorkflowAction getWorkflowAction(String id, String name) throws InstantiationException,
			IllegalAccessException, IllegalArgumentException, NoSuchFieldException, SecurityException,
			InvocationTargetException, NoSuchMethodException, MapperException;

    CoordinatorAction getCoordinatorAction(String id, int index) throws InstantiationException,
			IllegalAccessException, IllegalArgumentException, NoSuchFieldException, SecurityException,
			InvocationTargetException, NoSuchMethodException, MapperException;

    void kill(String id);

    void rerun(String id) throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, NoSuchFieldException, SecurityException, InvocationTargetException,
			NoSuchMethodException, MapperException;

    void rerun(String id, String skipNodes) throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, NoSuchFieldException, SecurityException, InvocationTargetException,
			NoSuchMethodException, MapperException;

    void rerun(String id, String skipNodes, Configuration config) throws InstantiationException,
	    IllegalAccessException, IllegalArgumentException, NoSuchFieldException, SecurityException,
	    InvocationTargetException, NoSuchMethodException;

    void suspend(String id);

    void resume(String id);

    void start(String id);

    void submit(Map<String, String> conf);

    GetStatusResponse getStatus();
}
