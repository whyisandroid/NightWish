package com.timetalent.client.ui.login;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.timetalent.client.R;
import com.timetalent.client.service.AppController;
import com.timetalent.client.ui.BaseActivity;
import com.timetalent.client.ui.MainFragmentActivity;
import com.timetalent.common.util.IntentUtil;

/******************************************
 * 类描述： 注册 第二步  个人资料
 * 类名称：RegisterFirstActivity  
 * @version: 1.0
 * @author: why
 * @time: 2014-10-11 下午7:54:27 
 ******************************************/
public class RegisterSecondActivity extends BaseActivity implements OnClickListener {
	private AppController controller;
	private TextView main_top_right;
	private Button bt_register_second_next;
	private RadioButton bt_register_second_1;
	private RadioButton bt_register_second_2;
	private RadioButton bt_register_second_3;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_second_layout);
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
		bt_register_second_next = (Button)findViewById(R.id.bt_register_second_next);
		bt_register_second_1 = (RadioButton)findViewById(R.id.bt_register_second_1);
		bt_register_second_2 = (RadioButton)findViewById(R.id.bt_register_second_2);
		bt_register_second_3 = (RadioButton)findViewById(R.id.bt_register_second_3);
	}

	/**
	 * 方法描述：TODO
	 * 
	 * @author: why
	 * @time: 2014-10-10 下午6:36:02
	 */
	private void initView() {
		((TextView)findViewById(R.id.main_top_title)).setText("个人资料");
		main_top_right.setVisibility(View.VISIBLE);
		main_top_right.setText("2/4");
		bt_register_second_next.setOnClickListener(this);
		bt_register_second_1.setOnClickListener(this);
		bt_register_second_2.setOnClickListener(this);
		bt_register_second_3.setOnClickListener(this);
	}
	
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_register_second_next:
			IntentUtil.intent(RegisterSecondActivity.this, RegisterThreeActivity.class);
			break;
		case R.id.bt_register_second_1:
			bt_register_second_1.setChecked(true);
			bt_register_second_2.setChecked(false);
			bt_register_second_3.setChecked(false);
			break;
		case R.id.bt_register_second_2:
			bt_register_second_1.setChecked(false);
			bt_register_second_2.setChecked(true);
			bt_register_second_3.setChecked(false);
			break;
		case R.id.bt_register_second_3:
			bt_register_second_1.setChecked(false);
			bt_register_second_2.setChecked(false);
			bt_register_second_3.setChecked(true);
			break;
		default:
			break;
		}
	}
}
