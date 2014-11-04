package com.timetalent.client.ui.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.timetalent.client.R;
import com.timetalent.client.entities.Blacklist;
import com.timetalent.client.entities.BlankName;
import com.timetalent.client.entities.Followedlist;
import com.timetalent.client.entities.MessageItem;
import com.timetalent.client.service.AppController;
import com.timetalent.client.ui.view.ListViewCompat;
import com.timetalent.client.ui.view.SlideView;
import com.timetalent.client.ui.view.SlideView.OnSlideListener;
import com.timetalent.common.util.ToastUtil;

/******************************************
 * 类描述： TODO 类名称：NearBaseAdapter
 * 
 * @version: 1.0
 * @author: Administrator
 * @time: 2014-10-10 下午10:12:16
 ******************************************/
public class HeimingdanAdapter extends BaseAdapter {
	private SlideView mLastSlideViewWithStatusOn;
	private LayoutInflater mInflater;
	private Context mContext;
	private List<BlankName> mBlankNameItems;
	private ListViewCompat mListview;
	Blacklist data = null;
	public HeimingdanAdapter(Context context,List<BlankName> mBlankNameItems,ListViewCompat mListview) {
		this.mInflater = LayoutInflater.from(context);
		this.mContext = context;
		this.mBlankNameItems = mBlankNameItems;
		this.mListview = mListview;
		data = (Blacklist) AppController.getController(((Activity)context)).getContext().getBusinessData("BlackData");
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mBlankNameItems.size();//
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mBlankNameItems.get(position);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getItemId(int)
	 */
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getView(int, android.view.View,
	 * android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		SlideView slideView = (SlideView) convertView;
		ViewHolder holder = new ViewHolder(slideView);
		if (slideView == null) {
			if(data!= null && data.getLists().size() > position){
				View itemView = mInflater.inflate(R.layout.near_list_item, null);
				slideView = new SlideView(mContext);
				slideView.setContentView(itemView);
				slideView.setOnSlideListener(slideListener);
				slideView.setTag(holder);
			}
			
		} else {
			 holder = (ViewHolder) slideView.getTag();// 取出ViewHolder对象
		}
		
		BlankName item = mBlankNameItems.get(position);
        item.slideView = slideView;
        item.slideView.shrink();
		
		 holder.deleteHolder.setOnClickListener(deleteItemListener);
		return slideView;
	}
	
	
	 private OnSlideListener slideListener = new  OnSlideListener() {
			
		@Override
		public void onSlide(View view, int status) {


	        if (mLastSlideViewWithStatusOn != null && mLastSlideViewWithStatusOn != view) {
	            mLastSlideViewWithStatusOn.shrink();
	        }

	        if (status == SLIDE_STATUS_ON) {
	            mLastSlideViewWithStatusOn = (SlideView) view;
	        }
	    
		
			// TODO Auto-generated method stub
			
		}
	};
	
	
	 private OnClickListener deleteItemListener =  new  OnClickListener() {
			
			@Override
			public void onClick(View view) {
				int position = mListview.getPositionForView(view);  
	            if (position != mListview.INVALID_POSITION) {  
	            	mBlankNameItems.remove(position);  
	                HeimingdanAdapter.this.notifyDataSetChanged();  
	            }   
			}
		};
	
	
	
	
	
	

	class ViewHolder {
		public ImageView imghead;
		public TextView tvname;
		public TextView tvonlinetime;
		public ImageView imgonline;
		public ImageView imgsex;
		public TextView tvage;
		public TextView tvzhiye;
		public TextView tvmiaoshu;

		public ViewGroup deleteHolder;

		ViewHolder(View convertView) {
			imghead = (ImageView) convertView.findViewById(R.id.imghead);
			tvname = (TextView) convertView.findViewById(R.id.tvname);
			imgonline = (ImageView) convertView.findViewById(R.id.imgonline);
			imgsex = (ImageView) convertView.findViewById(R.id.imgsex);
			tvage = (TextView) convertView.findViewById(R.id.tvage);
			tvzhiye = (TextView) convertView.findViewById(R.id.tvname);
			tvmiaoshu = (TextView) convertView.findViewById(R.id.tvmiaoshu);
			deleteHolder = (ViewGroup) convertView.findViewById(R.id.holder);
		}
	}

}
