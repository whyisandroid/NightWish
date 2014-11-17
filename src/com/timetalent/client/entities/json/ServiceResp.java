package com.timetalent.client.entities.json;

import com.timetalent.client.entities.Blacklist;
import com.timetalent.client.entities.Servicelist;


/******************************************
 * 类描述： 附近返回值
 * 类名称：NearResp  
 * @version: 1.0
 * @time: 2014-2-20 下午3:29:26 
 ******************************************/
public class ServiceResp extends BaseResp {
	Servicelist data;

	public Servicelist getData() {
		return data;
	}

	public void setData(Servicelist data) {
		this.data = data;
	}
	
	
}
