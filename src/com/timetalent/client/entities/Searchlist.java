package com.timetalent.client.entities;

import java.util.ArrayList;
import java.util.List;


/******************************************
 * 类描述： TODO
 * 类名称：Searchpackage  
 * @version: 1.0
 * @author: Administrator
 * @time: 2014-11-3 上午10:58:20 
 ******************************************/
public class Searchlist {
	public Searchlist(){
		users = new ArrayList<Searchpackage>();
	}
	List<Searchpackage> users = null;

	public List<Searchpackage> getUsers() {
		return users;
	}

	public void setUsers(List<Searchpackage> users) {
		this.users = users;
	}

}
