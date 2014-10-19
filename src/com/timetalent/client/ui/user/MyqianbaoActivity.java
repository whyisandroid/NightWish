package com.timetalent.client.ui.user;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.timetalent.client.R;
import com.timetalent.client.service.AppController;
import com.timetalent.client.ui.BaseActivity;
import com.timetalent.client.ui.adapter.DynamicAdapter;
import com.timetalent.client.ui.dynamic.AddDynamicActivity;
import com.timetalent.client.ui.near.SearchActivity;
import com.timetalent.client.ui.near.YirenActivity;
import com.timetalent.common.util.IntentUtil;
import com.timetalent.common.util.UIUtils;


/******************************************
 * 类描述： 动态界面
 * 类名称：NearDongtaiActivity  
 * @version: 1.0
 * @author: why
 * @time: 2014-10-10 下午6:32:12 
 ******************************************/
public class MyqianbaoActivity extends BaseActivity implements OnClickListener {
	private AppController controller;
	private TextView main_top_right;
	private ImageButton main_top_left;
	private Button btchongzhi;
	private Button bttixian;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_wallet);
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
		main_top_right = (TextView) this.findViewById(R.id.main_top_right);
		btchongzhi = (Button) findViewById(R.id.btchongzhi);
		bttixian = (Button) findViewById(R.id.bttixian);
	}

	/**
	 * 方法描述：TODO
	 * 
	 * @author: why
	 * @time: 2014-10-10 下午6:36:02
	 */
	private void initView() {
		((TextView)this.findViewById(R.id.main_top_title)).setText("我的零钱");
//		UIUtils.setDrawableLeft(this,main_top_right,R.drawable.d3_06);
		main_top_left.setVisibility(View.VISIBLE);
		main_top_right.setVisibility(View.VISIBLE);
		main_top_right.setText("账单");
//		UIUtils.setDrawableLeft(this,main_top_left2,R.drawable.d3_03);
		main_top_left.setOnClickListener(this);
		main_top_right.setOnClickListener(this);
		btchongzhi.setOnClickListener(this);
		bttixian.setOnClickListener(this);
	}
	
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.main_top_left:
			finish();
			break;
		case R.id.main_top_right:
			Toast.makeText(MyqianbaoActivity.this, "点击账单", 1000).show();
			break;
		case R.id.btchongzhi:
			IntentUtil.intent(MyqianbaoActivity.this, MychongzhiActivity.class);
			break;
		case R.id.bttixian:
			IntentUtil.intent(MyqianbaoActivity.this, MytixianActivity.class);
			break;
		default:
			break;
		}
	}

}
