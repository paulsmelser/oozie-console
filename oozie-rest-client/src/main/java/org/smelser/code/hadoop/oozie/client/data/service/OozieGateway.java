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

    GetWorkflowListResponse getWorkflows();

    GetRunningCoordinatorsResponse getRunningCoordinators();

    CoordinatorDto getCoordinator(String id, int len);

    WorkflowDto getWorkflow(String id, int len);

    GetKilledOrFailedWorkflowsResponse GetKilledOrFailedWorkflows(int len);

    void kill(String id);

    void reRun(String id) throws InstantiationException, IllegalAccessException,
            IllegalArgumentException, NoSuchFieldException, SecurityException, InvocationTargetException,
            NoSuchMethodException, MapperException;

    void reRun(String id, String skipNodes) throws InstantiationException, IllegalAccessException,
            IllegalArgumentException, NoSuchFieldException, SecurityException, InvocationTargetException,
            NoSuchMethodException, MapperException;

    void reRun(String id, String skipNodes, Configuration config) throws InstantiationException,
	    IllegalAccessException, IllegalArgumentException, NoSuchFieldException, SecurityException,
	    InvocationTargetException, NoSuchMethodException;

    void start(String id);

    void suspend(String id);

    void resume(String id);

    void submit(Map<String, String> conf);

    GetStatusResponse getStatus();
}
