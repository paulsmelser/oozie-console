package org.smelser.code.rest_client;

import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.smelser.code.hadoop.oozie.client.HadoopAccount;
import org.smelser.code.hadoop.oozie.client.data.service.OozieClient;
import org.smelser.code.hadoop.oozie.client.data.service.SimpleOozieClient;
import org.smelser.code.hadoop.oozie.client.entities.Coordinator;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import simplemapper.MapperException;

/**
 * Unit test for simple App.
 */
public class SimpleOozieClientTests extends TestCase{
	
	private OozieClient client;
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public SimpleOozieClientTests( String testName )
    {
        super( testName );
    }

    protected void setUp(){
    	client = new HadoopAccount("https://clustername.azurehdinsight.net", "username", "password").getOozieClient();
    }
    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( SimpleOozieClientTests.class );
    }

    /**
     * Rigourous Test :-)
     * @throws NoSuchMethodException 
     * @throws InvocationTargetException 
     * @throws SecurityException 
     * @throws NoSuchFieldException 
     * @throws IllegalArgumentException 
     * @throws IllegalAccessException 
     * @throws InstantiationException 
     */
    public void testApp() throws InstantiationException, IllegalAccessException, IllegalArgumentException, NoSuchFieldException, SecurityException, InvocationTargetException, NoSuchMethodException, MapperException {
        Collection<Coordinator> coords = client.getRunningCoordinators(15);
        Coordinator co = coords.iterator().next();
        Coordinator c =client.getCoordinator(co.getCoordJobId(), 10);
        System.out.println(c.getAcl());
    }
    
    @SuppressWarnings("serial")
	public void testSubmit(){
    	Map<String, String> conf = new HashMap<String, String>(){{
    		put("user.name", "admin");
    		put("appPath", "oozie/stage/apps/account/0.0.1");
    		put("oozie.coord.application.path", "wasb://${globalContainer}@${globalStorageAccount}.blob.core.windows.net/${appPath}/coordinatorApp.xml");
    		put("oozie.use.system.libpath", "true");
    		put("executionEnvironment", "stage");
    		put("yarn.app.mapreduce.am.staging-dir", "/user");
    		put("globalStorageAccount", "whatsnexxhd2");
    		put("clientContainer", "whatsmart");
    		put("clientStorageAccount", "whatsmartstage");
    		put("globalContainer", "whatsnexxhd2");
    		put("frequency", "1440");
    		put("dataMartUserName", "VieuxDuluthQuelFestin");
    		put("dataMartPassword", "Left4Death");
    		put("dataMartServerName", "fztzkp2yvk");
    		put("dataMartDbName", "DataMartStage");
    		put("portalUserName", "VieuxDuluthQuelFestin");
    		put("portalPassword", "Left4Death");
    		put("portalServerName", "fztzkp2yvk");
    		put("portalDbName", "PortalStage");
    		put("accountUserName", "VieuxDuluthQuelFestin");
    		put("accountPassword", "Left4Death");
    		put("accountServerName", "fztzkp2yvk");
    		put("accountDbName", "AccountStage");
    		put("accountId", "00000000-0000-0000-0000-000000000001");
    		put("start","2015-01-01T00:00Z");
    		put("end", "2016-01-01T00:00Z");
    	}};
    	client.submit(conf);
    }
}
