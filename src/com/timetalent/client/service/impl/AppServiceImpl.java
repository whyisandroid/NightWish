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
import com.timetalent.client.entities.json.LoginResp;
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
	
	
}
