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
import com.timetalent.common.util.StringUtil;
import com.timetalent.common.util.ToastUtil;

/******************************************
 * 类描述： 注册 第一步  用户名
 * 类名称：RegisterFirstActivity  
 * @version: 1.0
 * @author: why
 * @time: 2014-10-11 下午7:54:27 
 ******************************************/
public class RegisterFirstActivity extends BaseActivity implements OnClickListener {
	private AppController controller;
	private TextView main_top_right;
	private Button bt_register_first_next;
	private EditText et_register_username;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_first_layout);
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
		bt_register_first_next = (Button)findViewById(R.id.bt_register_first_next);
		et_register_username = (EditText)findViewById(R.id.et_register_username);
	}

	/**
	 * 方法描述：TODO
	 * 
	 * @author: why
	 * @time: 2014-10-10 下午6:36:02
	 */
	private void initView() {
		((TextView)findViewById(R.id.main_top_title)).setText("用户注册");
		main_top_right.setVisibility(View.VISIBLE);
		main_top_right.setText("1/4");
		bt_register_first_next.setOnClickListener(this);
	}
	
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_register_first_next:
			if (invaild()) {
				Bundle bundle = new Bundle();
				Register register = new Register();
				register.setUsername(et_register_username.getText().toString().trim());
				bundle.putSerializable("Register.register", register);
				IntentUtil.intent(RegisterFirstActivity.this,bundle,RegisterSecondActivity.class,false);
			}
			break;
		default:
			break;
		}
	}
	
	/**
	  * 方法描述：TODO
	  * @return
	  * @author: why
	  * @time: 2014-10-21 上午11:17:11
	  */
	private boolean invaild() {

		String account = et_register_username.getText().toString().trim();
		if(TextUtils.isEmpty(account)){
			ToastUtil.showToast(this, "请输入用户名", ToastUtil.LENGTH_LONG);
			return false;
		} 
		return true;
		
	}
}
