package com.timetalent.client.ui.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.timetalent.client.R;
import com.timetalent.client.entities.TaskShow;
import com.timetalent.client.service.AppController;
import com.timetalent.client.ui.MainFragmentActivity;
import com.timetalent.client.ui.chance.OfferInfoActivity;
import com.timetalent.client.ui.dialog.DialogUtil;
import com.timetalent.client.ui.dialog.IOSStyleDialog;
import com.timetalent.common.util.IntentUtil;
import com.timetalent.common.util.ProgressDialogUtil;


/******************************************
 * 类描述： 机会 adapter
 * 类名称：DynamicAdapter  
 * @version: 1.0
 * @author: wanghy
 * @time: 2014-10-12 下午7:58:51 
 ******************************************/
public class ChanceDetailAdapter extends BaseAdapter{
	
	private List<TaskShow> lists;
	private Context mContext;
	private TaskShow show;
	
	/**
	 * 类的构造方法
	 * 创建一个新的实例 DynamicAdapter.
	 * @param 
	 */
	public ChanceDetailAdapter(Context mContext,List<TaskShow> lists) {
		this.mContext = mContext;
		this.lists = lists;
	}
	
	@Override
	public int getCount() {
		return lists.size();
	}

	
	@Override
	public Object getItem(int position) {
		return lists.get(position);
	}

	
	@Override
	public long getItemId(int position) {
		return position;
	}

	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(R.layout.chance_detail_list_item, null);
			holder = new ViewHolder();
			holder.findView(convertView);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder)convertView.getTag();
		}
		show = lists.get(position);
		
		holder.tv_offer_detail_work.setText(show.getJob());
		holder.tv_offer_detail_work_time.setText(show.getWork_date_start()+"-"+show.getWork_date_end());
		holder.tv_offer_detail_message.setText(show.getDescription());
		holder.tv_offer_detail_qpply.setOnClickListener(applyListener);
		return convertView;
	}
	
	private OnClickListener applyListener = new  OnClickListener() {
		
		@Override
		public void onClick(View v) {
			showMessageTwo(mContext, "您要报名:"+show.getJob()+"?");
		}
	};
	
	
	/**
	  * 方法描述：左右两个按钮
	  * @param activity
	  * @author: why
	  * @time: 2014-8-13 下午7:43:12
	  */
	private void showMessageTwo(final Context context,String message) {
		final IOSStyleDialog dialog = new IOSStyleDialog(context, IOSStyleDialog.DIALOG_TWO);
		dialog.setMessage(message);
		dialog.setLeft("取消", new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dialog.closeDialog();
			}
		});
		dialog.setRight("确定", new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				apply();
				dialog.closeDialog();
			}
		});
	}
	
	/**
	  * 方法描述：TODO
	  * @author: why
	  * @time: 2014-10-21 上午11:17:14
	  */
	private void apply() {
		ProgressDialogUtil.showProgressDialog(mContext, "稍等…", false);
		new Thread(new Runnable() {
			@Override
			public void run() {
				AppController.getController().getContext().addBusinessData("Chance_Job_id", show.getId());
				AppController.getController().getContext().addBusinessData("Chance_Task_id", show.getTask_id());
				AppController.getController().chanceApply(show.getJob());
				ProgressDialogUtil.closeProgressDialog();
			}
		}).start();
	}
	
	
	static class ViewHolder{
		private TextView tv_offer_detail_work;
		private TextView tv_offer_detail_work_time;
		private ImageView tv_offer_detail_qpply;
		private TextView tv_offer_detail_message;
		
		public void findView(View convertView){
			tv_offer_detail_work = (TextView)convertView.findViewById(R.id.tv_offer_detail_work);
			tv_offer_detail_work_time = (TextView)convertView.findViewById(R.id.tv_offer_detail_work_time);
			tv_offer_detail_qpply = (ImageView)convertView.findViewById(R.id.tv_offer_detail_qpply);
			tv_offer_detail_message = (TextView)convertView.findViewById(R.id.tv_offer_detail_message);
		}
	}

}
