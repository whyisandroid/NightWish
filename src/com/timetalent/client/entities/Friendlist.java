package com.timetalent.client.entities;

import java.util.ArrayList;
import java.util.List;


/******************************************
 * 类描述： TODO
 * 类名称：Friendpackage  
 * @version: 1.0
 * @author: Administrator
 * @time: 2014-11-3 上午10:58:20 
 ******************************************/
public class Friendlist {
	public Friendlist(){
		users = new ArrayList<Friendpackage>();
	}
	List<Friendpackage> users = null;

	public List<Friendpackage> getUsers() {
		return users;
	}

	public void setUsers(List<Friendpackage> users) {
		this.users = users;
	}

}
