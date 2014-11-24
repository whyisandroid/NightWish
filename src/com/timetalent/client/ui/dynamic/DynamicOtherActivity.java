package com.timetalent.client.ui.dynamic;


import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.timetalent.client.R;
import com.timetalent.client.entities.FeedData;
import com.timetalent.client.service.AppController;
import com.timetalent.client.ui.BaseActivity;
import com.timetalent.client.ui.adapter.DynamicAdapter;
import com.timetalent.common.util.ProgressDialogUtil;


/******************************************
 * 类描述： 其他人的动态
 * 类名称：DynamicMyActivity  
 * @version: 1.0
 * @author: why
 * @time: 2014-10-10 下午6:31:03 
 ******************************************/
public class DynamicOtherActivity extends BaseActivity implements OnClickListener {
	private AppController controller;
	private ListView lv_dynamic;
	
	private Handler mHandler = new  Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0:
				FeedData data = (FeedData)controller.getContext().getBusinessData("Dynamic_OtherData");
				if(data != null){
					DynamicAdapter adapter = new DynamicAdapter(DynamicOtherActivity.this,data.getLists());
					lv_dynamic.setAdapter(adapter);
				}
				break;

			default:
				break;
			}
		};
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		controller = AppController.getController(this);
		setContentView(R.layout.dynamic_fragment);
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
		lv_dynamic = (ListView)findViewById(R.id.lv_dynamic);
	}

	/**
	 * 方法描述：TODO
	 * 
	 * @author: why
	 * @time: 2014-10-10 下午6:36:02
	 */
	private void initView() {
		String name = getIntent().getExtras().getString("userName");
		if(TextUtils.isEmpty(name)){
			((TextView)findViewById(R.id.main_top_title)).setText("动态");
		}else{
			((TextView)findViewById(R.id.main_top_title)).setText(name+"的动态");
		}
	}
	
	
	@Override
	public void onResume() {
		super.onResume();
		dynamic();
	}
	
	/**
	  * 方法描述：TODO
	  * @author: wanghy
	  * @time: 2014-11-5 下午9:55:14
	  */
	private void dynamic() {
			ProgressDialogUtil.showProgressDialog(this, "稍等…", false);
			new Thread(new Runnable() {
				@Override
				public void run() {
					controller.dynamicWho(mHandler);
					ProgressDialogUtil.closeProgressDialog();
				}
			}).start();
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		default:
			break;
		}
	}
}
