package com.timetalent.client.ui.user;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;

import com.timetalent.client.R;
import com.timetalent.client.entities.LoginData;
import com.timetalent.client.service.AppController;
import com.timetalent.client.ui.BaseActivity;
import com.timetalent.client.ui.adapter.DynamicAdapter;
import com.timetalent.client.ui.dynamic.DynamicAddActivity;
import com.timetalent.common.util.IntentUtil;
import com.timetalent.common.util.PictureUtil;
import com.timetalent.common.util.UIUtils;


/******************************************
 * 类描述： 动态界面
 * 类名称：NearDongtaiActivity  
 * @version: 1.0
 * @author: why
 * @time: 2014-10-10 下午6:32:12 
 ******************************************/
public class MychongzhifangshiActivity extends BaseActivity implements OnClickListener {
	private AppController controller;
	private TextView main_top_right;
	private ImageButton main_top_left;
	Button btok;
	ImageView imghead;
	TextView tvnickname;
	TextView tvmoney;
	TextView tvid;
	TextView tvmoneymiaoshu;
	TextView tvmoney1;
	TextView tvname1;
	LoginData user;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_chongzhifangshi);
		controller = AppController.getController(this);
		user = (LoginData) controller.getContext().getBusinessData("loginData");
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
		btok = (Button) findViewById(R.id.btok);
		imghead = (ImageView) findViewById(R.id.imghead);
		tvnickname = (TextView) findViewById(R.id.tvnickname);
		tvmoney = (TextView) findViewById(R.id.tvmoney);
		tvid = (TextView) findViewById(R.id.tvid);
		tvmoneymiaoshu = (TextView) findViewById(R.id.tvmoneymiaoshu);
		tvmoney1 = (TextView) findViewById(R.id.tvmoney1);
		tvname1 = (TextView) findViewById(R.id.tvname1);
	}

	/**
	 * 方法描述：TODO
	 * 
	 * @author: why
	 * @time: 2014-10-10 下午6:36:02
	 */
	private void initView() {
		((TextView)this.findViewById(R.id.main_top_title)).setText("我的零钱");
//		UIUtils.setDrawableLeft(this,main_top_right,R.drawable.d3_06);
		main_top_left.setVisibility(View.VISIBLE);
//		UIUtils.setDrawableLeft(this,main_top_left2,R.drawable.d3_03);
		main_top_left.setOnClickListener(this);
		btok.setOnClickListener(this);
		tvnickname.setText(user.getNickname());
		tvmoney.setText(user.getMoney());
		tvid.setText(""+user.getId());
		tvmoneymiaoshu.setText("充值"+controller.getContext().getStringData("wallet.money")+"元");
		tvmoney1.setText(""+controller.getContext().getStringData("wallet.money"));
		tvname1.setText(""+user.getNickname());
		new Thread(){
			public void run() {
				Message msg = new Message();
				msg.what = 1;
				msg.obj = PictureUtil.getImage(user.getAvatar(), user.getId(), "head");
				handler.sendMessage(msg);
			};
		}.start();
	}
	void setvalue(){
		controller.getContext().addBusinessData("wallet.type", "alipay");
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.main_top_left:
			finish();
			break;
		case R.id.btok:
			setvalue();
			new Thread(){
				public void run() {
					controller.mycharge_order();
					controller.mycomplete_order();
				};
			}.start();
			finish();
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
			case 3:
				break;
			}
		}
	};
}
