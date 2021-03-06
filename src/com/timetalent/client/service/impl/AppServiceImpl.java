package com.timetalent.client.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.os.Handler;
import android.util.Log;

import com.easemob.EMCallBack;
import com.easemob.EMError;
import com.easemob.chat.EMChatManager;
import com.easemob.chat.EMContactManager;
import com.easemob.chat.EMConversation;
import com.easemob.chat.EMGroupManager;
import com.easemob.exceptions.EaseMobException;
import com.easemob.util.EMLog;
import com.timetalent.client.TimeTalentApplication;
import com.timetalent.client.entities.PicValuePair;
import com.timetalent.client.entities.Register;
import com.timetalent.client.entities.TaskAdd;
import com.timetalent.client.entities.Walletorderpackage;
import com.timetalent.client.entities.json.AppConfigResp;
import com.timetalent.client.entities.json.AppResp;
import com.timetalent.client.entities.json.BaseResp;
import com.timetalent.client.entities.json.BaseinfoResp;
import com.timetalent.client.entities.json.BlackResp;
import com.timetalent.client.entities.json.DictionaryResp;
import com.timetalent.client.entities.json.FansyaoqingResp;
import com.timetalent.client.entities.json.FeedADDResp;
import com.timetalent.client.entities.json.FeedResp;
import com.timetalent.client.entities.json.FollowedResp;
import com.timetalent.client.entities.json.FollowingResp;
import com.timetalent.client.entities.json.FriendResp;
import com.timetalent.client.entities.json.LoginResp;
import com.timetalent.client.entities.json.MyapplytaskResp;
import com.timetalent.client.entities.json.MyphotoResp;
import com.timetalent.client.entities.json.MyserviceResp;
import com.timetalent.client.entities.json.MytasklistResp;
import com.timetalent.client.entities.json.NearResp;
import com.timetalent.client.entities.json.PushuserResp;
import com.timetalent.client.entities.json.RegisterResp;
import com.timetalent.client.entities.json.SearchResp;
import com.timetalent.client.entities.json.ServiceResp;
import com.timetalent.client.entities.json.TaskADDResp;
import com.timetalent.client.entities.json.TaskResp;
import com.timetalent.client.entities.json.TaskShowResp;
import com.timetalent.client.entities.json.UserinfoResp;
import com.timetalent.client.entities.json.WalletorderResp;
import com.timetalent.client.entities.json.YiRenyaoqingResp;
import com.timetalent.client.entities.json.servicelistResp;
import com.timetalent.client.service.AppContext;
import com.timetalent.client.service.AppController;
import com.timetalent.client.service.AppService;
import com.timetalent.client.ui.MainFragmentActivity;
import com.timetalent.client.ui.esaemob.Constant;
import com.timetalent.client.ui.esaemob.User;
import com.timetalent.client.ui.esaemob.UserDao;
import com.timetalent.common.exception.BusinessException;
import com.timetalent.common.exception.ErrorMessage;
import com.timetalent.common.net.Request;
import com.timetalent.common.util.Config;
import com.timetalent.common.util.IntentUtil;
import com.timetalent.common.util.LogUtil;
import com.timetalent.common.util.StringUtil;
import com.timetalent.common.util.ToastUtil;
import com.timetalent.common.util.aes.Md5;

/******************************************
 * 类描述： 业务实现类 类名称：ServiceImpl
 * 
 * @version: 1.0
 * @author: why
 * @time: 2014-2-13 下午2:09:22
 ******************************************/
public class AppServiceImpl implements AppService {
	@SuppressWarnings("unused")
	private String TAG = "AppServiceImpl";
	private AppContext context;
	/**
	 * 类的构造方法 创建一个新的实例 AppServiceImpl.
	 * 
	 * @param
	 * @param context
	 */
	public AppServiceImpl(AppContext context) {
		this.context = context;
	}

	/**
	 * 登录
	 */
	@Override
	public void login(final Handler handler) throws BusinessException {
		String account = (String)context.getBusinessData("user.account");
		String password = (String)context.getBusinessData("user.password");
		Request<LoginResp> request = new Request<LoginResp>();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		
		nameValuePairs.add(new BasicNameValuePair("userlogin", account));
		nameValuePairs.add(new BasicNameValuePair("password", password));
		nameValuePairs.add(new BasicNameValuePair("isdata", "true"));
		request.addParameter(Request.AJAXPARAMS, nameValuePairs);
		request.setUrl(Config.HTTP_USER_LOGIN);
		request.setR_calzz(LoginResp.class);
		final LoginResp resp = TimeTalentApplication.getAppSocket().shortConnect(request);
		if ("1".equals(resp.getStatus())) {
			// 登录环信 处理 
						//调用sdk登陆方法登陆聊天服务器
						final String name = StringUtil.getEsaeUserName(resp.getData().getId());
						final String pwd = Md5.digist(name);
						EMChatManager.getInstance().login(name, pwd, new EMCallBack() {
									
						    @Override
						    public void onSuccess() {
						    	emosOnSuccess(name);
						    	IntentUtil.intent(AppController.getController().getCurrentActivity(), MainFragmentActivity.class);
								handler.obtainMessage(AppController.HANDLER_TOAST, "登陆成功").sendToTarget();

								saveLoginData(resp);
						    }
						    @Override
						    public void onProgress(int progress, String status) {
						    }
						    @Override
						    public void onError(int code, String message) {
						    	LogUtil.Log("EMChatManager", "onError");
						    	if(code == EMError.INVALID_PASSWORD_USERNAME){
						    		try {
										huanxin_reg();
									} catch (BusinessException e) {
										e.printStackTrace();
									}finally{
										EMChatManager.getInstance().login(name, pwd, new EMCallBack() {
										    @Override
										    public void onSuccess() {
										    	emosOnSuccess(name);
										    	saveLoginData(resp);
										    	IntentUtil.intent(AppController.getController().getCurrentActivity(), MainFragmentActivity.class);
												handler.obtainMessage(AppController.HANDLER_TOAST, "登陆成功").sendToTarget();
										    }
										    @Override
										    public void onProgress(int progress, String status) {
										    }
										    @Override
										    public void onError(int code, String message){
										    	LogUtil.Log("EMChatManager", "onError");
										    		handler.obtainMessage(AppController.HANDLER_TOAST, "登录失败").sendToTarget();
										    }
										});
									}
						    	}
						    }
						});
		} else{
			throw new BusinessException(new ErrorMessage(resp.getText()));
		}
	}
	
	/**
	  * 方法描述：TODO
	  * @param resp
	  * @author: why
	  * @time: 2014-12-16 下午6:56:13
	  */
	private void saveLoginData(final LoginResp resp) {
		TimeTalentApplication.getInstance().setLogin(true);
		// 登录成功 存储 账户
		if (resp.getData() != null) {
			TimeTalentApplication.getInstance().saveLoginInfo(resp.getData());
			context.addBusinessData("loginData", resp.getData());
			context.addBusinessData("_session_id", resp.getData().getSession_id());
			context.addBusinessData("Login.type", resp.getData().getType());
			context.addBusinessData("Login.money", resp.getData().getMoney());
			context.addBusinessData("Login.avatar", resp.getData().getAvatar());
			context.addBusinessData("Login.nickname", resp.getData().getNickname());
		}
	}
	
