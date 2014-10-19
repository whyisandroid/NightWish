package com.timetalent.client.ui.fragment.util;


import android.graphics.drawable.GradientDrawable;



public class Background3 extends GradientDrawable{

	public Background3(){
		super(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{0x00000000,0x00000000,0x00000000});
		init();
	}
	
	public void init(){
		this.setCornerRadii(new float[]{0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f,0.0f});
	}
	
}
