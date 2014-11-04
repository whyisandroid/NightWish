package com.timetalent.client.ui.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.lidroid.xutils.BitmapUtils;
import com.timetalent.client.R;


/******************************************
 * 类描述： 动态 图片 处理
 * 类名称：DynamicPicAdapter  
 * @version: 1.0
 * @author: wanghy
 * @time: 2014-10-12 下午7:58:51 
 ******************************************/
public class DynamicPicAdapter extends  BaseAdapter{
	
	private Context mContext;
	private List<String> list;
	
	/**
	 * 类的构造方法
	 * 创建一个新的实例 DynamicAdapter.
	 * @param 
	 */
	public DynamicPicAdapter(Context mContext ,List<String> list) {
		this.mContext = mContext;
		this.list = list;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
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
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(R.layout.dynamic_pic_item, null);
		}
		ImageView iv_dynamic_pic = (ImageView)convertView.findViewById(R.id.iv_dynamic_pic);
		
		// 加载网络图片
		BitmapUtils bitmapUtils = new BitmapUtils(mContext);
		String pic = "http://124.193.223.166/xingtan/Uploads/avatar/201411/5458cf090dbd9.jpg";
		bitmapUtils.display(iv_dynamic_pic,pic);
		//iv_dynamic_pic.setImageResource(R.drawable.d3_56);
		return convertView;
	}

}
