package com.timetalent.client.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.timetalent.client.R;
import com.timetalent.client.service.AppController;
import com.timetalent.client.ui.adapter.DynamicAdapter;
import com.timetalent.client.ui.dynamic.DynamicAddActivity;
import com.timetalent.client.ui.dynamic.DynamicMyActivity;
import com.timetalent.common.util.IntentUtil;
import com.timetalent.common.util.UIUtils;


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
	private TextView main_top_right;
	private TextView main_top_left2;
	private ImageButton main_top_left;
	private ListView lv_dynamic;
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
	 * @time: 2014-10-10 下午6:36:00
	 */
	private void findView() {
		lv_dynamic = (ListView)view.findViewById(R.id.lv_dynamic);
		main_top_right = (TextView)view.findViewById(R.id.main_top_right);
		main_top_left2 = (TextView)view.findViewById(R.id.main_top_left2);
		main_top_left = (ImageButton)view.findViewById(R.id.main_top_left);
	}

	/**
	 * 方法描述：TODO
	 * 
	 * @author: why
	 * @time: 2014-10-10 下午6:36:02
	 */
	private void initView() {
		((TextView)view.findViewById(R.id.main_top_title)).setText("动态");
		UIUtils.setDrawableLeft(getActivity(),main_top_right,R.drawable.d3_06);
		main_top_left.setVisibility(View.GONE);
		UIUtils.setDrawableLeft(getActivity(),main_top_left2,R.drawable.d3_03);
		main_top_right.setOnClickListener(this);
		main_top_left.setOnClickListener(this);
		main_top_left2.setOnClickListener(this);
		
		DynamicAdapter adapter = new DynamicAdapter(getActivity());
		lv_dynamic.setAdapter(adapter);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.main_top_right:
			IntentUtil.intent(getActivity(), DynamicAddActivity.class);
			break;
		case R.id.main_top_left2:
			IntentUtil.intent(getActivity(), DynamicMyActivity.class);
			break;
		default:
			break;
		}
	}
}
