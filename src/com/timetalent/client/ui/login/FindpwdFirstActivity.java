package com.timetalent.client.ui.login;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.timetalent.client.R;
import com.timetalent.client.service.AppController;
import com.timetalent.client.ui.BaseActivity;
import com.timetalent.client.ui.dialog.DialogUtil;
import com.timetalent.common.util.IntentUtil;

/******************************************
 * 类描述： 找回密码 第一步  填写手机号
 * 类名称：FindpwdFirstActivity  
 * @version: 1.0
 * @author: why
 * @time: 2014-10-11 下午7:54:27 
 ******************************************/
public class FindpwdFirstActivity extends BaseActivity implements OnClickListener {
	private AppController controller;
	private TextView main_top_right;
	private Button bt_register_pwd_next;
	private Button bt_register_pwd_code;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.findpwd_first_layout);
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
		bt_register_pwd_next = (Button)findViewById(R.id.bt_register_pwd_next);
		bt_register_pwd_code = (Button)findViewById(R.id.bt_register_pwd_code);
	}

	/**
	 * 方法描述：TODO
	 * 
	 * @author: why
	 * @time: 2014-10-10 下午6:36:02
	 */
	private void initView() {
		((TextView)findViewById(R.id.main_top_title)).setText("找回密码");
		bt_register_pwd_next.setOnClickListener(this);
		bt_register_pwd_code.setOnClickListener(this);
	}
	
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_register_pwd_next:
			IntentUtil.intent(FindpwdFirstActivity.this, FindpwdSecondActivity.class);
			break;
		case R.id.bt_register_pwd_code:
			DialogUtil.showMessage(FindpwdFirstActivity.this, "验证码已经发送到您的手机");
			break;
		default:
			break;
		}
	}
}
