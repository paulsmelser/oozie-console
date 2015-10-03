package org.smelser.code.hadoop.oozie.client;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;

import org.smelser.code.hadoop.oozie.client.data.service.OozieGatewayImpl;
import org.smelser.code.hadoop.oozie.client.dto.CoordinatorActionDto;
import org.smelser.code.hadoop.oozie.client.dto.CoordinatorDto;
import org.smelser.code.hadoop.oozie.client.dto.GetRunningCoordinatorsResponse;
import org.smelser.code.hadoop.oozie.client.dto.GetWorkflowListResponse;
import org.smelser.code.hadoop.oozie.client.dto.WorkflowDto;
import org.smelser.code.hadoop.oozie.client.dto.mapper.CoordinatorMapperConfiguration;
import org.smelser.code.hadoop.oozie.client.entities.Coordinator;
import org.smelser.code.hadoop.oozie.client.entities.Workflow;

import simplemapper.Mapper;

import com.google.gson.Gson;
import simplemapper.MapperException;

public class App 
{
    public static void main( String[] args ) throws IOException
    {
    	try {
    		Mapper.createMap(CoordinatorDto.class, Coordinator.class, new CoordinatorMapperConfiguration());
    		
	        System.out.println( "Testing oozie gateway" );
	        OozieGatewayImpl gateway = new OozieGatewayImpl("https://whatsnexxhd2.azurehdinsight.net", "admin", "m3Od_w45Gd");
	        GetWorkflowListResponse response = gateway.getWorkflows();
	        
	        Gson gson = new Gson();
	        
	        System.out.println(gson.toJson(response));
	        GetRunningCoordinatorsResponse coords = gateway.getRunningCoordinators();
	        System.out.println(gson.toJson(coords));
	        Iterator<CoordinatorDto> it = coords.getCoordinatorjobs().iterator();
	        CoordinatorDto coord = it.next();
	        
	        coord = gateway.getCoordinator(it.next().coordJobId, 10);
	        
			Coordinator coordBe = Mapper.map(coord, Coordinator.class);
			
			
			System.out.println("Coordinator BE :" + gson.toJson(coordBe));
	        
	        Iterator<CoordinatorActionDto> coordActions = coord.actions.iterator();
	        WorkflowDto wDto = gateway.getWorkflow(coordActions.next().getExternalId(), 10);
	        System.out.println("Workflow BE :" + gson.toJson(Mapper.map(wDto, Workflow.class)));
	        
	        System.out.println(gson.toJson(gateway.GetKilledOrFailedWorkflows(10)));
	        
	        System.in.read();
    	} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MapperException e) {
			e.printStackTrace();
		}
	}
}
