package com.timetalent.client.ui.dynamic;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.timetalent.client.R;
import com.timetalent.client.entities.Feed;
import com.timetalent.client.entities.FeedData;
import com.timetalent.client.service.AppController;
import com.timetalent.client.ui.BaseActivity;
import com.timetalent.client.ui.adapter.DynamicAdapter;
import com.timetalent.client.ui.view.PullToRefreshView;
import com.timetalent.client.ui.view.PullToRefreshView.OnFooterRefreshListener;
import com.timetalent.client.ui.view.PullToRefreshView.OnHeaderRefreshListener;
import com.timetalent.common.util.ProgressDialogUtil;
import com.timetalent.common.util.StringUtil;
import com.timetalent.common.util.ToastUtil;


/******************************************
 * 类描述： 其他人的动态
 * 类名称：DynamicMyActivity  
 * @version: 1.0
 * @author: why
 * @time: 2014-10-10 下午6:31:03 
 ******************************************/
public class DynamicOtherActivity extends BaseActivity implements OnClickListener ,OnHeaderRefreshListener,OnFooterRefreshListener{ 
	private AppController controller;
	private ListView lv_dynamic;
	private ImageButton main_top_left;
	
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
				FeedData data = (FeedData)controller.getContext().getBusinessData("Dynamic_Other_Data");
				if(refresh == false){
					pageNum = Integer.valueOf(data.getPages().getPage());
					tolalPage =  Integer.valueOf(data.getPages().getNum());
				}
				if(data != null){
					for (Feed feed : data.getLists()) {
						if(!StringUtil.containsFeed(feed,listFeeds)){
							Collections.reverse(feed.getReply());
							listFeeds.add(feed);
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
		};
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		controller = AppController.getController(this);
		setContentView(R.layout.dynamic_fragment);
		findView();
		initView();
	}
	/**
	 * 方法描述：TODO
	 * 
	 * @author: why
	 * @time: 2014-10-10 下午6:36:00
	 */
	private void findView() {
		lv_dynamic = (ListView)findViewById(R.id.lv_dynamic);
		main_pull_refresh_view = (PullToRefreshView)findViewById(R.id.main_pull_refresh_view);
		main_top_left = (ImageButton)findViewById(R.id.main_top_left1);
	}

	/**
	 * 方法描述：TODO
	 * 
	 * @author: why
	 * @time: 2014-10-10 下午6:36:02
	 */
	private void initView() {
		findViewById(R.id.main_top_left).setVisibility(View.GONE);
		main_top_left.setImageResource(R.drawable.btn_gobackl);
		main_top_left.setVisibility(View.VISIBLE);
//		UIUtils.setDrawableLeft(this,main_top_left2,R.drawable.d3_03);
		main_top_left.setOnClickListener(this);
		String name = getIntent().getExtras().getString("userName");
		if(TextUtils.isEmpty(name)){
			((TextView)findViewById(R.id.main_top_title)).setText("动态");
		}else{
			((TextView)findViewById(R.id.main_top_title)).setText(name+"的动态");
		}
		
		main_pull_refresh_view.setOnHeaderRefreshListener(this);
		main_pull_refresh_view.setOnFooterRefreshListener(this);
		adapter = new DynamicAdapter(this,listFeeds,mHandler);
		lv_dynamic.setAdapter(adapter);
		dynamic(1);
	}
	
	
	@Override
	public void onResume() {
		super.onResume();
	}
	
	/**
	  * 方法描述：TODO
	  * @author: wanghy
	  * @time: 2014-11-5 下午9:55:14
	  */
	private void dynamic(final int num) {
			ProgressDialogUtil.showProgressDialog(this, "稍等…", false);
			new Thread(new Runnable() {
				@Override
				public void run() {
					controller.dynamicWho(mHandler,num);
					ProgressDialogUtil.closeProgressDialog();
				}
			}).start();
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.main_top_left1:
			this.finish();
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
			ToastUtil.showToast(this, "没有更多了", ToastUtil.LENGTH_LONG);
		}else{
			dynamic(num); 
		}
	}
}
