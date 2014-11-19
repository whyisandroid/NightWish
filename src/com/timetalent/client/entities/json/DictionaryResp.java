package com.timetalent.client.entities.json;

import java.util.List;

import com.timetalent.client.entities.dictionarypackage;


/******************************************
 * 类描述： 附近返回值
 * 类名称：NearResp  
 * @version: 1.0
 * @time: 2014-2-20 下午3:29:26 
 ******************************************/
public class DictionaryResp extends BaseResp {
	private List<dictionarypackage> data;

	public List<dictionarypackage> getData() {
		return data;
	}

	public void setData(List<dictionarypackage> data) {
		this.data = data;
	}



	
}
