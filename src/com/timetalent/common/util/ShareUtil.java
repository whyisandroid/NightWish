package com.timetalent.common.util;

import android.annotation.TargetApi;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.Platform.ShareParams;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.onekeyshare.ShareContentCustomizeCallback;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;

import com.timetalent.client.R;
import com.timetalent.client.entities.ShareMessage;
import com.timetalent.client.service.AppController;
import com.timetalent.common.net.NetWorkHelper;

/******************************************
 * 类描述： shareSDK 类名称：ShareUtil
 * 
 * @version: 1.0
 * @author: why
 * @time: 2014-8-21 下午3:17:30
 *****************************************/
public class ShareUtil {
	
	public static final String text = "今年杨坤20岁演唱会报名开始";
	public static final String  imageURL = "http://h.hiphotos.baidu.com/image/h%3D800%3Bcrop%3D0%2C0%2C1280%2C800/sign=9609c0c75e6034a836e2b581fb282a26/91ef76c6a7efce1b923a810cad51f3deb58f65e6.jpg";
	public static final String downURL = "http://www.baidu.com/";
	
	public static void showShare(Context context,ShareMessage share) {
		if (!NetWorkHelper.isNetworkAvailable(AppController.getController().getCurrentActivity())) {
			ToastUtil.showToast(context, "网络无法连接",ToastUtil.LENGTH_LONG);
			return;
		}
		ShareSDK.initSDK(context);
		OnekeyShare oks = new OnekeyShare();  
		// 关闭sso授权
		// oks.disableSSOWhenAuthorize();
		// 添加自定义分享
		//addURLLogo(oks, context);
		addMessageLogo(oks, context,share.getText());
		oks.setShareContentCustomizeCallback(new ShareContent(context,share));
		// 启动分享GUI
		oks.show(context);
	}

	private static void addURLLogo(final OnekeyShare oks, final Context context) {
		// 参考代码配置章节，设置分享参数
		// 构造一个图标
		Bitmap logo = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.logo_wechat);
		// 定义图标的标签
		String label = "复制下载链接";
		// 图标点击后会通过Toast提示消息
		OnClickListener listener = new OnClickListener() {
			public void onClick(View v) {
				copyText(downURL, context);
				Toast.makeText(context, "下载链接已复制", Toast.LENGTH_SHORT).show();
				oks.finish();
			}
		};
		oks.setCustomerLogo(logo, label, listener);
	}

	private static void addMessageLogo(final OnekeyShare oks,
			final Context context,final String text) {
		// 参考代码配置章节，设置分享参数
		// 构造一个图标
		Bitmap logo = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.logo_shortmessage);
		// 定义图标的标签
		String label = "动态";
		// 图标点击后会通过Toast提示消息
		OnClickListener listener = new OnClickListener() {
			public void onClick(View v) {
				
			}
		};
		oks.setCustomerLogo(logo, label, listener);
	}

	/**
	 * 方法描述：复制
	 * 
	 * @param text
	 * @param context
	 * @author: why
	 * @time: 2014-8-22 下午5:42:57
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private static void copyText(String text, Context context) {
		ClipboardManager cmb = (ClipboardManager) context
				.getSystemService(Context.CLIPBOARD_SERVICE);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			ClipData clipData = ClipData.newPlainText(text, text);
			cmb.setPrimaryClip(clipData);
			;
		} else {
			cmb.setText(text);
		}
	}

}

/**
 * **************************************** 类描述： 差异化分享 类名称：ShareWebchat
 * 
 * @version: 1.0
 * @author: why
 * @time: 2014-8-22 下午4:47:50
 ***************************************** 
 */
class ShareContent implements ShareContentCustomizeCallback {
	private Context context;
	private ShareMessage share; //分享内容

	/**
	 * 类的构造方法 创建一个新的实例 ShareWebchat.
	 * 
	 * @param
	 * @param context
	 */
	public ShareContent(Context context) {
		this.context = context;
	}
	
	public ShareContent(Context context,ShareMessage share) {
		this.context = context;
		this.share = share;
	}

	@Override
	public void onShare(Platform platform, ShareParams paramsToShare) {
		String title = share.getTitle();
		String text = share.getText();
		String imageURL = share.getImageURL();
		String webURL = share.getDownURL();
		
		if (Wechat.NAME.equals(platform.getName())) { // 微信朋友
			paramsToShare.setShareType(Platform.SHARE_WEBPAGE);
			paramsToShare.setTitle(title);
			paramsToShare.setText(text);
			Bitmap bt= BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher);
			paramsToShare.setImageData(bt);
			paramsToShare.setUrl(webURL);
			paramsToShare.setImagePath("");
			paramsToShare.setImageUrl("");
		} else if (SinaWeibo.NAME.equals(platform.getName())) { // 新浪微博
			paramsToShare.setText(text);
			paramsToShare.setImageUrl(imageURL);
		} else if (WechatMoments.NAME.equals(platform.getName())) { // 微信朋友圈
			paramsToShare.setShareType(Platform.SHARE_WEBPAGE);
			paramsToShare.setTitle(title);
			paramsToShare.setText(text);
			Bitmap bt= BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher);
			paramsToShare.setImageData(bt);
			paramsToShare.setUrl(webURL);
			paramsToShare.setImagePath("");
			paramsToShare.setImageUrl("");
		} else if ("TencentWeibo".equals(platform.getName())) { // 腾讯微博
			paramsToShare.setText(text);
			paramsToShare.setImageUrl(imageURL);
		}
	}
}
