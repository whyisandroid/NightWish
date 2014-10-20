package com.timetalent.client.ui.near;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.timetalent.client.R;
import com.timetalent.client.service.AppController;
import com.timetalent.client.ui.BaseActivity;
import com.timetalent.client.ui.chance.OfferInfoActivity;
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
public class FansActivity extends BaseActivity implements OnClickListener {
	private AppController controller;
	private TextView main_top_right;
	private ImageButton main_top_left;
	private LinearLayout ldongtai;
	private LinearLayout img1;
	private LinearLayout img2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.near_fansxiangqing);
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
		main_top_left = (ImageButton)this.findViewById(R.id.main_top_left);
		ldongtai = (LinearLayout) findViewById(R.id.lneardongtai);
		img1 = (LinearLayout) findViewById(R.id.imgduihua);
		img2 = (LinearLayout) findViewById(R.id.imgguanzhu);
	}

	/**
	 * 方法描述：TODO
	 * 
	 * @author: why
	 * @time: 2014-10-10 下午6:36:02
	 */
	private void initView() {
		((TextView)this.findViewById(R.id.main_top_title)).setText("吴沐熙vicky");
//		UIUtils.setDrawableLeft(this,main_top_right,R.drawable.d3_06);
		main_top_left.setVisibility(View.VISIBLE);
//		UIUtils.setDrawableLeft(this,main_top_left2,R.drawable.d3_03);
		main_top_left.setOnClickListener(this);
		ldongtai.setOnClickListener(this);
		img1.setOnClickListener(this);
		img2.setOnClickListener(this);
	}
	
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.main_top_left:
			finish();
			break;
		case R.id.lneardongtai:
			IntentUtil.intent(FansActivity.this, NearDongtaiActivity.class);
			break;
		case R.id.imgduihua:
			IntentUtil.intent(FansActivity.this, MessageChatActivity.class);
			break;
		case R.id.imgguanzhu:
			showMessageTwo(FansActivity.this, "关注？", "完成");
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
}
