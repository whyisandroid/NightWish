package com.timetalent.client.ui.login;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.timetalent.client.R;
import com.timetalent.client.entities.Register;
import com.timetalent.client.service.AppController;
import com.timetalent.client.ui.BaseActivity;
import com.timetalent.common.util.IntentUtil;
import com.timetalent.common.util.ProgressDialogUtil;
import com.timetalent.common.util.StringUtil;
import com.timetalent.common.util.ToastUtil;

/******************************************
 * 类描述： 注册 第四步  填写密码
 * 类名称：RegisterThreeActivity  
 * @version: 1.0
 * @author: why
 * @time: 2014-10-11 下午7:54:27 
 ******************************************/
public class RegisterFourActivity extends BaseActivity implements OnClickListener {
	private AppController controller;
	private TextView main_top_right;
	private Button bt_register_four_next;
	private EditText et_register_pwd1;
	private EditText et_register_pwd2;
	private Register register;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_four_layout);
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
		et_register_pwd1 = (EditText)findViewById(R.id.et_register_pwd1);
		et_register_pwd2 = (EditText)findViewById(R.id.et_register_pwd2);
	}

	/**
	 * 方法描述：TODO
	 * 
	 * @author: why
	 * @time: 2014-10-10 下午6:36:02
	 */
	private void initView() {
		((TextView)findViewById(R.id.main_top_title)).setText("设置密码");
		main_top_right.setVisibility(View.VISIBLE);
		main_top_right.setText("4/4");
		bt_register_four_next.setOnClickListener(this);
		register = (Register)getIntent().getExtras().get("Register.register");
	}
	
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_register_four_next:
			if (invaild()) {
				next();
			}
			break;
		default:
			break;
		}
	}
	
/**
  * 方法描述：TODO
  * @return
  * @author: wanghy
  * @time: 2014-10-23 下午10:53:15
  */
private boolean invaild() {


	String pwd1 = et_register_pwd1.getText().toString().trim();
	String pwd2 = et_register_pwd2.getText().toString().trim();
	String pwd1Validate = StringUtil.pwd(pwd1);
	String pwd2Validate = StringUtil.pwd(pwd2);
	if (!TextUtils.isEmpty(pwd1Validate)) {
		ToastUtil.showToast(this, pwd1Validate, ToastUtil.LENGTH_LONG);
		return false;
	}
	if (!TextUtils.isEmpty(pwd2Validate)) {
		ToastUtil.showToast(this, pwd2Validate, ToastUtil.LENGTH_LONG);
		return false;
	}
	
	if(pwd1.equals(pwd2)){
		register.setPassword(pwd1);
		controller.getContext().addBusinessData("Register.register", register);
	}else{
		ToastUtil.showToast(this, "两次输入的密码不一样", ToastUtil.LENGTH_LONG);
		return false;
	}
	return true;
	

}
/**
  * 方法描述：TODO
  * @author: wanghy
  * @time: 2014-10-23 下午10:52:33
  */
private void next() {
	ProgressDialogUtil.showProgressDialog(this, "请稍等…", false);
	new Thread(new Runnable() {
		@Override
		public void run() {
			controller.register();
			ProgressDialogUtil.closeProgressDialog();
		}
	}).start();
}
}
