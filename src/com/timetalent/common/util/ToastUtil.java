package com.timetalent.common.util;

import android.content.Context;
import android.widget.Toast;
/******************************************
	 * 类描述： 控制toast
	 * 类名称：ToastUtil  
 	 * @version: 1.0
	 * @author: why
	 * @time: 2014-8-14 下午5:17:59 
*****************************************/

public class ToastUtil {
	public static int LENGTH_LONG = 1500;
	public static int LENGTH_SHORT = Toast.LENGTH_SHORT;
	
	private static Toast mToast = null;

	public static void showToast(Context context, String msg, int duration) {
		if (mToast == null) {
			mToast = Toast.makeText(context, msg, duration);
		} else {
			mToast.setText(msg);
		}
		mToast.show();
	}
	
	public static void showToast(Context context, int msgId, int duration) {
		if (mToast == null) {
			mToast = Toast.makeText(context, msgId, duration);
		} else {
			mToast.setText(msgId);
		}
		mToast.show();
	}
	
	public static void cancelToast() {
		if (mToast != null) {
			mToast.cancel();
		}
	}
}
