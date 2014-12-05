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
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RadioButton;
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
public class fuwunextAdapter extends BaseAdapter{
	private LayoutInflater mInflater;
	List<Servicelistpackage> addlist;
	List<MyServicepackage> list;
	Context mcontext;
	public fuwunextAdapter(Context context){
		mInflater = LayoutInflater.from(context);
		mcontext = context;
		addlist = (List<Servicelistpackage>) AppController.getController(((Activity)context)).getContext().getBusinessData("addlist");
		list = new ArrayList<MyServicepackage>();
		if(addlist!= null){
			for(Servicelistpackage p:addlist){
				MyServicepackage t = new MyServicepackage();
				t.setService_id(p.getId());
				t.setMoney("0");
				t.setUnit("按时");
				list.add(t);
			}
		}
	}
	/* (non-Javadoc)
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount() {
		if(addlist != null){
			return addlist.size();
		}else{
			return 0;	
		}
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
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.my_fuwunextitem,
				    null);
			holder = new ViewHolder();
			holder.tvname = (TextView) convertView.findViewById(R.id.tvname);
			holder.etjiage =(EditText) convertView.findViewById(R.id.etjiage);
			holder.etjiage.setOnFocusChangeListener(new OnFocusChangeListener() {
				
				@Override
				public void onFocusChange(View v, boolean hasFocus) {
					MyServicepackage t = list.get(position);
					t.setMoney(((EditText)v).getText().toString());
					list.set(position, t);
					AppController.getController(((Activity)mcontext)).getContext().addBusinessData("addeditservice", list);
				}
			});
			
			holder.rb1 = (RadioButton) convertView.findViewById(R.id.rb1);
			holder.rb2 = (RadioButton) convertView.findViewById(R.id.rb2);
			holder.rb1.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					MyServicepackage t = list.get(position);
					t.setUnit("按时");
					list.set(position, t);
					AppController.getController(((Activity)mcontext)).getContext().addBusinessData("addeditservice", list);
				}
			});
			holder.rb2.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					MyServicepackage t = list.get(position);
					t.setUnit("按件");
					list.set(position, t);
					AppController.getController(((Activity)mcontext)).getContext().addBusinessData("addeditservice", list);
				}
			});
			convertView.setTag(holder);//绑定ViewHolder对象
		}else{
            holder = (ViewHolder)convertView.getTag();//取出ViewHolder对象 
            }
		holder.tvname.setText(""+addlist.get(position).getName());
		return convertView;
	}

	class ViewHolder{
	    public TextView tvname;
	    public EditText etjiage;
	    RadioButton rb1;
	    RadioButton rb2;
	    }
}
