package com.timetalent.client.ui.pay;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

import com.timetalent.client.R;
import com.timetalent.client.entities.LoginData;
import com.timetalent.client.service.AppController;
import com.timetalent.client.ui.BaseActivity;
import com.timetalent.client.ui.adapter.DynamicAdapter;
import com.timetalent.client.ui.dynamic.DynamicAddActivity;
import com.timetalent.common.util.IntentUtil;
import com.timetalent.common.util.PictureUtil;
import com.timetalent.common.util.StringUtil;
import com.timetalent.common.util.ToastUtil;
import com.timetalent.common.util.UIUtils;


/******************************************
 * 类描述： 动态界面
 * 类名称：NearDongtaiActivity  
 * @version: 1.0
 * @author: why
 * @time: 2014-10-10 下午6:32:12 
 ******************************************/
public class MychongzhiActivity extends BaseActivity implements OnClickListener {
	private AppController controller;
	private TextView main_top_right;
	private ImageButton main_top_left;
	Button btchongzhifangshi;
	ImageView imghead;
	TextView tvnickname;
	TextView tvmoney;
	TextView tvid;
	LoginData user;
	EditText etmoney;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_chongzhi);
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
		btchongzhifangshi = (Button) findViewById(R.id.btchongzhifangshi);
		etmoney = (EditText) findViewById(R.id.etmoney);
		imghead = (ImageView) findViewById(R.id.imghead);
		tvnickname = (TextView) findViewById(R.id.tvnickname);
		tvmoney = (TextView) findViewById(R.id.tvmoney);
		tvid = (TextView) findViewById(R.id.tvid);
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
		btchongzhifangshi.setOnClickListener(this);
		tvnickname.setText(user.getNickname());
		tvmoney.setText(user.getMoney());
		tvid.setText(""+user.getId());
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
		controller.getContext().addBusinessData("wallet.money", etmoney.getText()+"");
		controller.getContext().addBusinessData("wallet.type", "alipay");
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.main_top_left:
			finish();
			break;
		case R.id.btchongzhifangshi:
			String Validate1 = StringUtil.notnull(etmoney.getText().toString());
			if(!TextUtils.isEmpty(Validate1)){
				ToastUtil.showToast(this, Validate1, ToastUtil.LENGTH_LONG);
				return;
			}
			setvalue();
			IntentUtil.intent(MychongzhiActivity.this, MychongzhifangshiActivity.class);
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
