package com.timetalent.client.ui.chance;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.timetalent.client.R;
import com.timetalent.client.service.AppController;
import com.timetalent.client.ui.BaseActivity;
import com.timetalent.client.ui.MainFragmentActivity;
import com.timetalent.client.ui.dialog.DialogUtil;
import com.timetalent.client.ui.dialog.IOSStyleDialog;
import com.timetalent.client.ui.message.MessageChatActivity;
import com.timetalent.common.util.IntentUtil;
import com.timetalent.common.util.ToastUtil;


/******************************************
 * 类描述： 职位详情
 * 类名称：OfferInfoActivity  
 * @version: 1.0
 * @author: wanghy
 * @time: 2014-10-19 下午5:37:25 
 ******************************************/
public class OfferInfoActivity extends BaseActivity implements OnClickListener {
	private AppController controller;
	private TextView main_top_right;
	private LinearLayout ll_chance_offer_message; //发布者私聊
	private LinearLayout ll_chance_offer_apply; //我要报名
	
	
	private Handler mhandle = new Handler(){
		public void handleMessage(android.os.Message msg) {
			
		};
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chance_offer_info_layout);
		controller = AppController.getController(this);
		findView();
		initView();
	}
	/**
	 * 方法描述：TODO
	 * 
	 * @author: why
	 * @time: 2014-10-10 下午6:36:00
	 */
	private void findView() {
		main_top_right = (TextView)findViewById(R.id.main_top_right);
		ll_chance_offer_apply = (LinearLayout)findViewById(R.id.ll_chance_offer_apply);
		ll_chance_offer_message = (LinearLayout)findViewById(R.id.ll_chance_offer_message);
	}
	/**
	 * 方法描述：TODO
	 * 
	 * @author: why
	 * @time: 2014-10-10 下午6:36:02
	 */
	private void initView() {
		((TextView)findViewById(R.id.main_top_title)).setText("职位详情");
		main_top_right.setVisibility(View.VISIBLE);
		main_top_right.setText("分享");
		main_top_right.setOnClickListener(this);
		ll_chance_offer_apply.setOnClickListener(this);
		ll_chance_offer_message.setOnClickListener(this);
	}
	
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.main_top_right:
			ToastUtil.showToast(OfferInfoActivity.this, "分享有钱赚", ToastUtil.LENGTH_LONG);
			break;
		case R.id.ll_chance_offer_apply:
			showMessageTwo(OfferInfoActivity.this, "您要报名: 二人转演员?", "报名成功");
			break;
		case R.id.ll_chance_offer_message:
			IntentUtil.intent(OfferInfoActivity.this, MessageChatActivity.class);
			break;
		default:
			break;
		}
	}
	
	/**
	  * 方法描述：左右两个按钮
	  * @param activity
	  * @author: why
	  * @time: 2014-8-13 下午7:43:12
	  */
	private void showMessageTwo(final Context context,String message,final String toast) {
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
				final IOSStyleDialog dialog = new IOSStyleDialog(context, IOSStyleDialog.DIALOG_ONE);
				dialog.setMessage("您已经报名了： 二人转演员");
				dialog.setOne("确认",new OnClickListener() {
					@Override
					public void onClick(View v) {
						IntentUtil.intent(OfferInfoActivity.this, MainFragmentActivity.class);
						dialog.closeDialog();
					}
				});
			}
		});
	}
}
