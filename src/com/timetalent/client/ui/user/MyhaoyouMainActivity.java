package com.timetalent.client.ui.user;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
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
public class MyhaoyouMainActivity extends TabActivity  implements OnClickListener,GestureDetector.OnDoubleTapListener, android.view.GestureDetector.OnGestureListener {
	private AppController controller;
	private TextView main_top_right;
	private ImageButton main_top_left;
	int index;
	TabHost tabHost;
	private GestureDetector mGestureDetector;
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
		mGestureDetector = new GestureDetector(this);
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
					index = 0;
		        }
				if(tabId.equals("推荐")){
					index = 1;
		        }
				if(tabId.equals("关注")){
					index = 2;
		        }
				if(tabId.equals("粉丝")){
					index = 3;
		        }
			}
		});
	}
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
	main_top_right.setText("添加好友");
//	UIUtils.setDrawableLeft(this,main_top_left2,R.drawable.d3_03);
	main_top_left.setOnClickListener(this);
	main_top_right.setOnClickListener(this);
	}
	
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.main_top_left:
			finish();
		case R.id.main_top_right:
			IntentUtil.intent(MyhaoyouMainActivity.this, SearchActivity.class);
			break;
		default:
			break;
		}
	}
	
	/* (non-Javadoc)
	 * @see android.view.GestureDetector.OnGestureListener#onDown(android.view.MotionEvent)
	 */
	@Override
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/* (non-Javadoc)
	 * @see android.view.GestureDetector.OnGestureListener#onFling(android.view.MotionEvent, android.view.MotionEvent, float, float)
	 */
	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
        // TODO Auto-generated method stub  
        if(e1.getX() > e2.getX() && Math.abs(e1.getX() - e2.getX())>200) {//向左滑动  
            index++;
       }else if(e1.getX() < e2.getX()&& Math.abs(e1.getX() - e2.getX())>200) {//向右滑动  
    	   index--;
       }else {     
           return false;     
       }
        switch (index%4) {
		case 0:
			tabHost.setCurrentTabByTag("好友");
			break;
		case 1:
			tabHost.setCurrentTabByTag("推荐");
			break;
		case 2:
			tabHost.setCurrentTabByTag("关注");
			break;
		case 3:
			tabHost.setCurrentTabByTag("粉丝");
			break;
		default:
			break;
		}
       return true;  }
	
	/* (non-Javadoc)
	 * @see android.view.GestureDetector.OnGestureListener#onLongPress(android.view.MotionEvent)
	 */
	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	/* (non-Javadoc)
	 * @see android.view.GestureDetector.OnGestureListener#onScroll(android.view.MotionEvent, android.view.MotionEvent, float, float)
	 */
	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/* (non-Javadoc)
	 * @see android.view.GestureDetector.OnGestureListener#onShowPress(android.view.MotionEvent)
	 */
	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	/* (non-Javadoc)
	 * @see android.view.GestureDetector.OnGestureListener#onSingleTapUp(android.view.MotionEvent)
	 */
	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/* (non-Javadoc)
	 * @see android.view.GestureDetector.OnDoubleTapListener#onDoubleTap(android.view.MotionEvent)
	 */
	@Override
	public boolean onDoubleTap(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/* (non-Javadoc)
	 * @see android.view.GestureDetector.OnDoubleTapListener#onDoubleTapEvent(android.view.MotionEvent)
	 */
	@Override
	public boolean onDoubleTapEvent(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/* (non-Javadoc)
	 * @see android.view.GestureDetector.OnDoubleTapListener#onSingleTapConfirmed(android.view.MotionEvent)
	 */
	@Override
	public boolean onSingleTapConfirmed(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
    public boolean dispatchTouchEvent(MotionEvent ev){
            super.dispatchTouchEvent(ev);
            return mGestureDetector.onTouchEvent(ev);
    }
}
