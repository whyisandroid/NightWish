package com.timetalent.client.ui.fragment.util;


import android.graphics.drawable.GradientDrawable;



public class Background2 extends GradientDrawable{

	public Background2(){
		super(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{0x66000000,0x66000000,0x66000000});
		init();
	}
	
	public void init(){
		this.setCornerRadii(new float[]{0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f});
	}
	
}
