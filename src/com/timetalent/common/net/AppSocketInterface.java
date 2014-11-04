package com.timetalent.common.net;


import com.timetalent.common.exception.BusinessException;

/******************************************
 * 类描述： 网络层接口
 * 类名称：AppSocketInterface   短连接
 * @version: 1.0
 * @author: why
 * @time: 2014 - 1-11 上午11:48:01 
 ******************************************/
public interface AppSocketInterface {
	/**
	 * 
	  * 方法描述：短连接
	  * @param request
	  * @return
	  * @author: why
	  * @time: 2013-10-11 上午11:53:01
	 */
	public <T>T  shortConnect(Request<T> request) throws BusinessException;
	
	/**
	  * 方法描述：上传图片
	  * @param request
	  * @return
	  * @throws BusinessException
	  * @author: why
	  * @time: 2014-11-4 下午6:11:42
	 */
	public <T>T  imageLoad(Request<T> request) throws BusinessException;
	/**
	 * 
	  * 方法描述 ：长连接
	  * @param request
	  * @return
	  * @author: why
	  * @time: 2013-10-11 上午11:53:01
	 */
	
	public <T>T longConnect(Request<T> request);
}
