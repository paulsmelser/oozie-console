package org.smelser.code.hadoop.oozie.client.data.service.stubs;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.smelser.code.hadoop.oozie.client.data.service.OozieGateway;
import org.smelser.code.hadoop.oozie.client.dto.CoordinatorDto;
import org.smelser.code.hadoop.oozie.client.dto.GetKilledOrFailedWorkflowsResponse;
import org.smelser.code.hadoop.oozie.client.dto.GetRunningCoordinatorsResponse;
import org.smelser.code.hadoop.oozie.client.dto.GetStatusResponse;
import org.smelser.code.hadoop.oozie.client.dto.GetWorkflowListResponse;
import org.smelser.code.hadoop.oozie.client.dto.WorkflowDto;
import org.smelser.code.hadoop.oozie.client.entities.Configuration;
import org.smelser.code.hadoop.oozie.client.utils.Resource;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class OozieGatewayStubImpl implements OozieGateway {

    public GetWorkflowListResponse getWorkflows() {
	return null;
    }

    public GetRunningCoordinatorsResponse getRunningCoordinators() {
	Collection<CoordinatorDto> list = null;
	try {
	    Resource resource = new Resource("CoordinatorList.json");
	    Gson gson = new Gson();

	    list = gson.fromJson(resource.getAsString(), new ArrayList<CoordinatorDto>().getClass());
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	GetRunningCoordinatorsResponse response = new GetRunningCoordinatorsResponse();
	response.setCoordinatorjobs(list);
	return response;
    }

    public CoordinatorDto getCoordinator(String id, int len) {
	CoordinatorDto dto = new CoordinatorDto();
	dto.setAcl("test");
	return dto;
    }

    public WorkflowDto getWorkflow(String id, int len) {
	WorkflowDto result = null;
	try {
	    Resource file = new Resource("workflow.json");
	    Gson gson = new Gson();

	    result = gson.fromJson(file.getAsString(), WorkflowDto.class);
	} catch (JsonSyntaxException e) {
	    e.printStackTrace();
	} catch (IOException e) {
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

    public void reRun(String id) throws InstantiationException, IllegalAccessException,
	    IllegalArgumentException, NoSuchFieldException, SecurityException, InvocationTargetException,
	    NoSuchMethodException {
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

    public void reRun(String id, String skipNodes) throws InstantiationException, IllegalAccessException,
	    IllegalArgumentException, NoSuchFieldException, SecurityException, InvocationTargetException,
	    NoSuchMethodException {
	// TODO Auto-generated method stub

    }

    public void reRun(String id, String skipNodes, Configuration config) throws InstantiationException,
	    IllegalAccessException, IllegalArgumentException, NoSuchFieldException, SecurityException,
	    InvocationTargetException, NoSuchMethodException {
	// TODO Auto-generated method stub

    }

    public GetStatusResponse getStatus() {
	// TODO Auto-generated method stub
	return null;
    }

}
