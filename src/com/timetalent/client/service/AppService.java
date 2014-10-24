package com.timetalent.client.service;

import com.timetalent.common.exception.BusinessException;

/******************************************
 * 类描述： 业务类 类名称：AppService
 * 
 * @version: 1.0
 * @author: why
 * @time: 2014-2-13 下午2:09:22
 ******************************************/
public interface AppService {
	/** 登陆 **/
	public void login() throws BusinessException;
	/**获取验证码**/
	public void code() throws BusinessException;
	/**验证 验证码***/
	public void validationCode() throws BusinessException;
	/**注册***/
	public void register() throws BusinessException;
}
