package com.timetalent.client.entities;

import java.util.List;


/******************************************
 * 类描述： TODO
 * 类名称：FeedData  
 * @version: 1.0
 * @author: wanghy
 * @time: 2014-11-4 上午8:33:06 
 ******************************************/

/******************************************
	 * 类描述： TODO
	 * 类名称：FeedData  
 	 * @version: 1.0
	 * @author: wanghy
	 * @time: 2014-11-5 下午10:09:49 
******************************************/
public class FeedData {
	private Pages	pages;
	private List<Feed> lists;

	
	/**
	 * @param pages : set the property pages.
	 */
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	
	/**
	 * @return pages : return the property pages.
	 */
	public Pages getPages() {
		return pages;
	}
	/**
	 * @param lists : set the property lists.
	 */
	public void setLists(List<Feed> lists) {
		this.lists = lists;
	}
	
	/**
	 * @return lists : return the property lists.
	 */
	public List<Feed> getLists() {
		return lists;
	}
	
	
}
