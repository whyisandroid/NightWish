package com.timetalent.client;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * ****************************************
	 * 类描述： 持久化数据层
	 * 类名称：AppSharedPref  
 	 * @version: 1.0
	 * @author: why
	 * @time: 2014-2-18 下午6:04:05 
*****************************************
 */
public class AppSharedPref {
	
	/** SharePreferences名字 */
	private  String SHARE_PREFERENCES_NAME = "CreditPerson";
	
	/** The shared preferences. */
	private static SharedPreferences sharedPreferences = null;
	private static AppSharedPref asp = null;
	
	/**
	 * 构造函数.
	 */
	private AppSharedPref(Context context) {
		if (sharedPreferences == null) {
			sharedPreferences = context.getSharedPreferences(
					SHARE_PREFERENCES_NAME, Context.MODE_PRIVATE);
		}
	}

	/**
	 * 单态实例
	 */
	public static AppSharedPref getInstance(Context context) {
		if (asp == null) {
			asp = new AppSharedPref(context);
		}
		return asp;
	}
	
	
	/**
	  * 方法描述：TODO
	  * @param flag
	  * @author: why
	  * @time: 2014-7-1 下午3:18:33
	  */
	public void setTokenFlag(boolean flag) {

		Editor e = sharedPreferences.edit();
		e.putBoolean("tokenFlag",flag);
		e.commit();
	
	}

	/**
	  * 方法描述：TODO
	  * @return
	  * @author: why
	  * @time: 2014-7-1 下午3:18:44
	  */
	public boolean getTokenFlag() {
		return sharedPreferences.getBoolean("tokenFlag", false);
	}

	
	/**
	  * 方法描述：TODO
	  * @param token
	  * @author: why
	  * @time: 2014-7-1 下午4:14:21
	  */
	public void saveToken(String token) {
		Editor e = sharedPreferences.edit();
		e.putString("token",token);
		e.commit();
	}

	
	/**
	  * 方法描述：TODO
	  * @return
	  * @author: why
	  * @time: 2014-7-1 下午4:14:29
	  */
	public String getToken() {
		return sharedPreferences.getString("token", "");
	}
}
