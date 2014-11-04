package com.timetalent.client.ui.adapter;

import com.timetalent.client.R;
import com.timetalent.client.entities.Followedlist;
import com.timetalent.client.entities.Friendlist;
import com.timetalent.client.service.AppController;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


/******************************************
 * 类描述： TODO
 * 类名称：NearBaseAdapter  
 * @version: 1.0
 * @author: Administrator
 * @time: 2014-10-10 下午10:12:16 
 ******************************************/
public class HaoyouAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	Friendlist data = null;
	public HaoyouAdapter(Context context){
		this.mInflater = LayoutInflater.from(context);
		data = (Friendlist) AppController.getController(((Activity)context)).getContext().getBusinessData("FriendData");
	}
	@Override
	public int getCount() {
		if(data != null){
			return data.getLists().size();
		}else{
			return 0;	
		}
		//
	}

	
	/* (non-Javadoc)
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	
	/* (non-Javadoc)
	 * @see android.widget.Adapter#getItemId(int)
	 */
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	/* (non-Javadoc)
	 * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = new ViewHolder();
		if (convertView == null) {
			if(data!= null && data.getLists().size() > position){
				convertView = mInflater.inflate(R.layout.my_haoyou_item,
					    null);
				
				holder.imghead = (ImageView) convertView.findViewById(R.id.imghead);
				holder.tvname = (TextView) convertView.findViewById(R.id.tvname);
				holder.imgsex = (ImageView) convertView.findViewById(R.id.imgsex);
				holder.tvage = (TextView) convertView.findViewById(R.id.tvage);
				holder.tvzhiye = (TextView) convertView.findViewById(R.id.tvzhiye);
				holder.tvmiaoshu = (TextView) convertView.findViewById(R.id.tvmiaoshu);
				holder.bttianjia = (ImageButton) convertView.findViewById(R.id.bttianjia);
				holder.bttianjia.setVisibility(holder.bttianjia.GONE);
				convertView.setTag(holder);//绑定ViewHolder对象
			}
			
		}else{
            holder = (ViewHolder)convertView.getTag();//取出ViewHolder对象 
            }

		return convertView;
	}
	class ViewHolder{
	    public ImageView imghead;
	    public TextView tvname;
	    public ImageView imgsex;
	    public TextView tvage;
	    public TextView tvzhiye;
	    public TextView tvmiaoshu;
	    public ImageButton bttianjia;
	    
	    }

}
