package com.timetalent.client.ui.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.timetalent.client.R;



/******************************************
 * 类描述： IOS7样式 Dialog
 * 类名称：IOSStyleDialog  
 * @version: 1.0
 * @author: why
 * @time: 2014-8-13 下午4:01:22 
 ******************************************/
public class IOSStyleListDialog extends BasicDialog implements DialogInterface{
	private Button bt_left; // 右边button
	private Button bt_right; // 左边button
	public RadioButton rb1;
	public RadioButton rb2;
	public RadioButton rb3;
	public int index = 0;
	public  Button bt_one;// button one
	
	public static final int DIALOG_ONE = 1;  //Dialog 样式  一个Button 
	public static final int DIALOG_TWO = 2;  // Dialog 样式 2个BUtton
	
	/**
	 * 类的构造方法
	 * 创建一个新的实例 IOSStyleDialog.
	 * @param 
	 */
	public IOSStyleListDialog(Context context,int type) {
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
	private IOSStyleListDialog(Context context, int width, int height, int style) {
		super(context, width, height,style);
	}
	
	
	/**
	  * 方法描述：TODO
	  * @author: why
	  * @time: 2014-8-13 下午5:00:31
	  */
	private void initOne() {
		setContentView(R.layout.dialog_self_title_one);
		bt_one = (Button)findViewById(R.id.bt_dialog_one);
	}
	
	/**
	  * 方法描述：TODO
	  * @author: why
	  * @time: 2014-8-13 下午5:00:34
	  */
	private void InitTwo() {
		setContentView(R.layout.dialog_list_title_two);
		rb1 = (RadioButton) findViewById(R.id.radio0);
		rb2 = (RadioButton) findViewById(R.id.radio1);
		rb3 = (RadioButton) findViewById(R.id.radio2);
		rb1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				index = 0;
			}
		});
		rb2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				index = 1;
			}
		});
		rb3.setOnClickListener(new View.OnClickListener() {
	
			@Override
			public void onClick(View v) {
				index = 2;
			}
		});
		bt_left = (Button)findViewById(R.id.bt_dialog_no_title_left);
		bt_right = (Button)findViewById(R.id.bt_dialog_no_title_right);
	}

	
	public IOSStyleListDialog setMessage(String message){
		return this;
	}
	
	
	public IOSStyleListDialog setMessage(int  messageId){
		return this;
	}
	
	public IOSStyleListDialog setmTitle(String message){
		return this;
	}
	
	public IOSStyleListDialog setmTitle(int titleId){
		return this;
	}
	
	public IOSStyleListDialog setLeft(CharSequence text,View.OnClickListener listener ){
		this.bt_left.setText(text);
		this.bt_left.setOnClickListener(listener);
		return this;
	}
	
	public IOSStyleListDialog setLeft(int textId,View.OnClickListener listener ){
		this.bt_left.setText(textId);
		this.bt_left.setOnClickListener(listener);
		return this;
	}
	
	public IOSStyleListDialog setRight(CharSequence text,View.OnClickListener listener ){
		this.bt_right.setText(text);
		this.bt_right.setOnClickListener(listener);
		return this;
	}
	
	public IOSStyleListDialog setRight(int textId,View.OnClickListener listener ){
		this.bt_right.setText(textId);
		this.bt_right.setOnClickListener(listener);
		return this;
	}
	
	public IOSStyleListDialog setOne(CharSequence text,View.OnClickListener listener ){
		this.bt_one.setText(text);
		this.bt_one.setOnClickListener(listener);
		return this;
	}
	
	public IOSStyleListDialog setOne(int textId,View.OnClickListener listener ){
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
