package com.smelser.oozie.utilities;

import com.smelser.code.hadoop.oozie.client.data.service.OozieClient;
import org.springframework.stereotype.Component;

/**
 * Created by psmelser on 16-07-05.
 *
 * @author paul.smelser@esignlive.com
 */
@Component
public class ServiceLocator {

    public ServiceLocator(OozieClient client) {
        this.client = client;
    }

    private OozieClient client;

    public OozieClient getOozieClient(){
        return client;
    }

    public void register(OozieClient client){
        this.client = client;
    }
}
