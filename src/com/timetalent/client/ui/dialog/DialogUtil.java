package com.timetalent.client.ui.dialog;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.timetalent.client.TimeTalentApplication;
import com.timetalent.client.service.AppController;
import com.timetalent.client.ui.pay.MychongzhiActivity;
import com.timetalent.common.util.IntentUtil;
import com.timetalent.common.util.ProgressDialogUtil;
import com.timetalent.common.util.ToastUtil;


/**
 * **************************************** 
 * 类描述： 对话框统一管理 类名称：DialogUtil
 * @version: 1.0
 * @author: GHZ
 * @time: 2014-5-8 下午5:43:09
 ***************************************** 
 */
public class DialogUtil {
	
	/**
	  * 方法描述：退出账号
	  * @param activity
	  * @author: why
	  * @time: 2014-8-13 下午7:43:12
	  */
	public static void showExit(Activity activity,final Button submit) {
		final IOSStyleDialog dialog = new IOSStyleDialog(activity, IOSStyleDialog.DIALOG_TWO);
		dialog.setMessage("确定退出账号？");
		dialog.setLeft("确定", new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dialog.closeDialog();
				// 对话框
				TimeTalentApplication.getInstance().setLogin(false);
				submit.setText("登录");
			}
		});
		dialog.setRight("取消", new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dialog.closeDialog();
			}
		});
	}

	
	/**
	  * 方法描述：单个按钮
	  * @author: wanghy
	  * @time: 2014-10-12 下午5:22:19
	  */
	public static void showMessage(Context context,
			String message) {
		final IOSStyleDialog dialog = new IOSStyleDialog(context, IOSStyleDialog.DIALOG_ONE);
		dialog.setMessage(message);
		dialog.setOne("确认",new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.closeDialog();
			}
		});
	}
	
	/**
	  * 方法描述：左右两个按钮
	  * @param activity
	  * @author: why
	  * @time: 2014-8-13 下午7:43:12
	  */
	public static void showMessageTwo(final Context context,String message,final String toast) {
		final IOSStyleDialog dialog = new IOSStyleDialog(context, IOSStyleDialog.DIALOG_TWO);
		dialog.setMessage(message);
		dialog.setLeft("取消", new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dialog.closeDialog();
			}
		});
		dialog.setRight("确定", new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dialog.closeDialog();
				// 对话框
				ToastUtil.showToast(context, toast, ToastUtil.LENGTH_LONG);
			}
		});
	}


	
	/**
	  * 方法描述：TODO
	  * @param currentActivity
	  * @author: why
	  * @time: 2014-12-5 下午4:49:49
	  */
	public static void messageAccess(final Activity context) {

		final IOSStyleDialog dialog = new IOSStyleDialog(context, IOSStyleDialog.DIALOG_TWO);
		dialog.setMessage("您不在对方的好友列表,对话需要支付星币!");
		dialog.setLeft("取消", new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dialog.closeDialog();
			}
		});
		dialog.setRight("确定", new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.closeDialog();
				ProgressDialogUtil.showProgressDialog(context, "支付中…", false);
				new Thread(new Runnable() {
					@Override
					public void run() {
						AppController.getController().chatPay();
						ProgressDialogUtil.closeProgressDialog();
					}
				}).start();
			}
		});
	}
	
	/**
	  * 方法描述：TODO
	  * @param context
	  * @author: why
	  * @time: 2014-12-5 下午4:57:46
	  */
	public  static void messagePayN(final Activity context) {
		final IOSStyleDialog dialog = new IOSStyleDialog(context, IOSStyleDialog.DIALOG_TWO);
		dialog.setMessage("您的星币余额不足是否充值!");
		dialog.setLeft("取消", new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dialog.closeDialog();
			}
		});
		
		dialog.setRight("确定", new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dialog.closeDialog();
				IntentUtil.intent(context, MychongzhiActivity.class);
				// 充值
			}
		});
	}
}
