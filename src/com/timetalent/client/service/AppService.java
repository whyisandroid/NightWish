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
	
	/**发布 机会***/
	public void chanceAdd() throws BusinessException;
	
	/** 机会 列表***/
	public void chanceLists() throws BusinessException;
	
	/**机会  详情 ***/
	public void chanceDetails() throws BusinessException;
	
	/**申请报名某机会Task/apply***/
	public void chanceApply() throws BusinessException;
	
	/**获取 用户 最新 动态***/
	public void dynamicIndex() throws BusinessException;
	
	/**获取某个用户的动态列表***/
	public void dynamicWho() throws BusinessException;
	
	/**获取当前用户自己的动态列表***/
	public void dynamicMy() throws BusinessException;
	
	/**发布动态Feed/addfeed**/
	public void dynamicAdd() throws BusinessException;
	
	/**评论某动态Feed/add_reply***/
	public void dynamicRepaly() throws BusinessException;
	
	/**点赞Feed/favour***/
	public void dynamicFavour() throws BusinessException;
	
}
