package com.smelser.oozie;

import com.smelser.code.hadoop.oozie.SpringProfiles;
import com.smelser.code.hadoop.oozie.client.data.service.OozieClient;
import com.smelser.code.hadoop.oozie.client.data.service.stubs.OozieClientStub;
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
import com.smelser.oozie.authorization.AuthorizationCookie;
import com.smelser.oozie.authorization.CookieAuthorizationFilter;
import com.smelser.oozie.data.OozieClientFactory;
import com.smelser.oozie.utilities.ServiceLocator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import simplemapper.Mapper;

import javax.servlet.Filter;

/**
 * Created by paul.smelser@gmail.com on 03/10/15.
 * @author psmelser
 */
@SpringBootApplication
public class OozieWebApplication {

    public static void main(String [] args){
        SpringApplication.run(OozieWebApplication.class, args);
    }

    static {
        Mapper.createMap(CoordinatorDto.class, Coordinator.class, new CoordinatorMapperConfiguration());
        Mapper.createMap(CoordinatorActionDto.class, CoordinatorAction.class);
        Mapper.createMap(WorkflowDto.class, Workflow.class, new WorkflowMapperConfiguration());
        Mapper.createMap(WorkflowActionDto.class, WorkflowAction.class);
    }

    @Bean
    public ServletContextTemplateResolver templateResolver() {
        ServletContextTemplateResolver resolver = new ServletContextTemplateResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        resolver.setTemplateMode("HTML5");
        resolver.setCacheable(false);

        return resolver;
    }

    @Bean
    public Filter cookieAuthorizationFilter(AuthorizationCookie authorizationCookie,
                                            OozieClientFactory factory,
                                            ServiceLocator serviceLocator){
        return new CookieAuthorizationFilter(authorizationCookie, factory, serviceLocator);
    }

    @Bean
    @Profile(SpringProfiles.DEVELOPMENT)
    public OozieClient oozieClient(){
        return new OozieClientStub();
    }

}
