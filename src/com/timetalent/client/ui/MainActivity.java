package com.timetalent.client.ui;

import android.app.Activity;
import android.os.Bundle;

import com.timetalent.client.R;
import com.timetalent.common.util.IntentUtil;


public class MainActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// test
		findView();
		initView();
	}

	
	/**
	  * 方法描述：TODO
	  * @author: why
	  * @time: 2014-10-10 下午6:46:58
	  */
	private void initView() {
		IntentUtil.intent(this, MainFragmentActivity.class);
	}

	private void findView() {
		//test github
	}
}
