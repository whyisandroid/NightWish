package com.timetalent.client.ui.near;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.timetalent.client.R;
import com.timetalent.client.service.AppController;
import com.timetalent.client.ui.BaseActivity;
import com.timetalent.client.ui.GuideActivity;
import com.timetalent.client.ui.MainFragmentActivity;
import com.timetalent.client.ui.adapter.NearBaseAdapter;
import com.timetalent.client.ui.adapter.SearchBaseAdapter;
import com.timetalent.common.util.IntentUtil;


/******************************************
 * 类描述： 登录界面
 * 类名称：LoginActivity  
 * @version: 1.0
 * @author: why
 * @time: 2014-10-10 下午6:32:12 
 ******************************************/
public class SearchActivity extends BaseActivity implements OnClickListener {
	private AppController controller;
	private TextView main_top_right;
	private ImageButton main_top_left;
	private EditText main_top_title;
	private ListView list;
	SearchBaseAdapter adapter = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.near_sousuo);
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
		main_top_title = (EditText) this.findViewById(R.id.main_top_title);
		list = (ListView) findViewById(R.id.listView1);
		main_top_right = (TextView) findViewById(R.id.main_top_right);
	
	}

	/**
	 * 方法描述：TODO
	 * 
	 * @author: why
	 * @time: 2014-10-10 下午6:36:02
	 */
	private void initView() {

		list.setVisibility(list.INVISIBLE);
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				if(arg2 == 1){
					IntentUtil.intent(SearchActivity.this, YirenActivity.class);
				}
				if(arg2 == 2){
					IntentUtil.intent(SearchActivity.this, XingtanActivity.class);
				}
				if(arg2 == 3){
					IntentUtil.intent(SearchActivity.this, FansActivity.class);
				}
			}
		});
		main_top_right.setOnClickListener(this);
		main_top_left.setVisibility(View.VISIBLE);
//		UIUtils.setDrawableLeft(this,main_top_left2,R.drawable.d3_03);
		main_top_left.setOnClickListener(this);
	
	}
	public void setvalue(){
		controller.getContext().addBusinessData("search.search",main_top_title.getText().toString());
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.main_top_right:
			new Thread(){
				public void run() {
					controller.search();
					handler.sendEmptyMessage(1);
				
				};
			}.start();
			break;
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
            	list.setVisibility(list.VISIBLE);
            	adapter = new SearchBaseAdapter(SearchActivity.this);
            	setvalue();
        		list.setAdapter(adapter);
            	adapter.notifyDataSetChanged();
                break;
            }
        }
    };
}