	/**
	  * 方法描述：环信登录成功处理
	  * @param name
	  * @author: why
	  * @time: 2014-12-16 下午6:41:51
	  */
	private void emosOnSuccess(
			final String name) {
		// ** 第一次登录或者之前logout后，加载所有本地群和回话
		// ** manually load all local groups and
		// conversations in case we are auto login
		EMGroupManager.getInstance().loadAllGroups();
		EMChatManager.getInstance().loadAllConversations();
   	LogUtil.Log("EMChatManager", "onSuccess");
   	// 登陆成功，保存用户名密码
		TimeTalentApplication.getInstance().setUserName(name);
		//TimeTalentApplication.getInstance().setPassword(pwd);
		try {
			List<String> usernames = EMContactManager.getInstance().getContactUserNames();
			EMLog.d("roster", "contacts size: " + usernames.size());
			
			Map<String, User> userlist = new HashMap<String, User>();
			for (String username : usernames) {
				User user = new User();
				user.setUsername(username);
			//	setUserHearder(username, user);
				userlist.put(username, user);
			}
			// 添加user"申请与通知"
			User newFriends = new User();
			newFriends.setUsername(Constant.NEW_FRIENDS_USERNAME);
			newFriends.setNick("申请与通知");
			newFriends.setHeader("");
			userlist.put(Constant.NEW_FRIENDS_USERNAME, newFriends);
			
			// 存入内存
			TimeTalentApplication.getInstance().setContactList(userlist);
			// 存入db
			UserDao dao = new UserDao(AppController.getController().getCurrentActivity());
			List<User> users = new ArrayList<User>(userlist.values());
			dao.saveContactList(users);
			Hashtable<String, EMConversation> conversations = EMChatManager.getInstance().getAllConversations();
			
			// 获取群聊列表(群聊里只有groupid和groupname的简单信息),sdk会把群组存入到内存和db中
			EMGroupManager.getInstance().getGroupsFromServer();
		} catch (EaseMobException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Override
	public void huanxin_reg() throws BusinessException {
		Request<BaseResp> request = new Request<BaseResp>();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("_session_id", (String)context.getBusinessData("_session_id")));
		request.addParameter(Request.AJAXPARAMS, nameValuePairs);
		request.setUrl(Config.HTTP_USER_HUANXIN_REG);
		request.setR_calzz(BaseResp.class);
		BaseResp resp = TimeTalentApplication.getAppSocket().shortConnect(request);
		if ("1".equals(resp.getStatus())) {
		} else{
			throw new BusinessException(new ErrorMessage(resp.getText()));
		}
	}
	
	
	
	/* (non-Javadoc)
	 * @see com.timetalent.client.service.AppService#logout()
	 */
	@Override
	public void logout() throws BusinessException {

		Request<BaseResp> request = new Request<BaseResp>();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("_session_id", (String)context.getBusinessData("_session_id")));
		request.addParameter(Request.AJAXPARAMS, nameValuePairs);
		request.setUrl(Config.HTTP_USER_LOGOUT);
		request.setR_calzz(BaseResp.class);
		BaseResp resp = TimeTalentApplication.getAppSocket().shortConnect(request);
		if ("1".equals(resp.getStatus())) {
		} else{
			throw new BusinessException(new ErrorMessage(resp.getText()));
		}
	}
	
	
	/* (non-Javadoc)
	 * @see com.timetalent.client.service.AppService#resetPwd()
	 */
	@Override
	public void resetPwd() throws BusinessException {
		String phone = context.getStringData("phone");
		String assess = context.getStringData("code");
		String new_password = context.getStringData("find.pwd");
		Request<BaseResp> request = new Request<BaseResp>();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("phone", phone));
		nameValuePairs.add(new BasicNameValuePair("assess", assess));
		nameValuePairs.add(new BasicNameValuePair("new_password", new_password));
		request.addParameter(Request.AJAXPARAMS, nameValuePairs);
		request.setUrl(Config.HTTP_USER_RESET_PWD);
		request.setR_calzz(BaseResp.class);
		BaseResp resp = TimeTalentApplication.getAppSocket().shortConnect(request);
		if ("1".equals(resp.getStatus())){
		} else{
			throw new BusinessException(new ErrorMessage(resp.getText()));
		}
	}
	
	@Override
	public void code() throws BusinessException {
		String phone = context.getStringData("phone");
		Request<BaseResp> request = new Request<BaseResp>();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("phone", phone));
		request.addParameter(Request.AJAXPARAMS, nameValuePairs);
		request.setUrl(Config.HTTP_USER_PHONE_CODE);
		request.setR_calzz(BaseResp.class);
		BaseResp resp = TimeTalentApplication.getAppSocket().shortConnect(request);
		if ("1".equals(resp.getStatus())) {
		} else{
			throw new BusinessException(new ErrorMessage(resp.getText()));
		}
	}
	
	
	@Override
	public void validationCode() throws BusinessException {
		String phone = context.getStringData("phone");
		String code = context.getStringData("code");
		Request<BaseResp> request = new Request<BaseResp>();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("phone", phone));
		nameValuePairs.add(new BasicNameValuePair("assess", code));
		request.addParameter(Request.AJAXPARAMS, nameValuePairs);
		request.setUrl(Config.HTTP_USER_CODE_VERIFY);
		request.setR_calzz(BaseResp.class);
		BaseResp resp = TimeTalentApplication.getAppSocket().shortConnect(request);
		if ("1".equals(resp.getStatus())) {
		} else{
			throw new BusinessException(new ErrorMessage(resp.getText()));
		}
		
	}
	
	
	@Override
	public void register() throws BusinessException {
		Register register = (Register)context.getBusinessData("Register.register");
		Request<RegisterResp> request = new Request<RegisterResp>();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		
		nameValuePairs.add(new BasicNameValuePair("nickname", register.getUsername()));
		nameValuePairs.add(new BasicNameValuePair("phone", register.getPhone()));
		nameValuePairs.add(new BasicNameValuePair("password", register.getPassword()));
		nameValuePairs.add(new BasicNameValuePair("birthday", StringUtil.transformDate(register.getBirthday())+""));
		nameValuePairs.add(new BasicNameValuePair("sex", register.getSex()));
		nameValuePairs.add(new BasicNameValuePair("type", register.getType()));
		request.addParameter(Request.AJAXPARAMS, nameValuePairs);
		request.setUrl(Config.HTTP_USER_REGISTER);
		request.setR_calzz(RegisterResp.class);
		RegisterResp resp = TimeTalentApplication.getAppSocket().shortConnect(request);
		if ("1".equals(resp.getStatus())) {
			context.addBusinessData("Register.user_id", resp.getData().getUser_id());
		} else{
			throw new BusinessException(new ErrorMessage(resp.getText()));
		}
	
	}
	
	
	@Override
	public void register_avatar() throws BusinessException {
		String  user_id = context.getStringData("Register.user_id");
		Request<BaseResp> request = new Request<BaseResp>();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("user_id",user_id));
		request.addParameter(Request.AJAXPARAMS, nameValuePairs);
		List<PicValuePair> picValuePair =  (List<PicValuePair>)context.getBusinessData("Register.avatar");
		request.addParameter(Request.PICTURE, picValuePair);
		request.setUrl(Config.HTTP_USER_REGISTER_AVATAR);
		request.setR_calzz(BaseResp.class);
		BaseResp resp = TimeTalentApplication.getAppSocket().imageLoad(request);
		if ("1".equals(resp.getStatus())) {
		} else{
			throw new BusinessException(new ErrorMessage(resp.getText()));
		}
	}

	
	@Override
	public void chanceAdd() throws BusinessException {
		String _session_id = context.getStringData("_session_id");
		TaskAdd task = (TaskAdd)context.getBusinessData("Task_ADD");
		Request<TaskADDResp> request = new Request<TaskADDResp>();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("_session_id", _session_id));
		nameValuePairs.add(new BasicNameValuePair("title", task.getTitle()));
		nameValuePairs.add(new BasicNameValuePair("place", task.getPlace()));
		nameValuePairs.add(new BasicNameValuePair("job_lists_json", task.getJob_lists_json()));
		nameValuePairs.add(new BasicNameValuePair("cutoff_date", task.getCutoff_date()));
		nameValuePairs.add(new BasicNameValuePair("description", task.getDescription()));
		request.addParameter(Request.AJAXPARAMS, nameValuePairs);
		request.setUrl(Config.HTTP_USER_CHANCE_ADD);
		request.setR_calzz(TaskADDResp.class);  
		TaskADDResp resp = TimeTalentApplication.getAppSocket().shortConnect(request);
		if ("1".equals(resp.getStatus())) {
			context.addBusinessData("Task_ADD_id", resp.getData().getTask_id());
		} else{
			throw new BusinessException(new ErrorMessage(resp.getText()));
		}
	}

	
	@Override
	public void chanceLists(int num) throws BusinessException {
		String _session_id = context.getStringData("_session_id");
		String order = context.getStringData("chance_order");
		String search = context.getStringData("chance_search");
		Request<TaskResp> request = new Request<TaskResp>();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("_session_id",_session_id));
		nameValuePairs.add(new BasicNameValuePair("page",num+""));
		nameValuePairs.add(new BasicNameValuePair("page_per","10"));
		nameValuePairs.add(new BasicNameValuePair("order",order));
		nameValuePairs.add(new BasicNameValuePair("search",search));
		request.addParameter(Request.AJAXPARAMS, nameValuePairs);
		request.setUrl(Config.HTTP_USER_CHANCE_LIST);
		request.setR_calzz(TaskResp.class);
		TaskResp resp = TimeTalentApplication.getAppSocket().shortConnect(request);
		if ("1".equals(resp.getStatus())) {
			context.addBusinessData("Task_lists_data", resp.getData());
		} else{
			throw new BusinessException(new ErrorMessage(resp.getText()));
		}
	
	}
	
	@Override
	public void chanceDetails() throws BusinessException {
		String _session_id = context.getStringData("_session_id");
		String chance_detail_id = context.getStringData("chance_detail_id");
		Request<TaskShowResp> request = new Request<TaskShowResp>();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("_session_id", _session_id));
		nameValuePairs.add(new BasicNameValuePair("id", chance_detail_id));
		request.addParameter(Request.AJAXPARAMS, nameValuePairs);
		request.setUrl(Config.HTTP_USER_CHANCE_DETAIL);
		request.setR_calzz(TaskShowResp.class);
		TaskShowResp resp = TimeTalentApplication.getAppSocket().shortConnect(request);
		if ("1".equals(resp.getStatus())) {
			context.addBusinessData("Task_lists_detail", resp.getData());
		} else{
			throw new BusinessException(new ErrorMessage(resp.getText()));
		}
	}

	
	@Override
	public void chanceApply() throws BusinessException {
		String _session_id = context.getStringData("_session_id");
		String chance_Job_id = context.getStringData("Chance_Job_id");
		String chance_Task_id = context.getStringData("Chance_Task_id");
		
		Request<BaseResp> request = new Request<BaseResp>();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("_session_id",_session_id));
		nameValuePairs.add(new BasicNameValuePair("task_id",chance_Task_id));
		nameValuePairs.add(new BasicNameValuePair("task_job_id",chance_Job_id));
		request.addParameter(Request.AJAXPARAMS, nameValuePairs);
		request.setUrl(Config.HTTP_USER_CHANCE_APPLY);
		request.setR_calzz(BaseResp.class);
		BaseResp resp = TimeTalentApplication.getAppSocket().shortConnect(request);
		if ("1".equals(resp.getStatus())) {
			
		} else{
			throw new BusinessException(new ErrorMessage(resp.getText()));
		}
	
	}

	
	@Override
	public void dynamicIndex(int pageNum) throws BusinessException {
		String _session_id = context.getStringData("_session_id");
		Request<FeedResp> request = new Request<FeedResp>();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("_session_id", _session_id));
		nameValuePairs.add(new BasicNameValuePair("page", pageNum+""));
		nameValuePairs.add(new BasicNameValuePair("page_per", "10"));
		request.addParameter(Request.AJAXPARAMS, nameValuePairs);
		request.setUrl(Config.HTTP_USER_DYNAMIC_INDEX);
		request.setR_calzz(FeedResp.class);
		FeedResp resp = TimeTalentApplication.getAppSocket().shortConnect(request);
		if ("1".equals(resp.getStatus())) {
			context.addBusinessData("Dynamic_Data", resp.getData());
		} else{
			throw new BusinessException(new ErrorMessage(resp.getText()));
		}
	}
	
	
	/* (non-Javadoc)
	 * @see com.timetalent.client.service.AppService#dynamicMy()
	 */
	@Override
	public void dynamicMy(int pageNum) throws BusinessException {
		String _session_id = context.getStringData("_session_id");
		Request<FeedResp> request = new Request<FeedResp>();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("_session_id", _session_id));
		nameValuePairs.add(new BasicNameValuePair("page", pageNum+""));
		nameValuePairs.add(new BasicNameValuePair("page_per", "10"));
		request.addParameter(Request.AJAXPARAMS, nameValuePairs);
		request.setUrl(Config.HTTP_USER_DYNAMIC_MY);
		request.setR_calzz(FeedResp.class);
		FeedResp resp = TimeTalentApplication.getAppSocket().shortConnect(request);
		if ("1".equals(resp.getStatus())) {
			context.addBusinessData("Dynamic_My_Data", resp.getData());
		} else{
			throw new BusinessException(new ErrorMessage(resp.getText()));
		}
	}

	
	/* (non-Javadoc)
	 * @see com.timetalent.client.service.AppService#dynamicWho()
	 */
	@Override
	public void dynamicWho(int pageNum) throws BusinessException {

		String _session_id = context.getStringData("_session_id");
		String other_id = context.getStringData("other_id");
		Request<FeedResp> request = new Request<FeedResp>();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("_session_id", _session_id));
		nameValuePairs.add(new BasicNameValuePair("page", pageNum+""));
		nameValuePairs.add(new BasicNameValuePair("page_per", "10"));
		nameValuePairs.add(new BasicNameValuePair("user_id", other_id));
		request.addParameter(Request.AJAXPARAMS, nameValuePairs);
		request.setUrl(Config.HTTP_USER_DYNAMIC_WHO);
		request.setR_calzz(FeedResp.class);
		FeedResp resp = TimeTalentApplication.getAppSocket().shortConnect(request);
		if ("1".equals(resp.getStatus())) {
			context.addBusinessData("Dynamic_Other_Data", resp.getData());
		} else{
			throw new BusinessException(new ErrorMessage(resp.getText()));
		}
	}
	@Override
	public void dynamicAdd() throws BusinessException {
		String _session_id = context.getStringData("_session_id");
		String dynamic_add = context.getStringData("dynamic_add");
		Request<FeedADDResp> request = new Request<FeedADDResp>();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("_session_id", _session_id));
		nameValuePairs.add(new BasicNameValuePair("contents", dynamic_add));
		request.addParameter(Request.AJAXPARAMS, nameValuePairs);
		request.setUrl(Config.HTTP_USER_DYNAMIC_ADD);
		request.setR_calzz(FeedADDResp.class);
		FeedADDResp resp = TimeTalentApplication.getAppSocket().shortConnect(request);
		if ("1".equals(resp.getStatus())) {
			context.addBusinessData("Dynamic_ADD_Feed_id", resp.getData().getFeed_id());
		} else{
			throw new BusinessException(new ErrorMessage(resp.getText()));
		}
	}
	
	@Override
	public void dynamicAdd_pic(List<PicValuePair> picValuePair) throws BusinessException {
		String _session_id = context.getStringData("_session_id");
		String feed_add = context.getStringData("Dynamic_ADD_Feed_id");
		Request<BaseResp> request = new Request<BaseResp>();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("_session_id",_session_id));
		nameValuePairs.add(new BasicNameValuePair("feed_id",feed_add));
		request.addParameter(Request.AJAXPARAMS, nameValuePairs);
		request.addParameter(Request.PICTURE, picValuePair);
		request.setUrl(Config.HTTP_USER_DYNAMIC_ADD_PIC);
		request.setR_calzz(BaseResp.class);
		BaseResp resp = TimeTalentApplication.getAppSocket().imageLoad(request);
		if ("1".equals(resp.getStatus())) {
		} else{
			throw new BusinessException(new ErrorMessage(resp.getText()));
		}
	
	}
	
	/* (non-Javadoc)
	 * @see com.timetalent.client.service.AppService#dynamicRepaly()
	 */
	@Override
	public void dynamicRepaly() throws BusinessException {
		String _session_id = context.getStringData("_session_id");
		String feed_id = context.getStringData("dynamic_feed_id");
		String contents = context.getStringData("dynamic_feed_replay_message");

		Request<BaseResp> request = new Request<BaseResp>();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("_session_id", _session_id));
		nameValuePairs.add(new BasicNameValuePair("feed_id", feed_id));
		nameValuePairs.add(new BasicNameValuePair("contents", contents));
		request.addParameter(Request.AJAXPARAMS, nameValuePairs);
		request.setUrl(Config.HTTP_USER_DYNAMIC_REPALY);
		request.setR_calzz(BaseResp.class);
		BaseResp resp = TimeTalentApplication.getAppSocket().shortConnect(request);
		if ("1".equals(resp.getStatus())) {
			
		} else{
			throw new BusinessException(new ErrorMessage(resp.getText()));
		}
	
			
			
	}

	
	/* (non-Javadoc)
	 * @see com.timetalent.client.service.AppService#dynamicFavour()
	 */
	@Override
	public void dynamicFavour() throws BusinessException {

		String _session_id = context.getStringData("_session_id");
		String dynamic_feed_id = context.getStringData("dynamic_feed_id");
		Request<BaseResp> request = new Request<BaseResp>();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("_session_id", _session_id));
		nameValuePairs.add(new BasicNameValuePair("feed_id", dynamic_feed_id));
		request.addParameter(Request.AJAXPARAMS, nameValuePairs);
		request.setUrl(Config.HTTP_USER_DYNAMIC_FAVOUR);
		request.setR_calzz(BaseResp.class);
		BaseResp resp = TimeTalentApplication.getAppSocket().shortConnect(request);
		if ("1".equals(resp.getStatus())) {
			
		} else{
			throw new BusinessException(new ErrorMessage(resp.getText()));
		}
	}
	
	/* (non-Javadoc)
	 * @see com.timetalent.client.service.AppService#dynamicFavour()
	 */
	@Override
	public void dynamicUnFavour() throws BusinessException {

		String _session_id = context.getStringData("_session_id");
		String dynamic_feed_id = context.getStringData("dynamic_feed_id");
		Request<BaseResp> request = new Request<BaseResp>();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("_session_id", _session_id));
		nameValuePairs.add(new BasicNameValuePair("feed_id", dynamic_feed_id));
		request.addParameter(Request.AJAXPARAMS, nameValuePairs);
		request.setUrl(Config.HTTP_USER_DYNAMIC_UNFAVOUR);
		request.setR_calzz(BaseResp.class);
		BaseResp resp = TimeTalentApplication.getAppSocket().shortConnect(request);
		if ("1".equals(resp.getStatus())) {
			
		} else{
			throw new BusinessException(new ErrorMessage(resp.getText()));
		}
	}

	
	/* (non-Javadoc)
	 * @see com.timetalent.client.service.AppService#search()
	 */
	@Override
	public void search() throws BusinessException {


		Request<SearchResp> request = new Request<SearchResp>();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("_session_id", (String)context.getBusinessData("_session_id")));
		nameValuePairs.add(new BasicNameValuePair("search", (String)context.getBusinessData("search.search")));
		
		request.addParameter(Request.AJAXPARAMS, nameValuePairs);
		request.setUrl(Config.HTTP_NEAR_ZONE_SEARCH);
		request.setR_calzz(SearchResp.class);
		SearchResp resp = TimeTalentApplication.getAppSocket().shortConnect(request);
		if ("1".equals(resp.getStatus())) {
			if (resp.getData() != null) {
				context.addBusinessData("SearchData", resp.getData());
			}
		} else{
			throw new BusinessException(new ErrorMessage(resp.getText()));
		}
	}

	
	/* (non-Javadoc)
	 * @see com.timetalent.client.service.AppService#near()
	 */
	@Override
	public void near() throws BusinessException {

		String search = (String)context.getBusinessData("near.search");
		String lat = (String)context.getBusinessData("near.lat");
		String lng = (String)context.getBusinessData("near.lng");
		String sex = (String)context.getBusinessData("near.sex");
		String age_min = (String)context.getBusinessData("near.age_min");
		String age_max = (String)context.getBusinessData("near.age_max");
		String type = (String)context.getBusinessData("near.type");
		String major = (String)context.getBusinessData("near.major");
		
		Request<NearResp> request = new Request<NearResp>();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("_session_id", (String)context.getBusinessData("_session_id")));
		nameValuePairs.add(new BasicNameValuePair("page", "1"));
		nameValuePairs.add(new BasicNameValuePair("page_per", "100"));
		nameValuePairs.add(new BasicNameValuePair("search", search));
		nameValuePairs.add(new BasicNameValuePair("lat", lat));
		nameValuePairs.add(new BasicNameValuePair("lng", lng));
		nameValuePairs.add(new BasicNameValuePair("sex", sex));
		nameValuePairs.add(new BasicNameValuePair("age_min", age_min));
		nameValuePairs.add(new BasicNameValuePair("age_max", age_max));
		nameValuePairs.add(new BasicNameValuePair("type", type));
		nameValuePairs.add(new BasicNameValuePair("major", major));
		request.addParameter(Request.AJAXPARAMS, nameValuePairs);
		request.setUrl(Config.HTTP_NEAR_ZONE_NEAR);
		request.setR_calzz(NearResp.class);
		NearResp resp = TimeTalentApplication.getAppSocket().shortConnect(request);
		if ("1".equals(resp.getStatus())) {
			if (resp.getData() != null) {
				context.addBusinessData("NearData", resp.getData());
			}
		} else{
			throw new BusinessException(new ErrorMessage(resp.getText()));
		}
	
			
			
	}
	/* (non-Javadoc)
	 * @see com.timetalent.client.service.AppService#userinfo()
	 */
	@Override
	public void userinfo() throws BusinessException {

		String user_id = (String)context.getBusinessData("near.user_id");
		Request<UserinfoResp> request = new Request<UserinfoResp>();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("_session_id", (String)context.getBusinessData("_session_id")));
		nameValuePairs.add(new BasicNameValuePair("user_id", user_id));
		request.addParameter(Request.AJAXPARAMS, nameValuePairs);
		request.setUrl(Config.HTTP_NEAR_ZONE_USERINFO);
		request.setR_calzz(UserinfoResp.class);
		UserinfoResp resp = TimeTalentApplication.getAppSocket().shortConnect(request);
		if ("1".equals(resp.getStatus())) {
			if (resp.getData() != null) {
				context.addBusinessData("UserinfoData", resp.getData());
			}
		} else{
			throw new BusinessException(new ErrorMessage(resp.getText()));
		}
	
			
			
	}
	
	/* (non-Javadoc)
	 * @see com.timetalent.client.service.AppService#myfriend()
	 */
	@Override
	public void myfriend() throws BusinessException {


		Request<FriendResp> request = new Request<FriendResp>();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("_session_id", (String)context.getBusinessData("_session_id")));
		request.addParameter(Request.AJAXPARAMS, nameValuePairs);
		request.setUrl(Config.HTTP_MY_ZONE_FRIEND);
		request.setR_calzz(FriendResp.class);
		FriendResp resp = TimeTalentApplication.getAppSocket().shortConnect(request);
		if ("1".equals(resp.getStatus())) {
			if (resp.getData() != null) {
				context.addBusinessData("FriendData", resp.getData());
			}
		} else{
//			throw new BusinessException(new ErrorMessage(resp.getText()));
		}
	
			
			
	}

	
	/* (non-Javadoc)
	 * @see com.timetalent.client.service.AppService#myfollowing()
	 */
	@Override
	public void myfollowing() throws BusinessException {


		Request<FollowingResp> request = new Request<FollowingResp>();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("_session_id", (String)context.getBusinessData("_session_id")));
		request.addParameter(Request.AJAXPARAMS, nameValuePairs);
		request.setUrl(Config.HTTP_MY_ZONE_FOLLOWING);
		request.setR_calzz(FollowingResp.class);
		FollowingResp resp = TimeTalentApplication.getAppSocket().shortConnect(request);
		if ("1".equals(resp.getStatus())) {
			if (resp.getData() != null) {
				context.addBusinessData("FollowingData", resp.getData());
			}
		} else{
//			throw new BusinessException(new ErrorMessage(resp.getText()));
		}
	
			
			
	}

	
	/* (non-Javadoc)
	 * @see com.timetalent.client.service.AppService#myfollowed()
	 */
	@Override
	public void myfollowed() throws BusinessException {


		Request<FollowedResp> request = new Request<FollowedResp>();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("_session_id", (String)context.getBusinessData("_session_id")));
		request.addParameter(Request.AJAXPARAMS, nameValuePairs);
		request.setUrl(Config.HTTP_MY_ZONE_FOLLOWED);
		request.setR_calzz(FollowedResp.class);
		FollowedResp resp = TimeTalentApplication.getAppSocket().shortConnect(request);
		if ("1".equals(resp.getStatus())) {
			if (resp.getData() != null) {
				context.addBusinessData("FollowedData", resp.getData());
			}
		} else{
//			throw new BusinessException(new ErrorMessage(resp.getText()));
		}
	
			
			
	}

	
	/* (non-Javadoc)
	 * @see com.timetalent.client.service.AppService#mypushuser()
	 */
	@Override
	public void mypushuser() throws BusinessException {


		Request<PushuserResp> request = new Request<PushuserResp>();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("_session_id", (String)context.getBusinessData("_session_id")));
		request.addParameter(Request.AJAXPARAMS, nameValuePairs);
		request.setUrl(Config.HTTP_MY_ZONE_PUSHUSER);
		request.setR_calzz(PushuserResp.class);
		PushuserResp resp = TimeTalentApplication.getAppSocket().shortConnect(request);
		if ("1".equals(resp.getStatus())) {
			if (resp.getData() != null) {
				context.addBusinessData("PushuserData", resp.getData());
			}
		} else{
//			throw new BusinessException(new ErrorMessage(resp.getText()));
		}
	}

	
	/* (non-Javadoc)
	 * @see com.timetalent.client.service.AppService#myblack()
	 */
	@Override
	public void myblack() throws BusinessException {


		Request<BlackResp> request = new Request<BlackResp>();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("_session_id", (String)context.getBusinessData("_session_id")));
		request.addParameter(Request.AJAXPARAMS, nameValuePairs);
		request.setUrl(Config.HTTP_MY_ZONE_BLACK);
		request.setR_calzz(BlackResp.class);
		BlackResp resp = TimeTalentApplication.getAppSocket().shortConnect(request);
		if ("1".equals(resp.getStatus())) {
			if (resp.getData() != null) {
				context.addBusinessData("BlackData", resp.getData());
			}
		} else{
			throw new BusinessException(new ErrorMessage(resp.getText()));
		}
	}

	
	/* (non-Javadoc)
	 * @see com.timetalent.client.service.AppService#mycharge_order()
	 */
	@Override
	public void mycharge_order() throws BusinessException {


		Request<WalletorderResp> request = new Request<WalletorderResp>();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("_session_id", (String)context.getBusinessData("_session_id")));
		nameValuePairs.add(new BasicNameValuePair("money", (String)context.getBusinessData("wallet.money")));
		nameValuePairs.add(new BasicNameValuePair("type", (String)context.getBusinessData("wallet.type")));
		request.addParameter(Request.AJAXPARAMS, nameValuePairs);
		request.setUrl(Config.HTTP_MY_WALLET_CHARGEORDER);
		request.setR_calzz(WalletorderResp.class);
		WalletorderResp resp = TimeTalentApplication.getAppSocket().shortConnect(request);
		if ("1".equals(resp.getStatus())) {
			if (resp.getData() != null) {
				context.addBusinessData("orderData", resp.getData());
			}
		} else{
			throw new BusinessException(new ErrorMessage(resp.getText()));
		}
	
			
			
	}

	
	/* (non-Javadoc)
	 * @see com.timetalent.client.service.AppService#mycomplete_order()
	 */
	@Override
	public void mycomplete_order() throws BusinessException {


		Request<BaseResp> request = new Request<BaseResp>();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("_session_id", (String)context.getBusinessData("_session_id")));
		nameValuePairs.add(new BasicNameValuePair("id", ((Walletorderpackage)context.getBusinessData("orderData")).getId()+""));
		request.addParameter(Request.AJAXPARAMS, nameValuePairs);
		request.setUrl(Config.HTTP_MY_WALLET_COMPLETEORDER);
		request.setR_calzz(BaseResp.class);
		BaseResp resp = TimeTalentApplication.getAppSocket().shortConnect(request);
		if ("1".equals(resp.getStatus())) {
			ToastUtil.showToast(AppController.getController().getCurrentActivity(), resp.getText(), 1000);
		} else{
			throw new BusinessException(new ErrorMessage(resp.getText()));
		}
	
			
			
	}

	
	/* (non-Javadoc)
	 * @see com.timetalent.client.service.AppService#mycancel_order()
	 */
	@Override
	public void mycancel_order() throws BusinessException {


		Request<BaseResp> request = new Request<BaseResp>();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("_session_id", (String)context.getBusinessData("_session_id")));
		request.addParameter(Request.AJAXPARAMS, nameValuePairs);
		request.setUrl(Config.HTTP_MY_WALLET_CANCELORDER);
		request.setR_calzz(BaseResp.class);
		BaseResp resp = TimeTalentApplication.getAppSocket().shortConnect(request);
		if ("1".equals(resp.getStatus())) {
			ToastUtil.showToast(AppController.getController().getCurrentActivity(), resp.getText(), 1000);
		} else{
			throw new BusinessException(new ErrorMessage(resp.getText()));
		}
	
			
			
	}

	
	/* (non-Javadoc)
	 * @see com.timetalent.client.service.AppService#mywithdraw()
	 */
	@Override
	public void mywithdraw() throws BusinessException {


		Request<BaseResp> request = new Request<BaseResp>();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("_session_id", (String)context.getBusinessData("_session_id")));
		nameValuePairs.add(new BasicNameValuePair("money", (String)context.getBusinessData("tixian.money")));
		nameValuePairs.add(new BasicNameValuePair("account_num", (String)context.getBusinessData("tixian.account_num")));
		nameValuePairs.add(new BasicNameValuePair("account_name", (String)context.getBusinessData("tixian.account_name")));
		nameValuePairs.add(new BasicNameValuePair("type", (String)context.getBusinessData("tixian.type")));
		nameValuePairs.add(new BasicNameValuePair("account_vender", (String)context.getBusinessData("tixian.account_vender")));
		request.addParameter(Request.AJAXPARAMS, nameValuePairs);
		request.setUrl(Config.HTTP_MY_WALLET_WITHDRAW);
		request.setR_calzz(BaseResp.class);
		BaseResp resp = TimeTalentApplication.getAppSocket().shortConnect(request);
		if ("1".equals(resp.getStatus())) {
			
		} else{
			throw new BusinessException(new ErrorMessage(resp.getText()));
		}
	
			
			
	}

	
	/* (non-Javadoc)
	 * @see com.timetalent.client.service.AppService#mywithdeaw_lists()
	 */
	@Override
	public void mywithdeaw_lists() throws BusinessException {


		Request<BaseResp> request = new Request<BaseResp>();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("_session_id", (String)context.getBusinessData("_session_id")));
		request.addParameter(Request.AJAXPARAMS, nameValuePairs);
		request.setUrl(Config.HTTP_MY_WALLET_WITHDRAWLISTS);
		request.setR_calzz(BaseResp.class);
		BaseResp resp = TimeTalentApplication.getAppSocket().shortConnect(request);
		if ("1".equals(resp.getStatus())) {
			
		} else{
			throw new BusinessException(new ErrorMessage(resp.getText()));
		}
	
			
			
	}

	
	/* (non-Javadoc)
	 * @see com.timetalent.client.service.AppService#myinvite_add()
	 */
	@Override
	public void myinvite_add() throws BusinessException {


		Request<BaseResp> request = new Request<BaseResp>();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("_session_id", (String)context.getBusinessData("_session_id")));
		request.addParameter(Request.AJAXPARAMS, nameValuePairs);
		request.setUrl(Config.HTTP_MY_INVITE_ADD);
		request.setR_calzz(BaseResp.class);
		BaseResp resp = TimeTalentApplication.getAppSocket().shortConnect(request);
		if ("1".equals(resp.getStatus())) {
			
		} else{
			throw new BusinessException(new ErrorMessage(resp.getText()));
		}
	
			
			
	}

	
	/* (non-Javadoc)
	 * @see com.timetalent.client.service.AppService#myinvite_appoint()
	 */
	@Override
	public void myinvite_appoint() throws BusinessException {


		Request<FansyaoqingResp> request = new Request<FansyaoqingResp>();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("_session_id", (String)context.getBusinessData("_session_id")));
		request.addParameter(Request.AJAXPARAMS, nameValuePairs);
		request.setUrl(Config.HTTP_MY_INVITE_APPOINT);
		request.setR_calzz(FansyaoqingResp.class);
		FansyaoqingResp resp = TimeTalentApplication.getAppSocket().shortConnect(request);
		if ("1".equals(resp.getStatus())) {
			if(resp.getData() != null){
				context.addBusinessData("FansyaoqingData", resp.getData());
			}
		} else{
//			throw new BusinessException(new ErrorMessage(resp.getText()));
		}
	
			
			
	}

	
	/* (non-Javadoc)
	 * @see com.timetalent.client.service.AppService#myinvite_offer()
	 */
	@Override
	public void myinvite_offer() throws BusinessException {


		Request<YiRenyaoqingResp> request = new Request<YiRenyaoqingResp>();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("_session_id", (String)context.getBusinessData("_session_id")));
		nameValuePairs.add(new BasicNameValuePair("page", "1"));
		nameValuePairs.add(new BasicNameValuePair("page_per", "1000"));
		request.addParameter(Request.AJAXPARAMS, nameValuePairs);
		request.setUrl(Config.HTTP_MY_INVITE_OFFER);
		request.setR_calzz(YiRenyaoqingResp.class);
		YiRenyaoqingResp resp = TimeTalentApplication.getAppSocket().shortConnect(request);
		if ("1".equals(resp.getStatus())) {
			if(resp.getData() != null){
				context.addBusinessData("YirenyaoqingData", resp.getData());
			}
			
			} else{
//			throw new BusinessException(new ErrorMessage(resp.getText()));
		}
	
			
			
	}

	
	/* (non-Javadoc)
	 * @see com.timetalent.client.service.AppService#myinvite_accept()
	 */
	@Override
	public void myinvite_accept() throws BusinessException {


		Request<BaseResp> request = new Request<BaseResp>();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("_session_id", (String)context.getBusinessData("_session_id")));
		request.addParameter(Request.AJAXPARAMS, nameValuePairs);
		request.setUrl(Config.HTTP_MY_INVITE_ACCEPT);
		request.setR_calzz(BaseResp.class);
		BaseResp resp = TimeTalentApplication.getAppSocket().shortConnect(request);
		if ("1".equals(resp.getStatus())) {
			
		} else{
			throw new BusinessException(new ErrorMessage(resp.getText()));
		}
	
			
			
	}

	
	/* (non-Javadoc)
	 * @see com.timetalent.client.service.AppService#myinvite_payment()
	 */
	@Override
	public void myinvite_payment() throws BusinessException {


		Request<BaseResp> request = new Request<BaseResp>();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("_session_id", (String)context.getBusinessData("_session_id")));
		request.addParameter(Request.AJAXPARAMS, nameValuePairs);
		request.setUrl(Config.HTTP_MY_INVITE_PAYMENT);
		request.setR_calzz(BaseResp.class);
		BaseResp resp = TimeTalentApplication.getAppSocket().shortConnect(request);
		if ("1".equals(resp.getStatus())) {
			
		} else{
			throw new BusinessException(new ErrorMessage(resp.getText()));
		}
	
			
			
	}

	
	/* (non-Javadoc)
	 * @see com.timetalent.client.service.AppService#myapp_version()
	 */
	@Override
	public void myapp_version() throws BusinessException {
		Request<AppResp> request = new Request<AppResp>();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		Log.i("no", ""+TimeTalentApplication.getInstance().curVersionName);
		Log.i("system", TimeTalentApplication.getInstance().systemVersion+"");
		nameValuePairs.add(new BasicNameValuePair("number",""+TimeTalentApplication.getInstance().curVersionName));
		nameValuePairs.add(new BasicNameValuePair("system", "ANDROID"));
		request.addParameter(Request.AJAXPARAMS, nameValuePairs);
		request.setUrl(Config.HTTP_MY_APP_VERSION);
		request.setR_calzz(AppResp.class);
		AppResp resp = TimeTalentApplication.getAppSocket().shortConnect(request);
		if ("1".equals(resp.getStatus())) {
			if(resp.getData() != null){
				context.addBusinessData("AppData", resp.getData());
			}
			
			} else{
			throw new BusinessException(new ErrorMessage(resp.getText()));
		}
	}

	
	/* (non-Javadoc)
	 * @see com.timetalent.client.service.AppService#mybaseinfo()
	 */
	@Override
	public void mybaseinfo() throws BusinessException {
		Request<BaseinfoResp> request = new Request<BaseinfoResp>();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("_session_id", (String)context.getStringData("_session_id")));
		
		request.addParameter(Request.AJAXPARAMS, nameValuePairs);
		request.setUrl(Config.HTTP_MY_BASEINFO);
		request.setR_calzz(BaseinfoResp.class);
		BaseinfoResp resp = TimeTalentApplication.getAppSocket().shortConnect(request);
		if ("1".equals(resp.getStatus())) {
			if (resp.getData() != null) {
				context.addBusinessData("BaseinfoData", resp.getData());
			}
		} else{
			throw new BusinessException(new ErrorMessage(resp.getText()));
		}
	
			
			
	}

	
	/* (non-Javadoc)
	 * @see com.timetalent.client.service.AppService#mybaseinfoupdate()
	 */
	@Override
	public void mybaseinfoupdate() throws BusinessException {
		Request<BaseResp> request = new Request<BaseResp>();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("_session_id", (String)context.getStringData("_session_id")));
