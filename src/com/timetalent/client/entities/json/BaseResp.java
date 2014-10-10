package com.timetalent.client.entities.json;


/******************************************
 * 类描述： 网络返回对象基类
 * 类名称：BaseResp  
 * @version: 1.0
 * @author: ly
 * @time: 2013-12-9 下午2:39:25 
 ******************************************/
public class BaseResp {
	
	private String result;
	private String errorCode;
	private String msg;
	/**
	 * @return result : return the property result.
	 */
	public String getResult() {
		return result;
	}
	/**
	 * @param result : set the property result.
	 */
	public void setResult(String result) {
		this.result = result;
	}
	/**
	 * @return errorCode : return the property errorCode.
	 */
	public String getErrorCode() {
		return errorCode;
	}
	/**
	 * @param errorCode : set the property errorCode.
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	/**
	 * @return msg : return the property msg.
	 */
	public String getMsg() {
		return msg;
	}
	/**
	 * @param msg : set the property msg.
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BaseResp [result=" + result + ", errorCode=" + errorCode
				+ ", msg=" + msg + "]";
	}
}
