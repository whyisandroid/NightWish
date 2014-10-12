package com.timetalent.common.util;

import com.timetalent.client.R;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;


/******************************************
 * 类描述： UI 工具类 
 * 类名称：UIUtils
 * @version: 1.0
 * @author: why
 * @time: 2014-5-7 下午4:50:55
 ******************************************/
public class UIUtils {
	/**
	  * 方法描述：设置listView 的高度 等于所有元素相加
	  * @param lv
	  * @param la
	  * @author: why
	  * @time: 2014-5-7 下午4:51:40
	 */
	public static void setListViewHeight(ListView lv, BaseAdapter la) {
		// calculate height of all items.
		int h = 0;
		final int cnt = la.getCount();
		for (int i = 0; i < cnt; i++) {
			View item = la.getView(i, null, lv);
			item.measure(0, 0);
			h += item.getMeasuredHeight();
		}
		// reset ListView height
		ViewGroup.LayoutParams lp = lv.getLayoutParams();
		lp.height = h + (lv.getDividerHeight() * (cnt - 1));
		lv.setLayoutParams(lp);
	}

	
	/**
	  * 方法描述：TODO
	  * @param main_top_right
	  * @param f906
	  * @author: wanghy
	  * @time: 2014-10-12 下午2:48:45
	  */
	public static void setDrawableLeft(Context context,TextView textView, int id) {
		textView.setVisibility(View.VISIBLE);
		Drawable drawable= context.getResources().getDrawable(id);
		/// 这一步必须要做,否则不会显示.
		drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
		textView.setCompoundDrawables(drawable,null,null,null);
	}
}
