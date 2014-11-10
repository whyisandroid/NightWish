package com.timetalent.client.ui.near;

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
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.timetalent.client.R;
import com.timetalent.client.entities.Userinfopackage;
import com.timetalent.client.service.AppController;
import com.timetalent.client.ui.BaseActivity;
import com.timetalent.client.ui.adapter.NearBaseAdapter;
import com.timetalent.client.ui.adapter.ZuopinBaseAdapter;
import com.timetalent.client.ui.chance.WorkAppointmentActivity;
import com.timetalent.client.ui.dialog.IOSStyleDialog;
import com.timetalent.client.ui.dialog.IOSStyleListDialog;
import com.timetalent.client.ui.message.MessageChatActivity;
import com.timetalent.common.util.IntentUtil;


/******************************************
 * 类描述： 登录界面
 * 类名称：LoginActivity  
 * @version: 1.0
 * @author: why
 * @time: 2014-10-10 下午6:32:12 
 ******************************************/
public class YirenActivity extends BaseActivity implements OnClickListener,GestureDetector.OnDoubleTapListener, android.view.GestureDetector.OnGestureListener{
	private AppController controller;
	private LinearLayout ldongtai;
	private ViewFlipper vfpics;
	private ListView lzuopin;
	ZuopinBaseAdapter adapter;
	private ImageView imgpic1;
	private ImageView imgpic2;
	private ImageView imgpic3;
	private ImageView imgpic4;
	private ImageView imgpic5;
	private ImageView imgpic6;
	private ImageView imgpic7;
	private ImageView imgpic8;
	private TextView main_top_right;
	private ImageButton main_top_left;
	private ImageView img1;
	private ImageView img2;
	private ImageView img3;
	private ImageView imgtab1;
	private ImageView imgtab2;
	private ImageView imgtab3;
	private GestureDetector mGestureDetector;
	TextView tvxingzuo;
	TextView tvdizhi;
	TextView tvname;
	TextView tvnickname;
	TextView tvage;
	TextView tvxingzuo1;
	TextView tvzhiye;
	TextView tvjiaxiang;
	TextView tvheight;
	TextView tvsanwei;
	int index = 0;
	String userid = "1";
	public int screenw = 0;
	public float density = 1.0f;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.near_yirenxiangqing);
		userid = getIntent().getStringExtra("userid");
		controller = AppController.getController(this);
		DisplayMetrics dm = new DisplayMetrics();
		this.getWindowManager().getDefaultDisplay().getMetrics(dm);
		screenw = dm.widthPixels;
		density = dm.density;
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
		lzuopin = (ListView) findViewById(R.id.lzuopin);
		ldongtai = (LinearLayout) findViewById(R.id.lneardongtai);
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
		img1 = (ImageView) findViewById(R.id.imgduihua);
		img2 = (ImageView) findViewById(R.id.imgguanzhu);
		img3 = (ImageView) findViewById(R.id.imgyuyue);
		
		 tvxingzuo = (TextView) findViewById(R.id.tvxingzuo);
		 tvdizhi = (TextView) findViewById(R.id.tvdizhi);
		 tvname = (TextView) findViewById(R.id.tvname);
		 tvnickname = (TextView) findViewById(R.id.tvnickname);
		 tvage = (TextView) findViewById(R.id.tvage);
		 tvxingzuo1 = (TextView) findViewById(R.id.tvxingzuo1);
		 tvzhiye = (TextView) findViewById(R.id.tvzhiye);
		 tvjiaxiang = (TextView) findViewById(R.id.tvjiaxiang);
		 tvheight = (TextView) findViewById(R.id.tvheight);
		 tvsanwei = (TextView) findViewById(R.id.tvsanwei);
	}

	/**
	 * 方法描述：TODO
	 * 
	 * @author: why
	 * @time: 2014-10-10 下午6:36:02
	 */
	private void initView() {
		setvalue();
		new Thread(){
			public void run() {
				controller.userinfo();
				handler.sendEmptyMessage(1);
			};
		}.start();
		((TextView)this.findViewById(R.id.main_top_title)).setText("吴沐熙vicky");
//		UIUtils.setDrawableLeft(this,main_top_right,R.drawable.d3_06);
		main_top_left.setVisibility(View.VISIBLE);
//		UIUtils.setDrawableLeft(this,main_top_left2,R.drawable.d3_03);
		main_top_left.setOnClickListener(this);
		lzuopin.setAdapter(new ZuopinBaseAdapter(YirenActivity.this));
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
		img1.setOnClickListener(this);
		img2.setOnClickListener(this);
		img3.setOnClickListener(this);
	}
	
	public void setvalue(){
		controller.getContext().addBusinessData("near.user_id", userid);
	}
	/**
	 * 重新计算listview高度
	  * 方法描述：TODO
	  * @param listView0
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
		case R.id.main_top_left:
			finish();
			break;
		case R.id.img1:
			IntentUtil.intent(YirenActivity.this, PictureActivity.class);
			break;
		case R.id.img2:
			IntentUtil.intent(YirenActivity.this, PictureActivity.class);
			break;
		case R.id.img3:
			IntentUtil.intent(YirenActivity.this, PictureActivity.class);
			break;
		case R.id.img4:
			IntentUtil.intent(YirenActivity.this, PictureActivity.class);
			break;
		case R.id.img5:
			IntentUtil.intent(YirenActivity.this, PictureActivity.class);
			break;
		case R.id.img6:
			IntentUtil.intent(YirenActivity.this, PictureActivity.class);
			break;
		case R.id.img7:
			IntentUtil.intent(YirenActivity.this, PictureActivity.class);
			break;
		case R.id.img8:
			IntentUtil.intent(YirenActivity.this, PictureActivity.class);
			break;
		case R.id.lneardongtai:
			IntentUtil.intent(YirenActivity.this, NearDongtaiActivity.class);
			break;
		case R.id.imgduihua:
			IntentUtil.intent(YirenActivity.this, MessageChatActivity.class);
			break;
		case R.id.imgguanzhu:
			showMessageTwo(YirenActivity.this, "关注？", "完成");
			break;
		case R.id.imgyuyue:
			IntentUtil.intent(YirenActivity.this, WorkAppointmentActivity.class);
			break;
		default:
			break;
		}
	}
	private void showMessageTwo(final Context context,String message,final String toast) {
		final IOSStyleListDialog dialog = new IOSStyleListDialog(context, IOSStyleDialog.DIALOG_TWO);
		dialog.setLeft("取消", new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dialog.closeDialog();
			}
		});
		dialog.setRight("确认", new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dialog.closeDialog();
				switch (dialog.index) {
				case 0:
					final IOSStyleDialog dialog = new IOSStyleDialog(context, IOSStyleDialog.DIALOG_ONE);
					dialog.setMessage("您关注了此人");
					dialog.setOne("确认",new OnClickListener() {
						@Override
						public void onClick(View v) {
//							IntentUtil.intent(mContext, MainFragmentActivity.class);
							dialog.closeDialog();
						}
					});
					break;
				case 1:
					final IOSStyleDialog dialog1 = new IOSStyleDialog(context, IOSStyleDialog.DIALOG_ONE);
					dialog1.setMessage("您拉黑了此人");
					dialog1.setOne("确认",new OnClickListener() {
						@Override
						public void onClick(View v) {
//							IntentUtil.intent(mContext, MainFragmentActivity.class);
							dialog1.closeDialog();
						}
					});
					break;
				case 2:
					final IOSStyleDialog dialog2 = new IOSStyleDialog(context, IOSStyleDialog.DIALOG_ONE);
					dialog2.setMessage("您举报了此人");
					dialog2.setOne("确认",new OnClickListener() {
						@Override
						public void onClick(View v) {
//							IntentUtil.intent(mContext, MainFragmentActivity.class);
							dialog2.closeDialog();
						}
					});
					break;
				default:
					break;
				}
				

			}
		});
	}
	
	@Override  
    public boolean onTouchEvent(MotionEvent event) {  
        return mGestureDetector.onTouchEvent(event);  
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
				Userinfopackage u = (Userinfopackage) controller.getContext().getBusinessData("UserinfoData");
				if(u != null){
					tvxingzuo.setText(u.getConstella());
					tvdizhi.setText(u.getAge());
					 tvname.setText(u.getUsername());
					 tvnickname.setText(u.getNickname());
					 tvage.setText(u.getAge());
					 tvxingzuo1.setText(u.getConstella());
					 tvzhiye.setText(u.getMajor());
					 tvjiaxiang.setText(u.getAge());
					 tvheight.setText(u.getMore().getHeight()+"cm");
					 tvsanwei.setText(u.getMore().getBust()+u.getMore().getWaist()+u.getMore().getHip());
					adapter = new ZuopinBaseAdapter(YirenActivity.this);
					lzuopin.setAdapter(adapter);
					adapter.notifyDataSetChanged();
				}
				break;
			}
		}
	};
}
