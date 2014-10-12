package com.timetalent.client.ui.fragment;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.timetalent.client.R;
import com.timetalent.client.service.AppController;
import com.timetalent.client.ui.MainFragmentActivity;
import com.timetalent.client.ui.fragment.util.Background1;
import com.timetalent.client.ui.fragment.util.Background2;
import com.timetalent.client.ui.fragment.util.DipPxUtil;
import com.timetalent.client.ui.fragment.util.NearBaseAdapter;
import com.timetalent.client.ui.login.LoginActivity;
import com.timetalent.client.ui.near.FansActivity;
import com.timetalent.client.ui.near.SearchActivity;
import com.timetalent.client.ui.near.XingtanActivity;
import com.timetalent.client.ui.near.YirenActivity;
import com.timetalent.common.util.IntentUtil;

/**
 * **************************************** 类描述： 附近 类名称：NearFragment
 * 
 * @version: 1.0
 * @author: why
 * @time: 2014-10-10 下午6:30:07
 ***************************************** 
 */
public class NearFragment extends Fragment implements OnClickListener {
	private View view;
	private Context mContext;
	private AppController controller;
	private ListView list;
	private TextView btshaixuan;
	private TextView tvtitle;
	private TextView btsearch;
	private ImageButton btback;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		controller = AppController.getController(getActivity());
		view = inflater.inflate(R.layout.near_fragment, container, false);
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
		btback.setVisibility(btback.GONE);
		btshaixuan.setVisibility(btshaixuan.VISIBLE);
		btsearch.setVisibility(btsearch.VISIBLE);
		tvtitle.setText("附近");
		btshaixuan.setText("筛选");
		btsearch.setText("");
		btsearch.setBackgroundResource(R.drawable.f9_06);
		btsearch.setPadding(DipPxUtil.dip2px(mContext, 10), DipPxUtil.dip2px(mContext, 5), DipPxUtil.dip2px(mContext, 10), DipPxUtil.dip2px(mContext, 5));
		list.setAdapter(new NearBaseAdapter(getActivity()));
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				if(arg2 == 1){
					IntentUtil.intent(mContext, YirenActivity.class);
				}
				if(arg2 == 2){
					IntentUtil.intent(mContext, XingtanActivity.class);
				}
				if(arg2 == 3){
					IntentUtil.intent(mContext, FansActivity.class);
				}
				
				
			}
		});
		btshaixuan.setOnClickListener(this);
		btsearch.setOnClickListener(this);
	}

	/**
	 * 方法描述：TODO
	 * 
	 * @author: why
	 * @time: 2014-10-10 下午6:36:00
	 */
	private void findView() {
		list = (ListView) view.findViewById(R.id.listView1);
		btback = (ImageButton) view.findViewById(R.id.main_top_left);
		btshaixuan = (TextView) view.findViewById(R.id.main_top_left2);
		btsearch = (TextView) view.findViewById(R.id.main_top_right);
		tvtitle = (TextView) view.findViewById(R.id.main_top_title);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.main_top_left2:
			LayoutInflater inflater = LayoutInflater.from(view.getContext());
			View popview = inflater.inflate(R.layout.near_shaixuan, null);
			final PopupWindow pop = new PopupWindow(popview, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, false);
			pop.setBackgroundDrawable(new Background2());
			pop.setOutsideTouchable(true);
			pop.setFocusable(true);
			Button btok = (Button) popview.findViewById(R.id.btok);
			btok.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					pop.dismiss();
					
				}
			});
			pop.showAsDropDown(v);
			break;
		case R.id.main_top_right:
			IntentUtil.intent(this.mContext, SearchActivity.class);
			 
			break;
		default:
			break;
		}
	}
}
