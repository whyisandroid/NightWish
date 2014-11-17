package com.timetalent.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.DatePicker;
import android.widget.TextView;


public class StringUtil {
	/** 
	 *  方法描述:   把时间转换成需要的时间格式
	 *  方法名称:   getTime 
	 *  @return    			 
	 *  返回类型:   String    	  	
	 */
	public static String getToTime(String time) {
		if(!TextUtils.isEmpty(time)){
			return getToTime(time,"yyyy-MM-dd","yyyy-MM-dd");
		}
		return "";
	}
	
	
	/** 
	 *  方法描述:   把时间转换成需要的时间格式
	 *  方法名称:   getTime 
	 *  @return    			 
	 *  返回类型:   String    	  	
	 */
	public static String getToTime(String time,String oldFormat,String newFormat) {
		SimpleDateFormat formart  = new SimpleDateFormat(oldFormat);
		Date date = null;
		try {
			date = formart.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new SimpleDateFormat(newFormat).format(date);
	}
	
	
	/**
	  * 方法描述：添加日期选择
	  * @param context
	  * @param textView
	  * @author: wanghy
	  * @time: 2014-10-19 下午3:20:27
	 */
	public static void getData(Context context,final TextView textView){
		String date = textView.getText().toString();
		int year = 2014;
		int month = 10;
		int day = 1;
		if(date != null){
			String[] dates = date.split("-");
			if(dates.length == 3){
				year = Integer.valueOf(dates[0]);
				month = Integer.valueOf(dates[1])-1;
				day = Integer.valueOf(dates[2]);
			}
		}
		DatePickerDialog dialog = new DatePickerDialog(context, new OnDateSetListener() {
			
			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				String value = year+"-"+(monthOfYear+1)+"-"+dayOfMonth;
				textView.setText(StringUtil.getToTime(value));
			}
		}, year, month, day);
		dialog.show(); 
	}
	
	/**
	  * 方法描述：获取本地相册
	  * @author: wanghy
	  * @time: 2014-10-19 下午4:11:46
	  */
	public static void doGoToImg(Context mContext) {
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_PICK);
		intent.setType("image/*");
		((Activity)mContext).startActivityForResult(intent, 0);
	}
	
	
	
	
	/**
	 *  账号验证
	 * @param s
	 * @return
	 */
	public static String accountName(String s){
		String validation = "";
		if (TextUtils.isEmpty(s)) {
			validation ="请输入账号"; 
		}
		
	/*	else if (!RegExpUtil.isMobileNO(s) && !RegExpUtil.emailValidation(s)) {
			validation = "请输入正确账号"; 
		}*/
		return validation;
	}
	
	/**
	 * 验证密码
	 * @param s
	 * @return
	 */
	public static String pwd(String s){
		String validation = "";
		if (TextUtils.isEmpty(s)) {
			validation = "请输入密码"; 
		}else if (s.length() > 16 || s.length() < 6) {
			validation =  "请输入密码（6-16位之间）"; 
		}
		return validation;
	}
	
	/**
	 * 手机号码验证
	 * @param s
	 * @return
	 */
	public static String moblie(String s){
		String validation = "";
		if (TextUtils.isEmpty(s)) {
			validation =  "请填写手机号码"; 
		}else if (!RegExpUtil.isMobileNO(s)) {
			validation =  "请填写正确的手机号码"; 
		}
		return validation;
	}
}
