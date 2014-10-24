package com.timetalent.common.util;

import java.io.File;

import android.os.Environment;

/**
 * **************************************** 类描述： 配置信息类 类名称：Config
 * 
 * @version: 1.0
 * @author: why
 * @time: 2014-2-14 下午3:32:29
 ***************************************** 
 */
public class Config {

	/** 是否调试. */
	public final static boolean DEBUG = true;
	
	// 后台 测试
	public final static String MY_SERVICE = "http://124.193.223.166/xingtan/index.php";
	/** 临时文件保存路径. */
	// 下载包保存路径
	public static final String PATH_SDCARD = Environment.getExternalStorageDirectory()
			.getAbsolutePath() + File.separator + "TimeTalent";

	
	public final static String HTTP_USER_LOGIN = MY_SERVICE+ "/Login/login"; // 登录接口
	public final static String HTTP_USER_REGISTER = MY_SERVICE+ "/Reg/index"; // 注册接口

	
}
