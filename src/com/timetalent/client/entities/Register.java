package com.timetalent.client.entities;

import java.io.Serializable;

import android.os.Parcel;
import android.os.Parcelable;


/******************************************
 * 类描述： TODO
 * 类名称：Register  
 * @version: 1.0
 * @author: wanghy
 * @time: 2014-10-22 上午8:25:44 
 ******************************************/
public class Register implements Serializable{
	private String	username; //用户名
	private String	phone; //手机
	private String	email; //邮箱
	private String	password; //密码
	private String	birthday; //生日 11位时间戳
	private String	sex; //性别 1男2女
	private String	type; //身份类型 星探scout明星star粉丝fans
	/**
	 * @return username : return the property username.
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username : set the property username.
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return phone : return the property phone.
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * @param phone : set the property phone.
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * @return email : return the property email.
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email : set the property email.
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return password : return the property password.
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password : set the property password.
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return birthday : return the property birthday.
	 */
	public String getBirthday() {
		return birthday;
	}
	/**
	 * @param birthday : set the property birthday.
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	/**
	 * @return sex : return the property sex.
	 */
	public String getSex() {
		return sex;
	}
	/**
	 * @param sex : set the property sex.
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
	/**
	 * @return type : return the property type.
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type : set the property type.
	 */
	public void setType(String type) {
		this.type = type;
	}
}
