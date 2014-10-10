package com.timetalent.client.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.timetalent.client.R;
import com.timetalent.common.util.IntentUtil;


public class MainActivity extends BaseActivity {
	private static final int MSG_CASE_HOME=1;//home界面标识
	private static final int MSG_CASE_GUIDE=2;//引导界面标识
	
	@SuppressLint("HandlerLeak") private Handler mHandler = new Handler(){
		
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case MSG_CASE_HOME:
				//goHome();
				goGuide();
				break;
			case MSG_CASE_GUIDE:
				break;
			default:
				break;
			}
		};
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_layout);		
		initData();
		
	}
	
	/**
	 * 方法描述：首页启动暂时未开通：自改MSG_CASE_HOME--->MSG_CASE_GUIDE开通
	 * @author: ghf
	 * @time: 2014-9-20 下午5:51:56
	 */
	private void initData() {
			mHandler.sendEmptyMessageDelayed(MSG_CASE_HOME, 2000);
	}
	
	
	@Override
	protected void onDestroy() {
		mHandler.removeMessages(MSG_CASE_GUIDE);
		mHandler.removeMessages(MSG_CASE_HOME);
		super.onDestroy();
	}
	/**
	 * 方法描述：TODO
	 * @author: ghf
	 * @time: 2014-9-20 下午5:36:30
	 */
	private void goHome() {
		IntentUtil.intent(MainActivity.this, MainFragmentActivity.class, true);

	}
	/**
	 * 方法描述：TODO
	 * @author: ghf
	 * @time: 2014-9-20 下午5:36:47
	 */
	private void goGuide() {
		IntentUtil.intent(MainActivity.this, GuideActivity.class, true);
	}
}
