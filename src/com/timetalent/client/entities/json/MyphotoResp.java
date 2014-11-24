package com.timetalent.client.entities.json;

import java.util.List;

import com.timetalent.client.entities.MyPhotopackage;
import com.timetalent.client.entities.dictionarypackage;


/******************************************
 * 类描述： 附近返回值
 * 类名称：NearResp  
 * @version: 1.0
 * @time: 2014-2-20 下午3:29:26 
 ******************************************/
public class MyphotoResp extends BaseResp {
	private List<MyPhotopackage> data;

	public List<MyPhotopackage> getData() {
		return data;
	}

	public void setData(List<MyPhotopackage> data) {
		this.data = data;
	}



	
}
