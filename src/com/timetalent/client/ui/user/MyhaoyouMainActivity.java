package com.timetalent.client.ui.user;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import android.widget.Toast;

import com.timetalent.client.R;
import com.timetalent.client.service.AppController;
import com.timetalent.client.ui.BaseActivity;
import com.timetalent.client.ui.adapter.DynamicAdapter;
import com.timetalent.client.ui.dynamic.AddDynamicActivity;
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
public class MyhaoyouMainActivity extends TabActivity  implements OnClickListener {
	private AppController controller;
	private TextView main_top_right;
	private ImageButton main_top_left;
	int index;
	TabHost tabHost;

	TabSpec spec1;
	TabSpec spec2;
	TabSpec spec3;
	TabSpec spec4;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_haoyou);
		controller = AppController.getController(this);
		index = this.getIntent().getIntExtra("index", 0);
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
		main_top_left = (ImageButton)this.findViewById(R.id.main_top_left);
		main_top_right = (TextView) this.findViewById(R.id.main_top_right);
		tabHost=getTabHost();
		spec1 = tabHost.newTabSpec("好友");
		spec1.setContent(R.id.tab1);  
		spec1.setIndicator("好友");
		Intent in1=new Intent(this, MyhaoyouActivity.class);
		spec1.setContent(in1);
		
		spec2 = tabHost.newTabSpec("推荐");
		spec2.setContent(R.id.tab2);  
		spec2.setIndicator("推荐");
		Intent in2=new Intent(this, MytuijianActivity.class);
		spec2.setContent(in2);
		
		spec3 = tabHost.newTabSpec("关注");
		spec3.setContent(R.id.tab3);  
		spec3.setIndicator("关注");
		Intent in3=new Intent(this, MyguanzhuActivity.class);
		spec3.setContent(in3);
		
		spec4 = tabHost.newTabSpec("粉丝");
		spec4.setContent(R.id.tab4);  
		spec4.setIndicator("粉丝");
		Intent in4=new Intent(this, MyfansActivity.class);
		spec4.setContent(in4);
		tabHost.addTab(spec1);
		tabHost.addTab(spec2);
		tabHost.addTab(spec3);
		tabHost.addTab(spec4);
		tabHost.setOnTabChangedListener(new OnTabChangeListener() {
			
			@Override
			public void onTabChanged(String tabId) {
//				tabHost.setCurrentTabByTag(tabId);
//				updateTab(tabHost);
				if(tabId.equals("好友")){
		            Toast.makeText(MyhaoyouMainActivity.this, "好友分页", Toast.LENGTH_LONG).show();
		            
		        }
				if(tabId.equals("推荐")){
		            Toast.makeText(MyhaoyouMainActivity.this, "推荐分页", Toast.LENGTH_LONG).show();  
		        }
				if(tabId.equals("关注")){
		            Toast.makeText(MyhaoyouMainActivity.this, "关注分页", Toast.LENGTH_LONG).show();  
		        }
				if(tabId.equals("粉丝")){
		            Toast.makeText(MyhaoyouMainActivity.this, "粉丝分页", Toast.LENGTH_LONG).show();  
		        }
			}
		});
	}
//	private void updateTab(final TabHost tabHost) {
//        for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
//            View view = tabHost.getTabWidget().getChildAt(i);
//            TextView tv;
//            Log.i("test", i+"");
//            switch (i) {
//			case 0:
//				tv= (TextView) tabHost.getTabWidget().getChildAt(0).findViewById(R.id.textView1);
//				break;
//			case 1:
//				tv= (TextView) tabHost.getTabWidget().getChildAt(1).findViewById(R.id.textView2);
//				break;
//			case 2:
//				tv= (TextView) tabHost.getTabWidget().getChildAt(2).findViewById(R.id.textView3);
//				break;
//			case 3:
//				tv= (TextView) tabHost.getTabWidget().getChildAt(3).findViewById(R.id.textView4);
//				break;
//			default:
//				tv= (TextView) tabHost.getTabWidget().getChildAt(0).findViewById(R.id.textView1);
//				break;
//			}
//            
//            if (tabHost.getCurrentTab() == i) {//选中  
//                tv.setTextColor(0XFFFF0000);
//            } else {
//            	tv.setTextColor(0XFF000000);
//            } 
//        } 
//    } 
	/**
	 * 方法描述：TODO
	 * 
	 * @author: why
	 * @time: 2014-10-10 下午6:36:02
	 */
	private void initView() {
	tabHost.setCurrentTab(index);
	((TextView)this.findViewById(R.id.main_top_title)).setText("我的关注");
//	UIUtils.setDrawableLeft(this,main_top_right,R.drawable.d3_06);
	main_top_left.setVisibility(View.VISIBLE);
	main_top_right.setVisibility(View.VISIBLE);
//	UIUtils.setDrawableLeft(this,main_top_left2,R.drawable.d3_03);
	main_top_left.setOnClickListener(this);
	main_top_right.setOnClickListener(this);
	}
	
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.main_top_left:
			finish();
			break;
		case R.id.main_top_right:
			IntentUtil.intent(MyhaoyouMainActivity.this, SearchActivity.class);
			break;
		default:
			break;
		}
	}

}
