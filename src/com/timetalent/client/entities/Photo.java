package com.timetalent.client.entities;


/******************************************
 * 类描述： TODO
 * 类名称：Photo  
 * @version: 1.0
 * @author: wanghy
 * @time: 2014-11-5 下午10:04:38 
 ******************************************/
public class Photo {
	private String	id; // "20",
	private String	feed_id; // "38",
	private String	user_id; // "7",
	private String	savepath; // "./Uploads/feed/",
	private String	savename; // "201411/546881ac06427.jpg",
	private String	extension; // "jpg",
	private String	add_time; // "1416135084",
	private String	url; // "http://124.193.223.166//xingtan/Uploads/feed/201411/546881ac06427.jpg"
	private String sort;
	private String status;
	
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @param url : set the property url.
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	/**
	 * @return url : return the property url.
	 */
	public String getUrl() {
		return url;
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
	 * @return feed_id : return the property feed_id.
	 */
	public String getFeed_id() {
		return feed_id;
	}
	/**
	 * @param feed_id : set the property feed_id.
	 */
	public void setFeed_id(String feed_id) {
		this.feed_id = feed_id;
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
	 * @return savepath : return the property savepath.
	 */
	public String getSavepath() {
		return savepath;
	}
	/**
	 * @param savepath : set the property savepath.
	 */
	public void setSavepath(String savepath) {
		this.savepath = savepath;
	}
	/**
	 * @return savename : return the property savename.
	 */
	public String getSavename() {
		return savename;
	}
	/**
	 * @param savename : set the property savename.
	 */
	public void setSavename(String savename) {
		this.savename = savename;
	}
	/**
	 * @return extension : return the property extension.
	 */
	public String getExtension() {
		return extension;
	}
	/**
	 * @param extension : set the property extension.
	 */
	public void setExtension(String extension) {
		this.extension = extension;
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
