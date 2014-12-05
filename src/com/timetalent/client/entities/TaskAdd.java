package com.timetalent.client.entities;


/******************************************
 * 类描述： TODO
 * 类名称：TaskAdd  
 * @version: 1.0
 * @author: wanghy
 * @time: 2014-11-9 下午4:39:13 
 ******************************************/
public class TaskAdd {
	private String	_session_id; //用户会话ID
	private String	title; //工作标题
	private String	place; //工作地点
	private String	job_lists_json; //职位列表(可以为空,之后调用Task/add_job方法亦可) json数据[{'job':'','description':'','work_date_start':'','work_date_end':''},{'job':'','description':'','work_date_start':'','work_date_end':''}]
	private String	cutoff_date; //报名截止日期 格式 秒时间戳
	private String description;
	
	/**
	 * @param description : set the property description.
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * @return description : return the property description.
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @return _session_id : return the property _session_id.
	 */
	public String get_session_id() {
		return _session_id;
	}
	/**
	 * @param _session_id : set the property _session_id.
	 */
	public void set_session_id(String _session_id) {
		this._session_id = _session_id;
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
	 * @return job_lists_json : return the property job_lists_json.
	 */
	public String getJob_lists_json() {
		return job_lists_json;
	}
	/**
	 * @param job_lists_json : set the property job_lists_json.
	 */
	public void setJob_lists_json(String job_lists_json) {
		this.job_lists_json = job_lists_json;
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
}
