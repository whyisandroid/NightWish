package com.timetalent.client.ui.login;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.timetalent.client.R;
import com.timetalent.client.service.AppController;
import com.timetalent.client.ui.BaseActivity;
import com.timetalent.common.util.IntentUtil;
import com.timetalent.common.util.ProgressDialogUtil;
import com.timetalent.common.util.StringUtil;
import com.timetalent.common.util.ToastUtil;


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
	private EditText et_login_username;
	private EditText et_login_password;
	
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
		et_login_username = (EditText)findViewById(R.id.et_login_username);
		et_login_password = (EditText)findViewById(R.id.et_login_password);
	}

	/**
	 * 方法描述：TODO
	 * 
	 * @author: why
	 * @time: 2014-10-10 下午6:36:02
	 */
	private void initView() {
		et_login_password.setText("111111");
		et_login_username.setText("qwe");
		((TextView)findViewById(R.id.main_top_title)).setText("用户登录");
		main_top_right.setVisibility(View.VISIBLE);
		main_top_right.setText("");
		bt_login_next.setOnClickListener(this);
		tv_login_forget_pwd.setOnClickListener(this);
	//	UIUtils.setDrawableLeft(this,main_top_right,R.drawable.f9_06);
	}
	
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_login_next:
			if (invaild()) {
				login();
			}
			break;
		case R.id.tv_login_forget_pwd:
			IntentUtil.intent(LoginActivity.this, FindpwdFirstActivity.class);
			break;
		default:
			break;
		}
	}
	
	
	/**
	  * 方法描述：TODO
	  * @author: why
	  * @time: 2014-10-21 上午11:17:14
	  */
	private void login() {
		setValue();
		ProgressDialogUtil.showProgressDialog(this, "通信中…", false);
		new Thread(new Runnable() {
			@Override
			public void run() {
				controller.login();
				ProgressDialogUtil.closeProgressDialog();
			}
		}).start();
	}
	
	/**
	  * 方法描述：登录 需要的值
	  * @author: why
	  * @time: 2014-10-21 上午11:17:38
	  */
	private void setValue() {
		String account = et_login_username.getText().toString().trim();
		String password = et_login_password.getText().toString().trim();
		controller.getContext().addBusinessData("user.account",account);
		controller.getContext().addBusinessData("user.password",password);
	}
	/**
	  * 方法描述：TODO
	  * @return
	  * @author: why
	  * @time: 2014-10-21 上午11:17:11
	  */
	private boolean invaild() {

		String account = et_login_username.getText().toString().trim();
		String password = et_login_password.getText().toString().trim();
		String accountValidate = StringUtil.accountName(account);
		if(!TextUtils.isEmpty(accountValidate)){
			ToastUtil.showToast(this, accountValidate, ToastUtil.LENGTH_LONG);
			return true;
		} 
		String passwordValidate = StringUtil.pwd(password);
		if (!TextUtils.isEmpty(passwordValidate)) {
			ToastUtil.showToast(this, passwordValidate, ToastUtil.LENGTH_LONG);
			return false;
		}
		return true;
		
	}

}
