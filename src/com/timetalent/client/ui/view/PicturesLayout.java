package com.timetalent.client.ui.view;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.timetalent.client.R;
import com.timetalent.client.entities.MyPhotopackage;
import com.timetalent.client.entities.Userinfopackage;
import com.timetalent.client.service.AppController;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.Scroller;
import android.widget.ViewFlipper;

public class PicturesLayout extends LinearLayout {
	private AppController controller;
	ViewFlipper vfpics;
	LinearLayout ltabs;
	private LayoutInflater mInflater;
	/**
	  * 类的构造方法
	  * 创建一个新的实例 PicturesLayout.
	  * @param 
	  * @param context
	  */
	public PicturesLayout(Context context) {
		super(context);
		mInflater = LayoutInflater.from(context);
		mInflater.inflate(R.layout.pictures_layout,
			    null);
		findView();
	}
	public void setcontroller(AppController c){
		controller = c;
	}
	/**
	  * 类的构造方法
	  * 创建一个新的实例 PicturesLayout.
	  * @param 
	  * @param context
	  * @param attrs
	  */
	public PicturesLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		mInflater = LayoutInflater.from(context);
		mInflater.inflate(R.layout.pictures_layout,
			    null);
		findView();
	}
	/**
	  * 类的构造方法
	  * 创建一个新的实例 PicturesLayout.
	  * @param 
	  * @param context
	  * @param attrs
	  * @param defStyle
	  */
	@SuppressLint("NewApi")
	public PicturesLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mInflater = LayoutInflater.from(context);
		mInflater.inflate(R.layout.pictures_layout,
			    null);
		findView();
	}

	private void findView() {
		vfpics = (ViewFlipper) findViewById(R.id.vfpics);
		ltabs = (LinearLayout) findViewById(R.id.ltabs);
	}

	private void initView() {
		new Thread(){
			public void run() {
				controller.myphoto();
				handler.sendEmptyMessage(1);
			};
		}.start();
	}
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// super.handleMessage(msg);
			switch (msg.what) {
			case 1:
				List<MyPhotopackage> list =  (List<MyPhotopackage>) controller.getContext().getBusinessData("MyphotoData");
				if(list != null){
					int count = list.size();
					for(int i = 0;i < count/8 +1 ;i++){
						LinearLayout child = new LinearLayout(PicturesLayout.this.getContext());
						LayoutInflater.from(child.getContext()).inflate(R.layout.pictures_item,
							    null);
						vfpics.addView(child);
					}
				}
				break;
			}
		}
	};
}