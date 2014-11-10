package com.timetalent.client.entities;


/******************************************
 * 类描述： TODO
 * 类名称：TaskShow  
 * @version: 1.0
 * @author: wanghy
 * @time: 2014-11-8 下午6:07:25 
 ******************************************/
public class TaskShow {
	private String	id; // "1",
	private String	task_id; // "1",
	private String	job; // "php工程师",
	private String	description; // "好多职位等你哦~",
	private String	work_date_start; // "0",
	private String	work_date_end; // "0"
	/**
	 * @return id : return the property id.
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id : set the property id.
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return task_id : return the property task_id.
	 */
	public String getTask_id() {
		return task_id;
	}
	/**
	 * @param task_id : set the property task_id.
	 */
	public void setTask_id(String task_id) {
		this.task_id = task_id;
	}
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
