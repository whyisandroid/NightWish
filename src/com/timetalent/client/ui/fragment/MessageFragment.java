package com.timetalent.client.ui.fragment;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

import com.timetalent.client.R;
import com.timetalent.client.entities.MessageItem;
import com.timetalent.client.service.AppController;
import com.timetalent.client.ui.adapter.MessageDetailAdapter;
import com.timetalent.client.ui.message.MessageChatActivity;
import com.timetalent.client.ui.view.ListViewCompat;
import com.timetalent.common.util.IntentUtil;


/******************************************
 * 类描述： 消息
 * 类名称：MessageFragment  
 * @version: 1.0
 * @author: why
 * @time: 2014-10-10 下午6:32:12 
 ******************************************/
public class MessageFragment extends Fragment implements OnClickListener {
	private View view;
	private Context mContext;
	private AppController controller;
	private ListViewCompat lv_message;
	
	private List<MessageItem> mMessageItems;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		controller = AppController.getController(getActivity());
		view = inflater.inflate(R.layout.message_fragment, container, false);
		mContext = getActivity();
		findView();
		initView();
		return view;
	}

	/**
	 * 方法描述：TODO
	 * 
	 * @author: why
	 * @time: 2014-10-10 下午6:36:02
	 */
	private void initView() {
		((TextView)view.findViewById(R.id.main_top_title)).setText("消息");
		view.findViewById(R.id.main_top_left).setVisibility(View.GONE);
		
		
		mMessageItems = new ArrayList<MessageItem>();
		  for (int i = 0; i < 7; i++) {
	            MessageItem item = new MessageItem();
	            if (i % 2 == 0) {
	                item.iconRes = R.drawable.m3_03;
	                item.title = "华谊兄弟";
	                item.msg = "好的，那你明天过来我们公司面试吧，我等你！";
	                item.time = "18:43";
	            } else {
	                item.iconRes = R.drawable.f3_11;
	                item.title = "谢玉峰同学";
	                item.msg = "欢迎你使用星探";
	                item.time = "12月18日";
	            }
	            mMessageItems.add(item);
	        }
		  lv_message.setAdapter(new MessageDetailAdapter(mContext,mMessageItems,lv_message));
		  lv_message.setOnItemClickListener(messageItemListener);
	}

	/**
	 * 方法描述：TODO
	 * 
	 * @author: why
	 * @time: 2014-10-10 下午6:36:00
	 */
	private void findView() {
		lv_message = (ListViewCompat)view.findViewById(R.id.lv_message);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case 0:
			
			break;
		default:
			break;
		}
	}
	
	private OnItemClickListener messageItemListener = new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			IntentUtil.intent(getActivity(), MessageChatActivity.class);
		}
	};
}
