package com.timetalent.client.ui.adapter;

import java.io.File;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.timetalent.client.R;
import com.timetalent.client.ui.dynamic.DynamicAddActivity;
import com.timetalent.common.util.CommonData;
import com.timetalent.common.util.Config;
import com.timetalent.common.util.LogUtil;
import com.timetalent.common.util.PictureUtil;
import com.timetalent.common.util.StringUtil;
import com.timetalent.common.util.UIUtils;


/******************************************
 * 类描述： TODO
 * 类名称：DynamicAddAdapter  
 * @version: 1.0
 * @author: why
 * @time: 2014-11-6 下午8:50:11 
 ******************************************/
public class DynamicAddAdapter extends BaseAdapter{
    /** 选择按钮TAG */
    public static final long SELECT_TAG = 0;
	   /** 添加按钮TAG */
    public static final long ADD_TAG= 1;
	private Context mContext;
	// 图片
	private ArrayList<String> mData = new  ArrayList<String>();
	private HashMap<String, SoftReference<Bitmap>> cache = new HashMap<String, SoftReference<Bitmap>>();
	//单一View的高
		private int view_wh = 0 ;
	/**
	 * 类的构造方法
	 * 创建一个新的实例 DynamicAdapter.
	 * @param 
	 */
	public DynamicAddAdapter(Context mContext,Handler mHandler,ArrayList<String> mData) {
		this.mContext = mContext;
		this.mData = mData;
	}
	
	@Override
	public int getCount() {
			return mData.size()+1;
	}

	
	@Override
	public Object getItem(int position) {
		return position;
	}

	
	@Override
	public long getItemId(int position) {
		return position;
	}

	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		//控件的宽高
				if(CommonData.getScreenWidth((DynamicAddActivity)mContext) != 0 || view_wh == 0){
					view_wh = (CommonData.getScreenWidth((DynamicAddActivity)mContext)-UIUtils.dip2px(mContext, 14))/3;
				}
		
		//初始化convertView
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(R.layout.dynamic_add_item, null);
			holder = new ViewHolder();
			holder.findView(convertView);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder)convertView.getTag();
		}
		
		if (position == mData.size()) {
			//最后一个图片
			holder.position = mData.size();
			holder.mImage.setBackgroundResource(R.drawable.btn_dynamic_add_pic);
			holder.mImageContainer.getLayoutParams().height = view_wh;
			holder.mImage.setScaleType(ImageView.ScaleType.CENTER);
			holder.mImage.getLayoutParams().width = ViewGroup.LayoutParams.WRAP_CONTENT;
			
			holder.mImageIcon.setVisibility(View.GONE);
			OnClickMessage onClickMessage = new OnClickMessage();
			onClickMessage.position = position;
			onClickMessage.tag = ADD_TAG;
			holder.mImage.setTag(onClickMessage);
			holder.mImage.setOnClickListener(mGridViewItemListener);
		} else {
			//非最后一个图片
			String path = mData.get(position);  
			holder.position = position;
			
			//Bitmap bitmap = PictureUtil.toRoundCorner(PictureUtil.decodeSampledBitmapFromFile(path, 200, 200), 0);
			//holder.mImage.setImageBitmap(bitmap);
			if (cache.get(path) != null && cache.get(path).get() != null) {
				holder.mImage.setImageBitmap(cache.get(path).get());
			} else {
				holder.mImage.setImageResource(R.color.main_grey);
				new LoadImageTask().execute(holder, position, path);
			}
			
			holder.mImage.setBackgroundResource(android.R.color.white);
			holder.mImageContainer.getLayoutParams().height = view_wh;
			holder.mImage.setScaleType(ImageView.ScaleType.FIT_XY);
			holder.mImage.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
			OnClickMessage onClickMessage = new OnClickMessage();
			onClickMessage.position = position;
			onClickMessage.tag = SELECT_TAG;
			holder.mImage.setTag(onClickMessage);
			holder.mImageIcon.setVisibility(View.VISIBLE);  
			holder.mImage.setOnClickListener(mGridViewItemListener);
		}
		
		
		return convertView;
	}
	
	static class ViewHolder {
		int position;
		FrameLayout mImageContainer;
		ImageView mImage;
		ImageView mImageIcon;
		
		public void  findView(View convertView ){
			mImage = (ImageView)convertView.findViewById(R.id.crop_image_item_image);
			mImageIcon = (ImageView)convertView.findViewById(R.id.crop_image_item_image_icon);
			mImageContainer = (FrameLayout) convertView
					.findViewById(R.id.crop_image_item_image_container);
		}
	}
	private class OnClickMessage{
		long tag;
		int position;
	}
	
	private View.OnClickListener mGridViewItemListener = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			OnClickMessage onClickMessage = (OnClickMessage)v.getTag();
			if ( onClickMessage.tag == ADD_TAG ) {
				StringUtil.doGoToImg(mContext);
			}else if (onClickMessage.tag == SELECT_TAG){
				 LogUtil.Log("点击图片路径："+mData.get(onClickMessage.position));
				 String value = mData.get(onClickMessage.position);
				 //下方是是通过Intent调用系统的图片查看器的关键代码
				 File file = new File(value);
				 Intent intent = new Intent();
				 intent.setAction(android.content.Intent.ACTION_VIEW);
				 intent.setDataAndType(Uri.fromFile(file), "image/*");
				 mContext.startActivity(intent);
			}
		}
	};
	
	private final class LoadImageTask extends AsyncTask<Object, Integer, Uri> {
		private ViewHolder holder;
		private int position;
		private String path;

		@Override
		protected Uri doInBackground(Object... params) {
			this.holder = (ViewHolder) params[0];
			this.position = (Integer) params[1];
			this.path = (String) params[2];

			try {
				File filePath = new File(Config.PATH_PIC_THUMB);
				if (!filePath.exists()) {
					filePath.mkdirs();
				}
				File f = new File(filePath, PictureUtil.getMD5(path)+".png");
				if(!f.exists()){
					Bitmap bitmap = PictureUtil.toRoundCorner(PictureUtil.decodeSampledBitmapFromFile(path, 180, 180), 0);
					PictureUtil.saveBitmapToFile(bitmap, f.getAbsolutePath());
				}
				return Uri.fromFile(f);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Uri result) {
			if (result != null && holder.mImage != null) {
					Bitmap b = PictureUtil.decodeUriAsBitmap(mContext, result);
					SoftReference<Bitmap> bitmapcache = new SoftReference<Bitmap>(b);
					cache.put(path, bitmapcache);
					holder.mImage.setImageBitmap(b);
			}
		}
	}
}
