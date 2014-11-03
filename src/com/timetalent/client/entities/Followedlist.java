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
public class Followedlist {
	public Followedlist(){
		users = new ArrayList<Followedpackage>();
	}
	List<Followedpackage> users = null;

	public List<Followedpackage> getUsers() {
		return users;
	}

	public void setUsers(List<Followedpackage> users) {
		this.users = users;
	}

}
