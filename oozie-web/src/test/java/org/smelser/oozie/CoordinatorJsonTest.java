package org.smelser.oozie;

import java.io.IOException;

import org.junit.Test;

import com.google.gson.Gson;
import com.smelser.code.hadoop.oozie.client.dto.CoordinatorActionDto;
import com.smelser.code.hadoop.oozie.client.dto.CoordinatorDto;
import com.smelser.code.hadoop.oozie.client.dto.WorkflowActionDto;
import com.smelser.code.hadoop.oozie.client.dto.WorkflowDto;
import com.smelser.code.hadoop.oozie.client.dto.mapper.CoordinatorMapperConfiguration;
import com.smelser.code.hadoop.oozie.client.dto.mapper.WorkflowMapperConfiguration;
import com.smelser.code.hadoop.oozie.client.entities.Coordinator;
import com.smelser.code.hadoop.oozie.client.entities.CoordinatorAction;
import com.smelser.code.hadoop.oozie.client.entities.Workflow;
import com.smelser.code.hadoop.oozie.client.entities.WorkflowAction;
import com.smelser.code.hadoop.oozie.client.utils.Resource;
import simplemapper.Mapper;
import simplemapper.MapperException;

public class CoordinatorJsonTest {
    private static final Gson GSON = new Gson();

    @Test
    public void TEST() throws IOException, MapperException {
        Resource resource = Resource.fromFile("coordinator.json");
        String resourceString = resource.getAsString();
        CoordinatorDto result = GSON.fromJson(resourceString, CoordinatorDto.class);
        Coordinator map = Mapper.map(result, Coordinator.class);
    }

    public void configureMapper(){
        Mapper.createMap(CoordinatorDto.class, Coordinator.class, new CoordinatorMapperConfiguration());
        Mapper.createMap(CoordinatorActionDto.class, CoordinatorAction.class);
        Mapper.createMap(WorkflowDto.class, Workflow.class, new WorkflowMapperConfiguration());
        Mapper.createMap(WorkflowActionDto.class, WorkflowAction.class);
    }
}
