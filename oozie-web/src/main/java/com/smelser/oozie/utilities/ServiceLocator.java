package com.smelser.oozie.utilities;

import com.smelser.code.hadoop.oozie.client.data.service.OozieClient;
import com.smelser.oozie.data.OozieClientFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by psmelser on 16-07-05.
 *
 * @author paul.smelser@esignlive.com
 */
@Component
public class ServiceLocator {

    @Autowired
    private OozieClientFactory factory;
    private OozieClient client;

    public OozieClient getOozieClient(){
        return client;
    }

    public void register(OozieClient client){
        this.client = client;
    }
}
