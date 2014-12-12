package com.timetalent.client.entities.json;

import com.timetalent.client.entities.AppConfigPackage;
import com.timetalent.client.entities.LoginData;
import com.timetalent.client.entities.Walletorderpackage;


/******************************************
 * 类描述： 登录返回值
 * 类名称：LoginResp  
 * @version: 1.0
 * @time: 2014-2-20 下午3:29:26 
 ******************************************/
public class AppConfigResp extends BaseResp {
	private String data;

	/**
	 * @return data : return the property data.
	 */
	public String getData() {
		return data;
	}

	/**
	 * @param data : set the property data.
	 */
	public void setData(String data) {
		this.data = data;
	}
	
}
