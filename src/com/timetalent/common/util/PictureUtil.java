package com.timetalent.common.util;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;


/******************************************
 * 类描述： 图片处理 公共类
 * 类名称：PictureUtil  
 * @version: 1.0
 * @author: why
 * @time: 2014-10-27 下午4:34:35 
 ******************************************/
public class PictureUtil {
	
	/**
	  * 方法描述：TODO
	  * @author: why
	  * @time: 2014-10-27 下午4:35:32
	  */
	public static void headPic(Activity activity,int crop,File sdcardTempFile) {
		Intent intent = new Intent("android.intent.action.PICK");
		intent.setDataAndType(MediaStore.Images.Media.INTERNAL_CONTENT_URI, "image/*");
		intent.putExtra("output", Uri.fromFile(sdcardTempFile));
		intent.putExtra("crop", "true");
		intent.putExtra("aspectX", 1);// 裁剪框比例
		intent.putExtra("aspectY", 1);
		intent.putExtra("outputX", crop);// 输出图片大小
		intent.putExtra("outputY", crop);
		activity.startActivityForResult(intent, 0);
	}
	
	/**
	  * 方法描述：获取本地相册
	  * @author: wanghy
	  * @time: 2014-10-19 下午4:11:46
	  */
	public static void doGoToImg(Activity mContext,File sdcardTempFile) {
		Intent intent = new Intent("android.intent.action.PICK");
		intent.setDataAndType(MediaStore.Images.Media.INTERNAL_CONTENT_URI, "image/*");
		intent.putExtra("output", Uri.fromFile(sdcardTempFile));
		intent.putExtra("crop", "true");
		intent.putExtra("aspectX", 600);// 裁剪框比例
		intent.putExtra("aspectY", 350);
		intent.putExtra("outputX", 600);// 输出图片大小
		intent.putExtra("outputY", 350);
		mContext.startActivityForResult(intent, 0);
	}
}
