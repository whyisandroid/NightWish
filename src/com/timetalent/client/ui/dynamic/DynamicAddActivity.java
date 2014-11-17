package com.timetalent.client.ui.dynamic;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.BitmapUtils;
import com.timetalent.client.R;
import com.timetalent.client.entities.PicValuePair;
import com.timetalent.client.entities.Picture;
import com.timetalent.client.service.AppController;
import com.timetalent.client.ui.BaseActivity;
import com.timetalent.client.ui.adapter.DynamicAddAdapter;
import com.timetalent.common.util.LogUtil;
import com.timetalent.common.util.PictureUtil;
import com.timetalent.common.util.ProgressDialogUtil;
import com.timetalent.common.util.ToastUtil;
import com.timetalent.common.util.UIUtils;


/******************************************
 * 类描述： 添加动态
 * 类名称：AddDynamicActivity  
 * @version: 1.0
 * @author: wanghy
 * @time: 2014-10-12 下午8:25:54 
 ******************************************/
public class DynamicAddActivity extends BaseActivity implements OnClickListener {
	private AppController controller;
	private TextView main_top_right;
	private TextView main_top_left2;
	private EditText et_dynamic_add_content;
	private GridView gv_dynamic_add;
	private DynamicAddAdapter adapter;
	
	// 原图列表
	ArrayList<Picture> imgList = new ArrayList<Picture>();
	
	private Handler mHandler = new  Handler(){
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0:
				//发送图片 
				for (int i = 0; i < imgList.size(); i++) {
					File file = new File(imgList.get(i).getPath());
					sendPic(file);
				}
				break;
			default:
				break;
			}
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dynamic_add_info);
		controller = AppController.getController(this);
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
		main_top_right = (TextView)findViewById(R.id.main_top_right);
		main_top_left2 = (TextView)findViewById(R.id.main_top_left2);
		et_dynamic_add_content =(EditText)findViewById(R.id.et_dynamic_add_content);
		gv_dynamic_add = (GridView)findViewById(R.id.gv_dynamic_add);
	}

	/**
	 * 方法描述：TODO
	 * 
	 * @author: why
	 * @time: 2014-10-10 下午6:36:02
	 */
	private void initView() {
		((TextView)findViewById(R.id.main_top_title)).setText("添加动态");
		main_top_right.setVisibility(View.VISIBLE);
		main_top_right.setText("发表");
		main_top_right.setOnClickListener(this);
		findViewById(R.id.main_top_left).setVisibility(View.GONE);
		main_top_left2.setVisibility(View.VISIBLE);
		main_top_left2.setText("取消");
		main_top_right.setOnClickListener(this);
		main_top_left2.setOnClickListener(this);
		adapter = new  DynamicAddAdapter(this,mHandler,imgList);
		gv_dynamic_add.setAdapter(adapter);
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			if (data != null) {
				Uri uri = data.getData();
				if (!TextUtils.isEmpty(uri.getAuthority())) {
					Cursor cursor = getContentResolver().query(uri,
							new String[] { MediaStore.Images.Media._ID,MediaStore.Images.Media.DATA },
							null, null, null);
					if (null == cursor) {
						ToastUtil.showToast(DynamicAddActivity.this, "图片没找到", 0);
						return;
					}
					cursor.moveToFirst();
					String id = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media._ID));
					String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
					cursor.close();
					LogUtil.Log("path=" + path);
					if (PictureUtil.isPicture(path)) {
							// 添加
						Picture pic = new Picture(Integer.valueOf(id),path);
							if (!containsPic(pic)) {
								imgList.add(pic);
							} else {
								ToastUtil.showToast(this, "您已添加此图片！",
										ToastUtil.LENGTH_SHORT);
							}
							adapter.notifyDataSetChanged();
					} else {
						Toast.makeText(
								this,
								"请选择png或者jpg格式的图片",
								Toast.LENGTH_SHORT).show();
					}
				}
				adapter.notifyDataSetChanged();
				}
			}
	}
	
	/**
	  * 方法描述：TODO
	  * @param pic
	  * @return
	  * @author: wanghy
	  * @time: 2014-11-16 下午6:29:12
	  */
	private boolean containsPic(Picture pic) {
		for (Picture picture : imgList) {
			if(picture.getId() == pic.getId()){
				return true;
			}
		}
		return false;
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.main_top_right:
			if(invaild()){
				send();
			}
			break;
		case R.id.main_top_left2:
			this.finish();
			break;
		default:
			break;
		}
	}
	
	/**
	  * 方法描述：TODO
	  * @author: why
	  * @time: 2014-10-21 上午11:17:14
	  */
	private void send() {
		ProgressDialogUtil.showProgressDialog(this, "通信中…", false);
		new Thread(new Runnable() {
			@Override
			public void run() {
				controller.dynamicAdd(mHandler);
				ProgressDialogUtil.closeProgressDialog();
			}
		}).start();
	}
	

	private void sendPic(final File file) {
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				List<PicValuePair> picValuePair = new ArrayList<PicValuePair>();
				picValuePair.add(new PicValuePair("photo", file));
				controller.dynamicAddPic(picValuePair);
			}
		}).start();
		
	};
	
	/**
	  * 方法描述：TODO
	  * @return
	  * @author: why
	  * @time: 2014-10-21 上午11:17:11
	  */
	private boolean invaild() {
		String account = et_dynamic_add_content.getText().toString().trim();
		if(TextUtils.isEmpty(account)){
			ToastUtil.showToast(this, "发表内容不能为空", ToastUtil.LENGTH_LONG);
			return false;
		}else{
			controller.getContext().addBusinessData("dynamic_add", account);
		}
		return true;
	}
}
