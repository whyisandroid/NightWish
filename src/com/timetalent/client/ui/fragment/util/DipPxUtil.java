package com.timetalent.client.ui.fragment.util;

import android.content.Context;
import android.util.Log;

public class DipPxUtil {
    /** * 根据手机的分辨率从dp 的单位 转成为px(像素) */ 
    public static int dip2px(Context context, float dpValue) { 
            final float scale = context.getResources().getDisplayMetrics().density;
//            Log.i("像素", ""+context.getResources().getDisplayMetrics().densityDpi);
            return (int) (dpValue * scale + 0.5f); 
    } 

    /** * 根据手机的分辨率从px(像素) 的单位 转成为dp */ 
    public static int px2dip(Context context, float pxValue) { 
            final float scale = context.getResources().getDisplayMetrics().density; 
            return (int) (pxValue / scale + 0.5f);
    }
    
    /**
     * 获取屏幕的宽
     * @param context
     * @return
     */
    public static int screenwpx(Context context){
    	return context.getResources().getDisplayMetrics().widthPixels;
    }
    /**
     * 获取屏幕的高
     * @param context
     * @return
     */
    public static int screenhpx(Context context){
    	return context.getResources().getDisplayMetrics().heightPixels;
    }
    
    /**
     * 百分比布局宽 返回px
     */
    public static int baifenbi2pxw(Context context,float baifenbi){
    	int px;
    	px = (int) (context.getResources().getDisplayMetrics().widthPixels * baifenbi + 0.5f);
    	return px;
    }
    
    /**
     * 百分比布局高 返回px
     */
    public static int baifenbi2pxh(Context context,float baifenbi){
    	int px;
    	px = (int) (context.getResources().getDisplayMetrics().heightPixels * baifenbi + 0.5f);
    	return px;
    }
}
