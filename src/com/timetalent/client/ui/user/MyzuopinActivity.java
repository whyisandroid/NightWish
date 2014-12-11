package com.timetalent.client.ui.user;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.timetalent.client.R;
import com.timetalent.client.entities.PicValuePair;
import com.timetalent.client.service.AppController;
import com.timetalent.client.ui.BaseActivity;
import com.timetalent.client.ui.GuideActivity;
import com.timetalent.client.ui.MainFragmentActivity;
import com.timetalent.client.ui.adapter.ZuopinBaseAdapter;
import com.timetalent.client.ui.near.NearDongtaiActivity;
import com.timetalent.client.ui.near.PictureActivity;
import com.timetalent.client.ui.view.EditPicturesLayout;
import com.timetalent.common.util.Config;
import com.timetalent.common.util.IntentUtil;
import com.timetalent.common.util.LogUtil;
import com.timetalent.common.util.PictureUtil;
import com.timetalent.common.util.StringUtil;
import com.timetalent.common.util.ToastUtil;

/******************************************
 * 类描述： 登录界面 类名称：LoginActivity
 * 
 * @version: 1.0
 * @author: why
 * @time: 2014-10-10 下午6:32:12
 ******************************************/
public class MyzuopinActivity extends BaseActivity implements OnClickListener {
	private AppController controller;
	private TextView main_top_right;
	private ImageButton main_top_left;
	private Button btbaocun;
	ImageView imghead;
	EditText ettitle;
	EditText etactor;
	EditText ettime;
	public int screenw = 400;
	public int screenh = 800;
	public float density = 1.0f;
	List<PicValuePair> picValuePair;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_yirenzuopin_update);
		controller = AppController.getController(this);
		DisplayMetrics dm = new DisplayMetrics();
		this.getWindowManager().getDefaultDisplay().getMetrics(dm);
		screenw = dm.widthPixels;
		density = dm.density;
		screenh = dm.heightPixels;
		findView();
		initView();
	}

	/**
	 * 方法描述：TODO
	 * 
	 * @author: why
	 * @time: 2014-10-10 下午6:36:00
	 */
	private void findView() {
		main_top_left = (ImageButton) this.findViewById(R.id.main_top_left);
		btbaocun = (Button) findViewById(R.id.btbaocun);
		imghead = (ImageView) findViewById(R.id.imgzuopin);
		ettitle = (EditText) findViewById(R.id.etzuopinname);
		etactor = (EditText) findViewById(R.id.etzuopinjuese);
		ettime = (EditText) findViewById(R.id.etzuopintime);
	}

	/**
	 * 方法描述：TODO
	 * 
	 * @author: why
	 * @time: 2014-10-10 下午6:36:02
	 */
	private void initView() {
		((TextView) this.findViewById(R.id.main_top_title)).setText("作品上传");
		// UIUtils.setDrawableLeft(this,main_top_right,R.drawable.d3_06);
		main_top_left.setVisibility(View.VISIBLE);
		// UIUtils.setDrawableLeft(this,main_top_left2,R.drawable.d3_03);
		main_top_left.setOnClickListener(this);
		btbaocun.setOnClickListener(this);
		imghead.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.main_top_left:
			finish();
			break;
		case R.id.imgzuopin:
			StringUtil.doGoToImg(MyzuopinActivity.this);
			break;
		case R.id.btbaocun:
			controller.getContext().addBusinessData("work.title", ettitle.getText().toString());
			  controller.getContext().addBusinessData("work.actor", etactor.getText().toString());
			  controller.getContext().addBusinessData("work.time", ettime.getText().toString());
			  new Thread(){
				  public void run() {
					  controller.myworksadd(picValuePair);
				  };
			  }.start();
			finish();
		default:
			break;
		}
	}
	protected void onActivityResult(int requestCode, int resultCode, android.content.Intent data) {

		if (resultCode == RESULT_OK) {
			if (data != null) {
				Uri uri = data.getData();
				if (!TextUtils.isEmpty(uri.getAuthority())) {
					Cursor cursor = getContentResolver().query(uri,
							new String[] { MediaStore.Images.Media._ID,MediaStore.Images.Media.DATA },
							null, null, null);
					if (null == cursor) {
						ToastUtil.showToast(MyzuopinActivity.this, "图片没找到", 0);
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
								  picValuePair = new ArrayList<PicValuePair>();
								  picValuePair.add(new PicValuePair("photo", file));
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
	public int calculateInSampleSize(BitmapFactory.Options options,int reqWidth, int reqHeight) {
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
	public Bitmap getSmallBitmap(String filePath) {
	        final BitmapFactory.Options options = new BitmapFactory.Options();
	        options.inJustDecodeBounds = true;
	        BitmapFactory.decodeFile(filePath, options);

	        // Calculate inSampleSize
	    options.inSampleSize = calculateInSampleSize(options, screenw, screenh);

	        // Decode bitmap with inSampleSize set
	    options.inJustDecodeBounds = false;

	    return BitmapFactory.decodeFile(filePath, options);
	    }
	 public File getFileFromBytes(byte[] b, String outputFile){
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
