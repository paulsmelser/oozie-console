package com.smelser.code.hadoop.oozie.client.data.service.stubs;

import java.io.IOException;
import java.util.Map;

import com.smelser.code.hadoop.oozie.client.dto.GetKilledOrFailedWorkflowsResponse;
import com.smelser.code.hadoop.oozie.client.dto.GetRunningCoordinatorsResponse;
import com.smelser.code.hadoop.oozie.client.dto.WorkflowDto;
import com.smelser.code.hadoop.oozie.client.data.service.OozieGateway;
import com.smelser.code.hadoop.oozie.client.dto.CoordinatorDto;
import com.smelser.code.hadoop.oozie.client.dto.GetStatusResponse;
import com.smelser.code.hadoop.oozie.client.dto.GetWorkflowListResponse;
import com.smelser.code.hadoop.oozie.client.entities.Configuration;
import com.smelser.code.hadoop.oozie.client.utils.Resource;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

class OozieGatewayStub implements OozieGateway {

    private static final Gson GSON = new Gson();

    public GetWorkflowListResponse getWorkflows() {
	return null;
    }

    public GetRunningCoordinatorsResponse getRunningCoordinators() {
        GetRunningCoordinatorsResponse list = null;
        try {
            Resource resource = new Resource("coordinatorList.json");

            list = GSON.fromJson(resource.getAsString(), GetRunningCoordinatorsResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public CoordinatorDto getCoordinator(String id, int len) {
        CoordinatorDto result = null;
        try {
            Resource resource = new Resource("coordinator.json");

            result = GSON.fromJson(resource.getAsString(), CoordinatorDto.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public WorkflowDto getWorkflow(String id, int len) {
	WorkflowDto result = null;
	try {
	    Resource file = new Resource("workflow.json");

	    result = GSON.fromJson(file.getAsString(), WorkflowDto.class);
	} catch (JsonSyntaxException | IOException e) {
	    e.printStackTrace();
	}
        return result;
    }

    public GetKilledOrFailedWorkflowsResponse GetKilledOrFailedWorkflows(int len) {
	// TODO Auto-generated method stub
	return null;
    }

    public void kill(String id) {
	// TODO Auto-generated method stub

    }

    public void reRun(String id) {
	// TODO Auto-generated method stub

    }

    public void start(String id) {
	// TODO Auto-generated method stub

    }

    public void suspend(String id) {
	// TODO Auto-generated method stub

    }

    public void resume(String id) {
	// TODO Auto-generated method stub

    }

    public void submit(Map<String, String> conf) {
	// TODO Auto-generated method stub

    }

    public void reRun(String id, String skipNodes) {
	// TODO Auto-generated method stub

    }

    public void reRun(String id, String skipNodes, Configuration config) {
	// TODO Auto-generated method stub

    }

    public GetStatusResponse getStatus() {
	// TODO Auto-generated method stub
	return null;
    }

}
