package com.timetalent.client.entities.json;

import com.timetalent.client.entities.Searchlist;


/******************************************
 * 类描述： 附近返回值
 * 类名称：NearResp  
 * @version: 1.0
 * @time: 2014-2-20 下午3:29:26 
 ******************************************/
public class SearchResp extends BaseResp {
	private Searchlist data;

	/**
	 * @return data : return the property data.
	 */
	public Searchlist getData() {
		return data;
	}

	/**
	 * @param data : set the property data.
	 */
	public void setData(Searchlist data) {
		this.data = data;
	}
	
}
