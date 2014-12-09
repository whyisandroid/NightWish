package com.timetalent.client.entities.json;

import com.timetalent.client.entities.MyYirenyaoqinglist;
import com.timetalent.client.entities.Nearlist;


/******************************************
 * 类描述： 附近返回值
 * 类名称：NearResp  
 * @version: 1.0
 * @time: 2014-2-20 下午3:29:26 
 ******************************************/
public class YiRenyaoqingResp extends BaseResp {
	private MyYirenyaoqinglist data;

	/**
	 * @return data : return the property data.
	 */
	public MyYirenyaoqinglist getData() {
		return data;
	}

	/**
	 * @param data : set the property data.
	 */
	public void setData(MyYirenyaoqinglist data) {
		this.data = data;
	}
	
}
