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
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.timetalent.client.R;
import com.timetalent.client.ui.dynamic.DynamicMyActivity;
import com.timetalent.client.ui.near.PictureActivity;
import com.timetalent.client.ui.view.HorizontalListView;
import com.timetalent.common.util.IntentUtil;
import com.timetalent.common.util.UIUtils;


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
		HorizontalListView lv_dynamic_pic = (HorizontalListView)convertView.findViewById(R.id.hlv_dynamic_pic);
		
		List<String> list = new ArrayList<String>();
		list.add("http://e.hiphotos.baidu.com/image/pic/item/bba1cd11728b4710d5f390f6c0cec3fdfc03232f.jpg");
		list.add("http://d.hiphotos.baidu.com/image/pic/item/810a19d8bc3eb1356040da81a51ea8d3fd1f4400.jpg");
		list.add("http://b.hiphotos.baidu.com/image/pic/item/f2deb48f8c5494eec9b562232ef5e0fe99257e70.jpg");
		list.add("http://f.hiphotos.baidu.com/image/pic/item/5fdf8db1cb1349544c89855e554e9258d1094a70.jpg");
		list.add("http://a.hiphotos.baidu.com/image/pic/item/0d338744ebf81a4ca1a7833ad52a6059242da6a8.jpg");
		list.add("http://a.hiphotos.baidu.com/image/pic/item/0d338744ebf81a4ca1a7833ad52a6059242da6a8.jpg");
		list.add("http://a.hiphotos.baidu.com/image/pic/item/0d338744ebf81a4ca1a7833ad52a6059242da6a8.jpg");
		list.add("http://a.hiphotos.baidu.com/image/pic/item/0d338744ebf81a4ca1a7833ad52a6059242da6a8.jpg");
		list.add("http://a.hiphotos.baidu.com/image/pic/item/0d338744ebf81a4ca1a7833ad52a6059242da6a8.jpg");
		list.add("http://a.hiphotos.baidu.com/image/pic/item/0d338744ebf81a4ca1a7833ad52a6059242da6a8.jpg");
		
		DynamicPicAdapter adapter = new DynamicPicAdapter(mContext,list);
		lv_dynamic_pic.setAdapter(adapter); 
		
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
