package com.timetalent.client.ui.chance;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.timetalent.client.R;
import com.timetalent.client.service.AppController;
import com.timetalent.client.ui.BaseActivity;
import com.timetalent.client.ui.adapter.OfferAddAdapter;
import com.timetalent.common.util.StringUtil;
import com.timetalent.common.util.ToastUtil;
import com.timetalent.common.util.UIUtils;


/******************************************
 * 类描述： 发职位
 * 类名称：OfferActivity  
 * @version: 1.0
 * @author: why
 * @time: 2014-10-14 下午7:17:40 
 ******************************************/
public class OfferAddActivity extends BaseActivity implements OnClickListener {
	private AppController controller;
	private Button bt_chance_offer;
	private RelativeLayout rl_chance_data1;//报名截止日期
	private TextView tv_chance_data1;
	private Button bt_chance_offer_picture;
	
	private ListView lv_chance_add;
	private ImageView iv_chance_offer_add;
	
	private static int num = 1;
	 private OfferAddAdapter adapter ;
	
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
	
		bt_chance_offer_picture = (Button)findViewById(R.id.bt_chance_offer_picture);
		
		lv_chance_add = (ListView)findViewById(R.id.lv_chance_add);
		iv_chance_offer_add = (ImageView)findViewById(R.id.iv_chance_offer_add);
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
		
		bt_chance_offer_picture.setOnClickListener(this);
		iv_chance_offer_add.setOnClickListener(this);
		
		adapter = new  OfferAddAdapter(this, num);
		lv_chance_add.setAdapter(adapter);
		UIUtils.setListViewHeight(lv_chance_add, adapter);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_chance_offer:
			ToastUtil.showToast(OfferAddActivity.this, "发布成功！", ToastUtil.LENGTH_LONG);
			controller.getCurrentActivity().finish();
			break;
		case R.id.rl_chance_data1:
			StringUtil.getData(OfferAddActivity.this,tv_chance_data1);
			break;
		case R.id.bt_chance_offer_picture:
			StringUtil.doGoToImg(OfferAddActivity.this);
			break;
		case R.id.iv_chance_offer_add:
			num++;
			adapter = new  OfferAddAdapter(this, num);
			lv_chance_add.setAdapter(adapter);
			UIUtils.setListViewHeight(lv_chance_add, adapter);
		default:
			break;
		}
	}
}
