package com.timetalent.client.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.timetalent.client.R;
import com.timetalent.client.ui.dynamic.DynamicMyActivity;
import com.timetalent.client.ui.near.PictureActivity;
import com.timetalent.common.util.IntentUtil;


/******************************************
 * 类描述： 动态 adapter
 * 类名称：DynamicAdapter  
 * @version: 1.0
 * @author: wanghy
 * @time: 2014-10-12 下午7:58:51 
 ******************************************/
public class DynamicAdapter extends BaseAdapter{
	
	private Context mContext;
	
	/**
	 * 类的构造方法
	 * 创建一个新的实例 DynamicAdapter.
	 * @param 
	 */
	public DynamicAdapter(Context mContext) {
		this.mContext = mContext;
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
			convertView = LayoutInflater.from(mContext).inflate(R.layout.dynamic_list_item, null);
		}
		
		ImageView iv_dynamic_head = (ImageView)convertView.findViewById(R.id.iv_dynamic_head);
		ImageView iv_dynamic_1 = (ImageView)convertView.findViewById(R.id.iv_dynamic_1);
		ImageView iv_dynamic_2 = (ImageView)convertView.findViewById(R.id.iv_dynamic_2);
		ImageView iv_dynamic_3 = (ImageView)convertView.findViewById(R.id.iv_dynamic_3);
		ImageView iv_dynamic_good = (ImageView)convertView.findViewById(R.id.iv_dynamic_good);
		final TextView tv_dynamic_add_1 = (TextView)convertView.findViewById(R.id.tv_dynamic_add_1);
		iv_dynamic_head.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				IntentUtil.intent(mContext, DynamicMyActivity.class);
			}
		});
		
		
		iv_dynamic_1.setOnClickListener(ivListener);
		iv_dynamic_2.setOnClickListener(ivListener);
		iv_dynamic_3.setOnClickListener(ivListener);
		iv_dynamic_good.setOnClickListener(new  OnClickListener() {
			
				@Override
				public void onClick(View v) {
					tv_dynamic_add_1.setVisibility(View.VISIBLE);
					tv_dynamic_add_1.animate().setDuration(2000);
					tv_dynamic_add_1.animate().alpha(0);
				}
			}
		);
		return convertView;
	}
	private OnClickListener ivListener= new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			IntentUtil.intent(mContext, PictureActivity.class);
		}
	};
}
