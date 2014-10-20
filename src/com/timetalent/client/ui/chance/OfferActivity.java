package com.timetalent.client.ui.chance;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.timetalent.client.R;
import com.timetalent.client.service.AppController;
import com.timetalent.client.ui.BaseActivity;
import com.timetalent.common.util.StringUtil;
import com.timetalent.common.util.ToastUtil;


/******************************************
 * 类描述： 发职位
 * 类名称：OfferActivity  
 * @version: 1.0
 * @author: why
 * @time: 2014-10-14 下午7:17:40 
 ******************************************/
public class OfferActivity extends BaseActivity implements OnClickListener {
	private AppController controller;
	private Button bt_chance_offer;
	private RelativeLayout rl_chance_data1;//报名截止日期
	private TextView tv_chance_data1;
	private RelativeLayout rl_chance_data2;//工作开始日期
	private TextView tv_chance_data2;
	private RelativeLayout rl_chance_data3;//工作结束日期
	private TextView tv_chance_data3;
	
	private Button bt_chance_offer_picture;
	 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chance_offer_layout);
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
		bt_chance_offer = (Button)findViewById(R.id.bt_chance_offer);
		tv_chance_data1 = (TextView)findViewById(R.id.tv_chance_data1);
		rl_chance_data1 = (RelativeLayout)findViewById(R.id.rl_chance_data1);
		tv_chance_data2 = (TextView)findViewById(R.id.tv_chance_data2);
		rl_chance_data2 = (RelativeLayout)findViewById(R.id.rl_chance_data2);
		tv_chance_data3 = (TextView)findViewById(R.id.tv_chance_data3);
		rl_chance_data3 = (RelativeLayout)findViewById(R.id.rl_chance_data3);
		bt_chance_offer_picture = (Button)findViewById(R.id.bt_chance_offer_picture);
	}
	
	/**
	 * 方法描述：TODO
	 * 
	 * @author: why
	 * @time: 2014-10-10 下午6:36:02
	 */
	private void initView() {
		((TextView)findViewById(R.id.main_top_title)).setText("发布职位");
		bt_chance_offer.setOnClickListener(this);
		rl_chance_data1.setOnClickListener(this);
		rl_chance_data2.setOnClickListener(this);
		rl_chance_data3.setOnClickListener(this);
		bt_chance_offer_picture.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_chance_offer:
			ToastUtil.showToast(OfferActivity.this, "发布成功！", ToastUtil.LENGTH_LONG);
			controller.getCurrentActivity().finish();
			break;
		case R.id.rl_chance_data1:
			StringUtil.getData(OfferActivity.this,tv_chance_data1);
			break;
		case R.id.rl_chance_data2:
			StringUtil.getData(OfferActivity.this,tv_chance_data2);
			break;
		case R.id.rl_chance_data3:
			StringUtil.getData(OfferActivity.this,tv_chance_data3);
			break;
			
		case R.id.bt_chance_offer_picture:
			StringUtil.doGoToImg(OfferActivity.this);
			break;
		default:
			break;
		}
	}
}
