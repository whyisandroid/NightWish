package com.timetalent.client.ui.user;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.timetalent.client.R;
import com.timetalent.client.entities.Baseinfopackage;
import com.timetalent.client.entities.Userinfopackage;
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
public class YirenziliaoActivity extends BaseActivity implements OnClickListener,GestureDetector.OnDoubleTapListener, android.view.GestureDetector.OnGestureListener {
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
	TextView tvage1;
	TextView tvxingzuo;
	TextView tvdizhi;
	TextView tvtime;
	TextView tvname;
	TextView tvnickname;
	TextView tvage;
	TextView tvxingzuo1;
	TextView tvzhiye;
	TextView tvjiaxiang;
	TextView tvheight;
	TextView tvsanwei;
	private GestureDetector mGestureDetector;
	int index = 0;
	private LinearLayout ldongtai;
	private ListView lzuopin;
	private TextView main_top_right;
	private ImageButton main_top_left;
	public int screenw = 0;
	public float density = 1.0f;
	Baseinfopackage u;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_yirenziliao);
		controller = AppController.getController(this);
		DisplayMetrics dm = new DisplayMetrics();
		this.getWindowManager().getDefaultDisplay().getMetrics(dm);
		screenw = dm.widthPixels;
		density = dm.density;
		findView();
		new Thread(){
			public void run() {
				controller.mybaseinfo();
				handler.sendEmptyMessage(1);
			};
		}.start();
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
		
		lzuopin = (ListView) findViewById(R.id.lzuopin);
		ldongtai = (LinearLayout) findViewById(R.id.lneardongtai);
		main_top_right = (TextView)this.findViewById(R.id.main_top_right);
		main_top_left = (ImageButton)this.findViewById(R.id.main_top_left);
		
		 tvage1 = (TextView)this.findViewById(R.id.tvage1);
		 tvxingzuo = (TextView)this.findViewById(R.id.tvxingzuo);
		 tvdizhi = (TextView)this.findViewById(R.id.tvdizhi);
		 tvtime = (TextView)this.findViewById(R.id.tvtime);
		 tvname = (TextView)this.findViewById(R.id.tvname);
		 tvnickname = (TextView)this.findViewById(R.id.tvnickname);
		 tvage = (TextView)this.findViewById(R.id.tvage);
		 tvxingzuo1 = (TextView)this.findViewById(R.id.tvxingzuo1);
		 tvzhiye = (TextView)this.findViewById(R.id.tvzhiye);
		 tvjiaxiang = (TextView)this.findViewById(R.id.tvjiaxiang);
		 tvheight = (TextView)this.findViewById(R.id.tvheight);
		 tvsanwei = (TextView)this.findViewById(R.id.tvsanwei);
	}

	/**
	 * 方法描述：TODO
	 * 
	 * @author: why
	 * @time: 2014-10-10 下午6:36:02
	 */
	private void initView() {
		setvalue();
		((TextView)this.findViewById(R.id.main_top_title)).setText("吴沐熙vicky");
		((TextView)this.findViewById(R.id.main_top_right)).setText("编辑");
		main_top_right.setVisibility(main_top_right.VISIBLE);
//		UIUtils.setDrawableLeft(this,main_top_right,R.drawable.d3_06);
//		this.findViewById(R.id.main_top_left).setVisibility(View.GONE);
//		UIUtils.setDrawableLeft(this,main_top_left2,R.drawable.d3_03);
		main_top_right.setOnClickListener(this);
		lzuopin.setAdapter(new ZuopinBaseAdapter(YirenziliaoActivity.this));
		setListViewHeightBasedOnChildren(lzuopin);
		ldongtai.setOnClickListener(this);
		
		imgpic1.setOnClickListener(this);
		imgpic2.setOnClickListener(this);
		imgpic3.setOnClickListener(this);
		imgpic4.setOnClickListener(this);
		imgpic5.setOnClickListener(this);
		imgpic6.setOnClickListener(this);
		imgpic7.setOnClickListener(this);
		imgpic8.setOnClickListener(this);
		LayoutParams  p1 = imgpic1.getLayoutParams();
		p1.height = (int)(screenw/4-8*density);
		imgpic1.setLayoutParams(p1);
		
		LayoutParams  p2 = imgpic2.getLayoutParams();
		p2.height = (int)(screenw/4-8*density);
		imgpic2.setLayoutParams(p2);
		
		LayoutParams  p3 = imgpic3.getLayoutParams();
		p3.height = (int)(screenw/4-8*density);
		imgpic3.setLayoutParams(p3);
		
		LayoutParams  p4 = imgpic4.getLayoutParams();
		p4.height = (int)(screenw/4-8*density);
		imgpic4.setLayoutParams(p4);
		
		LayoutParams  p5 = imgpic5.getLayoutParams();
		p5.height = (int)(screenw/4-8*density);
		imgpic5.setLayoutParams(p5);
		
		LayoutParams  p6 = imgpic6.getLayoutParams();
		p6.height = (int)(screenw/4-8*density);
		imgpic6.setLayoutParams(p6);
		
		LayoutParams  p7 = imgpic7.getLayoutParams();
		p7.height = (int)(screenw/4-8*density);
		imgpic7.setLayoutParams(p7);
		
		LayoutParams  p8 = imgpic8.getLayoutParams();
		p8.height = (int)(screenw/4-8*density);
		imgpic8.setLayoutParams(p8);
		tvage1.setText(u.getAge());
		 tvxingzuo.setText(u.getConstella());
		 tvdizhi.setText(u.getProvince());
		 tvtime.setText(u.getLast_time());
		 tvname.setText(u.getRealname());
		 tvnickname.setText(u.getNickname());
		 tvage.setText(u.getAge());
		 tvxingzuo1.setText(u.getConstella());
		 tvzhiye.setText(u.getMajor());
		 tvjiaxiang.setText(u.getProvince());
		 tvheight.setText(u.getAge());
		 tvsanwei.setText(u.getAge());
	}
	void setvalue(){
		
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
			IntentUtil.intent(YirenziliaoActivity.this, MyDongtaiActivity.class);
			break;
		case R.id.main_top_left:
			finish();
			break;
		case R.id.main_top_right:
			IntentUtil.intent(YirenziliaoActivity.this, YirenziliaobianjiActivity.class);
			break;
		case R.id.img1:
			IntentUtil.intent(YirenziliaoActivity.this, PictureActivity.class);
			break;
		case R.id.img2:
			IntentUtil.intent(YirenziliaoActivity.this, PictureActivity.class);
			break;
		case R.id.img3:
			IntentUtil.intent(YirenziliaoActivity.this, PictureActivity.class);
			break;
		case R.id.img4:
			IntentUtil.intent(YirenziliaoActivity.this, PictureActivity.class);
			break;
		case R.id.img5:
			IntentUtil.intent(YirenziliaoActivity.this, PictureActivity.class);
			break;
		case R.id.img6:
			IntentUtil.intent(YirenziliaoActivity.this, PictureActivity.class);
			break;
		case R.id.img7:
			IntentUtil.intent(YirenziliaoActivity.this, PictureActivity.class);
			break;
		case R.id.img8:
			IntentUtil.intent(YirenziliaoActivity.this, PictureActivity.class);
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
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// super.handleMessage(msg);
			switch (msg.what) {
			case 1:
				u = (Baseinfopackage) controller.getContext().getBusinessData("BaseinfoData");
				if(u != null){
					initView();
				}
				break;
			}
		}
	};
}
