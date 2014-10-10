package com.timetalent.client.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;

import com.timetalent.client.R;
import com.timetalent.client.service.AppController;


/******************************************
 * 类描述： 动态
 * 类名称：DynamicFragment  
 * @version: 1.0
 * @author: why
 * @time: 2014-10-10 下午6:31:03 
 ******************************************/
public class DynamicFragment extends Fragment implements OnClickListener {
	private View view;
	private Context mContext;
	private AppController controller;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		controller = AppController.getController(getActivity());
		view = inflater.inflate(R.layout.dynamic_fragment, container, false);
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
		// TODO Auto-generated method stub

	}

	/**
	 * 方法描述：TODO
	 * 
	 * @author: why
	 * @time: 2014-10-10 下午6:36:00
	 */
	private void findView() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case 0:

			break;
		default:
			break;
		}
	}
}
