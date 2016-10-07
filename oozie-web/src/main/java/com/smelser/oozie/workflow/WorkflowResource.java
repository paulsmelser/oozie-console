package com.smelser.oozie.workflow;

import com.smelser.code.hadoop.oozie.client.data.service.OozieException;
import com.smelser.code.hadoop.oozie.client.entities.ConfigurationSerializer;
import com.smelser.code.hadoop.oozie.client.entities.Workflow;
import com.smelser.code.hadoop.oozie.client.entities.WorkflowAction;
import com.smelser.oozie.utilities.ServiceLocator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import simplemapper.MapperException;

import java.util.Collection;
import java.util.Collections;

/**
 * Created by psmelser on 16-07-05.
 *
 * @author paul.smelser@gmail.com
 */
@RestController
@RequestMapping("api/v1")
@Scope(value = "session")
public class WorkflowResource {

    private final ServiceLocator serviceLocator;

    @Autowired
    public WorkflowResource(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    @RequestMapping(value = "workflows", method = RequestMethod.GET)
    public Collection<Workflow> getWorkflows(@RequestHeader("Cookie") String cookie)
            throws MapperException {
        return serviceLocator.getOozieClient().getWorkflows(100);
    }

    @RequestMapping(value = "workflow/rerun/{id}", method = RequestMethod.PUT)
    public void rerunWorkflow(@PathVariable String id,
                              @RequestParam(value = "skipNodes", required = false) String skipNodes,
                              @RequestParam(value = "config", required = false) String config) throws OozieException {
        if (skipNodes == null) {
            serviceLocator.getOozieClient().rerun(id);
        } else {
            serviceLocator.getOozieClient().rerun(id, skipNodes, ConfigurationSerializer.fromXml(config));
        }
    }

    @RequestMapping(value = "workflow/{id}", method = RequestMethod.GET)
     public Workflow getWorkflow(@PathVariable String id)
            throws MapperException {
        Workflow wf = serviceLocator.getOozieClient().getWorkflow(id, 50);
        Collections.sort(wf.getConf().getProperties(), (o1, o2) -> o1.getName().compareTo(o2.getName()));
        return wf;
    }

    @RequestMapping(value = "workflow/{id}/action/{name}", method = RequestMethod.GET)
    public WorkflowAction getWorkflowAction(@PathVariable String id, @PathVariable String name) throws MapperException {
        return serviceLocator.getOozieClient().getWorkflowAction(id, name);
    }
}
