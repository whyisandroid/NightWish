package com.timetalent.client.ui.user;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.timetalent.client.R;
import com.timetalent.client.entities.Followinglist;
import com.timetalent.client.service.AppController;
import com.timetalent.client.ui.BaseActivity;
import com.timetalent.client.ui.adapter.DynamicAdapter;
import com.timetalent.client.ui.adapter.FansAdapter;
import com.timetalent.client.ui.adapter.GuanzhuAdapter;
import com.timetalent.client.ui.adapter.HaoyouAdapter;
import com.timetalent.client.ui.adapter.SearchBaseAdapter;
import com.timetalent.client.ui.dynamic.DynamicAddActivity;
import com.timetalent.client.ui.message.MessageChatActivity;
import com.timetalent.client.ui.near.FansActivity;
import com.timetalent.client.ui.near.XingtanActivity;
import com.timetalent.client.ui.near.YirenActivity;
import com.timetalent.common.util.IntentUtil;
import com.timetalent.common.util.UIUtils;


/******************************************
 * 类描述： 动态界面
 * 类名称：NearDongtaiActivity  
 * @version: 1.0
 * @author: why
 * @time: 2014-10-10 下午6:32:12 
 ******************************************/
public class MyguanzhuActivity extends BaseActivity implements OnClickListener {
	private AppController controller;
	private TextView main_top_right;
	private ImageButton main_top_left;
	private ListView lhaoyou;
	GuanzhuAdapter adapter = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_haoyou_fragment);
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
		lhaoyou = (ListView) findViewById(R.id.lhaoyou);
		}

	/**
	 * 方法描述：TODO
	 * 
	 * @author: why
	 * @time: 2014-10-10 下午6:36:02
	 */
	private void initView() {
		lhaoyou.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Followinglist l = (Followinglist) controller.getContext().getBusinessData("FollowingData");
				if(l != null && l.getLists() != null){
					if(l.getLists().get(arg2).getType().equals("star")){
						Bundle bundle1 = new Bundle();
						bundle1.putString("userid", l.getLists().get(arg2).getId());
						IntentUtil.intent(MyguanzhuActivity.this, bundle1,YirenActivity.class,false);
					}else if(l.getLists().get(arg2).getType().equals("scout")){
						Bundle bundle1 = new Bundle();
						bundle1.putString("userid", l.getLists().get(arg2).getId());
						IntentUtil.intent(MyguanzhuActivity.this, bundle1,XingtanActivity.class,false);
					}else{
						Bundle bundle1 = new Bundle();
						bundle1.putString("userid", l.getLists().get(arg2).getId());
						IntentUtil.intent(MyguanzhuActivity.this, bundle1,FansActivity.class,false);
					}
				}
				
			}
		});
		setvalue();
		new Thread(){
			public void run() {
				controller.myfollowing();
				handler.sendEmptyMessage(1);
			};
		}.start();
		}
	public void setvalue(){
//		controller.getContext().addBusinessData("search.search",main_top_title.getText().toString());
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.main_top_left:
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
            	adapter = new GuanzhuAdapter(MyguanzhuActivity.this);
            	lhaoyou.setAdapter(adapter);
            	adapter.notifyDataSetChanged();
                break;
            }
        }
    };
}
