package com.timetalent.client.ui.chance;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.timetalent.client.R;
import com.timetalent.client.entities.Job;
import com.timetalent.client.entities.PicValuePair;
import com.timetalent.client.entities.TaskAdd;
import com.timetalent.client.service.AppController;
import com.timetalent.client.ui.BaseActivity;
import com.timetalent.client.ui.MainActivity;
import com.timetalent.client.ui.adapter.OfferAddAdapter;
import com.timetalent.common.exception.BusinessException;
import com.timetalent.common.util.Json_U;
import com.timetalent.common.util.LogUtil;
import com.timetalent.common.util.PictureUtil;
import com.timetalent.common.util.ProgressDialogUtil;
import com.timetalent.common.util.StringUtil;
import com.timetalent.common.util.ToastUtil;
import com.timetalent.common.util.UIUtils;


/******************************************
 * 类描述： 发职位
 * 类名称：OfferActivity  
 * @version: 1.0
 * @author: why
 * @time: 2014-10-14 下午7:17:40 
 ******************************************/
public class OfferAddActivity extends BaseActivity implements OnClickListener {
	private AppController controller;
	private Button bt_chance_offer;
	private RelativeLayout rl_chance_data1;//报名截止日期
	private TextView tv_chance_data1;
	private EditText et_chance_add_title_des;
	
	private ListView lv_chance_add;
	private ImageView iv_chance_offer_add;
	public static Boolean ADDFlag = false;
	
	
	private OfferAddAdapter adapter ;
	 
