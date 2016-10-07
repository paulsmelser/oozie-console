package com.smelser.code.hadoop.oozie.client.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
public class Utils {
    public final StopWatch stopWatch;

    @Autowired
    public Utils(StopWatch stopWatch) {
        this.stopWatch = stopWatch;
    }
}
