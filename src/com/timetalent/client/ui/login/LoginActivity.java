package com.timetalent.client.ui.login;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.timetalent.client.R;
import com.timetalent.client.service.AppController;
import com.timetalent.client.ui.BaseActivity;
import com.timetalent.client.ui.MainFragmentActivity;
import com.timetalent.common.util.IntentUtil;
import com.timetalent.common.util.ToastUtil;
import com.timetalent.common.util.UIUtils;


/******************************************
 * 类描述： 登录界面
 * 类名称：LoginActivity  
 * @version: 1.0
 * @author: why
 * @time: 2014-10-10 下午6:32:12 
 ******************************************/
public class LoginActivity extends BaseActivity implements OnClickListener {
	private AppController controller;
	private Button bt_login_next;
	private TextView main_top_right;
	
	private TextView tv_login_forget_pwd; //忘记密码
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_layout);
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
		bt_login_next = (Button)findViewById(R.id.bt_login_next);
		main_top_right = (TextView)findViewById(R.id.main_top_right);
		tv_login_forget_pwd = (TextView)findViewById(R.id.tv_login_forget_pwd);
	}

	/**
	 * 方法描述：TODO
	 * 
	 * @author: why
	 * @time: 2014-10-10 下午6:36:02
	 */
	private void initView() {
		((TextView)findViewById(R.id.main_top_title)).setText("用户登录");
		main_top_right.setVisibility(View.VISIBLE);
		main_top_right.setText("");
		bt_login_next.setOnClickListener(this);
		tv_login_forget_pwd.setOnClickListener(this);
		UIUtils.setDrawableLeft(this,main_top_right,R.drawable.f9_06);
	}
	
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_login_next:
			IntentUtil.intent(LoginActivity.this, MainFragmentActivity.class);
			ToastUtil.showToast(LoginActivity.this, "登陆成功",ToastUtil.LENGTH_LONG);
			break;
		case R.id.tv_login_forget_pwd:
			IntentUtil.intent(LoginActivity.this, FindpwdFirstActivity.class);
			break;
		default:
			break;
		}
	}

}
