package com.timetalent.client.ui.dynamic;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.timetalent.client.R;
import com.timetalent.client.service.AppController;
import com.timetalent.client.ui.BaseActivity;
import com.timetalent.client.ui.adapter.DynamicAddAdapter;
import com.timetalent.common.util.IntentUtil;
import com.timetalent.common.util.ProgressDialogUtil;
import com.timetalent.common.util.StringUtil;
import com.timetalent.common.util.ToastUtil;


/******************************************
 * 类描述： 添加动态
 * 类名称：AddDynamicActivity  
 * @version: 1.0
 * @author: wanghy
 * @time: 2014-10-12 下午8:25:54 
 ******************************************/
public class DynamicAddActivity extends BaseActivity implements OnClickListener {
	private AppController controller;
	private TextView main_top_right;
	private TextView main_top_left2;
	private ImageView iv_dynamic_add;//动态
	private EditText et_dynamic_add_content;
	private GridView gv_dynamic_add;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dynamic_add_info);
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
		main_top_left2 = (TextView)findViewById(R.id.main_top_left2);
		iv_dynamic_add = (ImageView)findViewById(R.id.iv_dynamic_add);
		et_dynamic_add_content =(EditText)findViewById(R.id.et_dynamic_add_content);
		gv_dynamic_add = (GridView)findViewById(R.id.gv_dynamic_add);
	}

	/**
	 * 方法描述：TODO
	 * 
	 * @author: why
	 * @time: 2014-10-10 下午6:36:02
	 */
	private void initView() {
		((TextView)findViewById(R.id.main_top_title)).setText("添加动态");
		main_top_right.setVisibility(View.VISIBLE);
		main_top_right.setText("发表");
		main_top_right.setOnClickListener(this);
		findViewById(R.id.main_top_left).setVisibility(View.GONE);
		main_top_left2.setVisibility(View.VISIBLE);
		main_top_left2.setText("取消");
		main_top_right.setOnClickListener(this);
		main_top_left2.setOnClickListener(this);
		iv_dynamic_add.setOnClickListener(this);
		DynamicAddAdapter adapter = new  DynamicAddAdapter(this);
		gv_dynamic_add.setAdapter(adapter);
	}
	
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.main_top_right:
			if(invaild()){
				send();
			}
			break;
		case R.id.main_top_left2:
			IntentUtil.intent(DynamicAddActivity.this, DynamicMyActivity.class);
			break;
		case R.id.iv_dynamic_add:
			StringUtil.doGoToImg(DynamicAddActivity.this);
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
	private void send() {
		ProgressDialogUtil.showProgressDialog(this, "通信中…", false);
		new Thread(new Runnable() {
			@Override
			public void run() {
				controller.chanceAdd();
				ProgressDialogUtil.closeProgressDialog();
			}
		}).start();
	}
	
	/**
	  * 方法描述：TODO
	  * @return
	  * @author: why
	  * @time: 2014-10-21 上午11:17:11
	  */
	private boolean invaild() {
		String account = et_dynamic_add_content.getText().toString().trim();
		if(TextUtils.isEmpty(account)){
			ToastUtil.showToast(this, "发表内容不能为空", ToastUtil.LENGTH_LONG);
			return false;
		}else{
			controller.getContext().addBusinessData("dynamic_add", account);
		}
		return true;
	}
}
