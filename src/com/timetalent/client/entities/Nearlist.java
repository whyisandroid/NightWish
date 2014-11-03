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
		users = new ArrayList<Nearpackage>();
	}
	List<Nearpackage> users = null;

	public List<Nearpackage> getUsers() {
		return users;
	}

	public void setUsers(List<Nearpackage> users) {
		this.users = users;
	}

}
