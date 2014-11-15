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
import com.timetalent.client.entities.Register;
import com.timetalent.client.service.AppController;
import com.timetalent.client.ui.BaseActivity;
import com.timetalent.common.util.IntentUtil;
import com.timetalent.common.util.ProgressDialogUtil;
import com.timetalent.common.util.StringUtil;
import com.timetalent.common.util.ToastUtil;

/******************************************
 * 类描述： 注册 第三步  手机验证
 * 类名称：RegisterThreeActivity  
 * @version: 1.0
 * @author: why
 * @time: 2014-10-11 下午7:54:27 
 ******************************************/
public class RegisterThreeActivity extends BaseActivity implements OnClickListener {
	private AppController controller;
	private TextView main_top_right;
	private Button bt_register_three_next;
	private Register register;
	private Button bt_register_three_code;
	private EditText et_register_phone;
	private EditText et_register_code;
	
	
	private Handler mHandler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0:
				Bundle bundle = new Bundle();
				bundle.putSerializable("Register.register", register);
				IntentUtil.intent(RegisterThreeActivity.this,bundle,RegisterFourActivity.class,false);
				break;
			case 10:
				//获取验证码 成功
				break;
			default:
				break;
			}
		};
	};
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_three_layout);
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
		bt_register_three_next = (Button)findViewById(R.id.bt_register_three_next);
		bt_register_three_code = (Button)findViewById(R.id.bt_register_three_code);
		et_register_phone = (EditText)findViewById(R.id.et_register_phone);
		et_register_code = (EditText)findViewById(R.id.et_register_code);
		
	}

	/**
	 * 方法描述：TODO
	 * 
	 * @author: why
	 * @time: 2014-10-10 下午6:36:02
	 */
	private void initView() {
		((TextView)findViewById(R.id.main_top_title)).setText("手机验证");
		main_top_right.setVisibility(View.VISIBLE);
		main_top_right.setText("3/4");
		bt_register_three_next.setOnClickListener(this);
		bt_register_three_code.setOnClickListener(this);
		register = (Register)getIntent().getExtras().getSerializable("Register.register");
	}
	
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_register_three_next:
			if (invaild()) {
				next();
			}
			break;
		case R.id.bt_register_three_code:
			getCode();
			break;
		default:
			break;
		}
	}
	
	
	
	/**
	  * 方法描述：TODO
	  * @author: wanghy
	  * @time: 2014-10-23 下午10:54:00
	  */
	private void getCode() {
		ProgressDialogUtil.showProgressDialog(this, "获取验证码…", false);
		new Thread(new Runnable() {
			@Override
			public void run() {
				controller.code(mHandler);
				ProgressDialogUtil.closeProgressDialog();
			}
		}).start();
	}
	/**
	  * 方法描述：TODO
	  * @return
	  * @author: wanghy
	  * @time: 2014-10-23 下午10:53:15
	  */
	private boolean invaild() {


		String phone = et_register_phone.getText().toString().trim();
		String code = et_register_code.getText().toString().trim();
		String accountValidate = StringUtil.moblie(phone);
		if(!TextUtils.isEmpty(accountValidate)){
			ToastUtil.showToast(this, accountValidate, ToastUtil.LENGTH_LONG);
			return false;
		}else{
			controller.getContext().addBusinessData("register.phone", phone);
			register.setPhone(phone);
		}
		if (TextUtils.isEmpty(code)) {
			ToastUtil.showToast(this, "请输入验证码", ToastUtil.LENGTH_LONG);
			return false;
		}else{
			controller.getContext().addBusinessData("register.code", code);
		}
		return true;
		
	
	}
	/**
	  * 方法描述：TODO
	  * @author: wanghy
	  * @time: 2014-10-23 下午10:52:33
	  */
	private void next() {
		ProgressDialogUtil.showProgressDialog(this, "获取验证码…", false);
		new Thread(new Runnable() {
			@Override
			public void run() {
				controller.validationCode(mHandler);
				ProgressDialogUtil.closeProgressDialog();
			}
		}).start();
	}
}
