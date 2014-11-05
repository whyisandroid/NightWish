package com.timetalent.client.entities.json;


/******************************************
 * 类描述： 网络返回对象基类
 * 类名称：BaseResp  
 * @version: 1.0
 * @author: ly
 * @time: 2013-12-9 下午2:39:25 
 ******************************************/
public class BaseResp {
	
	private String status;
	private String text;
	
	/**
	 * @return status : return the property status.
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status : set the property status.
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return text : return the property text.
	 */
	public String getText() {
		return text;
	}
	/**
	 * @param text : set the property text.
	 */
	public void setText(String text) {
		this.text = text;
	}
}
