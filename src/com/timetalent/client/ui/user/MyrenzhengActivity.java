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
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
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
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.timetalent.client.R;
import com.timetalent.client.entities.Baseinfopackage;
import com.timetalent.client.entities.LoginData;
import com.timetalent.client.entities.PicValuePair;
import com.timetalent.client.service.AppController;
import com.timetalent.client.ui.BaseActivity;
import com.timetalent.client.ui.GuideActivity;
import com.timetalent.client.ui.MainFragmentActivity;
import com.timetalent.client.ui.adapter.ZuopinBaseAdapter;
import com.timetalent.client.ui.near.NearDongtaiActivity;
import com.timetalent.client.ui.near.PictureActivity;
import com.timetalent.client.ui.view.EditPicturesLayout;
import com.timetalent.client.ui.view.RenzhengEditPicturesLayout;
import com.timetalent.common.util.Config;
import com.timetalent.common.util.IDCard;
import com.timetalent.common.util.IntentUtil;
import com.timetalent.common.util.LogUtil;
import com.timetalent.common.util.PictureUtil;
import com.timetalent.common.util.StringUtil;
import com.timetalent.common.util.ToastUtil;


/******************************************
 * 类描述： 我的认证
 * 类名称：MyrenzhengActivity  
 * @version: 1.0
 * @author: why
 * @time: 2014-10-10 下午6:32:12 
 ******************************************/
