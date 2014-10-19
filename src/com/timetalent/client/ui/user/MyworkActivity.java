package com.timetalent.client.ui.user;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.timetalent.client.R;
import com.timetalent.client.service.AppController;
import com.timetalent.client.ui.BaseActivity;
import com.timetalent.client.ui.adapter.ChanceDetailAdapter;
import com.timetalent.client.ui.adapter.DynamicAdapter;
import com.timetalent.client.ui.adapter.WorkAdapter;
import com.timetalent.client.ui.adapter.YaoqingAdapter;
import com.timetalent.client.ui.chance.OfferDetailActivity;
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
public class MyworkActivity extends BaseActivity implements OnClickListener {
	private AppController controller;
	private TextView main_top_right;
	private ImageButton main_top_left;
	RadioButton rb1;
	RadioButton rb2;
	private ListView lwork;
	private ListView lyaoqing;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_work);
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
		main_top_left = (ImageButton)this.findViewById(R.id.main_top_left);
		lwork = (ListView) findViewById(R.id.lwork);
		lyaoqing = (ListView) findViewById(R.id.lyaoqing);
		rb1 = (RadioButton) findViewById(R.id.radioButton1);
		rb2 = (RadioButton) findViewById(R.id.radioButton2);
	}

	/**
	 * 方法描述：TODO
	 * 
	 * @author: why
	 * @time: 2014-10-10 下午6:36:02
	 */
	private void initView() {
		((TextView)this.findViewById(R.id.main_top_title)).setText("工作管理");
//		UIUtils.setDrawableLeft(this,main_top_right,R.drawable.d3_06);
		main_top_left.setVisibility(View.VISIBLE);
//		UIUtils.setDrawableLeft(this,main_top_left2,R.drawable.d3_03);
		main_top_left.setOnClickListener(this);
		lwork.setAdapter(new WorkAdapter(MyworkActivity.this));
		lwork.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				IntentUtil.intent(MyworkActivity.this, OfferDetailActivity.class);
				
			}
		});
		lyaoqing.setAdapter(new YaoqingAdapter(MyworkActivity.this));
		rb1.setOnClickListener(this);
		rb2.setOnClickListener(this);
		lyaoqing.setVisibility(lyaoqing.GONE);
	}
	
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.main_top_left:
			finish();
			break;
		case R.id.radioButton1:
			lwork.setVisibility(lwork.GONE);
			lyaoqing.setVisibility(lyaoqing.VISIBLE);
			break;
		case R.id.radioButton2:
			lwork.setVisibility(lwork.VISIBLE);
			lyaoqing.setVisibility(lyaoqing.GONE);
			break;
		default:
			break;
		}
	}

}
