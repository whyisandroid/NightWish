package com.timetalent.client.service;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.timetalent.client.entities.PicValuePair;
import com.timetalent.client.service.impl.AppServiceImpl;
import com.timetalent.client.ui.MainFragmentActivity;
import com.timetalent.client.ui.chance.OfferAddActivity;
import com.timetalent.client.ui.chance.OfferDetailActivity;
import com.timetalent.client.ui.dialog.DialogUtil;
import com.timetalent.client.ui.dynamic.DynamicAddActivity;
import com.timetalent.client.ui.esaemob.ChatActivity;
import com.timetalent.client.ui.login.LoginActivity;
import com.timetalent.common.exception.BusinessException;
import com.timetalent.common.util.IntentUtil;
import com.timetalent.common.util.StringUtil;
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
	public static final int HANDLER_TOAST = 1; // 吐司 专用
	private static final int HANDLER_UPDATE = 2; // 更新
	private static final int HANDLER_UPDATE_ABOUT = 3; // 更新 错误信息由提示 about
	
	private static final int MESSAGE_ACCESS = 4;// 不能聊天对话框
	private static final int MESSAGE_PAY = 5;// 提示支付聊天费用
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
					DialogUtil.showMessage(currentActivity,msg.obj.toString());
				}
				break;
			case HANDLER_TOAST:
				if(!TextUtils.isEmpty(msg.obj.toString())){
					ToastUtil.showToast(currentActivity, msg.obj.toString(), ToastUtil.LENGTH_LONG);
				}
				break;
			case MESSAGE_ACCESS:
				DialogUtil.messageAccess(currentActivity);
				break;
			case MESSAGE_PAY:
				DialogUtil.messagePayN(currentActivity);
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
			service.login(handler);
		} catch (BusinessException e) {
			e.printStackTrace();
			handler.obtainMessage(HANDLER_TOAST, e.getErrorMessage().getMessage()).sendToTarget();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void logout(){
		try {
			service.logout();
			IntentUtil.intent(currentActivity, MainFragmentActivity.class);
			handler.obtainMessage(HANDLER_TOAST, "注销成功").sendToTarget();
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
	
	/**
	  * 方法描述：TODO
	  * @author: wanghy
	  * @time: 2014-10-23 下午10:54:28
	  */
	public void resetPwd() {
		try {
			service.resetPwd();
			IntentUtil.intent(currentActivity, LoginActivity.class);
		} catch (BusinessException e) {
			handler.obtainMessage(HANDLER_TOAST, e.getErrorMessage().getMessage()).sendToTarget();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	/**
	  * 方法描述：TODO
	  * @author: wanghy
	  * @time: 2014-10-23 下午10:54:28
	  */
	public void code(Handler mHandler) {
		try {
			service.code();
			handler.obtainMessage(HANDLER_DIALOG,"验证码已经发送到您的手机").sendToTarget();
		} catch (BusinessException e) {
			handler.obtainMessage(HANDLER_TOAST, e.getErrorMessage().getMessage()).sendToTarget();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	/**
	  * 方法描述：TODO
	  * @author: wanghy
	  * @time: 2014-10-23 下午11:03:50
	  */
	public void validationCode(Handler mHandler) {
		try {
			service.validationCode();
			mHandler.obtainMessage(0).sendToTarget();
		} catch (BusinessException e) {
			e.printStackTrace();
			handler.obtainMessage(HANDLER_TOAST, e.getErrorMessage().getMessage()).sendToTarget();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	/**
	  * 方法描述：TODO
	  * @author: wanghy
	  * @time: 2014-10-23 下午11:24:31
	  */
	public void register() {
		try {
			service.register();
			service.register_avatar();
			handler.obtainMessage(HANDLER_TOAST, "注册成功").sendToTarget();
			IntentUtil.intent(currentActivity, LoginActivity.class);
		} catch (BusinessException e) {
			e.printStackTrace();
			handler.obtainMessage(HANDLER_TOAST, e.getErrorMessage().getMessage()).sendToTarget();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	  * 方法描述：TODO
	  * @author: wanghy
	  * @time: 2014-10-23 下午11:24:31
	  */
	public void register_avatar() {
		try {
			service.register_avatar();
		} catch (BusinessException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	public void chanceAdd() {
		try {
			service.chanceAdd();
			OfferAddActivity.ADDFlag = true;
			handler.obtainMessage(HANDLER_TOAST,"发表成功").sendToTarget();
			currentActivity.finish();
		} catch (BusinessException e) {
			e.printStackTrace();
			handler.obtainMessage(HANDLER_TOAST, e.getErrorMessage().getMessage()).sendToTarget();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void chatAccess(String ID,String nickName,String userImageURL) {
		try {
			service.chatAccess();
			Intent intent = new Intent(currentActivity,ChatActivity.class);
			intent.putExtra("userId", StringUtil.getEsaeUserName(ID));
			intent.putExtra("nickName", nickName);
			intent.putExtra("userImageURL", userImageURL);
			currentActivity.startActivity(intent);
			context.addBusinessData("userId", StringUtil.getEsaeUserName(ID));
			context.addBusinessData("nickName", nickName);
			context.addBusinessData("userImageURL", userImageURL);
		} catch (BusinessException e) {
			e.printStackTrace();
			handler.obtainMessage(MESSAGE_ACCESS, e.getErrorMessage().getMessage()).sendToTarget();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void chatPay() {
		try {
			service.chatPay();
			Intent intent = new Intent(currentActivity,ChatActivity.class);
			intent.putExtra("userId", context.getStringData("userId"));
			intent.putExtra("nickName",context.getStringData("nickName"));
			intent.putExtra("userImageURL",context.getStringData("userImageURL"));
			currentActivity.startActivity(intent);
		} catch (BusinessException e) {
			e.printStackTrace();
			if(2 == e.getErrorMessage().getCode()){
				handler.obtainMessage(MESSAGE_PAY, e.getErrorMessage().getMessage()).sendToTarget();
			}else{
				handler.obtainMessage(HANDLER_TOAST, e.getErrorMessage().getMessage()).sendToTarget();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void chanceLists(Handler mHandler,int num) {
		try {
			service.chanceLists(num);
			mHandler.obtainMessage(0).sendToTarget();
		} catch (BusinessException e) {
			e.printStackTrace();
			mHandler.obtainMessage(1).sendToTarget();
			handler.obtainMessage(HANDLER_TOAST, e.getErrorMessage().getMessage()).sendToTarget();
		} catch (Exception e) {
			mHandler.obtainMessage(1).sendToTarget();
			e.printStackTrace();
		}
	}
	
	public void chanceDetails() {
		try {
			service.chanceDetails();
			IntentUtil.intent(currentActivity, OfferDetailActivity.class);
		} catch (BusinessException e) {
			e.printStackTrace();
			handler.obtainMessage(HANDLER_TOAST, e.getErrorMessage().getMessage()).sendToTarget();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void chanceApply(String job) {
		try {
			service.chanceApply();
			handler.obtainMessage(HANDLER_DIALOG,"您已经报名了："+job).sendToTarget();
			//IntentUtil.intent(currentActivity, MainFragmentActivity.class);
		} catch (BusinessException e) {
			e.printStackTrace();
			handler.obtainMessage(HANDLER_TOAST, e.getErrorMessage().getMessage()).sendToTarget();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void dynamicAdd(Handler mHandler) {
		try {
			service.dynamicAdd();
			mHandler.obtainMessage(0).sendToTarget();
		} catch (BusinessException e) {
			e.printStackTrace();
			handler.obtainMessage(HANDLER_TOAST, e.getErrorMessage().getMessage()).sendToTarget();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void dynamicAddPic(List<PicValuePair> picValuePair) {
		try {
			service.dynamicAdd_pic(picValuePair);
			DynamicAddActivity.ADDFlag = true;
			handler.obtainMessage(HANDLER_TOAST,"发布成功").sendToTarget();
			currentActivity.finish();
		} catch (BusinessException e) {
			e.printStackTrace();
			handler.obtainMessage(HANDLER_TOAST, e.getErrorMessage().getMessage()).sendToTarget();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void dynamicIndex(Handler mHandler, int pageNum) {
			try {
				service.dynamicIndex(pageNum);
				mHandler.obtainMessage(0).sendToTarget();
			} catch (BusinessException e) {
				e.printStackTrace();
				mHandler.obtainMessage(1).sendToTarget();
				handler.obtainMessage(HANDLER_TOAST, e.getErrorMessage().getMessage()).sendToTarget();
			} catch (Exception e) {
				e.printStackTrace();
				mHandler.obtainMessage(1).sendToTarget();
			}
		
	}
	
	public void dynamicRepaly(Handler mHandler,Handler adaHandler,com.timetalent.client.ui.adapter.DynamicAdapter.ViewHolder holder) {
		try {
			service.dynamicRepaly();
			adaHandler.obtainMessage(1,holder).sendToTarget();
		} catch (BusinessException e) {
			e.printStackTrace();
			handler.obtainMessage(HANDLER_TOAST, e.getErrorMessage().getMessage()).sendToTarget();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

	
	
	public void dynamicFavour(Handler mHandler,com.timetalent.client.ui.adapter.DynamicAdapter.ViewHolder holder) {
		try {
			service.dynamicFavour();
			mHandler.obtainMessage(0, holder).sendToTarget();
		} catch (BusinessException e) {
			handler.obtainMessage(HANDLER_TOAST, e.getErrorMessage().getMessage()).sendToTarget();
		} catch (Exception e) {
		}
	}
	
	public void dynamicUnFavour(Handler mHandler,com.timetalent.client.ui.adapter.DynamicAdapter.ViewHolder holder) {
		try {
			service.dynamicUnFavour();
			mHandler.obtainMessage(2, holder).sendToTarget();
		} catch (BusinessException e) {
			handler.obtainMessage(HANDLER_TOAST, e.getErrorMessage().getMessage()).sendToTarget();
		} catch (Exception e) {
		}
	}
	
	public void dynamicMy(Handler mHandler,int num) {
		try {
			service.dynamicMy(num);
			mHandler.obtainMessage(0).sendToTarget();
		} catch (BusinessException e) {
			e.printStackTrace();
			mHandler.obtainMessage(1).sendToTarget();
			handler.obtainMessage(HANDLER_TOAST, e.getErrorMessage().getMessage()).sendToTarget();
		} catch (Exception e) {
			e.printStackTrace();
			mHandler.obtainMessage(1).sendToTarget();
		}
	}
	
	public void dynamicWho(Handler mHandler,int num) {
		try {
			service.dynamicWho(num);
			mHandler.obtainMessage(0).sendToTarget();
		} catch (BusinessException e) {
			e.printStackTrace();
			mHandler.obtainMessage(1).sendToTarget();
			handler.obtainMessage(HANDLER_TOAST, e.getErrorMessage().getMessage()).sendToTarget();
		} catch (Exception e) {
			e.printStackTrace();
			mHandler.obtainMessage(1).sendToTarget();
		}
	}
	
	/**
	 * 搜索附近
	  * 方法描述：TODO
	  * @author: Administrator
	  * @time: 2014-11-2 下午12:18:42
	 */
	public void search() {
		try {
			service.search();
		} catch (BusinessException e) {
			e.printStackTrace();
			handler.obtainMessage(HANDLER_TOAST, e.getErrorMessage().getMessage()).sendToTarget();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**附近的人Zone/near***/
	public void near() {
		try {
			service.near();
		} catch (BusinessException e) {
			e.printStackTrace();
			handler.obtainMessage(HANDLER_TOAST, e.getErrorMessage().getMessage()).sendToTarget();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	  * 方法描述：TODO
	  * @author: Administrator
	  * @time: 2014-11-7 上午10:50:55
	 */
	public void userinfo(){
		try {
			service.userinfo();
		} catch (BusinessException e) {
			e.printStackTrace();
			handler.obtainMessage(HANDLER_TOAST, e.getErrorMessage().getMessage()).sendToTarget();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**获取我的好友列表Zone/friend***/
	public void myfriend() {
		try {
			service.myfriend();
		} catch (BusinessException e) {
			e.printStackTrace();
			handler.obtainMessage(HANDLER_TOAST, e.getErrorMessage().getMessage()).sendToTarget();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**获取我的关注列表Zone/following***/
	public void myfollowing() {
		try {
			service.myfollowing();
		} catch (BusinessException e) {
			e.printStackTrace();
			handler.obtainMessage(HANDLER_TOAST, e.getErrorMessage().getMessage()).sendToTarget();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**获取关注我的人列表,即粉丝Zone/followed***/
	public void myfollowed() {
		try {
			service.myfollowed();
		} catch (BusinessException e) {
			e.printStackTrace();
			handler.obtainMessage(HANDLER_TOAST, e.getErrorMessage().getMessage()).sendToTarget();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**获取推荐用户列表Zone/pushuser***/
	public void mypushuser() {
		try {
			service.mypushuser();
		} catch (BusinessException e) {
			e.printStackTrace();
			handler.obtainMessage(HANDLER_TOAST, e.getErrorMessage().getMessage()).sendToTarget();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**获取黑名单列表Zone/black***/
	public void myblack() {
		try {
			service.myblack();
		} catch (BusinessException e) {
			e.printStackTrace();
			handler.obtainMessage(HANDLER_TOAST, e.getErrorMessage().getMessage()).sendToTarget();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**充值订单Wallet/charge_order***/
	public void mycharge_order() {
		try {
			service.mycharge_order();
		} catch (BusinessException e) {
			e.printStackTrace();
			handler.obtainMessage(HANDLER_TOAST, e.getErrorMessage().getMessage()).sendToTarget();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**充值完成Wallet/complete_order***/
	public void mycomplete_order() {
		try {
			service.mycomplete_order();
		} catch (BusinessException e) {
			e.printStackTrace();
			handler.obtainMessage(HANDLER_TOAST, e.getErrorMessage().getMessage()).sendToTarget();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**取消订单Wallet/cancel_order***/
	public void mycancel_order() {
		try {
			service.mycancel_order();
		} catch (BusinessException e) {
			e.printStackTrace();
			handler.obtainMessage(HANDLER_TOAST, e.getErrorMessage().getMessage()).sendToTarget();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**提现Wallet/withdraw***/
	public void mywithdraw() {
		try {
			service.mywithdraw();
			currentActivity.finish();
			handler.obtainMessage(HANDLER_TOAST, "提现成功，预计3个工作日到达指定的账户!").sendToTarget();
		} catch (BusinessException e) {
			e.printStackTrace();
			handler.obtainMessage(HANDLER_TOAST, e.getErrorMessage().getMessage()).sendToTarget();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**提现记录Wallet/withdraw_lists***/
	public void mywithdeaw_lists() {
		try {
			service.mywithdeaw_lists();
		} catch (BusinessException e) {
			e.printStackTrace();
			handler.obtainMessage(HANDLER_TOAST, e.getErrorMessage().getMessage()).sendToTarget();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**星探、粉丝邀约某明星Invite/add***/
	public void myinvite_add() {
		try {
			service.myinvite_add();
		} catch (BusinessException e) {
			e.printStackTrace();
			handler.obtainMessage(HANDLER_TOAST, e.getErrorMessage().getMessage()).sendToTarget();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**我的邀约记录 星探或粉丝邀约明星的记录Invite/appoint***/
	public void myinvite_appoint() {
		try {
			service.myinvite_appoint();
		} catch (BusinessException e) {
			e.printStackTrace();
			handler.obtainMessage(HANDLER_TOAST, e.getErrorMessage().getMessage()).sendToTarget();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**邀约我的记录 明星被邀约的记录Invite/offer***/
	public void myinvite_offer() {
		try {
			service.myinvite_offer();
		} catch (BusinessException e) {
			e.printStackTrace();
			handler.obtainMessage(HANDLER_TOAST, e.getErrorMessage().getMessage()).sendToTarget();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**接受邀约Invite/accept***/
	public void myinvite_accept() {
		try {
			service.myinvite_accept();
		} catch (BusinessException e) {
			e.printStackTrace();
			handler.obtainMessage(HANDLER_TOAST, e.getErrorMessage().getMessage()).sendToTarget();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**支付邀约费用 确定支付邀约费用，发生在发起邀约方Invite/payment***/
	public void myinvite_payment() {
		try {
			service.myinvite_payment();
		} catch (BusinessException e) {
			e.printStackTrace();
			handler.obtainMessage(HANDLER_TOAST, e.getErrorMessage().getMessage()).sendToTarget();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void myapp_version(){
		try {
			service.myapp_version();
		} catch (BusinessException e) {
			e.printStackTrace();
			handler.obtainMessage(HANDLER_TOAST, e.getErrorMessage().getMessage()).sendToTarget();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void mybaseinfo(){
		try {
			service.mybaseinfo();
		} catch (BusinessException e) {
			e.printStackTrace();
			handler.obtainMessage(HANDLER_TOAST, e.getErrorMessage().getMessage()).sendToTarget();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void mybaseinfoupdate(){
		try {
			service.mybaseinfoupdate();
		} catch (BusinessException e) {
			e.printStackTrace();
			handler.obtainMessage(HANDLER_TOAST, e.getErrorMessage().getMessage()).sendToTarget();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	  * 方法描述：TODO
	  * @author: wanghy
	  * @time: 2014-10-23 下午10:54:28
	  */
	public void walletOrder() {
		try {
			service.walletOrder();
		} catch (BusinessException e) {
			handler.obtainMessage(HANDLER_TOAST, e.getErrorMessage().getMessage()).sendToTarget();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	  * 方法描述：TODO
	  * @author: wanghy
	  * @time: 2014-10-23 下午10:54:28
	  */
	public void walletPay() {
		try {
			service.walletPay();
		} catch (BusinessException e) {
			handler.obtainMessage(HANDLER_TOAST, e.getErrorMessage().getMessage()).sendToTarget();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	  * 方法描述：TODO
	  * @author: wanghy
	  * @time: 2014-10-23 下午10:54:28
	  */
	public void walletWithdraw() {
		try {
			service.walletWithdraw();
		} catch (BusinessException e) {
			handler.obtainMessage(HANDLER_TOAST, e.getErrorMessage().getMessage()).sendToTarget();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	  * 方法描述：TODO
	  * @author: wanghy
	  * @time: 2014-10-23 下午10:54:28
	  */
	public void walletWithdrawList() {
		try {
			service.walletWithdrawList();
		} catch (BusinessException e) {
			handler.obtainMessage(HANDLER_TOAST, e.getErrorMessage().getMessage()).sendToTarget();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	  * 方法描述：TODO
	  * @author: wanghy
	  * @time: 2014-10-23 下午10:54:28
	  */
	public void mylocation_update() {
		String lat = "";
		String lng = "";
		LocationManager loctionManager;
		String contextService = Context.LOCATION_SERVICE;
		// 通过系统服务，取得LocationManager对象

		loctionManager = (LocationManager) currentActivity.getSystemService(contextService);

		// /////////////////////////////////
		Criteria criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_FINE);// 高精度
		criteria.setAltitudeRequired(false);// 不要求海拔
		criteria.setBearingRequired(false);// 不要求方位
		criteria.setCostAllowed(true);// 允许有花费
		criteria.setPowerRequirement(Criteria.POWER_LOW);// 低功耗
		// 从可用的位置提供器中，匹配以上标准的最佳提供器
		String provider = loctionManager.getBestProvider(criteria, true);
		if(provider == null){
			lat = "";
			lng = "";
		}else{
			// 获得最后一次变化的位置
			Location location = loctionManager.getLastKnownLocation(provider);
			// //////////////////////////////////
			if (location != null) {
				lat = ""+location.getLatitude();
				lng = ""+location
						.getLongitude();
			}
				else {
					lat = "";
					lng = "";
				}
		}
		context.addBusinessData("my.lat", lat);
		context.addBusinessData("my.lng", lng);
		try {
			service.mylocation_update();
		} catch (BusinessException e) {
			handler.obtainMessage(HANDLER_TOAST, e.getErrorMessage().getMessage()).sendToTarget();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 我的服务
	  * 方法描述：TODO
	  * @author: Administrator
	  * @time: 2014-11-17 下午1:38:22
	 */
	public void myuser_service() {
		try {
			service.myuser_service();
		} catch (BusinessException e) {
			handler.obtainMessage(HANDLER_TOAST, e.getErrorMessage().getMessage()).sendToTarget();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void myuser_addservice() {
		try {
			service.myuser_addservice();
		} catch (BusinessException e) {
			handler.obtainMessage(HANDLER_TOAST, e.getErrorMessage().getMessage()).sendToTarget();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void myuser_loyal() {
		try {
			service.myuser_loyal();
		} catch (BusinessException e) {
			handler.obtainMessage(HANDLER_TOAST, e.getErrorMessage().getMessage()).sendToTarget();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void myuser_loyal_item(List<PicValuePair> picValuePair) {
		try {
			service.myuser_loyal_item(picValuePair);
		} catch (BusinessException e) {
			handler.obtainMessage(HANDLER_TOAST, e.getErrorMessage().getMessage()).sendToTarget();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void moreinfo() {
		try {
			service.moreinfo();
		} catch (BusinessException e) {
			handler.obtainMessage(HANDLER_TOAST, e.getErrorMessage().getMessage()).sendToTarget();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void mydo_social() {
		try {
			service.mydo_social();
		} catch (BusinessException e) {
			handler.obtainMessage(HANDLER_TOAST, e.getErrorMessage().getMessage()).sendToTarget();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void myreport() {
		try {
			service.myreport();
		} catch (BusinessException e) {
			handler.obtainMessage(HANDLER_TOAST, e.getErrorMessage().getMessage()).sendToTarget();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void myphoto() {
		try {
			service.myphoto();
		} catch (BusinessException e) {
			handler.obtainMessage(HANDLER_TOAST, e.getErrorMessage().getMessage()).sendToTarget();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void myphotodel() {
		try {
			service.myphotodel();
		} catch (BusinessException e) {
			handler.obtainMessage(HANDLER_TOAST, e.getErrorMessage().getMessage()).sendToTarget();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void myavatarupdate(List<PicValuePair> picValuePair) {
		try {
			service.myavatarupdate(picValuePair);
		} catch (BusinessException e) {
			handler.obtainMessage(HANDLER_TOAST, e.getErrorMessage().getMessage()).sendToTarget();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void myavatardel() {
		try {
			service.myavatardel();
		} catch (BusinessException e) {
			handler.obtainMessage(HANDLER_TOAST, e.getErrorMessage().getMessage()).sendToTarget();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void dictionary() {
		try {
			service.dictionary();
		} catch (BusinessException e) {
			handler.obtainMessage(HANDLER_TOAST, e.getErrorMessage().getMessage()).sendToTarget();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void myapp_config() {
		try {
			service.myapp_config();
		} catch (BusinessException e) {
			handler.obtainMessage(HANDLER_TOAST, e.getErrorMessage().getMessage()).sendToTarget();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void orderlist() {
		try {
			service.orderlist();
		} catch (BusinessException e) {
			handler.obtainMessage(HANDLER_TOAST, e.getErrorMessage().getMessage()).sendToTarget();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void myservice_list() {
		try {
			service.myservice_list();
		} catch (BusinessException e) {
			handler.obtainMessage(HANDLER_TOAST, e.getErrorMessage().getMessage()).sendToTarget();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void mytasklists() {
		try {
			service.mytasklists();
		} catch (BusinessException e) {
			handler.obtainMessage(HANDLER_TOAST, e.getErrorMessage().getMessage()).sendToTarget();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void myapplytasklists() {
		try {
			service.myapplytasklists();
		} catch (BusinessException e) {
			handler.obtainMessage(HANDLER_TOAST, e.getErrorMessage().getMessage()).sendToTarget();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void myphotoupdate(List<PicValuePair> picValuePair){
		try {
			service.myphotoupdate(picValuePair);
		} catch (BusinessException e) {
			handler.obtainMessage(HANDLER_TOAST, e.getErrorMessage().getMessage()).sendToTarget();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void myuser_delservice(){
		try {
			service.myuser_delservice();
		} catch (BusinessException e) {
			handler.obtainMessage(HANDLER_TOAST, e.getErrorMessage().getMessage()).sendToTarget();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void mymoreinfoupdate(){
		try {
			service.mymoreinfoupdate();
		} catch (BusinessException e) {
			handler.obtainMessage(HANDLER_TOAST, e.getErrorMessage().getMessage()).sendToTarget();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void myworksadd(List<PicValuePair> picValuePair){
		try {
			service.myworksadd(picValuePair);
		} catch (BusinessException e) {
			handler.obtainMessage(HANDLER_TOAST, e.getErrorMessage().getMessage()).sendToTarget();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
