package com.timetalent.client.ui.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.timetalent.client.R;
import com.timetalent.client.entities.AppPackage;
import com.timetalent.client.entities.LoginData;
import com.timetalent.client.entities.Userinfopackage;
import com.timetalent.client.service.AppController;
import com.timetalent.client.service.AppManager;
import com.timetalent.client.ui.adapter.NearBaseAdapter;
import com.timetalent.client.ui.dialog.DialogUtil;
import com.timetalent.client.ui.dialog.ExitIOSStyleListDialog;
import com.timetalent.client.ui.dialog.IOSStyleDialog;
import com.timetalent.client.ui.dialog.IOSStyleListDialog;
import com.timetalent.client.ui.near.SearchActivity;
import com.timetalent.client.ui.user.FansziliaoActivity;
import com.timetalent.client.ui.user.FansziliaobianjiActivity;
import com.timetalent.client.ui.user.MyAboutActivity;
import com.timetalent.client.ui.user.MyDongtaiActivity;
import com.timetalent.client.ui.user.MyHelpActivity;
import com.timetalent.client.ui.user.MyfansActivity;
import com.timetalent.client.ui.user.MyfuwuActivity;
import com.timetalent.client.ui.user.MyguanzhuActivity;
import com.timetalent.client.ui.user.MyhaoyouMainActivity;
import com.timetalent.client.ui.user.MyheimingdanActivity;
import com.timetalent.client.ui.user.MylianxiActivity;
import com.timetalent.client.ui.user.MyqianbaoActivity;
import com.timetalent.client.ui.user.MyrenzhengActivity;
import com.timetalent.client.ui.user.MytuijianActivity;
import com.timetalent.client.ui.user.MyworkActivity;
import com.timetalent.client.ui.user.MyxieyiActivity;
import com.timetalent.client.ui.user.XingtanziliaoActivity;
import com.timetalent.client.ui.user.XingtanziliaobianjiActivity;
import com.timetalent.client.ui.user.YirenziliaoActivity;
import com.timetalent.client.ui.user.YirenziliaobianjiActivity;
import com.timetalent.common.util.Downloadapk;
import com.timetalent.common.util.IntentUtil;
import com.timetalent.common.util.PictureUtil;


/******************************************
 * 类描述： 我的
 * 类名称：UserFragment  
 * @version: 1.0
 * @author: why
 * @time: 2014-10-10 下午6:32:45 
 ******************************************/
public class UserFragment extends Fragment implements OnClickListener {
	private View view;
	private Context mContext;
	private TextView main_top_right;
	private ImageButton main_top_left;
	private AppController controller;
	private LinearLayout lmydongtai;
	private LinearLayout lmyhaoyou;
	private LinearLayout lmytuijian;
	private LinearLayout lmyguanzhu;
	private LinearLayout lmyfans;
	private LinearLayout lmyqianbao;
	private LinearLayout lrenzheng;
	private LinearLayout lmywork;
	private LinearLayout lmyfuwu;
	private LinearLayout lheimingdan;
	private LinearLayout lgengxin;
	private LinearLayout lbangzhu;
	private LinearLayout lqinglitupian;
	private LinearLayout lexit;
	private LinearLayout labout;
	private LinearLayout lkefu;
	private LinearLayout lxieyi;
	ImageView imghead;
	ImageView imgloyal_pass;
	TextView tvnickname;
	TextView tvmoney;
	TextView tvcounthaoyou;
	TextView tvcountpushuser;
	TextView tvcountguanzhu;
	TextView tvcountfans;
	int r = 0;
	LoginData user;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		controller = AppController.getController(getActivity());
		user = (LoginData) controller.getContext().getBusinessData("loginData");
		
		String type = user.getType();
		if(type.equalsIgnoreCase("star")){
			r = 0;
		}else if(type.equalsIgnoreCase("scout")){
			r = 1;
		}else if(type.equalsIgnoreCase("fans")){
			r = 2;
		}
		switch (r) {
		case 0:
			view = inflater.inflate(R.layout.my_main_mingxing, container, false);
			break;
		case 1:
			view = inflater.inflate(R.layout.my_main_xingtan, container, false);
			break;
		case 2:
			view = inflater.inflate(R.layout.my_main_fans, container, false);
			break;
		default:
			break;
		}
		
