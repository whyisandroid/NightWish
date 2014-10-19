package com.timetalent.client.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.timetalent.client.R;
import com.timetalent.client.ui.chance.OfferInfoActivity;
import com.timetalent.client.ui.dialog.DialogUtil;
import com.timetalent.common.util.IntentUtil;


/******************************************
 * 类描述： 机会 adapter
 * 类名称：DynamicAdapter  
 * @version: 1.0
 * @author: wanghy
 * @time: 2014-10-12 下午7:58:51 
 ******************************************/
public class ChanceDetailAdapter extends BaseAdapter{
	
	private Context mContext;
	
	/**
	 * 类的构造方法
	 * 创建一个新的实例 DynamicAdapter.
	 * @param 
	 */
	public ChanceDetailAdapter(Context mContext) {
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
			convertView = LayoutInflater.from(mContext).inflate(R.layout.chance_detail_list_item, null);
		}
		
		ImageView iv = (ImageView)convertView.findViewById(R.id.tv_offer_detail_qpply);
		iv.setOnClickListener(applyListener);
		return convertView;
	}
	
	private OnClickListener applyListener = new  OnClickListener() {
		
		@Override
		public void onClick(View v) {
			IntentUtil.intent(mContext, OfferInfoActivity.class);
		}
	};

}
