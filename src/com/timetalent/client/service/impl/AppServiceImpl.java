package com.timetalent.client.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.timetalent.client.TimeTalentApplication;
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
}
