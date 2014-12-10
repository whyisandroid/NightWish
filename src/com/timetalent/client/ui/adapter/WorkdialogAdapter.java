package com.timetalent.client.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.timetalent.client.R;
import com.timetalent.client.ui.MainFragmentActivity;
import com.timetalent.client.ui.adapter.NearBaseAdapter.ViewHolder;
import com.timetalent.client.ui.chance.OfferInfoActivity;
import com.timetalent.client.ui.dialog.IOSStyleDialog;
import com.timetalent.client.ui.near.XingtanActivity;
import com.timetalent.client.ui.pay.MychongzhiActivity;
import com.timetalent.client.ui.pay.MychongzhifangshiActivity;
import com.timetalent.common.util.IntentUtil;


/******************************************
 * 类描述： 机会 adapter
 * 类名称：DynamicAdapter  
 * @version: 1.0
 * @author: wanghy
 * @time: 2014-10-12 下午7:58:51 
 ******************************************/
public class WorkdialogAdapter extends BaseAdapter{
	
	private Context mContext;
	private LayoutInflater mInflater;
	/**
	 * 类的构造方法
	 * 创建一个新的实例 DynamicAdapter.
	 * @param 
	 */
	public WorkdialogAdapter(Context mContext) {
		this.mContext = mContext;
		this.mInflater = LayoutInflater.from(mContext);
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
		ViewHolder holder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.my_workdialog_item,
				    null);
			holder = new ViewHolder();
			holder.tvmiaoshu = (TextView) convertView.findViewById(R.id.tvmiaoshu);
			holder.tvcount = (TextView) convertView.findViewById(R.id.tvcount);
			convertView.setTag(holder);//绑定ViewHolder对象
		}else{
            holder = (ViewHolder)convertView.getTag();//取出ViewHolder对象 
            }
		return convertView;
	}
	class ViewHolder{
	    public TextView tvmiaoshu;
	    public TextView tvcount;
	    }
}
