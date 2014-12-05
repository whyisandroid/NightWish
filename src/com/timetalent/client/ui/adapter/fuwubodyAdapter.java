package com.timetalent.client.ui.adapter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.timetalent.client.R;
import com.timetalent.client.entities.MyServicepackage;
import com.timetalent.client.entities.Servicelistpackage;
import com.timetalent.client.service.AppController;
import com.timetalent.client.ui.adapter.HaoyouAdapter.ViewHolder;


/******************************************
 * 类描述： 机会 adapter
 * 类名称：DynamicAdapter  
 * @version: 1.0
 * @author: wanghy
 * @time: 2014-10-12 下午7:58:51 
 ******************************************/
public class fuwubodyAdapter extends BaseAdapter{//
	private LayoutInflater mInflater;
	List<Servicelistpackage> list;
	List<Servicelistpackage> addlist;
	Context mcontext;
	public fuwubodyAdapter(Context context){
		mInflater = LayoutInflater.from(context);
		mcontext = context;
		list = (List<Servicelistpackage>) AppController.getController(((Activity)context)).getContext().getBusinessData("servicelistData");
		addlist = new ArrayList<Servicelistpackage>();
	}
	/* (non-Javadoc)
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount() {if(list != null){
		return list.size();
	}else{
		return 0;	
	}}

	
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
	public View getView(final int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.my_fuwu_body_item,
				    null);
			holder = new ViewHolder();
			holder.tvindex = (TextView) convertView.findViewById(R.id.tvindex);
			holder.tvindex1 = (TextView) convertView.findViewById(R.id.tvindex1);
			holder.l1 = (LinearLayout) convertView.findViewById(R.id.l1);
			holder.l2 = (LinearLayout) convertView.findViewById(R.id.l2);
			holder.l2.setVisibility(View.GONE);
			holder.l1.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					holder.l1.setVisibility(View.GONE);
					holder.l2.setVisibility(View.VISIBLE);
					addlist.add(list.get(position));
					AppController.getController(((Activity)mcontext)).getContext().addBusinessData("addlist", addlist);
				}
			});
			holder.l2.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					holder.l1.setVisibility(View.VISIBLE);
					holder.l2.setVisibility(View.GONE);
					addlist.remove(list.get(position));
					AppController.getController(((Activity)mcontext)).getContext().addBusinessData("addlist", addlist);
				}
			});
			convertView.setTag(holder);//绑定ViewHolder对象
		}else{
            holder = (ViewHolder)convertView.getTag();//取出ViewHolder对象 
            }
		if(list != null){
			holder.tvindex.setText(""+list.get(position).getId());
			holder.tvindex1.setText(""+list.get(position).getId());
		}
		return convertView;
	}

	class ViewHolder{
	    public LinearLayout l1;
	    public LinearLayout l2;
	    public TextView tvindex;
	    public TextView tvindex1;
	    }
}