		mContext = getActivity();
		new Thread(){
			public void run() {
				controller.getContext().addBusinessData("near.user_id", user.getId());
				controller.userinfo();
				handler.sendEmptyMessage(2);
			};
		}.start();
		findView();
		initView();
		return view;
	}

	/**
	 * 方法描述：TODO
	 * 
	 * @author: why
	 * @time: 2014-10-10 下午6:36:02
	 */
	private void initView() {
		((TextView)view.findViewById(R.id.main_top_title)).setText("我的");
//		UIUtils.setDrawableLeft(this,main_top_right,R.drawable.d3_06);
		main_top_left.setVisibility(View.GONE);
//		UIUtils.setDrawableLeft(this,main_top_left2,R.drawable.d3_03);
		main_top_left.setOnClickListener(this);
		lmydongtai.setOnClickListener(this);
		lmyhaoyou.setOnClickListener(this);
		lmytuijian.setOnClickListener(this);
		lmyguanzhu.setOnClickListener(this);
		lmyfans.setOnClickListener(this);
		lmyqianbao.setOnClickListener(this);
		lmywork.setOnClickListener(this);
		
		lheimingdan.setOnClickListener(this);
		lgengxin.setOnClickListener(this);
		lbangzhu.setOnClickListener(this);
		lqinglitupian.setOnClickListener(this);
		lexit.setOnClickListener(this);
		labout.setOnClickListener(this);
		lkefu.setOnClickListener(this);
		lxieyi.setOnClickListener(this);
		switch (r) {
		case 0:
			lmyfuwu.setOnClickListener(this);
			lrenzheng.setOnClickListener(this);
			break;
		case 1:
			lrenzheng.setOnClickListener(this);
			break;
		case 2:
	
			break;
		default:
			break;
		}
		new Thread(){
			public void run() {
				Message msg = new Message();
				msg.what = 1;
				msg.obj = PictureUtil.getImage(user.getAvatar(), user.getId(), "head");
				handler.sendMessage(msg);
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
		main_top_left = (ImageButton)view.findViewById(R.id.main_top_left);
		imghead = (ImageView) view.findViewById(R.id.imghead);
		imgloyal_pass = (ImageView) view.findViewById(R.id.imgloyal_pass);
		lmydongtai = (LinearLayout) view.findViewById(R.id.lmydongtai);
		lmyhaoyou = (LinearLayout) view.findViewById(R.id.lmyhaoyou);
		lmytuijian = (LinearLayout) view.findViewById(R.id.lmytuijian);
		lmyguanzhu = (LinearLayout) view.findViewById(R.id.lmyguanzhu);
		lmyfans = (LinearLayout) view.findViewById(R.id.lmyfans);
		lmyqianbao = (LinearLayout) view.findViewById(R.id.lmyqianbao);
		lmywork = (LinearLayout) view.findViewById(R.id.lmywork);
		lheimingdan = (LinearLayout) view.findViewById(R.id.lheimingdan);

		lgengxin = (LinearLayout) view.findViewById(R.id.lgengxin);
		lbangzhu = (LinearLayout) view.findViewById(R.id.lbangzhu);
		lqinglitupian = (LinearLayout) view.findViewById(R.id.lqinglitupian);
		lexit = (LinearLayout) view.findViewById(R.id.lexit);
		labout = (LinearLayout) view.findViewById(R.id.labout);
		lxieyi = (LinearLayout) view.findViewById(R.id.lxieyi);
		lkefu = (LinearLayout) view.findViewById(R.id.lkefu);
		switch (r) {
		case 0:
			lmyfuwu = (LinearLayout) view.findViewById(R.id.lmyfuwu);
			lrenzheng = (LinearLayout) view.findViewById(R.id.lmyrenzheng);
			break;
		case 1:
			lrenzheng = (LinearLayout) view.findViewById(R.id.lmyrenzheng);
			break;
		case 2:
	
			break;
		default:
			break;
		}
		
		 tvnickname = (TextView) view.findViewById(R.id.tvnickname);
		 tvmoney = (TextView) view.findViewById(R.id.tvmoney);
		 tvcounthaoyou = (TextView) view.findViewById(R.id.tvcounthaoyou);
		 tvcountpushuser = (TextView) view.findViewById(R.id.tvcountpushuser);
		 tvcountguanzhu = (TextView) view.findViewById(R.id.tvcountguanzhu);
		 tvcountfans = (TextView) view.findViewById(R.id.tvcountfans);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.main_top_left:
//			finish();
			break;
		case R.id.labout:
			IntentUtil.intent(this.mContext, MyAboutActivity.class);
			break;
		case R.id.lkefu:
			IntentUtil.intent(this.mContext, MylianxiActivity.class);
			break;
		case R.id.lxieyi:
			IntentUtil.intent(this.mContext, MyxieyiActivity.class);
			break;
		case R.id.lmydongtai:
			switch (r) {
			case 0:
				IntentUtil.intent(this.mContext, YirenziliaoActivity.class);
				break;
case 1:
	IntentUtil.intent(this.mContext, XingtanziliaoActivity.class);
				break;
case 2:
	IntentUtil.intent(this.mContext, FansziliaoActivity.class);
	break;
			default:
				break;
			}
			break;
		case R.id.lmyhaoyou:
			Bundle bundle0 = new Bundle();
			bundle0.putInt("index", 0);
			IntentUtil.intent(this.mContext, bundle0,MyhaoyouMainActivity.class,false);
			break;
		case R.id.lmytuijian:
			Bundle bundle1 = new Bundle();
			bundle1.putInt("index", 1);
			IntentUtil.intent(this.mContext, bundle1,MyhaoyouMainActivity.class,false);
			break;
		case R.id.lmyguanzhu:
			Bundle bundle2 = new Bundle();
			bundle2.putInt("index", 2);
			IntentUtil.intent(this.mContext, bundle2,MyhaoyouMainActivity.class,false);
			break;
		case R.id.lmyfans:
			Bundle bundle3 = new Bundle();
			bundle3.putInt("index", 3);
			IntentUtil.intent(this.mContext, bundle3,MyhaoyouMainActivity.class,false);
			break;
		case R.id.lmyqianbao:
			IntentUtil.intent(this.mContext, MyqianbaoActivity.class);
			break;
		case R.id.lmywork:
			Bundle bundle = new Bundle();
			bundle.putInt("index", r);
			IntentUtil.intent(this.mContext, bundle,MyworkActivity.class,false);
			break;
		case R.id.lmyfuwu:
			IntentUtil.intent(this.mContext, MyfuwuActivity.class);
			break;
		case R.id.lmyrenzheng:
			IntentUtil.intent(this.mContext, MyrenzhengActivity.class);
			break;
		case R.id.lheimingdan:
			IntentUtil.intent(this.mContext, MyheimingdanActivity.class);
			break;
		case R.id.lgengxin:
			new Thread(){
				public void run() {
					controller.myapp_version();
					handler.sendEmptyMessage(3);
				};
			}.start();

//			DialogUtil.showMessage(getActivity(), "已经是最新版本!");
			break;
		case R.id.lbangzhu:
			IntentUtil.intent(this.mContext, MyHelpActivity.class);
			break;
		case R.id.lqinglitupian:
			long size = PictureUtil.deleteFile();
			if(size == -1024){
				DialogUtil.showMessage(getActivity(), "本地缓存图片清除失败，请检查SD卡");
				break;
			}
			DialogUtil.showMessage(getActivity(), "本地缓存图片已清除,清理缓存"+size/1024+"K");
			break;
		case R.id.lexit:
			final ExitIOSStyleListDialog dialog = new ExitIOSStyleListDialog(mContext, ExitIOSStyleListDialog.DIALOG_TWO);
			dialog.setUp(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					AppManager.getAppManager().AppExit(mContext);
				}
			});
			dialog.setDown(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					dialog.closeDialog();
					new Thread(){
						public void run() {
							controller.logout();
						};
					}.start();
					System.exit(0);
					
				}
			});
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
				BitmapDrawable img = (BitmapDrawable) msg.obj;
				Bitmap bm = PictureUtil.getRoundedCornerBitmap(img.getBitmap());
				imghead.setImageBitmap(bm);
				LayoutParams pa = (LayoutParams)imghead.getLayoutParams();
				pa.height = imghead.getWidth();
