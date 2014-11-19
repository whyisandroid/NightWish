package com.timetalent.client.ui.dialog;

import java.util.List;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.timetalent.client.R;
import com.timetalent.client.entities.Userinfopackage;
import com.timetalent.client.entities.dictionarypackage;
import com.timetalent.client.service.AppController;
import com.timetalent.client.ui.adapter.ZuopinBaseAdapter;
import com.timetalent.client.ui.near.YirenActivity;



/******************************************
 * 类描述： IOS7样式 Dialog
 * 类名称：IOSStyleDialog  
 * @version: 1.0
 * @author: why
 * @time: 2014-8-13 下午4:01:22 
 ******************************************/
public class ReportIOSStyleDialog extends BasicDialog implements DialogInterface{
	private Button bt_left; // 右边button
	private Button bt_right; // 左边button
	public RadioGroup rg;
	public RadioButton rb1;
	public RadioButton rb2;
	public RadioButton rb3;
	public EditText etreport;
	public int index = 0;
	public  Button bt_one;// button one
	AppController controller;
	Context context;
	Userinfopackage u ;
	public static final int DIALOG_ONE = 1;  //Dialog 样式  一个Button 
	public static final int DIALOG_TWO = 2;  // Dialog 样式 2个BUtton
	
	/**
	 * 类的构造方法
	 * 创建一个新的实例 IOSStyleDialog.
	 * @param 
	 */
	public ReportIOSStyleDialog(Context context,AppController c, int type) {
		this(context, 280, 150, R.style.add_dialog);
		this.controller = c;
		this.context = context;
		u = (Userinfopackage) controller.getContext().getBusinessData("UserinfoData");
		if(type == DIALOG_ONE){
			initOne();
			this.show();
		}else if(type ==  DIALOG_TWO){
			new Thread(){
				public void run() {
					controller.getContext().addBusinessData("dictionary.type", "report");
					controller.dictionary();
					handler.sendEmptyMessage(1);
				};
			}.start();
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
	private ReportIOSStyleDialog(Context context, int width, int height, int style) {
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
		setContentView(R.layout.dialog_report);
		rg = (RadioGroup) findViewById(R.id.radioGroup1);
		final List<dictionarypackage> list =(List<dictionarypackage>) controller.getContext().getBusinessData("DictionaryData");
		
		if(list != null){
			for(int i = 0;i < list.size();i++){
				dictionarypackage pk = list.get(i);
				RadioButton rb = new RadioButton(context);
				rb.setButtonDrawable(null);
				rb.setTextColor((ColorStateList) context.getResources().getColorStateList(R.color.color_2));
				rb.setTextSize(14);
				rb.setText(pk.getName());
				rb.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						for(int j = 0;j< list.size();j++){
							if(((RadioButton)(v)).getText().toString().equals(list.get(j).getName())){
								index = j;
							}
						}
						
					}
				});
				rg.addView(rb);
			}
			
		}
		etreport = (EditText) findViewById(R.id.etreport);
		bt_left = (Button)findViewById(R.id.bt_dialog_no_title_left);
		bt_left.setText("取消");
		bt_left.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
//				IntentUtil.intent(mContext, MainFragmentActivity.class);
				closeDialog();
			}
		});
		bt_right = (Button)findViewById(R.id.bt_dialog_no_title_right);
		bt_right.setText("确认");
		bt_right.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				new Thread(){
					public void run() {
						controller.getContext().addBusinessData("report.type", list.get(index).getKey());
						controller.getContext().addBusinessData("report.target_id", u.getId());
						controller.getContext().addBusinessData("report.msg", etreport.getText().toString());
						controller.myreport();
					};
				}.start();
				closeDialog();
			}
		});
	}

	
	public ReportIOSStyleDialog setMessage(String message){
		return this;
	}
	
	
	public ReportIOSStyleDialog setMessage(int  messageId){
		return this;
	}
	
	public ReportIOSStyleDialog setmTitle(String message){
		return this;
	}
	
	public ReportIOSStyleDialog setmTitle(int titleId){
		return this;
	}
	
	public ReportIOSStyleDialog setLeft(CharSequence text,View.OnClickListener listener ){
		this.bt_left.setText(text);
		this.bt_left.setOnClickListener(listener);
		return this;
	}
	
	public ReportIOSStyleDialog setLeft(int textId,View.OnClickListener listener ){
		this.bt_left.setText(textId);
		this.bt_left.setOnClickListener(listener);
		return this;
	}
	
	public ReportIOSStyleDialog setRight(CharSequence text,View.OnClickListener listener ){
		this.bt_right.setText(text);
		this.bt_right.setOnClickListener(listener);
		return this;
	}
	
	public ReportIOSStyleDialog setRight(int textId,View.OnClickListener listener ){
		this.bt_right.setText(textId);
		this.bt_right.setOnClickListener(listener);
		return this;
	}
	
	public ReportIOSStyleDialog setOne(CharSequence text,View.OnClickListener listener ){
		this.bt_one.setText(text);
		this.bt_one.setOnClickListener(listener);
		return this;
	}
	
	public ReportIOSStyleDialog setOne(int textId,View.OnClickListener listener ){
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
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// super.handleMessage(msg);
			switch (msg.what) {
			case 1:
				InitTwo();
				ReportIOSStyleDialog.this.show();
				break;
			}
		}
	};
}
