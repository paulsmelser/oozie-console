package com.smelser.oozie.data.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;

import com.smelser.code.hadoop.oozie.client.dto.CoordinatorDto;
import com.smelser.code.hadoop.oozie.client.dto.GetRunningCoordinatorsResponse;
import com.smelser.code.hadoop.oozie.client.entities.Coordinator;
import org.springframework.stereotype.Service;

import simplemapper.Mapper;
import simplemapper.MapperException;

@Service
public class OozieServiceAgentImpl implements OozieServiceAgent{

	@Override
	public Collection<Coordinator> getRunningCoordinators() throws InstantiationException, IllegalAccessException, IllegalArgumentException, NoSuchFieldException, SecurityException, InvocationTargetException, NoSuchMethodException, MapperException {
		GetRunningCoordinatorsResponse coords = gateway.getRunningCoordinators();
		Collection<Coordinator> result = new ArrayList<Coordinator>();
		for (CoordinatorDto dto : coords.getCoordinatorjobs()){
			result.add(Mapper.map(dto, Coordinator.class));
		}
		return result;
	}

}
