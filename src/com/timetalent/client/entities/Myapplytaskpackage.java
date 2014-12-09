package com.timetalent.client.entities;


/******************************************
 * 类描述： TODO
 * 类名称：Searchpackage  
 * @version: 1.0
 * @author: Administrator
 * @time: 2014-11-3 上午10:58:20 
 ******************************************/
public class Myapplytaskpackage {
	String user_id = "";
	String task_id = "";
	String task_job_id = "";
	String pass = "";
	String time = "";
	Taskpackage task = null;
	String time_ago = "";
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getTask_id() {
		return task_id;
	}
	public void setTask_id(String task_id) {
		this.task_id = task_id;
	}
	public String getTask_job_id() {
		return task_job_id;
	}
	public void setTask_job_id(String task_job_id) {
		this.task_job_id = task_job_id;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Taskpackage getTask() {
		return task;
	}
	public void setTask(Taskpackage task) {
		this.task = task;
	}
	public String getTime_ago() {
		return time_ago;
	}
	public void setTime_ago(String time_ago) {
		this.time_ago = time_ago;
	}

	
}
