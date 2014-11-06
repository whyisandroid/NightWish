package com.timetalent.client.entities.json;

import com.timetalent.client.entities.Blacklist;
import com.timetalent.client.entities.FeedID;


/******************************************
 * 类描述： TODO
 * 类名称：FeedADDResp  
 * @version: 1.0
 * @author: wanghy
 * @time: 2014-11-6 上午8:04:38 
 ******************************************/
public class FeedADDResp extends BaseResp {
	private FeedID data;

	/**
	 * @param data : set the property data.
	 */
	public void setData(FeedID data) {
		this.data = data;
	}
	
	/**
	 * @return data : return the property data.
	 */
	public FeedID getData() {
		return data;
	}
}
