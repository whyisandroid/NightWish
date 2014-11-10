package com.timetalent.client.ui.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.timetalent.client.R;
import com.timetalent.client.entities.Task;
import com.timetalent.client.ui.adapter.DynamicAdapter.ViewHolder;
import com.timetalent.client.ui.chance.OfferAddActivity;
import com.timetalent.common.util.StringUtil;


/******************************************
 * 类描述： 机会 adapter
 * 类名称：DynamicAdapter  
 * @version: 1.0
 * @author: wanghy
 * @time: 2014-10-12 下午7:58:51 
 ******************************************/
public class ChanceAdapter extends BaseAdapter{
	
	private Context mContext;
	private List<Task> lists;
	
	/**
	 * 类的构造方法
	 * 创建一个新的实例 DynamicAdapter.
	 * @param 
	 */
	public ChanceAdapter(Context mContext,List<Task> lists) {
		this.mContext = mContext;
		this.lists = lists;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return lists.size();
	}

	
	@Override
	public Object getItem(int position) {
		return lists.get(position);
	}

	
	@Override
	public long getItemId(int position) {
		return position;
	}

	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(R.layout.chance_list_item, null);
			holder = new ViewHolder();
			holder.findView(convertView);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder)convertView.getTag();
		}
		Task task = lists.get(position);
		holder.tv_chance_title.setText(task.getTitle());
		holder.tv_chance_time.setText("报名截止时间："+task.getCutoff_date());
		return convertView;
	}
	
	static class ViewHolder{
		private ImageView iv_chance_banner;
		private TextView tv_chance_title;
		private TextView tv_chance_time;
		
		public void findView(View convertView){
			iv_chance_banner = (ImageView)convertView.findViewById(R.id.iv_chance_banner);
			tv_chance_title = (TextView)convertView.findViewById(R.id.tv_chance_title);
			tv_chance_time = (TextView)convertView.findViewById(R.id.tv_chance_time);
		}
	}

}
