package com.timetalent.client.entities;

import java.util.List;


/******************************************
 * 类描述： TODO
 * 类名称：Searchpackage  
 * @version: 1.0
 * @author: Administrator
 * @time: 2014-11-3 上午10:58:20 
 ******************************************/
public class Mytaskpackage {
	String id = "";
	String user_id = "";
	String title = "";
	String description = "";
	String place = "";
	String banner = "";
	String cutoff_date = "";
	String view_num = "";
	String sort = "";
	String add_time = "";
	List<Jobpackage> jobs = null;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getBanner() {
		return banner;
	}
	public void setBanner(String banner) {
		this.banner = banner;
	}
	public String getCutoff_date() {
		return cutoff_date;
	}
	public void setCutoff_date(String cutoff_date) {
		this.cutoff_date = cutoff_date;
	}
	public String getView_num() {
		return view_num;
	}
	public void setView_num(String view_num) {
		this.view_num = view_num;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getAdd_time() {
		return add_time;
	}
	public void setAdd_time(String add_time) {
		this.add_time = add_time;
	}
	public List<Jobpackage> getJobs() {
		return jobs;
	}
	public void setJobs(List<Jobpackage> jobs) {
		this.jobs = jobs;
	}

	
}
