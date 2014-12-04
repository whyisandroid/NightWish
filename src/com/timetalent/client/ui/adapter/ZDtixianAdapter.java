package com.timetalent.client.ui.adapter;

import java.util.ArrayList;
import java.util.List;

import com.timetalent.client.R;
import com.timetalent.client.entities.Followedlist;
import com.timetalent.client.entities.Followedpackage;
import com.timetalent.client.entities.Followingpackage;
import com.timetalent.client.entities.Searchlist;
import com.timetalent.client.service.AppController;
import com.timetalent.client.ui.adapter.GuanzhuAdapter.myOnClickListener;
import com.timetalent.client.ui.fragment.util.Background1;
import com.timetalent.client.ui.near.FansActivity;
import com.timetalent.client.ui.near.XingtanActivity;
import com.timetalent.client.ui.near.YirenActivity;
import com.timetalent.common.util.IntentUtil;
import com.timetalent.common.util.PictureUtil;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;


/******************************************
 * 类描述： TODO
 * 类名称：NearBaseAdapter  
 * @version: 1.0
 * @author: Administrator
 * @time: 2014-10-10 下午10:12:16 
 ******************************************/
public class ZDtixianAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	Followedlist data = null;
	Context mcontext;
	public ZDtixianAdapter(Context context){
		this.mInflater = LayoutInflater.from(context);
		this.mcontext = context;
		new Thread(){
			public void run() {
				 AppController.getController(((Activity)ZDtixianAdapter.this.mcontext)).mywithdeaw_lists();
				 handler.sendEmptyMessage(1);
			}
		}.start();
	}
	@Override
	public int getCount() {
		if(data != null && data.getLists()!= null){
			return data.getLists().size();
		}else{
			return 0;	
		}
		//
	}

	
	/* (non-Javadoc)
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	
	/* (non-Javadoc)
	 * @see android.widget.Adapter#getItemId(int)
	 */
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	/* (non-Javadoc)
	 * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder = new ViewHolder();;
		if (convertView == null) {
			if(data!= null && data.getLists().size() > position){
				convertView = mInflater.inflate(R.layout.my_zhangdan_tixian_item,
					    null);
				holder.tvdanhao = (TextView) convertView.findViewById(R.id.tvdanhao);
				holder.tvjine = (TextView) convertView.findViewById(R.id.tvjine);
				holder.tvtime = (TextView) convertView.findViewById(R.id.tvtime);
				holder.tvstate = (TextView) convertView.findViewById(R.id.tvstate);
				convertView.setTag(holder);//绑定ViewHolder对象
			}
		}else{
            holder = (ViewHolder)convertView.getTag();//取出ViewHolder对象 
            }
//		holder.tvdanhao.setText(""+data.getLists().get(position).getNickname());
//		holder.tvjine.setText(data.getLists().get(position).getAge()+"岁");
//		holder.tvtime.setText("职业/"+data.getLists().get(position).getType());
//		holder.tvstate.setText(""+data.getLists().get(position).getCity());
		return convertView;
	}
	class ViewHolder{
	    public TextView tvdanhao;
	    public TextView tvjine;
	    public TextView tvtime;
	    public TextView tvstate;
	    
	    }
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// super.handleMessage(msg);
			switch (msg.what) {
			case 1:
				data = (Followedlist) AppController.getController(((Activity)mcontext)).getContext().getBusinessData("FollowedData");
				ZDtixianAdapter.this.notifyDataSetChanged();
				break;
			case 2:
				break;
			case 3:
				break;
			}
		}
	};
	class myOnClickListener implements OnClickListener{
		Followedpackage data;
		public myOnClickListener(Followedpackage followedpackage) {
			data = followedpackage;
		}
		@Override
		public void onClick(View v) {
			if(data.getType().equals("star")){
				Bundle bundle1 = new Bundle();
				bundle1.putString("userid", data.getId());
				IntentUtil.intent(mcontext, bundle1,YirenActivity.class,false);
			}else if(data.getType().equals("scout")){
				Bundle bundle1 = new Bundle();
				bundle1.putString("userid", data.getId());
				IntentUtil.intent(mcontext, bundle1,XingtanActivity.class,false);
			}else if(data.getType().equals("fans")){
				Bundle bundle1 = new Bundle();
				bundle1.putString("userid", data.getId());
				IntentUtil.intent(mcontext, bundle1,FansActivity.class,false);
			}
			
		}
		
	}
}
