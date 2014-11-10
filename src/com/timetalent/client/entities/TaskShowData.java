package com.timetalent.client.entities;

import java.util.List;


/******************************************
 * 类描述： TODO
 * 类名称：TaskShowData  
 * @version: 1.0
 * @author: wanghy
 * @time: 2014-11-8 下午6:08:48 
 ******************************************/
public class TaskShowData extends Task{
	private List<TaskShow> jobs;
	/**
	 * @param jobs : set the property jobs.
	 */
	public void setJobs(List<TaskShow> jobs) {
		this.jobs = jobs;
	}
	
	/**
	 * @return jobs : return the property jobs.
	 */
	public List<TaskShow> getJobs() {
		return jobs;
	}
}
