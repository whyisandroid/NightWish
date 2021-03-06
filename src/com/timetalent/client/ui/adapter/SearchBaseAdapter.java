package com.timetalent.client.ui.adapter;

import java.util.ArrayList;
import java.util.List;

import com.timetalent.client.R;
import com.timetalent.client.entities.Nearlist;
import com.timetalent.client.entities.Searchlist;
import com.timetalent.client.service.AppContext;
import com.timetalent.client.service.AppController;
import com.timetalent.client.ui.fragment.util.Background1;
import com.timetalent.common.util.PictureUtil;
import com.timetalent.common.util.StringUtil;

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
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;


/******************************************
 * 类描述： TODO
 * 类名称：NearBaseAdapter  
 * @version: 1.0
 * @author: Administrator
 * @time: 2014-10-10 下午10:12:16 
 ******************************************/
public class SearchBaseAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	Searchlist data = null;
	List<Drawable> icons = new ArrayList<Drawable>();
	public SearchBaseAdapter(Context context){
		this.mInflater = LayoutInflater.from(context);
		data = (Searchlist) AppController.getController(((Activity)context)).getContext().getBusinessData("SearchData");
		if(icons == null){
			icons = new ArrayList<Drawable>();
		}else{
			icons.clear();
		}
		if(data == null || data.getLists() == null){
			return;
		}
		for (int i = 0; i < data.getLists().size(); i++) {
			icons.add(new Background1());
		}
		new Thread(){
			public void run() {
				for (int i = 0; i < data.getLists().size(); i++) {
					Drawable bd1 = PictureUtil.getImage(data.getLists().get(i).getAvatar(), data.getLists().get(i).getId(), "head");
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
		ViewHolder holder = new ViewHolder();;
		if (convertView == null) {
			if(data!= null && data.getLists().size() > position){
				convertView = mInflater.inflate(R.layout.near_list_item, null);
				
				holder.imghead = (ImageView) convertView.findViewById(R.id.imghead);
				holder.tvname = (TextView) convertView.findViewById(R.id.tvname);
				holder.tvonlinetime = (TextView) convertView.findViewById(R.id.tvonlinetime);
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
		holder.imghead.setImageDrawable(icons.get(position));
		holder.imghead.setPadding(10, 10, 10, 10);
		LayoutParams pa = (LayoutParams) holder.imghead.getLayoutParams();
		pa.height = holder.imghead.getWidth();
		holder.tvname.setText(""+data.getLists().get(position).getNickname());
		holder.tvage.setText(data.getLists().get(position).getAge()+"岁");
		holder.tvzhiye.setText("职业/"+data.getLists().get(position).getMajor());
		holder.tvonlinetime.setText(data.getLists().get(position).getCity()+" "+StringUtil.transformTime(data.getLists().get(position).getLast_time()));
		if(data.getLists().get(position).getLoyal_pass().equals("0")){
			holder.imgonline.setImageResource(R.drawable.f3_13);
		}else{
			holder.imgonline.setImageResource(R.drawable.f3_34);
		}
		if(data.getLists().get(position).getSex().equals("0")){
			holder.imgsex.setImageResource(R.drawable.f3_15);
		}else{
			holder.imgsex.setImageResource(R.drawable.f3_31);
		}
		holder.tvmiaoshu.setText(""+data.getLists().get(position).getContent());
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
				
				SearchBaseAdapter.this.notifyDataSetChanged();
				break;
			case 2:
				break;
			case 3:
				break;
			}
		}
	};
}
