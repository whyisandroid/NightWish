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
import com.timetalent.client.ui.fragment.util.Background3;
import com.timetalent.client.ui.near.FansActivity;
import com.timetalent.client.ui.near.PictureActivity;
import com.timetalent.client.ui.user.FansziliaobianjiActivity;
import com.timetalent.common.util.IntentUtil;
import com.timetalent.common.util.PictureUtil;
import com.timetalent.common.util.StringUtil;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager.OnActivityResultListener;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.Gravity;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import android.widget.Scroller;
import android.widget.ViewFlipper;

public class EditPicturesLayout extends LinearLayout implements
		GestureDetector.OnDoubleTapListener,
		android.view.GestureDetector.OnGestureListener {
	private AppController controller;
	ViewFlipper vfpics;
	LinearLayout ltabs;
	private LayoutInflater mInflater;
	Context mcontext;
	int index = 0;
	int photoindex = 0;
	public int screenw = 0;
	public float density = 1.0f;
	List<MyPhotopackage> list = null;
	List<Drawable> pictures = null;
	private GestureDetector mGestureDetector;

	/**
	 * 类的构造方法 创建一个新的实例 PicturesLayout.
	 * 
	 * @param
	 * @param context
	 */
	public EditPicturesLayout(Context context) {
		super(context);
		mInflater = LayoutInflater.from(context);
		mInflater.inflate(R.layout.pictures_layout, null);
		mcontext = context;
		DisplayMetrics dm2 = getResources().getDisplayMetrics();
		screenw = dm2.widthPixels;
		density = dm2.density;
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
	public EditPicturesLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		mInflater = LayoutInflater.from(context);
		mInflater.inflate(R.layout.pictures_layout, null);
		mcontext = context;
		DisplayMetrics dm2 = getResources().getDisplayMetrics();
		screenw = dm2.widthPixels;
		density = dm2.density;
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
	public EditPicturesLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mInflater = LayoutInflater.from(context);
		mInflater.inflate(R.layout.pictures_layout, null);
		mcontext = context;
		DisplayMetrics dm2 = getResources().getDisplayMetrics();
		screenw = dm2.widthPixels;
		density = dm2.density;
		findView();
	}

	private void findView() {
		mGestureDetector = new GestureDetector(this);
//		vfpics = (ViewFlipper) this.findViewById(R.id.vfpics1);
//		vfpics = (ViewFlipper) LayoutInflater.from(PicturesLayout.this.getContext()).inflate(
//				R.layout.pictures_item, null);
		vfpics = new ViewFlipper(this.mcontext);
		if(vfpics == null){
			Log.i("sssasas", "vf");
		}
//		ltabs = (LinearLayout) this.findViewById(R.id.ltabs1);
		ltabs = new LinearLayout(mcontext);
		this.setOrientation(this.VERTICAL);
		this.setGravity(Gravity.CENTER_HORIZONTAL);
		ltabs.setGravity(Gravity.CENTER_HORIZONTAL);
		this.addView(vfpics);
		this.addView(ltabs,new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
	}

	public void initView() {
		new Thread() {
			public void run() {
				controller.myphoto();
				list = (List<MyPhotopackage>) controller.getContext()
						.getBusinessData("MyphotoData");
				pictures = new ArrayList<Drawable>();
				if(list!= null){
					for (MyPhotopackage p : list) {
						Drawable db = PictureUtil.getImage(p.getUrl(),
								p.getUser_id(), p.getId());
						if(db instanceof BitmapDrawable){
							pictures.add(new BitmapDrawable( PictureUtil.toRoundCorner(((BitmapDrawable) db).getBitmap(), (int)(10*density))));
						}else{
							pictures.add(db);
						}
						controller.getContext().addBusinessData("photolist", pictures);
					}
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
				if(list == null){
					vfpics.removeAllViews();
					LinearLayout child = (LinearLayout) LayoutInflater.from(EditPicturesLayout.this.getContext()).inflate(
							R.layout.pictures_item, null);
					PhotoImageView imgpic1 = (PhotoImageView) child
							.findViewById(R.id.img1);
					imgpic1.setImageDrawable(new Background1());
					LayoutParams  p1 = (LayoutParams) imgpic1.getLayoutParams();
					p1.height = (int)(screenw/4-8*density);
					imgpic1.setImageResource(R.drawable.d11_03);
					imgpic1.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							StringUtil.doGoToImg(EditPicturesLayout.this.getContext());
						}
					});
					PhotoImageView imgpic2 = (PhotoImageView) child
							.findViewById(R.id.img2);
					imgpic2.setVisibility(INVISIBLE);
					PhotoImageView imgpic3 = (PhotoImageView) child
							.findViewById(R.id.img3);
					imgpic3.setVisibility(INVISIBLE);
					PhotoImageView imgpic4 = (PhotoImageView) child
							.findViewById(R.id.img4);
					imgpic4.setVisibility(INVISIBLE);
					PhotoImageView imgpic5 = (PhotoImageView) child
							.findViewById(R.id.img5);
					imgpic5.setVisibility(GONE);
					PhotoImageView imgpic6 = (PhotoImageView) child
							.findViewById(R.id.img6);
					imgpic6.setVisibility(GONE);
					PhotoImageView imgpic7 = (PhotoImageView) child
							.findViewById(R.id.img7);
					imgpic7.setVisibility(GONE);
					PhotoImageView imgpic8 = (PhotoImageView) child
							.findViewById(R.id.img8);
					imgpic8.setVisibility(GONE);
					LayoutParams  p2 = (LayoutParams) imgpic2.getLayoutParams();
					p2.height = (int)(screenw/4-8*density);
					
					LayoutParams  p3 = (LayoutParams) imgpic3.getLayoutParams();
					p3.height = (int)(screenw/4-8*density);
					
					LayoutParams  p4 = (LayoutParams) imgpic4.getLayoutParams();
					p4.height = (int)(screenw/4-8*density);
					
					LayoutParams  p5 = (LayoutParams) imgpic5.getLayoutParams();
					p5.height = (int)(screenw/4-8*density);
					
					LayoutParams  p6 = (LayoutParams) imgpic6.getLayoutParams();
					p6.height = (int)(screenw/4-8*density);
					
					LayoutParams  p7 = (LayoutParams) imgpic7.getLayoutParams();
					p7.height = (int)(screenw/4-8*density);
					
					LayoutParams  p8 = (LayoutParams) imgpic8.getLayoutParams();
					p8.height = (int)(screenw/4-8*density);
					vfpics.addView(child);
					ImageView tab = new ImageView(ltabs.getContext());
						tab.setImageResource(R.drawable.f10_26);
					LinearLayout.LayoutParams pitem = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
					ltabs.addView(tab,pitem);
					
				}
				if (list != null) {
					vfpics.removeAllViews();
					int count = list.size();
					for (int i = 0; i < count / 8 + 1; i++) {
//						LinearLayout child = new LinearLayout(
//								PicturesLayout.this.getContext());
						LinearLayout child = (LinearLayout) LayoutInflater.from(EditPicturesLayout.this.getContext()).inflate(
								R.layout.pictures_item, null);
						PhotoImageView imgpic1 = (PhotoImageView) child
								.findViewById(R.id.img1);
						PhotoImageView imgpic2 = (PhotoImageView) child
								.findViewById(R.id.img2);
						PhotoImageView imgpic3 = (PhotoImageView) child
								.findViewById(R.id.img3);
						PhotoImageView imgpic4 = (PhotoImageView) child
								.findViewById(R.id.img4);
						PhotoImageView imgpic5 = (PhotoImageView) child
								.findViewById(R.id.img5);
						PhotoImageView imgpic6 = (PhotoImageView) child
								.findViewById(R.id.img6);
						PhotoImageView imgpic7 = (PhotoImageView) child
								.findViewById(R.id.img7);
						PhotoImageView imgpic8 = (PhotoImageView) child
								.findViewById(R.id.img8);
						imgpic1.setImageDrawable(new Background1());
						imgpic2.setImageDrawable(new Background1());
						imgpic3.setImageDrawable(new Background1());
						imgpic4.setImageDrawable(new Background1());
						imgpic5.setImageDrawable(new Background1());
						imgpic6.setImageDrawable(new Background1());
						imgpic7.setImageDrawable(new Background1());
						imgpic8.setImageDrawable(new Background1());
						LayoutParams  p1 = (LayoutParams) imgpic1.getLayoutParams();
						p1.height = (int)(screenw/4-8*density);
						
						LayoutParams  p2 = (LayoutParams) imgpic2.getLayoutParams();
						p2.height = (int)(screenw/4-8*density);
						
						LayoutParams  p3 = (LayoutParams) imgpic3.getLayoutParams();
						p3.height = (int)(screenw/4-8*density);
						
						LayoutParams  p4 = (LayoutParams) imgpic4.getLayoutParams();
						p4.height = (int)(screenw/4-8*density);
						
						LayoutParams  p5 = (LayoutParams) imgpic5.getLayoutParams();
						p5.height = (int)(screenw/4-8*density);
						
						LayoutParams  p6 = (LayoutParams) imgpic6.getLayoutParams();
						p6.height = (int)(screenw/4-8*density);
						
						LayoutParams  p7 = (LayoutParams) imgpic7.getLayoutParams();
						p7.height = (int)(screenw/4-8*density);
						
						LayoutParams  p8 = (LayoutParams) imgpic8.getLayoutParams();
						p8.height = (int)(screenw/4-8*density);
						for (int j = 0; j < 8; j++) {
							switch (j) {
							case 0:
								if(j*i < pictures.size()){
									if(i*8+j < pictures.size()){
										imgpic1.setindex(i*8+j);
										imgpic1.id = list.get(i*8+j).getId();
										imgpic1.setImageDrawable(pictures.get(i*8+j));
										
										imgpic1.setOnClickListener(new OnClickListener() {
											@Override
											public void onClick(View v) {
												deletephoto((PhotoImageView) v);
											}
										});
										
									}else if(i*8+j == pictures.size()){
										imgpic1.setImageResource(R.drawable.d11_03);
										imgpic1.setOnClickListener(new OnClickListener() {
											@Override
											public void onClick(View v) {
												StringUtil.doGoToImg(EditPicturesLayout.this.getContext());
											}
										});
									}else{
										imgpic1.setImageDrawable(new Background1());
										imgpic1.setOnClickListener(null);
										imgpic1.setVisibility(INVISIBLE);
									}
									
								}
								if(i == 0 && pictures.size() == 0){
									imgpic1.setImageResource(R.drawable.d11_03);
									imgpic1.setOnClickListener(new OnClickListener() {
										@Override
										public void onClick(View v) {
											StringUtil.doGoToImg(EditPicturesLayout.this.getContext());
										}
									});
								}
								break;
							case 1:
								if(j*i < pictures.size()){if(i*8+j < pictures.size()){
									imgpic2.setindex(i*8+j);
									imgpic2.id = list.get(i*8+j).getId();
									imgpic2.setImageDrawable(pictures.get(i*8+j));
									
									imgpic2.setOnClickListener(new OnClickListener() {
										@Override
										public void onClick(View v) {
											deletephoto((PhotoImageView) v);
										}
									});
									
									
								}else if(i*8+j == pictures.size()){
									imgpic2.setImageResource(R.drawable.d11_03);
									imgpic2.setOnClickListener(new OnClickListener() {
										@Override
										public void onClick(View v) {
											StringUtil.doGoToImg(EditPicturesLayout.this.getContext());
										}
									});
								}else{
									imgpic2.setImageDrawable(new Background1());
									imgpic2.setOnClickListener(null);
									imgpic2.setVisibility(INVISIBLE);
								}}
								break;
							case 2:
								if(j*i < pictures.size()){if(i*8+j < pictures.size()){
									imgpic3.setindex(i*8+j);
									imgpic3.id = list.get(i*8+j).getId();
									imgpic3.setImageDrawable(pictures.get(i*8+j));
									
									imgpic3.setOnClickListener(new OnClickListener() {
										@Override
										public void onClick(View v) {
											deletephoto((PhotoImageView) v);
										}
									});
									
								}else if(i*8+j == pictures.size()){
									imgpic3.setImageResource(R.drawable.d11_03);
									imgpic3.setOnClickListener(new OnClickListener() {
										@Override
										public void onClick(View v) {
											StringUtil.doGoToImg(EditPicturesLayout.this.getContext());
										}
									});
								}else{
									imgpic3.setImageDrawable(new Background1());
									imgpic3.setOnClickListener(null);
									imgpic3.setVisibility(INVISIBLE);
									
								}}
								break;
							case 3:
								if(j*i < pictures.size()){if(i*8+j < pictures.size()){
									imgpic4.setindex(i*8+j);
									imgpic4.id = list.get(i*8+j).getId();
									imgpic4.setImageDrawable(pictures.get(i*8+j));
									
									imgpic4.setOnClickListener(new OnClickListener() {
										@Override
										public void onClick(View v) {
											deletephoto((PhotoImageView) v);
										}
									});
									
								}else if(i*8+j == pictures.size()){
									imgpic4.setImageResource(R.drawable.d11_03);
									imgpic4.setOnClickListener(new OnClickListener() {
										@Override
										public void onClick(View v) {
											StringUtil.doGoToImg(EditPicturesLayout.this.getContext());
										}
									});
									
								}else{
									imgpic4.setImageDrawable(new Background1());
									imgpic4.setOnClickListener(null);
									imgpic4.setVisibility(INVISIBLE);
								}}
								break;
							case 4:
								if(j*i < pictures.size()){if(i*8+j < pictures.size()){
									imgpic5.setindex(i*8+j);
									imgpic5.id = list.get(i*8+j).getId();
									imgpic5.setImageDrawable(pictures.get(i*8+j));
									
									imgpic5.setOnClickListener(new OnClickListener() {
										@Override
										public void onClick(View v) {
											deletephoto((PhotoImageView) v);
										}
									});
									
								}else if(i*8+j == pictures.size()){
									imgpic5.setImageResource(R.drawable.d11_03);
									imgpic5.setOnClickListener(new OnClickListener() {
										@Override
										public void onClick(View v) {
											StringUtil.doGoToImg(EditPicturesLayout.this.getContext());
										}
									});
								}else{
									imgpic5.setImageDrawable(new Background1());
									imgpic5.setOnClickListener(null);
									imgpic5.setVisibility(INVISIBLE);
								}}
								break;
							case 5:
								if(j*i < pictures.size()){if(i*8+j < pictures.size()){
									imgpic6.setindex(i*8+j);
									imgpic6.id = list.get(i*8+j).getId();
									imgpic6.setImageDrawable(pictures.get(i*8+j));
									
									imgpic6.setOnClickListener(new OnClickListener() {
										@Override
										public void onClick(View v) {
											deletephoto((PhotoImageView) v);
										}
									});
									
									
								}else if(i*8+j == pictures.size()){
									imgpic6.setImageResource(R.drawable.d11_03);
									imgpic6.setOnClickListener(new OnClickListener() {
										@Override
										public void onClick(View v) {
											StringUtil.doGoToImg(EditPicturesLayout.this.getContext());
										}
									});
								}else{
									imgpic6.setImageDrawable(new Background1());
									imgpic6.setOnClickListener(null);
									imgpic6.setVisibility(INVISIBLE);
								}}
								break;
							case 6:
								if(j*i < pictures.size()){if(i*8+j < pictures.size()){
									imgpic7.setindex(i*8+j);
									imgpic7.id = list.get(i*8+j).getId();
									imgpic7.setImageDrawable(pictures.get(i*8+j));
									imgpic7.setOnClickListener(new OnClickListener() {
										@Override
										public void onClick(View v) {
											deletephoto((PhotoImageView) v);
										}
									});
									
									
								}else if(i*8+j == pictures.size()){
									imgpic7.setImageResource(R.drawable.d11_03);
									imgpic7.setOnClickListener(new OnClickListener() {
										@Override
										public void onClick(View v) {
											StringUtil.doGoToImg(EditPicturesLayout.this.getContext());
										}
									});
								}else{
									imgpic7.setImageDrawable(new Background1());
									imgpic7.setOnClickListener(null);
									imgpic7.setVisibility(INVISIBLE);
								}}
								break;
							case 7:
								if(j*i < pictures.size()){if(i*8+j < pictures.size()){
									imgpic8.setindex(i*8+j);
									imgpic8.id = list.get(i*8+j).getId();
									imgpic8.setImageDrawable(pictures.get(i*8+j));
									imgpic8.setOnClickListener(new OnClickListener() {
										@Override
										public void onClick(View v) {
											deletephoto((PhotoImageView) v);
										}
									});
								}else if(i*8+j == pictures.size()){
									imgpic8.setImageResource(R.drawable.d11_03);
									imgpic8.setOnClickListener(new OnClickListener() {
										@Override
										public void onClick(View v) {
											StringUtil.doGoToImg(EditPicturesLayout.this.getContext());
										}
									});
								}else{
									imgpic8.setImageDrawable(new Background1());
									imgpic8.setOnClickListener(null);
									imgpic8.setVisibility(INVISIBLE);
								}}
								break;
							default:
								break;
							}
							if(count == 0 ){
//								imgpic1.setVisibility(GONE);
								imgpic2.setVisibility(INVISIBLE);
								imgpic3.setVisibility(INVISIBLE);
								imgpic4.setVisibility(INVISIBLE);
							}
							if(count < 4){
								imgpic5.setVisibility(GONE);
								imgpic6.setVisibility(GONE);
								imgpic7.setVisibility(GONE);
								imgpic8.setVisibility(GONE);
							}
							if(count == 4){
								imgpic6.setVisibility(INVISIBLE);
								imgpic7.setVisibility(INVISIBLE);
								imgpic8.setVisibility(INVISIBLE);
							}
						}
						vfpics.addView(child);
						ImageView tab = new ImageView(ltabs.getContext());
						if (i == 0) {
							tab.setImageResource(R.drawable.f10_26);
						} else {
							tab.setImageResource(R.drawable.f10_24);
						}
						LinearLayout.LayoutParams pitem = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
						ltabs.addView(tab,pitem);
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
		Log.i("huadong", "fly");
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
	public boolean onTouchEvent(MotionEvent ev) {
		super.onTouchEvent(ev);
		return mGestureDetector.onTouchEvent(ev);
	}
	public void deletephoto(final PhotoImageView imgv){
		LayoutInflater inflater = LayoutInflater.from(imgv.getContext());
		View popview = inflater.inflate(R.layout.bianji_delete, null);
		final PopupWindow pop = new PopupWindow(popview, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, false);
		pop.setBackgroundDrawable(new Background3());
		pop.setOutsideTouchable(true);
		pop.setFocusable(true);
		Button btok = (Button) popview.findViewById(R.id.btok);
		btok.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				list.remove(imgv.index);
				pictures.remove(imgv.index);
				new Thread(){
					public void run() {
						controller.getContext().addBusinessData("photo.id", imgv.id);
						controller.myphotodel();
					};
				}.start();
				((PhotoImageView) imgv).setImageResource(R.drawable.d11_03);
				pop.dismiss();
				handler.sendEmptyMessage(1);
			}
		});
		int[] location = new int[2];  
		imgv.getLocationOnScreen(location);
		pop.showAtLocation(imgv, Gravity.NO_GRAVITY, location[0]-pop.getWidth()-19, location[1]-pop.getHeight()-19);
	}
	public void onDestroy(){
		if(pictures != null){
			for (int i = 0; i < pictures.size(); i++) {
				if(pictures.get(i) instanceof BitmapDrawable){
					Bitmap bmp = ((BitmapDrawable) pictures.get(i)).getBitmap();
					if (null != bmp && !bmp.isRecycled()){
						bmp.recycle();
						bmp = null;
						}
				}
			}
			
		}
	}
}