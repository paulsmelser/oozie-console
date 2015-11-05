package com.smelser.code.hadoop.oozie.client.dto;

import java.util.Collection;

public class GetRunningCoordinatorsResponse {
	private int total;
	private int offset;
	private int len;
	private Collection<CoordinatorDto> coordinatorjobs;
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public int getLen() {
		return len;
	}
	public void setLen(int len) {
		this.len = len;
	}
	public Collection<CoordinatorDto> getCoordinatorjobs() {
		return coordinatorjobs;
	}
	public void setCoordinatorjobs(Collection<CoordinatorDto> coordinatorjobs) {
		this.coordinatorjobs = coordinatorjobs;
	}
}
