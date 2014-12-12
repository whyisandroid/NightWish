package com.timetalent.client.ui.dynamic;


import android.app.ActionBar.LayoutParams;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.timetalent.client.R;
import com.timetalent.client.entities.FeedData;
import com.timetalent.client.service.AppController;
import com.timetalent.client.ui.BaseActivity;
import com.timetalent.client.ui.adapter.DynamicAdapter;
import com.timetalent.common.util.IntentUtil;
import com.timetalent.common.util.ProgressDialogUtil;
import com.timetalent.common.util.UIUtils;


/******************************************
 * 类描述： 我的动态
 * 类名称：DynamicMyActivity  
 * @version: 1.0
 * @author: why
 * @time: 2014-10-10 下午6:31:03 
 ******************************************/
public class DynamicMyActivity extends BaseActivity implements OnClickListener {
	private AppController controller;
	private TextView main_top_right;
	private ImageButton main_top_left;
	private ListView lv_dynamic;
	
	private Handler mHandler = new  Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0:
				FeedData data = (FeedData)controller.getContext().getBusinessData("Dynamic_MyData");
				if(data != null){
					DynamicAdapter adapter = new DynamicAdapter(DynamicMyActivity.this,data.getLists(),mHandler);
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
		main_top_right = (TextView)findViewById(R.id.main_top_right);
		main_top_left = (ImageButton)findViewById(R.id.main_top_left1);
	}

	/**
	 * 方法描述：TODO
	 * 
	 * @author: why
	 * @time: 2014-10-10 下午6:36:02
	 */
	private void initView() {
		findViewById(R.id.main_top_left).setVisibility(View.GONE);
		main_top_left.setVisibility(View.VISIBLE);
		((TextView)findViewById(R.id.main_top_title)).setText("我的动态");
		UIUtils.setDrawableLeft(DynamicMyActivity.this,main_top_right,R.drawable.d3_06);
		main_top_right.setOnClickListener(this);
		main_top_left.setImageResource(R.drawable.btn_gobackl);
		main_top_left.setOnClickListener(this);
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
					controller.dynamicMy(mHandler);
					ProgressDialogUtil.closeProgressDialog();
				}
			}).start();
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.main_top_right:
			IntentUtil.intent(DynamicMyActivity.this, DynamicAddActivity.class);
			break;
		case R.id.main_top_left1:
			this.finish();
			break;
		default:
			break;
		}
	}
}
