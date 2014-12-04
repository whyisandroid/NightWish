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
import com.timetalent.client.ui.adapter.FansYaoqingAdapter;
import com.timetalent.client.ui.adapter.WorkAdapter;
import com.timetalent.client.ui.adapter.XingtanWorkAdapter;
import com.timetalent.client.ui.adapter.XingtanYaoqingAdapter;
import com.timetalent.client.ui.adapter.YaoqingAdapter;
import com.timetalent.client.ui.adapter.YirenWorkAdapter;
import com.timetalent.client.ui.adapter.YirenYaoqingAdapter;
import com.timetalent.client.ui.adapter.ZDchongzhiAdapter;
import com.timetalent.client.ui.adapter.ZDtixianAdapter;
import com.timetalent.client.ui.adapter.ZDworkAdapter;
import com.timetalent.client.ui.chance.OfferDetailActivity;
import com.timetalent.client.ui.dialog.IOSStyleDialog;
import com.timetalent.client.ui.dialog.IOSStyleListDialog;
import com.timetalent.client.ui.dialog.WorkIOSStyleListDialog;
import com.timetalent.client.ui.dynamic.DynamicAddActivity;
import com.timetalent.common.util.IntentUtil;
import com.timetalent.common.util.UIUtils;


/******************************************
 * 类描述： 动态界面
 * 类名称：NearDongtaiActivity  
 * @version: 1.0
 * @author: why
 * @time: 2014-10-10 下午6:32:12 
 ******************************************/
public class MyzhangdanActivity extends BaseActivity implements OnClickListener {
	private AppController controller;
	private TextView main_top_right;
	private ImageButton main_top_left;
	RadioButton rb1;
	RadioButton rb2;
	RadioButton rb3;
	private ListView lvchongzhi;
	private ListView lvtixian;
	private ListView lvwork;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		controller = AppController.getController(this);
			setContentView(R.layout.my_zhangdan);
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
		rb1 = (RadioButton) findViewById(R.id.radioButton1);
		rb2 = (RadioButton) findViewById(R.id.radioButton2);
		rb3 = (RadioButton) findViewById(R.id.radioButton3);
		lvchongzhi = (ListView) findViewById(R.id.lvchongzhi);
		lvtixian = (ListView) findViewById(R.id.lvtixian);
		lvwork = (ListView) findViewById(R.id.lvwork);
		
	}


	/**
	 * 方法描述：TODO
	 * 
	 * @author: why
	 * @time: 2014-10-10 下午6:36:02
	 */
	private void initView() {
		((TextView)this.findViewById(R.id.main_top_title)).setText("账单查询");
//		UIUtils.setDrawableLeft(this,main_top_right,R.drawable.d3_06);
		main_top_left.setVisibility(View.VISIBLE);
//		UIUtils.setDrawableLeft(this,main_top_left2,R.drawable.d3_03);
		main_top_left.setOnClickListener(this);
		
		rb1.setOnClickListener(this);
		rb2.setOnClickListener(this);
		rb3.setOnClickListener(this);
		lvchongzhi.setAdapter(new ZDchongzhiAdapter(MyzhangdanActivity.this));
		lvtixian.setAdapter(new ZDtixianAdapter(MyzhangdanActivity.this));
		lvwork.setAdapter(new ZDworkAdapter(MyzhangdanActivity.this));
		lvchongzhi.setVisibility(View.VISIBLE);
		lvtixian.setVisibility(View.GONE);
		lvwork.setVisibility(View.GONE);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.main_top_left:
			finish();
			break;
		case R.id.radioButton1:
			lvchongzhi.setVisibility(View.VISIBLE);
			lvtixian.setVisibility(View.GONE);
			lvwork.setVisibility(View.GONE);
			break;
		case R.id.radioButton2:
			lvchongzhi.setVisibility(View.GONE);
			lvtixian.setVisibility(View.VISIBLE);
			lvwork.setVisibility(View.GONE);
			break;
		case R.id.radioButton3:
			lvchongzhi.setVisibility(View.GONE);
			lvtixian.setVisibility(View.GONE);
			lvwork.setVisibility(View.VISIBLE);
			break;
		default:
			break;
		}
	}

}
