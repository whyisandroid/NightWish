package com.timetalent.client.ui.adapter;

import java.util.ArrayList;
import java.util.List;

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
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

import com.timetalent.client.R;
import com.timetalent.client.entities.MyYirenyaoqinglist;
import com.timetalent.client.entities.MyYirenyaoqingpackage;
import com.timetalent.client.entities.Myfansyaoqinglist;
import com.timetalent.client.entities.Myfansyaoqingpackage;
import com.timetalent.client.service.AppController;
import com.timetalent.client.ui.MainFragmentActivity;
import com.timetalent.client.ui.adapter.NearBaseAdapter.ViewHolder;
import com.timetalent.client.ui.chance.OfferInfoActivity;
import com.timetalent.client.ui.dialog.IOSStyleDialog;
import com.timetalent.client.ui.fragment.util.Background1;
import com.timetalent.client.ui.near.FansActivity;
import com.timetalent.client.ui.near.XingtanActivity;
import com.timetalent.client.ui.near.YirenActivity;
import com.timetalent.client.ui.user.MychongzhiActivity;
import com.timetalent.client.ui.user.MychongzhifangshiActivity;
import com.timetalent.common.util.IntentUtil;
import com.timetalent.common.util.PictureUtil;


/******************************************
 * 类描述： 机会 adapter
 * 类名称：DynamicAdapter  
 * @version: 1.0
 * @author: wanghy
 * @time: 2014-10-12 下午7:58:51 
 ******************************************/
public class XingtanYaoqingAdapter extends BaseAdapter{
	
	private Context mContext;
	private LayoutInflater mInflater;
	AppController controller;
	Myfansyaoqinglist data = null;
	List<Drawable> icons = new ArrayList<Drawable>();
	public float density = 1.0f;
	/**
	 * 类的构造方法
	 * 创建一个新的实例 DynamicAdapter.
	 * @param 
	 */
	public XingtanYaoqingAdapter(Context mContext,AppController c) {
		this.mContext = mContext;
		this.mInflater = LayoutInflater.from(mContext);
		controller = c;
		DisplayMetrics dm2 = mContext.getResources().getDisplayMetrics();
		density = dm2.density;
		new Thread(){
			public void run() {
				controller.myinvite_appoint();
				data = (Myfansyaoqinglist) controller.getContext().getBusinessData("FansyaoqingData");
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
						for (int i = 0; i < data.getLists().size(); i++) {
							Drawable bd1 = PictureUtil.getImage(data.getLists().get(i).getUser().getAvatar(), data.getLists().get(i).getTarget_id(), "head");
							BitmapDrawable bd = null;
							if(bd1 instanceof BitmapDrawable){
								bd = (BitmapDrawable) bd1;
							}
							
							if(bd == null){
								handler.sendEmptyMessage(1);
								continue;
							}
							Bitmap temp = bd.getBitmap();
							if(temp == null){
								handler.sendEmptyMessage(1);
								continue;
							}
							Bitmap bm = PictureUtil.getRoundedCornerBitmap(temp);
							icons.set(i, new BitmapDrawable(bm));
							handler.sendEmptyMessage(1);
						}
			};
		}.start();
	}
	
