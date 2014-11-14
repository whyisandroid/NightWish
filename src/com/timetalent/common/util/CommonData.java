package com.timetalent.common.util;



import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.DisplayMetrics;

/** 
 * 公共数据类.
 * @author ly
 * @version v1.0 2013-5-1 09:30
 */
public class CommonData {

	/** 应用程序版本号CODE. */
	private static int versionCode;
	public static int getVersionCode(Context context) {
		if(versionCode == 0){
			PackageInfo info = getPackageInfo(context);
			CommonData.versionCode = info.versionCode;
			CommonData.versionName = info.versionName;
		}
		return versionCode;
	}

	/** 应用程序版本号NAME. */
	private static String versionName;
	public static String getVersionName(Context context) {
		if(versionName == null){
			PackageInfo info = getPackageInfo(context);
			CommonData.versionCode = info.versionCode;
			CommonData.versionName = info.versionName;
		}
		return versionName;
	}
	
	/**
	 * 获取App安装包信息
	 * @return
	 */
	public static PackageInfo getPackageInfo(Context context) {
		PackageInfo info = null;
		try { 
			info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
		} catch (NameNotFoundException e) {    
			e.printStackTrace();
		} 
		if(info == null) info = new PackageInfo();
		return info;
	}
	
    /** 屏幕宽度. */
	private static int screenWidth;
    public static int getScreenWidth(Activity a) {
    	if(screenWidth == 0){
    		initDeviceInfo(a);
    	}
		return screenWidth;
	}

	/** 屏幕高度. */
	private static int screenHeight;
	public static int getScreenHeight(Activity a) {
    	if(screenHeight == 0){
    		initDeviceInfo(a);
    	}
		return screenHeight;
	}
    
    /** 屏幕密度. */
	private static float density;
	public static float getDensity(Activity a) {
    	if(density == 0.0f){
    		initDeviceInfo(a);
    	}
		return density;
	}
    
    /** 去掉标题栏高度 程序可用高度. */
//	private static int canUseHeight;
//	public static int getCanUseHeight(Activity a) {
//    	if(screenHeight == 0){
//    		initDeviceInfo(a);
//    	}
//		return canUseHeight;
//	}
	
	/**
	 * 主进程初始化信息
	 */
	public static void initDeviceInfo(Activity a) {
		// 初始化屏幕宽、高度信息
		DisplayMetrics dm = new DisplayMetrics();
		a.getWindowManager().getDefaultDisplay().getMetrics(dm);
		// 存储设备宽度px
		CommonData.screenWidth = dm.widthPixels;
		// 存储设备高度px
		CommonData.screenHeight = dm.heightPixels;
		// 存储设备密度（a 160dpi screen this density value will be 1; on a 120 dpi
		// screen it would be .75; etc.）
		CommonData.density = dm.density;
		//Log
		LogUtil.Log("宽度："+dm.widthPixels+"高度："+dm.heightPixels+"密度："+dm.density);
	}

}
