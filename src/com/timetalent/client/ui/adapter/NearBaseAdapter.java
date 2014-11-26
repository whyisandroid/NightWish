package com.timetalent.client.ui.adapter;

import java.util.ArrayList;
import java.util.List;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.timetalent.client.R;
import com.timetalent.client.entities.Nearlist;
import com.timetalent.client.entities.Nearpackage;
import com.timetalent.client.service.AppContext;
import com.timetalent.client.service.AppController;
import com.timetalent.client.ui.fragment.util.Background1;
import com.timetalent.common.util.PictureUtil;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;


/******************************************
 * 类描述： TODO
 * 类名称：NearBaseAdapter  
 * @version: 1.0
 * @author: Administrator
 * @time: 2014-10-10 下午10:12:16 
 ******************************************/
public class NearBaseAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	Nearlist data = null;
	List<Drawable> icons = new ArrayList<Drawable>();
	public NearBaseAdapter(Context context){
		this.mInflater = LayoutInflater.from(context);
		data = (Nearlist) AppController.getController(((Activity)context)).getContext().getBusinessData("NearData");
		if(icons == null){
			icons = new ArrayList<Drawable>();
		}else{
			icons.clear();
		}
		for (int i = 0; i < data.getLists().size(); i++) {
			icons.add(new Background1());
		}
		new Thread(){
			public void run() {
				for (int i = 0; i < data.getLists().size(); i++) {
					BitmapDrawable bd = (BitmapDrawable) PictureUtil.getImage(data.getLists().get(i).getAvatar(), data.getLists().get(i).getId(), "head");
					if(bd == null){
						continue;
					}
					Bitmap temp = bd.getBitmap();
					if(temp == null){
						continue;
					}
					Bitmap bm = PictureUtil.getRoundedCornerBitmap(temp);
					icons.set(i, new BitmapDrawable(bm));
					handler.sendEmptyMessage(1);
				}
			}
		}.start();
	}
	@Override
	public int getCount() {
		if(data != null && data.getLists()!= null){
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
				convertView = mInflater.inflate(R.layout.near_list_item, null);
				holder.imghead = (ImageView) convertView.findViewById(R.id.imghead);
				holder.tvname = (TextView) convertView.findViewById(R.id.tvname);
				holder.imgonline = (ImageView) convertView.findViewById(R.id.imgonline);
				holder.imgsex = (ImageView) convertView.findViewById(R.id.imgsex);
				holder.tvage = (TextView) convertView.findViewById(R.id.tvage);
				holder.tvzhiye = (TextView) convertView.findViewById(R.id.tvzhiye);
				holder.tvmiaoshu = (TextView) convertView.findViewById(R.id.tvmiaoshu);
				
//				holder.tvmiaoshu.setText(data.getUsers().get(position).get);
				convertView.setTag(holder);//绑定ViewHolder对象
			}
		}else{
            holder = (ViewHolder)convertView.getTag();//取出ViewHolder对象 
            }
//		ImageLoader.getInstance().displayImage(data.getLists().get(position).getAvatar(), holder.imghead);
		holder.imghead.setImageDrawable(icons.get(position));
		holder.imghead.setPadding(10, 10, 10, 10);
		LayoutParams pa = (LayoutParams) holder.imghead.getLayoutParams();
		pa.height = holder.imghead.getWidth();
//		holder.imghead.setImageBitmap( ImageLoader.getInstance().loadImageSync("http://124.193.223.166/xingtan/Uploads/avatar/201411/5458d19bd4a43.jpg"));//"http://124.193.223.166/xingtan/Uploads/avatar/"+data.getLists().get(position).getAvatar()
		holder.tvname.setText(data.getLists().get(position).getNickname());
		holder.tvage.setText(data.getLists().get(position).getBirthday());
		holder.tvzhiye.setText("职业/"+data.getLists().get(position).getType());
		return convertView;
	}
	class ViewHolder{
	    public ImageView imghead;
	    public TextView tvname;
	    public TextView tvonlinetime;
	    public ImageView imgonline;
	    public ImageView imgsex;
	    public TextView tvage;
	    public TextView tvzhiye;
	    public TextView tvmiaoshu;
	    }
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// super.handleMessage(msg);
			switch (msg.what) {
			case 1:
				
				NearBaseAdapter.this.notifyDataSetChanged();
				break;
			case 2:
				break;
			case 3:
				break;
			}
		}
	};
}
