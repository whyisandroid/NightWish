package com.timetalent.client.ui.user;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.timetalent.client.R;
import com.timetalent.client.entities.Blacklist;
import com.timetalent.client.entities.Blackpackage;
import com.timetalent.client.entities.BlankName;
import com.timetalent.client.entities.MessageItem;
import com.timetalent.client.service.AppController;
import com.timetalent.client.ui.BaseActivity;
import com.timetalent.client.ui.adapter.GuanzhuAdapter;
import com.timetalent.client.ui.adapter.HeimingdanAdapter;
import com.timetalent.client.ui.adapter.SearchBaseAdapter;
import com.timetalent.client.ui.view.ListViewCompat;


/******************************************
 * 类描述： 黑名单
 * 类名称：MyheimingdanActivity  
 * @version: 1.0
 * @author: why
 * @time: 2014-10-10 下午6:32:12 
 ******************************************/
public class MyheimingdanActivity extends BaseActivity implements OnClickListener {
	private AppController controller;
	private TextView main_top_right;
	private ImageButton main_top_left;
	private ListViewCompat lheimingdan;
	
	private List<BlankName> mBlankItems;
	HeimingdanAdapter adapter = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_heimingdan);
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
		lheimingdan = (ListViewCompat) findViewById(R.id.lheimingdan);
	}

	/**
	 * 方法描述：TODO
	 * 
	 * @author: why
	 * @time: 2014-10-10 下午6:36:02
	 */
	private void initView() {
		((TextView)this.findViewById(R.id.main_top_title)).setText("黑名单");
//		UIUtils.setDrawableLeft(this,main_top_right,R.drawable.d3_06);
		main_top_left.setVisibility(View.VISIBLE);
//		UIUtils.setDrawableLeft(this,main_top_left2,R.drawable.d3_03);
		main_top_left.setOnClickListener(this);
		
		setvalue();
		new Thread(){
			public void run() {
				controller.myblack();
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
            	mBlankItems = new ArrayList<BlankName>();
            	
            	Blacklist bl = (Blacklist) controller.getContext().getBusinessData("BlackData");
            	for(Blackpackage b:bl.getLists()){
            		mBlankItems.add(new BlankName());
            	}
            	adapter = new HeimingdanAdapter(MyheimingdanActivity.this,mBlankItems,lheimingdan);
            	lheimingdan.setAdapter(adapter);
            	adapter.notifyDataSetChanged();
                break;
            }
        }
    };
}
