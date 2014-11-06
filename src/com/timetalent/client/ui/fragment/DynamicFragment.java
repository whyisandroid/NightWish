package com.timetalent.client.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.timetalent.client.R;
import com.timetalent.client.entities.FeedData;
import com.timetalent.client.service.AppController;
import com.timetalent.client.ui.adapter.DynamicAdapter;
import com.timetalent.client.ui.dynamic.DynamicAddActivity;
import com.timetalent.client.ui.dynamic.DynamicMyActivity;
import com.timetalent.common.util.IntentUtil;
import com.timetalent.common.util.ProgressDialogUtil;
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
	private AppController controller;
	private TextView main_top_right;
	private TextView main_top_left2;
	private ImageButton main_top_left;
	private ListView lv_dynamic;
	
	private Handler mHandler = new  Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0:
				FeedData data = (FeedData)controller.getContext().getBusinessData("Dynamic_Data");
				if(data != null){
					DynamicAdapter adapter = new DynamicAdapter(getActivity(),data.getLists());
					lv_dynamic.setAdapter(adapter);
				}
				break;

			default:
				break;
			}
		};
	};
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		controller = AppController.getController(getActivity());
		view = inflater.inflate(R.layout.dynamic_fragment, container, false);
		getActivity();
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
		FeedData data = (FeedData)controller.getContext().getBusinessData("Dynamic_Data");
		
		if(data != null){
			DynamicAdapter adapter = new DynamicAdapter(getActivity(),data.getLists());
			lv_dynamic.setAdapter(adapter);
		}
	}
	
	
	/* (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onResume()
	 */
	@Override
	public void onResume() {
		super.onResume();
		myDynamic();
	}
	
	/**
	  * 方法描述：TODO
	  * @author: wanghy
	  * @time: 2014-11-5 下午9:55:14
	  */
	private void myDynamic() {
		/**
		  * 方法描述：TODO
		  * @author: why
		  * @time: 2014-10-21 上午11:17:14
		  */
			ProgressDialogUtil.showProgressDialog(getActivity(), "登录中…", false);
			new Thread(new Runnable() {
				@Override
				public void run() {
					controller.dynamicMy(mHandler);
					ProgressDialogUtil.closeProgressDialog();
				}
			}).start();
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
