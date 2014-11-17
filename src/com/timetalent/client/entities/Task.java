package com.timetalent.client.entities;


/******************************************
 * 类描述： TODO
 * 类名称：Task  
 * @version: 1.0
 * @author: wanghy
 * @time: 2014-11-8 下午3:34:24 
 ******************************************/
public class Task {
	private String	id; // "1",
	private String	user_id; // "2",
	private String	title; // "剧组招聘",
	private String	place; // "各地",
	private String	banner; // "",
	private String	cutoff_date; // "0",
	private String	view_num; // "0",
	private String	sort; // "0",
	private String	add_time; // "0"
	private String  description;
	
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
	 * @return user_id : return the property user_id.
	 */
	public String getUser_id() {
		return user_id;
	}
	/**
	 * @param user_id : set the property user_id.
	 */
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	/**
	 * @return title : return the property title.
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title : set the property title.
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return place : return the property place.
	 */
	public String getPlace() {
		return place;
	}
	/**
	 * @param place : set the property place.
	 */
	public void setPlace(String place) {
		this.place = place;
	}
	/**
	 * @return banner : return the property banner.
	 */
	public String getBanner() {
		return banner;
	}
	/**
	 * @param banner : set the property banner.
	 */
	public void setBanner(String banner) {
		this.banner = banner;
	}
	/**
	 * @return cutoff_date : return the property cutoff_date.
	 */
	public String getCutoff_date() {
		return cutoff_date;
	}
	/**
	 * @param cutoff_date : set the property cutoff_date.
	 */
	public void setCutoff_date(String cutoff_date) {
		this.cutoff_date = cutoff_date;
	}
	/**
	 * @return view_num : return the property view_num.
	 */
	public String getView_num() {
		return view_num;
	}
	/**
	 * @param view_num : set the property view_num.
	 */
	public void setView_num(String view_num) {
		this.view_num = view_num;
	}
	/**
	 * @return sort : return the property sort.
	 */
	public String getSort() {
		return sort;
	}
	/**
	 * @param sort : set the property sort.
	 */
	public void setSort(String sort) {
		this.sort = sort;
	}
	/**
	 * @return add_time : return the property add_time.
	 */
	public String getAdd_time() {
		return add_time;
	}
	/**
	 * @param add_time : set the property add_time.
	 */
	public void setAdd_time(String add_time) {
		this.add_time = add_time;
	}
}
