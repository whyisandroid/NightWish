package com.timetalent.client.entities.json;

import com.timetalent.client.entities.TaskData;


/******************************************
 * 类描述： TODO
 * 类名称：TaskResp  
 * @version: 1.0
 * @author: wanghy
 * @time: 2014-11-8 下午3:32:34 
 ******************************************/

/******************************************
	 * 类描述： TODO
	 * 类名称：TaskResp  
 	 * @version: 1.0
	 * @author: wanghy
	 * @time: 2014-11-8 下午3:35:16 
******************************************/
public class TaskResp extends BaseResp {
	private TaskData data;
	
	/**
	 * @param data : set the property data.
	 */
	public void setData(TaskData data) {
		this.data = data;
	}
	
	/**
	 * @return data : return the property data.
	 */
	public TaskData getData() {
		return data;
	}
}
