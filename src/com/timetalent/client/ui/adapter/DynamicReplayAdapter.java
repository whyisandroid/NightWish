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

import com.nostra13.universalimageloader.core.ImageLoader;
import com.timetalent.client.R;
import com.timetalent.client.entities.Replay;
import com.timetalent.client.entities.Task;
import com.timetalent.client.ui.adapter.DynamicAdapter.ViewHolder;
import com.timetalent.client.ui.chance.OfferAddActivity;
import com.timetalent.common.util.StringUtil;


/******************************************
 * 类描述： 动态 回复 adapter
 * 类名称：DynamicReplayAdapter  
 * @version: 1.0
 * @author: wanghy
 * @time: 2014-10-12 下午7:58:51 
 ******************************************/
public class DynamicReplayAdapter extends BaseAdapter{
	
	private Context mContext;
	private List<Replay> lists;
	
	/**
	 * 类的构造方法
	 * 创建一个新的实例 DynamicAdapter.
	 * @param 
	 */
	public DynamicReplayAdapter(Context mContext,List<Replay> lists) {
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
		Replay replay = lists.get(position);
		holder.tv_dynamic_replay_name.setText(replay.getName()+":");
		holder.tv_dynamic_replay_content.setText(replay.getName());
		return convertView;
	}
	
	static class ViewHolder{
		private TextView tv_dynamic_replay_name;
		private TextView tv_dynamic_replay_content;
		
		public void findView(View convertView){
			tv_dynamic_replay_name = (TextView)convertView.findViewById(R.id.tv_chance_title);
			tv_dynamic_replay_content = (TextView)convertView.findViewById(R.id.tv_dynamic_replay_content);
		}
	}

}