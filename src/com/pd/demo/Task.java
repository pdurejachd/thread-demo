package com.pd.demo;

public class Task {
	
	private String taskName;
	private String status;
	
	public Task(String taskName){
		this.taskName = taskName;
		this.status = "NEW";
	}
	
	public void startTask() {
		status="STARTED";
		System.out.print(taskName+"	TASK STATUS: "+status);
	}
	
	public void completeTask() {
		status="COMPLETED";
		System.out.print(taskName+"	TASK STATUS: "+status);
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
