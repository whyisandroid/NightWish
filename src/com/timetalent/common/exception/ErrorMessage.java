package com.timetalent.common.exception;

public class ErrorMessage implements java.io.Serializable{
	
	private static final long serialVersionUID=7714918001204577L;
	/**
	 * 异常类型
	 */
	private int code;
	
	/**
	 * 异常名称
	 */         
	private String name;
	
	/**
	 * 异常信息
	 */
	private String message;
	
	
	/**
	 * 详细信息
	 */
	private String details;
	
	
	public ErrorMessage(int code,String name,String mesg,String details){
		this.code = code;
		this.name = name;
		this.message = mesg;
		this.details = details;
	}
	
	
	public ErrorMessage(int code,String mesg){
		this.message=mesg;
		this.code=code;
	}
	
	public ErrorMessage(String message){
		this.message=message;
	}
	
	public ErrorMessage(String mesg,String details){
		this.message=mesg;
		this.details=details;
	}
	
	
	public ErrorMessage(){
		
	}
	

	public String getDetails() {
		return details;
	}


	public void setDetails(String details) {
		this.details = details;
	}


	public int getCode() {
		return code;
	}



	public void setCode(int code) {
		this.code = code;
	}



	public String getMessage() {
		return message;
	}



	public void setMessage(String message) {
		this.message = message;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}

	public String getSimpleString(){
		return null;
	}
}
