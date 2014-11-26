package com.timetalent.client.ui.view;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.timetalent.client.R;
import com.timetalent.client.entities.MyPhotopackage;
import com.timetalent.client.entities.Userinfopackage;
import com.timetalent.client.service.AppController;
import com.timetalent.client.ui.fragment.util.Background1;
import com.timetalent.client.ui.near.FansActivity;
import com.timetalent.client.ui.near.PictureActivity;
import com.timetalent.common.util.IntentUtil;
import com.timetalent.common.util.PictureUtil;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.Scroller;
import android.widget.ViewFlipper;

public class PicturesLayout extends LinearLayout implements
		GestureDetector.OnDoubleTapListener,
		android.view.GestureDetector.OnGestureListener {
	private AppController controller;
	ViewFlipper vfpics;
	LinearLayout ltabs;
	private LayoutInflater mInflater;
	Context mcontext;
	int index = 0;
	List<MyPhotopackage> list = null;
	List<Drawable> pictures = null;
	private GestureDetector mGestureDetector;

	/**
	 * 类的构造方法 创建一个新的实例 PicturesLayout.
	 * 
	 * @param
	 * @param context
	 */
	public PicturesLayout(Context context) {
		super(context);
		mInflater = LayoutInflater.from(context);
		mInflater.inflate(R.layout.pictures_layout, null);
		mcontext = context;
		findView();
	}

	public void setcontroller(AppController c) {
		controller = c;
	}

	/**
	 * 类的构造方法 创建一个新的实例 PicturesLayout.
	 * 
	 * @param
	 * @param context
	 * @param attrs
	 */
	public PicturesLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		mInflater = LayoutInflater.from(context);
		mInflater.inflate(R.layout.pictures_layout, null);
		mcontext = context;
		findView();
	}

	/**
	 * 类的构造方法 创建一个新的实例 PicturesLayout.
	 * 
	 * @param
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	@SuppressLint("NewApi")
	public PicturesLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mInflater = LayoutInflater.from(context);
		mInflater.inflate(R.layout.pictures_layout, null);
		mcontext = context;
		findView();
	}

	private void findView() {
		mGestureDetector = new GestureDetector(this);
		vfpics = (ViewFlipper) findViewById(R.id.vfpics);
		ltabs = (LinearLayout) findViewById(R.id.ltabs);
	}

	private void initView() {
		new Thread() {
			public void run() {
				controller.myphoto();
				list = (List<MyPhotopackage>) controller.getContext()
						.getBusinessData("MyphotoData");
				pictures = new ArrayList<Drawable>();
				for (MyPhotopackage p : list) {
					pictures.add(PictureUtil.getImage(p.getUrl(),
							p.getUser_id(), p.getId()));
				}
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
				if (list != null) {
					int count = list.size();
					for (int i = 0; i < count / 8 + 1; i++) {
						LinearLayout child = new LinearLayout(
								PicturesLayout.this.getContext());
						LayoutInflater.from(child.getContext()).inflate(
								R.layout.pictures_item, null);
						ImageView imgpic1 = (ImageView) child
								.findViewById(R.id.img1);
						ImageView imgpic2 = (ImageView) child
								.findViewById(R.id.img2);
						;
						ImageView imgpic3 = (ImageView) child
								.findViewById(R.id.img3);
						;
						ImageView imgpic4 = (ImageView) child
								.findViewById(R.id.img4);
						;
						ImageView imgpic5 = (ImageView) child
								.findViewById(R.id.img5);
						;
						ImageView imgpic6 = (ImageView) child
								.findViewById(R.id.img6);
						;
						ImageView imgpic7 = (ImageView) child
								.findViewById(R.id.img7);
						;
						ImageView imgpic8 = (ImageView) child
								.findViewById(R.id.img8);
						;
						imgpic1.setImageDrawable(new Background1());
						imgpic2.setImageDrawable(new Background1());
						imgpic3.setImageDrawable(new Background1());
						imgpic4.setImageDrawable(new Background1());
						imgpic5.setImageDrawable(new Background1());
						imgpic6.setImageDrawable(new Background1());
						imgpic7.setImageDrawable(new Background1());
						imgpic8.setImageDrawable(new Background1());
						imgpic1.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(View v) {
								IntentUtil.intent(mcontext,
										PictureActivity.class);
							}
						});
						imgpic2.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(View v) {
								IntentUtil.intent(mcontext,
										PictureActivity.class);
							}
						});
						imgpic3.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(View v) {
								IntentUtil.intent(mcontext,
										PictureActivity.class);
							}
						});
						imgpic4.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(View v) {
								IntentUtil.intent(mcontext,
										PictureActivity.class);
							}
						});
						imgpic5.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(View v) {
								IntentUtil.intent(mcontext,
										PictureActivity.class);
							}
						});
						imgpic6.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(View v) {
								IntentUtil.intent(mcontext,
										PictureActivity.class);
							}
						});
						imgpic7.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(View v) {
								IntentUtil.intent(mcontext,
										PictureActivity.class);
							}
						});
						imgpic8.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(View v) {
								IntentUtil.intent(mcontext,
										PictureActivity.class);
							}
						});
						for (int j = 0; j < 8; j++) {
							switch (j) {
							case 0:
								if(j*i < pictures.size()){
									imgpic1.setImageDrawable(pictures.get(i*8+j));
								}
								break;
							case 1:
								if(j*i < pictures.size()){
								imgpic2.setImageDrawable(pictures.get(i*8+j));
								}
								break;
							case 2:
								if(j*i < pictures.size()){
								imgpic3.setImageDrawable(pictures.get(i*8+j));
								}
								break;
							case 3:
								if(j*i < pictures.size()){
								imgpic4.setImageDrawable(pictures.get(i*8+j));
								}
								break;
							case 4:
								if(j*i < pictures.size()){
								imgpic5.setImageDrawable(pictures.get(i*8+j));
								}
								break;
							case 5:
								if(j*i < pictures.size()){
								imgpic6.setImageDrawable(pictures.get(i*8+j));
								}
								break;
							case 6:
								if(j*i < pictures.size()){
								imgpic7.setImageDrawable(pictures.get(i*8+j));
								}
								break;
							case 7:
								if(j*i < pictures.size()){
								imgpic8.setImageDrawable(pictures.get(i*8+j));
								}
								break;
							default:
								break;
							}
						}
						vfpics.addView(child);
						ImageView tab = new ImageView(ltabs.getContext());
						if (i == 0) {
							tab.setImageResource(R.drawable.f10_26);
						} else {
							tab.setImageResource(R.drawable.f10_24);
						}

						ltabs.addView(child);
					}
				}
				break;
			}
		}
	};

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.GestureDetector.OnGestureListener#onDown(android.view.
	 * MotionEvent)
	 */
	@Override
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.GestureDetector.OnGestureListener#onFling(android.view.
	 * MotionEvent, android.view.MotionEvent, float, float)
	 */
	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		// TODO Auto-generated method stub
		if (e1.getX() > e2.getX() && Math.abs(e1.getY()) < 450) {// 向左滑动
			vfpics.setInAnimation(mcontext, R.anim.push_left_in);
			vfpics.setOutAnimation(mcontext, R.anim.push_left_out);
			vfpics.showNext();
			index++;
		} else if (e1.getX() < e2.getX() && Math.abs(e1.getY()) < 450) {// 向右滑动
			vfpics.setInAnimation(mcontext, R.anim.push_right_in);
			vfpics.setOutAnimation(mcontext, R.anim.push_right_out);
			vfpics.showPrevious();
			index--;
		} else {
			return false;
		}
		for(int x = 0;x < list.size()/8;x++){
			if(x == index%(list.size()/8)){
				((ImageView)ltabs.getChildAt(x)).setImageResource(R.drawable.f10_26);
			}else{
				((ImageView)ltabs.getChildAt(x)).setImageResource(R.drawable.f10_24);
			}
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.view.GestureDetector.OnGestureListener#onLongPress(android.view
	 * .MotionEvent)
	 */
	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.view.GestureDetector.OnGestureListener#onScroll(android.view.
	 * MotionEvent, android.view.MotionEvent, float, float)
	 */
	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.view.GestureDetector.OnGestureListener#onShowPress(android.view
	 * .MotionEvent)
	 */
	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.view.GestureDetector.OnGestureListener#onSingleTapUp(android.
	 * view.MotionEvent)
	 */
	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.view.GestureDetector.OnDoubleTapListener#onDoubleTap(android.
	 * view.MotionEvent)
	 */
	@Override
	public boolean onDoubleTap(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.view.GestureDetector.OnDoubleTapListener#onDoubleTapEvent(android
	 * .view.MotionEvent)
	 */
	@Override
	public boolean onDoubleTapEvent(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.view.GestureDetector.OnDoubleTapListener#onSingleTapConfirmed
	 * (android.view.MotionEvent)
	 */
	@Override
	public boolean onSingleTapConfirmed(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		super.dispatchTouchEvent(ev);
		return mGestureDetector.onTouchEvent(ev);
	}
}