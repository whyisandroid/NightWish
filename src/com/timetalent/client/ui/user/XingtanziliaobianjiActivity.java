package com.timetalent.client.ui.user;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.Gravity;
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
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.timetalent.client.R;
import com.timetalent.client.entities.Baseinfopackage;
import com.timetalent.client.entities.LoginData;
import com.timetalent.client.entities.Userinfopackage;
import com.timetalent.client.service.AppController;
import com.timetalent.client.ui.BaseActivity;
import com.timetalent.client.ui.GuideActivity;
import com.timetalent.client.ui.MainFragmentActivity;
import com.timetalent.client.ui.adapter.TonggaoBaseAdapter;
import com.timetalent.client.ui.adapter.ZuopinBaseAdapter;
import com.timetalent.client.ui.addresscheck.City_cnActivity;
import com.timetalent.client.ui.fragment.util.Background2;
import com.timetalent.client.ui.fragment.util.Background3;
import com.timetalent.client.ui.near.NearDongtaiActivity;
import com.timetalent.client.ui.near.PictureActivity;
import com.timetalent.client.ui.near.XingtanActivity;
import com.timetalent.client.ui.view.EditPicturesLayout;
import com.timetalent.client.ui.view.PicturesLayout;
import com.timetalent.common.util.IntentUtil;
import com.timetalent.common.util.PictureUtil;
import com.timetalent.common.util.StringUtil;


/******************************************
 * 类描述： 登录界面
 * 类名称：LoginActivity  
 * @version: 1.0
 * @author: why
 * @time: 2014-10-10 下午6:32:12 
 ******************************************/
