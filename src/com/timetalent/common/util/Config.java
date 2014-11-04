package com.timetalent.common.util;

import java.io.File;

import android.os.Environment;

/**
 * **************************************** 
 * 类描述： 配置信息类 类名称：Config
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
	// 项目路径
	public static final String PATH_SDCARD = Environment.getExternalStorageDirectory()
			.getAbsolutePath() + File.separator + "TimeTalent";

	//图片保存路径
	public static final String PIC_PATH = "/mnt/sdcard/TimeTalent/picture/";
	
	//
	public final static String HTTP_USER_LOGIN = MY_SERVICE+ "/Login/login"; // 登录接口
	public final static String HTTP_USER_REGISTER = MY_SERVICE+ "/Reg/index"; // 注册接口
	
	public final static String HTTP_USER_REGISTER_AVATAR = MY_SERVICE+ "/Reg/avatar_upload"; // 注册接口 上传图片
	// 机会
	public final static String HTTP_USER_CHANCE_ADD = MY_SERVICE+ "/Task/add"; // 添加机会
	public final static String HTTP_USER_CHANCE_LIST = MY_SERVICE+ "/Task/lists"; //机会列表
	public final static String HTTP_USER_CHANCE_DETAIL = MY_SERVICE+ "/Task/show"; // 机会详情
	public final static String HTTP_USER_CHANCE_APPLY = MY_SERVICE+ "/Task/apply"; // 机会报名

	// 动态
	public final static String HTTP_USER_DYNAMIC_INDEX = MY_SERVICE+ "/Feed/index"; // 获取所有 用户的最新动态
	public final static String HTTP_USER_DYNAMIC_WHO = MY_SERVICE+ "/Feed/whofeed"; // 获取某个用户的动态列表
	public final static String HTTP_USER_DYNAMIC_MY = MY_SERVICE+ "/Feed/myfeed"; // 获取当前用户自己的动态列表
	public final static String HTTP_USER_DYNAMIC_ADD = MY_SERVICE+ "/Feed/addfeed"; //发布动态Feed/addfeed
	public final static String HTTP_USER_DYNAMIC_REPALY = MY_SERVICE+ "/Feed/add_reply"; // 评论某动态Feed/add_reply
	public final static String HTTP_USER_DYNAMIC_FAVOUR = MY_SERVICE+ "/Feed/favour"; // 点赞Feed/favour
}
