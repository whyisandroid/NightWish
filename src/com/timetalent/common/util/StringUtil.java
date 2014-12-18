package com.timetalent.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.timetalent.client.entities.Feed;
import com.timetalent.client.entities.Task;
import com.timetalent.common.util.aes.Md5;

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
	 *  方法描述:   把时间戳 转换为时间
	 *  方法名称:   getTime 
	 *  @return    			 
	 *  返回类型:   String    	  	
	 */
	public static String transformTime(String time) {
		return transformTime(time,"yyyy年MM月dd日");
	}
	
	/** 
	 *  方法描述:   把时间戳 转换为时间
	 *  方法名称:   getTime 
	 *  @return    			 
	 *  返回类型:   String    	  	
	 */
	public static String transformTime(String time,String formant ) {
		if(!TextUtils.isEmpty(time)){
			if(RegExpUtil.isNumeric(time)){
				SimpleDateFormat sdf = new SimpleDateFormat(formant);
				return sdf.format(new Date(Long.valueOf(time)*1000));
			}else{
				return time;
			}
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
	  * 方法描述：转换为 unix 时间戳 format yyyy-MM-dd 
	  * @param time
	  * @return
	  * @author: why
	  * @time: 2014-12-18 下午3:16:46
	 */
	public static long transformDate(String time){
		return transformDate(time,"yyyy-MM-dd")/1000;
	}
	
	
	/**
	  * 方法描述：时间转换为时间戳
	  * @param time
	  * @param formant
	  * @return
	  * @author: why
	  * @time: 2014-12-18 下午3:15:27
	 */
	public static long transformDate(String time,String formant ) {
		SimpleDateFormat sdf = new SimpleDateFormat(formant);
		try {
			return sdf.parse(time).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
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
	/**
	 * 手机号码验证
	 * @param s
	 * @return
	 */
	public static String shenfenzheng(String s){
		String validation = "";
		if (TextUtils.isEmpty(s)) {
			validation =  "请填写身份证号"; 
		}else if (!RegExpUtil.personIdValidation(s)) {
			validation =  "请填写正确的身份证号"; 
		}
		return validation;
	}
	/**
	 * 验证内容非空
	  * 方法描述：TODO
	  * @param s
	  * @return
	  * @author: Administrator
	  * @time: 2014-11-20 上午9:54:21
	 */
	public static String notnull(String s){
		String validation = "";
		if (TextUtils.isEmpty(s)) {
			validation = "内容不能为空"; 
		}
		return validation;
	}


	
	/**
	  * 方法描述：获取 环信用户名  
	  * @param id
	  * @author: why
	  * @time: 2014-12-3 下午4:05:13
	  */
	public static String getEsaeUserName(String id) {
		StringBuffer sb = new StringBuffer();
		if(!TextUtils.isEmpty(id)){
			sb.append("uid_"+id);
		}
		return sb.toString();
	}
	
	/**
	  * 方法描述：获取 环信密码
	  * @param id
	  * @author: why
	  * @time: 2014-12-3 下午4:05:13
	  */
	public static String getEsaePwd(String id) {
		StringBuffer sb = new StringBuffer();
		if(!TextUtils.isEmpty(id)){
			sb.append(Md5.digist(id));
		}
		return sb.toString();
	}
	

	public static boolean containsFeed(Feed feed, List<Feed> listFeeds) {
		for (int i = 0; i < listFeeds.size(); i++) {
			if(listFeeds.get(i).getId().equals(feed.getId())){
				listFeeds.get(i).setTime_ago(feed.getTime_ago());
				listFeeds.get(i).setFavour_do(feed.getFavour_do());
				listFeeds.get(i).setFavour_num(feed.getFavour_num());
				return true;
			}
		}
		return false;
	}



	
	/**
	  * 方法描述：TODO
	  * @param task
	  * @param listTasks
	  * @return
	  * @author: why
	  * @time: 2014-12-18 下午9:07:34
	  */
	public static boolean containsTask(Task task, List<Task> listTasks) {
		for (int i = 0; i < listTasks.size(); i++) {
			if(listTasks.get(i).getId().equals(task.getId())){
				return true;
			}
		}
		return false;
	};
}
