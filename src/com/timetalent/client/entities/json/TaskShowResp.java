package com.timetalent.client.entities.json;

import com.timetalent.client.entities.FeedID;
import com.timetalent.client.entities.TaskShowData;


/******************************************
 * 类描述： TODO
 * 类名称：TaskShowResp  
 * @version: 1.0
 * @author: wanghy
 * @time: 2014-11-8 下午6:10:45 
 ******************************************/
public class TaskShowResp extends BaseResp {
	private TaskShowData data;
	
	/**
	 * @param data : set the property data.
	 */
	public void setData(TaskShowData data) {
		this.data = data;
	}
	
	/**
	 * @return data : return the property data.
	 */
	public TaskShowData getData() {
		return data;
	}
}
