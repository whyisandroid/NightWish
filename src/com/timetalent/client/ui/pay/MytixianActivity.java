package com.timetalent.client.ui.pay;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.timetalent.client.R;
import com.timetalent.client.service.AppController;
import com.timetalent.client.ui.BaseActivity;
import com.timetalent.client.ui.adapter.DynamicAdapter;
import com.timetalent.client.ui.dynamic.DynamicAddActivity;
import com.timetalent.common.util.IntentUtil;
import com.timetalent.common.util.ProgressDialogUtil;
import com.timetalent.common.util.StringUtil;
import com.timetalent.common.util.ToastUtil;
import com.timetalent.common.util.UIUtils;


/******************************************
 * 类描述： 动态界面
 * 类名称：NearDongtaiActivity  
 * @version: 1.0
 * @author: why
 * @time: 2014-10-10 下午6:32:12 
 ******************************************/
public class MytixianActivity extends BaseActivity implements OnClickListener {
	private AppController controller;
	private TextView main_top_right;
	private ImageButton main_top_left;
	Button bttixian;
	EditText etaccount_num;
	EditText etaccount_name;
	EditText etmoney;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_tixian);
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
		main_top_left = (ImageButton)this.findViewById(R.id.main_top_left);
		bttixian = (Button) this.findViewById(R.id.bttixian);
		etaccount_num = (EditText) this.findViewById(R.id.etaccount_num);
		etaccount_name = (EditText) this.findViewById(R.id.etaccount_name);
		etmoney = (EditText) this.findViewById(R.id.etmoney);
	}

	/**
	 * 方法描述：TODO
	 * 
	 * @author: why
	 * @time: 2014-10-10 下午6:36:02
	 */
	private void initView() {
		((TextView)this.findViewById(R.id.main_top_title)).setText("我的零钱");
//		UIUtils.setDrawableLeft(this,main_top_right,R.drawable.d3_06);
		main_top_left.setVisibility(View.VISIBLE);
//		UIUtils.setDrawableLeft(this,main_top_left2,R.drawable.d3_03);
		main_top_left.setOnClickListener(this);
		bttixian.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.main_top_left:
			finish();
			break;
		case R.id.bttixian:
			if(invaild()){
				withdraw();
			}
			break;
		default:
			break;
		}
	}
	
	
	/**
	  * 方法描述：TODO
	  * @author: why
	  * @time: 2014-12-10 下午9:39:16
	  */
	private void withdraw() {
		ProgressDialogUtil.showProgressDialog(this, "通信中…", false);
		new Thread(){
			public void run() {
				controller.mywithdraw();
				ProgressDialogUtil.closeProgressDialog();
			};
		}.start();
		
	}
	/**
	  * 方法描述：TODO
	  * @return
	  * @author: why
	  * @time: 2014-10-21 上午11:17:11
	  */
	private boolean invaild() {
		String account = etaccount_num.getText().toString().trim();
		String name = etaccount_name.getText().toString().trim();
		String money = etmoney.getText().toString().trim();
		if(TextUtils.isEmpty(account)){
			ToastUtil.showToast(this, "账号不能为空!", ToastUtil.LENGTH_LONG);
			return false;
		} else{
			controller.getContext().addBusinessData("tixian.account_num", etaccount_num.getText()+"");
		}
		if (TextUtils.isEmpty(name)) {
			ToastUtil.showToast(this, "姓名不能为空", ToastUtil.LENGTH_LONG);
			return false;
		}else{
			controller.getContext().addBusinessData("tixian.account_name", etaccount_name.getText()+"");
		}
		if (TextUtils.isEmpty(money)) {
			ToastUtil.showToast(this, "提现星币不能为空", ToastUtil.LENGTH_LONG);
			return false;
		}else{
			controller.getContext().addBusinessData("tixian.money", etmoney.getText()+"");
		}
		controller.getContext().addBusinessData("tixian.type", "alipay");
		controller.getContext().addBusinessData("tixian.account_vender", "ALIBABA");
		return true;
	}

}
