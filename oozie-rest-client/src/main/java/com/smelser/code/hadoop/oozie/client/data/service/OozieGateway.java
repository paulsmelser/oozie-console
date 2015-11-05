package com.smelser.code.hadoop.oozie.client.data.service;

import com.smelser.code.hadoop.oozie.client.dto.*;
import com.smelser.code.hadoop.oozie.client.entities.Configuration;
import simplemapper.MapperException;

import java.util.Map;

public interface OozieGateway {

    GetWorkflowListResponse getWorkflows();

    GetRunningCoordinatorsResponse getRunningCoordinators();

    CoordinatorDto getCoordinator(String id, int len);

    WorkflowDto getWorkflow(String id, int len);

    GetKilledOrFailedWorkflowsResponse GetKilledOrFailedWorkflows(int len);

    void kill(String id);

    void reRun(String id) throws  MapperException;

    void reRun(String id, String skipNodes) throws MapperException;

    void reRun(String id, String skipNodes, Configuration config);

    void start(String id);

    void suspend(String id);

    void resume(String id);

    void submit(Map<String, String> conf);

    GetStatusResponse getStatus();
}
