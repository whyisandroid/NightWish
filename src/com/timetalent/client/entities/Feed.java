package com.timetalent.client.entities;

import java.util.List;


/******************************************
 * 类描述： TODO
 * 类名称：Feed  
 * @version: 1.0
 * @author: wanghy
 * @time: 2014-11-4 上午8:23:52 
 ******************************************/
public class Feed {
	private String	id; // "17",
	private String	user_id; // "2",
	private String	contents; // "asdasd",
	private String	time; // "1414985518",
	private List<String>	photos; // [],
	private String	favour_num; // "0",
	private String	favour_do; // "N",
	private String	reply_num; // "0",
	private List<Replay> reply; // [],
	private User	user; // {
	private String	time_ago; // "昨天 11:31"
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
	 * @return photos : return the property photos.
	 */
	public List<String> getPhotos() {
		return photos;
	}
	/**
	 * @param photos : set the property photos.
	 */
	public void setPhotos(List<String> photos) {
		this.photos = photos;
	}
	/**
	 * @return favour_num : return the property favour_num.
	 */
	public String getFavour_num() {
		return favour_num;
	}
	/**
	 * @param favour_num : set the property favour_num.
	 */
	public void setFavour_num(String favour_num) {
		this.favour_num = favour_num;
	}
	/**
	 * @return favour_do : return the property favour_do.
	 */
	public String getFavour_do() {
		return favour_do;
	}
	/**
	 * @param favour_do : set the property favour_do.
	 */
	public void setFavour_do(String favour_do) {
		this.favour_do = favour_do;
	}
	/**
	 * @return reply_num : return the property reply_num.
	 */
	public String getReply_num() {
		return reply_num;
	}
	/**
	 * @param reply_num : set the property reply_num.
	 */
	public void setReply_num(String reply_num) {
		this.reply_num = reply_num;
	}
	/**
	 * @return reply : return the property reply.
	 */
	public List<Replay> getReply() {
		return reply;
	}
	/**
	 * @param reply : set the property reply.
	 */
	public void setReply(List<Replay> reply) {
		this.reply = reply;
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
	/**
	 * @return time_ago : return the property time_ago.
	 */
	public String getTime_ago() {
		return time_ago;
	}
	/**
	 * @param time_ago : set the property time_ago.
	 */
	public void setTime_ago(String time_ago) {
		this.time_ago = time_ago;
	}
	
}
