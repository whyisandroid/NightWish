package com.timetalent.client.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;


/******************************************
 * 类描述： TODO
 * 类名称：PhotoImageView  
 * @version: 1.0
 * @author: Administrator
 * @time: 2014-11-29 下午4:27:19 
 ******************************************/
public class PhotoImageView extends ImageView {
	int index = 0;
	/**
	  * 类的构造方法
	  * 创建一个新的实例 PhotoImageView.
	  * @param 
	  * @param context
	  */
	public PhotoImageView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	/**
	  * 类的构造方法
	  * 创建一个新的实例 PhotoImageView.
	  * @param 
	  * @param context
	  * @param attrs
	  */
	public PhotoImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	/**
	  * 类的构造方法
	  * 创建一个新的实例 PhotoImageView.
	  * @param 
	  * @param context
	  * @param attrs
	  * @param defStyle
	  */
	public PhotoImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public void setindex(int i){
		index = i;
	}

	

}
