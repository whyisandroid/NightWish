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
 * 类描述：找回密码 第二步  重置密码
 * 类名称：FindpwdSecondActivity  
 * @version: 1.0
 * @author: why
 * @time: 2014-10-11 下午7:54:27 
 ******************************************/
public class FindpwdSecondActivity extends BaseActivity implements OnClickListener {
	private AppController controller;
	private TextView main_top_right;
	private Button bt_register_four_next;
	private EditText et_find_pwd1;
	private EditText et_find_pwd2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.findpwd_second_layout);
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
		bt_register_four_next = (Button)findViewById(R.id.bt_register_four_next);
		et_find_pwd1 = (EditText)findViewById(R.id.et_find_pwd1);
		et_find_pwd2 = (EditText)findViewById(R.id.et_find_pwd2);
	}

	/**
	 * 方法描述：TODO
	 * 
	 * @author: why
	 * @time: 2014-10-10 下午6:36:02
	 */
	private void initView() {
		((TextView)findViewById(R.id.main_top_title)).setText("密码重置");
		bt_register_four_next.setOnClickListener(this);
	}
	
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_register_four_next:
			if(invaild()){
				next();
			}
			break;
		default:
			break;
		}
	}
	
	
	/**
	  * 方法描述：TODO
	  * @author: wanghy
	  * @time: 2014-11-17 下午11:26:25
	  */
	private void next() {

		ProgressDialogUtil.showProgressDialog(this, "通信中…", false);
		new Thread(new Runnable() {
			@Override
			public void run() {
				controller.resetPwd();
				ProgressDialogUtil.closeProgressDialog();
			}
		}).start();
	
	}
	/**
	  * 方法描述：TODO
	  * @return
	  * @author: wanghy
	  * @time: 2014-11-17 下午11:26:09
	  */
	private boolean invaild() {
		String password = et_find_pwd1.getText().toString().trim();
		String password2 = et_find_pwd2.getText().toString().trim();
		String passwordValidate = StringUtil.pwd(password);
		if (!TextUtils.isEmpty(passwordValidate)){
			ToastUtil.showToast(this, passwordValidate, ToastUtil.LENGTH_LONG);
			return false;
		}
		String passwordValidate2 = StringUtil.pwd(password2);
		if (!TextUtils.isEmpty(passwordValidate2)){
			ToastUtil.showToast(this, passwordValidate, ToastUtil.LENGTH_LONG);
			return false;
		}
		
		if(password.equals(password2)){
			controller.getContext().addBusinessData("find.pwd", password2);
		}else{
			ToastUtil.showToast(this, "两次输入的密码不一致", ToastUtil.LENGTH_LONG);
			return false;
		}
		return true;
	}
}
