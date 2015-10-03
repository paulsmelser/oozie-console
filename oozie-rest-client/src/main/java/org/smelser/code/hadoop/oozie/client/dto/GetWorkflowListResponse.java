package org.smelser.code.hadoop.oozie.client.dto;

import java.util.List;

public class GetWorkflowListResponse {
	private int total;
	private List<WorkflowDto> workflows;
	
	public List<WorkflowDto> getWorkflows() {
		return workflows;
	}
	public void setWorkflows(List<WorkflowDto> workflows) {
		this.workflows = workflows;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
}
