package com.timetalent.client.ui.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

import com.timetalent.client.R;
import com.timetalent.client.entities.Myapplytasklist;
import com.timetalent.client.entities.Myfansyaoqinglist;
import com.timetalent.client.entities.Myfansyaoqingpackage;
import com.timetalent.client.service.AppController;
import com.timetalent.client.ui.adapter.FansYaoqingAdapter.ViewHolder;
import com.timetalent.client.ui.fragment.util.Background1;
import com.timetalent.client.ui.near.FansActivity;
import com.timetalent.client.ui.near.XingtanActivity;
import com.timetalent.client.ui.near.YirenActivity;
import com.timetalent.common.util.IntentUtil;
import com.timetalent.common.util.PictureUtil;
import com.timetalent.common.util.ProgressDialogUtil;


/******************************************
 * 类描述： 机会 adapter
 * 类名称：DynamicAdapter  
 * @version: 1.0
 * @author: wanghy
 * @time: 2014-10-12 下午7:58:51 
 ******************************************/
public class YirenWorkAdapter extends BaseAdapter{
	
	private Context mContext;
	private LayoutInflater mInflater;
	AppController controller;
	Myapplytasklist data = null;
	List<Drawable> icons = new ArrayList<Drawable>();
	public float density = 1.0f;
	/**
	 * 类的构造方法
	 * 创建一个新的实例 DynamicAdapter.
	 * @param 
	 */
	public YirenWorkAdapter(Context mContext,AppController c) {
		this.mContext = mContext;
		this.mInflater = LayoutInflater.from(mContext);
		controller = c;
		DisplayMetrics dm2 = mContext.getResources().getDisplayMetrics();
		density = dm2.density;
		new Thread(){
			public void run() {
				controller.myapplytasklists();
				data = (Myapplytasklist) controller.getContext().getBusinessData("MyapplytaskData");
				handler.sendEmptyMessage(1);
//				if(icons == null){
//					icons = new ArrayList<Drawable>();
//				}else{
//					icons.clear();
//				}
//				if(data == null || data.getLists() == null){
//					return;
//				}
//				for (int i = 0; i < data.getLists().size(); i++) {
//					icons.add(new Background1());
//				}
//						for (int i = 0; i < data.getLists().size(); i++) {
//							Drawable bd1 = PictureUtil.getImage(data.getLists().get(i).getUser().getAvatar(), data.getLists().get(i).getTarget_id(), "head");
//							BitmapDrawable bd = null;
//							if(bd1 instanceof BitmapDrawable){
//								bd = (BitmapDrawable) bd1;
//							}
//							
//							if(bd == null){
//								handler.sendEmptyMessage(1);
//								continue;
//							}
//							Bitmap temp = bd.getBitmap();
//							if(temp == null){
//								handler.sendEmptyMessage(1);
//								continue;
//							}
//							Bitmap bm = PictureUtil.getRoundedCornerBitmap(temp);
//							icons.set(i, new BitmapDrawable(bm));
//							handler.sendEmptyMessage(1);
//						}
			};
		}.start();
	}
	
	@Override
	public int getCount() {
		if(data != null && data.getLists() != null){
			return data.getLists().size();
		}else{
			return 0;
		}
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.chance_list_item,
				    null);
			holder = new ViewHolder();
			holder.relativeLayout1 = (RelativeLayout) convertView.findViewById(R.id.relativeLayoutroot);
			holder.tv_chance_title = (TextView) convertView.findViewById(R.id.tv_chance_title);
			holder.tv_chance_address = (TextView) convertView.findViewById(R.id.tv_chance_address);
			holder.tv_chance_time = (TextView) convertView.findViewById(R.id.tv_chance_time);
			convertView.setTag(holder);//绑定ViewHolder对象
		}else{
            holder = (ViewHolder)convertView.getTag();//取出ViewHolder对象 
            }
		if(data!= null && data.getLists()!= null){
			holder.tv_chance_title.setText(""+data.getLists().get(position).getTask().getTitle());
			holder.tv_chance_address.setText(""+data.getLists().get(position).getTask().getPlace());
			holder.tv_chance_time.setText("报名截止时间："+data.getLists().get(position).getTask().getCutoff_date());
			holder.relativeLayout1.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					new Thread(new Runnable() {
						@Override
						public void run() {
							controller.getContext().addBusinessData("chance_detail_id", data.getLists().get(position).getTask_id());
							controller.chanceDetails();
							ProgressDialogUtil.closeProgressDialog();
						}  
					}).start();
					
				}
			});
		}
		return convertView;
	}
	class ViewHolder{
	    public ImageView iv_chance_rect;
	    public TextView tv_chance_title;
	    public TextView tv_chance_address;
	    public RelativeLayout relativeLayout1;
	    public TextView tv_chance_time;
	    }
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// super.handleMessage(msg);
			switch (msg.what) {
			case 1:
					YirenWorkAdapter.this.notifyDataSetChanged();
				break;
			case 2:
				YirenWorkAdapter.this.notifyDataSetChanged();
				break;
			}
		}
	};
	class myOnClickListener implements OnClickListener{
		Myfansyaoqingpackage data;
		public myOnClickListener(Myfansyaoqingpackage followedpackage) {
			data = followedpackage;
		}
		@Override
		public void onClick(View v) {
			if(data.getUser().getType().equals("star")){
				Bundle bundle1 = new Bundle();
				bundle1.putString("userid", data.getTarget_id());
				IntentUtil.intent(mContext, bundle1,YirenActivity.class,false);
			}else if(data.getUser().getType().equals("scout")){
				Bundle bundle1 = new Bundle();
				bundle1.putString("userid", data.getTarget_id());
				IntentUtil.intent(mContext, bundle1,XingtanActivity.class,false);
			}else if(data.getUser().getType().equals("fans")){
				Bundle bundle1 = new Bundle();
				bundle1.putString("userid", data.getTarget_id());
				IntentUtil.intent(mContext, bundle1,FansActivity.class,false);
			}
			
		}
		
	}
}
