package com.smelser.oozie.retry;

import org.springframework.retry.RetryPolicy;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.backoff.ThreadWaitSleeper;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

public class ExponentialRetryTemplate {

    public static RetryTemplate newRetryTemplate(){
        RetryTemplate template = new RetryTemplate();
        template.setBackOffPolicy(getBackOffPolicy());
        template.setRetryPolicy(getRetryPolicy());
        return template;
    }
    private static ExponentialBackOffPolicy getBackOffPolicy() {
        ExponentialBackOffPolicy exponentialBackOffPolicy = new ExponentialBackOffPolicy();
        exponentialBackOffPolicy.withSleeper(new ThreadWaitSleeper());
        exponentialBackOffPolicy.setMaxInterval(100000);
        return exponentialBackOffPolicy;
    }

    private static RetryPolicy getRetryPolicy() {
        SimpleRetryPolicy policy = new SimpleRetryPolicy();
        policy.setMaxAttempts(8);
        return policy;
    }
}
