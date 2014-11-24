package com.timetalent.client.entities;


/******************************************
 * 类描述： TODO
 * 类名称：Replay  
 * @version: 1.0
 * @author: wanghy
 * @time: 2014-11-4 上午8:27:04 
 ******************************************/
public class Replay {
	private String id;
	private String feed_id;
	private String user_id;
	private String contents;
	private String time;
	private User user;
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
	 * @return contents : return the property contents.
	 */
	public String getContents() {
		return contents;
	}
	/**
	 * @param contents : set the property contents.
	 */
	public void setContents(String contents) {
		this.contents = contents;
	}
	/**
	 * @return time : return the property time.
	 */
	public String getTime() {
		return time;
	}
	/**
	 * @param time : set the property time.
	 */
	public void setTime(String time) {
		this.time = time;
	}
	/**
	 * @return user : return the property user.
	 */
	public User getUser() {
		return user;
	}
	/**
	 * @param user : set the property user.
	 */
	public void setUser(User user) {
		this.user = user;
	}
}
