package com.timetalent.client.entities;

import java.io.File;


/******************************************
 * 类描述： 上传图片用
 * 类名称：PicValuePair  
 * @version: 1.0
 * @author: why
 * @time: 2014-11-4 下午6:25:55 
 ******************************************/
public class PicValuePair {
	private String key;
	private File picFile;
	
	/**
	  * 类的构造方法
	  * 创建一个新的实例 PicValuePair.
	  * @param 
	  * @param string
	  * @param picFile2
	  */
	public PicValuePair(String key, File picFile) {
		this.key = key;
		this.picFile = picFile;
	}
	/**
	 * @return key : return the property key.
	 */
	public String getKey() {
		return key;
	}
	/**
	 * @param key : set the property key.
	 */
	public void setKey(String key) {
		this.key = key;
	}
	/**
	 * @return picFile : return the property picFile.
	 */
	public File getPicFile() {
		return picFile;
	}
	/**
	 * @param picFile : set the property picFile.
	 */
	public void setPicFile(File picFile) {
		this.picFile = picFile;
	}
}
