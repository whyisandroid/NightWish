package com.timetalent.client.ui.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.timetalent.client.R;
import com.timetalent.client.entities.ChatMsg;


/******************************************
 * 类描述： 对话 adapter
 * 类名称：MessageChatAdapter  
 * @version: 1.0
 * @author: wanghy
 * @time: 2014-10-12 下午7:58:51 
 ******************************************/
public class MessageChatAdapter extends BaseAdapter{
	
	
	public static interface IMsgViewType {
		int IMVT_COM_MSG = 0;
		int IMVT_TO_MSG = 1;
	}
	
	private List<ChatMsg> list;

	
	private Context mContext;
	
	private LayoutInflater mInflater;
	
	/**
	 * 类的构造方法
	 * 创建一个新的实例 DynamicAdapter.
	 * @param 
	 */
	public MessageChatAdapter(Context mContext, List<ChatMsg> list) {
		this.mContext = mContext;
		this.list = list;
		mInflater = LayoutInflater.from(mContext);
	}
	
	@Override
	public int getCount() {
		return list.size();
	}

	
	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	
	@Override
	public long getItemId(int position) {
		return position;
	}

	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		ChatMsg chat = list.get(position);
		boolean isComMsg = chat.getMsgType();
		ViewHolder viewHolder = null;
		if (convertView == null) {
			if (isComMsg) {
				convertView = mInflater.inflate(R.layout.message_chat_left,null);
			} else {
				convertView = mInflater.inflate(R.layout.message_chat_right,null);
			}
			viewHolder = new ViewHolder();
			viewHolder.tvContent = (TextView)convertView.findViewById(R.id.tv_chatcontent);
			viewHolder.tvSendTime = (TextView)convertView.findViewById(R.id.tv_sendtime);
			viewHolder.isComMsg = isComMsg;			
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.tvContent.setText(chat.getText());	
		viewHolder.tvSendTime.setText(chat.getDate());	
		return convertView;
	}
	
	static class ViewHolder {
		public TextView tvSendTime;
		public TextView tvUserName;
		public TextView tvContent;
		public TextView tvTime;
		public boolean isComMsg = true;
	}
}
