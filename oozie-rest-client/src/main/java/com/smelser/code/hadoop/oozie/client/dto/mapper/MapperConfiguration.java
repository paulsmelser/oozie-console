package com.smelser.code.hadoop.oozie.client.dto.mapper;

import com.smelser.code.hadoop.oozie.client.dto.CoordinatorDto;
import com.smelser.code.hadoop.oozie.client.dto.WorkflowActionDto;
import com.smelser.code.hadoop.oozie.client.dto.WorkflowDto;
import com.smelser.code.hadoop.oozie.client.entities.Coordinator;
import com.smelser.code.hadoop.oozie.client.entities.CoordinatorAction;
import com.smelser.code.hadoop.oozie.client.entities.Workflow;
import com.smelser.code.hadoop.oozie.client.dto.CoordinatorActionDto;
import com.smelser.code.hadoop.oozie.client.entities.WorkflowAction;

import simplemapper.Mapper;

public class MapperConfiguration {
	
	static {
		Mapper.createMap(CoordinatorDto.class, Coordinator.class, new CoordinatorMapperConfiguration());
		Mapper.createMap(CoordinatorActionDto.class, CoordinatorAction.class);
		Mapper.createMap(WorkflowDto.class, Workflow.class, new WorkflowMapperConfiguration());
		Mapper.createMap(WorkflowActionDto.class, WorkflowAction.class);
	}

}
