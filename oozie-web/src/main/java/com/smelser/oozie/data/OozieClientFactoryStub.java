package com.smelser.oozie.data;

import com.smelser.code.hadoop.oozie.SpringProfiles;
import com.smelser.code.hadoop.oozie.client.data.service.OozieClient;
import com.smelser.code.hadoop.oozie.client.data.service.stubs.OozieClientStub;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by paul.smelser on 8/1/16.
 *
 * @author paul.smelser@appdirect.com
 */
@Component
@Scope("prototype")
@Profile(SpringProfiles.NOT_DEVELOPMENT)
public class OozieClientFactoryStub implements OozieClientFactory{

    public OozieClient create(String clusterUri, String username, String password){
        return new OozieClientStub();
    }
}
