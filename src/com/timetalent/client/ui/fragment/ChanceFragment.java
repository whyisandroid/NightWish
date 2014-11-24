package com.timetalent.client.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;

import com.timetalent.client.R;
import com.timetalent.client.entities.TaskData;
import com.timetalent.client.service.AppController;
import com.timetalent.client.ui.adapter.ChanceAdapter;
import com.timetalent.client.ui.chance.OfferAddActivity;
import com.timetalent.client.ui.chance.OfferDetailActivity;
import com.timetalent.common.util.IntentUtil;
import com.timetalent.common.util.ProgressDialogUtil;


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
	private ImageView main_top_find_right;
	
	private RadioButton rb_chance_hot;
	private RadioButton rb_chance_new;
	private ImageView main_top_find_query;
	
	private ListView lv_chance;
	private TaskData data;
	
	
	
	private Handler mHandler = new  Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0:
				update();
				break;
			default:
				break;
			}
		}
	};
	
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		controller = AppController.getController(getActivity());
		view = inflater.inflate(R.layout.chance_fragment, container, false);
		mContext = getActivity();
		findView();
		initView();
		getData();
		return view;
	}
	

	/**
	 * 方法描述：TODO  
	 * 
	 * @author: why
	 * @time: 2014-10-10 下午6:36:00
	 */
	private void findView() {
		lv_chance = (ListView)view.findViewById(R.id.lv_chance);
		main_top_find_right = (ImageView)view.findViewById(R.id.main_top_find_right);
		rb_chance_hot = (RadioButton)view.findViewById(R.id.rb_chance_hot);
		rb_chance_new = (RadioButton)view.findViewById(R.id.rb_chance_new);
		main_top_find_query = (ImageView)view.findViewById(R.id.main_top_find_query);
	}
	/**
	 * 方法描述：TODO
	 * 
	 * @author: why
	 * @time: 2014-10-10 下午6:36:02
	 */
	private void initView() {
		rb_chance_hot.setOnClickListener(this);
		rb_chance_new.setOnClickListener(this);
		main_top_find_right.setOnClickListener(this);
		main_top_find_query.setOnClickListener(this);
		update();
	}
	
	/**
	  * 方法描述：TODO
	  * @author: wanghy
	  * @time: 2014-11-8 下午3:39:22
	  */
	private void getData() {
		ProgressDialogUtil.showProgressDialog(getActivity(), "稍等…", false);
		new Thread(new Runnable() {
			@Override
			public void run() {
				controller.chanceLists(mHandler);
				ProgressDialogUtil.closeProgressDialog();
			}
		}).start();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.main_top_find_query:
			
			break;
		case R.id.rb_chance_hot:
			controller.getContext().addBusinessData("chance_order", "hot");
			getData();
			break;
		case R.id.rb_chance_new:
			controller.getContext().addBusinessData("chance_order", "new");
			getData();
			break;
		case R.id.main_top_find_right:
			IntentUtil.intent(getActivity(), OfferAddActivity.class);
			break;
		default:
			break;
		}
	}
	
	
	/**
	  * 方法描述：TODO
	  * @author: wanghy
	  * @time: 2014-11-8 下午3:43:32
	  */
	private void update() {
		String type = controller.getContext().getStringData("Login.type");
		if("star".equals(type)){
			main_top_find_right.setVisibility(View.VISIBLE);
		}else{
			main_top_find_right.setVisibility(View.INVISIBLE);
		}
		
		data = (TaskData)controller.getContext().getBusinessData("Task_lists_data");
		if(data != null){
			ChanceAdapter adapter = new ChanceAdapter(getActivity(),data.getLists());
			lv_chance.setAdapter(adapter);
			lv_chance.setOnItemClickListener(chanceItemlistener);
		}
	}
	
	private OnItemClickListener chanceItemlistener = new  OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			controller.getContext().addBusinessData("chance_detail_id", data.getLists().get(arg2).getId());
			show();
		}
	};
	

	/**
	  * 方法描述：TODO
	  * @author: wanghy
	  * @time: 2014-11-8 下午3:39:22
	  */
	private void show() {
		ProgressDialogUtil.showProgressDialog(getActivity(), "稍等…", false);
		new Thread(new Runnable() {
			@Override
			public void run() {
				controller.chanceDetails();
				ProgressDialogUtil.closeProgressDialog();
			}
		}).start();
	}
	
}
