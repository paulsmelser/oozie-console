package com.smelser.code.hadoop.oozie.client.dto;

import java.io.IOException;

import org.junit.Test;

import com.google.gson.Gson;
import com.smelser.code.hadoop.oozie.client.dto.mapper.CoordinatorMapperConfiguration;
import com.smelser.code.hadoop.oozie.client.entities.Coordinator;
import com.smelser.code.hadoop.oozie.client.utils.Resource;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import simplemapper.Mapper;
import simplemapper.MapperException;

public class CoordinatorDtoMappingTest {

    @Test
    public void test() throws IOException, MapperException {

        Resource resource = Resource.fromFile("coordinator.json");
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(CoordinatorDto.class, Coordinator.class);


        CoordinatorDto coordinator = new Gson().fromJson(resource.getAsString(), CoordinatorDto.class);
        Mapper.createMap(CoordinatorDto.class, Coordinator.class, new CoordinatorMapperConfiguration());

        long startTime = System.nanoTime();
        Coordinator map = Mapper.map(coordinator, Coordinator.class);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);


        long startTime2 = System.nanoTime();
        Coordinator map2 = mapperFactory.getMapperFacade().map(coordinator, Coordinator.class);
        long endTime2 = System.nanoTime();
        long duration2 = (endTime2 - startTime2);

        Gson gson = new Gson();
        System.out.println(gson.toJson(map));
        System.out.println(gson.toJson(map2));

        System.out.println(duration);
        System.out.println(duration2);
        System.out.println(duration/duration2);
    }

}