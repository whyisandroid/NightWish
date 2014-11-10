package com.timetalent.client.entities.json;

import com.timetalent.client.entities.Blacklist;
import com.timetalent.client.entities.FeedID;
import com.timetalent.client.entities.Taskid;


/******************************************
 * 类描述： TODO
 * 类名称：TaskADDResp  
 * @version: 1.0
 * @author: wanghy
 * @time: 2014-11-6 上午8:04:38 
 ******************************************/
public class TaskADDResp extends BaseResp {
	private Taskid data;

	/**
	 * @param data : set the property data.
	 */
	public void setData(Taskid data) {
		this.data = data;
	}
	
	/**
	 * @return data : return the property data.
	 */
	public Taskid getData() {
		return data;
	}
}
