package com.timetalent.client.ui.adapter;

import java.util.ArrayList;
import java.util.List;

import com.easemob.util.DensityUtil;
import com.timetalent.client.R;
import com.timetalent.client.entities.Userinfopackage;
import com.timetalent.client.service.AppController;
import com.timetalent.client.ui.fragment.util.Background1;
import com.timetalent.client.ui.near.YirenActivity;
import com.timetalent.client.ui.user.MyzuopinActivity;
import com.timetalent.common.util.IntentUtil;
import com.timetalent.common.util.PictureUtil;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;


/******************************************
 * 类描述： TODO
 * 类名称：NearBaseAdapter  
 * @version: 1.0
 * @author: Administrator
 * @time: 2014-10-10 下午10:12:16 
 ******************************************/
public class ZuopinupdateAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	List<Drawable> icons = new ArrayList<Drawable>();
	Context context;
	Userinfopackage data = null;
	float density = 1.0f;
	public ZuopinupdateAdapter(Context context){
		this.context = context;
		this.mInflater = LayoutInflater.from(context);
		DisplayMetrics dm2 = context.getResources().getDisplayMetrics();
		density = dm2.density;
		data = (Userinfopackage) AppController.getController(((Activity)context)).getContext().getBusinessData("UserinfoData");
		if(icons == null){
			icons = new ArrayList<Drawable>();
		}else{
			icons.clear();
		}
		if(data == null || data.getMore() == null||data.getMore().getWorks() == null){
			return;
		}
		for (int i = 0; i < data.getMore().getWorks().size(); i++) {
			icons.add(new Background1());
		}
		new Thread(){
			public void run() {
				for (int i = 0; i < data.getMore().getWorks().size(); i++) {
					Drawable bd1 = PictureUtil.getImage(data.getMore().getWorks().get(i).getUrl(), data.getMore().getWorks().get(i).getUser_id()+data.getMore().getWorks().get(i).getId(), "work");
					BitmapDrawable bd = null;
					if(bd1 instanceof BitmapDrawable){
						bd = (BitmapDrawable) bd1;
					}
					
					if(bd == null){
						continue;
					}
					Bitmap temp = bd.getBitmap();
					if(temp == null){
						continue;
					}
					Bitmap bm = PictureUtil.toRoundCorner(temp, (int)(5*density));//getRoundedCornerBitmap(temp);
					icons.set(i, new BitmapDrawable(bm));
					handler.sendEmptyMessage(1);
				}
			}
		}.start();
	}
	@Override
	public int getCount() {
		if(data != null && data.getMore() != null && data.getMore().getWorks() != null){
			return data.getMore().getWorks().size()+1;
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
		if(data != null && data.getMore() != null && data.getMore().getWorks() == null && position == 0){
			if (convertView == null) {
				convertView = mInflater.inflate(R.layout.near_yirenzuopin_item,
					    null);
				holder = new ViewHolder();
				holder.lroot = (LinearLayout) convertView.findViewById(R.id.lroot);
				holder.imgzuopin = (ImageView) convertView.findViewById(R.id.imgzuopin);
				holder.tvzuopinname = (TextView) convertView.findViewById(R.id.tvzuopinname);
				holder.tvzuopinjuese = (TextView) convertView.findViewById(R.id.tvzuopinjuese);
				holder.tvzuopintime = (TextView) convertView.findViewById(R.id.tvzuopintime);
				convertView.setTag(holder);//绑定ViewHolder对象
			}else{
	            holder = (ViewHolder)convertView.getTag();//取出ViewHolder对象 
	            }
			holder.lroot.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					IntentUtil.intent(context,MyzuopinActivity.class);
				}
			});
			return convertView;
		}
		if(data != null && data.getMore() != null && data.getMore().getWorks() != null && position == data.getMore().getWorks().size()){
			if (convertView == null) {
				convertView = mInflater.inflate(R.layout.near_yirenzuopin_item,
					    null);
				holder = new ViewHolder();
				holder.lroot = (LinearLayout) convertView.findViewById(R.id.lroot);
				holder.imgzuopin = (ImageView) convertView.findViewById(R.id.imgzuopin);
				holder.tvzuopinname = (TextView) convertView.findViewById(R.id.tvzuopinname);
				holder.tvzuopinjuese = (TextView) convertView.findViewById(R.id.tvzuopinjuese);
				holder.tvzuopintime = (TextView) convertView.findViewById(R.id.tvzuopintime);
				convertView.setTag(holder);//绑定ViewHolder对象
			}else{
	            holder = (ViewHolder)convertView.getTag();//取出ViewHolder对象 
	            }
			holder.lroot.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					IntentUtil.intent(context,MyzuopinActivity.class);
					}
			});
			return convertView;
		}
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.near_yirenzuopin_item,
				    null);
			holder = new ViewHolder();
			holder.lroot = (LinearLayout) convertView.findViewById(R.id.lroot);
			holder.imgzuopin = (ImageView) convertView.findViewById(R.id.imgzuopin);
			holder.tvzuopinname = (TextView) convertView.findViewById(R.id.tvzuopinname);
			holder.tvzuopinjuese = (TextView) convertView.findViewById(R.id.tvzuopinjuese);
			holder.tvzuopintime = (TextView) convertView.findViewById(R.id.tvzuopintime);
			convertView.setTag(holder);//绑定ViewHolder对象
		}else{
            holder = (ViewHolder)convertView.getTag();//取出ViewHolder对象 
            }
		if(data != null && data.getMore() != null && data.getMore().getWorks() != null){
			holder.imgzuopin.setImageDrawable(icons.get(position));
//			holder.imgzuopin.setPadding(10, 10, 10, 10);
			LayoutParams pa = (LayoutParams) holder.imgzuopin.getLayoutParams();
			pa.width = (int) (50*density);
			pa.height = (int) (50*density);
			holder.tvzuopinname.setText(""+data.getMore().getWorks().get(position).getTitle());
			holder.tvzuopinjuese.setText(""+data.getMore().getWorks().get(position).getActor());
			holder.tvzuopintime.setText(""+data.getMore().getWorks().get(position).getTime());
		}
		
		return convertView;
	}
	class ViewHolder{
		public LinearLayout lroot;
	    public ImageView imgzuopin;
	    public TextView tvzuopinname;
	    public TextView tvzuopinjuese;
	    public TextView tvzuopintime;
	    }
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// super.handleMessage(msg);
			switch (msg.what) {
			case 1:
				ZuopinupdateAdapter.this.notifyDataSetChanged();
				break;
			case 2:
				break;
			case 3:
				break;
			}
		}
	};
}
