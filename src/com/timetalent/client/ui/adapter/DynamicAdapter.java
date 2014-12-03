package com.timetalent.client.ui.adapter;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.timetalent.client.R;
import com.timetalent.client.entities.Feed;
import com.timetalent.client.entities.Photo;
import com.timetalent.client.service.AppController;
import com.timetalent.client.ui.dynamic.DynamicOtherActivity;
import com.timetalent.client.ui.view.HorizontalListView;
import com.timetalent.common.util.IntentUtil;
import com.timetalent.common.util.PictureUtil;
import com.timetalent.common.util.ProgressDialogUtil;
import com.timetalent.common.util.ToastUtil;


/******************************************
 * 类描述： 动态 adapter
 * 类名称：DynamicAdapter  
 * @version: 1.0
 * @author: wanghy
 * @time: 2014-10-12 下午7:58:51 
 ******************************************/
@SuppressLint("NewApi")
public class DynamicAdapter extends BaseAdapter{
	private List<Feed> lists;
	private Context mContext;
	private LayoutInflater inflater;
	private AppController controller;
	private Handler dynamicHandler; 
	
	private Handler handler  = new  Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0:
				ViewHolder holder = (ViewHolder)msg.obj;
				holder.tv_dynamic_add_1.setVisibility(View.VISIBLE);
				holder.tv_dynamic_add_1.animate().setDuration(2000);
				holder.tv_dynamic_add_1.animate().alpha(0);
				holder.iv_dynamic_good.setSelected(false);
				holder.iv_dynamic_good_num.setText(Integer.valueOf(holder.iv_dynamic_good_num.getText().toString())+1+"");
				break;
			case 1:
				ViewHolder holder1 = (ViewHolder)msg.obj;
				break;
			default:
				break;
			}
			
		};
	};
	
	/**
	 * 类的构造方法
	 * 创建一个新的实例 DynamicAdapter.
	 * @param 
	 */
	public DynamicAdapter(Context mContext,List<Feed> lists,Handler dynamicHandler) {
		this.mContext = mContext;
		this.lists = lists;
		inflater = LayoutInflater.from(mContext);
		controller = AppController.getController();
		this.dynamicHandler = dynamicHandler;
	}
	
	/**
	 * 类的构造方法
	 * 创建一个新的实例 DynamicAdapter.
	 * @param 
	 */
	public DynamicAdapter(Context mContext,List<Feed> lists) {
		this.mContext = mContext;
		this.lists = lists;
		inflater = LayoutInflater.from(mContext);
		controller = AppController.getController();
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
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
		final ViewHolder holder;
		final Feed feed = lists.get(position);
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.dynamic_list_item, null);
			holder = new ViewHolder();
			holder.findView(convertView);
			convertView.setTag(holder);
		}else{
			holder=(ViewHolder)convertView.getTag();
		}
		
		
		holder.tv_dynamic_name.setText(feed.getUser().getNickname());
		feed.getUser().setLoyal_pass("1");
		holder.iv_dynamic_pass.setSelected("1".equals(feed.getUser().getLoyal_pass()));
		holder.iv_dynamic_sex.setSelected("1".equals(feed.getUser().getSex()));
		holder.tv_dynamic_age.setText(feed.getUser().getAge());
		holder.tv_dynamic_constella.setText(feed.getUser().getConstella());
		holder.tv_dynamic_content.setText(feed.getContents());
		holder.tv_dynamic_time.setText(feed.getTime_ago());
		holder.iv_dynamic_good_num.setText(feed.getFavour_num());
		holder.iv_dynamic_good.setSelected("N".equals(feed.getFavour_do()));
		
		// 处理 回复
		DynamicReplayAdapter replayAdapter = new DynamicReplayAdapter(mContext, feed.getReply());
		holder.lv_dynamic_replay.setAdapter(replayAdapter);
		//UIUtils.setListViewHeight(holder.lv_dynamic_replay, replayAdapter);
		// 处理头像
		ImageLoader.getInstance().displayImage(feed.getUser().getAvatar(), holder.iv_dynamic_head,PictureUtil.getCircleOption());
		// 处理图片
		
		List<Photo> list = feed.getPhotos();
		/*list.add("http://f.hiphotos.baidu.com/image/pic/item/5fdf8db1cb1349544c89855e554e9258d1094a70.jpg");
		list.add("http://a.hiphotos.baidu.com/image/pic/item/0d338744ebf81a4ca1a7833ad52a6059242da6a8.jpg");
		list.add("http://a.hiphotos.baidu.com/image/pic/item/0d338744ebf81a4ca1a7833ad52a6059242da6a8.jpg");
		list.add("http://a.hiphotos.baidu.com/image/pic/item/0d338744ebf81a4ca1a7833ad52a6059242da6a8.jpg");
		list.add("http://a.hiphotos.baidu.com/image/pic/item/0d338744ebf81a4ca1a7833ad52a6059242da6a8.jpg");*/
		if(list.size() != 0){
			holder.lv_dynamic_pic.setVisibility(View.VISIBLE);
			DynamicPicAdapter adapter = new DynamicPicAdapter(mContext,list);
			holder.lv_dynamic_pic.setAdapter(adapter); 
		}else{
			holder.lv_dynamic_pic.setVisibility(View.GONE);
		}
		
		holder.iv_dynamic_head.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Bundle bundle = new Bundle();
				bundle.putString("userName", feed.getUser().getNickname());
				controller.getContext().addBusinessData("other_id",feed.getUser_id());
				IntentUtil.intent(mContext,bundle,DynamicOtherActivity.class,false);
			}
		});
		
		holder.iv_dynamic_good.setOnClickListener(new  OnClickListener() {
			
				@Override
				public void onClick(View v) {
					new Thread(){
						public void run() {
							controller.getContext().addBusinessData("dynamic_feed_id",feed.getId());
							controller.dynamicFavour(handler,holder);
						};
					}.start();
					
				}
			}
		);
		
		holder.iv_dynamic_message.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				holder.rl_dynamic_message.setVisibility(View.VISIBLE ==holder.rl_dynamic_message.getVisibility()?View.GONE:View.VISIBLE );
			}
		});
		holder.tv_dynamic_send.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(invaild()){
					send();
				}
			}
			private void send() {
				ProgressDialogUtil.showProgressDialog(mContext, "发送中…", false);
				new Thread(new Runnable() {
					@Override
					public void run() {
						controller.dynamicRepaly(dynamicHandler,handler);
						holder.rl_dynamic_message.setVisibility(View.GONE);
						ProgressDialogUtil.closeProgressDialog();
					}
				}).start();
			}
			private boolean invaild() {
				String message = holder.et_dynamic_message.getText().toString().trim();
				if (TextUtils.isEmpty(message)) {
					ToastUtil.showToast(mContext, "回复不能为空", ToastUtil.LENGTH_LONG);
					return false;
				}else{
					controller.getContext().addBusinessData("dynamic_feed_id",feed.getId());
					controller.getContext().addBusinessData("dynamic_feed_replay_message",message);
				}
				return true;
			}
		});
		return convertView;
	}
	public static class ViewHolder{
		private ImageView iv_dynamic_head; 				
		private TextView tv_dynamic_name;
		private ImageView iv_dynamic_pass; 	             			
		private ImageView iv_dynamic_sex; 	             			
		private TextView tv_dynamic_age;		
		private TextView tv_dynamic_constella;
		private TextView tv_dynamic_content;
		private TextView tv_dynamic_time;
		
		private TextView iv_dynamic_good_num;
		private ImageView iv_dynamic_good;
		private TextView tv_dynamic_add_1;
		private ImageView iv_dynamic_message;
		private RelativeLayout rl_dynamic_message;
		private HorizontalListView lv_dynamic_pic ;
		private ListView lv_dynamic_replay;
		private EditText et_dynamic_message;
		private TextView tv_dynamic_send;
		
		private void findView(View view){
			iv_dynamic_head = (ImageView)view.findViewById(R.id.iv_dynamic_head);
			tv_dynamic_name = (TextView)view.findViewById(R.id.tv_dynamic_name);
			iv_dynamic_pass = (ImageView)view.findViewById(R.id.iv_dynamic_pass);
			iv_dynamic_sex = (ImageView)view.findViewById(R.id.iv_dynamic_sex);
			tv_dynamic_age = (TextView)view.findViewById(R.id.tv_dynamic_age);
			tv_dynamic_constella = (TextView)view.findViewById(R.id.tv_dynamic_constella);
			tv_dynamic_content = (TextView)view.findViewById(R.id.tv_dynamic_content);
			tv_dynamic_time = (TextView)view.findViewById(R.id.tv_dynamic_time);
			tv_dynamic_send = (TextView)view.findViewById(R.id.tv_dynamic_send);

			iv_dynamic_good_num = (TextView)view.findViewById(R.id.iv_dynamic_good_num);
			iv_dynamic_good = (ImageView)view.findViewById(R.id.iv_dynamic_good);
			tv_dynamic_add_1 = (TextView)view.findViewById(R.id.tv_dynamic_add_1);
			iv_dynamic_message = (ImageView)view.findViewById(R.id.iv_dynamic_message);
			rl_dynamic_message = (RelativeLayout)view.findViewById(R.id.rl_dynamic_message);
			lv_dynamic_pic = (HorizontalListView)view.findViewById(R.id.hlv_dynamic_pic);
			lv_dynamic_replay = (ListView)view.findViewById(R.id.lv_dynamic_replay);
			et_dynamic_message = (EditText)view.findViewById(R.id.et_dynamic_message);
		}
	}
}
