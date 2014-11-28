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
import com.timetalent.client.ui.view.PicturesLayout;
import com.timetalent.common.util.IntentUtil;


/******************************************
 * 类描述： 登录界面
 * 类名称：LoginActivity  
 * @version: 1.0
 * @author: why
 * @time: 2014-10-10 下午6:32:12 
 ******************************************/
public class FansziliaoActivity extends BaseActivity implements OnClickListener {
	private AppController controller;
	PicturesLayout piclay;
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
	int index = 0;
	private LinearLayout ldongtai;
	private TextView main_top_right;
	private ImageButton main_top_left;
	public int screenw = 0;
	public float density = 1.0f;
	Baseinfopackage u;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_fansziliao);
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
		piclay = (PicturesLayout) findViewById(R.id.piclay);
		
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
	}

	/**
	 * 方法描述：TODO
	 * 
	 * @author: why
	 * @time: 2014-10-10 下午6:36:02
	 */
	private void initView() {
		setvalue();
		((TextView)this.findViewById(R.id.main_top_title)).setText(""+u.getNickname());
		((TextView)this.findViewById(R.id.main_top_right)).setText("编辑");
		main_top_right.setVisibility(main_top_right.VISIBLE);
//		UIUtils.setDrawableLeft(this,main_top_right,R.drawable.d3_06);
//		this.findViewById(R.id.main_top_left).setVisibility(View.GONE);
//		UIUtils.setDrawableLeft(this,main_top_left2,R.drawable.d3_03);
		piclay.setcontroller(controller);
		piclay.initView();
		
		main_top_right.setOnClickListener(this);
		ldongtai.setOnClickListener(this);
		
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
