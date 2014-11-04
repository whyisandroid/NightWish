package com.timetalent.client.entities.json;

import com.timetalent.client.entities.FeedData;


/******************************************
 * 类描述： TODO
 * 类名称：FeedResp  
 * @version: 1.0
 * @author: wanghy
 * @time: 2014-11-4 上午8:32:14 
 ******************************************/
public class FeedResp extends BaseResp {
	private FeedData data;

	/**
	 * @return data : return the property data.
	 */
	public FeedData getData() {
		return data;
	}

	/**
	 * @param data : set the property data.
	 */
	public void setData(FeedData data) {
		this.data = data;
	}
}