	private File sdcardTempFile;
	private List<Job> list; 
	private EditText et_chance_add_title;
	private RelativeLayout rl_chance_add_place;//地点
	private TextView tv_chance_add_place;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chance_offer_layout);
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
		bt_chance_offer = (Button)findViewById(R.id.bt_chance_offer);
		tv_chance_data1 = (TextView)findViewById(R.id.tv_chance_data1);
		tv_chance_add_place = (TextView)findViewById(R.id.tv_chance_add_place);
		rl_chance_data1 = (RelativeLayout)findViewById(R.id.rl_chance_data1);
		rl_chance_add_place = (RelativeLayout)findViewById(R.id.rl_chance_add_place);
	
		
		lv_chance_add = (ListView)findViewById(R.id.lv_chance_add);
		iv_chance_offer_add = (ImageView)findViewById(R.id.iv_chance_offer_add);
		et_chance_add_title = (EditText)findViewById(R.id.et_chance_add_title);
		et_chance_add_title_des = (EditText)findViewById(R.id.et_chance_add_title_des);
	}
	
	/**
	 * 方法描述：TODO
	 * 
	 * @author: why
	 * @time: 2014-10-10 下午6:36:02
	 */
	private void initView() {
		sdcardTempFile = new File("/mnt/sdcard/", "tmp_pic_" + SystemClock.currentThreadTimeMillis() + ".jpg");
		((TextView)findViewById(R.id.main_top_title)).setText("发布职位");
		bt_chance_offer.setOnClickListener(this);
		rl_chance_data1.setOnClickListener(this);
		rl_chance_add_place.setOnClickListener(this);
		
		iv_chance_offer_add.setOnClickListener(this);
		list = new ArrayList<Job>();
		list.add(new Job());
		adapter = new  OfferAddAdapter(this, list);
		lv_chance_add.setAdapter(adapter);
		UIUtils.setListViewHeight(lv_chance_add, adapter);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_chance_offer:
			
			if (invaild()) {
				addTask();
			}
			break;
		case R.id.rl_chance_data1:
			StringUtil.getData(OfferAddActivity.this,tv_chance_data1);
			break;
		case R.id.iv_chance_offer_add:
			list = adapter.getList();
			list.add(new Job());
			adapter = new  OfferAddAdapter(this, list);
			lv_chance_add.setAdapter(adapter);
			UIUtils.setListViewHeight(lv_chance_add, adapter);
			break;
			
		case R.id.rl_chance_add_place:
			final CharSequence[] charSequences = {"北京","上海","天津","广州","海口","深圳"};
			AlertDialog.Builder builder= new AlertDialog.Builder(this);
			
			builder.setTitle("工作地点")
					.setIcon(R.drawable.ic_launcher)
					.setItems(charSequences, new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							tv_chance_add_place.setText(charSequences[which]);
						}
					}).show();
			break;
		default:
			break;
		}
	}
	
	
	
	/**
	  * 方法描述：TODO
	  * @author: wanghy
	  * @time: 2014-11-9 下午4:18:01
	  */
	private void addTask() {
		ProgressDialogUtil.showProgressDialog(this, "发布中…", false);
		new Thread(new Runnable() {
			@Override
			public void run() {
				controller.chanceAdd();
				ProgressDialogUtil.closeProgressDialog();
			}
		}).start();
	}
	/**
	  * 方法描述：TODO
	  * @return
	  * @author: wanghy
	  * @time: 2014-11-9 下午4:17:44
	  */
	private boolean invaild() {
		TaskAdd task = new TaskAdd();
		String title = et_chance_add_title.getText().toString().trim();
		if(TextUtils.isEmpty(title)){
			ToastUtil.showToast(this, "招聘专题不能为空", ToastUtil.LENGTH_LONG);
			return false;
		}else{
			task.setTitle(title);
		}
		
		String titledes = et_chance_add_title_des.getText().toString().trim();
		if(TextUtils.isEmpty(titledes)){
			ToastUtil.showToast(this, "工作介绍不能为空", ToastUtil.LENGTH_LONG);
			return false;
		}else{
			task.setDescription(titledes);
		}
		
		String data1 = tv_chance_data1.getText().toString().trim();
		if(TextUtils.isEmpty(data1)){
			ToastUtil.showToast(this, "报名截止日期 不能为空", ToastUtil.LENGTH_LONG);
			return false;
		}else{
			task.setCutoff_date(StringUtil.transformDate(data1)+"");
		}
		
		String palce = tv_chance_add_place.getText().toString().trim();
		if(TextUtils.isEmpty(palce)){
			ToastUtil.showToast(this, "工作地点不能为空", ToastUtil.LENGTH_LONG);
			return false;
		}else{
			task.setPlace(palce);
		}
		
		
		for (Job job : list) {
			if(TextUtils.isEmpty(job.getJob())){
				ToastUtil.showToast(this, "职位名称不能为空", ToastUtil.LENGTH_LONG);
				return false;
			}
			if(TextUtils.isEmpty(job.getDescription())){
				ToastUtil.showToast(this, "职位描述 不能为空", ToastUtil.LENGTH_LONG);
				return false;
			}
			if(TextUtils.isEmpty(job.getWork_date_start())){
				ToastUtil.showToast(this, "工作开始时间不能为空", ToastUtil.LENGTH_LONG);
				return false;
			}
			if(TextUtils.isEmpty(job.getWork_date_end())){
				ToastUtil.showToast(this, "工作结束时间不能为空", ToastUtil.LENGTH_LONG);
				return false;
			}
		}
		
		
		try {
			String job_json = Json_U.objToJsonStr(adapter.getList());
			LogUtil.Log("发布成功",job_json);
			task.setJob_lists_json(job_json);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
		controller.getContext().addBusinessData("Task_ADD", task);
		return true;
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
/*			Bitmap bmp = BitmapFactory.decodeFile(sdcardTempFile
					.getAbsolutePath());
			iv_chance_add_head.setImageBitmap(bmp);
			pictureFlag = true;
			List<PicValuePair> picValuePair = new ArrayList<PicValuePair>();
			picValuePair.add(new PicValuePair("banner", sdcardTempFile));
			controller.getContext().addBusinessData("Task.banner", picValuePair);*/
		}
	}
}
