package com.timetalent.common.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.timetalent.client.ui.view.CircleBitmapDisplayer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;


/******************************************
 * 类描述： 图片处理 公共类
 * 类名称：PictureUtil  
 * @version: 1.0
 * @author: why
 * @time: 2014-10-27 下午4:34:35 
 ******************************************/
public class PictureUtil {
	
	/**
	  * 方法描述：TODO
	  * @author: why
	  * @time: 2014-10-27 下午4:35:32
	  */
	public static void headPic(Activity activity,int crop,File sdcardTempFile) {
		Intent intent = new Intent("android.intent.action.PICK");
		intent.setDataAndType(MediaStore.Images.Media.INTERNAL_CONTENT_URI, "image/*");
		intent.putExtra("output", Uri.fromFile(sdcardTempFile));
		intent.putExtra("crop", "true");
		intent.putExtra("aspectX", 1);// 裁剪框比例
		intent.putExtra("aspectY", 1);
		intent.putExtra("outputX", crop);// 输出图片大小
		intent.putExtra("outputY", crop);
		activity.startActivityForResult(intent, 0);
	}
	
	/**
	  * 方法描述：获取本地相册
	  * @author: wanghy
	  * @time: 2014-10-19 下午4:11:46
	  */
	public static void doGoToImg(Activity mContext,File sdcardTempFile) {
		Intent intent = new Intent("android.intent.action.PICK");
		intent.setDataAndType(MediaStore.Images.Media.INTERNAL_CONTENT_URI, "image/*");
		intent.putExtra("output", Uri.fromFile(sdcardTempFile));
		intent.putExtra("crop", "true");
		intent.putExtra("aspectX", 600);// 裁剪框比例
		intent.putExtra("aspectY", 350);
		intent.putExtra("outputX", 600);// 输出图片大小
		intent.putExtra("outputY", 350);
		mContext.startActivityForResult(intent, 0);
	}
	
	/**
	 * 检测图片名字是否合法  必须是 JPG  ＪＰＥＧ　　ＰＮＧ　
	 * @param name
	 * @return
	 */
	public static boolean isPicture(String name){
		boolean flag = false;
		if(null == name){
			return flag;
		}
		String value = name.toLowerCase();
		if(value.endsWith(".jpg")){
			flag = true;
		}else if(value.endsWith(".png")){
			flag = true;
		}else if(value.endsWith(".jpeg")){
			flag = true;
		}
		return flag;
	}
	
	/**
	 * uri转成图片
	 * 
	 * @param context
	 * @param uri
	 * @return
	 */
	public static Bitmap decodeUriAsBitmap(Context context, Uri uri){ 
	     Bitmap bitmap = null; 
	     try { 
	         bitmap = BitmapFactory.decodeStream(context.getContentResolver().openInputStream(uri)); 
	     } catch (FileNotFoundException e) { 
	         e.printStackTrace(); 
	         return null; 
	     } 
	     return bitmap; 
	 } 
	
	/**
	 * Save Bitmap to a file. 保存图片到SD卡。
	 * 
	 * @param bitmap
	 * @param file
	 * @return error message if the saving is failed. null if the saving is
	 *         successful.
	 * @throws IOException
	 */
	public static void saveBitmapToFile(Bitmap bitmap, String _file) throws IOException {
		BufferedOutputStream os = null;
		try {
			File file = new File(_file);
			int end = _file.lastIndexOf(File.separator);
			String _filePath = _file.substring(0, end);
			File filePath = new File(_filePath);
			if (!filePath.exists()) {
				filePath.mkdirs();
			}
			file.createNewFile();
			os = new BufferedOutputStream(new FileOutputStream(file));
			bitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
			
		} finally {
			if (os != null) {
				try {
					os.close();
					Log.i("saveBitmapToFile", "保存的图片： "+ _file);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 得到字符串的MD5值
	 * 
	 * @param content
	 * @return
	 */
	public static String getMD5(String content) {
		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			digest.update(content.getBytes());
			return getHashString(digest);

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static String getHashString(MessageDigest digest) {
		StringBuilder builder = new StringBuilder();
		for (byte b : digest.digest()) {
			builder.append(Integer.toHexString((b >> 4) & 0xf));
			builder.append(Integer.toHexString(b & 0xf));
		}
		return builder.toString();
	}
	
	/**
	 * 比例大小压缩方法
	 * @param pathName
	 * @param reqWidth
	 * @param reqHeight
	 * @return
	 */
	public static Bitmap decodeSampledBitmapFromFile(String pathName,
			int reqWidth, int reqHeight) {

		// First decode with inJustDecodeBounds=true to check dimensions
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inPreferredConfig = Bitmap.Config.ARGB_8888;//降低图片从ARGB888到RGB565
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(pathName, options);

		// Calculate inSampleSize
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;
		if (height > reqHeight || width > reqWidth) {
			if (width > height) {
				inSampleSize = Math.round((float) height / (float) reqHeight);
			} else {
				inSampleSize = Math.round((float) width / (float) reqWidth);
			}
		}
		options.inSampleSize = inSampleSize;

		// Decode bitmap with inSampleSize set
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeFile(pathName, options);
	}
	
	/**
	 * 生成圆角图片
	 * @param bitmap
	 * @param pixels
	 * @return
	 */
    public static Bitmap toRoundCorner(Bitmap bitmap, int pixels) {  
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);  
        Canvas canvas = new Canvas(output);  
        final int color = 0xff424242;  
        final Paint paint = new Paint();  
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());  
        final RectF rectF = new RectF(rect);  
        final float roundPx = pixels;  
        paint.setAntiAlias(true);  
        canvas.drawARGB(0, 0, 0, 0);  
        paint.setColor(color);  
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);  
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));  
        canvas.drawBitmap(bitmap, rect, rect, paint); 
        return output;  
    }
    
    
    /**
     * 
      * 方法描述：TODO
      * @param bitmap
      * @return
      * @author: why
      * @time: 2014-11-24 上午11:32:38
     */
    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap) {

        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(),android.graphics.Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final Paint paint = new Paint();
        //保证是方形，并且从中心画
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int w;
        int deltaX = 0;
        int deltaY = 0;
        if (width <= height) {
            w = width;
            deltaY = height - w;
        } else {
            w = height;
            deltaX = width - w;
        }
        final Rect rect = new Rect(deltaX, deltaY, w, w);
        final RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        //圆形，所有只用一个
        
        int radius = (int) (Math.sqrt(w * w * 2.0d) / 2);
        canvas.drawRoundRect(rectF, radius, radius, paint);

        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }

	
	/**
	  * 方法描述：TODO
	  * @return
	  * @author: why
	  * @time: 2014-11-24 上午11:33:27
	  */
	public static DisplayImageOptions getCircleOption() {
	    DisplayImageOptions options = new DisplayImageOptions.Builder()
	    .cacheInMemory(true)
	    .cacheOnDisk(true)
	    .displayer(new CircleBitmapDisplayer())
	    .build();
		return options;
	}
}
