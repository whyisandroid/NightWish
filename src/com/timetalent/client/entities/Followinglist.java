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
public class Followinglist {
	public Followinglist(){
		users = new ArrayList<Followingpackage>();
	}
	List<Followingpackage> users = null;

	public List<Followingpackage> getUsers() {
		return users;
	}

	public void setUsers(List<Followingpackage> users) {
		this.users = users;
	}

}
