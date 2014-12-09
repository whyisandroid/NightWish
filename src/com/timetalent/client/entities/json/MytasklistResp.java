package com.timetalent.client.entities.json;

import com.timetalent.client.entities.Myapplytasklist;
import com.timetalent.client.entities.Mytasklist;
import com.timetalent.client.entities.Nearlist;


/******************************************
 * 类描述： 附近返回值
 * 类名称：NearResp  
 * @version: 1.0
 * @time: 2014-2-20 下午3:29:26 
 ******************************************/
public class MytasklistResp extends BaseResp {
	private Mytasklist data;

	/**
	 * @return data : return the property data.
	 */
	public Mytasklist getData() {
		return data;
	}

	/**
	 * @param data : set the property data.
	 */
	public void setData(Mytasklist data) {
		this.data = data;
	}
	
}
