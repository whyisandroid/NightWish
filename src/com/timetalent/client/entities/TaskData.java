package com.timetalent.client.entities;

import java.util.List;


/******************************************
 * 类描述： TODO
 * 类名称：TaskData  
 * @version: 1.0
 * @author: wanghy
 * @time: 2014-11-8 下午3:32:55 
 ******************************************/
public class TaskData {
	private List<Task> lists;
	private Pages pages;
	/**
	 * @return lists : return the property lists.
	 */
	public List<Task> getLists() {
		return lists;
	}
	/**
	 * @param lists : set the property lists.
	 */
	public void setLists(List<Task> lists) {
		this.lists = lists;
	}
	/**
	 * @return pages : return the property pages.
	 */
	public Pages getPages() {
		return pages;
	}
	/**
	 * @param pages : set the property pages.
	 */
	public void setPages(Pages pages) {
		this.pages = pages;
	}
}
