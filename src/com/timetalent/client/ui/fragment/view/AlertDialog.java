package com.timetalent.client.ui.fragment.view;

import java.util.ArrayList;
import java.util.Date;

import com.timetalent.client.R;
import com.timetalent.client.ui.fragment.util.Background1;
import com.timetalent.client.ui.fragment.util.DipPxUtil;



import android.R.color;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.Window;
import android.view.GestureDetector.OnGestureListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 
 * @author Administrator
 * 
 */
public class AlertDialog extends Dialog {
	LinearLayout rootlayout;
	LinearLayout titlelayout;
	LinearLayout bodylayout;
	LinearLayout endlayout;
	
	TextView tvtitle = null;
	TextView tvmiaoshu = null;
	Button btcanel = null;
	Button btok = null;
	Context mycontext;
	String miaoshu = "";
	int screenw = 0;
	int screenh = 0;
	double density = 0;

	public AlertDialog(Context context,String log) {
		super(context);
		mycontext = context;
		miaoshu = log;
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		DisplayMetrics dm = new DisplayMetrics();
		((Activity) mycontext).getWindowManager().getDefaultDisplay()
				.getMetrics(dm);

		density = dm.density;
		screenw = dm.widthPixels;
		screenh = dm.heightPixels;
		Window window = AlertDialog.this.getWindow();
		Drawable b = new Background1();
		b.setAlpha(0);
		window.setBackgroundDrawable(b);
		WindowManager.LayoutParams lp = window.getAttributes();
		lp.alpha = 0.97f;
		lp.dimAmount = 0.7f;
		lp.height = LayoutParams.MATCH_PARENT;
		lp.width = LayoutParams.MATCH_PARENT;
		window.setWindowAnimations(android.R.style.Animation_Translucent);
		getWindow().setAttributes(
				(android.view.WindowManager.LayoutParams) lp);
		init();

	}

	public void init() {

		rootlayout = new LinearLayout(mycontext);
		rootlayout.setOrientation(LinearLayout.VERTICAL);
		rootlayout.setBackgroundDrawable(new Background1());
		rootlayout.setGravity(Gravity.CENTER_VERTICAL);

		titlelayout = new LinearLayout(mycontext);
		bodylayout = new LinearLayout(mycontext);
		endlayout = new LinearLayout(mycontext);

		titlelayout.setOrientation(LinearLayout.HORIZONTAL);
		bodylayout.setOrientation(LinearLayout.VERTICAL);
		endlayout.setOrientation(LinearLayout.HORIZONTAL);

		titlelayout.setGravity(Gravity.CENTER_HORIZONTAL);
		endlayout.setGravity(Gravity.CENTER_HORIZONTAL + Gravity.LEFT
				+ Gravity.RIGHT);

		titlelayout
				.setPadding(DipPxUtil.dip2px(mycontext, 15),
						DipPxUtil.dip2px(mycontext, 15),
						DipPxUtil.dip2px(mycontext, 15),
						DipPxUtil.dip2px(mycontext, 5));
		bodylayout
				.setPadding(DipPxUtil.dip2px(mycontext, 15), 0,
						DipPxUtil.dip2px(mycontext, 15),
						DipPxUtil.dip2px(mycontext, 5));
		endlayout.setPadding(DipPxUtil.dip2px(mycontext, 15), 0,
				DipPxUtil.dip2px(mycontext, 15),
				DipPxUtil.dip2px(mycontext, 25));

		titlelayout.setBackgroundDrawable(new Background1());
		bodylayout.setBackgroundDrawable(new Background1());
		endlayout.setBackgroundDrawable(new Background1());
		// ͷ
		tvtitle = new TextView(mycontext);
		tvtitle.setText("提示");
		tvtitle.setTextSize(TypedValue.COMPLEX_UNIT_PX,
				DipPxUtil.dip2px(mycontext, 18));
		tvtitle.setTextColor(color.black);
		tvtitle.setBackgroundDrawable(new Background1());
		tvtitle.setGravity(Gravity.LEFT);
		LayoutParams titleparams = new LayoutParams(screenw / 6,
				LayoutParams.WRAP_CONTENT);
		titlelayout.addView(tvtitle, titleparams);

		tvmiaoshu = new TextView(mycontext);
		tvmiaoshu.setText(miaoshu);
		tvmiaoshu.setTextSize(TypedValue.COMPLEX_UNIT_PX,
				DipPxUtil.dip2px(mycontext, 18));
		tvmiaoshu.setTextColor(color.black);
		tvmiaoshu.setBackgroundDrawable(new Background1());
		tvmiaoshu.setGravity(Gravity.CENTER);
		bodylayout.addView(tvmiaoshu,LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
		
		btcanel = new Button(mycontext);
		btcanel.setBackgroundDrawable(new Background1());
		btcanel.setText("取消");
		btcanel.setTextColor(0XFF969696);
		btcanel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
			}
		});
		btok = new Button(mycontext);
		btok.setBackgroundDrawable(new Background1());
		btok.setText("确认");
		btok.setTextColor(0XFF960000);
		btok.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
			}
		});
		endlayout.addView(btcanel, new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1));
		endlayout.addView(btok,new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1));
		rootlayout.addView(titlelayout, new LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		rootlayout.addView(bodylayout, new LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		rootlayout.addView(endlayout, new LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		setContentView(rootlayout, new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
	}
}