//				imghead.setPadding(0, 20, 0, 20);
				break;
			case 2:
				Userinfopackage info = (Userinfopackage) controller.getContext().getBusinessData("UserinfoData");
				if(info.getLoyal_pass().equals("1")){
					imgloyal_pass.setImageResource(R.drawable.f3_13);
				}else{
					imgloyal_pass.setImageResource(R.drawable.f3_34);
				}
				tvnickname.setText(info.getNickname()+"");
				 tvmoney.setText(user.getMoney()+"");
				 tvcounthaoyou.setText(info.getCount().getFriend()+"");
				 tvcountpushuser.setText(info.getCount().getPushuser()+"");
				 tvcountguanzhu.setText(info.getCount().getFollowing()+"");
				 tvcountfans.setText(info.getCount().getFollowed()+"");
				 
				break;
			case 3:
				final AppPackage appdata = (AppPackage) controller.getContext().getBusinessData("AppData");
				if(appdata != null){

					final IOSStyleDialog dialog = new IOSStyleDialog(getActivity(),
							IOSStyleDialog.DIALOG_TWO);
					dialog.setmTitle("提示");
					dialog.setMessage("是否升级星探？");
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
							new Downloadapk(getActivity()).downloadApk(appdata.getUpdate_url(), "星探", appdata.getName()+appdata.getNumber()+".apk");
							
						}
					});
				
					}
				
				break;
			}
		}
	};
}
