package com.timetalent.client.ui.user;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.timetalent.client.R;
import com.timetalent.client.entities.Baseinfopackage;
import com.timetalent.client.entities.LoginData;
import com.timetalent.client.entities.PicValuePair;
import com.timetalent.client.entities.Picture;
import com.timetalent.client.entities.Userinfopackage;
import com.timetalent.client.service.AppController;
import com.timetalent.client.ui.BaseActivity;
import com.timetalent.client.ui.GuideActivity;
import com.timetalent.client.ui.MainFragmentActivity;
import com.timetalent.client.ui.adapter.ZuopinBaseAdapter;
import com.timetalent.client.ui.adapter.zhiyexuanzeAdapter;
import com.timetalent.client.ui.addresscheck.City_cnActivity;
import com.timetalent.client.ui.dynamic.DynamicAddActivity;
import com.timetalent.client.ui.dynamic.DynamicMyActivity;
import com.timetalent.client.ui.fragment.util.Background2;
import com.timetalent.client.ui.fragment.util.Background3;
import com.timetalent.client.ui.near.NearDongtaiActivity;
import com.timetalent.client.ui.near.PictureActivity;
import com.timetalent.client.ui.near.XingtanActivity;
import com.timetalent.client.ui.view.EditPicturesLayout;
import com.timetalent.client.ui.view.PicturesLayout;
import com.timetalent.common.util.Config;
import com.timetalent.common.util.IntentUtil;
import com.timetalent.common.util.LogUtil;
import com.timetalent.common.util.PictureUtil;
import com.timetalent.common.util.StringUtil;
import com.timetalent.common.util.ToastUtil;


/******************************************
 * 类描述： fans编辑页面
 * 类名称：FansziliaobianjiActivity  
 * @version: 1.0
 * @author: why
 * @time: 2014-10-10 下午6:32:12 
 ******************************************/
public class FansziliaobianjiActivity extends BaseActivity implements OnClickListener{
	private AppController controller;
	EditPicturesLayout piclay;	
	EditText etname ;
	EditText etnickname ;
	TextView tvjiaxiang;
	TextView tvage;
	TextView tvage1;
	ImageView imgsex;
	TextView tvxingzuo;
	TextView tvxingzuo1;
	
	Spinner spmajor;
	EditText etheight;
	
