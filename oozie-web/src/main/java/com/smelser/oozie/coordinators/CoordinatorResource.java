package com.smelser.oozie.coordinators;

import com.google.common.collect.Lists;
import com.smelser.code.hadoop.oozie.client.entities.Coordinator;
import com.smelser.oozie.utilities.ServiceLocator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryPolicy;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.backoff.ThreadWaitSleeper;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import simplemapper.MapperException;

import java.util.Collection;

/**
 * Created by psmelser on 16-07-05.
 *
 * @author paul.smelser@esignlive.com
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
        RetryTemplate template = new RetryTemplate();
        template.setBackOffPolicy(getBackOffPolicy());
        template.setRetryPolicy(getRetryPolicy());

        return template.execute(retryContext -> {
            if (retryContext.getRetryCount() < 7) {
                throw new RuntimeException();
            }
            return serviceLocator.getOozieClient().getRunningCoordinators(100);
        });
    }

    private ExponentialBackOffPolicy getBackOffPolicy() {
        ExponentialBackOffPolicy exponentialBackOffPolicy = new ExponentialBackOffPolicy();
        exponentialBackOffPolicy.withSleeper(new ThreadWaitSleeper());
        exponentialBackOffPolicy.setMaxInterval(100000);
        return exponentialBackOffPolicy;
    }

    private RetryPolicy getRetryPolicy() {
        SimpleRetryPolicy policy = new SimpleRetryPolicy();
        policy.setMaxAttempts(8);
        return policy;
    }

    @RequestMapping(value = "coordinator/{id}", method = RequestMethod.GET)
    public Coordinator getCoordinator(@PathVariable String id) throws  MapperException {
        return serviceLocator.getOozieClient().getCoordinator(id, 50);
    }
}
