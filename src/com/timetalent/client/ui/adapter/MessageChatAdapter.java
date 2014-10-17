package com.timetalent.client.ui.adapter;

import java.lang.reflect.Field;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.timetalent.client.R;
import com.timetalent.client.entities.ChatMsg;


/******************************************
 * 类描述： 对话 adapter
 * 类名称：MessageChatAdapter  
 * @version: 1.0
 * @author: wanghy
 * @time: 2014-10-12 下午7:58:51 
 ******************************************/
public class MessageChatAdapter extends BaseAdapter{
	
	
	public static interface IMsgViewType {
		int IMVT_COM_MSG = 0;
		int IMVT_TO_MSG = 1;
	}
	
	private List<ChatMsg> list;

	
	private Context mContext;
	
	private LayoutInflater mInflater;
	
	/**
	 * 类的构造方法
	 * 创建一个新的实例 DynamicAdapter.
	 * @param 
	 */
	public MessageChatAdapter(Context mContext, List<ChatMsg> list) {
		this.mContext = mContext;
		this.list = list;
		mInflater = LayoutInflater.from(mContext);
	}
	
	@Override
	public int getCount() {
		return list.size();
	}

	
	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	
	@Override
	public long getItemId(int position) {
		return position;
	}

	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		ChatMsg chat = list.get(position);
		boolean isComMsg = chat.getMsgType();
		ViewHolder viewHolder = null;

		if (isComMsg) {
			convertView = mInflater.inflate(R.layout.message_chat_left, null);
		} else {
			convertView = mInflater.inflate(R.layout.message_chat_right, null);
		}
		viewHolder = new ViewHolder();
		viewHolder.tvContent = (TextView) convertView
				.findViewById(R.id.tv_chatcontent);
		viewHolder.tvSendTime = (TextView) convertView
				.findViewById(R.id.tv_sendtime);  
		viewHolder.isComMsg = isComMsg;	
		
		
		try {
			String zhengze = "f0[0-9]{2}|f10[0-7]";											//正则表达式，用来判断消息内是否有表情
			SpannableString spannableString = getExpressionString(mContext, chat.getText(), zhengze);
			viewHolder.tvContent.setText(spannableString);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		viewHolder.tvSendTime.setText(chat.getDate());	
		return convertView;
	}
	
	static class ViewHolder {
		public TextView tvSendTime;
		public TextView tvUserName;
		public TextView tvContent;
		public TextView tvTime;
		public boolean isComMsg = true;
	}
	
	
	 /**
     * 得到一个SpanableString对象，通过传入的字符串,并进行正则判断
     * @param context
     * @param str
     * @return
     */
    public static SpannableString getExpressionString(Context context,String str,String zhengze){
    	SpannableString spannableString = new SpannableString(str);
        Pattern sinaPatten = Pattern.compile(zhengze, Pattern.CASE_INSENSITIVE);		//通过传入的正则表达式来生成一个pattern
        try {
            dealExpression(context,spannableString, sinaPatten, 0);
        } catch (Exception e) {
            Log.e("dealExpression", e.getMessage());
        }
        return spannableString;
    }
    
	/**
	 * 对spanableString进行正则判断，如果符合要求，则以表情图片代替
	 * @param context
	 * @param spannableString
	 * @param patten
	 * @param start
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @throws NumberFormatException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
    public static void dealExpression(Context context,SpannableString spannableString, Pattern patten, int start) throws SecurityException, NoSuchFieldException, NumberFormatException, IllegalArgumentException, IllegalAccessException {
    	Matcher matcher = patten.matcher(spannableString);
        while (matcher.find()) {
            String key = matcher.group();
            if (matcher.start() < start) {
                continue;
            }
            Field field = R.drawable.class.getDeclaredField(key);
			int resId = Integer.parseInt(field.get(null).toString());		//通过上面匹配得到的字符串来生成图片资源id
            if (resId != 0) {
                Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resId);	
                ImageSpan imageSpan = new ImageSpan(bitmap);				//通过图片资源id来得到bitmap，用一个ImageSpan来包装
                int end = matcher.start() + key.length();					//计算该图片名字的长度，也就是要替换的字符串的长度
                spannableString.setSpan(imageSpan, matcher.start(), end, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);	//将该图片替换字符串中规定的位置中
                if (end < spannableString.length()) {						//如果整个字符串还未验证完，则继续。。
                    dealExpression(context,spannableString,  patten, end);
                }
                break;
            }
        }
    }
}
