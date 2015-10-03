package org.smelser.code.hadoop.oozie.client.dto.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.smelser.code.hadoop.oozie.client.dto.WorkflowActionDto;
import org.smelser.code.hadoop.oozie.client.dto.WorkflowDto;
import org.smelser.code.hadoop.oozie.client.entities.ConfigurationSerializer;
import org.smelser.code.hadoop.oozie.client.entities.Workflow;
import org.smelser.code.hadoop.oozie.client.entities.WorkflowAction;
import org.smelser.code.hadoop.oozie.client.utils.JsonUtils;

import simplemapper.CustomMapping;
import simplemapper.Mapper;

public class WorkflowMapperConfiguration implements CustomMapping<WorkflowDto, Workflow>{

	public void map(WorkflowDto source, Workflow destination) {
		if(source.getConf() != null){
			destination.setConf(ConfigurationSerializer.fromXml(source.getConf()));
		}
		if(source.getStartTime() != null){
			destination.setStartTime(JsonUtils.parseDateRfc822(source.getStartTime()));
		}
		if(source.getEndTime() != null){
			destination.setEndTime(JsonUtils.parseDateRfc822(source.getEndTime()));
		}
		if (source.getActions() != null){
			Collection<WorkflowAction> actions = new ArrayList<WorkflowAction>();
			Iterator<WorkflowActionDto> it = source.getActions().iterator();
			while(it.hasNext()){
				try {
					actions.add(Mapper.map(it.next(), WorkflowAction.class));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			destination.setActions(actions);
		}
	}
}
