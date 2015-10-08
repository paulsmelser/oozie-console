package org.smelser.code.hadoop.oozie.client.data.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.smelser.code.hadoop.oozie.client.dto.CoordinatorActionDto;
import org.smelser.code.hadoop.oozie.client.dto.CoordinatorDto;
import org.smelser.code.hadoop.oozie.client.dto.GetKilledOrFailedWorkflowsResponse;
import org.smelser.code.hadoop.oozie.client.dto.GetRunningCoordinatorsResponse;
import org.smelser.code.hadoop.oozie.client.dto.GetStatusResponse;
import org.smelser.code.hadoop.oozie.client.dto.GetWorkflowListResponse;
import org.smelser.code.hadoop.oozie.client.dto.WorkflowDto;
import org.smelser.code.hadoop.oozie.client.entities.Configuration;
import org.smelser.code.hadoop.oozie.client.entities.ConfigurationSerializer;
import org.smelser.code.hadoop.oozie.client.entities.Property;
import org.smelser.code.hadoop.oozie.client.entities.Workflow;

import simplemapper.Mapper;

import com.owlike.genson.ext.jaxrs.GensonJsonConverter;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import simplemapper.MapperException;

public class OozieGatewayImpl implements OozieGateway {

    private WebResource service;

    public OozieGatewayImpl(String uri, String username, String password) {
	ClientConfig config = new DefaultClientConfig(GensonJsonConverter.class);
	final HTTPBasicAuthFilter authFilter = new HTTPBasicAuthFilter(username, password);
	Client client = Client.create(config);
	client.addFilter(authFilter);
	service = client.resource(UriBuilder.fromUri(uri).build());
    }

    public GetWorkflowListResponse getWorkflows() {
	return service.path("/oozie/v2/jobs").queryParam("jobtype", "wf").accept(MediaType.APPLICATION_JSON)
		.get(GetWorkflowListResponse.class);
    }

    public GetRunningCoordinatorsResponse getRunningCoordinators() {
	return service.path("/oozie/v2/jobs").queryParam("jobtype", "coord")
		.queryParam("filter", "status%3DRUNNING").accept(MediaType.APPLICATION_JSON)
		.get(GetRunningCoordinatorsResponse.class);

    }

    public CoordinatorDto getCoordinator(String id, int len) {
	CoordinatorDto result = service.path(String.format("/oozie/v2/job/%s", id))
		.queryParam("len", Integer.toString(len)).accept(MediaType.APPLICATION_JSON)
		.get(CoordinatorDto.class);
	if (Integer.parseInt(result.getTotal()) > len) {
	    result = service.path(String.format("/oozie/v2/job/%s", id))
		    .queryParam("len", Integer.toString(len))
		    .queryParam("offset", Integer.toString(Integer.parseInt(result.getTotal()) - len + 1))
		    .accept(MediaType.APPLICATION_JSON).get(CoordinatorDto.class);
	}
	CoordinatorActionDto[] actionArray = result.getActions().toArray(
		new CoordinatorActionDto[result.getActions().size()]);
	Collection<CoordinatorActionDto> actions = new ArrayList<CoordinatorActionDto>();
	for (int i = actionArray.length - 1; i != -1; i--) {
	    actions.add(actionArray[i]);
	}
	result.setActions(actions);
	return result;
    }

    public WorkflowDto getWorkflow(String id, int len) {
	return service.path(String.format("/oozie/v2/job/%s", id)).queryParam("len", Integer.toString(len))
		.accept(MediaType.APPLICATION_JSON).get(WorkflowDto.class);
    }

    public GetKilledOrFailedWorkflowsResponse GetKilledOrFailedWorkflows(int len) {
	GetKilledOrFailedWorkflowsResponse response = new GetKilledOrFailedWorkflowsResponse();
	response.setWorkflows(service.path("/oozie/v2/jobs").queryParam("jobtype", "wf")
		.queryParam("filter", "status%3DKILLED;status%3DFAILED")
		.queryParam("len", Integer.toString(len)).accept(MediaType.APPLICATION_JSON)
		.get(GetWorkflowListResponse.class));
	return response;
    }

    public void reRun(String id) throws MapperException {
	WorkflowDto wf = getWorkflow(id, 100);

	Workflow workflow = Mapper.map(wf, Workflow.class);
	workflow.getConf().getProperties().add(new Property("oozie.wf.rerun.skip.nodes", ":start:"));
	for (Iterator<Property> props = workflow.getConf().getProperties().iterator(); props.hasNext();) {
	    Property prop = props.next();
	    if (prop != null) {
		if (prop.getName().equals("oozie.coord.application.path")) {
		    props.remove();
		    break;
		}
	    }
	}
	String body = ConfigurationSerializer.toXml(workflow.getConf());
	ClientResponse result = service.path("oozie/v2/job/" + id).queryParam("action", "rerun")
		.type(MediaType.APPLICATION_XML).put(ClientResponse.class, body);
    }

    public void reRun(String id, String skipNodes) throws MapperException {
	WorkflowDto wf = getWorkflow(id, 100);

	Workflow workflow = Mapper.map(wf, Workflow.class);

	for (Iterator<Property> props = workflow.getConf().getProperties().iterator(); props.hasNext();) {
	    Property prop = props.next();
	    if (prop != null) {
		if (prop.getName().equals("oozie.coord.application.path")
			|| prop.getName().equals("oozie.wf.rerun.skip.nodes")) {
		    props.remove();
		}
	    }
	}
	workflow.getConf().getProperties().add(new Property("oozie.wf.rerun.skip.nodes", skipNodes));

	String body = ConfigurationSerializer.toXml(workflow.getConf());
	service.path("oozie/v2/job/" + id).queryParam("action", "rerun").type(MediaType.APPLICATION_XML)
		.put(ClientResponse.class, body);
    }

    public void reRun(String id, String skipNodes, Configuration config)  {
	config.remove("oozie.coord.application.path");
	config.add("oozie.wf.rerun.skip.nodes", skipNodes);
	String body = ConfigurationSerializer.toXml(config);
	service.path("oozie/v2/job/" + id).queryParam("action", "rerun").type(MediaType.APPLICATION_XML)
		.put(ClientResponse.class, body);
    }

    public void kill(String id) {
	service.path("oozie/v1/job/" + id).queryParam("action", "kill").put("");
    }

    public void start(String id) {
	service.path("oozie/v1/job/" + id).queryParam("action", "start").put("");
    }

    public void suspend(String id) {
	service.path("oozie/v1/job/" + id).queryParam("action", "suspend").put(ClientResponse.class);
    }

    public void resume(String id) {
	service.path("oozie/v2/job" + id).queryParam("action", "resume").put("");
    }

    public void submit(Map<String, String> conf) {
	Configuration configuration = new Configuration();
	List<Property> props = new ArrayList<Property>();
	for (Iterator<Entry<String, String>> it = conf.entrySet().iterator(); it.hasNext();) {
	    Entry<String, String> entry = it.next();
	    props.add(new Property(entry.getKey(), entry.getValue()));
	}
	configuration.setProperties(props);
	String body = ConfigurationSerializer.toXml(configuration);
	service.path("oozie/v2/jobs").type(MediaType.APPLICATION_XML).post(ClientResponse.class, body);
    }

    public GetStatusResponse getStatus() {
	return service.path("oozie/v2/admin/status").get(GetStatusResponse.class);
    }
}
