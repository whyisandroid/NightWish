package com.timetalent.client.ui.adapter;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.timetalent.client.R;
import com.timetalent.client.ui.dynamic.DynamicMyActivity;
import com.timetalent.client.ui.near.PictureActivity;
import com.timetalent.client.ui.view.HorizontalListView;
import com.timetalent.common.util.IntentUtil;


/******************************************
 * 类描述： 动态 adapter
 * 类名称：DynamicAdapter  
 * @version: 1.0
 * @author: wanghy
 * @time: 2014-10-12 下午7:58:51 
 ******************************************/
@SuppressLint("NewApi")
public class DynamicAdapter extends BaseAdapter{
	
	private Context mContext;
	private LayoutInflater inflater;
	
	/**
	 * 类的构造方法
	 * 创建一个新的实例 DynamicAdapter.
	 * @param 
	 */
	public DynamicAdapter(Context mContext) {
		this.mContext = mContext;
		inflater = LayoutInflater.from(mContext);
		
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 4;
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
			convertView = inflater.inflate(R.layout.dynamic_list_item, null);
		}
		
		ImageView iv_dynamic_head = (ImageView)convertView.findViewById(R.id.iv_dynamic_head);
		ImageView iv_dynamic_good = (ImageView)convertView.findViewById(R.id.iv_dynamic_good);
		final TextView tv_dynamic_add_1 = (TextView)convertView.findViewById(R.id.tv_dynamic_add_1);
		
		ImageView iv_dynamic_message = (ImageView)convertView.findViewById(R.id.iv_dynamic_message);
		final LinearLayout ll_dynamic_message = (LinearLayout)convertView.findViewById(R.id.ll_dynamic_message);
		
		// 处理图片
		HorizontalListView hl_dynamic = (HorizontalListView)convertView.findViewById(R.id.hl_dynamic);
		
		List<String> list = new ArrayList<String>();
		list.add("afahf");
		list.add("242rwerw");
		hl_dynamic.setAdapter(new DynamicPicAdapter(mContext,list)); // 为viewpager设置adapter  
		

		
		
		
		iv_dynamic_head.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				IntentUtil.intent(mContext, DynamicMyActivity.class);
			}
		});
		
		
		iv_dynamic_good.setOnClickListener(new  OnClickListener() {
			
				@Override
				public void onClick(View v) {
					tv_dynamic_add_1.setVisibility(View.VISIBLE);
					tv_dynamic_add_1.animate().setDuration(2000);
					tv_dynamic_add_1.animate().alpha(0);
				}
			}
		);
		
		iv_dynamic_message.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ll_dynamic_message.setVisibility(View.VISIBLE);
			}
		});
		return convertView;
	}
	private OnClickListener ivListener= new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			IntentUtil.intent(mContext, PictureActivity.class);
		}
	};
}
