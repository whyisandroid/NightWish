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
}
