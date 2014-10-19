package com.timetalent.client.ui.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.timetalent.client.R;
import com.timetalent.client.entities.MessageItem;
import com.timetalent.client.ui.view.ListViewCompat;
import com.timetalent.client.ui.view.SlideView;
import com.timetalent.client.ui.view.SlideView.OnSlideListener;


/******************************************
 * 类描述： 消息 adapter
 * 类名称：DynamicAdapter  
 * @version: 1.0
 * @author: wanghy
 * @time: 2014-10-12 下午7:58:51 
 ******************************************/
public class MessageDetailAdapter extends BaseAdapter{
	
	private Context mContext;
	private List<MessageItem> mMessageItems;
	 private SlideView mLastSlideViewWithStatusOn;
	 private ListViewCompat mListview;
	 
	 private int position;
	
	/**
	 * 类的构造方法
	 * 创建一个新的实例 DynamicAdapter.
	 * @param 
	 */
	public MessageDetailAdapter(Context mContext,List<MessageItem> mMessageItems,ListViewCompat mListview) {
		this.mContext = mContext;
		this.mMessageItems = mMessageItems;
		this.mListview = mListview;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mMessageItems.size();
	}

	
	@Override
	public Object getItem(int position) {
		return mMessageItems.get(position);
	}

	
	@Override
	public long getItemId(int position) {
		return position;
	}

	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        SlideView slideView = (SlideView) convertView;
        if (slideView == null) {
            View itemView = LayoutInflater.from(mContext).inflate(R.layout.message_list_item, null);

            slideView = new SlideView(mContext);
            slideView.setContentView(itemView);

            holder = new ViewHolder(slideView);
            slideView.setOnSlideListener(slideListener);
            slideView.setTag(holder);
        } else {
            holder = (ViewHolder) slideView.getTag();
        }
        MessageItem item = mMessageItems.get(position);
        item.slideView = slideView;
        item.slideView.shrink();

        holder.icon.setImageResource(item.iconRes);
        holder.title.setText(item.title);
        holder.msg.setText(item.msg);
        holder.time.setText(item.time);
        holder.deleteHolder.setOnClickListener(deleteItemListener);

        return slideView;
    
	}
	
	 private static class ViewHolder {
	        public ImageView icon;
	        public TextView title;
	        public TextView msg;
	        public TextView time;
	        public ViewGroup deleteHolder;

	        ViewHolder(View view) {
	            icon = (ImageView) view.findViewById(R.id.iv_chat_icon);
	            title = (TextView) view.findViewById(R.id.tv_chat_name);
	            msg = (TextView) view.findViewById(R.id.tv_chat_msg);
	            time = (TextView) view.findViewById(R.id.tv_chat_time);
	            deleteHolder = (ViewGroup)view.findViewById(R.id.holder);
	        }
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
                mMessageItems.remove(position);  
                MessageDetailAdapter.this.notifyDataSetChanged();  
            }   
		}
	};

}
