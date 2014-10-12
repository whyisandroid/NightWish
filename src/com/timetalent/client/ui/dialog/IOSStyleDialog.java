package com.timetalent.client.ui.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.timetalent.client.R;



/******************************************
 * 类描述： IOS7样式 Dialog
 * 类名称：IOSStyleDialog  
 * @version: 1.0
 * @author: why
 * @time: 2014-8-13 下午4:01:22 
 ******************************************/
public class IOSStyleDialog extends BasicDialog implements DialogInterface{
	private Button bt_left; // 右边button
	private Button bt_right; // 左边button
	public TextView message; // 提示内容
	
	public  TextView title;// 标题内容
	public  Button bt_one;// button one
	
	public static final int DIALOG_ONE = 1;  //Dialog 样式  一个Button 
	public static final int DIALOG_TWO = 2;  // Dialog 样式 2个BUtton
	
	/**
	 * 类的构造方法
	 * 创建一个新的实例 IOSStyleDialog.
	 * @param 
	 */
	public IOSStyleDialog(Context context,int type) {
		this(context, 280, 150, R.style.add_dialog);
		if(type == DIALOG_ONE){
			initOne();
			this.show();
		}else if(type ==  DIALOG_TWO){
			InitTwo();
			this.show();
		}
	}
	/**
	  * 类的构造方法
	  * 创建一个新的实例 IOSStyleDialog.
	  * @param 
	  * @param context
	  * @param width
	  * @param height
	  * @param layout
	  * @param style
	  */
	private IOSStyleDialog(Context context, int width, int height, int style) {
		super(context, width, height,style);
	}
	
	
	/**
	  * 方法描述：TODO
	  * @author: why
	  * @time: 2014-8-13 下午5:00:31
	  */
	private void initOne() {
		setContentView(R.layout.dialog_self_title_one);
		title = (TextView)findViewById(R.id.tv_dialog_title);
		message = (TextView)findViewById(R.id.tv_dialog_message);
		bt_one = (Button)findViewById(R.id.bt_dialog_one);
	}
	
	/**
	  * 方法描述：TODO
	  * @author: why
	  * @time: 2014-8-13 下午5:00:34
	  */
	private void InitTwo() {
		setContentView(R.layout.dialog_self_title_two);
		title = (TextView)findViewById(R.id.tv_dialog_title);
		message = (TextView)findViewById(R.id.tv_dialog_message);
		bt_left = (Button)findViewById(R.id.bt_dialog_no_title_left);
		bt_right = (Button)findViewById(R.id.bt_dialog_no_title_right);
	}

	
	public IOSStyleDialog setMessage(String message){
		this.message.setText(message);
		return this;
	}
	
	
	public IOSStyleDialog setMessage(int  messageId){
		this.message.setText(messageId);
		return this;
	}
	
	public IOSStyleDialog setmTitle(String message){
		title.setVisibility(View.VISIBLE);
		this.title.setText(message);
		return this;
	}
	
	public IOSStyleDialog setmTitle(int titleId){
		title.setVisibility(View.VISIBLE);
		this.title.setText(titleId);
		return this;
	}
	
	public IOSStyleDialog setLeft(CharSequence text,View.OnClickListener listener ){
		this.bt_left.setText(text);
		this.bt_left.setOnClickListener(listener);
		return this;
	}
	
	public IOSStyleDialog setLeft(int textId,View.OnClickListener listener ){
		this.bt_left.setText(textId);
		this.bt_left.setOnClickListener(listener);
		return this;
	}
	
	public IOSStyleDialog setRight(CharSequence text,View.OnClickListener listener ){
		this.bt_right.setText(text);
		this.bt_right.setOnClickListener(listener);
		return this;
	}
	
	public IOSStyleDialog setRight(int textId,View.OnClickListener listener ){
		this.bt_right.setText(textId);
		this.bt_right.setOnClickListener(listener);
		return this;
	}
	
	public IOSStyleDialog setOne(CharSequence text,View.OnClickListener listener ){
		this.bt_one.setText(text);
		this.bt_one.setOnClickListener(listener);
		return this;
	}
	
	public IOSStyleDialog setOne(int textId,View.OnClickListener listener ){
		this.bt_one.setText(textId);
		this.bt_one.setOnClickListener(listener);
		return this;
	}
	
	/**
	  * 方法描述：关闭dialog
	  * @author: why
	  * @time: 2013-12-25 上午10:15:19
	 */
	public void closeDialog(){
		if(this!=null && isShowing()){
			dismiss();
		}
	}
}
