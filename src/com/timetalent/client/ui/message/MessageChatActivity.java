package com.timetalent.client.ui.message;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.timetalent.client.R;
import com.timetalent.client.entities.ChatMsg;
import com.timetalent.client.service.AppController;
import com.timetalent.client.ui.BaseActivity;
import com.timetalent.client.ui.adapter.MessageChatAdapter;
import com.timetalent.common.util.ToastUtil;


/******************************************
 * 类描述： 聊天页面
 * 类名称：MessageChatActivity  
 * @version: 1.0
 * @author: why
 * @time: 2014-10-15 下午8:10:24 
 ******************************************/
@SuppressLint("NewApi")
public class MessageChatActivity extends BaseActivity implements OnClickListener {
	private AppController controller;
	private ListView lv_chat;  
	private EditText et_chat_message; // 输入信息
	private TextView tv_chat_send; // 发送
	private ImageView tv_chat_popup; // 表情
	
	private GridView gl_chat_popup;// 表情 Grid
	private int[] imageIds = new int[21];
	
	private List<ChatMsg> list;// 对话内容 
	private MessageChatAdapter mAdapter;//list adapter
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.message_chat_layout);
		controller = AppController.getController(this);
		findView();
		initView();
	}
	/**
	 * 方法描述：TODO
	 * 
	 * @author: why
	 * @time: 2014-10-10 下午6:36:00
	 */
	private void findView() {
		lv_chat = (ListView)findViewById(R.id.lv_chat);
		et_chat_message = (EditText)findViewById(R.id.tv_chat_message);
		tv_chat_send = (TextView) findViewById(R.id.tv_chat_send);
		tv_chat_popup = (ImageView)findViewById(R.id.tv_chat_popup);
		gl_chat_popup =(GridView)findViewById(R.id.gl_chat_popup);
	}
	/**
	 * 方法描述：TODO
	 * 
	 * @author: why
	 * @time: 2014-10-10 下午6:36:02
	 */
	private void initView() {
		((TextView)findViewById(R.id.main_top_title)).setText("威尔斯顿");
		
		list  = new ArrayList<ChatMsg>();
		initList(list);
		mAdapter = new MessageChatAdapter(this,list);
		lv_chat.setAdapter(mAdapter);
		tv_chat_send.setOnClickListener(this);
		tv_chat_popup.setOnClickListener(this);
		gl_chat_popup.setOnItemClickListener(glItemListener);
	}
	
	
	
	/**
	  * 方法描述：初始化 聊天页面 测试数据
	  * @param list
	  * @author: why
	  * @time: 2014-10-15 下午9:29:07
	  */
	private void initList(List<ChatMsg> list) {
		ChatMsg msg1 = new  ChatMsg("11111", "昨天 9:24", "你好啊", true);
		ChatMsg msg2 = new  ChatMsg("22222", "", "你也好啊, 谢谢", false);
		ChatMsg msg3 = new  ChatMsg("11111", "昨天 9:24", "请问王宝强在家吗? 我是王导,有点工作的事情找强强,你能帮我叫他过来吗?", true);
		ChatMsg msg4 = new  ChatMsg("22222", "", "宝强他现在很忙呀  宝强他现在很忙呀  宝强他现在很忙呀  宝强他现在很忙呀  宝强他现在很忙呀", false);
		list.add(msg1);
		list.add(msg2);
		list.add(msg3);  
		list.add(msg4);
		
		ChatMsg msg5 = new  ChatMsg("22222", "", "宝强他现在很忙呀  宝强他现在很忙呀  宝强他现在很忙呀  宝强他现在很忙呀  宝强他现在很忙呀", false);
		ChatMsg msg6 = new  ChatMsg("22222", "", "宝强他现在很忙呀  宝强他现在很忙呀  宝强他现在很忙呀  宝强他现在很忙呀  宝强他现在很忙呀", false);
		ChatMsg msg7 = new  ChatMsg("22222", "", "宝强他现在很忙呀  宝强他现在很忙呀  宝强他现在很忙呀  宝强他现在很忙呀  宝强他现在很忙呀", false);
	/*	list.add(msg5);
		list.add(msg6);
		list.add(msg7);*/
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_chat_send:
			send();
			break;
		case R.id.tv_chat_popup:
			if( Integer.valueOf(gl_chat_popup.getTag().toString()) == 1){
				gl_chat_popup.setTag("0");
				tv_chat_popup.setImageResource(R.drawable.m18_06);
				// 处理 删掉 数据
				List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
				SimpleAdapter simpleAdapter = new SimpleAdapter(this, listItems,
						R.layout.message_chat_popup_item, new String[] { "image" },
						new int[] { R.id.image });
				gl_chat_popup.setAdapter(simpleAdapter);
			}else{
				tv_chat_popup.setImageResource(R.drawable.f17_03);
				popup();
			}
			break;
		default:
			break;
		}
	}
	
	
	
	/**
	  * 方法描述：表情
	  * @author: wanghy
	  * @time: 2014-10-17 下午10:21:12
	  */
	private void popup(){
		List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
		// 生成21个表情的id，封装
		for (int i = 0; i < 21; i++) {
			try {
				if (i < 10) {
					Field field = R.drawable.class.getDeclaredField("f00" + i);
					int resourceId = Integer.parseInt(field.get(null)
							.toString());
					imageIds[i] = resourceId;
				} else if (i < 100) {
					Field field = R.drawable.class.getDeclaredField("f0" + i);
					int resourceId = Integer.parseInt(field.get(null)
							.toString());
					imageIds[i] = resourceId;
				} else {
					Field field = R.drawable.class.getDeclaredField("f" + i);
					int resourceId = Integer.parseInt(field.get(null)
							.toString());
					imageIds[i] = resourceId;
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			Map<String, Object> listItem = new HashMap<String, Object>();
			listItem.put("image", imageIds[i]);
			listItems.add(listItem);
		}
		SimpleAdapter simpleAdapter = new SimpleAdapter(this, listItems,
				R.layout.message_chat_popup_item, new String[] { "image" },
				new int[] { R.id.image });
		gl_chat_popup.setAdapter(simpleAdapter);
		gl_chat_popup.setTag(1);
	}
	
	
	private void send() {
		String contString = et_chat_message.getText().toString();
		if (contString.length() > 0) {
			ChatMsg entity = new ChatMsg();
			entity.setDate(getDate());
			entity.setName("高富帅");
			entity.setMsgType(false);
			entity.setText(contString);

			list.add(entity);
			mAdapter.notifyDataSetChanged();
			et_chat_message.setText("");
			lv_chat.setSelection(lv_chat.getCount() - 1);
		}
	}

	
	private String getDate() {
		Calendar c = Calendar.getInstance();

		String year = String.valueOf(c.get(Calendar.YEAR));
		String month = String.valueOf(c.get(Calendar.MONTH));
		String day = String.valueOf(c.get(Calendar.DAY_OF_MONTH) + 1);
		String hour = String.valueOf(c.get(Calendar.HOUR_OF_DAY));
		String mins = String.valueOf(c.get(Calendar.MINUTE));

		StringBuffer sbBuffer = new StringBuffer();
		sbBuffer.append(year + "-" + month + "-" + day + " " + hour + ":"
				+ mins);

		return sbBuffer.toString();
	}
	
	private OnItemClickListener glItemListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {

			Bitmap bitmap = null;
			bitmap = BitmapFactory.decodeResource(getResources(), imageIds[arg2 % imageIds.length]);
			ImageSpan imageSpan = new ImageSpan(MessageChatActivity.this, bitmap);
			String str = null;
			if(arg2<10){
				str = "f00"+arg2;
			}else if(arg2<100){
				str = "f0"+arg2;
			}else{
				str = "f"+arg2;
			}
			SpannableString spannableString = new SpannableString(str);
			spannableString.setSpan(imageSpan, 0, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			et_chat_message.append(spannableString);
		
		}
	};
	
	
}
