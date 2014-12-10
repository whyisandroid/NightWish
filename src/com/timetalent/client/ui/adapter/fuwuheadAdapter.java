package com.timetalent.client.ui.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
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
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.timetalent.client.R;
import com.timetalent.client.entities.MyServicepackage;
import com.timetalent.client.service.AppController;
import com.timetalent.client.ui.adapter.HaoyouAdapter.ViewHolder;
import com.timetalent.client.ui.fragment.util.Background3;
import com.timetalent.client.ui.login.FindpwdFirstActivity;
import com.timetalent.client.ui.login.LoginActivity;
import com.timetalent.client.ui.user.MyfuwunextActivity;
import com.timetalent.client.ui.view.PhotoImageView;
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
			return 1;	
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
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.my_fuwu_item,
				    null);
			holder = new ViewHolder();
			holder.tvindex = (TextView) convertView.findViewById(R.id.tvindex);
			holder.l1 = (LinearLayout) convertView.findViewById(R.id.l1);
			holder.l1.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					deleteservice(v, list.get(position).getService_id());
				}
			});
			holder.l2 = (LinearLayout) convertView.findViewById(R.id.l2);
			holder.l2.setVisibility(View.GONE);
			convertView.setTag(holder);//绑定ViewHolder对象
		}else{
            holder = (ViewHolder)convertView.getTag();//取出ViewHolder对象 
            }
		if(position == 0 && list == null){
			holder.l1.setVisibility(View.GONE);
			holder.l2.setVisibility(View.VISIBLE);
		} else if(list != null && position == list.size()){
			holder.l1.setVisibility(View.GONE);
			holder.l2.setVisibility(View.VISIBLE);
		}else{
			holder.tvindex.setText(""+list.get(position).getName());
		}
		
		return convertView;
	}

	class ViewHolder{
	    public LinearLayout l1;
	    public LinearLayout l2;
	    public TextView tvindex;
	    
	    }
	public void deleteservice(View v,final String serviceid){
		LayoutInflater inflater = LayoutInflater.from(v.getContext());
		View popview = inflater.inflate(R.layout.bianji_delete, null);
		final PopupWindow pop = new PopupWindow(popview, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, false);
		pop.setBackgroundDrawable(new Background3());
		pop.setOutsideTouchable(true);
		pop.setFocusable(true);
		Button btok = (Button) popview.findViewById(R.id.btok);
		btok.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				for (int i = 0; i < list.size(); i++) {
					if(list.get(i).getService_id().equals(serviceid)){
						list.remove(i);
						break;
					}
				}
				new Thread(){
					public void run() {
						AppController.getController(((Activity)mcontext)).getContext().addBusinessData("delservice.serviceid", serviceid);
						AppController.getController(((Activity)mcontext)).myuser_delservice();
					};
				}.start();
				pop.dismiss();
				handler.sendEmptyMessage(1);
			}
		});
		int[] location = new int[2];  
		v.getLocationOnScreen(location);
		pop.showAtLocation(v, Gravity.NO_GRAVITY, location[0]-pop.getWidth()-19, location[1]-pop.getHeight()-19);
	}
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				fuwuheadAdapter.this.notifyDataSetChanged();
				break;
			default:
				break;
			}
		}
		};
}

