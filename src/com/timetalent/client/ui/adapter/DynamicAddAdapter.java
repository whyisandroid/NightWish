package com.timetalent.client.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.timetalent.client.R;


/******************************************
 * 类描述： TODO
 * 类名称：DynamicAddAdapter  
 * @version: 1.0
 * @author: why
 * @time: 2014-11-6 下午8:50:11 
 ******************************************/
public class DynamicAddAdapter extends BaseAdapter{
	
	private Context mContext;
	
	/**
	 * 类的构造方法
	 * 创建一个新的实例 DynamicAdapter.
	 * @param 
	 */
	public DynamicAddAdapter(Context mContext) {
		this.mContext = mContext;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 1;
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
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(R.layout.dynamic_add_item, null);
		}
		return convertView;
	}
}
