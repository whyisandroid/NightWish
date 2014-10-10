package com.timetalent.client;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ServiceInfo;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import com.timetalent.common.net.AppSocketInterface;
import com.timetalent.common.net.XUtilsSocketImpl;
import com.timetalent.common.util.LogUtil;

/******************************************
 * 类描述： 程序入口类 类名称：TimeTalentApplication
 * 
 * @version: 1.0
 * @author: why
 * @time: 2014-10-13 下午2:09:22
 ******************************************/
public class TimeTalentApplication extends Application {
	private String TAG = "TimeTalentApplication";

	/** 实例化 **/
	private static TimeTalentApplication instance;
	/** 网络链接 **/
	private static AppSocketInterface appSocket;

	public int curVersionCode; // 版本号
	public String curVersionName; // 版本名字

	public String deviceID; // 设备ID

	private boolean login;// 登录情况

	@Override
	public void onCreate() {
		super.onCreate();
		init();
	}

	/**
	 * 方法描述：初始化
	 * 
	 * @author: why
	 * @time: 2014-2-14 下午3:46:04
	 */
	private void init() {
		instance = this;
		appSocket = new XUtilsSocketImpl();
		getCurrentVersion();
		//getDeviceID();
	}

	/**
	 * 方法描述：获取设备唯一标示
	 * 
	 * @author: why
	 * @time: 2014-2-21 下午8:53:03
	 */
	private void getDeviceID() {
		TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		String DEVICE_ID = tm.getDeviceId();
		if (!TextUtils.isEmpty(DEVICE_ID)) {
			deviceID = DEVICE_ID;
		} else {
			//deviceID = TCAgent.getDeviceId(this);
		}
	}

	/**
	 * @return login : return the property login.
	 */
	public boolean isLogin() {
		return login;
	}

	/**
	 * @param login
	 *            : set the property login.
	 */
	public void setLogin(boolean login) {
		this.login = login;
		if (!login) {
			//AppController.getController().getContext().clearBusinessData();
		}
	}

	/**
	 * 方法描述: 获取网络通信实例
	 * 
	 * @return
	 * @author: why
	 * @time: 2013-10-21 下午3:32:02
	 */
	public static AppSocketInterface getAppSocket() {
		return appSocket;
	}

	/**
	 * 方法描述：获取实例
	 * 
	 * @return
	 * @author: why
	 * @time: 2013-10-21 下午2:52:44
	 */
	public static TimeTalentApplication getInstance() {
		return instance;
	}

	/**
	 * 获取当前客户端版本信息
	 */
	private void getCurrentVersion() {
		try {
			PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), 0);
			curVersionName = info.versionName;
			curVersionCode = info.versionCode;
		} catch (NameNotFoundException e) {
			e.printStackTrace(System.err);
		}
	}
	
	/**
	 * 方法描述：存储token信息
	 * @author: why
	 * @time: 2014-7-1 下午3:16:35
	 */
	public void saveToken(String token) {
		AppSharedPref.getInstance(this).saveToken(token);
	}
	
	/**
	 * 方法描述：TODO
	 * @author: why
	 * @time: 2014-7-1 下午3:17:47
	 */
	public String getToken() {
		return AppSharedPref.getInstance(this).getToken();
	}
	
	
	/**
	 * 方法描述：存储token信息
	 * @author: why
	 * @time: 2014-7-1 下午3:16:35
	 */
	public void saveTokenFlag(boolean flag) {
		AppSharedPref.getInstance(this).setTokenFlag(flag);
	}


	
	
	
	
	/**
	 * 方法描述：TODO
	 * @author: why
	 * @time: 2014-7-1 下午3:17:47
	 */
	public boolean isTokenFlag() {
		return AppSharedPref.getInstance(this).getTokenFlag();
	}
}
