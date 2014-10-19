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
import com.timetalent.client.ui.user.MychongzhiActivity;
import com.timetalent.client.ui.user.MychongzhifangshiActivity;
import com.timetalent.common.util.IntentUtil;


/******************************************
 * 类描述： 机会 adapter
 * 类名称：DynamicAdapter  
 * @version: 1.0
 * @author: wanghy
 * @time: 2014-10-12 下午7:58:51 
 ******************************************/
public class YaoqingAdapter extends BaseAdapter{
	
	private Context mContext;
	private LayoutInflater mInflater;
	/**
	 * 类的构造方法
	 * 创建一个新的实例 DynamicAdapter.
	 * @param 
	 */
	public YaoqingAdapter(Context mContext) {
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
			convertView = mInflater.inflate(R.layout.my_yirenwork_item,
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
					showMessageTwo(mContext, "您要接受此次工作邀请吗？", "完成");
					
				}
			});
			holder.imgsex = (ImageView) convertView.findViewById(R.id.imgsex);
			holder.tvage = (TextView) convertView.findViewById(R.id.tvage);
			holder.tvzhiye = (TextView) convertView.findViewById(R.id.tvname);
			holder.tvmiaoshu = (TextView) convertView.findViewById(R.id.tvmiaoshu);
			convertView.setTag(holder);//绑定ViewHolder对象
		}else{
            holder = (ViewHolder)convertView.getTag();//取出ViewHolder对象 
            }
		return convertView;
	}
	private void showMessageTwo(final Context context,String message,final String toast) {
		final IOSStyleDialog dialog = new IOSStyleDialog(context, IOSStyleDialog.DIALOG_TWO);
		dialog.setMessage(message);
		dialog.setLeft("拒接", new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dialog.closeDialog();
				final IOSStyleDialog dialog = new IOSStyleDialog(context, IOSStyleDialog.DIALOG_ONE);
				dialog.setMessage("您拒接了本次邀请");
				dialog.setOne("确认",new OnClickListener() {
					@Override
					public void onClick(View v) {
//						IntentUtil.intent(mContext, MainFragmentActivity.class);
						dialog.closeDialog();
					}
				});
			}
		});
		dialog.setRight("确定", new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dialog.closeDialog();
				final IOSStyleDialog dialog = new IOSStyleDialog(context, IOSStyleDialog.DIALOG_ONE);
				dialog.setMessage("您接受了本次邀请");
				dialog.setOne("确认",new OnClickListener() {
					@Override
					public void onClick(View v) {
//						IntentUtil.intent(mContext, MainFragmentActivity.class);
						dialog.closeDialog();
					}
				});
			}
		});
	}
	class ViewHolder{
	    public ImageView imghead;
	    public TextView tvname;
	    public TextView tvstatus;
	    public LinearLayout lneirong;
	    public ImageView imgsex;
	    public TextView tvage;
	    public TextView tvzhiye;
	    public TextView tvmiaoshu;
	    }
}
