package com.timetalent.client.ui.fragment;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.timetalent.client.R;
import com.timetalent.client.service.AppController;
import com.timetalent.client.ui.fragment.util.Background1;
import com.timetalent.client.ui.fragment.util.Background2;
import com.timetalent.client.ui.fragment.util.NearBaseAdapter;

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
	private TextView tvshaixuan;
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
		list.setAdapter(new NearBaseAdapter(getActivity()));
		tvshaixuan.setOnClickListener(this);
	}

	/**
	 * 方法描述：TODO
	 * 
	 * @author: why
	 * @time: 2014-10-10 下午6:36:00
	 */
	private void findView() {
		list = (ListView) view.findViewById(R.id.listView1);
		tvshaixuan = (TextView) view.findViewById(R.id.tvshaixuan);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tvshaixuan:
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
		default:
			break;
		}
	}
}
