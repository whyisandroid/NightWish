package com.timetalent.client.service;

import java.util.List;

import android.os.Handler;

import com.timetalent.client.entities.PicValuePair;
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
	public void login(Handler handler) throws BusinessException;
	/**环信注册**/
	public void huanxin_reg() throws BusinessException;
	/**
	 * exit
	 */
	public void logout() throws BusinessException;
	/**修改密码**/
	public void resetPwd()throws BusinessException;
	/**获取验证码**/
	public void code() throws BusinessException;
	/**验证 验证码***/
	public void validationCode() throws BusinessException;
	/**注册***/
	public void register() throws BusinessException;
	/**注册-上传图片**/
	public void register_avatar() throws BusinessException;
	
	
	/**发布 机会***/
	public void chanceAdd() throws BusinessException;
	
	/** 机会 列表***/
	public void chanceLists(int num) throws BusinessException;
	
	/**机会  详情 ***/
	public void chanceDetails() throws BusinessException;
	
	/**申请报名某机会Task/apply***/
	public void chanceApply() throws BusinessException;
	
	/**获取 用户 最新 动态
	 * @param pageNum ***/
	public void dynamicIndex(int pageNum) throws BusinessException;
	
	/**获取某个用户的动态列表***/
	public void dynamicWho(int pageNum) throws BusinessException;
	
	/**获取当前用户自己的动态列表***/
	public void dynamicMy(int pageNum) throws BusinessException;
	
	/**发布动态Feed/addfeed**/
	public void dynamicAdd() throws BusinessException;
	
	/**是否可以聊天Message/access**/
	public void chatAccess() throws BusinessException;
	
	/** 支付聊天费用/Message/chat_pay**/
	public void chatPay() throws BusinessException;
	
	/**上传动态照片Feed/add_photo**/
	public void dynamicAdd_pic(List<PicValuePair> picValuePair) throws BusinessException;
	
	
	/**评论某动态Feed/add_reply***/
	public void dynamicRepaly() throws BusinessException;
	
	/**点赞Feed/favour***/
	public void dynamicFavour() throws BusinessException;
	
	/**点赞Feed/unfavour***/
	public void dynamicUnFavour() throws BusinessException;
	
	/**搜索用户Zone/search***/
	public void search() throws BusinessException;
	
	/**附近的人Zone/near***/ 
	public void near() throws BusinessException;
	/**
	 * 某个用户的资料
	 */
	public void userinfo() throws BusinessException;
	public void moreinfo() throws BusinessException;
	
	/**获取我的好友列表Zone/friend***/
	public void myfriend() throws BusinessException;
	
	/**获取我的关注列表Zone/following***/
	public void myfollowing() throws BusinessException;
	
	/**获取关注我的人列表,即粉丝Zone/followed***/
	public void myfollowed() throws BusinessException;
	
	/**获取推荐用户列表Zone/pushuser***/
	public void mypushuser() throws BusinessException;
	
	/**获取黑名单列表Zone/black***/
	public void myblack() throws BusinessException;
	
	/**充值订单Wallet/charge_order***/
	public void mycharge_order() throws BusinessException;
	
	/**充值完成Wallet/complete_order***/
	public void mycomplete_order() throws BusinessException;
	
	/**取消订单Wallet/cancel_order***/
	public void mycancel_order() throws BusinessException;
	
	/**提现Wallet/withdraw***/
	public void mywithdraw() throws BusinessException;
	
	/**提现记录Wallet/withdraw_lists***/
	public void mywithdeaw_lists() throws BusinessException;
	
	/**星探、粉丝邀约某明星Invite/add***/
	public void myinvite_add() throws BusinessException;
	
	/**我的邀约记录 星探或粉丝邀约明星的记录Invite/appoint***/
	public void myinvite_appoint() throws BusinessException;
	
	/**邀约我的记录 明星被邀约的记录Invite/offer***/
	public void myinvite_offer	() throws BusinessException;
	
	/**接受邀约Invite/accept***/
	public void myinvite_accept() throws BusinessException;
	
	/**支付邀约费用 确定支付邀约费用，发生在发起邀约方Invite/payment***/
	public void myinvite_payment() throws BusinessException;
	
	public void myapp_version() throws BusinessException;
	
	public void mybaseinfo() throws BusinessException;
	
	public void mybaseinfoupdate() throws BusinessException;
	public void mymoreinfoupdate() throws BusinessException;
	// 充值订单
	public void walletOrder() throws BusinessException;
	// 确认支付
	public void walletPay() throws BusinessException;
	// 提现
	public void walletWithdraw() throws BusinessException;
	// 提现记录
	public void walletWithdrawList() throws BusinessException;
	// 提现记录
	public void mylocation_update() throws BusinessException;
	//
	public void myuser_service() throws BusinessException;
	//
	public void myuser_addservice() throws BusinessException;
	public void myuser_delservice() throws BusinessException;
	public void myapp_config() throws BusinessException;
	//
	public void myservice_list() throws BusinessException;
	public void myuser_loyal() throws BusinessException;
	public void myuser_loyal_item(List<PicValuePair> picValuePair) throws BusinessException;
	public void mydo_social() throws BusinessException;
	public void myreport() throws BusinessException;
	public void myphoto() throws BusinessException;
	public void myphotoupdate(List<PicValuePair> picValuePair) throws BusinessException;
	public void myphotodel() throws BusinessException;
	public void myavatarupdate(List<PicValuePair> picValuePair) throws BusinessException;
	public void myavatardel() throws BusinessException;
	public void dictionary() throws BusinessException;
	public void orderlist() throws BusinessException;
	public void mytasklists() throws BusinessException;
	public void myapplytasklists() throws BusinessException;
	public void myworksadd(List<PicValuePair> picValuePair) throws BusinessException;
}
