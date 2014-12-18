package com.timetalent.client.ui.fragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.R.integer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.timetalent.client.R;
import com.timetalent.client.entities.Feed;
import com.timetalent.client.entities.FeedData;
import com.timetalent.client.entities.LoginData;
import com.timetalent.client.service.AppController;
import com.timetalent.client.ui.adapter.DynamicAdapter;
import com.timetalent.client.ui.dynamic.DynamicAddActivity;
import com.timetalent.client.ui.dynamic.DynamicMyActivity;
import com.timetalent.client.ui.view.PullToRefreshView;
import com.timetalent.client.ui.view.PullToRefreshView.OnFooterRefreshListener;
import com.timetalent.client.ui.view.PullToRefreshView.OnHeaderRefreshListener;
import com.timetalent.common.util.IntentUtil;
import com.timetalent.common.util.PictureUtil;
import com.timetalent.common.util.StringUtil;
import com.timetalent.common.util.ToastUtil;
import com.timetalent.common.util.UIUtils;


/******************************************
 * 类描述： 动态
 * 类名称：DynamicFragment  
 * @version: 1.0
 * @author: why
 * @time: 2014-10-10 下午6:31:03 
 ******************************************/
public class DynamicFragment extends Fragment implements OnClickListener,OnHeaderRefreshListener,OnFooterRefreshListener {
	private View view;
	private AppController controller;
	private TextView main_top_right;
	private ImageView main_top_left;
	private ListView lv_dynamic;
	private PullToRefreshView main_pull_refresh_view;
	private List<Feed> listFeeds = new  ArrayList<Feed>();
	private int pageNum = 1;
	private int  tolalPage = -1;
	private boolean refresh = false;
	
	private DynamicAdapter adapter;
	
	private Handler mHandler = new  Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0:
				FeedData data = (FeedData)controller.getContext().getBusinessData("Dynamic_Data");
				if(refresh == false){
					pageNum = Integer.valueOf(data.getPages().getPage());
					tolalPage =  Integer.valueOf(data.getPages().getNum());
				}
				if(data != null){
					for (Feed feed : data.getLists()) {
						if(!StringUtil.containsFeed(feed,listFeeds)){
							Collections.reverse(feed.getReply());
							if(DynamicAddActivity.ADDFlag){
								listFeeds.add(0,feed);
								DynamicAddActivity.ADDFlag = false;
							}else{
								listFeeds.add(feed);
							}
						}
					}
					
					adapter.notifyDataSetChanged();
				}
				main_pull_refresh_view.onHeaderRefreshComplete();
				main_pull_refresh_view.onFooterRefreshComplete();
				break;
			case 1:
				main_pull_refresh_view.onHeaderRefreshComplete();
				main_pull_refresh_view.onFooterRefreshComplete();
			default:
				break;
			}
		}
	};
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		controller = AppController.getController(getActivity());
		view = inflater.inflate(R.layout.dynamic_fragment, container, false);
		findView();
		initView();
		return view;
	}
	/**
	 * 方法描述：TODO
	 * @author: why
	 * @time: 2014-10-10 下午6:36:00
	 */
	private void findView() {
		lv_dynamic = (ListView)view.findViewById(R.id.lv_dynamic);
		main_top_right = (TextView)view.findViewById(R.id.main_top_right);
		main_top_left = (ImageView)view.findViewById(R.id.main_top_left);
		main_pull_refresh_view = (PullToRefreshView)view.findViewById(R.id.main_pull_refresh_view);
	}

	/**
	 * 方法描述：TODO
	 * @author: why
	 * @time: 2014-10-10 下午6:36:02
	 */
	private void initView() {
		view.findViewById(R.id.main_top_left1).setVisibility(View.GONE);
		((TextView)view.findViewById(R.id.main_top_title)).setText("动态");
		main_top_right.setOnClickListener(this);
		main_pull_refresh_view.setOnHeaderRefreshListener(this);
		main_pull_refresh_view.setOnFooterRefreshListener(this);
		
		// 加载网络图片
		LoginData loginData = (LoginData)controller.getContext().getBusinessData("loginData");
		if(loginData != null){
			ImageLoader.getInstance().displayImage(loginData.getAvatar(), main_top_left,PictureUtil.getCircleOption());
		}
		main_top_left.setOnClickListener(this);
		UIUtils.setDrawableLeft(getActivity(),main_top_right,R.drawable.d3_06);
		adapter = new DynamicAdapter(getActivity(),listFeeds,mHandler);
		lv_dynamic.setAdapter(adapter);
	}

	@Override
	public void onResume() {
		super.onResume();
		dynamic(1);
	}
	
	/**
	  * 方法描述：TODO
	  * @author: wanghy
	  * @time: 2014-11-5 下午9:55:14
	  */
	private void dynamic(final int pageNum) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					controller.dynamicIndex(mHandler,pageNum);
				}
			}).start();
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.main_top_right:
			IntentUtil.intent(getActivity(), DynamicAddActivity.class);
			break;
		case R.id.main_top_left:
			IntentUtil.intent(getActivity(), DynamicMyActivity.class);
			break;
		default:
			break;
		}
	}
	
	@Override
	public void onHeaderRefresh(PullToRefreshView view) {
		refresh = true;
		dynamic(1); 
	}
	
	@Override
	public void onFooterRefresh(PullToRefreshView view) {
		refresh = false;
		int num = pageNum+1;
		if(num > tolalPage){
			main_pull_refresh_view.onFooterRefreshComplete();
			ToastUtil.showToast(getActivity(), "没有更多了", ToastUtil.LENGTH_LONG);
		}else{
			dynamic(num); 
		}
	}
}
