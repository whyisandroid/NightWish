package com.timetalent.client.ui.message;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.timetalent.client.R;
import com.timetalent.client.entities.ChatMsg;
import com.timetalent.client.service.AppController;
import com.timetalent.client.ui.BaseActivity;
import com.timetalent.client.ui.adapter.MessageChatAdapter;


/******************************************
 * 类描述： 聊天页面
 * 类名称：MessageChatActivity  
 * @version: 1.0
 * @author: why
 * @time: 2014-10-15 下午8:10:24 
 ******************************************/
public class MessageChatActivity extends BaseActivity implements OnClickListener {
	private AppController controller;
	private ListView lv_chat;
	
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
	}
	/**
	 * 方法描述：TODO
	 * 
	 * @author: why
	 * @time: 2014-10-10 下午6:36:02
	 */
	private void initView() {
		((TextView)findViewById(R.id.main_top_title)).setText("威尔斯顿");
		
		List<ChatMsg> list  = new ArrayList<ChatMsg>();
		initList(list);
		MessageChatAdapter adapter = new MessageChatAdapter(this,list);
		lv_chat.setAdapter(adapter);
		
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
		case R.id.bt_login_next:
			break;
		default:
			break;
		}
	}

}
