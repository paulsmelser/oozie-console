package org.smelser.code.hadoop.oozie.client.data.service;

import org.smelser.code.hadoop.oozie.client.dto.GetStatusResponse;
import org.smelser.code.hadoop.oozie.client.entities.*;
import simplemapper.MapperException;

import java.util.Collection;
import java.util.Map;

public interface OozieClient {

    Collection<Workflow> getWorkflows(int len) throws MapperException;

    Collection<Coordinator> getRunningCoordinators(int len) throws MapperException;

    Workflow getWorkflow(String id, int len) throws MapperException;

    Coordinator getCoordinator(String id, int len) throws MapperException;

    WorkflowAction getWorkflowAction(String id, String name) throws  MapperException;

    CoordinatorAction getCoordinatorAction(String id, int index) throws MapperException;

    void kill(String id);

    void rerun(String id) throws OozieException;

    void rerun(String id, String skipNodes) throws OozieException;

    void rerun(String id, String skipNodes, Configuration config) throws OozieException;

    void suspend(String id);

    void resume(String id);

    void start(String id);

    void submit(Map<String, String> conf);

    GetStatusResponse getStatus();
}
