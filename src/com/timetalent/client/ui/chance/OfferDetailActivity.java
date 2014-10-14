package com.timetalent.client.ui.chance;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.timetalent.client.R;
import com.timetalent.client.service.AppController;
import com.timetalent.client.ui.BaseActivity;
import com.timetalent.client.ui.adapter.ChanceDetailAdapter;
import com.timetalent.common.util.ToastUtil;


/******************************************
 * 类描述： 职位详情
 * 类名称：OfferDetailActivity  
 * @version: 1.0
 * @author: why
 * @time: 2014-10-14 下午8:49:03 
 ******************************************/
public class OfferDetailActivity extends BaseActivity implements OnClickListener {
	private AppController controller;
	private TextView main_top_right;
	private ListView lv_chance_detail;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chance_offer_detail_layout);
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
		lv_chance_detail = (ListView)findViewById(R.id.lv_chance_detail);
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
		ChanceDetailAdapter adapter  = new  ChanceDetailAdapter(this);
		lv_chance_detail.setAdapter(adapter);
	}
	
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.main_top_right:
			ToastUtil.showToast(OfferDetailActivity.this, "分享有钱赚", ToastUtil.LENGTH_LONG);
			break;
		default:
			break;
		}
	}
}
