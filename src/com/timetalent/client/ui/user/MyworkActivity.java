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
public class MyworkActivity extends BaseActivity implements OnClickListener {
	private AppController controller;
	private TextView main_top_right;
	private ImageButton main_top_left;
	RadioButton rb1;
	RadioButton rb2;
	private ListView lwork;
	private ListView lyaoqing;
	int index;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		controller = AppController.getController(this);
		index = this.getIntent().getIntExtra("index", 0);
		switch (index) {
		case 0:
			setContentView(R.layout.my_workyiren);
			break;
		case 1:
			setContentView(R.layout.my_workxingtan);
			break;
		case 2:
			setContentView(R.layout.my_workfans);
			break;
		default:
			break;
		}
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
		lyaoqing = (ListView) findViewById(R.id.lyaoqing);
		rb1 = (RadioButton) findViewById(R.id.radioButton1);
		rb2 = (RadioButton) findViewById(R.id.radioButton2);
		lwork = (ListView) findViewById(R.id.lwork);
		
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
		switch (index) {
		case 0:
			lyaoqing.setAdapter(new YirenYaoqingAdapter(MyworkActivity.this,controller));
			break;
		case 1:
			lyaoqing.setAdapter(new XingtanWorkAdapter(MyworkActivity.this,controller));
			break;
		case 2:
			lyaoqing.setAdapter(new FansYaoqingAdapter(MyworkActivity.this,controller,"Y"));
			break;
		default:
			break;
		}
		
		rb1.setOnClickListener(this);
		rb2.setOnClickListener(this);
		lyaoqing.setVisibility(lyaoqing.GONE);
		
		if(index == 0){
			lwork.setAdapter(new YirenWorkAdapter(MyworkActivity.this,controller));
//			lwork.setOnItemClickListener(new OnItemClickListener() {
//				@Override
//				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
//						long arg3) {
//					IntentUtil.intent(MyworkActivity.this, OfferDetailActivity.class);
//				}
//			});
		}else if(index == 1){
			lwork.setAdapter(new XingtanYaoqingAdapter(MyworkActivity.this,controller));
			lwork.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
						long arg3) {
					final WorkIOSStyleListDialog dialog = new WorkIOSStyleListDialog(MyworkActivity.this, WorkIOSStyleListDialog.DIALOG_TWO);
				}
			});
		}else{
			lwork.setAdapter(new FansYaoqingAdapter(MyworkActivity.this,controller,"N"));
		}
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.main_top_left:
			finish();
			break;
		case R.id.radioButton1:
			switch (index) {
			case 0:
				
				break;
			case 1:
				
				break;
			case 2:
	
		break;
			default:
				break;
			}
			lwork.setVisibility(lwork.GONE);
			lyaoqing.setVisibility(lyaoqing.VISIBLE);
			break;
		case R.id.radioButton2:
			switch (index) {
			case 0:
				
				break;
			case 1:
				
				break;
			case 2:
	
				break;
			default:
				break;
			}
			lwork.setVisibility(lwork.VISIBLE);
			lyaoqing.setVisibility(lyaoqing.GONE);
			break;
		default:
			break;
		}
	}

}