public class MyrenzhengActivity extends BaseActivity implements OnClickListener {
	private AppController controller;
	private TextView main_top_right;
	private ImageButton main_top_left;
	ImageView img1,img2,img3;
	LinearLayout l1,l2,l3,l5,l6,l7,l8;
	private Button btnext;
	ImageView imghead;
	TextView tvname;
	EditText etrealname;
	EditText etshenfenzheng;
	EditText etitem;
	TextView tvaddmore;
	TextView tvresult;
	RenzhengEditPicturesLayout piclay;	
	int index = 0;
	LoginData user;
	public int screenw = 0;
	public int screenh = 0;
	public float density = 1.0f;
	List<String> items;
	List<List<PicValuePair>> picValuePairs;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_renzheng);
		controller = AppController.getController(this);
		user = (LoginData) controller.getContext().getBusinessData("loginData");
		DisplayMetrics dm = new DisplayMetrics();
		this.getWindowManager().getDefaultDisplay().getMetrics(dm);
		screenw = dm.widthPixels;
		screenh = dm.heightPixels;
		density = dm.density;
		items = new ArrayList<String>();
		picValuePairs = new ArrayList<List<PicValuePair>>();
		picValuePairs.add(new ArrayList<PicValuePair>());
		picValuePairs.add(new ArrayList<PicValuePair>());
		picValuePairs.add(new ArrayList<PicValuePair>());
		picValuePairs.add(new ArrayList<PicValuePair>());
		picValuePairs.add(new ArrayList<PicValuePair>());
		items.add("");
		items.add("");
		items.add("");
		items.add("");
		items.add("");
		findView();
		initView();
	}
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
		main_top_left = (ImageButton)this.findViewById(R.id.main_top_left);
		btnext = (Button) findViewById(R.id.btnext);
		img1 = (ImageView) findViewById(R.id.img1);
		img2 = (ImageView) findViewById(R.id.img2);
		img3 = (ImageView) findViewById(R.id.img3);
		l1 =(LinearLayout) findViewById(R.id.l1);
		l2 =(LinearLayout) findViewById(R.id.l2);
		l3 =(LinearLayout) findViewById(R.id.l3);
		l5 =(LinearLayout) findViewById(R.id.l5);
		l6 =(LinearLayout) findViewById(R.id.l6);
		l7 =(LinearLayout) findViewById(R.id.l7);
		l8 =(LinearLayout) findViewById(R.id.l8);
		imghead = (ImageView) findViewById(R.id.imghead);
		tvname = (TextView) findViewById(R.id.tvname);
		etrealname = (EditText) findViewById(R.id.etrealname);
		tvresult = (TextView) findViewById(R.id.tvresult);
		etshenfenzheng = (EditText) findViewById(R.id.etshenfenzheng);
		etitem =  (EditText) l2.findViewById(R.id.etitem);
		etitem.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				items.set(0, s.toString());
				
			}
		});
		piclay = (RenzhengEditPicturesLayout) l2.findViewById(R.id.piclay);
		tvaddmore = (TextView) findViewById(R.id.tvaddmore);
	}

	/**
	 * 方法描述：TODO
	 * 
	 * @author: why
	 * @time: 2014-10-10 下午6:36:02
	 */
	private void initView() {
		
	//	etshenfenzheng.setText("413001198801136517");

		((TextView)this.findViewById(R.id.main_top_title)).setText("认证");
//		UIUtils.setDrawableLeft(this,main_top_right,R.drawable.d3_06);
		main_top_left.setVisibility(View.VISIBLE);
//		UIUtils.setDrawableLeft(this,main_top_left2,R.drawable.d3_03);
		main_top_left.setOnClickListener(this);
//		new Thread(){
//			public void run() {
		new Thread(){
			public void run() {
				Message msg = new Message();
				msg.what = 1;
				msg.obj = PictureUtil.getImage(user.getAvatar(), user.getId(), "head");
				handler.sendMessage(msg);
			};
		}.start();
		piclay.setcontroller(controller);
		piclay.initView();
//				bmp = ImageLoader.getInstance().loadImageSync(user.getAvatar());
//				handler.sendEmptyMessage(1);
//			};
//		}.start();
		
//		imghead.setImageBitmap(bmp);
		tvname.setText(user.getNickname());
		btnext.setOnClickListener(this);
		img1.setImageResource(R.drawable.m22_10);
		img2.setImageResource(R.drawable.m22_03);
		img3.setImageResource(R.drawable.m22_09);
		l1.setVisibility(View.VISIBLE);
		l2.setVisibility(View.GONE);
		l3.setVisibility(View.GONE);
		tvaddmore.setOnClickListener(this);
		tvresult.setText("恭喜"+user.getNickname());
	}
	public void setvalue(){
		controller.getContext().addBusinessData("loyal.realname", etrealname.getText().toString());
		controller.getContext().addBusinessData("loyal.certificate", etshenfenzheng.getText().toString());
		
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.main_top_left:
			finish();
			break;
		case R.id.tvaddmore:
			if(l5.getVisibility() == View.GONE){
				String temp = StringUtil.notnull(etitem.getText().toString());
				if(!temp.isEmpty()){
					ToastUtil.showToast(this, temp, ToastUtil.LENGTH_LONG);
					return;
				}
				l5.setVisibility(View.VISIBLE);
				etitem = (EditText) l5.findViewById(R.id.etitem);
				etitem.addTextChangedListener(new TextWatcher() {
					
					@Override
					public void onTextChanged(CharSequence s, int start, int before, int count) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void beforeTextChanged(CharSequence s, int start, int count,
							int after) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void afterTextChanged(Editable s) {
						items.set(1, s.toString());
						
					}
				});
				piclay = (RenzhengEditPicturesLayout) l5.findViewById(R.id.piclay);
				piclay.setcontroller(controller);
				piclay.initView();
				break;
			}else if(l6.getVisibility() == View.GONE){
				String temp = StringUtil.notnull(etitem.getText().toString());
				if(!temp.isEmpty()){
					ToastUtil.showToast(this, temp, ToastUtil.LENGTH_LONG);
					return;
				}
				l6.setVisibility(View.VISIBLE);
				etitem = (EditText) l6.findViewById(R.id.etitem);
				etitem.addTextChangedListener(new TextWatcher() {
					
					@Override
					public void onTextChanged(CharSequence s, int start, int before, int count) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void beforeTextChanged(CharSequence s, int start, int count,
							int after) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void afterTextChanged(Editable s) {
						items.set(2, s.toString());
						
					}
				});
				piclay = (RenzhengEditPicturesLayout) l6.findViewById(R.id.piclay);
				piclay.setcontroller(controller);
				piclay.initView();
				break;
			}else if(l7.getVisibility() == View.GONE){
				String temp = StringUtil.notnull(etitem.getText().toString());
				if(!temp.isEmpty()){
					ToastUtil.showToast(this, temp, ToastUtil.LENGTH_LONG);
					return;
				}
				l7.setVisibility(View.VISIBLE);
				etitem = (EditText) l7.findViewById(R.id.etitem);
				etitem.addTextChangedListener(new TextWatcher() {
					
					@Override
					public void onTextChanged(CharSequence s, int start, int before, int count) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void beforeTextChanged(CharSequence s, int start, int count,
							int after) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void afterTextChanged(Editable s) {
						items.set(3, s.toString());
						
					}
				});
				piclay = (RenzhengEditPicturesLayout) l7.findViewById(R.id.piclay);
				piclay.setcontroller(controller);
				piclay.initView();
				break;
			}else if(l8.getVisibility() == View.GONE){
				String temp = StringUtil.notnull(etitem.getText().toString());
				if(!temp.isEmpty()){
					ToastUtil.showToast(this, temp, ToastUtil.LENGTH_LONG);
					return;
				}
				l8.setVisibility(View.VISIBLE);
				etitem = (EditText) l8.findViewById(R.id.etitem);
				etitem.addTextChangedListener(new TextWatcher() {
					
					@Override
					public void onTextChanged(CharSequence s, int start, int before, int count) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void beforeTextChanged(CharSequence s, int start, int count,
							int after) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void afterTextChanged(Editable s) {
						items.set(4, s.toString());
						
					}
				});
				piclay = (RenzhengEditPicturesLayout) l8.findViewById(R.id.piclay);
				piclay.setcontroller(controller);
				piclay.initView();
				tvaddmore.setVisibility(View.GONE);
				break;
			}
			break;
		case R.id.btnext:
			if(l8.getVisibility() == View.VISIBLE){
				String temp = StringUtil.notnull(etitem.getText().toString());
				if(!temp.isEmpty()){
					ToastUtil.showToast(this, temp, ToastUtil.LENGTH_LONG);
					return;
				}
			}
			String realname = etrealname.getText().toString().trim();
			String shenfenzheng = etshenfenzheng.getText().toString().trim();
			String Validate1 = StringUtil.notnull(realname);
			String Validate2 = IDCard.IDCardValidate(shenfenzheng);
			if(!TextUtils.isEmpty(Validate1)){
				ToastUtil.showToast(this, Validate1, ToastUtil.LENGTH_LONG);
				return;
			}
			if(!TextUtils.isEmpty(Validate2)){
				ToastUtil.showToast(this, Validate2, ToastUtil.LENGTH_LONG);
				return;
			}
			if(index == 0){
				setvalue();
				new Thread(){
					public void run() {
						controller.myuser_loyal();
					};
				}.start();
			}
			
			
			if(index == 2){
				String etitem11 = etitem.getText().toString();
				if(TextUtils.isEmpty(etitem11)){
					ToastUtil.showToast(this, "项目全称不能为空", ToastUtil.LENGTH_LONG);
					return;
				}
			}
			
			index++;
			switch (index) {
			case 1:
				img1.setImageResource(R.drawable.m22_07);
				img2.setImageResource(R.drawable.m22_04);
				img3.setImageResource(R.drawable.m22_09);
				l1.setVisibility(View.GONE);
				l2.setVisibility(View.VISIBLE);
				tvaddmore.setVisibility(View.VISIBLE);
				l3.setVisibility(View.GONE);
				btnext.setText("下一步");
				break;
			case 2:
				
				
				
				new Thread(){
					public void run() {
						for(int i = 0; i < items.size(); i++){
							String it = items.get(i);
							if(!it.equals("")){
								controller.getContext().addBusinessData("loyal.item", it);
								controller.myuser_loyal_item(picValuePairs.get(i));
							}
						}
					};
				}.start();
				img1.setImageResource(R.drawable.m22_07);
				img2.setImageResource(R.drawable.m22_05);
				img3.setImageResource(R.drawable.m22_08);
				l1.setVisibility(View.GONE);
				l2.setVisibility(View.GONE);
				l5.setVisibility(View.GONE);
				l6.setVisibility(View.GONE);
				l7.setVisibility(View.GONE);
				l8.setVisibility(View.GONE);
				tvaddmore.setVisibility(View.GONE);
				l3.setVisibility(View.VISIBLE);
				btnext.setText("完成");
				break;
			case 3:
				finish();
				break;
			default:
				finish();
				break;
			}
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
						ToastUtil.showToast(MyrenzhengActivity.this, "图片没找到", 0);
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
							        int index = 0;
								  if(l2.getVisibility() == View.VISIBLE){
									  index = 0;
								  }
								  if(l5.getVisibility() == View.VISIBLE){
									  index = 1;
								  }
								  if(l6.getVisibility() == View.VISIBLE){
									  index = 2;
								  }
								  if(l7.getVisibility() == View.VISIBLE){
									  index = 3;
								  }
								  if(l8.getVisibility() == View.VISIBLE){
									  index = 4;
								  }
//								  controller.getContext().addBusinessData("loyal.item", etitem.getText().toString());
								  File file = getFileFromBytes(b, Config.IMAGEPATH+(picValuePairs.get(index).size()+1)+"temp.jpg");
								  items.add(etitem.getText().toString());
								  picValuePairs.get(index).add(new PicValuePair("photo"+picValuePairs.get(index).size()+1, file));
									Log.i("gesg11111y", picValuePairs.get(index).size()+"");
//									controller.myuser_loyal_item(picValuePair);
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
