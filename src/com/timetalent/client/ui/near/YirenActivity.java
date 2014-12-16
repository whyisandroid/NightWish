package com.timetalent.client.ui.near;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
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
import com.timetalent.client.entities.LoginData;
import com.timetalent.client.entities.Userinfopackage;
import com.timetalent.client.entities.advocatuspackage;
import com.timetalent.client.entities.awardpackage;
import com.timetalent.client.service.AppController;
import com.timetalent.client.ui.BaseActivity;
import com.timetalent.client.ui.adapter.NearBaseAdapter;
import com.timetalent.client.ui.adapter.ZuopinBaseAdapter;
import com.timetalent.client.ui.chance.WorkAppointmentActivity;
import com.timetalent.client.ui.dialog.IOSStyleDialog;
import com.timetalent.client.ui.dialog.IOSStyleListDialog;
import com.timetalent.client.ui.dialog.ReportIOSStyleDialog;
import com.timetalent.client.ui.dynamic.DynamicOtherActivity;
import com.timetalent.client.ui.esaemob.ChatActivity;
import com.timetalent.client.ui.fragment.util.Background1;
import com.timetalent.client.ui.message.MessageChatActivity;
import com.timetalent.client.ui.view.NearPicturesLayout;
import com.timetalent.client.ui.view.PicturesLayout;
import com.timetalent.common.util.IntentUtil;
import com.timetalent.common.util.PictureUtil;


/******************************************
 * 类描述： 登录界面
 * 类名称：LoginActivity  
 * @version: 1.0
 * @author: why
 * @time: 2014-10-10 下午6:32:12 
 ******************************************/
