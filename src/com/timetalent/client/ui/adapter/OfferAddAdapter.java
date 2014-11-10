package com.timetalent.client.ui.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.timetalent.client.R;
import com.timetalent.client.entities.Job;
import com.timetalent.common.util.StringUtil;


/******************************************
 * 类描述： 机会 adapter
 * 类名称：DynamicAdapter  
 * @version: 1.0
 * @author: wanghy
 * @time: 2014-10-12 下午7:58:51 
 ******************************************/
public class OfferAddAdapter extends BaseAdapter{
	
	private Context mContext;
	private List<Job> list = new ArrayList<Job>();
	
	/**
	 * 类的构造方法
	 * 创建一个新的实例 DynamicAdapter.
	 * @param 
	 */
	public OfferAddAdapter(Context mContext,List<Job> list) {
		this.mContext = mContext;
		this.list = list;
	}
	
	@Override
	public int getCount() {
		return list.size();
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
	public View getView(final int position, View convertView, ViewGroup parent) {
		Job job = list.get(position);
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(R.layout.chance_offer_add_item, null);
		}
		
		final	TextView tv_chance_data2 = (TextView)convertView.findViewById(R.id.tv_chance_data2);
		RelativeLayout	rl_chance_data2 = (RelativeLayout)convertView.findViewById(R.id.rl_chance_data2);
		final	TextView	tv_chance_data3 = (TextView)convertView.findViewById(R.id.tv_chance_data3);
		RelativeLayout rl_chance_data3 = (RelativeLayout)convertView.findViewById(R.id.rl_chance_data3);
		final EditText et_chance_add_job = (EditText)convertView.findViewById(R.id.et_chance_add_job);
		final EditText et_chance_add_job_info = (EditText)convertView.findViewById(R.id.et_chance_add_job_info);
		
		
		tv_chance_data2.setText(job.getWork_date_start());
		tv_chance_data3.setText(job.getWork_date_end());
		et_chance_add_job.setText(job.getJob());
		et_chance_add_job_info.setText(job.getDescription());
		
		tv_chance_data2.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}
			@Override
			public void afterTextChanged(Editable s) {
				list.get(position).setWork_date_start(tv_chance_data2.getText().toString());
			}
		});
		
		
		
		tv_chance_data3.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}
			@Override
			public void afterTextChanged(Editable s) {
				list.get(position).setWork_date_end(tv_chance_data3.getText().toString());
			}
		});
		
		
		et_chance_add_job.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				list.get(position).setJob(et_chance_add_job.getText().toString());
			}
		});
		
		et_chance_add_job_info.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				list.get(position).setDescription(et_chance_add_job_info.getText().toString());
			}
		});
		
		rl_chance_data2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				StringUtil.getData(mContext,tv_chance_data2);
			}
		});
		rl_chance_data3.setOnClickListener(new  OnClickListener() {
			@Override
			public void onClick(View v) {
				StringUtil.getData(mContext,tv_chance_data3);
			}
		});
		return convertView;
	}
	
	/**
	 * @param list : set the property list.
	 */
	public void setList(List<Job> list) {
		this.list = list;
	}
	
	/**
	 * @return list : return the property list.
	 */
	public List<Job> getList() {
		return list;
	}
}
