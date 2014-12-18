package com.timetalent.client.ui.fragment;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;

import com.timetalent.client.R;
import com.timetalent.client.entities.Feed;
import com.timetalent.client.entities.Task;
import com.timetalent.client.entities.TaskData;
import com.timetalent.client.service.AppController;
import com.timetalent.client.ui.adapter.ChanceAdapter;
import com.timetalent.client.ui.chance.OfferAddActivity;
import com.timetalent.client.ui.dynamic.DynamicAddActivity;
import com.timetalent.client.ui.view.PullToRefreshView;
import com.timetalent.client.ui.view.PullToRefreshView.OnFooterRefreshListener;
import com.timetalent.client.ui.view.PullToRefreshView.OnHeaderRefreshListener;
import com.timetalent.common.util.IntentUtil;
import com.timetalent.common.util.ProgressDialogUtil;
import com.timetalent.common.util.StringUtil;
import com.timetalent.common.util.ToastUtil;


/******************************************
 * 类描述： 机会
 * 类名称：ChanceFragment  
 * @version: 1.0
 * @author: why
 * @time: 2014-10-10 下午6:31:43 
 ******************************************/
public class ChanceFragment extends Fragment implements OnClickListener ,OnHeaderRefreshListener,OnFooterRefreshListener {
	private View view;
	private Context mContext;
	private AppController controller;
	private ImageView main_top_find_right;
	private Button main_top_find_right2;
	
	private RadioButton rb_chance_hot;
	private RadioButton rb_chance_new;
	private ImageView main_top_find_query;
	private EditText main_top_find_message;
	
	private ListView lv_chance;
	private TaskData data;
	
	private PullToRefreshView main_pull_refresh_view;
	private List<Task> listTasks = new  ArrayList<Task>();
	private int pageNum = 1;
	private int  tolalPage = -1;
	private boolean refresh = false;
	
	private ChanceAdapter adapter;
	
	
	
	private Handler mHandler = new  Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0:
				update();
				main_pull_refresh_view.onHeaderRefreshComplete();
				main_pull_refresh_view.onFooterRefreshComplete();
				break;
			case 1:
				main_pull_refresh_view.onHeaderRefreshComplete();
				main_pull_refresh_view.onFooterRefreshComplete();
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
		main_top_find_message = (EditText)view.findViewById(R.id.main_top_find_message);
		main_top_find_right2 = (Button)view.findViewById(R.id.main_top_find_right2);
		main_pull_refresh_view = (PullToRefreshView)view.findViewById(R.id.main_pull_refresh_view);
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
		main_top_find_right2.setOnClickListener(this);
		main_pull_refresh_view.setOnHeaderRefreshListener(this);
		main_pull_refresh_view.setOnFooterRefreshListener(this);
		lv_chance.setOnItemClickListener(chanceItemlistener);
		update();
	}
	
	
	@Override
	public void onResume() {
		super.onResume();
		getData(1);
	}
	
	/**
	  * 方法描述：TODO
	  * @author: wanghy
	  * @time: 2014-11-8 下午3:39:22
	  */
	private void getData(final int num) {
	//	ProgressDialogUtil.showProgressDialog(getActivity(), "正在通信中…", false);
		new Thread(new Runnable() {
			@Override
			public void run() {
				controller.chanceLists(mHandler,num);
			//	ProgressDialogUtil.closeProgressDialog();
			}
		}).start();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.main_top_find_right2:
		case R.id.main_top_find_query:
			if(invaild()){
				OfferAddActivity.ADDFlag = false;
				refresh = false;
				listTasks.clear();
				getData(1);
			}
			break;
		case R.id.rb_chance_hot:
			controller.getContext().addBusinessData("chance_search","");
			controller.getContext().addBusinessData("chance_order", "hot");
			refresh = false;
			listTasks.clear();
			OfferAddActivity.ADDFlag = false;
			getData(1);
			break;
		case R.id.rb_chance_new:
			controller.getContext().addBusinessData("chance_search","");
			controller.getContext().addBusinessData("chance_order", "new");
			refresh = false;
			OfferAddActivity.ADDFlag = false;
			listTasks.clear();
			getData(1);
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
		if("scout".equals(type)){
			main_top_find_right.setVisibility(View.VISIBLE);
			main_top_find_right2.setVisibility(View.GONE);
		}else{
			main_top_find_right.setVisibility(View.INVISIBLE);
			main_top_find_right2.setVisibility(View.VISIBLE);
		}
		data = (TaskData)controller.getContext().getBusinessData("Task_lists_data");
		if(data == null){
			return;
		}
		for (Task task : data.getLists()) {
			if(!StringUtil.containsTask(task,listTasks)){
				if(OfferAddActivity.ADDFlag){
					listTasks.add(0,task);
				}else{
					listTasks.add(task);
				}
			}
		}
		if(refresh){
			adapter.notifyDataSetChanged();
		}else{
				adapter = new ChanceAdapter(getActivity(),listTasks);
				lv_chance.setAdapter(adapter);
			}
	}
	
	private OnItemClickListener chanceItemlistener = new  OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			controller.getContext().addBusinessData("chance_detail_id", listTasks.get(arg2).getId());
			show();
		}
	};
	/**
	  * 方法描述：TODO
	  * @return
	  * @author: why
	  * @time: 2014-10-21 上午11:17:11
	  */
	private boolean invaild() {
		String message = main_top_find_message.getText().toString().trim();
		if(TextUtils.isEmpty(message)){
			ToastUtil.showToast(getActivity(), "查询内容 不能为空", ToastUtil.LENGTH_LONG);
			return false;
		}else{
			controller.getContext().addBusinessData("chance_search",message);
		}
		return true;
	}

	/**
	  * 方法描述：TODO
	  * @author: wanghy
	  * @time: 2014-11-8 下午3:39:22
	  */
	private void show() {
		ProgressDialogUtil.showProgressDialog(getActivity(), "正在通信中…", false);
		new Thread(new Runnable() {
			@Override
			public void run() {
				controller.chanceDetails();
				ProgressDialogUtil.closeProgressDialog();
			}  
		}).start();
	}
	
	@Override
	public void onHeaderRefresh(PullToRefreshView view) {
		OfferAddActivity.ADDFlag = true;
		refresh = true;
		getData(1); 
	}
	
	@Override
	public void onFooterRefresh(PullToRefreshView view) {
		OfferAddActivity.ADDFlag = false;
		refresh = true;
		int num = pageNum+1;
		if(num > tolalPage){
			main_pull_refresh_view.onFooterRefreshComplete();
			ToastUtil.showToast(getActivity(), "没有更多了", ToastUtil.LENGTH_LONG);
		}else{
			getData(num); 
		}
	}
}
