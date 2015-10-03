package org.smelser.code.hadoop.oozie.client.dto.mapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.smelser.code.hadoop.oozie.client.dto.CoordinatorActionDto;
import org.smelser.code.hadoop.oozie.client.dto.CoordinatorDto;
import org.smelser.code.hadoop.oozie.client.entities.ConfigurationSerializer;
import org.smelser.code.hadoop.oozie.client.entities.Coordinator;
import org.smelser.code.hadoop.oozie.client.entities.CoordinatorAction;
import org.smelser.code.hadoop.oozie.client.utils.JsonUtils;

import simplemapper.CustomMapping;
import simplemapper.Mapper;

public class CoordinatorMapperConfiguration implements CustomMapping<CoordinatorDto, Coordinator>{

	public void map(CoordinatorDto source, Coordinator destination) {
		if (source.getConf() != null){
			destination.setConf(ConfigurationSerializer.fromXml(source.getConf()));
		}
		if(source.getStartTime() != null){
			destination.setStartTime(JsonUtils.parseDateRfc822(source.getStartTime()));
		}
		if(source.getEndTime() != null){
			destination.setEndTime(JsonUtils.parseDateRfc822(source.getEndTime()));
		}
		if (source.getActions() != null){
			Collection<CoordinatorAction> actions = new ArrayList<CoordinatorAction>();
			Iterator<CoordinatorActionDto> it = source.getActions().iterator();
			while(it.hasNext()){
				try {
					actions.add(Mapper.map(it.next(), CoordinatorAction.class));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			destination.setActions(actions);
		}
	}

}
