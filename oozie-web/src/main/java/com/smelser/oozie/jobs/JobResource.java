package com.smelser.oozie.jobs;

import com.smelser.oozie.utilities.ServiceLocator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by psmelser on 16-07-05.
 *
 * @author paul.smelser@esignlive.com
 */
@RestController
@RequestMapping("api/v1")
@Scope(value = "session")
public class JobResource {

    private final ServiceLocator serviceLocator;

    @Autowired
    public JobResource(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    @RequestMapping(value = "job/kill/{id}", method = RequestMethod.PUT)
    public void kill(@PathVariable String id) {
        serviceLocator.getOozieClient().kill(id);
    }
}
