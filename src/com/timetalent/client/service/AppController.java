package com.timetalent.client.service;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.timetalent.client.service.impl.AppServiceImpl;
import com.timetalent.client.ui.MainFragmentActivity;
import com.timetalent.client.ui.login.LoginActivity;
import com.timetalent.common.exception.BusinessException;
import com.timetalent.common.util.IntentUtil;
import com.timetalent.common.util.ToastUtil;

/******************************************
 * 类描述： 控制层 该类保存客户端控制器。 类名称：AppController
 * 
 * @version: 1.0
 * @author: why
 * @time: 2014-2-13 下午2:09:22
 ******************************************/

public class AppController {
	/**
	 * 客户端上下文，该对象用来缓存客户端业务数据和参数配置
	 */
	private AppContext context;
	/** 服务对象 **/
	private AppService service;

	public AppContext getContext() {
		return context;
	}

	public void setContext(AppContext context) {
		this.context = context;
	}

	/***
	 * 控制器单例对象
	 */
	private static AppController controller = null;

	/***
	 * 当前android的活动对象
	 */
	private Activity currentActivity;

	private AppController(Activity act) {
		this.currentActivity = act;
		createContext();
		service = new AppServiceImpl(context);
	}

	/**
	 * 初始化客户端配置信息
	 */
	private void createContext() {
		context = new AppContext();
		// context.setConfigProerties(getProperties());
	}

	/**
	 * 得到单例的controller对象
	 * 
	 * @return
	 */
	public synchronized static AppController getController() {
		if (controller == null) {
			controller = new AppController(null);
		}
		return controller;
	}

	/**
	 * 得到单例controller对象，并设置当前controller当前关联的Activity活动对象
	 */
	public synchronized static AppController getController(Activity act) {
		if (controller == null) {
			controller = new AppController(act);
		} else {
			controller.setCurrentActivity(act);
		}
		return controller;
	}

	public Activity getCurrentActivity() {
		return currentActivity;
	}

	public void setCurrentActivity(Activity currentActivity) {
		this.currentActivity = currentActivity;
	}
	private static final int HANDLER_DIALOG = 0; //弹对话框
	private static final int HANDLER_TOAST = 1; // 吐司 专用
	private static final int HANDLER_UPDATE = 2; // 更新
	private static final int HANDLER_UPDATE_ABOUT = 3; // 更新 错误信息由提示 about
	private Handler accHandler; // 账户页面专用 Handler

	public void setAccHandler(Handler accHandler) {
		this.accHandler = accHandler;
	}

	public Handler getAccHandler() {
		return accHandler;
	}

	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			
			case HANDLER_DIALOG:
				if(!TextUtils.isEmpty(msg.obj.toString())){
					//DialogUtil.showError(currentActivity,msg.obj.toString());
				}
			case HANDLER_TOAST:
				if(!TextUtils.isEmpty(msg.obj.toString())){
					ToastUtil.showToast(currentActivity, msg.obj.toString(), ToastUtil.LENGTH_LONG);
				}
				break;
			default:
				break;
			}
		};
	};

	/**
	 * 方法描述：登录业务
	 * 
	 * @author: why
	 * @time: 2014-2-17 下午5:51:11
	 */
	public void login() {
		try {
			service.login();
			//AppManager.getAppManager().finishActivity(currentActivity);
			IntentUtil.intent(currentActivity, MainFragmentActivity.class);
			handler.obtainMessage(HANDLER_TOAST, "登陆成功").sendToTarget();
		} catch (BusinessException e) {
			e.printStackTrace();
			handler.obtainMessage(HANDLER_TOAST, e.getErrorMessage().getMessage()).sendToTarget();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	/**
	  * 方法描述：TODO
	  * @param alterHandler
	  * @author: why
	  * @time: 2014-10-11 下午3:39:55
	  */
	public void setAlterHandler(Handler alterHandler) {
		
	}
}
