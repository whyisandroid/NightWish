package com.timetalent.client.ui.login;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.timetalent.client.R;
import com.timetalent.client.entities.PicValuePair;
import com.timetalent.client.entities.Register;
import com.timetalent.client.service.AppController;
import com.timetalent.client.ui.BaseActivity;
import com.timetalent.common.util.Config;
import com.timetalent.common.util.IntentUtil;
import com.timetalent.common.util.PictureUtil;
import com.timetalent.common.util.ProgressDialogUtil;
import com.timetalent.common.util.StringUtil;
import com.timetalent.common.util.ToastUtil;

/******************************************
 * 类描述： 注册 第二步  个人资料
 * 类名称：RegisterFirstActivity  
 * @version: 1.0
 * @author: why
 * @time: 2014-10-11 下午7:54:27 
 ******************************************/
public class RegisterSecondActivity extends BaseActivity implements OnClickListener {
	private String TAG = "RegisterSecondActivity";
	private AppController controller;
	private TextView main_top_right;
	private Button bt_register_second_next;
	private RadioButton bt_register_second_1;
	private RadioButton bt_register_second_2;
	private RadioButton bt_register_second_3;
	private ImageView iv_register_head; // 头像
	private RelativeLayout rl_register_data;//选择生日
	private TextView tv_register_data; // 生日
	
	private RadioGroup rg_register_sex;
	
	private Register register;
	
	
	 private int crop = 180; // 图片尺寸
	 
	 private File sdcardTempFile;
	 private List<PicValuePair> picValuePair = new ArrayList<PicValuePair>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_second_layout);
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
		bt_register_second_next = (Button)findViewById(R.id.bt_register_second_next);
		bt_register_second_1 = (RadioButton)findViewById(R.id.bt_register_second_1);
		bt_register_second_2 = (RadioButton)findViewById(R.id.bt_register_second_2);
		bt_register_second_3 = (RadioButton)findViewById(R.id.bt_register_second_3);
		iv_register_head = (ImageView)findViewById(R.id.iv_register_head);
		tv_register_data = (TextView)findViewById(R.id.tv_register_data);
		rl_register_data = (RelativeLayout)findViewById(R.id.rl_register_data);
		rg_register_sex = (RadioGroup)findViewById(R.id.rg_register_sex);
	}

	/**
	 * 方法描述：TODO
	 * 
	 * @author: why
	 * @time: 2014-10-10 下午6:36:02
	 */
	private void initView() {
		((TextView)findViewById(R.id.main_top_title)).setText("个人资料");
		main_top_right.setVisibility(View.VISIBLE);
		main_top_right.setText("2/4");
		bt_register_second_next.setOnClickListener(this);
		bt_register_second_1.setOnClickListener(this);
		bt_register_second_2.setOnClickListener(this);
		bt_register_second_3.setOnClickListener(this);
		iv_register_head.setOnClickListener(this);
		rl_register_data.setOnClickListener(this);
		register = (Register)getIntent().getExtras().getSerializable("Register.register");
		register.setType("fans");
		sdcardTempFile = new File("/mnt/sdcard/", "tmp_pic_" + SystemClock.currentThreadTimeMillis() + ".jpg");
	}
	
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_register_second_next:
			if (invaild()) {
				Bundle bundle = new Bundle();
				register.setBirthday(tv_register_data.getText().toString().trim());
				bundle.putSerializable("Register.register", register);
				IntentUtil.intent(RegisterSecondActivity.this,bundle,RegisterThreeActivity.class,false);
			}
			
			break;
		case R.id.bt_register_second_1:
			register.setType("fans");
			bt_register_second_1.setChecked(true);
			bt_register_second_2.setChecked(false);
			bt_register_second_3.setChecked(false);
			break;
		case R.id.bt_register_second_2:
			register.setType("scout");
			bt_register_second_1.setChecked(false);
			bt_register_second_2.setChecked(true);
			bt_register_second_3.setChecked(false);
			break;
		case R.id.bt_register_second_3:
			register.setType("star");
			bt_register_second_1.setChecked(false);
			bt_register_second_2.setChecked(false);
			bt_register_second_3.setChecked(true);
			break;
		case R.id.iv_register_head:
			PictureUtil.headPic(RegisterSecondActivity.this, crop, sdcardTempFile);
			break;
		case R.id.rl_register_data:
			StringUtil.getData(RegisterSecondActivity.this,tv_register_data);
			break;
		default:
			break;
		}
	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			Bitmap bmp = BitmapFactory.decodeFile(sdcardTempFile
					.getAbsolutePath());
			iv_register_head.setImageBitmap(bmp);
			// 上传头像 处理
			picValuePair.add(new PicValuePair("avatar", sdcardTempFile));
			controller.getContext().addBusinessData("Register.avatar", picValuePair);
		}
	}
	/**
	  * 方法描述：TODO
	  * @return
	  * @author: wanghy
	  * @time: 2014-10-23 下午10:25:08
	  */
	private boolean invaild() {
		String data = tv_register_data.getText().toString().trim();
		if(picValuePair.size() == 0){
			ToastUtil.showToast(this, "请选择头像", ToastUtil.LENGTH_LONG);
			return false;
		}
		if(TextUtils.isEmpty(data)){
			ToastUtil.showToast(this, "请选择生日", ToastUtil.LENGTH_LONG);
			return false;
		} 
		
		 if(rg_register_sex.getCheckedRadioButtonId() == R.id.rb_register_man){
			 register.setSex("1");
		 }else{
			 register.setSex("2");
		 }
		return true;
	}
}
