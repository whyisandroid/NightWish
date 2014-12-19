package com.timetalent.client.entities;

import java.util.ArrayList;
import java.util.List;


/******************************************
 * 类描述： TODO
 * 类名称：Nearpackage  
 * @version: 1.0
 * @author: Administrator
 * @time: 2014-11-3 上午10:58:20 
 ******************************************/
public class Nearlist {
	public Nearlist(){
	}
	List<Nearpackage> lists = null;
	Pages pages = null;
	public List<Nearpackage> getLists() {
		return lists;
	}
	public void setLists(List<Nearpackage> lists) {
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
