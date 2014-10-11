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
public class SearchFragment extends Fragment implements OnClickListener {
	private View view;
	private Context mContext;
	private AppController controller;
	private ListView list;
	private TextView tvok;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		controller = AppController.getController(getActivity());
		view = inflater.inflate(R.layout.near_sousuo, container, false);
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
		list.setAdapter(new NearBaseAdapter(mContext));
		list.setVisibility(list.GONE);
		tvok.setOnClickListener(this);
	}

	/**
	 * 方法描述：TODO
	 * 
	 * @author: why
	 * @time: 2014-10-10 下午6:36:00
	 */
	private void findView() {
		list = (ListView) view.findViewById(R.id.listView1);
		tvok = (TextView) view.findViewById(R.id.tvok);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tvok:
			list.setVisibility(list.VISIBLE);
			break;
		default:
			break;
		}
	}
}
