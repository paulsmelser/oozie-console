package org.smelser.code.hadoop.oozie.client.data.service;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.smelser.code.hadoop.oozie.client.dto.CoordinatorDto;
import org.smelser.code.hadoop.oozie.client.dto.GetKilledOrFailedWorkflowsResponse;
import org.smelser.code.hadoop.oozie.client.dto.GetRunningCoordinatorsResponse;
import org.smelser.code.hadoop.oozie.client.dto.GetStatusResponse;
import org.smelser.code.hadoop.oozie.client.dto.GetWorkflowListResponse;
import org.smelser.code.hadoop.oozie.client.dto.WorkflowDto;
import org.smelser.code.hadoop.oozie.client.entities.Configuration;
import simplemapper.MapperException;

public interface OozieGateway {

    public GetWorkflowListResponse getWorkflows();

    public GetRunningCoordinatorsResponse getRunningCoordinators();

    public CoordinatorDto getCoordinator(String id, int len);

    public WorkflowDto getWorkflow(String id, int len);

    public GetKilledOrFailedWorkflowsResponse GetKilledOrFailedWorkflows(int len);

    public void kill(String id);

    public void reRun(String id) throws InstantiationException, IllegalAccessException,
            IllegalArgumentException, NoSuchFieldException, SecurityException, InvocationTargetException,
            NoSuchMethodException, MapperException;

    public void reRun(String id, String skipNodes) throws InstantiationException, IllegalAccessException,
            IllegalArgumentException, NoSuchFieldException, SecurityException, InvocationTargetException,
            NoSuchMethodException, MapperException;

    public void reRun(String id, String skipNodes, Configuration config) throws InstantiationException,
	    IllegalAccessException, IllegalArgumentException, NoSuchFieldException, SecurityException,
	    InvocationTargetException, NoSuchMethodException;

    public void start(String id);

    public void suspend(String id);

    public void resume(String id);

    public void submit(Map<String, String> conf);

    public GetStatusResponse getStatus();
}
