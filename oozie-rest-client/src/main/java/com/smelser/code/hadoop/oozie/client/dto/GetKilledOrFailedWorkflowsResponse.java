package com.smelser.code.hadoop.oozie.client.dto;

public class GetKilledOrFailedWorkflowsResponse {
	private GetWorkflowListResponse workflows;

	public GetWorkflowListResponse getWorkflows() {
		return workflows;
	}

	public void setWorkflows(GetWorkflowListResponse workflows) {
		this.workflows = workflows;
	}
}