public class XingtanziliaobianjiActivity extends BaseActivity implements OnClickListener {
	private AppController controller;
	EditPicturesLayout piclay;	
	TextView tvjiaxiang;
	private GestureDetector mGestureDetector;
	int index = 0;
	private LinearLayout ldongtai;
	private ListView ltonggao;
	private TextView main_top_right;
	private ImageButton main_top_left;
	private LinearLayout lage;
	private Button btok;
	public int screenw = 0;
	public float density = 1.0f;
	ImageView imghead;
	TextView tvage;
	TextView tvage1;
	ImageView imgsex;
	TextView tvxingzuo;
	TextView tvxingzuo1;
	TextView tvfeed;
	LoginData user;
	Userinfopackage u;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_xingtanziliaobianji);
		controller = AppController.getController(this);
		user = (LoginData) controller.getContext().getBusinessData("loginData");
		DisplayMetrics dm = new DisplayMetrics();
		this.getWindowManager().getDefaultDisplay().getMetrics(dm);
		screenw = dm.widthPixels;
		density = dm.density;
		findView();
		new Thread(){
			public void run() {
				controller.getContext().addBusinessData("near.user_id", user.getId());
				controller.userinfo();
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
		piclay = (EditPicturesLayout) findViewById(R.id.piclay);
		ltonggao = (ListView) findViewById(R.id.ltonggao);
		ldongtai = (LinearLayout) findViewById(R.id.ldongtai);
		main_top_right = (TextView)this.findViewById(R.id.main_top_right);
		main_top_left = (ImageButton)this.findViewById(R.id.main_top_left);
		lage = (LinearLayout) findViewById(R.id.lage);
		btok = (Button) findViewById(R.id.btok);
		tvjiaxiang = (TextView) findViewById(R.id.tvjiaxiang);
		imghead = (ImageView)this.findViewById(R.id.imghead);
		tvage1 = (TextView)this.findViewById(R.id.tvage1);
		tvage = (TextView) findViewById(R.id.tvage);
		tvxingzuo1 = (TextView) findViewById(R.id.tvxingzuo1);
		tvxingzuo = (TextView) findViewById(R.id.tvxingzuo);
		imgsex = (ImageView)this.findViewById(R.id.imgsex);
		tvfeed = (TextView)this.findViewById(R.id.tvfeed);
	}

	/**
	 * 方法描述：TODO
	 * 
	 * @author: why
	 * @time: 2014-10-10 下午6:36:02
	 */
	private void initView() {
		((TextView)this.findViewById(R.id.main_top_title)).setText(""+u.getNickname());
//		((TextView)this.findViewById(R.id.main_top_right)).setText("编辑");
//		UIUtils.setDrawableLeft(this,main_top_right,R.drawable.d3_06);
		this.findViewById(R.id.main_top_left).setVisibility(View.VISIBLE);
//		UIUtils.setDrawableLeft(this,main_top_left2,R.drawable.d3_03);
		piclay.setcontroller(controller);
		piclay.initView();
		ltonggao.setAdapter(new TonggaoBaseAdapter(XingtanziliaobianjiActivity.this));
		setListViewHeightBasedOnChildren(ltonggao);
		ldongtai.setOnClickListener(this);
		lage.setOnClickListener(this);
		btok.setOnClickListener(this);
		tvage1.setText(controller.getContext().getStringData("edit.age"));
		tvage.setText(controller.getContext().getStringData("edit.age"));
		tvxingzuo1.setText(controller.getContext().getStringData("edit.xingzuo"));
		tvxingzuo.setText(controller.getContext().getStringData("edit.xingzuo"));
		tvfeed.setText(u.getCount().getFeed());
		if(u.getSex().equals("1")){
			imgsex.setImageResource(R.drawable.f_05);
		}else{
			imgsex.setImageResource(R.drawable.f_03);
		}
		tvjiaxiang.setOnClickListener(this);
	}
	@Override
	protected void onStart() {
		super.onStart();
		tvage1.setText(controller.getContext().getStringData("edit.age"));
		tvage.setText(controller.getContext().getStringData("edit.age"));
		tvxingzuo.setText(controller.getContext().getStringData("edit.xingzuo"));
		tvxingzuo1.setText(controller.getContext().getStringData("edit.xingzuo"));
		tvjiaxiang.setText(controller.getContext().getStringData("edit.jiaxiang"));
		
	}
	@Override
	protected void onStop() {
		super.onStop();
		tvage.setText(controller.getContext().getStringData("edit.age"));
		tvxingzuo1.setText(controller.getContext().getStringData("edit.xingzuo"));
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
	public void setvalue(){
		controller.getContext().addBusinessData("fans.bianji.username", "");
		controller.getContext().addBusinessData("fans.bianji.phone", "");
		controller.getContext().addBusinessData("fans.bianji.email", "");
		controller.getContext().addBusinessData("fans.bianji.sex", "");
		controller.getContext().addBusinessData("fans.bianji.nickname", "");
		controller.getContext().addBusinessData("fans.bianji.realname", "");
		controller.getContext().addBusinessData("fans.bianji.birthday", "");
		controller.getContext().addBusinessData("fans.bianji.constella", "");
		controller.getContext().addBusinessData("fans.bianji.certificate", "");
		controller.getContext().addBusinessData("fans.bianji.certificate", "");
		controller.getContext().addBusinessData("fans.bianji.city", "");
	}
	@Override
	public void onClick(final View vclick) {
		switch (vclick.getId()) {
		case R.id.tvjiaxiang:
			IntentUtil.intent(XingtanziliaobianjiActivity.this, City_cnActivity.class);
			break;
		case R.id.bt_login_next:
			break;
		case R.id.lneardongtai:
			IntentUtil.intent(XingtanziliaobianjiActivity.this, MyDongtaiActivity.class);
			break;
		case R.id.main_top_left:
			finish();
			break;
		case R.id.lage:
			IntentUtil.intent(XingtanziliaobianjiActivity.this, MyageActivity.class);
			break;
		case R.id.btok:
			setvalue();
			controller.mybaseinfoupdate();
			finish();
			break;
		case R.id.img1:
		case R.id.img2:
		case R.id.img3:
		case R.id.img4:
		case R.id.img5:
		case R.id.img6:
		case R.id.img7:
			LayoutInflater inflater = LayoutInflater.from(XingtanziliaobianjiActivity.this);
			View popview = inflater.inflate(R.layout.bianji_delete, null);
			final PopupWindow pop = new PopupWindow(popview, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, false);
			pop.setBackgroundDrawable(new Background3());
			pop.setOutsideTouchable(true);
			pop.setFocusable(true);
			Button btok = (Button) popview.findViewById(R.id.btok);
			btok.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					((ImageView) vclick).setImageResource(R.drawable.d11_03);
					pop.dismiss();
				}
			});
			int[] location = new int[2];  
			vclick.getLocationOnScreen(location);
			pop.showAtLocation(vclick, Gravity.NO_GRAVITY, location[0]-pop.getWidth()-19, location[1]-pop.getHeight()-19);
			break;
		case R.id.img8:
			StringUtil.doGoToImg(XingtanziliaobianjiActivity.this);
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
				 u = (Userinfopackage) controller.getContext().getBusinessData("UserinfoData");
				if(u != null){
					initView();
					new Thread(){
						public void run() {
							Message msg = new Message();
							msg.what = 2;
							msg.obj = PictureUtil.getImage(u.getAvatar(), u.getId(), "head");
							handler.sendMessage(msg);
						};
					}.start();
				}
				break;
			case 2:
				BitmapDrawable img = (BitmapDrawable) msg.obj;
				Bitmap bm = PictureUtil.toRoundCorner(img.getBitmap(),10);
				imghead.setImageBitmap(bm);
				LayoutParams pa = (LayoutParams)imghead.getLayoutParams();
				pa.width = (int) (50*density);
				pa.height = (int) (50*density);
				
//				imghead.setPadding(0, (int) (20*density), 0, (int) (20*density));
				break;
			}
		}
	};
}
