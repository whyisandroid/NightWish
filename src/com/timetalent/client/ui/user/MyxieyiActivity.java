package com.timetalent.client.ui.user;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.timetalent.client.R;
import com.timetalent.client.entities.AppConfigPackage;
import com.timetalent.client.service.AppController;
import com.timetalent.client.ui.BaseActivity;
import com.timetalent.client.ui.adapter.DynamicAdapter;
import com.timetalent.client.ui.adapter.FansAdapter;
import com.timetalent.client.ui.adapter.HaoyouAdapter;
import com.timetalent.client.ui.adapter.SearchBaseAdapter;
import com.timetalent.client.ui.dynamic.DynamicAddActivity;
import com.timetalent.client.ui.near.SearchActivity;
import com.timetalent.common.util.IntentUtil;
import com.timetalent.common.util.UIUtils;


/******************************************
 * 类描述： 动态界面
 * 类名称：NearDongtaiActivity  
 * @version: 1.0
 * @author: why
 * @time: 2014-10-10 下午6:32:12 
 ******************************************/
public class MyxieyiActivity extends BaseActivity implements OnClickListener {
	private AppController controller;
	private TextView main_top_right;
	private ImageButton main_top_left;
	AppConfigPackage data;
	WebView wb;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.yonghuxieyi);
		controller = AppController.getController(this);
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
		main_top_left = (ImageButton) findViewById(R.id.main_top_left);
		wb = (WebView) findViewById(R.id.wb);
		}

	/**
	 * 方法描述：TODO
	 * 
	 * @author: why
	 * @time: 2014-10-10 下午6:36:02
	 */
	private void initView() {
		((TextView)this.findViewById(R.id.main_top_title)).setText("用户协议");
		main_top_left.setOnClickListener(this);
		setvalue();
		new Thread(){
			public void run() {
				controller.myapp_config();
				handler.sendEmptyMessage(1);
			};
		}.start();
		}
	public void setvalue(){
		controller.getContext().addBusinessData("app.name", "app_agreement");
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.main_top_left:
			finish();
			break;
		default:
			break;
		}
	}
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// super.handleMessage(msg);
			switch (msg.what) {
			case 1:
				data = (AppConfigPackage) controller.getContext().getBusinessData("AppConfigdata");
				if(data != null){
//					data.setUrl("http://www.baidu.com");
			        WebSettings webSettings = wb.getSettings();
			        webSettings.setUseWideViewPort(true); 
			        webSettings.setLoadWithOverviewMode(true);
			        wb.setWebViewClient
			        (new WebViewClient(){
			            public boolean shouldOverrideUrlLoading(WebView view, String url) {       
			                view.loadUrl(url);
			                return true;       
			            }
			            public void onProgressChanged(WebView view, int progress) {
			       }
			     });
			         webSettings.setJavaScriptEnabled(true);
					wb.loadUrl(data.getUrl());
//			         wb.loadDataWithBaseURL(null,"<html><body><img src=\"http://wap.yomai.com/test1.gif\" /></body></html>", "text/html",  "utf-8", null);
				}
				break;
			}
		}
	};
}
