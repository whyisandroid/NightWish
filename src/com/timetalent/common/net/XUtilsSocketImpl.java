package com.timetalent.common.net;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseStream;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.util.CookieUtils;
import com.timetalent.client.service.AppController;
import com.timetalent.common.exception.BusinessException;
import com.timetalent.common.exception.ErrorMessage;
import com.timetalent.common.util.Json_U;
import com.timetalent.common.util.LogUtil;
import com.timetalent.common.util.aes.MD5Test;

/******************************************
 * 类描述： XUtils 框架实现网络处理 类名称：XUtilsSocketImpl
 * 
 * @version: 1.0
 * @author: why
 * @time: 2014-2-12 上午10:46:38
 ******************************************/
public class XUtilsSocketImpl implements AppSocketInterface {
	private static HttpUtils httpUtils;
	public final static int TIMEOUT_SOCKET = 30 * 1000; // 超时时间，默认10秒
	public final static int RETRY_TIME = 3;// 重试次数
	public final static String CHARSET = "UTF-8";
	private CookieUtils cookieUtils;

	@SuppressWarnings("unchecked")
	@Override
	public <T> T shortConnect(Request<T> request) throws BusinessException {

		if (!NetWorkHelper.isNetworkAvailable(AppController.getController().getCurrentActivity())) {
			throw new BusinessException(new ErrorMessage("网络无法连接"));
		}
		String value = "";
		HttpUtils httpUtils = getHttpUtils();
		try {
			RequestParams params = new RequestParams();
			List<NameValuePair> nameValuePairs = (List<NameValuePair>) request
					.getParameter(Request.AJAXPARAMS);
			if (nameValuePairs == null) {
				nameValuePairs = new ArrayList<NameValuePair>();
			}
			String sing = md5Sign(nameValuePairs); 
			nameValuePairs.add(new BasicNameValuePair("_sign",sing));
			params.addBodyParameter(nameValuePairs);
			LogUtil.Log("sendHttp", request.getUrl() + nameValuePairs.toString());
			ResponseStream responseStream = httpUtils.sendSync(HttpRequest.HttpMethod.POST,
					request.getUrl(), params);

			value = responseStream.readString();  
			LogUtil.Log("XUtilsSocketImpl", value);
		} catch (com.lidroid.xutils.exception.HttpException e) {
			e.printStackTrace();
			throw new BusinessException(new ErrorMessage("网络故障，请您稍后再试"));
		} catch (IOException e) {
			e.printStackTrace();
			throw new BusinessException(new ErrorMessage("网络故障，请您稍后再试"));
		}

		if (value == null) {
			throw new BusinessException(new ErrorMessage("网络故障，请您稍后再试"));
		}
		return Json_U.parseJsonToObj(value, request.getR_calzz());
	}


	
	/**
	  * 方法描述：TODO
	  * @return
	  * @author: why
	 * @throws IOException 
	  * @time: 2014-10-21 下午8:05:52
	  */
	private String md5Sign(List<NameValuePair> nameValuePairs) throws IOException {
		HashMap<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < nameValuePairs.size(); i++) {
			NameValuePair value = nameValuePairs.get(i);
			map.put(value.getName(),value.getValue());
		}
		return MD5Test.getSignature(map, "999999");
	}


	@Override
	public <T> T longConnect(Request<T> request) {
		// TODO Auto-generated method stub
		return null;
	}

	public synchronized HttpUtils getHttpUtils() {
		if (httpUtils == null) {
			cookieUtils = new CookieUtils(AppController.getController().getCurrentActivity());
			httpUtils = new HttpUtils();
			httpUtils.configResponseTextCharset(CHARSET);
			httpUtils.configTimeout(TIMEOUT_SOCKET);
			httpUtils.configRequestRetryCount(RETRY_TIME);
			httpUtils.configCookieStore(cookieUtils);
		}
		return httpUtils;
	}
}
