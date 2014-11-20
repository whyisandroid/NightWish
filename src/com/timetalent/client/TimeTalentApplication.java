package com.timetalent.client;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.preference.PreferenceManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.timetalent.common.net.AppSocketInterface;
import com.timetalent.common.net.XUtilsSocketImpl;

/******************************************
 * 类描述： 程序入口类 类名称：TimeTalentApplication
 * 
 * @version: 1.0
 * @author: why
 * @time: 2014-10-13 下午2:09:22
 ******************************************/
public class TimeTalentApplication extends Application {
	private String TAG = "TimeTalentApplication";
	public static Context applicationContext;

	/** 实例化 **/
	private static TimeTalentApplication instance;
	/** 网络链接 **/
	private static AppSocketInterface appSocket;

	public int curVersionCode; // 版本号
	public String curVersionName; // 版本名字
	public String systemVersion; 
	public String deviceID; // 设备ID
	
	// login user name
	public final String PREF_USERNAME = "username";
	private String userName = null;

	private boolean login;// 登录情况

	@Override
	public void onCreate() {
		super.onCreate();
		init();
	}

	/**
	 * 方法描述：初始化
	 * 
	 * @author: why
	 * @time: 2014-2-14 下午3:46:04
	 */
	private void init() {
		applicationContext = this;
		instance = this;
		appSocket = new XUtilsSocketImpl();
		getCurrentVersion();
		initImageLoad();
		//getDeviceID();
	}
	/**
	 * 获取当前登陆用户名
	 *
	 * @return
	 */
	public String getUserName() {
		if (userName == null) {
			SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(applicationContext);
			userName = preferences.getString(PREF_USERNAME, null);
		}
		return userName;
	}

	
	/**
	  * 方法描述：TODO
	  * @author: wanghy
	  * @time: 2014-11-16 下午11:27:48
	  */
	private void initImageLoad() {
		DisplayImageOptions options = new DisplayImageOptions.Builder()
		.cacheInMemory(true)
		.imageScaleType(ImageScaleType.IN_SAMPLE_INT) 
		.bitmapConfig(Bitmap.Config.RGB_565)// 防止内存溢出的，图片太多就这这个。还有其他设置
		.displayer(new RoundedBitmapDisplayer(5))  //圆角，不需要请删除
		                           .build();  
		
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				this)
				.memoryCacheExtraOptions(480, 800)// 缓存在内存的图片的宽和高度
				.memoryCache(new WeakMemoryCache()) 
				.memoryCacheSize(2 * 1024 * 1024) //缓存到内存的最大数据
				.defaultDisplayImageOptions(options).  //上面的options对象，一些属性配置
				build();
		ImageLoader.getInstance().init(config); //初始化
	}

	/**
	 * 方法描述：获取设备唯一标示
	 * 
	 * @author: why
	 * @time: 2014-2-21 下午8:53:03
	 */
	private void getDeviceID() {
		TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		String DEVICE_ID = tm.getDeviceId();
		if (!TextUtils.isEmpty(DEVICE_ID)) {
			deviceID = DEVICE_ID;
		} else {
			//deviceID = TCAgent.getDeviceId(this);
		}
	}

	/**
	 * @return login : return the property login.
	 */
	public boolean isLogin() {
		return login;
	}

	/**
	 * @param login
	 *            : set the property login.
	 */
	public void setLogin(boolean login) {
		this.login = login;
		if (!login) {
			//AppController.getController().getContext().clearBusinessData();
		}
	}

	/**
	 * 方法描述: 获取网络通信实例
	 * 
	 * @return
	 * @author: why
	 * @time: 2013-10-21 下午3:32:02
	 */
	public static AppSocketInterface getAppSocket() {
		return appSocket;
	}

	/**
	 * 方法描述：获取实例
	 * 
	 * @return
	 * @author: why
	 * @time: 2013-10-21 下午2:52:44
	 */
	public static TimeTalentApplication getInstance() {
		return instance;
	}

	/**
	 * 获取当前客户端版本信息
	 */
	private void getCurrentVersion() {
		try {
			PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), 0);
			curVersionName = info.versionName;
			curVersionCode = info.versionCode;
			systemVersion = android.os.Build.VERSION.SDK;
		} catch (NameNotFoundException e) {
			e.printStackTrace(System.err);
		}
	}
	
	/**
	 * 方法描述：存储token信息
	 * @author: why
	 * @time: 2014-7-1 下午3:16:35
	 */
	public void saveToken(String token) {
		AppSharedPref.getInstance(this).saveToken(token);
	}
	
	/**
	 * 方法描述：TODO
	 * @author: why
	 * @time: 2014-7-1 下午3:17:47
	 */
	public String getToken() {
		return AppSharedPref.getInstance(this).getToken();
	}
	
	
	/**
	 * 方法描述：存储token信息
	 * @author: why
	 * @time: 2014-7-1 下午3:16:35
	 */
	public void saveTokenFlag(boolean flag) {
		AppSharedPref.getInstance(this).setTokenFlag(flag);
	}


	
	
	
	
	/**
	 * 方法描述：TODO
	 * @author: why
	 * @time: 2014-7-1 下午3:17:47
	 */
	public boolean isTokenFlag() {
		return AppSharedPref.getInstance(this).getTokenFlag();
	}
}
