package com.timetalent.client.ui.user;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.timetalent.client.R;
import com.timetalent.client.service.AppController;
import com.timetalent.client.ui.BaseActivity;
import com.timetalent.client.ui.GuideActivity;
import com.timetalent.client.ui.MainFragmentActivity;
import com.timetalent.client.ui.adapter.ZuopinBaseAdapter;
import com.timetalent.client.ui.near.NearDongtaiActivity;
import com.timetalent.client.ui.near.PictureActivity;
import com.timetalent.client.ui.near.XingtanActivity;
import com.timetalent.common.util.IntentUtil;


/******************************************
 * 类描述： 登录界面
 * 类名称：LoginActivity  
 * @version: 1.0
 * @author: why
 * @time: 2014-10-10 下午6:32:12 
 ******************************************/
public class FansziliaoActivity extends BaseActivity implements OnClickListener,GestureDetector.OnDoubleTapListener, android.view.GestureDetector.OnGestureListener {
	private AppController controller;
	private ViewFlipper vfpics;
	private ImageView imgpic1;
	private ImageView imgpic2;
	private ImageView imgpic3;
	private ImageView imgpic4;
	private ImageView imgpic5;
	private ImageView imgpic6;
	private ImageView imgpic7;
	private ImageView imgpic8;
	private ImageView imgtab1;
	private ImageView imgtab2;
	private ImageView imgtab3;
	private GestureDetector mGestureDetector;
	int index = 0;
	private LinearLayout ldongtai;
	private TextView main_top_right;
	private ImageButton main_top_left;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_fansziliao);
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
		mGestureDetector = new GestureDetector(this);
		vfpics = (ViewFlipper) findViewById(R.id.vfpics);
		imgpic1 = (ImageView) vfpics.getCurrentView().findViewById(R.id.img1);
		imgpic2 = (ImageView) vfpics.getCurrentView().findViewById(R.id.img2);
		imgpic3 = (ImageView) vfpics.getCurrentView().findViewById(R.id.img3);
		imgpic4 = (ImageView) vfpics.getCurrentView().findViewById(R.id.img4);
		imgpic5 = (ImageView) vfpics.getCurrentView().findViewById(R.id.img5);
		imgpic6 = (ImageView) vfpics.getCurrentView().findViewById(R.id.img6);
		imgpic7 = (ImageView) vfpics.getCurrentView().findViewById(R.id.img7);
		imgpic8 = (ImageView) vfpics.getCurrentView().findViewById(R.id.img8);
		imgtab1 = (ImageView) findViewById(R.id.imgtab1);
		imgtab2 = (ImageView) findViewById(R.id.imgtab2);
		imgtab3 = (ImageView) findViewById(R.id.imgtab3);
		
		ldongtai = (LinearLayout) findViewById(R.id.lneardongtai);
		main_top_right = (TextView)this.findViewById(R.id.main_top_right);
		main_top_left = (ImageButton)this.findViewById(R.id.main_top_left);
	}

	/**
	 * 方法描述：TODO
	 * 
	 * @author: why
	 * @time: 2014-10-10 下午6:36:02
	 */
	private void initView() {
		((TextView)this.findViewById(R.id.main_top_title)).setText("吴沐熙vicky");
		((TextView)this.findViewById(R.id.main_top_right)).setText("编辑");
		main_top_right.setVisibility(main_top_right.VISIBLE);
//		UIUtils.setDrawableLeft(this,main_top_right,R.drawable.d3_06);
//		this.findViewById(R.id.main_top_left).setVisibility(View.GONE);
//		UIUtils.setDrawableLeft(this,main_top_left2,R.drawable.d3_03);
		main_top_right.setOnClickListener(this);
		ldongtai.setOnClickListener(this);
		
		imgpic1.setOnClickListener(this);
		imgpic2.setOnClickListener(this);
		imgpic3.setOnClickListener(this);
		imgpic4.setOnClickListener(this);
		imgpic5.setOnClickListener(this);
		imgpic6.setOnClickListener(this);
		imgpic7.setOnClickListener(this);
		imgpic8.setOnClickListener(this);
	}
	
	/**
	 * 重新计算listview高度
	  * 方法描述：TODO
	  * @param listView
	  * @author: Administrator
	  * @time: 2014-10-13 下午4:19:11
	 */
	public void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();   
        if (listAdapter == null) {  
            // pre-condition  
            return;  
        }  
  
        int totalHeight = 0;  
        for (int i = 0; i < listAdapter.getCount(); i++) {  
            View listItem = listAdapter.getView(i, null, listView);  
            listItem.measure(0, 0);  
            totalHeight += listItem.getMeasuredHeight();  
        }  
  
        ViewGroup.LayoutParams params = listView.getLayoutParams();  
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));  
        listView.setLayoutParams(params);  
    }
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_login_next:
			break;
		case R.id.lneardongtai:
			IntentUtil.intent(FansziliaoActivity.this, MyDongtaiActivity.class);
			break;
		case R.id.main_top_left:
			finish();
			break;
		case R.id.main_top_right:
			IntentUtil.intent(FansziliaoActivity.this, FansziliaobianjiActivity.class);
			break;
		case R.id.img1:
			IntentUtil.intent(FansziliaoActivity.this, PictureActivity.class);
			break;
		case R.id.img2:
			IntentUtil.intent(FansziliaoActivity.this, PictureActivity.class);
			break;
		case R.id.img3:
			IntentUtil.intent(FansziliaoActivity.this, PictureActivity.class);
			break;
		case R.id.img4:
			IntentUtil.intent(FansziliaoActivity.this, PictureActivity.class);
			break;
		case R.id.img5:
			IntentUtil.intent(FansziliaoActivity.this, PictureActivity.class);
			break;
		case R.id.img6:
			IntentUtil.intent(FansziliaoActivity.this, PictureActivity.class);
			break;
		case R.id.img7:
			IntentUtil.intent(FansziliaoActivity.this, PictureActivity.class);
			break;
		case R.id.img8:
			IntentUtil.intent(FansziliaoActivity.this, PictureActivity.class);
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
        if(e1.getX() > e2.getX() && Math.abs(e1.getY())<450) {//向左滑动  
            vfpics.setInAnimation(getApplicationContext(), R.anim.push_left_in);     
            vfpics.setOutAnimation(getApplicationContext(), R.anim.push_left_out);     
            vfpics.showNext();
            index++;
       }else if(e1.getX() < e2.getX()&& Math.abs(e1.getY())<450) {//向右滑动  
    	   vfpics.setInAnimation(getApplicationContext(), R.anim.push_right_in);     
    	   vfpics.setOutAnimation(getApplicationContext(), R.anim.push_right_out);
    	   vfpics.showPrevious();
    	   index--;
       }else {     
           return false;     
       }
        switch (index%3) {
		case 0:
			imgtab1.setImageResource(R.drawable.f10_26);
			imgtab2.setImageResource(R.drawable.f10_24);
			imgtab3.setImageResource(R.drawable.f10_24);
			break;
		case 1:
			imgtab1.setImageResource(R.drawable.f10_24);
			imgtab2.setImageResource(R.drawable.f10_26);
			imgtab3.setImageResource(R.drawable.f10_24);
			break;
		case 2:
			imgtab1.setImageResource(R.drawable.f10_24);
			imgtab2.setImageResource(R.drawable.f10_24);
			imgtab3.setImageResource(R.drawable.f10_26);
			break;
		default:
			break;
		}
        findView();
		initView();
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