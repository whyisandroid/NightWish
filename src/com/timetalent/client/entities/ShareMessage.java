package com.timetalent.client.entities;

import com.timetalent.common.util.ShareUtil;


/******************************************
 * 类描述： 分享内容
 * 类名称：ShareMessage  
 * @version: 1.0
 * @author: why
 * @time: 2014-10-9 下午1:23:45 
 ******************************************/
public class ShareMessage {
	private String title;
	private String text;
	private String imageURL;
	private String downURL;
	
	/**
	  * 类的构造方法
	  * 创建一个新的实例 ShareMessage.
	  * @param 
	  * @param text
	  */
	public ShareMessage(String text) {
		this.text = text;
		this.title = "";
		this.imageURL = ShareUtil.imageURL;
		this.downURL = ShareUtil.downURL;
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
	/**
	 * @return imageURL : return the property imageURL.
	 */
	public String getImageURL() {
		return imageURL;
	}
	/**
	 * @param imageURL : set the property imageURL.
	 */
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	/**
	 * @return downURL : return the property downURL.
	 */
	public String getDownURL() {
		return downURL;
	}
	/**
	 * @param downURL : set the property downURL.
	 */
	public void setDownURL(String downURL) {
		this.downURL = downURL;
	}

}
