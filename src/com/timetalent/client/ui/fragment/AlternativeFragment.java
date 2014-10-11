package com.timetalent.client.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.timetalent.client.R;
import com.timetalent.client.TimeTalentApplication;
import com.timetalent.client.service.AppController;


/******************************************
 * 类描述： 附近  fragment 切换类
 * 类名称：AlternativeFragment  
 * @version: 1.0
 * @author: ghf
 * @time: 2014-10-8 下午5:21:00 
 ******************************************/
public class AlternativeFragment extends Fragment{

	FragmentTransaction transaction;
	Fragment fragment;
	View rootView;
	public static final int Alter_SWITCH_VIEW=1;
	private AppController controller;
	 private Handler  alterHandler = new Handler(){
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case Alter_SWITCH_VIEW:
				onReLoadView();
				break;
		
			default:
				break;
			}
		}
	};
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		// 缓存rootView
		if (null==rootView) {
			rootView=inflater.inflate(R.layout.alternative_fragment_layout, container, false);
		}
		// 缓存的rootView需要判断是否已经被加过parent，如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
		ViewGroup parent = (ViewGroup) rootView.getParent();
		if (parent != null)
		{
			parent.removeView(rootView);
		}		
		controller = AppController.getController(getActivity());
		controller.setAlterHandler(alterHandler);
		onReLoadView();
		return rootView;
	}
	
	/**
	 * 方法描述：TODO
	 * @author: ghf
	 * @time: 2014-10-9 下午4:23:36
	 */
	public void onReLoadView() {
			
		transaction = getChildFragmentManager().beginTransaction();
		if (TimeTalentApplication.getInstance().isLogin()) {
			if (fragment!=null) {
				//Log.e("登录", fragment.isInLayout()+"");				
				if (fragment instanceof NearFragment||!fragment.isInLayout()) {
					fragment.onDetach();
					fragment = new UserFragment();
				}
			}else{
				fragment = new UserFragment();
			}
		}else{
			if (fragment!=null) {
				//Log.e("未登录", fragment.isInLayout()+"");
				if (fragment instanceof UserFragment||!fragment.isInLayout()) {
					fragment.onDetach();
					fragment = new NearFragment();
				}
			}else{
				fragment = new NearFragment();
			}
		}        
        transaction.replace(R.id.alter_fragment, fragment);
        transaction.commitAllowingStateLoss();
	}
}
