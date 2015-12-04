package com.smelser.oozie.controller;

import com.smelser.code.hadoop.oozie.client.data.service.OozieClient;
import com.smelser.code.hadoop.oozie.client.data.service.OozieException;
import com.smelser.code.hadoop.oozie.client.dto.CoordinatorActionDto;
import com.smelser.code.hadoop.oozie.client.dto.CoordinatorDto;
import com.smelser.code.hadoop.oozie.client.dto.WorkflowActionDto;
import com.smelser.code.hadoop.oozie.client.dto.WorkflowDto;
import com.smelser.code.hadoop.oozie.client.dto.mapper.CoordinatorMapperConfiguration;
import com.smelser.code.hadoop.oozie.client.dto.mapper.WorkflowMapperConfiguration;
import com.smelser.code.hadoop.oozie.client.entities.*;
import com.smelser.oozie.data.OozieClientFactory;
import com.smelser.oozie.utilities.EncryptUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import simplemapper.Mapper;
import simplemapper.MapperException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("rest/v1")
@Scope(value = "session")
public class PortalRestController {

    static {
	Mapper.createMap(CoordinatorDto.class, Coordinator.class, new CoordinatorMapperConfiguration());
	Mapper.createMap(CoordinatorActionDto.class, CoordinatorAction.class);
	Mapper.createMap(WorkflowDto.class, Workflow.class, new WorkflowMapperConfiguration());
	Mapper.createMap(WorkflowActionDto.class, WorkflowAction.class);
    }

    @Autowired
    private OozieClientFactory factory;
	@Autowired
	private AuthCookieService authCookieService;
    private OozieClient client;

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public void login(@RequestParam(value = "clusterUri", required = true) String clusterUri,
	    @RequestParam(value = "username", required = true) String username,
	    @RequestParam(value = "password", required = true) String password,
	    @RequestParam(value = "stayLoggedIn", required = true) boolean stayLoggedIn,
	    HttpServletResponse response) {
        if (stayLoggedIn) {
            Cookie cookie = authCookieService.create(clusterUri, username, password);
            response.addCookie(cookie);
        }
        client = factory.create(clusterUri, username, password);
        client.getStatus();
    }

	@RequestMapping(value = "cookie", method = RequestMethod.GET)
    public UserLogin loginWithCookie(@RequestHeader("Cookie") String cookie) {
        UserLogin auth = getAuthFromCookie(cookie);
        ensureClient(cookie);
        return auth;
    }

    @RequestMapping(value = "coordinators", method = RequestMethod.GET)
    public Collection<Coordinator> getCoordinators(HttpSession session, @RequestHeader("Cookie") String cookie) throws MapperException {
	    return ensureClient(cookie).getRunningCoordinators(100);
    }

    private OozieClient ensureClient(String cookie) {
        if (client != null) {
            return client;
        }
        if (!cookie.contains("_oc")) {
            return null;
        }
        UserLogin auth = getAuthFromCookie(cookie);
        if (client == null) {

            client = factory.create(auth.getUrl(), auth.getUsername(), auth.getPassword());

        }
        return client;
    }

    @RequestMapping(value = "workflows", method = RequestMethod.GET)
    public Collection<Workflow> getWorkflows(@RequestHeader("Cookie") String cookie)
			throws  MapperException {
	    return ensureClient(cookie).getWorkflows(100);
    }

    @RequestMapping(value = "coordinator/{id}", method = RequestMethod.GET)
    public Coordinator getCoordinator(@PathVariable String id, @RequestHeader("Cookie") String cookie)
			throws  MapperException {
	    return ensureClient(cookie).getCoordinator(id, 50);
    }

    @RequestMapping(value = "workflow/rerun/{id}", method = RequestMethod.PUT)
    public void rerunWorkflow(@PathVariable String id,
	    @RequestParam(value = "skipNodes", required = false) String skipNodes,
	    @RequestParam(value = "config", required = false) String config,
	    @RequestHeader("Cookie") String cookie) throws OozieException {
	        ensureClient(cookie);
        if (skipNodes == null) {
            client.rerun(id);
        } else {
            client.rerun(id, skipNodes, ConfigurationSerializer.fromXml(config));
        }
    }

    @RequestMapping(value = "workflow/{id}", method = RequestMethod.GET)
    public Workflow getWorkflow(@PathVariable String id, @RequestHeader("Cookie") String cookie)
			throws MapperException {
        Workflow wf = ensureClient(cookie).getWorkflow(id, 50);
        Collections.sort(wf.getConf().getProperties(), (o1, o2) -> o1.getName().compareTo(o2.getName()));
        return wf;
    }

    @RequestMapping(value = "job/kill/{id}", method = RequestMethod.PUT)
    public void kill(@PathVariable String id, @RequestHeader("Cookie") String cookie) {
	ensureClient(cookie).kill(id);
    }

    @RequestMapping(value = "workflow/{id}/action/{name}", method = RequestMethod.GET)
    public WorkflowAction getWorkflowAction(@PathVariable String id, @PathVariable String name,
	    @RequestHeader("Cookie") String cookie) throws MapperException {
	    return ensureClient(cookie).getWorkflowAction(id, name);
    }

    public UserLogin getAuthFromCookie(String cookie) {
        String pattern = "OC.(\\d{1}).(\\d{1}).[A-Z, a-z, 0-9, =]*";
        Pattern r = Pattern.compile(pattern);
        Matcher matcher = r.matcher(cookie);
        if (matcher.find()) {
            String[] auth = EncryptUtils.base64decode(matcher.group().split("\\.")[3]).split("\\|");
            return new UserLogin(auth[0], auth[1], auth[2]);
        }
        return new UserLogin();
    }
}