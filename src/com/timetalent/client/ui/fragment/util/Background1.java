package com.timetalent.client.ui.fragment.util;


import android.graphics.drawable.GradientDrawable;



public class Background1 extends GradientDrawable{

	public Background1(){
		super(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{0xFFFFFFFF,0xFFFFFFFF,0xFFFFFFFF});
		init();
	}
	
	public void init(){
		this.setCornerRadii(new float[]{0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f});
	}
	
}
