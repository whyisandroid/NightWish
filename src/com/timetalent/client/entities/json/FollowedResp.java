package com.timetalent.client.entities.json;

import com.timetalent.client.entities.Followedlist;


/******************************************
 * 类描述： 附近返回值
 * 类名称：NearResp  
 * @version: 1.0
 * @time: 2014-2-20 下午3:29:26 
 ******************************************/
public class FollowedResp extends BaseResp {
	private Followedlist data;

	/**
	 * @return data : return the property data.
	 */
	public Followedlist getData() {
		return data;
	}

	/**
	 * @param data : set the property data.
	 */
	public void setData(Followedlist data) {
		this.data = data;
	}
	
}
