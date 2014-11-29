package com.timetalent.client.ui.adapter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

import com.easemob.chat.StartServiceReceiver;
import com.timetalent.client.R;
import com.timetalent.client.entities.Blacklist;
import com.timetalent.client.entities.BlankName;
import com.timetalent.client.entities.Followedlist;
import com.timetalent.client.entities.MessageItem;
import com.timetalent.client.service.AppController;
import com.timetalent.client.ui.fragment.util.Background1;
import com.timetalent.client.ui.view.ListViewCompat;
import com.timetalent.client.ui.view.SlideView;
import com.timetalent.client.ui.view.SlideView.OnSlideListener;
import com.timetalent.common.util.PictureUtil;
import com.timetalent.common.util.ToastUtil;

/******************************************
 * 类描述： TODO 类名称：NearBaseAdapter
 * 
 * @version: 1.0
 * @author: Administrator
 * @time: 2014-10-10 下午10:12:16
 ******************************************/
public class HeimingdanAdapter extends BaseAdapter {
	private SlideView mLastSlideViewWithStatusOn;
	private LayoutInflater mInflater;
	private Context mContext;
	private List<BlankName> mBlankNameItems;
	private ListViewCompat mListview;
	Blacklist data = null;
	List<Drawable> icons = new ArrayList<Drawable>();
	public HeimingdanAdapter(Context context,List<BlankName> mBlankNameItems,ListViewCompat mListview) {
		this.mInflater = LayoutInflater.from(context);
		this.mContext = context;
		this.mBlankNameItems = mBlankNameItems;
		this.mListview = mListview;
		data = (Blacklist) AppController.getController(((Activity)context)).getContext().getBusinessData("BlackData");
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
		// TODO Auto-generated method stub
		return mBlankNameItems.size();//
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mBlankNameItems.get(position);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getItemId(int)
	 */
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getView(int, android.view.View,
	 * android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		SlideView slideView = (SlideView) convertView;
		ViewHolder holder = null;
		if (slideView == null) {
			if(data!= null && data.getLists().size() > position){
				View itemView = mInflater.inflate(R.layout.near_list_item, null);
				slideView = new SlideView(mContext);
				slideView.setContentView(itemView);
				slideView.setOnSlideListener(slideListener);
				holder = new ViewHolder(slideView);
				slideView.setTag(holder);
			}
			
		} else {
			 holder = (ViewHolder) slideView.getTag();// 取出ViewHolder对象
		}
		
		BlankName item = mBlankNameItems.get(position);
        item.slideView = slideView;
        item.slideView.shrink();
		if(holder!=null){
			holder.deleteHolder.setOnClickListener(deleteItemListener);
		}
		
		holder.imghead.setImageDrawable(icons.get(position));
		holder.imghead.setPadding(10, 10, 10, 10);
		LayoutParams pa = (LayoutParams) holder.imghead.getLayoutParams();
		pa.height = holder.imghead.getWidth();
//		holder.imghead.setImageBitmap( ImageLoader.getInstance().loadImageSync("http://124.193.223.166/xingtan/Uploads/avatar/201411/5458d19bd4a43.jpg"));//"http://124.193.223.166/xingtan/Uploads/avatar/"+data.getLists().get(position).getAvatar()
		holder.tvname.setText(""+data.getLists().get(position).getNickname());
		holder.tvage.setText(data.getLists().get(position).getAge()+"岁");
		holder.tvzhiye.setText("职业/"+data.getLists().get(position).getType());
		if(data.getLists().get(position).getLoyal_pass().equals("1")){
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
		
		
		return slideView;
	}
	
	
	 private OnSlideListener slideListener = new  OnSlideListener() {
			
		@Override
		public void onSlide(View view, int status) {


	        if (mLastSlideViewWithStatusOn != null && mLastSlideViewWithStatusOn != view) {
	            mLastSlideViewWithStatusOn.shrink();
	        }

	        if (status == SLIDE_STATUS_ON) {
	            mLastSlideViewWithStatusOn = (SlideView) view;
	        }
	    
		
			// TODO Auto-generated method stub
			
		}
	};
	
	
	 private OnClickListener deleteItemListener =  new  OnClickListener() {
			
			@Override
			public void onClick(View view) {
				final int position = mListview.getPositionForView(view);  
	            if (position != mListview.INVALID_POSITION) {  
	            	mBlankNameItems.remove(position);  
	                HeimingdanAdapter.this.notifyDataSetChanged();
	                new Thread(){
						public void run() {
							AppController.getController(((Activity)mContext)).getContext().addBusinessData("my.do", "unblack");
							AppController.getController(((Activity)mContext)).getContext().addBusinessData("my.target_id", ""+data.getLists().get(position).getId());
							AppController.getController(((Activity)mContext)).mydo_social();
						};
					}.start();
	                
	            }   
			}
		};
	
	
	
	
	
	

	class ViewHolder {
		public ImageView imghead;
		public TextView tvname;
		public TextView tvonlinetime;
		public ImageView imgonline;
		public ImageView imgsex;
		public TextView tvage;
		public TextView tvzhiye;
		public TextView tvmiaoshu;

		public ViewGroup deleteHolder;

		ViewHolder(View convertView) {
			imghead = (ImageView) convertView.findViewById(R.id.imghead);
			tvname = (TextView) convertView.findViewById(R.id.tvname);
			imgonline = (ImageView) convertView.findViewById(R.id.imgonline);
			imgsex = (ImageView) convertView.findViewById(R.id.imgsex);
			tvage = (TextView) convertView.findViewById(R.id.tvage);
			tvzhiye = (TextView) convertView.findViewById(R.id.tvname);
			tvmiaoshu = (TextView) convertView.findViewById(R.id.tvmiaoshu);
			deleteHolder = (ViewGroup) convertView.findViewById(R.id.holder);
		}
	}
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// super.handleMessage(msg);
			switch (msg.what) {
			case 1:
				
				HeimingdanAdapter.this.notifyDataSetChanged();
				break;
			case 2:
				break;
			case 3:
				break;
			}
		}
	};
}
