package com.timetalent.client.ui.user;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.timetalent.client.R;
import com.timetalent.client.service.AppController;
import com.timetalent.client.ui.BaseActivity;
import com.timetalent.client.ui.adapter.DynamicAdapter;
import com.timetalent.client.ui.adapter.NearBaseAdapter;
import com.timetalent.client.ui.adapter.fuwubodyAdapter;
import com.timetalent.client.ui.adapter.fuwuheadAdapter;
import com.timetalent.client.ui.dynamic.DynamicAddActivity;
import com.timetalent.common.util.IntentUtil;
import com.timetalent.common.util.UIUtils;


/******************************************
 * 类描述： 动态界面
 * 类名称：NearDongtaiActivity  
 * @version: 1.0
 * @author: why
 * @time: 2014-10-10 下午6:32:12 
 ******************************************/
public class MyfuwuActivity extends BaseActivity implements OnClickListener {
	private AppController controller;
	private TextView main_top_right;
	private ImageButton main_top_left;
	fuwuheadAdapter headadapter;
	fuwubodyAdapter bodyadapter;
	Button btok;
	private GridView  gvhead;
	private GridView  gvbody;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_fuwu);
		controller = AppController.getController(this);
		findView();
		new Thread(){
			public void run() {
				controller.myuser_service();
				controller.myservice_list();
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
		gvhead = (GridView) findViewById(R.id.gvhead);
		gvbody = (GridView) findViewById(R.id.gvbody);
		main_top_left = (ImageButton)this.findViewById(R.id.main_top_left);
		
	}

	/**
	 * 方法描述：TODO
	 * 
	 * @author: why
	 * @time: 2014-10-10 下午6:36:02
	 */
	private void initView() {
		headadapter = new fuwuheadAdapter(MyfuwuActivity.this);
		bodyadapter = new fuwubodyAdapter(MyfuwuActivity.this);
		gvhead.setAdapter(headadapter);
		gvbody.setAdapter(bodyadapter);
		setListViewHeightBasedOnChildren(gvhead);
		setListViewHeightBasedOnChildren(gvbody);
		btok = (Button) findViewById(R.id.btok);
		((TextView)this.findViewById(R.id.main_top_title)).setText("我的服务");
//		UIUtils.setDrawableLeft(this,main_top_right,R.drawable.d3_06);
		main_top_left.setVisibility(View.VISIBLE);
//		UIUtils.setDrawableLeft(this,main_top_left2,R.drawable.d3_03);
		main_top_left.setOnClickListener(this);
		btok.setOnClickListener(this);
	}
	/**
	 * 重新计算listview高度
	  * 方法描述：TODO
	  * @param listView
	  * @author: Administrator
	  * @time: 2014-10-13 下午4:19:11
	 */
	public void setListViewHeightBasedOnChildren(GridView listView) {
        ListAdapter listAdapter = listView.getAdapter();   
        if (listAdapter == null) {
            // pre-condition  
            return;  
        }  
  
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount()/2+1; i++) {  
            View listItem = listAdapter.getView(i, null, listView);  
            listItem.measure(0, 0);  
            totalHeight += listItem.getMeasuredHeight();  
        }  
  
        ViewGroup.LayoutParams params = listView.getLayoutParams();  
        params.height = totalHeight + (listView.getVerticalFadingEdgeLength() * (listAdapter.getCount()/2));  
        listView.setLayoutParams(params);  
    }
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.main_top_left:
			finish();
			break;
		case R.id.btok:
			IntentUtil.intent(MyfuwuActivity.this, MyfuwunextActivity.class);
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
				initView();
				break;
			case 2:
				break;
			case 3:
				break;
			}
		}
	};
}
