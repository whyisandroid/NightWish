package com.timetalent.client.entities;


/******************************************
 * 类描述： TODO
 * 类名称：Job  
 * @version: 1.0
 * @author: wanghy
 * @time: 2014-11-9 下午3:38:17 
 ******************************************/
public class Jobpackage {
	private String id;//	职位名称
	private String task_id;//	工作介绍
	private String job;//	工作开始时间 格式 秒时间戳
	private String description;//	工作结束时间 格式 秒时间戳
	private String work_date_start;//	工作结束时间 格式 秒时间戳
	private String work_date_end;//	工作结束时间 格式 秒时间戳
	private String apply_num;//	工作结束时间 格式 秒时间戳
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTask_id() {
		return task_id;
	}
	public void setTask_id(String task_id) {
		this.task_id = task_id;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getWork_date_start() {
		return work_date_start;
	}
	public void setWork_date_start(String work_date_start) {
		this.work_date_start = work_date_start;
	}
	public String getWork_date_end() {
		return work_date_end;
	}
	public void setWork_date_end(String work_date_end) {
		this.work_date_end = work_date_end;
	}
	public String getApply_num() {
		return apply_num;
	}
	public void setApply_num(String apply_num) {
		this.apply_num = apply_num;
	}

}