	@Override
	public int getCount() {
		if(data != null && data.getLists() != null){
			return data.getLists().size();
		}else{
			return 0;
		}
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
			convertView = mInflater.inflate(R.layout.my_xingtanwork_item,
				    null);
			holder = new ViewHolder();
			holder.imghead = (ImageView) convertView.findViewById(R.id.imghead);
			holder.imghead.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					IntentUtil.intent(mContext, XingtanActivity.class);
					
				}
			});
			holder.tvname = (TextView) convertView.findViewById(R.id.tvname);
			holder.tvstatus = (TextView) convertView.findViewById(R.id.tvstatus);
			holder.tvstatus.setText("未处理");
			holder.tvstatus.setTextColor(0XFFFF0000);
			holder.lneirong = (LinearLayout) convertView.findViewById(R.id.lneirong);
			holder.lneirong.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					showMessageTwo(mContext, "对方已完成预定工作，\n星币将转入对方账户？", "完成");
					
				}
			});
			holder.tvjiesuan = (TextView) convertView.findViewById(R.id.tvjiesuan);
			holder.tvjiage = (TextView) convertView.findViewById(R.id.tvjiage);
			holder.tvdizhi = (TextView) convertView.findViewById(R.id.tvdizhi);
			holder.tvriqi = (TextView) convertView.findViewById(R.id.tvriqi);
			convertView.setTag(holder);//绑定ViewHolder对象
		}else{
            holder = (ViewHolder)convertView.getTag();//取出ViewHolder对象 
            }
		if(data!= null && data.getLists()!= null){
			holder.imghead.setImageDrawable(icons.get(position));
//			holder.imghead.setPadding(10, 10, 10, 10);
			LayoutParams pa = (LayoutParams) holder.imghead.getLayoutParams();
			pa.width = (int) (50*density);
			pa.height = (int) (50*density);
			holder.tvname.setText(data.getLists().get(position).getService_job());
			if(data.getLists().get(position).getAccept().equals("Y")){
				holder.tvstatus.setText("完成交易");
			}else if(data.getLists().get(position).getAccept().equals("N")){
				holder.tvstatus.setText("已被拒绝");
			}else{
				holder.tvstatus.setText("未处理");
			}
			
			holder.tvjiesuan.setText(data.getLists().get(position).getService_method());
			holder.tvjiage.setText(data.getLists().get(position).getMoney());
			holder.tvdizhi.setText(data.getLists().get(position).getWork_place());
			holder.tvriqi.setText(data.getLists().get(position).getWork_date()+"天");
		}
		return convertView;
	}
	private void showMessageTwo(final Context context,String message,final String toast) {
		final IOSStyleDialog dialog = new IOSStyleDialog(context, IOSStyleDialog.DIALOG_TWO);
		dialog.setMessage(message);
		dialog.setLeft("取消", new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dialog.closeDialog();
//				final IOSStyleDialog dialog = new IOSStyleDialog(context, IOSStyleDialog.DIALOG_ONE);
//				dialog.setMessage("您拒接了本次邀请");
//				dialog.setOne("确认",new OnClickListener() {
//					@Override
//					public void onClick(View v) {
////						IntentUtil.intent(mContext, MainFragmentActivity.class);
//						dialog.closeDialog();
//					}
//				});
			}
		});
		dialog.setRight("确定", new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dialog.closeDialog();
//				final IOSStyleDialog dialog = new IOSStyleDialog(context, IOSStyleDialog.DIALOG_ONE);
//				dialog.setMessage("您接受了本次邀请");
//				dialog.setOne("确认",new OnClickListener() {
//					@Override
//					public void onClick(View v) {
////						IntentUtil.intent(mContext, MainFragmentActivity.class);
//						dialog.closeDialog();
//					}
//				});
			}
		});
	}
	class ViewHolder{
	    public ImageView imghead;
	    public TextView tvname;
	    public TextView tvstatus;
	    public LinearLayout lneirong;
	    public TextView tvjiesuan;
	    public TextView tvjiage;
	    public TextView tvdizhi;
	    public TextView tvriqi;
	    }
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// super.handleMessage(msg);
			switch (msg.what) {
			case 1:
					XingtanYaoqingAdapter.this.notifyDataSetChanged();
				break;
			case 2:
				XingtanYaoqingAdapter.this.notifyDataSetChanged();
				break;
			}
		}
	};
	class myOnClickListener implements OnClickListener{
		Myfansyaoqingpackage data;
		public myOnClickListener(Myfansyaoqingpackage followedpackage) {
			data = followedpackage;
		}
		@Override
		public void onClick(View v) {
			if(data.getUser().getType().equals("star")){
				Bundle bundle1 = new Bundle();
				bundle1.putString("userid", data.getTarget_id());
				IntentUtil.intent(mContext, bundle1,YirenActivity.class,false);
			}else if(data.getUser().getType().equals("scout")){
				Bundle bundle1 = new Bundle();
				bundle1.putString("userid", data.getTarget_id());
				IntentUtil.intent(mContext, bundle1,XingtanActivity.class,false);
			}else if(data.getUser().getType().equals("fans")){
				Bundle bundle1 = new Bundle();
				bundle1.putString("userid", data.getTarget_id());
				IntentUtil.intent(mContext, bundle1,FansActivity.class,false);
			}
			
		}
		
	}
}
