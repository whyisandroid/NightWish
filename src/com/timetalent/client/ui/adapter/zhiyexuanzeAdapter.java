package com.timetalent.client.ui.adapter;

import java.util.List;

import com.timetalent.client.R;
import com.timetalent.client.entities.dictionarypackage;
import com.timetalent.client.service.AppController;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
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
public class zhiyexuanzeAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	List<dictionarypackage> data;
	AppController controller;
	public zhiyexuanzeAdapter(Context context,AppController c,final String type){
		this.mInflater = LayoutInflater.from(context);
		controller = c;
		new Thread(){
			public void run() {
				controller.getContext().addBusinessData("dictionary.type",type );
				controller.dictionary();
				handler.sendEmptyMessage(1);
			};
		}.start();
		
	}
	@Override
	public int getCount() {
		if(data != null){
			return data.size()+1;
		}else{
			return 1;
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
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.sp_item,
				    null);
			holder = new ViewHolder();
			holder.tvsrc = (TextView) convertView.findViewById(R.id.tvsrc);
			convertView.setTag(holder);//绑定ViewHolder对象
		}else{
            holder = (ViewHolder)convertView.getTag();//取出ViewHolder对象 
            }
		if(position == 0){
			holder.tvsrc.setOnClickListener(null);
			holder.tvsrc.setText("选择职业");
		}
		if(data != null && position > 0){
			holder.tvsrc.setText(""+data.get(position-1).getName());
			holder.tvsrc.setOnClickListener(new myOnClickListener(position-1));
		}
		return convertView;
	}
	class myOnClickListener implements OnClickListener{
		int index = 0;
		public myOnClickListener(int i){
			index = i;
		}
		@Override
		public void onClick(View v) {
			if(data != null && data.size() > index){
				controller.getContext().addBusinessData("bianji.major", ""+data.get(index).getKey());
			}
		}
		
	}
	class ViewHolder{
	    public TextView tvsrc;
	    }

	Handler handler = new Handler(){
		@Override
		public void handleMessage(Message message) {
			switch (message.what) {
			case 1:
				data = (List<dictionarypackage>) controller.getContext().getBusinessData("DictionaryData");
				zhiyexuanzeAdapter.this.notifyDataSetChanged();
				break;

			default:
				break;
			}
		};
	};
}
