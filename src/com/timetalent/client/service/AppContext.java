package com.timetalent.client.service;

import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;


/******************************************
 * 类描述：  该类用来缓存客户端数据，对外部提供访问这些数据的方法。
 * 类名称：AppContext  
 * @version: 1.0
 * @author: why
 * @time: 2014-2-13 下午2:09:22 
 ******************************************/


public class AppContext {
	/** 缓存运行时业务数据 **/
	private Map<String, Object> businessData;

	/** 客户端配置信息 **/
	private Properties configProerties;

	public String getSystemProperty(String name) {
		return configProerties.getProperty(name);
	}

	public void setConfigProerties(Properties configProerties) {
		this.configProerties = configProerties;
	}

	public AppContext() {
		businessData = new Hashtable<String, Object>();
		// /** 初始化测试数据 **/
		// User user = new User();
		//CreditWealthApplication.getInstance().setLogin(true);
		
	}

	public Map<String, Object> getBusinessData() {
		return businessData;
	}

	public Object getBusinessData(String key) {
		return businessData.get(key);
	}
	public String getStringData(String key){
		return (String)businessData.get(key);
	}

	public void addBusinessData(String name, Object value) {
		businessData.put(name, value);
	}

	public void deleteBusinessData(String key) {
		businessData.remove(key);
	}

	public void clearBusinessData(){
		businessData.clear();
	}
}
