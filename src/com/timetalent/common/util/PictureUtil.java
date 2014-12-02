package com.timetalent.common.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.timetalent.client.ui.fragment.util.Background1;
import com.timetalent.client.ui.view.CircleBitmapDisplayer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
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
	/**
	 * 获取网络图片
	 * http://www.adarrive.com/Public/images/slide-6.png
	 */
	public static Drawable getImage(String Url, String userid, String pictureid) {
		if (Url == null || Url.equals("")) {
			return new Background1();
		}
		Drawable drawable = new Background1();
		// //////////此处判断目录中是否存在此图片文件，存在的话直接用这个图片，不存在的话下载图片并存到目录中///////////////
		if (android.os.Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED)) {
			int count = Url.split("\\.").length;
			if (count > 0) {
				// /处理文件
				File imagefile = new File(Config.IMAGEPATH + userid + "_" + pictureid
						+ "." + Url.split("\\.")[count - 1]);
				if (imagefile.exists()) {
					drawable = Drawable.createFromPath(Config.IMAGEPATH + userid + "_"
							+ pictureid + "." + Url.split("\\.")[count - 1]);
					return drawable;
				}
			}

		}
		// /////////////////////////////

		try {
			drawable = Drawable.createFromStream(new URL(Url).openStream(),
					"image.png");
			if(drawable == null){

				return new Background1();
			
			}
			// //////存图片到sd卡/////////////
			BitmapDrawable bd = (BitmapDrawable) drawable;
			Bitmap bm = bd.getBitmap();
			if(bm == null){


				return new Background1();
			
			
			}
			int count = Url.split("\\.").length;
			if (count > 0) {
				File file = new File(Config.IMAGEPATH + userid + "_" + pictureid + "."
						+ Url.split("\\.")[count - 1]);
				boolean sdCardExist = android.os.Environment
						.getExternalStorageState().equals(
								android.os.Environment.MEDIA_MOUNTED); // 判断sd卡是否存在
				if (sdCardExist) {
					File imgpath = new File(Config.DOWNLOADPATH);
					File ad = new File(Config.IMAGEPATH);
					// 如果文件夹不存在
					if (imgpath != null && !imgpath.exists()) {
						// 按照指定的路径创建文件夹
						imgpath.mkdir();
						// 如果文件夹不存在
					} else if (ad != null && !ad.exists()) {
						// 按照指定的路径创建文件夹
						ad.mkdir();
					}
					// 检查图片是否存在
					if (file != null && !file.exists()) {
						file.createNewFile();
					}
				}
				BufferedOutputStream bos = new BufferedOutputStream(
						new FileOutputStream(file));
				if (Url.split("\\.")[count - 1].equals("png")
						|| Url.split("\\.")[count - 1].equals("PNG")) {
					bm.compress(Bitmap.CompressFormat.PNG, 100, bos);
				} else {
					bm.compress(Bitmap.CompressFormat.JPEG, 100, bos);
				}
				bos.flush();
				bos.close();
			}
			return drawable;
		} catch (IOException e) {		
		return drawable;
	}
	}
	/**
	 * 删除SD卡或者手机的缓存图片和目录
	 */
	public static long deleteFile() {
		long size = 0;
		File dirFile = new File(Config.IMAGEPATH);
		if(! dirFile.exists()){
			return -1024;
		}
		if (dirFile.isDirectory()) {
			String[] children = dirFile.list();
			for (int i = 0; i < children.length; i++) {
				File temp = new File(dirFile, children[i]);
				size+= temp.length();
				temp.delete();
			}
		}
		return size;
	}
}
