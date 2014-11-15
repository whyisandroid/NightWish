package com.timetalent.client.entities;


/******************************************
 * 类描述： TODO
 * 类名称：Picture  
 * @version: 1.0
 * @author: why
 * @time: 2014-11-15 下午4:11:15 
 ******************************************/
public class Picture {
	private String path; //地址
	private int id; //id
	
	/**
	  * 类的构造方法
	  * 创建一个新的实例 Picture.
	  * @param 
	  * @param id2
	  * @param path2
	  */
	public Picture(int id, String path) {
		this.path = path;
		this.id = id;
	}
	/**
	 * @return path : return the property path.
	 */
	public String getPath() {
		return path;
	}
	/**
	 * @param path : set the property path.
	 */
	public void setPath(String path) {
		this.path = path;
	}
	/**
	 * @return id : return the property id.
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id : set the property id.
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return id;
	}
}
