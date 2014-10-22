package com.timetalent.client.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.timetalent.client.R;
import com.timetalent.common.util.StringUtil;


/******************************************
 * 类描述： 机会 adapter
 * 类名称：DynamicAdapter  
 * @version: 1.0
 * @author: wanghy
 * @time: 2014-10-12 下午7:58:51 
 ******************************************/
public class OfferAddAdapter extends BaseAdapter{
	
	private Context mContext;
	private int num = 0;
	
	/**
	 * 类的构造方法
	 * 创建一个新的实例 DynamicAdapter.
	 * @param 
	 */
	public OfferAddAdapter(Context mContext,int num) {
		this.mContext = mContext;
		this.num = num;
	}
	
	@Override
	public int getCount() {
		return num;
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
			convertView = LayoutInflater.from(mContext).inflate(R.layout.chance_offer_add_item, null);
		}
		
		final	TextView tv_chance_data2 = (TextView)convertView.findViewById(R.id.tv_chance_data2);
		RelativeLayout	rl_chance_data2 = (RelativeLayout)convertView.findViewById(R.id.rl_chance_data2);
		final	TextView	tv_chance_data3 = (TextView)convertView.findViewById(R.id.tv_chance_data3);
		RelativeLayout rl_chance_data3 = (RelativeLayout)convertView.findViewById(R.id.rl_chance_data3);
		
		rl_chance_data2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				StringUtil.getData(mContext,tv_chance_data2);
			}
		});
		rl_chance_data3.setOnClickListener(new  OnClickListener() {
			@Override
			public void onClick(View v) {
				StringUtil.getData(mContext,tv_chance_data3);
			}
		});
		return convertView;
	}

}
