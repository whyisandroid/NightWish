package com.timetalent.client.ui.user;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.timetalent.client.R;
import com.timetalent.client.service.AppController;
import com.timetalent.client.ui.BaseActivity;
import com.timetalent.client.ui.adapter.DynamicAdapter;
import com.timetalent.client.ui.dynamic.AddDynamicActivity;
import com.timetalent.common.util.IntentUtil;
import com.timetalent.common.util.UIUtils;


/******************************************
 * 类描述： 动态界面
 * 类名称：NearDongtaiActivity  
 * @version: 1.0
 * @author: why
 * @time: 2014-10-10 下午6:32:12 
 ******************************************/
public class MytixianActivity extends BaseActivity implements OnClickListener {
	private AppController controller;
	private TextView main_top_right;
	private ImageButton main_top_left;
	private ListView lv_dynamic;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_tixian);
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
	private void findView() {}

	/**
	 * 方法描述：TODO
	 * 
	 * @author: why
	 * @time: 2014-10-10 下午6:36:02
	 */
	private void initView() {}
	
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.main_top_left:
			finish();
			break;
		default:
			break;
		}
	}

}
