package com.timetalent.client.ui.adapter;

import com.timetalent.client.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


/******************************************
 * 类描述： TODO
 * 类名称：NearBaseAdapter  
 * @version: 1.0
 * @author: Administrator
 * @time: 2014-10-10 下午10:12:16 
 ******************************************/
public class TonggaoBaseAdapter extends BaseAdapter {

	private LayoutInflater mInflater;

	public TonggaoBaseAdapter(Context context){
		this.mInflater = LayoutInflater.from(context);

	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 5;//
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
		ViewHolder holder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.near_xingtantonggao_item,
				    null);
			holder = new ViewHolder();
			holder.imgtonggao = (ImageView) convertView.findViewById(R.id.imgtonggao);
			holder.tvtonggaoname = (TextView) convertView.findViewById(R.id.tvtonggaoname);
			holder.tvtonggaoneirong = (TextView) convertView.findViewById(R.id.tvtonggaoneirong);
			holder.tvtonggaoyaoqiu = (TextView) convertView.findViewById(R.id.tvtonggaoyaoqiu);
			convertView.setTag(holder);//绑定ViewHolder对象
		}else{
            holder = (ViewHolder)convertView.getTag();//取出ViewHolder对象 
            }

		return convertView;
	}
	class ViewHolder{
	    public ImageView imgtonggao;
	    public TextView tvtonggaoname;
	    public TextView tvtonggaoneirong;
	    public TextView tvtonggaoyaoqiu;
	    }

}
