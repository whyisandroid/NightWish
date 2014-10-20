package com.timetalent.client.ui.chance;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.timetalent.client.R;
import com.timetalent.client.service.AppController;
import com.timetalent.client.ui.BaseActivity;
import com.timetalent.client.ui.dialog.DialogUtil;
import com.timetalent.client.ui.dialog.IOSStyleDialog;
import com.timetalent.common.util.IntentUtil;
import com.timetalent.common.util.StringUtil;


/******************************************
 * 类描述： TODO
 * 类名称：WorkAppointmentActivity  
 * @version: 1.0
 * @author: wanghy
 * @time: 2014-10-19 下午10:43:04 
 ******************************************/
public class WorkAppointmentActivity extends BaseActivity implements OnClickListener {
	private AppController controller;
	private TextView tv_work_data;
	private LinearLayout ll_work_apply;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.work_appointment_layout);
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
		tv_work_data = (TextView)findViewById(R.id.tv_work_data);
		ll_work_apply = (LinearLayout)findViewById(R.id.ll_work_apply);
	}
	/**
	 * 方法描述：TODO
	 * 
	 * @author: why
	 * @time: 2014-10-10 下午6:36:02
	 */
	private void initView() {
		((TextView)findViewById(R.id.main_top_title)).setText("工作预约");
		tv_work_data.setOnClickListener(this);
		ll_work_apply.setOnClickListener(this);
	}
	
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_work_data:
			StringUtil.getData(WorkAppointmentActivity.this,tv_work_data);
			break;
		case R.id.ll_work_apply:
			final IOSStyleDialog dialog = new IOSStyleDialog(WorkAppointmentActivity.this, IOSStyleDialog.DIALOG_ONE);
			dialog.setMessage("您已成功预约了某某的工作");
			dialog.setOne("确认",new OnClickListener() {
				@Override
				public void onClick(View v) {
					dialog.closeDialog();
					WorkAppointmentActivity.this.finish();
				}
			});
			break;
		default:
			break;
		}
	}

}
