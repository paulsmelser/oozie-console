package org.smelser.code.hadoop.oozie.client.dto.mapper;

import org.smelser.code.hadoop.oozie.client.dto.CoordinatorActionDto;
import org.smelser.code.hadoop.oozie.client.dto.CoordinatorDto;
import org.smelser.code.hadoop.oozie.client.dto.WorkflowActionDto;
import org.smelser.code.hadoop.oozie.client.dto.WorkflowDto;
import org.smelser.code.hadoop.oozie.client.entities.Coordinator;
import org.smelser.code.hadoop.oozie.client.entities.CoordinatorAction;
import org.smelser.code.hadoop.oozie.client.entities.Workflow;
import org.smelser.code.hadoop.oozie.client.entities.WorkflowAction;

import simplemapper.Mapper;

public class MapperConfiguration {
	
	static {
		Mapper.createMap(CoordinatorDto.class, Coordinator.class, new CoordinatorMapperConfiguration());
		Mapper.createMap(CoordinatorActionDto.class, CoordinatorAction.class);
		Mapper.createMap(WorkflowDto.class, Workflow.class, new WorkflowMapperConfiguration());
		Mapper.createMap(WorkflowActionDto.class, WorkflowAction.class);
	}

}
