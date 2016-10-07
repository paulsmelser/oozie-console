package com.smelser.oozie.coordinators;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.retry.RetryPolicy;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.backoff.ThreadWaitSleeper;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.smelser.code.hadoop.oozie.client.entities.Coordinator;
import com.smelser.oozie.retry.ExponentialRetryTemplate;
import com.smelser.oozie.utilities.ServiceLocator;
import simplemapper.MapperException;

/**
 * Created by psmelser on 16-07-05.
 *
 * @author paul.smelser@gmail.com
 */
@RestController
@RequestMapping("api/v1")
@Scope(value = "session")
public class CoordinatorResource {

    private final ServiceLocator serviceLocator;

    @Autowired
    public CoordinatorResource(ServiceLocator serviceLocator) {
        this.serviceLocator = serviceLocator;
    }

    @RequestMapping(value = "coordinators", method = RequestMethod.GET)
    public Collection<Coordinator> getCoordinators() throws MapperException {
        return ExponentialRetryTemplate.newRetryTemplate().execute(retryContext -> serviceLocator.getOozieClient().getRunningCoordinators(100));
    }
    @RequestMapping(value = "coordinator/{id}", method = RequestMethod.GET)
    public Coordinator getCoordinator(@PathVariable String id) throws MapperException {
        return ExponentialRetryTemplate.newRetryTemplate().execute(retryContext ->serviceLocator.getOozieClient().getCoordinator(id, 50));
    }
}
