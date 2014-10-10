package com.timetalent.client.entities;


/******************************************
 * 类描述： 登录信息
 * 类名称：LoginData  
 * @version: 1.0
 * @time: 2014-2-20 下午3:30:21 
 ******************************************/
public class LoginData {   
	private String passportId; // 
	private String name;  // 姓名
	private String pwd;  // 姓名
	/**
	 * @return the passportId
	 */
	public String getPassportId() {
		return passportId;
	}
	/**
	 * @param passportId the passportId to set
	 */
	public void setPassportId(String passportId) {
		this.passportId = passportId;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the pwd
	 */
	public String getPwd() {
		return pwd;
	}
	/**
	 * @param pwd the pwd to set
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
}
