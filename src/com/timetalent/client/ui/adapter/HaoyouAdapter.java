package com.timetalent.client.ui.adapter;

import java.util.ArrayList;
import java.util.List;

import com.timetalent.client.R;
import com.timetalent.client.entities.Followedlist;
import com.timetalent.client.entities.Followingpackage;
import com.timetalent.client.entities.Friendlist;
import com.timetalent.client.entities.Friendpackage;
import com.timetalent.client.service.AppController;
import com.timetalent.client.ui.adapter.GuanzhuAdapter.myOnClickListener;
import com.timetalent.client.ui.fragment.util.Background1;
import com.timetalent.client.ui.near.FansActivity;
import com.timetalent.client.ui.near.XingtanActivity;
import com.timetalent.client.ui.near.YirenActivity;
import com.timetalent.common.util.IntentUtil;
import com.timetalent.common.util.PictureUtil;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
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
public class HaoyouAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	Friendlist data = null;
	List<Drawable> icons = new ArrayList<Drawable>();
	Context mcontext;
	float density = 1.0f;
	public HaoyouAdapter(Context context){
		this.mInflater = LayoutInflater.from(context);
		mcontext = context;
		DisplayMetrics dm2 = mcontext.getResources().getDisplayMetrics();
		density = dm2.density;
		data = (Friendlist) AppController.getController(((Activity)context)).getContext().getBusinessData("FriendData");
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
		ViewHolder holder = new ViewHolder();
		if (convertView == null) {
			if(data!= null && data.getLists().size() > position){
				convertView = mInflater.inflate(R.layout.my_haoyou_item,
					    null);
				
				holder.imghead = (ImageView) convertView.findViewById(R.id.imghead);
				holder.tvname = (TextView) convertView.findViewById(R.id.tvname);
				holder.imgsex = (ImageView) convertView.findViewById(R.id.imgsex);
				holder.tvage = (TextView) convertView.findViewById(R.id.tvage);
				holder.tvzhiye = (TextView) convertView.findViewById(R.id.tvzhiye);
				holder.tvmiaoshu = (TextView) convertView.findViewById(R.id.tvmiaoshu);
				holder.bttianjia = (ImageButton) convertView.findViewById(R.id.bttianjia);
				holder.bttianjia.setVisibility(holder.bttianjia.GONE);
				convertView.setTag(holder);//绑定ViewHolder对象
			}
			
		}else{
            holder = (ViewHolder)convertView.getTag();//取出ViewHolder对象 
            }
		holder.imghead.setImageDrawable(icons.get(position));
//		holder.imghead.setPadding(0, (int) (5*density), 0, (int) (5*density));
		LayoutParams pa = (LayoutParams) holder.imghead.getLayoutParams();
		pa.width = (int) (50*density);
		pa.height = (int) (50*density);
		holder.imghead.setOnClickListener(new myOnClickListener(data.getLists().get(position)));
//		holder.imghead.setImageBitmap( ImageLoader.getInstance().loadImageSync("http://124.193.223.166/xingtan/Uploads/avatar/201411/5458d19bd4a43.jpg"));//"http://124.193.223.166/xingtan/Uploads/avatar/"+data.getLists().get(position).getAvatar()
		holder.tvname.setText(""+data.getLists().get(position).getNickname());
		holder.tvage.setText(data.getLists().get(position).getAge()+"岁");
		holder.tvzhiye.setText("职业/"+data.getLists().get(position).getType());
		if(data.getLists().get(position).getSex().equals("0")){
			holder.imgsex.setImageResource(R.drawable.f3_15);
		}else{
			holder.imgsex.setImageResource(R.drawable.f3_31);
		}
		holder.tvmiaoshu.setText(""+data.getLists().get(position).getCity());
		return convertView;
	}
	class ViewHolder{
	    public ImageView imghead;
	    public TextView tvname;
	    public ImageView imgsex;
	    public TextView tvage;
	    public TextView tvzhiye;
	    public TextView tvmiaoshu;
	    public ImageButton bttianjia;
	    
	    }
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// super.handleMessage(msg);
			switch (msg.what) {
			case 1:
				
				HaoyouAdapter.this.notifyDataSetChanged();
				break;
			case 2:
				break;
			case 3:
				break;
			}
		}
	};
	class myOnClickListener implements OnClickListener{
		Friendpackage data;
		public myOnClickListener(Friendpackage friendpackage) {
			data = friendpackage;
		}
		@Override
		public void onClick(View v) {
			if(data.getType().equals("star")){
				Bundle bundle1 = new Bundle();
				bundle1.putString("userid", data.getId());
				IntentUtil.intent(mcontext, bundle1,YirenActivity.class,false);
			}else if(data.getType().equals("scout")){
				Bundle bundle1 = new Bundle();
				bundle1.putString("userid", data.getId());
				IntentUtil.intent(mcontext, bundle1,XingtanActivity.class,false);
			}else if(data.getType().equals("fans")){
				Bundle bundle1 = new Bundle();
				bundle1.putString("userid", data.getId());
				IntentUtil.intent(mcontext, bundle1,FansActivity.class,false);
			}
			
		}
		
	}
}
