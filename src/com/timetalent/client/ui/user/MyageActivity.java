package com.timetalent.client.ui.user;

import java.util.Calendar;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
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

/******************************************
 * 类描述： 登录界面 类名称：LoginActivity
 * 
 * @version: 1.0
 * @author: why
 * @time: 2014-10-10 下午6:32:12
 ******************************************/
public class MyageActivity extends BaseActivity implements OnClickListener {
	private AppController controller;
	private TextView main_top_right;
	private ImageButton main_top_left;
	TextView tvage;
	TextView tvxingzuo;
	DatePicker dp;
	private Button btok;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_age);
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
		main_top_left = (ImageButton) this.findViewById(R.id.main_top_left);
		tvage = (TextView) findViewById(R.id.tvage);
		tvxingzuo = (TextView) findViewById(R.id.tvxingzuo);
		dp = (DatePicker) findViewById(R.id.dp);
		btok = (Button) findViewById(R.id.btok);
	}

	/**
	 * 方法描述：TODO
	 * 
	 * @author: why
	 * @time: 2014-10-10 下午6:36:02
	 */
	private void initView() {
		((TextView) this.findViewById(R.id.main_top_title)).setText("年龄");
		// UIUtils.setDrawableLeft(this,main_top_right,R.drawable.d3_06);
		main_top_left.setVisibility(View.VISIBLE);
		// UIUtils.setDrawableLeft(this,main_top_left2,R.drawable.d3_03);
		main_top_left.setOnClickListener(this);
		Calendar calendar = Calendar.getInstance();
		final int yearnow = calendar.get(Calendar.YEAR);
		int monthOfYearnow = calendar.get(Calendar.MONTH);
		int dayOfMonthnow = calendar.get(Calendar.DAY_OF_MONTH);
		dp.init(yearnow, monthOfYearnow, dayOfMonthnow,
				new OnDateChangedListener() {

					public void onDateChanged(DatePicker view, int year,
							int monthOfYear, int dayOfMonth) {
						tvage.setText((yearnow - year) + "岁");
						switch (monthOfYear) {
						case 0:
							if (dayOfMonth < 20) {
								tvxingzuo.setText("摩羯座");
							} else {
								tvxingzuo.setText("水瓶座");
							}
							break;
						case 1:
							if (dayOfMonth < 19) {
								tvxingzuo.setText("水瓶座");
							} else {
								tvxingzuo.setText("双鱼座");
							}
							break;
						case 2:
							if (dayOfMonth < 21) {
								tvxingzuo.setText("双鱼座");
							} else {
								tvxingzuo.setText("白羊座");
							}
							break;
						case 3:
							if (dayOfMonth < 20) {
								tvxingzuo.setText("白羊座");
							} else {
								tvxingzuo.setText("金牛座");
							}
							break;
						case 4:
							if (dayOfMonth < 21) {
								tvxingzuo.setText("金牛座");
							} else {
								tvxingzuo.setText("双子座");
							}
							break;
						case 5:
							if (dayOfMonth < 22) {
								tvxingzuo.setText("双子座");
							} else {
								tvxingzuo.setText("巨蟹座");
							}
							break;
						case 6:
							if (dayOfMonth < 23) {
								tvxingzuo.setText("巨蟹座");
							} else {
								tvxingzuo.setText("狮子座");
							}
							break;
						case 7:
							if (dayOfMonth < 23) {
								tvxingzuo.setText("狮子座");
							} else {
								tvxingzuo.setText("处女座");
							}
							break;
						case 8:
							if (dayOfMonth < 23) {
								tvxingzuo.setText("处女座");
							} else {
								tvxingzuo.setText("天秤座");
							}
							break;
						case 9:
							if (dayOfMonth < 24) {
								tvxingzuo.setText("天秤座");
							} else {
								tvxingzuo.setText("天蝎座");
							}
							break;
						case 10:
							if (dayOfMonth < 23) {
								tvxingzuo.setText("天蝎座");
							} else {
								tvxingzuo.setText("射手座");
							}
							break;
						case 11:
							if (dayOfMonth < 22) {
								tvxingzuo.setText("射手座");
							} else {
								tvxingzuo.setText("摩羯座");
							}
							break;
						default:
							break;
						}
						controller.getContext().addBusinessData("edit.age", ""+tvage.getText().toString());
						controller.getContext().addBusinessData("edit.xingzuo", ""+tvxingzuo.getText().toString());
					}

				});
		btok.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.main_top_left:
			finish();
			break;
		case R.id.btok:
			finish();
		default:
			break;
		}
	}

}
