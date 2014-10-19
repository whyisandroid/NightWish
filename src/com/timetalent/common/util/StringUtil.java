package com.timetalent.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
	  * 方法描述：TODO
	  * @author: wanghy
	  * @time: 2014-10-19 下午4:11:46
	  */
	public static void doGoToImg(Context mContext) {
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_PICK);
		intent.setType("image/*");
		mContext.startActivity(intent);
	}
}