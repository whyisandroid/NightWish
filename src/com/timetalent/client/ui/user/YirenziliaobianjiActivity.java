package com.timetalent.client.ui.user;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.timetalent.client.R;
import com.timetalent.client.service.AppController;
import com.timetalent.client.ui.BaseActivity;
import com.timetalent.client.ui.GuideActivity;
import com.timetalent.client.ui.MainFragmentActivity;
import com.timetalent.client.ui.adapter.ZuopinBaseAdapter;
import com.timetalent.client.ui.fragment.util.Background2;
import com.timetalent.client.ui.fragment.util.Background3;
import com.timetalent.client.ui.near.NearDongtaiActivity;
import com.timetalent.client.ui.near.PictureActivity;
import com.timetalent.common.util.IntentUtil;


/******************************************
 * 类描述： 登录界面
 * 类名称：LoginActivity  
 * @version: 1.0
 * @author: why
 * @time: 2014-10-10 下午6:32:12 
 ******************************************/
public class YirenziliaobianjiActivity extends BaseActivity implements OnClickListener {
	private AppController controller;
	private LinearLayout ldongtai;
	private ListView lzuopin;
	private ImageView imgpic;
	private ImageView imgpic1;
	private TextView main_top_right;
	private ImageButton main_top_left;
	private LinearLayout lage;
	private LinearLayout lsanwei;
	private Button btok;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_yirenziliaobianji);
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
		lzuopin = (ListView) findViewById(R.id.lzuopin);
		ldongtai = (LinearLayout) findViewById(R.id.ldongtai);
		imgpic = (ImageView) findViewById(R.id.imageView2);
		imgpic1 = (ImageView) findViewById(R.id.ImageView05);
		main_top_right = (TextView)this.findViewById(R.id.main_top_right);
		main_top_left = (ImageButton)this.findViewById(R.id.main_top_left);
		lage = (LinearLayout) findViewById(R.id.lage);
		lsanwei = (LinearLayout) findViewById(R.id.lsanwei);
		btok = (Button) findViewById(R.id.btok);
	}

	/**
	 * 方法描述：TODO
	 * 
	 * @author: why
	 * @time: 2014-10-10 下午6:36:02
	 */
	private void initView() {
		((TextView)this.findViewById(R.id.main_top_title)).setText("吴沐熙vicky");
//		((TextView)this.findViewById(R.id.main_top_right)).setText("编辑");
//		UIUtils.setDrawableLeft(this,main_top_right,R.drawable.d3_06);
		this.findViewById(R.id.main_top_left).setVisibility(View.VISIBLE);
//		UIUtils.setDrawableLeft(this,main_top_left2,R.drawable.d3_03);
		lzuopin.setAdapter(new ZuopinBaseAdapter(YirenziliaobianjiActivity.this));
		setListViewHeightBasedOnChildren(lzuopin);
		ldongtai.setOnClickListener(this);
		imgpic.setOnClickListener(this);
		imgpic1.setOnClickListener(this);
		lage.setOnClickListener(this);
		lsanwei.setOnClickListener(this);
		btok.setOnClickListener(this);
	}
	
	/**
	 * 重新计算listview高度
	  * 方法描述：TODO
	  * @param listView
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
		case R.id.bt_login_next:
			break;
		case R.id.imageView2:
			IntentUtil.intent(YirenziliaobianjiActivity.this, PictureActivity.class);
			break;
		case R.id.ImageView05:
			LayoutInflater inflater = LayoutInflater.from(YirenziliaobianjiActivity.this);
			View popview = inflater.inflate(R.layout.bianji_delete, null);
			final PopupWindow pop = new PopupWindow(popview, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, false);
			pop.setBackgroundDrawable(new Background3());
			pop.setOutsideTouchable(true);
			pop.setFocusable(true);
			Button btok = (Button) popview.findViewById(R.id.btok);
			btok.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					imgpic1.setImageResource(R.drawable.d11_03);
					pop.dismiss();
					
				}
			});
			int[] location = new int[2];  
	        v.getLocationOnScreen(location);
			pop.showAtLocation(v, Gravity.NO_GRAVITY, location[0]-pop.getWidth()-15, location[1]-pop.getHeight()-15);
			break;
		case R.id.lneardongtai:
			IntentUtil.intent(YirenziliaobianjiActivity.this, MyDongtaiActivity.class);
			break;
		case R.id.main_top_left:
			finish();
			break;
		case R.id.lage:
			IntentUtil.intent(YirenziliaobianjiActivity.this, MyageActivity.class);
			break;
		case R.id.lsanwei:
			IntentUtil.intent(YirenziliaobianjiActivity.this, MysanweiActivity.class);
			break;
		case R.id.btok:
			finish();
			break;
		default:
			break;
		}
	}

}
