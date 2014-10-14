package com.timetalent.client.ui.chance;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.timetalent.client.R;
import com.timetalent.client.service.AppController;
import com.timetalent.client.ui.BaseActivity;


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
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_chance_offer:
			controller.getCurrentActivity().finish();
			break;
		default:
			break;
		}
	}

}