public class YirenActivity extends BaseActivity implements OnClickListener{
	private AppController controller;
	NearPicturesLayout piclay;
	private LinearLayout ldongtai;
	private ListView lzuopin;
	ZuopinBaseAdapter adapter;
	private TextView main_top_right;
	private ImageButton main_top_left;
	private ImageView img1;
	private ImageView img2;
	private ImageView img3;
	private GestureDetector mGestureDetector;
	ImageView imghead;
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
	TextView tvfeed;
	TextView tvage1;
	ImageView imgsex1;
	TextView tvcontent;
	TextView tvdaiyan;
	TextView tvhuojiangjilu;
	int index = 0;
	String userid = "1";
	public int screenw = 0;
	public float density = 1.0f;
	Userinfopackage u;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onStart()
	 */
	@Override
	protected void onStart() {
		super.onStart();
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
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		piclay.onDestroy();
	}
	/**
	 * 方法描述：TODO
	 * 
	 * @author: why
	 * @time: 2014-10-10 下午6:36:00
	 */
	private void findView() {
		piclay = (NearPicturesLayout) findViewById(R.id.piclay);
		main_top_left = (ImageButton)this.findViewById(R.id.main_top_left);
		lzuopin = (ListView) findViewById(R.id.lzuopin);
		ldongtai = (LinearLayout) findViewById(R.id.lneardongtai);
		img1 = (ImageView) findViewById(R.id.imgduihua);
		img2 = (ImageView) findViewById(R.id.imgguanzhu);
		img3 = (ImageView) findViewById(R.id.imgyuyue);
		
		imghead = (ImageView) findViewById(R.id.imghead);
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
		 tvfeed = (TextView) findViewById(R.id.tvfeed);
		 tvage1 = (TextView) findViewById(R.id.tvage1);
		 imgsex1 = (ImageView) findViewById(R.id.imgsex1);
		 tvcontent = (TextView) findViewById(R.id.tvcontent);
		 tvdaiyan = (TextView) findViewById(R.id.tvdaiyan); 
		 tvhuojiangjilu = (TextView) findViewById(R.id.tvhuojiangjilu); 
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
		((TextView)this.findViewById(R.id.main_top_title)).setText("");
//		UIUtils.setDrawableLeft(this,main_top_right,R.drawable.d3_06);
		main_top_left.setVisibility(View.VISIBLE);
//		UIUtils.setDrawableLeft(this,main_top_left2,R.drawable.d3_03);
		main_top_left.setOnClickListener(this);
		ldongtai.setOnClickListener(this);
		img1.setOnClickListener(this);
		img2.setOnClickListener(this);
		img3.setOnClickListener(this);
		piclay.setcontroller(controller);
		piclay.initView();
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
		case R.id.lneardongtai:
			controller.getContext().addBusinessData("other_id", u.getId());
			Bundle bundle0 = new Bundle();
			bundle0.putString("userName", u.getNickname());
			IntentUtil.intent(YirenActivity.this, bundle0,DynamicOtherActivity.class,false);
			break;
		case R.id.imgduihua:
Intent intent = new Intent(YirenActivity.this,ChatActivity.class);
			
			intent.putExtra("userId", u.getId());
			intent.putExtra("nickName", u.getNickname());
			intent.putExtra("userImageURL", u.getAvatar());
			controller.getContext().addBusinessData("userId", u.getId());
			controller.getContext().addBusinessData("nickName", u.getNickname());
			controller.getContext().addBusinessData("userImageURL", u.getAvatar());
			YirenActivity.this.startActivity(intent);
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
		final Userinfopackage u = (Userinfopackage) controller.getContext().getBusinessData("UserinfoData");
		final IOSStyleListDialog dialog = new IOSStyleListDialog(context, IOSStyleDialog.DIALOG_TWO,u);
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
					new Thread(){
						public void run() {
							if(u.getFollow_do().equals("Y")){
								controller.getContext().addBusinessData("my.do", "unfollow");
							}else{
								controller.getContext().addBusinessData("my.do", "follow");
							}
							controller.getContext().addBusinessData("my.target_id", u.getId());
							controller.mydo_social();
						};
					}.start();
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
					new Thread(){
						public void run() {
							controller.getContext().addBusinessData("my.do", "black");
							controller.getContext().addBusinessData("my.target_id", u.getId());
							controller.mydo_social();
						};
					}.start();
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
					
					final ReportIOSStyleDialog dialog2 = new ReportIOSStyleDialog(context,controller ,IOSStyleDialog.DIALOG_TWO);
					break;
				default:
					break;
				}
				

			}
		});
	}
	
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// super.handleMessage(msg);
			switch (msg.what) {
			case 1:
				u = (Userinfopackage) controller.getContext().getBusinessData("UserinfoData");
				LoginData user = (LoginData) controller.getContext().getBusinessData("loginData");
				if(u != null){
					((TextView)YirenActivity.this.findViewById(R.id.main_top_title)).setText(""+u.getNickname());
					new Thread(){
						public void run() {
							Message msg = new Message();
							msg.what = 2;
							msg.obj = PictureUtil.getImage(u.getAvatar(), u.getId(), "head");
							handler.sendMessage(msg);
						};
					}.start();
					tvxingzuo.setText(u.getConstella());
					tvdizhi.setText(u.getAge());
					 tvname.setText(u.getUsername());
					 tvnickname.setText(u.getNickname());
					 tvage.setText(u.getAge());
					 tvxingzuo1.setText(u.getConstella());
					 tvzhiye.setText(u.getMajor());
					 tvjiaxiang.setText(u.getProvince()+u.getCity());
					 tvheight.setText(u.getMore().getHeight()+"cm");
					 tvfeed.setText(u.getCount().getFeed());
					 tvage1.setText(u.getAge());
					 tvcontent.setText(u.getMore().getContent()+"");
					 if(u.getSex().equals("1")){
						 imgsex1.setImageResource(R.drawable.f_05);
					 }else if(u.getSex().equals("2")){
						 imgsex1.setImageResource(R.drawable.f_03);
					 }else{
						 imgsex1.setImageResource(R.drawable.f_05); 
					 }
					 tvsanwei.setText(u.getMore().getBust()+","+u.getMore().getWaist()+","+u.getMore().getHip());
					adapter = new ZuopinBaseAdapter(YirenActivity.this);
					lzuopin.setAdapter(adapter);
					setListViewHeightBasedOnChildren(lzuopin);
					adapter.notifyDataSetChanged();
					if(!user.getType().equals("scout")){
						img3.setVisibility(View.GONE);
					}
					String awards = "";
					for(awardpackage a:u.getMore().getAward()){
						awards+= a.getContent()+"\n";
					}
					tvdaiyan.setText(awards);
					String huojiang = "";
					for(advocatuspackage b:u.getMore().getAdvocatus()){
						huojiang+= b.getContent()+"\n";
					}
					tvhuojiangjilu.setText(huojiang);
				}
				break;
			case 2:
				if(msg.obj instanceof Background1){
					imghead.setImageDrawable((Drawable)msg.obj);
					break;
				}
				BitmapDrawable img = (BitmapDrawable) msg.obj;
				Bitmap bm = PictureUtil.toRoundCorner(img.getBitmap(),10);
				imghead.setImageBitmap(bm);
				LayoutParams pa = (LayoutParams)imghead.getLayoutParams();
				pa.width = (int) (50*density);
				pa.height = (int) (50*density);
				break;
			}
		}
	};
}
