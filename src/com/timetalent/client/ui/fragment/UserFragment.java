package com.timetalent.client.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.timetalent.client.R;
import com.timetalent.client.service.AppController;
import com.timetalent.client.ui.near.SearchActivity;
import com.timetalent.client.ui.user.MyDongtaiActivity;
import com.timetalent.client.ui.user.MyfansActivity;
import com.timetalent.client.ui.user.MyfuwuActivity;
import com.timetalent.client.ui.user.MyguanzhuActivity;
import com.timetalent.client.ui.user.MyhaoyouMainActivity;
import com.timetalent.client.ui.user.MyheimingdanActivity;
import com.timetalent.client.ui.user.MyqianbaoActivity;
import com.timetalent.client.ui.user.MytuijianActivity;
import com.timetalent.client.ui.user.MyworkActivity;
import com.timetalent.client.ui.user.YirenziliaoActivity;
import com.timetalent.common.util.IntentUtil;


/******************************************
 * 类描述： 我的
 * 类名称：UserFragment  
 * @version: 1.0
 * @author: why
 * @time: 2014-10-10 下午6:32:45 
 ******************************************/
public class UserFragment extends Fragment implements OnClickListener {
	private View view;
	private Context mContext;
	private TextView main_top_right;
	private ImageButton main_top_left;
	private AppController controller;
	private LinearLayout lmydongtai;
	private LinearLayout lmyhaoyou;
	private LinearLayout lmytuijian;
	private LinearLayout lmyguanzhu;
	private LinearLayout lmyfans;
	private LinearLayout lmyqianbao;
	private LinearLayout lmywork;
	private LinearLayout lmyfuwu;
	private LinearLayout lheimingdan;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		controller = AppController.getController(getActivity());
		view = inflater.inflate(R.layout.user_fragment, container, false);
		mContext = getActivity();
		findView();
		initView();
		return view;
	}

	/**
	 * 方法描述：TODO
	 * 
	 * @author: why
	 * @time: 2014-10-10 下午6:36:02
	 */
	private void initView() {
		((TextView)view.findViewById(R.id.main_top_title)).setText("我的");
//		UIUtils.setDrawableLeft(this,main_top_right,R.drawable.d3_06);
		main_top_left.setVisibility(View.VISIBLE);
//		UIUtils.setDrawableLeft(this,main_top_left2,R.drawable.d3_03);
		main_top_left.setOnClickListener(this);
		lmydongtai.setOnClickListener(this);
		lmyhaoyou.setOnClickListener(this);
		lmytuijian.setOnClickListener(this);
		lmyguanzhu.setOnClickListener(this);
		lmyfans.setOnClickListener(this);
		lmyqianbao.setOnClickListener(this);
		lmywork.setOnClickListener(this);
		lmyfuwu.setOnClickListener(this);
		lheimingdan.setOnClickListener(this);

	}

	/**
	 * 方法描述：TODO
	 * 
	 * @author: why
	 * @time: 2014-10-10 下午6:36:00
	 */
	private void findView() {
		main_top_left = (ImageButton)view.findViewById(R.id.main_top_left);
		lmydongtai = (LinearLayout) view.findViewById(R.id.lmydongtai);
		lmyhaoyou = (LinearLayout) view.findViewById(R.id.lmyhaoyou);
		lmytuijian = (LinearLayout) view.findViewById(R.id.lmytuijian);
		lmyguanzhu = (LinearLayout) view.findViewById(R.id.lmyguanzhu);
		lmyfans = (LinearLayout) view.findViewById(R.id.lmyfans);
		lmyqianbao = (LinearLayout) view.findViewById(R.id.lmyqianbao);
		lmywork = (LinearLayout) view.findViewById(R.id.lmywork);
		lmyfuwu = (LinearLayout) view.findViewById(R.id.lmyfuwu);
		lheimingdan = (LinearLayout) view.findViewById(R.id.lheimingdan);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.main_top_left:
//			finish();
			break;
		case R.id.lmydongtai:
			IntentUtil.intent(this.mContext, YirenziliaoActivity.class);
			break;
		case R.id.lmyhaoyou:
			Bundle bundle0 = new Bundle();
			bundle0.putInt("index", 0);
			IntentUtil.intent(this.mContext, bundle0,MyhaoyouMainActivity.class,false);
			break;
		case R.id.lmytuijian:
			Bundle bundle1 = new Bundle();
			bundle1.putInt("index", 1);
			IntentUtil.intent(this.mContext, bundle1,MyhaoyouMainActivity.class,false);
			break;
		case R.id.lmyguanzhu:
			Bundle bundle2 = new Bundle();
			bundle2.putInt("index", 2);
			IntentUtil.intent(this.mContext, bundle2,MyhaoyouMainActivity.class,false);
			break;
		case R.id.lmyfans:
			Bundle bundle3 = new Bundle();
			bundle3.putInt("index", 3);
			IntentUtil.intent(this.mContext, bundle3,MyhaoyouMainActivity.class,false);
			break;
		case R.id.lmyqianbao:
			IntentUtil.intent(this.mContext, MyqianbaoActivity.class);
			break;
		case R.id.lmywork:
			IntentUtil.intent(this.mContext, MyworkActivity.class);
			break;
		case R.id.lmyfuwu:
			IntentUtil.intent(this.mContext, MyfuwuActivity.class);
			break;
		case R.id.lheimingdan:
			IntentUtil.intent(this.mContext, MyheimingdanActivity.class);
			break;
		default:
			break;
		}
	}

}
