package com.timetalent.client.ui.adapter;

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
import com.timetalent.client.service.AppController;
import com.timetalent.client.ui.adapter.HaoyouAdapter.ViewHolder;
import com.timetalent.client.ui.login.FindpwdFirstActivity;
import com.timetalent.client.ui.login.LoginActivity;
import com.timetalent.client.ui.user.MyfuwunextActivity;
import com.timetalent.common.util.IntentUtil;


/******************************************
 * 类描述： 机会 adapter
 * 类名称：DynamicAdapter  
 * @version: 1.0
 * @author: wanghy
 * @time: 2014-10-12 下午7:58:51 
 ******************************************/
public class fuwuheadAdapter extends BaseAdapter{
	private LayoutInflater mInflater;
	List<MyServicepackage> list;
	Context mcontext;
	public fuwuheadAdapter(Context context){
		mInflater = LayoutInflater.from(context);
		mcontext = context;
		list = (List<MyServicepackage>) AppController.getController(((Activity)context)).getContext().getBusinessData("MyserviceData");
	
	}
	/* (non-Javadoc)
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount() {
		if(list != null){
			return list.size()+1;
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
		ViewHolder holder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.my_fuwu_item,
				    null);
			holder = new ViewHolder();
			holder.tvindex = (TextView) convertView.findViewById(R.id.tvindex);
			holder.l1 = (LinearLayout) convertView.findViewById(R.id.l1);
			holder.l2 = (LinearLayout) convertView.findViewById(R.id.l2);
			holder.l2.setVisibility(View.GONE);
			convertView.setTag(holder);//绑定ViewHolder对象
		}else{
            holder = (ViewHolder)convertView.getTag();//取出ViewHolder对象 
            }
		if(position == list.size()){
			holder.l1.setVisibility(View.GONE);
			holder.l2.setVisibility(View.VISIBLE);
//			holder.l2.setOnClickListener(new OnClickListener() {
//				
//				@Override
//				public void onClick(View v) {
//					IntentUtil.intent(mcontext, MyfuwunextActivity.class);
//				}
//			});
		}else{
			holder.tvindex.setText(""+list.get(position).getService_id());
		}
		
		return convertView;
	}

	class ViewHolder{
	    public LinearLayout l1;
	    public LinearLayout l2;
	    public TextView tvindex;
	    
	    }
}
