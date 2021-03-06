package com.timetalent.client.ui.chance;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.timetalent.client.R;
import com.timetalent.client.entities.ShareMessage;
import com.timetalent.client.entities.TaskShowData;
import com.timetalent.client.service.AppController;
import com.timetalent.client.ui.BaseActivity;
import com.timetalent.client.ui.adapter.ChanceDetailAdapter;
import com.timetalent.client.ui.esaemob.ChatActivity;
import com.timetalent.common.util.ProgressDialogUtil;
import com.timetalent.common.util.ShareUtil;
import com.timetalent.common.util.StringUtil;
import com.timetalent.common.util.UIUtils;


/******************************************
 * 类描述： 职位详情
 * 类名称：OfferDetailActivity  
 * @version: 1.0
 * @author: why
 * @time: 2014-10-14 下午8:49:03 
 ******************************************/
public class OfferDetailActivity extends BaseActivity implements OnClickListener {
	private AppController controller;
	private TextView main_top_right;
	private ListView lv_chance_detail;
	private TextView bt_chance_offer;
	
	
	private TextView tv_chance_title;
	private TextView tv_chance_time;
	private TextView tv_offer_detail_address;
	private TextView tv_chance_address;
	
	private LinearLayout ll_chance_offer_message;
	private TextView tv_chance_detail_des;
	
	
	private TaskShowData data;
	
	private Handler handler  = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0:
				Intent intent = new Intent(OfferDetailActivity.this,ChatActivity.class);
				intent.putExtra("userId", StringUtil.getEsaeUserName(data.getUser_id()));
				intent.putExtra("nickName", data.getUser().getNickname());
				startActivity(intent);
				break;
			default:
				break;
			}
		};
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chance_offer_detail_layout);
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
		main_top_right = (TextView)findViewById(R.id.main_top_right);
		lv_chance_detail = (ListView)findViewById(R.id.lv_chance_detail);
		bt_chance_offer = (TextView)findViewById(R.id.bt_chance_offer);
		tv_chance_detail_des = (TextView)findViewById(R.id.tv_chance_detail_des);
		 
		
		tv_chance_title = (TextView)findViewById(R.id.tv_chance_title);
		tv_chance_time = (TextView)findViewById(R.id.tv_chance_time);
		tv_offer_detail_address = (TextView)findViewById(R.id.tv_offer_detail_address);
		ll_chance_offer_message = (LinearLayout)findViewById(R.id.ll_chance_offer_message);
		tv_chance_address = (TextView)findViewById(R.id.tv_chance_address);
	}
	/**
	 * 方法描述：TODO
	 * 
	 * @author: why
	 * @time: 2014-10-10 下午6:36:02
	 */
	private void initView() {
		((TextView)findViewById(R.id.main_top_title)).setText("职位详情");
		main_top_right.setVisibility(View.GONE);
		main_top_right.setText("分享");
		main_top_right.setOnClickListener(this);
		bt_chance_offer.setOnClickListener(this);
		ll_chance_offer_message.setOnClickListener(this);
		setData();
	}
	
	/**
	  * 方法描述：TODO
	  * @author: wanghy
	  * @time: 2014-11-8 下午6:26:06
	  */
	private void setData() {
		data = (TaskShowData)controller.getContext().getBusinessData("Task_lists_detail");
		if(data != null){
			tv_chance_title.setText(data.getTitle());
			tv_chance_time.setText("报名截止日期："+StringUtil.transformTime(data.getCutoff_date()));
			tv_offer_detail_address.setText(data.getPlace());
			tv_chance_detail_des.setText(data.getDescription());
			tv_chance_address.setText(data.getPlace());
			// 处理图片
			//ImageLoader.getInstance().displayImage(data.getBanner(),iv_chance_banner);
			ChanceDetailAdapter adapter  = new  ChanceDetailAdapter(this,data.getJobs());
			lv_chance_detail.setAdapter(adapter);
			UIUtils.setListViewHeight(lv_chance_detail, adapter);
		}
	}
	
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.main_top_right:
			ShareMessage share = new  ShareMessage("今年杨坤20岁演唱会报名开始");
			ShareUtil.showShare(OfferDetailActivity.this, share);
			break;
		case R.id.bt_chance_offer:
		case R.id.ll_chance_offer_message:
			ProgressDialogUtil.showProgressDialog(this, "通信中…", false);
			new Thread(new Runnable() {
				@Override
				public void run() {
					controller.getContext().addBusinessData("Chat.target_id",data.getUser_id() );
					controller.chatAccess(data.getUser_id(),data.getUser().getNickname(),data.getUser().getAvatar());
					//controller.chatAccess("10060","看看","http://118.144.74.24//xingtan/Uploads/avatar/201412/5486a52489e43.jpeg");
					ProgressDialogUtil.closeProgressDialog();
				}
			}).start();
			//IntentUtil.intent(OfferDetailActivity.this,ChatActivity.class);
			break;
		default:
			break;
		}
	}
}
