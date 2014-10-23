package com.timetalent.client.ui.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
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
public class ExitIOSStyleListDialog extends BasicDialog implements DialogInterface{
	public LinearLayout lexit;
	public LinearLayout lchange;
	
	public static final int DIALOG_ONE = 1;  //Dialog 样式  一个Button
	public static final int DIALOG_TWO = 2;  // Dialog 样式 2个BUtton
	
	/**
	 * 类的构造方法
	 * 创建一个新的实例 IOSStyleDialog.
	 * @param 
	 */
	public ExitIOSStyleListDialog(Context context,int type) {
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
	private ExitIOSStyleListDialog(Context context, int width, int height, int style) {
		super(context, width, height,style);
	}
	
	
	/**
	  * 方法描述：TODO
	  * @author: why
	  * @time: 2014-8-13 下午5:00:31
	  */
	private void initOne() {
		setContentView(R.layout.dialog_self_title_one);
	}
	
	/**
	  * 方法描述：TODO
	  * @author: why
	  * @time: 2014-8-13 下午5:00:34
	  */
	private void InitTwo() {
		setContentView(R.layout.dialog_list);
		lexit = (LinearLayout) findViewById(R.id.lexit);
		lchange = (LinearLayout) findViewById(R.id.lchange);
		lexit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				System.exit(0);
			}
		});
		lchange.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dismiss();
			}
		});
	}

	
	public ExitIOSStyleListDialog setMessage(String message){
		return this;
	}
	
	
	public ExitIOSStyleListDialog setMessage(int  messageId){
		return this;
	}
	
	public ExitIOSStyleListDialog setmTitle(String message){
		return this;
	}
	
	public ExitIOSStyleListDialog setmTitle(int titleId){
		return this;
	}
	public ExitIOSStyleListDialog setUp(View.OnClickListener listener ){
		this.lexit.setOnClickListener(listener);
		return this;
	}
	public ExitIOSStyleListDialog setDown(View.OnClickListener listener ){
		this.lchange.setOnClickListener(listener);
		return this;
	}
	public ExitIOSStyleListDialog setLeft(CharSequence text,View.OnClickListener listener ){
		return this;
	}
	
	public ExitIOSStyleListDialog setLeft(int textId,View.OnClickListener listener ){
		return this;
	}
	
	public ExitIOSStyleListDialog setRight(CharSequence text,View.OnClickListener listener ){
		return this;
	}
	
	public ExitIOSStyleListDialog setRight(int textId,View.OnClickListener listener ){
		return this;
	}
	
	public ExitIOSStyleListDialog setOne(CharSequence text,View.OnClickListener listener ){
		return this;
	}
	
	public ExitIOSStyleListDialog setOne(int textId,View.OnClickListener listener ){
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
