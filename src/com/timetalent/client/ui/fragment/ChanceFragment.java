package com.timetalent.client.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ListView;

import com.timetalent.client.R;
import com.timetalent.client.service.AppController;
import com.timetalent.client.ui.adapter.ChanceAdapter;


/******************************************
 * 类描述： 机会
 * 类名称：ChanceFragment  
 * @version: 1.0
 * @author: why
 * @time: 2014-10-10 下午6:31:43 
 ******************************************/
public class ChanceFragment extends Fragment implements OnClickListener {
	private View view;
	private Context mContext;
	private AppController controller;
	
	private ListView lv_chance;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		controller = AppController.getController(getActivity());
		view = inflater.inflate(R.layout.chance_fragment, container, false);
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
		ChanceAdapter adapter = new ChanceAdapter(getActivity());
		lv_chance.setAdapter(adapter);
	}

	/**
	 * 方法描述：TODO  
	 * 
	 * @author: why
	 * @time: 2014-10-10 下午6:36:00
	 */
	private void findView() {
		lv_chance = (ListView)view.findViewById(R.id.lv_chance);
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
