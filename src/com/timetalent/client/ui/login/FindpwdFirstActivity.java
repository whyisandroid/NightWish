package com.timetalent.client.ui.login;

import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.timetalent.client.R;
import com.timetalent.client.service.AppController;
import com.timetalent.client.ui.BaseActivity;
import com.timetalent.client.ui.dialog.DialogUtil;
import com.timetalent.common.util.IntentUtil;
import com.timetalent.common.util.ProgressDialogUtil;
import com.timetalent.common.util.StringUtil;
import com.timetalent.common.util.ToastUtil;

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
	private EditText et_find_pwd_phone;
	private EditText et_find_pwd_code;
	
	private Handler mHandler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			
			case 0:
				IntentUtil.intent(FindpwdFirstActivity.this, FindpwdSecondActivity.class);
				break;
			default:
				break;
			}
		};
	};
	
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
		et_find_pwd_phone = (EditText)findViewById(R.id.et_find_pwd_phone);
		et_find_pwd_code = (EditText)findViewById(R.id.et_find_pwd_code);
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
			if(invaildNext()){
				next();
			}
			break;
		case R.id.bt_register_pwd_code:
			if(invaild()){
				getCode();
			}
			break;
		default:
			break;
		}
	}
	
	
	
	/**
	  * 方法描述：TODO
	  * @author: why
	  * @time: 2014-11-15 上午11:48:33
	  */
	private void next() {
		ProgressDialogUtil.showProgressDialog(this, "通信中…", false);
		new Thread(new Runnable() {
			@Override
			public void run() {
				controller.validationCode(mHandler);
				ProgressDialogUtil.closeProgressDialog();
			}
		}).start();
	
	}
	/**
	  * 方法描述：TODO
	  * @return
	  * @author: why
	  * @time: 2014-11-15 上午11:42:09
	  */
	private boolean invaild() {
		String phone = et_find_pwd_phone.getText().toString().trim();
		String accountValidate = StringUtil.moblie(phone);
		if(!TextUtils.isEmpty(accountValidate)){
			ToastUtil.showToast(this, accountValidate, ToastUtil.LENGTH_LONG);
			return false;
		}else{
			controller.getContext().addBusinessData("phone", phone);
		}
		return true;
	}
	
	/**
	  * 方法描述：TODO
	  * @return
	  * @author: why
	  * @time: 2014-11-15 上午11:42:09
	  */
	private boolean invaildNext() {
		String phone = et_find_pwd_phone.getText().toString().trim();
		String accountValidate = StringUtil.moblie(phone);
		if(!TextUtils.isEmpty(accountValidate)){
			ToastUtil.showToast(this, accountValidate, ToastUtil.LENGTH_LONG);
			return false;
		}else{
			controller.getContext().addBusinessData("phone", phone);
		} 
		
		String code = et_find_pwd_code.getText().toString().trim();
		if(TextUtils.isEmpty(code)){
			ToastUtil.showToast(this, "验证码不能为空", ToastUtil.LENGTH_LONG);
			return false;
		}else{
			controller.getContext().addBusinessData("code", code);
		}
		return true;
	}
	/**
	  * 方法描述：TODO
	  * @author: why
	  * @time: 2014-11-15 上午11:41:39
	  */
	private void getCode() {
		ProgressDialogUtil.showProgressDialog(this, "通信中…", false);
		new Thread(new Runnable() {
			@Override
			public void run() {
				controller.code(mHandler);
				ProgressDialogUtil.closeProgressDialog();
			}
		}).start();
	}
}
