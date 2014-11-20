package com.timetalent.client.ui.user;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.timetalent.client.R;
import com.timetalent.client.service.AppController;
import com.timetalent.client.ui.BaseActivity;
import com.timetalent.client.ui.GuideActivity;
import com.timetalent.client.ui.MainFragmentActivity;
import com.timetalent.client.ui.adapter.ZuopinBaseAdapter;
import com.timetalent.client.ui.near.NearDongtaiActivity;
import com.timetalent.client.ui.near.PictureActivity;
import com.timetalent.common.util.IntentUtil;
import com.timetalent.common.util.StringUtil;
import com.timetalent.common.util.ToastUtil;


/******************************************
 * 类描述： 登录界面
 * 类名称：LoginActivity  
 * @version: 1.0
 * @author: why
 * @time: 2014-10-10 下午6:32:12 
 ******************************************/
public class MyrenzhengActivity extends BaseActivity implements OnClickListener {
	private AppController controller;
	private TextView main_top_right;
	private ImageButton main_top_left;
	ImageView img1,img2,img3;
	LinearLayout l1,l2,l3;
	private Button btnext;
	EditText etrealname;
	EditText etshenfenzheng;
	int index = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_renzheng);
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
		btnext = (Button) findViewById(R.id.btnext);
		img1 = (ImageView) findViewById(R.id.img1);
		img2 = (ImageView) findViewById(R.id.img2);
		img3 = (ImageView) findViewById(R.id.img3);
		l1 =(LinearLayout) findViewById(R.id.l1);
		l2 =(LinearLayout) findViewById(R.id.l2);
		l3 =(LinearLayout) findViewById(R.id.l3);
		etrealname = (EditText) findViewById(R.id.etrealname);
		etshenfenzheng = (EditText) findViewById(R.id.etshenfenzheng);
	}

	/**
	 * 方法描述：TODO
	 * 
	 * @author: why
	 * @time: 2014-10-10 下午6:36:02
	 */
	private void initView() {
		((TextView)this.findViewById(R.id.main_top_title)).setText("认证");
//		UIUtils.setDrawableLeft(this,main_top_right,R.drawable.d3_06);
		main_top_left.setVisibility(View.VISIBLE);
//		UIUtils.setDrawableLeft(this,main_top_left2,R.drawable.d3_03);
		main_top_left.setOnClickListener(this);
		btnext.setOnClickListener(this);
		img1.setImageResource(R.drawable.m22_10);
		img2.setImageResource(R.drawable.m22_03);
		img3.setImageResource(R.drawable.m22_09);
		l1.setVisibility(View.VISIBLE);
		l2.setVisibility(View.GONE);
		l3.setVisibility(View.GONE);
	}
	public void setvalue(){
		controller.getContext().addBusinessData("loyal.realname", etrealname.getText().toString());
		controller.getContext().addBusinessData("loyal.certificate", etshenfenzheng.getText().toString());
		
		
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.main_top_left:
			finish();
			break;
		case R.id.btnext:
			String realname = etrealname.getText().toString().trim();
			String shenfenzheng = etshenfenzheng.getText().toString().trim();
			String Validate1 = StringUtil.accountName(realname);
			String Validate2 = StringUtil.accountName(shenfenzheng);
			if(!TextUtils.isEmpty(Validate1)){
				ToastUtil.showToast(this, Validate1, ToastUtil.LENGTH_LONG);
				return;
			}
			if(!TextUtils.isEmpty(Validate2)){
				ToastUtil.showToast(this, Validate2, ToastUtil.LENGTH_LONG);
				return;
			}
			setvalue();
			new Thread(){
				public void run() {
					controller.myuser_loyal();
				};
			}.start();
			index++;
			switch (index) {
			case 1:
				new Thread(){
					public void run() {
						controller.myuser_loyal_item();
					};
				}.start();
				img1.setImageResource(R.drawable.m22_07);
				img2.setImageResource(R.drawable.m22_04);
				img3.setImageResource(R.drawable.m22_09);
				l1.setVisibility(View.GONE);
				l2.setVisibility(View.VISIBLE);
				l3.setVisibility(View.GONE);
				btnext.setText("下一步");
				break;
			case 2:
				img1.setImageResource(R.drawable.m22_07);
				img2.setImageResource(R.drawable.m22_05);
				img3.setImageResource(R.drawable.m22_08);
				l1.setVisibility(View.GONE);
				l2.setVisibility(View.GONE);
				l3.setVisibility(View.VISIBLE);
				btnext.setText("完成");
				break;
			case 3:
				finish();
				break;
			default:
				finish();
				break;
			}
		default:
			break;
		}
	}

}
