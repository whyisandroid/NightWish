package com.timetalent.client.entities.json;

import com.timetalent.client.entities.Blacklist;


/******************************************
 * 类描述： 附近返回值
 * 类名称：NearResp  
 * @version: 1.0
 * @time: 2014-2-20 下午3:29:26 
 ******************************************/
public class BlackResp extends BaseResp {
	private Blacklist data;

	/**
	 * @return data : return the property data.
	 */
	public Blacklist getData() {
		return data;
	}

	/**
	 * @param data : set the property data.
	 */
	public void setData(Blacklist data) {
		this.data = data;
	}
	
}
