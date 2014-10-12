package com.timetalent.client.ui.dynamic;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.timetalent.client.R;
import com.timetalent.client.service.AppController;
import com.timetalent.client.ui.BaseActivity;


/******************************************
 * 类描述： 添加动态
 * 类名称：AddDynamicActivity  
 * @version: 1.0
 * @author: wanghy
 * @time: 2014-10-12 下午8:25:54 
 ******************************************/
public class AddDynamicActivity extends BaseActivity implements OnClickListener {
	private AppController controller;
	private TextView main_top_right;
	private TextView main_top_left2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dynamic_add_info);
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
		main_top_left2 = (TextView)findViewById(R.id.main_top_left2);
	}

	/**
	 * 方法描述：TODO
	 * 
	 * @author: why
	 * @time: 2014-10-10 下午6:36:02
	 */
	private void initView() {
		((TextView)findViewById(R.id.main_top_title)).setText("添加动态");
		main_top_right.setVisibility(View.VISIBLE);
		main_top_right.setText("发表");
		findViewById(R.id.main_top_left).setVisibility(View.GONE);
		main_top_left2.setVisibility(View.VISIBLE);
		main_top_left2.setText("取消");
		
	}
	
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_login_next:
			break;
		default:
			break;
		}
	}
}
