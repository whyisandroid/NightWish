package com.timetalent.client.entities;

import java.util.List;


/******************************************
 * 类描述： TODO
 * 类名称：Searchpackage  
 * @version: 1.0
 * @author: Administrator
 * @time: 2014-11-3 上午10:58:20 
 ******************************************/
public class Userinfopackage {
	String username = "";
	String sex = "";
	String nickname = "";
	String birthday = "";
	String phone = "";
	String avatar = "";
	String constella = "";
	String type = "";
	String major = "0";
	String loyal_pass = "1";
	String age = "";
	String id = "";
	Userinfomorepackage more = null;
	Countpackage count = null;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getConstella() {
		return constella;
	}
	public void setConstella(String constella) {
		this.constella = constella;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getLoyal_pass() {
		return loyal_pass;
	}
	public void setLoyal_pass(String loyal_pass) {
		this.loyal_pass = loyal_pass;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Userinfomorepackage getMore() {
		return more;
	}
	public void setMore(Userinfomorepackage more) {
		this.more = more;
	}
	public Countpackage getCount() {
		return count;
	}
	public void setCount(Countpackage count) {
		this.count = count;
	}
	
}
