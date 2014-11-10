package com.timetalent.client.entities;


/******************************************
 * 类描述： TODO
 * 类名称：Job  
 * @version: 1.0
 * @author: wanghy
 * @time: 2014-11-9 下午3:38:17 
 ******************************************/
public class Job {
	private String job;//	职位名称
	private String description;//	工作介绍
	private String work_date_start;//	工作开始时间 格式 秒时间戳
	private String work_date_end;//	工作结束时间 格式 秒时间戳
	/**
	 * @return job : return the property job.
	 */
	public String getJob() {
		return job;
	}
	/**
	 * @param job : set the property job.
	 */
	public void setJob(String job) {
		this.job = job;
	}
	/**
	 * @return description : return the property description.
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description : set the property description.
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return work_date_start : return the property work_date_start.
	 */
	public String getWork_date_start() {
		return work_date_start;
	}
	/**
	 * @param work_date_start : set the property work_date_start.
	 */
	public void setWork_date_start(String work_date_start) {
		this.work_date_start = work_date_start;
	}
	/**
	 * @return work_date_end : return the property work_date_end.
	 */
	public String getWork_date_end() {
		return work_date_end;
	}
	/**
	 * @param work_date_end : set the property work_date_end.
	 */
	public void setWork_date_end(String work_date_end) {
		this.work_date_end = work_date_end;
	}
}
