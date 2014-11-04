package com.timetalent.client.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.timetalent.client.TimeTalentApplication;
import com.timetalent.client.entities.PicValuePair;
import com.timetalent.client.entities.Register;
import com.timetalent.client.entities.json.BaseResp;
import com.timetalent.client.entities.json.BlackResp;
import com.timetalent.client.entities.json.FollowedResp;
import com.timetalent.client.entities.json.FollowingResp;
import com.timetalent.client.entities.json.FriendResp;
import com.timetalent.client.entities.json.LoginResp;
import com.timetalent.client.entities.json.NearResp;
import com.timetalent.client.entities.json.PushuserResp;
import com.timetalent.client.entities.json.SearchResp;
import com.timetalent.client.service.AppContext;
import com.timetalent.client.service.AppService;
import com.timetalent.common.exception.BusinessException;
import com.timetalent.common.exception.ErrorMessage;
import com.timetalent.common.net.Request;
import com.timetalent.common.util.Config;

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
	public void login() throws BusinessException {
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
		LoginResp resp = TimeTalentApplication.getAppSocket().shortConnect(request);
		if ("1".equals(resp.getStatus())) {
			TimeTalentApplication.getInstance().setLogin(true);
			// 登录成功 存储 账户
			if (resp.getData() != null) {
				context.addBusinessData("loginData", resp.getData());
				context.addBusinessData("_session_id", resp.getData().getSession_id());
			}
		} else{
			throw new BusinessException(new ErrorMessage(resp.getText()));
		}
	}

	
	@Override
	public void code() throws BusinessException {
		
	}
	
	
	@Override
	public void validationCode() throws BusinessException {
		String phone = context.getStringData("register.phone");
		String code = context.getStringData("register.code");
		
	}
	
	
	@Override
	public void register() throws BusinessException {
		Register register = (Register)context.getBusinessData("Register.register");
		Request<BaseResp> request = new Request<BaseResp>();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		
		nameValuePairs.add(new BasicNameValuePair("username", register.getUsername()));
		nameValuePairs.add(new BasicNameValuePair("phone", register.getPhone()));
		nameValuePairs.add(new BasicNameValuePair("password", register.getPassword()));
		nameValuePairs.add(new BasicNameValuePair("birthday", register.getBirthday()));
		nameValuePairs.add(new BasicNameValuePair("sex", register.getSex()));
		nameValuePairs.add(new BasicNameValuePair("type", register.getType()));
		nameValuePairs.add(new BasicNameValuePair("email", "1@qq.com"));
		request.addParameter(Request.AJAXPARAMS, nameValuePairs);
		request.setUrl(Config.HTTP_USER_REGISTER);
		request.setR_calzz(BaseResp.class);
		BaseResp resp = TimeTalentApplication.getAppSocket().shortConnect(request);
		if ("1".equals(resp.getStatus())) {
		} else{
			throw new BusinessException(new ErrorMessage(resp.getText()));
		}
	
	}
	
	
	@Override
	public void register_avatar() throws BusinessException {
		String  user_id = (String)context.getBusinessData("User.user_id");
		Request<BaseResp> request = new Request<BaseResp>();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("user_id", "7"));
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
		Request<BaseResp> request = new Request<BaseResp>();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("_session_id", _session_id));
		request.addParameter(Request.AJAXPARAMS, nameValuePairs);
		request.setUrl(Config.HTTP_USER_CHANCE_ADD);
		request.setR_calzz(BaseResp.class);
		BaseResp resp = TimeTalentApplication.getAppSocket().shortConnect(request);
		if ("1".equals(resp.getStatus())) {
			
		} else{
			throw new BusinessException(new ErrorMessage(resp.getText()));
		}
	}

	
	@Override
	public void chanceLists() throws BusinessException {
		Request<BaseResp> request = new Request<BaseResp>();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("_session_id", "_session_id"));
		request.addParameter(Request.AJAXPARAMS, nameValuePairs);
		request.setUrl(Config.HTTP_USER_CHANCE_LIST);
		request.setR_calzz(BaseResp.class);
		BaseResp resp = TimeTalentApplication.getAppSocket().shortConnect(request);
		if ("1".equals(resp.getStatus())) {
			
		} else{
			throw new BusinessException(new ErrorMessage(resp.getText()));
		}
	
	}

	
	@Override
	public void chanceDetails() throws BusinessException {
		Request<BaseResp> request = new Request<BaseResp>();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("_session_id", "_session_id"));
		request.addParameter(Request.AJAXPARAMS, nameValuePairs);
		request.setUrl(Config.HTTP_USER_CHANCE_DETAIL);
		request.setR_calzz(BaseResp.class);
		BaseResp resp = TimeTalentApplication.getAppSocket().shortConnect(request);
		if ("1".equals(resp.getStatus())) {
			
		} else{
			throw new BusinessException(new ErrorMessage(resp.getText()));
		}
	}

	
	@Override
	public void chanceApply() throws BusinessException {
		Request<BaseResp> request = new Request<BaseResp>();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("_session_id", "_session_id"));
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
	public void dynamicIndex() throws BusinessException {

		Request<BaseResp> request = new Request<BaseResp>();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("_session_id", "_session_id"));
		request.addParameter(Request.AJAXPARAMS, nameValuePairs);
		request.setUrl(Config.HTTP_USER_DYNAMIC_INDEX);
		request.setR_calzz(BaseResp.class);
		BaseResp resp = TimeTalentApplication.getAppSocket().shortConnect(request);
		if ("1".equals(resp.getStatus())) {
			
		} else{
			throw new BusinessException(new ErrorMessage(resp.getText()));
		}
	
			
	}

	
	/* (non-Javadoc)
	 * @see com.timetalent.client.service.AppService#dynamicWho()
	 */
	@Override
	public void dynamicWho() throws BusinessException {


		Request<BaseResp> request = new Request<BaseResp>();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("_session_id", "_session_id"));
		request.addParameter(Request.AJAXPARAMS, nameValuePairs);
		request.setUrl(Config.HTTP_USER_DYNAMIC_INDEX);
		request.setR_calzz(BaseResp.class);
		BaseResp resp = TimeTalentApplication.getAppSocket().shortConnect(request);
		if ("1".equals(resp.getStatus())) {
			
		} else{
			throw new BusinessException(new ErrorMessage(resp.getText()));
		}
	
			
			
	}

	
	/* (non-Javadoc)
	 * @see com.timetalent.client.service.AppService#dynamicMy()
	 */
	@Override
	public void dynamicMy() throws BusinessException {
		String _session_id = context.getStringData("_session_id");
		Request<BaseResp> request = new Request<BaseResp>();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("_session_id", _session_id));
		nameValuePairs.add(new BasicNameValuePair("page", "1"));
		nameValuePairs.add(new BasicNameValuePair("page_per", "10"));
		request.addParameter(Request.AJAXPARAMS, nameValuePairs);
		request.setUrl(Config.HTTP_USER_DYNAMIC_INDEX);
		request.setR_calzz(BaseResp.class);
		BaseResp resp = TimeTalentApplication.getAppSocket().shortConnect(request);
		if ("1".equals(resp.getStatus())) {
			
		} else{
			throw new BusinessException(new ErrorMessage(resp.getText()));
		}
	
			
			
	}

	
	/* (non-Javadoc)
	 * @see com.timetalent.client.service.AppService#dynamicAdd()
	 */
	@Override
	public void dynamicAdd() throws BusinessException {


		Request<BaseResp> request = new Request<BaseResp>();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("_session_id", "_session_id"));
		request.addParameter(Request.AJAXPARAMS, nameValuePairs);
		request.setUrl(Config.HTTP_USER_DYNAMIC_ADD);
		request.setR_calzz(BaseResp.class);
		BaseResp resp = TimeTalentApplication.getAppSocket().shortConnect(request);
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


		Request<BaseResp> request = new Request<BaseResp>();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("_session_id", "_session_id"));
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


		Request<BaseResp> request = new Request<BaseResp>();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("_session_id", "_session_id"));
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
	 * @see com.timetalent.client.service.AppService#search()
	 */
	@Override
	public void search() throws BusinessException {


		Request<SearchResp> request = new Request<SearchResp>();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("_session_id", (String)context.getBusinessData("_session_id")));
		nameValuePairs.add(new BasicNameValuePair("search.search", (String)context.getBusinessData("search.search")));
		
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

		String lat = (String)context.getBusinessData("near.lat");
		String lng = (String)context.getBusinessData("near.lng");
		Request<NearResp> request = new Request<NearResp>();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("_session_id", (String)context.getBusinessData("_session_id")));
		nameValuePairs.add(new BasicNameValuePair("lat", lat));
		nameValuePairs.add(new BasicNameValuePair("lng", lng));
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
			throw new BusinessException(new ErrorMessage(resp.getText()));
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
			throw new BusinessException(new ErrorMessage(resp.getText()));
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
			throw new BusinessException(new ErrorMessage(resp.getText()));
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
			throw new BusinessException(new ErrorMessage(resp.getText()));
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


		Request<BaseResp> request = new Request<BaseResp>();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("_session_id", (String)context.getBusinessData("_session_id")));
		request.addParameter(Request.AJAXPARAMS, nameValuePairs);
		request.setUrl(Config.HTTP_MY_WALLET_CHARGEORDER);
		request.setR_calzz(BaseResp.class);
		BaseResp resp = TimeTalentApplication.getAppSocket().shortConnect(request);
		if ("1".equals(resp.getStatus())) {
			
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
		request.addParameter(Request.AJAXPARAMS, nameValuePairs);
		request.setUrl(Config.HTTP_MY_WALLET_COMPLETEORDER);
		request.setR_calzz(BaseResp.class);
		BaseResp resp = TimeTalentApplication.getAppSocket().shortConnect(request);
		if ("1".equals(resp.getStatus())) {
			
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


		Request<BaseResp> request = new Request<BaseResp>();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("_session_id", (String)context.getBusinessData("_session_id")));
		request.addParameter(Request.AJAXPARAMS, nameValuePairs);
		request.setUrl(Config.HTTP_MY_INVITE_APPOINT);
		request.setR_calzz(BaseResp.class);
		BaseResp resp = TimeTalentApplication.getAppSocket().shortConnect(request);
		if ("1".equals(resp.getStatus())) {
			
		} else{
			throw new BusinessException(new ErrorMessage(resp.getText()));
		}
	
			
			
	}

	
	/* (non-Javadoc)
	 * @see com.timetalent.client.service.AppService#myinvite_offer()
	 */
	@Override
	public void myinvite_offer() throws BusinessException {


		Request<BaseResp> request = new Request<BaseResp>();
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("_session_id", (String)context.getBusinessData("_session_id")));
		request.addParameter(Request.AJAXPARAMS, nameValuePairs);
		request.setUrl(Config.HTTP_MY_INVITE_OFFER);
		request.setR_calzz(BaseResp.class);
		BaseResp resp = TimeTalentApplication.getAppSocket().shortConnect(request);
		if ("1".equals(resp.getStatus())) {
			
		} else{
			throw new BusinessException(new ErrorMessage(resp.getText()));
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
	
	
}
