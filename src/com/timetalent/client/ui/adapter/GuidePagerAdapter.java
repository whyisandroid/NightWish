package com.timetalent.client.ui.adapter;

import java.util.List;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;


/******************************************
 * 类描述： TODO
 * 类名称：GuidePagerAdapter  
 * @version: 1.0
 * @author: ghf
 * @time: 2014-9-20 下午6:28:21 
 ******************************************/
public class GuidePagerAdapter extends PagerAdapter{
	private List<View> views;
	private Activity activity;

	/**
	 * 类的构造方法
	 * 创建一个新的实例 GuidePagerAdapter.
	 * @param 
	 */
	public GuidePagerAdapter(List<View> views, Activity activity) {
		this.views = views;
        this.activity = activity;

	}
	// 获取要滑动的控件的数量
	@Override
	public int getCount() {
		
		return views.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		
		return arg0 == arg1;
	}
	

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		
		container.addView(views.get(position));
		return views.get(position);
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// TODO Auto-generated method stub
		container.removeView(views.get(position));
	}

}