	int index = 0;
	private LinearLayout ldongtai;
	private TextView main_top_right;
	private ImageButton main_top_left;
	private LinearLayout lage;
	private Button btok;
	public int screenw = 400;
	public int screenh = 800;
	public float density = 1.0f;
	ImageView imghead;
	LoginData user;
	Userinfopackage u;
	int updatetstte = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_fansziliaobianji);
		controller = AppController.getController(this);
		user = (LoginData) controller.getContext().getBusinessData("loginData");
		DisplayMetrics dm = new DisplayMetrics();
		this.getWindowManager().getDefaultDisplay().getMetrics(dm);
		screenw = dm.widthPixels;
		density = dm.density;
		screenw = dm.widthPixels;
		screenh = dm.heightPixels;
		findView();
		new Thread(){
			public void run() {
				controller.getContext().addBusinessData("near.user_id", user.getId());
				controller.userinfo();
				handler.sendEmptyMessage(1);
			};
		}.start();
	}
	
	/* (non-Javadoc)
	 * @see com.timetalent.client.ui.BaseActivity#onDestroy()
	 */
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		piclay.onDestroy();
	}
	/**
	 * 方法描述：TODO
	 * 
	 * @author: why
	 * @time: 2014-10-10 下午6:36:00
	 */
	private void findView() {
		piclay = (EditPicturesLayout) findViewById(R.id.piclay);
		ldongtai = (LinearLayout) findViewById(R.id.ldongtai);
		main_top_right = (TextView)this.findViewById(R.id.main_top_right);
		main_top_left = (ImageButton)this.findViewById(R.id.main_top_left);
		lage = (LinearLayout) findViewById(R.id.lage);
		btok = (Button) findViewById(R.id.btok);
		tvjiaxiang = (TextView) findViewById(R.id.tvjiaxiang);
		imghead = (ImageView)this.findViewById(R.id.imghead);
		tvage1 = (TextView)this.findViewById(R.id.tvage1);
		tvage = (TextView) findViewById(R.id.tvage);
		tvxingzuo1 = (TextView) findViewById(R.id.tvxingzuo1);
		tvxingzuo = (TextView) findViewById(R.id.tvxingzuo);
		imgsex = (ImageView)this.findViewById(R.id.imgsex);
		etname = (EditText) this.findViewById(R.id.etname);
		etnickname = (EditText) this.findViewById(R.id.etnickname);
		
		spmajor = (Spinner) this.findViewById(R.id.spzhiye);
		etheight = (EditText) this.findViewById(R.id.etheight);
	}

	/**
	 * 方法描述：TODO
	 * 
	 * @author: why
	 * @time: 2014-10-10 下午6:36:02
	 */
	private void initView() {
		((TextView)this.findViewById(R.id.main_top_title)).setText(""+u.getNickname());
//		((TextView)this.findViewById(R.id.main_top_right)).setText("编辑");
//		UIUtils.setDrawableLeft(this,main_top_right,R.drawable.d3_06);
		this.findViewById(R.id.main_top_left).setVisibility(View.VISIBLE);
//		UIUtils.setDrawableLeft(this,main_top_left2,R.drawable.d3_03);
		piclay.setcontroller(controller);
		piclay.initView();
		ldongtai.setOnClickListener(this);
		lage.setOnClickListener(this);
		btok.setOnClickListener(this);
		
		tvjiaxiang.setOnClickListener(this);
		tvage1.setText(controller.getContext().getStringData("edit.age"));
		tvage.setText(controller.getContext().getStringData("edit.age"));
		tvxingzuo1.setText(controller.getContext().getStringData("edit.xingzuo"));
		tvxingzuo.setText(controller.getContext().getStringData("edit.xingzuo"));
		if(u.getSex().equals("1")){
			imgsex.setImageResource(R.drawable.f_05);
		}else{
			imgsex.setImageResource(R.drawable.f_03);
		}
		spmajor.setAdapter(new zhiyexuanzeAdapter(FansziliaobianjiActivity.this, controller, "fans"));
		
		
		etnickname.setText(u.getNickname());
		tvage1.setText(u.getAge());
		tvage.setText(u.getAge());
		tvxingzuo.setText(u.getConstella());
		tvxingzuo1.setText(u.getConstella());
		tvjiaxiang.setText(u.getProvince()+u.getCity());
		etheight.setText(u.getMore().getHeight());
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		tvage1.setText(controller.getContext().getStringData("edit.age"));
		tvage.setText(controller.getContext().getStringData("edit.age"));
		tvxingzuo.setText(controller.getContext().getStringData("edit.xingzuo"));
		tvxingzuo1.setText(controller.getContext().getStringData("edit.xingzuo"));
		tvjiaxiang.setText(controller.getContext().getStringData("edit.jiaxiang"));
	}
	@Override
	protected void onStop() {
		super.onStop();
		tvage.setText(controller.getContext().getStringData("edit.age"));
		tvxingzuo1.setText(controller.getContext().getStringData("edit.xingzuo"));
	}
	/**
	 * 重新计算listview高度
	  * 方法描述：TODO
	  * @param listView
	  * @author: Administrator
	  * @time: 2014-10-13 下午4:19:11
	 */
	public void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();   
        if (listAdapter == null) {  
            // pre-condition  
            return;  
        }
  
        int totalHeight = 0;  
        for (int i = 0; i < listAdapter.getCount(); i++) {  
            View listItem = listAdapter.getView(i, null, listView);  
            listItem.measure(0, 0);  
            totalHeight += listItem.getMeasuredHeight();  
        }  
  
        ViewGroup.LayoutParams params = listView.getLayoutParams();  
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));  
        listView.setLayoutParams(params);  
    }
	public void setvalue(){
//		controller.getContext().addBusinessData("bianji.username", etnickname.getText().toString());
		controller.getContext().addBusinessData("bianji.phone", u.getPhone());
		controller.getContext().addBusinessData("bianji.email", "");
		controller.getContext().addBusinessData("bianji.sex", u.getSex());
		controller.getContext().addBusinessData("bianji.nickname", etnickname.getText().toString());
		controller.getContext().addBusinessData("bianji.realname", etname.getText().toString());
		controller.getContext().addBusinessData("bianji.birthday", tvage1.getText().toString());
		controller.getContext().addBusinessData("bianji.constella", tvxingzuo1.getText().toString());
		controller.getContext().addBusinessData("bianji.certificate", "");
		
		
		controller.getContext().addBusinessData("bianji.bust", "");
		controller.getContext().addBusinessData("bianji.waist", "");
		controller.getContext().addBusinessData("bianji.hip", "");
		controller.getContext().addBusinessData("bianji.height", ""+etheight.getText().toString());
		controller.getContext().addBusinessData("bianji.weight", "");
//		controller.getContext().addBusinessData("bianji.major", ""+tvmajor.getText().toString());
		
		
		String[] jiaxiang = tvjiaxiang.getText().toString().split(" ");
		if(jiaxiang.length >= 2){
			controller.getContext().addBusinessData("bianji.province", jiaxiang[0]);
			controller.getContext().addBusinessData("bianji.city", jiaxiang[1]);
		}else{
			controller.getContext().addBusinessData("bianji.province", "");
			controller.getContext().addBusinessData("bianji.city", "");
		}
	}
	@Override
	public void onClick(final View vclick) {
		switch (vclick.getId()) {
		case R.id.tvjiaxiang:
			IntentUtil.intent(FansziliaobianjiActivity.this, City_cnActivity.class);
			break;
		case R.id.bt_login_next:
			break;
		case R.id.ldongtai:
			updatetstte = 2;
			StringUtil.doGoToImg(FansziliaobianjiActivity.this);
			break;
		case R.id.main_top_left:
			finish();
			break;
		case R.id.lage:
			IntentUtil.intent(FansziliaobianjiActivity.this, MyageActivity.class);
			break;
		case R.id.lsanwei:
			IntentUtil.intent(FansziliaobianjiActivity.this, MysanweiActivity.class);
			break;
		case R.id.btok:
			setvalue();
			new Thread(){
				public void run() {
					controller.mybaseinfoupdate();
					controller.mymoreinfoupdate();
				};
			}.start();
			
			
			finish();
			break;
		case R.id.img1:
		case R.id.img2:
		case R.id.img3:
		case R.id.img4:
		case R.id.img5:
		case R.id.img6:
		case R.id.img7:
			LayoutInflater inflater = LayoutInflater.from(FansziliaobianjiActivity.this);
			View popview = inflater.inflate(R.layout.bianji_delete, null);
			final PopupWindow pop = new PopupWindow(popview, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, false);
			pop.setBackgroundDrawable(new Background3());
			pop.setOutsideTouchable(true);
			pop.setFocusable(true);
			Button btok = (Button) popview.findViewById(R.id.btok);
			btok.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					((ImageView) vclick).setImageResource(R.drawable.d11_03);
					pop.dismiss();
				}
			});
			int[] location = new int[2];  
			vclick.getLocationOnScreen(location);
			pop.showAtLocation(vclick, Gravity.NO_GRAVITY, location[0]-pop.getWidth()-19, location[1]-pop.getHeight()-19);
			break;
		case R.id.img8:
			StringUtil.doGoToImg(FansziliaobianjiActivity.this);
			break;
		default:
			break;
		}
	}
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// super.handleMessage(msg);
			switch (msg.what) {
			case 1:
				 u = (Userinfopackage) controller.getContext().getBusinessData("UserinfoData");
				if(u != null){
					initView();
					new Thread(){
						public void run() {
							Message msg = new Message();
							msg.what = 2;
							msg.obj = PictureUtil.getImage(u.getAvatar(), u.getId(), "head");
							handler.sendMessage(msg);
						};
					}.start();
				}
				break;
			case 2:
				if(msg.obj instanceof Drawable){
					imghead.setImageDrawable((Drawable)(msg.obj));
				}else{
					BitmapDrawable img = (BitmapDrawable) msg.obj;
					Bitmap bm = PictureUtil.toRoundCorner(img.getBitmap(),10);
					imghead.setImageBitmap(bm);
				}
				LayoutParams pa = (LayoutParams)imghead.getLayoutParams();
				pa.width = (int) (50*density);
				pa.height = (int) (50*density);
//				imghead.setPadding(0, (int) (20*density), 0, (int) (20*density));
				break;
			case 3:
				
				break;
			}
		}
	};
	protected void onActivityResult(int requestCode, int resultCode, android.content.Intent data) {

		if (resultCode == RESULT_OK) {
			if (data != null) {
				Uri uri = data.getData();
				if (!TextUtils.isEmpty(uri.getAuthority())) {
					Cursor cursor = getContentResolver().query(uri,
							new String[] { MediaStore.Images.Media._ID,MediaStore.Images.Media.DATA },
							null, null, null);
					if (null == cursor) {
						ToastUtil.showToast(FansziliaobianjiActivity.this, "图片没找到", 0);
						return;
					}
					cursor.moveToFirst();
					String id = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media._ID));
					final String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
					cursor.close();
					LogUtil.Log("path=" + path);
					if (PictureUtil.isPicture(path)) {
						new Thread(){
							  public void run() {
								  Bitmap bm = getSmallBitmap(path);
								  ByteArrayOutputStream baos = new ByteArrayOutputStream();
							        bm.compress(Bitmap.CompressFormat.JPEG, 30, baos);
							        byte[] b = baos.toByteArray();
							        
								  File file = getFileFromBytes(b, Config.IMAGEPATH+"temp.jpg");
								  List<PicValuePair> picValuePair = new ArrayList<PicValuePair>();
								  if(updatetstte == 2){
									  picValuePair.add(new PicValuePair("avatar", file));
									  controller.myavatarupdate(picValuePair);
									  updatetstte = 0;
								  }else{
									  picValuePair.add(new PicValuePair("photo1", file));
									  controller.myphotoupdate(picValuePair);
								  }
									
							  };
						  }.start();
					} else {
						Toast.makeText(
								this,
								"请选择png或者jpg格式的图片",
								Toast.LENGTH_SHORT).show();
					}
				}
				}
			}
	};
	//计算图片的缩放值
	public  int calculateInSampleSize(BitmapFactory.Options options,int reqWidth, int reqHeight) {
	    final int height = options.outHeight;
	    final int width = options.outWidth;
	    int inSampleSize = 1;

	    if (height > reqHeight || width > reqWidth) {
	             final int heightRatio = Math.round((float) height/ (float) reqHeight);
	             final int widthRatio = Math.round((float) width / (float) reqWidth);
	             inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
	    }
	        return inSampleSize;
	}
	// 根据路径获得图片并压缩，返回bitmap用于显示
	public  Bitmap getSmallBitmap(String filePath) {
	        final BitmapFactory.Options options = new BitmapFactory.Options();
	        options.inJustDecodeBounds = true;
	        BitmapFactory.decodeFile(filePath, options);

	        // Calculate inSampleSize
	    options.inSampleSize = calculateInSampleSize(options, screenw, screenh);

	        // Decode bitmap with inSampleSize set
	    options.inJustDecodeBounds = false;

	    return BitmapFactory.decodeFile(filePath, options);
	    }
	 public  File getFileFromBytes(byte[] b, String outputFile){
	        BufferedOutputStream stream = null;
	        File file = null;
	        try {
	            file = new File(outputFile);
	            FileOutputStream fstream = new FileOutputStream(file);
	            stream = new BufferedOutputStream(fstream);
	            stream.write(b);
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            if (stream != null) {
	                try {
	                    stream.close();
	                } catch (IOException e1) {
	                    e1.printStackTrace();
	                }
	            }
	        }
	        return file;
	    }
}