//		nameValuePairs.add(new BasicNameValuePair("username", (String)context.getStringData("bianji.username")));
//		nameValuePairs.add(new BasicNameValuePair("phone", (String)context.getStringData("bianji.phone")));
//		nameValuePairs.add(new BasicNameValuePair("email", (String)context.getStringData("bianji.email")));
		nameValuePairs.add(new BasicNameValuePair("sex", (String)context.getStringData("bianji.sex")));
		nameValuePairs.add(new BasicNameValuePair("nickname", (String)context.getStringData("bianji.nickname")));
		nameValuePairs.add(new BasicNameValuePair("realname", (String)context.getStringData("bianji.realname")));
		nameValuePairs.add(new BasicNameValuePair("birthday", (String)context.getStringData("bianji.birthday")));
		nameValuePairs.add(new BasicNameValuePair("constella", (String)context.getStringData("bianji.constella")));
		nameValuePairs.add(new BasicNameValuePair("certificate", (String)context.getStringData("bianji.certificate")));
		nameValuePairs.add(new BasicNameValuePair("province", (String)context.getStringData("bianji.province")));
		nameValuePairs.add(new BasicNameValuePair("city", (String)context.getStringData("bianji.city")));
		nameValuePairs.add(new BasicNameValuePair("major", (String)context.getStringData("bianji.major")));
		request.addParameter(Request.AJAXPARAMS, nameValuePairs);
		request.setUrl(Config.HTTP_MY_BASEINFOUPDATE);
		request.setR_calzz(BaseResp.class);
		BaseResp resp = TimeTalentApplication.getAppSocket().shortConnect(request);
		if ("1".equals(resp.getStatus())) {
			ToastUtil.showToast(AppController.getController().getCurrentActivity(), resp.getText(), 1000);
			} else{
			throw new BusinessException(new ErrorMessage(resp.getText()));
		}
	
			
			
	}

	
	@Override
	public void walletOrder() throws BusinessException {
		String _session_id = context.getStringData("_session_id");
		Request<BaseResp> request = new Request<BaseResp>();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("_session_id", _session_id));
		request.addParameter(Request.AJAXPARAMS, nameValuePairs);
		request.setUrl(Config.HTTP_USER_WALLET_ORDER);
		request.setR_calzz(BaseResp.class);  
		BaseResp resp = TimeTalentApplication.getAppSocket().shortConnect(request);
		if ("1".equals(resp.getStatus())) {
			
		} else{
			throw new BusinessException(new ErrorMessage(resp.getText()));
		}
	
	}

	
	@Override
	public void walletPay() throws BusinessException {

		String _session_id = context.getStringData("_session_id");
		Request<BaseResp> request = new Request<BaseResp>();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("_session_id", _session_id));
		request.addParameter(Request.AJAXPARAMS, nameValuePairs);
		request.setUrl(Config.HTTP_USER_WALLET_ORDER_OK);
		request.setR_calzz(BaseResp.class);  
		BaseResp resp = TimeTalentApplication.getAppSocket().shortConnect(request);
		if ("1".equals(resp.getStatus())) {
			
		} else{
			throw new BusinessException(new ErrorMessage(resp.getText()));
		}
	
	
		
	}

	
	@Override
	public void walletWithdraw() throws BusinessException {

		String _session_id = context.getStringData("_session_id");
		Request<BaseResp> request = new Request<BaseResp>();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("_session_id", _session_id));
		request.addParameter(Request.AJAXPARAMS, nameValuePairs);
		request.setUrl(Config.HTTP_USER_WALLET_WITHDRAW);
		request.setR_calzz(BaseResp.class);  
		BaseResp resp = TimeTalentApplication.getAppSocket().shortConnect(request);
		if ("1".equals(resp.getStatus())) {
			
		} else{
			throw new BusinessException(new ErrorMessage(resp.getText()));
		}
	
	
		
	}

	
	@Override
	public void walletWithdrawList() throws BusinessException {

		String _session_id = context.getStringData("_session_id");
		Request<BaseResp> request = new Request<BaseResp>();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("_session_id", _session_id));
		request.addParameter(Request.AJAXPARAMS, nameValuePairs);
		request.setUrl(Config.HTTP_USER_WALLET_WITHDRAW_LIST);
		request.setR_calzz(BaseResp.class);  
		BaseResp resp = TimeTalentApplication.getAppSocket().shortConnect(request);
		if ("1".equals(resp.getStatus())) {
			
		} else{
			throw new BusinessException(new ErrorMessage(resp.getText()));
		}
	
	
		
	}

	
	@Override
	public void mylocation_update() throws BusinessException {
		String lat = context.getStringData("my.lat");
		String lng = context.getStringData("my.lng");

		String _session_id = context.getStringData("_session_id");
		Request<BaseResp> request = new Request<BaseResp>();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("_session_id", _session_id));
		nameValuePairs.add(new BasicNameValuePair("lat", lat));
		nameValuePairs.add(new BasicNameValuePair("lng", lng));
		request.addParameter(Request.AJAXPARAMS, nameValuePairs);
		request.setUrl(Config.HTTP_MY_LOCATION_UPDATE);
		request.setR_calzz(BaseResp.class);
		BaseResp resp = TimeTalentApplication.getAppSocket().shortConnect(request);
		if ("1".equals(resp.getStatus())) {
			
		} else{
			throw new BusinessException(new ErrorMessage(resp.getText()));
		}
	
	
		
	}

	
	@Override
	public void myuser_service() throws BusinessException {
		String _session_id = context.getStringData("_session_id");
		Request<MyserviceResp> request = new Request<MyserviceResp>();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("_session_id", _session_id));
		request.addParameter(Request.AJAXPARAMS, nameValuePairs);
		request.setUrl(Config.HTTP_MY_USER_SERVICE);
		request.setR_calzz(MyserviceResp.class);  
		MyserviceResp resp = TimeTalentApplication.getAppSocket().shortConnect(request);
		if ("1".equals(resp.getStatus())) {
			if (resp.getData() != null) {
				context.addBusinessData("MyserviceData", resp.getData());
			}
		
		} else{
//			throw new BusinessException(new ErrorMessage(resp.getText()));
		}
	
	}

	
	@Override
	public void myuser_addservice() throws BusinessException {
		String _session_id = context.getStringData("_session_id");
		String serviceid = context.getStringData("service.serviceid");
		String money = context.getStringData("service.money");
		String unit = context.getStringData("service.unit");
		Request<BaseResp> request = new Request<BaseResp>();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("_session_id", _session_id));
		nameValuePairs.add(new BasicNameValuePair("service_id", serviceid));
		nameValuePairs.add(new BasicNameValuePair("money", money));
		nameValuePairs.add(new BasicNameValuePair("unit", unit));
		request.addParameter(Request.AJAXPARAMS, nameValuePairs);
		request.setUrl(Config.HTTP_MY_USER_ADDSERVICE);
		request.setR_calzz(BaseResp.class);  
		BaseResp resp = TimeTalentApplication.getAppSocket().shortConnect(request);
		if ("1".equals(resp.getStatus())) {
			ToastUtil.showToast(AppController.getController().getCurrentActivity(), resp.getText(), 1000);
			} else{
			throw new BusinessException(new ErrorMessage(resp.getText()));
		}
	
	}

	
	@Override
	public void myservice_list() throws BusinessException {
		String _session_id = context.getStringData("_session_id");
		Request<servicelistResp> request = new Request<servicelistResp>();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("_session_id", _session_id));
		request.addParameter(Request.AJAXPARAMS, nameValuePairs);
		request.setUrl(Config.HTTP_MY_USER_SERVICE_LIST);
		request.setR_calzz(servicelistResp.class);
		servicelistResp resp = TimeTalentApplication.getAppSocket().shortConnect(request);
		if ("1".equals(resp.getStatus())) {
			if (resp.getData() != null) {
				context.addBusinessData("servicelistData", resp.getData());
			}
		
		} else{
//			throw new BusinessException(new ErrorMessage(resp.getText()));
		}
	
	}

	
	@Override
	public void myuser_loyal() throws BusinessException {
		String _session_id = context.getStringData("_session_id");
		String realname = context.getStringData("loyal.realname");
		String certificate = context.getStringData("loyal.certificate");
		Request<BaseResp> request = new Request<BaseResp>();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("_session_id", _session_id));
		nameValuePairs.add(new BasicNameValuePair("realname", realname));
		nameValuePairs.add(new BasicNameValuePair("certificate", certificate));
		request.addParameter(Request.AJAXPARAMS, nameValuePairs);
		request.setUrl(Config.HTTP_MY_USER_LOYAL);
		request.setR_calzz(BaseResp.class);
		BaseResp resp = TimeTalentApplication.getAppSocket().shortConnect(request);
		if ("1".equals(resp.getStatus())) {
			
		} else{
			throw new BusinessException(new ErrorMessage(resp.getText()));
		}
	
	}

	
	@Override
	public void myuser_loyal_item(List<PicValuePair> picValuePair) throws BusinessException {
//		String _session_id = context.getStringData("_session_id");
//		String item = context.getStringData("loyal.item");
//		Request<BaseResp> request = new Request<BaseResp>();
//		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
//		nameValuePairs.add(new BasicNameValuePair("_session_id", _session_id));
//		nameValuePairs.add(new BasicNameValuePair("item", item));
//		request.addParameter(Request.AJAXPARAMS, nameValuePairs);
//		request.addParameter(Request.PICTURE, picValuePair);
//		request.setUrl(Config.HTTP_MY_USER_LOYAL_ITEM);
//		request.setR_calzz(BaseResp.class);
//		BaseResp resp = TimeTalentApplication.getAppSocket().shortConnect(request);
//		if ("1".equals(resp.getStatus())) {
//			
//		} else{
//			throw new BusinessException(new ErrorMessage(resp.getText()));
//		}
	
		
		
		
		


		String _session_id = context.getStringData("_session_id");
		String item = context.getStringData("loyal.item");
		Request<BaseResp> request = new Request<BaseResp>();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("_session_id",_session_id));
		nameValuePairs.add(new BasicNameValuePair("item",item));
		request.addParameter(Request.AJAXPARAMS, nameValuePairs);
		request.addParameter(Request.PICTURE, picValuePair);
		request.setUrl(Config.HTTP_MY_USER_LOYAL_ITEM);
		request.setR_calzz(BaseResp.class);
		BaseResp resp = TimeTalentApplication.getAppSocket().imageLoad(request);
		if ("1".equals(resp.getStatus())) {
			ToastUtil.showToast(AppController.getController().getCurrentActivity(), resp.getText(), 1000);
			} else{
			throw new BusinessException(new ErrorMessage(resp.getText()));
		}
	
	}

	
	@Override
	public void moreinfo() throws BusinessException {
		Request<UserinfoResp> request = new Request<UserinfoResp>();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("_session_id", (String)context.getBusinessData("_session_id")));
		request.addParameter(Request.AJAXPARAMS, nameValuePairs);
		request.setUrl(Config.HTTP_MY_MOREINFO);
		request.setR_calzz(UserinfoResp.class);
		UserinfoResp resp = TimeTalentApplication.getAppSocket().shortConnect(request);
		if ("1".equals(resp.getStatus())) {
			if (resp.getData() != null) {
				context.addBusinessData("MoreinfoData", resp.getData());
			}
		} else{
			throw new BusinessException(new ErrorMessage(resp.getText()));
		}
	
			
			
	}

	
	@Override
	public void mydo_social() throws BusinessException {

		Request<BaseResp> request = new Request<BaseResp>();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("_session_id", (String)context.getStringData("_session_id")));
		Log.i("my.do", (String)context.getStringData("my.do"));
		Log.i("my.target_id", (String)context.getStringData("my.target_id"));
		nameValuePairs.add(new BasicNameValuePair("do", (String)context.getStringData("my.do")));
		nameValuePairs.add(new BasicNameValuePair("target_id", (String)context.getStringData("my.target_id")));
		request.addParameter(Request.AJAXPARAMS, nameValuePairs);
		request.setUrl(Config.HTTP_MY_DO_SOCIAL);
		request.setR_calzz(BaseResp.class);
		BaseResp resp = TimeTalentApplication.getAppSocket().shortConnect(request);
		if ("1".equals(resp.getStatus())) {
			ToastUtil.showToast(AppController.getController().getCurrentActivity(), resp.getText(), 1000);
		} else{
			throw new BusinessException(new ErrorMessage(resp.getText()));
		}
	}

	
	@Override
	public void myreport() throws BusinessException {
		Request<BaseResp> request = new Request<BaseResp>();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("_session_id", (String)context.getBusinessData("_session_id")));
		nameValuePairs.add(new BasicNameValuePair("target_id", (String)context.getBusinessData("report.target_id")));
		nameValuePairs.add(new BasicNameValuePair("type", (String)context.getBusinessData("report.type")));
		nameValuePairs.add(new BasicNameValuePair("msg", (String)context.getBusinessData("report.msg")));
		request.addParameter(Request.AJAXPARAMS, nameValuePairs);
		request.setUrl(Config.HTTP_MY_REPORT);
		request.setR_calzz(BaseResp.class);
		BaseResp resp = TimeTalentApplication.getAppSocket().shortConnect(request);
		if ("1".equals(resp.getStatus())) {
			ToastUtil.showToast(AppController.getController().getCurrentActivity(), resp.getText(), 1000);
		} else{
			throw new BusinessException(new ErrorMessage(resp.getText()));
		}
	}

	
	@Override
	public void myphoto() throws BusinessException {

		Request<MyphotoResp> request = new Request<MyphotoResp>();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("_session_id", (String)context.getBusinessData("_session_id")));
		request.addParameter(Request.AJAXPARAMS, nameValuePairs);
		request.setUrl(Config.HTTP_MY_PHOTO);
		request.setR_calzz(MyphotoResp.class);
		MyphotoResp resp = TimeTalentApplication.getAppSocket().shortConnect(request);
		if ("1".equals(resp.getStatus())) {
			if (resp.getData() != null) {
				context.addBusinessData("MyphotoData", resp.getData());
			}
		} else{
//			throw new BusinessException(new ErrorMessage(resp.getText()));
		}
	}

	
	@Override
	public void myphotodel() throws BusinessException {

		Request<BaseResp> request = new Request<BaseResp>();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("_session_id", (String)context.getBusinessData("_session_id")));
		nameValuePairs.add(new BasicNameValuePair("id", (String)context.getBusinessData("photo.id")));
		request.addParameter(Request.AJAXPARAMS, nameValuePairs);
		request.setUrl(Config.HTTP_MY_PHOTO_DEL);
		request.setR_calzz(BaseResp.class);
		BaseResp resp = TimeTalentApplication.getAppSocket().shortConnect(request);
		if ("1".equals(resp.getStatus())) {
		} else{
			throw new BusinessException(new ErrorMessage(resp.getText()));
		}
	}

	
	@Override
	public void myavatarupdate(List<PicValuePair> picValuePair) throws BusinessException {


		String _session_id = context.getStringData("_session_id");
		Request<BaseResp> request = new Request<BaseResp>();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("_session_id",_session_id));
		request.addParameter(Request.AJAXPARAMS, nameValuePairs);
		request.addParameter(Request.PICTURE, picValuePair);
		request.setUrl(Config.HTTP_MY_AVATAR_UPDATE);
		request.setR_calzz(BaseResp.class);
		BaseResp resp = TimeTalentApplication.getAppSocket().imageLoad(request);
		if ("1".equals(resp.getStatus())) {
			ToastUtil.showToast(AppController.getController().getCurrentActivity(), resp.getText(), 1000);
			} else{
			throw new BusinessException(new ErrorMessage(resp.getText()));
		}
	
		
		
		
	}

	
	@Override
	public void myavatardel() throws BusinessException {

		Request<BaseResp> request = new Request<BaseResp>();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("_session_id", (String)context.getBusinessData("_session_id")));
		nameValuePairs.add(new BasicNameValuePair("id", (String)context.getBusinessData("photo.id")));
		request.addParameter(Request.AJAXPARAMS, nameValuePairs);
		request.setUrl(Config.HTTP_MY_AVATAR_DEL);
		request.setR_calzz(BaseResp.class);
		BaseResp resp = TimeTalentApplication.getAppSocket().shortConnect(request);
		if ("1".equals(resp.getStatus())) {
		} else{
			throw new BusinessException(new ErrorMessage(resp.getText()));
		}
	}

	
	@Override
	public void dictionary() throws BusinessException {

		Request<DictionaryResp> request = new Request<DictionaryResp>();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("_session_id", (String)context.getBusinessData("_session_id")));
		nameValuePairs.add(new BasicNameValuePair("type", (String)context.getBusinessData("dictionary.type")));
		request.addParameter(Request.AJAXPARAMS, nameValuePairs);
		request.setUrl(Config.HTTP_YSYTEM_DICTIONARY);
		request.setR_calzz(DictionaryResp.class);
		DictionaryResp resp = TimeTalentApplication.getAppSocket().shortConnect(request);
		if ("1".equals(resp.getStatus())) {
			if (resp.getData() != null) {
				context.addBusinessData("DictionaryData", resp.getData());
			}
		
		} else{
			throw new BusinessException(new ErrorMessage(resp.getText()));
		}
	}

	
	/* (non-Javadoc)
	 * @see com.timetalent.client.service.AppService#myapp_config()
	 */
	@Override
	public void myapp_config() throws BusinessException {
		String name = (String)context.getBusinessData("app.name");
		Request<AppConfigResp> request = new Request<AppConfigResp>();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("_session_id", (String)context.getBusinessData("_session_id")));
		nameValuePairs.add(new BasicNameValuePair("name", name));
		request.addParameter(Request.AJAXPARAMS, nameValuePairs);
		request.setUrl(Config.HTTP_MY_APP_CONFIG);
		request.setR_calzz(AppConfigResp.class);
		AppConfigResp resp = TimeTalentApplication.getAppSocket().shortConnect(request);
		if ("1".equals(resp.getStatus())) {
			if (resp.getData() != null) {
				context.addBusinessData("AppConfigdata", resp.getData());
			}
		
		} else{
			throw new BusinessException(new ErrorMessage(resp.getText()));
		}
	}

	
	/* (non-Javadoc)
	 * @see com.timetalent.client.service.AppService#orderlist()
	 */
	@Override
	public void orderlist() throws BusinessException {
		String _session_id = context.getStringData("_session_id");
		Request<BaseResp> request = new Request<BaseResp>();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("_session_id", _session_id));
		request.addParameter(Request.AJAXPARAMS, nameValuePairs);
		request.setUrl(Config.HTTP_MY_WALLET_ORDERLISTS);
		request.setR_calzz(BaseResp.class);  
		BaseResp resp = TimeTalentApplication.getAppSocket().shortConnect(request);
		if ("1".equals(resp.getStatus())) {
			
		} else{
//			throw new BusinessException(new ErrorMessage(resp.getText()));
		}
	}
	
	
	
	@Override
	public void chatAccess() throws BusinessException {
		String _session_id = context.getStringData("_session_id");
		String target_id = context.getStringData("Chat.target_id");
		Request<BaseResp> request = new Request<BaseResp>();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("_session_id", _session_id));
		nameValuePairs.add(new BasicNameValuePair("target_id", target_id));
		request.addParameter(Request.AJAXPARAMS, nameValuePairs);
		request.setUrl(Config.HTTP_USER_CHAT_ACCESS);
		request.setR_calzz(BaseResp.class);  
		BaseResp resp = TimeTalentApplication.getAppSocket().shortConnect(request);
		if ("1".equals(resp.getStatus())) {
			
		} else{
			throw new BusinessException(new ErrorMessage(resp.getText()));
		}
	}
	
	
	/* (non-Javadoc)
	 * @see com.timetalent.client.service.AppService#InvitePayment()
	 */
	@Override
	public void chatPay() throws BusinessException {

		String _session_id = context.getStringData("_session_id");
		String target_id = context.getStringData("Chat.target_id");
		Request<BaseResp> request = new Request<BaseResp>();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("_session_id", _session_id));
		nameValuePairs.add(new BasicNameValuePair("target_id", target_id));
		request.addParameter(Request.AJAXPARAMS, nameValuePairs);
		request.setUrl(Config.HTTP_USER_CHAT_PAY);
		request.setR_calzz(BaseResp.class);  
		BaseResp resp = TimeTalentApplication.getAppSocket().shortConnect(request);
		if ("1".equals(resp.getStatus())) {
		} else{
			throw new BusinessException(new ErrorMessage(Integer.valueOf(resp.getStatus()),resp.getText()));
		}
	
	}

	
	/* (non-Javadoc)
	 * @see com.timetalent.client.service.AppService#mytasklists()
	 */
	@Override
	public void mytasklists() throws BusinessException {
		String _session_id = context.getStringData("_session_id");
		Request<MytasklistResp> request = new Request<MytasklistResp>();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("_session_id", _session_id));
		request.addParameter(Request.AJAXPARAMS, nameValuePairs);
		request.setUrl(Config.HTTP_MY_TASK_LISTS);
		request.setR_calzz(MytasklistResp.class);  
		MytasklistResp resp = TimeTalentApplication.getAppSocket().shortConnect(request);
		if ("1".equals(resp.getStatus())) {
			if (resp.getData() != null) {
				context.addBusinessData("MytasklistData", resp.getData());
			}
		
		} else{
//			throw new BusinessException(new ErrorMessage(resp.getText()));
		}
	}

	
	/* (non-Javadoc)
	 * @see com.timetalent.client.service.AppService#myapplytasklists()
	 */
	@Override
	public void myapplytasklists() throws BusinessException {
		String _session_id = context.getStringData("_session_id");
		Request<MyapplytaskResp> request = new Request<MyapplytaskResp>();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("_session_id", _session_id));
		request.addParameter(Request.AJAXPARAMS, nameValuePairs);
		request.setUrl(Config.HTTP_MY_TASK_APPLY_LISTS);
		request.setR_calzz(MyapplytaskResp.class);  
		MyapplytaskResp resp = TimeTalentApplication.getAppSocket().shortConnect(request);
		if ("1".equals(resp.getStatus())) {
			if (resp.getData() != null) {
				context.addBusinessData("MyapplytaskData", resp.getData());
			}
		
		} else{
//			throw new BusinessException(new ErrorMessage(resp.getText()));
		}
	}

	
	/* (non-Javadoc)
	 * @see com.timetalent.client.service.AppService#myphotoupdate()
	 */
	@Override
	public void myphotoupdate(List<PicValuePair> picValuePair) throws BusinessException {

		String _session_id = context.getStringData("_session_id");
		Request<BaseResp> request = new Request<BaseResp>();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("_session_id",_session_id));
		request.addParameter(Request.AJAXPARAMS, nameValuePairs);
		request.addParameter(Request.PICTURE, picValuePair);
		request.setUrl(Config.HTTP_MY_PHOTO_UPDATE);
		request.setR_calzz(BaseResp.class);
		BaseResp resp = TimeTalentApplication.getAppSocket().imageLoad(request);
		if ("1".equals(resp.getStatus())) {
			ToastUtil.showToast(AppController.getController().getCurrentActivity(), resp.getText(), 1000);
			} else{
			throw new BusinessException(new ErrorMessage(resp.getText()));
		}
	}

	
	/* (non-Javadoc)
	 * @see com.timetalent.client.service.AppService#myuser_delservice()
	 */
	@Override
	public void myuser_delservice() throws BusinessException {
		String _session_id = context.getStringData("_session_id");
		String serviceid = context.getStringData("delservice.serviceid");
		Request<BaseResp> request = new Request<BaseResp>();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("_session_id", _session_id));
		nameValuePairs.add(new BasicNameValuePair("service_id", serviceid));
		request.addParameter(Request.AJAXPARAMS, nameValuePairs);
		request.setUrl(Config.HTTP_MY_USER_DELSERVICE);
		request.setR_calzz(BaseResp.class);  
		BaseResp resp = TimeTalentApplication.getAppSocket().shortConnect(request);
		if ("1".equals(resp.getStatus())) {
			ToastUtil.showToast(AppController.getController().getCurrentActivity(), resp.getText(), 1000);
			} else{
			throw new BusinessException(new ErrorMessage(resp.getText()));
		}
	
	}

	
	/* (non-Javadoc)
	 * @see com.timetalent.client.service.AppService#myworksadd()
	 */
	@Override
	public void myworksadd(List<PicValuePair> picValuePair) throws BusinessException {

		String _session_id = context.getStringData("_session_id");
		String title = context.getStringData("work.title");
		String actor = context.getStringData("work.actor");
		String time = context.getStringData("work.time");
		Request<BaseResp> request = new Request<BaseResp>();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("_session_id",_session_id));
		nameValuePairs.add(new BasicNameValuePair("title",title));
		nameValuePairs.add(new BasicNameValuePair("actor",actor));
		nameValuePairs.add(new BasicNameValuePair("time",time));
		request.addParameter(Request.AJAXPARAMS, nameValuePairs);
		request.addParameter(Request.PICTURE, picValuePair);
		request.setUrl(Config.HTTP_MY_WORKS_ADD);
		request.setR_calzz(BaseResp.class);
		BaseResp resp = TimeTalentApplication.getAppSocket().imageLoad(request);
		if ("1".equals(resp.getStatus())) {
			ToastUtil.showToast(AppController.getController().getCurrentActivity(), resp.getText(), 1000);
			} else{
			throw new BusinessException(new ErrorMessage(resp.getText()));
		}
	}

	
	/* (non-Javadoc)
	 * @see com.timetalent.client.service.AppService#mymoreinfoupdate()
	 */
	@Override
	public void mymoreinfoupdate() throws BusinessException {
		Request<BaseResp> request = new Request<BaseResp>();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("_session_id", (String)context.getStringData("_session_id")));
		nameValuePairs.add(new BasicNameValuePair("bust", (String)context.getStringData("bianji.bust")));
		nameValuePairs.add(new BasicNameValuePair("waist", (String)context.getStringData("bianji.waist")));
		nameValuePairs.add(new BasicNameValuePair("hip", (String)context.getStringData("bianji.hip")));
		nameValuePairs.add(new BasicNameValuePair("height", (String)context.getStringData("bianji.height")));
		nameValuePairs.add(new BasicNameValuePair("weight", (String)context.getStringData("bianji.weight")));
		nameValuePairs.add(new BasicNameValuePair("major", (String)context.getStringData("bianji.major")));
		request.addParameter(Request.AJAXPARAMS, nameValuePairs);
		request.setUrl(Config.HTTP_MY_MOREINFO_UPDATE);
		request.setR_calzz(BaseResp.class);
		BaseResp resp = TimeTalentApplication.getAppSocket().shortConnect(request);
		if ("1".equals(resp.getStatus())) {
//			ToastUtil.showToast(AppController.getController().getCurrentActivity(), resp.getText(), 1000);
			} else{
			throw new BusinessException(new ErrorMessage(resp.getText()));
		}
	
			
			
	}
}
