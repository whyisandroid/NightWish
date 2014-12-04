package com.timetalent.client.ui.near;

import android.content.Context;
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
import com.timetalent.client.ui.GuideActivity;
import com.timetalent.client.ui.MainFragmentActivity;
import com.timetalent.client.ui.adapter.NearBaseAdapter;
import com.timetalent.client.ui.adapter.TonggaoBaseAdapter;
import com.timetalent.client.ui.adapter.ZuopinBaseAdapter;
import com.timetalent.client.ui.dialog.IOSStyleDialog;
import com.timetalent.client.ui.dialog.IOSStyleListDialog;
import com.timetalent.client.ui.message.MessageChatActivity;
import com.timetalent.client.ui.view.NearPicturesLayout;
import com.timetalent.client.ui.view.PicturesLayout;
import com.timetalent.common.util.IntentUtil;
import com.timetalent.common.util.PictureUtil;
import com.timetalent.client.ui.fragment.util.Background1;

/******************************************
 * 类描述： 登录界面
 * 类名称：LoginActivity  
 * @version: 1.0
 * @author: why
 * @time: 2014-10-10 下午6:32:12 
 ******************************************/
public class XingtanActivity extends BaseActivity implements OnClickListener{
	private AppController controller;
	NearPicturesLayout piclay;
	int index = 0;
	String userid = "1";
	private TextView main_top_right;
	private ImageButton main_top_left;
	private LinearLayout ldongtai;
	private ListView ltonggao;
	TonggaoBaseAdapter adapter;
	private LinearLayout img1;
	private LinearLayout img2;
	public int screenw = 0;
	public float density = 1.0f;
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
	Userinfopackage u;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.near_xingtanxiangqing);
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
		
		piclay = (NearPicturesLayout) findViewById(R.id.piclay);
		main_top_left = (ImageButton)this.findViewById(R.id.main_top_left);
		ltonggao = (ListView) findViewById(R.id.ltonggao);
		ldongtai = (LinearLayout) findViewById(R.id.lneardongtai);
		img1 = (LinearLayout) findViewById(R.id.imgduihua);
		img2 = (LinearLayout) findViewById(R.id.imgguanzhu);
		tvxingzuo = (TextView) findViewById(R.id.tvxingzuo);
		imghead = (ImageView) findViewById(R.id.imghead);
		 tvdizhi = (TextView) findViewById(R.id.tvdizhi);
		 tvname = (TextView) findViewById(R.id.tvname);
		 tvnickname = (TextView) findViewById(R.id.tvnickname);
		 tvage = (TextView) findViewById(R.id.tvage);
		 tvxingzuo1 = (TextView) findViewById(R.id.tvxingzuo1);
		 tvzhiye = (TextView) findViewById(R.id.tvzhiye);
		 tvjiaxiang = (TextView) findViewById(R.id.tvjiaxiang);
		 tvheight = (TextView) findViewById(R.id.tvheight);
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
		ltonggao.setAdapter(new TonggaoBaseAdapter(XingtanActivity.this));
		setListViewHeightBasedOnChildren(ltonggao);
		ldongtai.setOnClickListener(this);
		img1.setOnClickListener(this);
		img2.setOnClickListener(this);
		piclay.setcontroller(controller);
		piclay.initView();
		
	}
	public void setvalue(){
		controller.getContext().addBusinessData("near.user_id", userid);
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
		case R.id.main_top_left:
			finish();
			break;
		case R.id.lneardongtai:
			IntentUtil.intent(XingtanActivity.this, NearDongtaiActivity.class);
			break;
		case R.id.imgduihua:
			IntentUtil.intent(XingtanActivity.this, MessageChatActivity.class);
			break;
		case R.id.imgguanzhu:
			showMessageTwo(XingtanActivity.this, "关注？", "完成");
			break;
		case R.id.img1:
			IntentUtil.intent(XingtanActivity.this, PictureActivity.class);
			break;
		case R.id.img2:
			IntentUtil.intent(XingtanActivity.this, PictureActivity.class);
			break;
		case R.id.img3:
			IntentUtil.intent(XingtanActivity.this, PictureActivity.class);
			break;
		case R.id.img4:
			IntentUtil.intent(XingtanActivity.this, PictureActivity.class);
			break;
		case R.id.img5:
			IntentUtil.intent(XingtanActivity.this, PictureActivity.class);
			break;
		case R.id.img6:
			IntentUtil.intent(XingtanActivity.this, PictureActivity.class);
			break;
		case R.id.img7:
			IntentUtil.intent(XingtanActivity.this, PictureActivity.class);
			break;
		case R.id.img8:
			IntentUtil.intent(XingtanActivity.this, PictureActivity.class);
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
				final Userinfopackage u = (Userinfopackage) controller.getContext().getBusinessData("UserinfoData");
				dialog.closeDialog();
				switch (dialog.index) {
				case 0:
					new Thread(){
						public void run() {
							controller.getContext().addBusinessData("my.do", "follow");
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
					new Thread(){
						public void run() {
							controller.getContext().addBusinessData("report.type", "black");
							controller.getContext().addBusinessData("report.target_id", u.getId());
							controller.getContext().addBusinessData("report.msg", "black");
							controller.myreport();
						};
					}.start();
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
	
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// super.handleMessage(msg);
			switch (msg.what) {
			case 1:
				u = (Userinfopackage) controller.getContext().getBusinessData("UserinfoData");
				if(u != null){
					((TextView)XingtanActivity.this.findViewById(R.id.main_top_title)).setText(""+u.getNickname());
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
					 tvjiaxiang.setText(u.getAge());
					 tvheight.setText(u.getMore().getHeight()+"cm");
				}
				adapter = new TonggaoBaseAdapter(XingtanActivity.this);
				ltonggao.setAdapter(adapter);
				adapter.notifyDataSetChanged();
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
