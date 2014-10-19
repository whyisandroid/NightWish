package com.timetalent.client.ui.near;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.timetalent.client.R;
import com.timetalent.client.service.AppController;
import com.timetalent.client.ui.BaseActivity;
import com.timetalent.client.ui.GuideActivity;
import com.timetalent.client.ui.MainFragmentActivity;
import com.timetalent.client.ui.adapter.ZuopinBaseAdapter;
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
public class YirenActivity extends BaseActivity implements OnClickListener {
	private AppController controller;
	private LinearLayout ldongtai;
	private ListView lzuopin;
	private ImageView imgpic;
	private TextView main_top_right;
	private ImageButton main_top_left;
	private ImageView img1;
	private ImageView img2;
	private ImageView img3;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.near_yirenxiangqing);
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
		lzuopin = (ListView) findViewById(R.id.lzuopin);
		ldongtai = (LinearLayout) findViewById(R.id.lneardongtai);
		imgpic = (ImageView) findViewById(R.id.ImageView04);
		img1 = (ImageView) findViewById(R.id.imgduihua);
		img2 = (ImageView) findViewById(R.id.imgguanzhu);
		img3 = (ImageView) findViewById(R.id.imgyuyue);
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
		lzuopin.setAdapter(new ZuopinBaseAdapter(YirenActivity.this));
		setListViewHeightBasedOnChildren(lzuopin);
		ldongtai.setOnClickListener(this);
		imgpic.setOnClickListener(this);
		img1.setOnClickListener(this);
		img2.setOnClickListener(this);
		img3.setOnClickListener(this);
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
		case R.id.ImageView04:
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
			IntentUtil.intent(YirenActivity.this, MessageChatActivity.class);
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
