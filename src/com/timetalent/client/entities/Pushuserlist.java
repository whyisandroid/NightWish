package com.timetalent.client.entities;

import java.util.ArrayList;
import java.util.List;


/******************************************
 * 类描述： TODO
 * 类名称：Pushuserpackage  
 * @version: 1.0
 * @author: Administrator
 * @time: 2014-11-3 上午10:58:20 
 ******************************************/
public class Pushuserlist {
	public Pushuserlist(){
		users = new ArrayList<Pushuserpackage>();
	}
	List<Pushuserpackage> users = null;

	public List<Pushuserpackage> getUsers() {
		return users;
	}

	public void setUsers(List<Pushuserpackage> users) {
		this.users = users;
	}

}
