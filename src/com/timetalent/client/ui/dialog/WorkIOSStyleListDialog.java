package com.timetalent.client.ui.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.timetalent.client.R;
import com.timetalent.client.ui.adapter.WorkdialogAdapter;
import com.timetalent.client.ui.chance.OfferDetailActivity;
import com.timetalent.client.ui.user.MyworkActivity;
import com.timetalent.client.ui.user.MyworkslistActivity;
import com.timetalent.common.util.IntentUtil;



/******************************************
 * 类描述： IOS7样式 Dialog
 * 类名称：IOSStyleDialog  
 * @version: 1.0
 * @author: why
 * @time: 2014-8-13 下午4:01:22 
 ******************************************/
public class WorkIOSStyleListDialog extends BasicDialog implements DialogInterface{
	public ListView lworks;
	
	public static final int DIALOG_ONE = 1;  //Dialog 样式  一个Button
	public static final int DIALOG_TWO = 2;  // Dialog 样式 2个BUtton
	Context mcontext;
	/**
	 * 类的构造方法
	 * 创建一个新的实例 IOSStyleDialog.
	 * @param 
	 */
	public WorkIOSStyleListDialog(Context context,int type) {
		this(context, 280, 150, R.style.add_dialog);
		mcontext = context;
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
	private WorkIOSStyleListDialog(Context context, int width, int height, int style) {
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
		setContentView(R.layout.my_workdialog);
		lworks = (ListView) findViewById(R.id.listView1);
		lworks.setAdapter(new WorkdialogAdapter(mcontext));
		lworks.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				IntentUtil.intent(mcontext, MyworkslistActivity.class);
				closeDialog();
			}
		});
	}

	
	public WorkIOSStyleListDialog setMessage(String message){
		return this;
	}
	
	
	public WorkIOSStyleListDialog setMessage(int  messageId){
		return this;
	}
	
	public WorkIOSStyleListDialog setmTitle(String message){
		return this;
	}
	
	public WorkIOSStyleListDialog setmTitle(int titleId){
		return this;
	}
	public WorkIOSStyleListDialog setUp(View.OnClickListener listener ){
		return this;
	}
	public WorkIOSStyleListDialog setDown(View.OnClickListener listener ){
		return this;
	}
	public WorkIOSStyleListDialog setLeft(CharSequence text,View.OnClickListener listener ){
		return this;
	}
	
	public WorkIOSStyleListDialog setLeft(int textId,View.OnClickListener listener ){
		return this;
	}
	
	public WorkIOSStyleListDialog setRight(CharSequence text,View.OnClickListener listener ){
		return this;
	}
	
	public WorkIOSStyleListDialog setRight(int textId,View.OnClickListener listener ){
		return this;
	}
	
	public WorkIOSStyleListDialog setOne(CharSequence text,View.OnClickListener listener ){
		return this;
	}
	
	public WorkIOSStyleListDialog setOne(int textId,View.OnClickListener listener ){
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
