package com.timetalent.client.ui.fragment;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;

import com.timetalent.client.R;
import com.timetalent.client.entities.Nearlist;
import com.timetalent.client.service.AppController;
import com.timetalent.client.ui.MainFragmentActivity;
import com.timetalent.client.ui.adapter.NearBaseAdapter;
import com.timetalent.client.ui.dialog.IOSStyleDialog;
import com.timetalent.client.ui.fragment.util.Background1;
import com.timetalent.client.ui.fragment.util.Background2;
import com.timetalent.client.ui.fragment.util.DipPxUtil;
import com.timetalent.client.ui.login.LoginActivity;
import com.timetalent.client.ui.near.FansActivity;
import com.timetalent.client.ui.near.SearchActivity;
import com.timetalent.client.ui.near.XingtanActivity;
import com.timetalent.client.ui.near.YirenActivity;
import com.timetalent.client.ui.user.YirenziliaobianjiActivity;
import com.timetalent.common.util.IntentUtil;
import com.timetalent.common.util.UIUtils;

/**
 * **************************************** 类描述： 附近 类名称：NearFragment
 * 
 * @version: 1.0
 * @author: why
 * @time: 2014-10-10 下午6:30:07
 ***************************************** 
 */
public class NearFragment extends Fragment implements OnClickListener {
	private View view;
	private Context mContext;
	private AppController controller;
	private ListView list;
	private TextView btshaixuan;
	private TextView tvtitle;
	private TextView btsearch;
	private ImageButton btback;
	NearBaseAdapter adapter = null;

	public String search = "";
	public String lat = "116.287128";
	public String lng = "39.830486";
	public String sex = "0";
	public String age_min = "0";
	public String age_max = "80";
	public String type = "star";
	public String major = "singer";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		controller = AppController.getController(getActivity());
		view = inflater.inflate(R.layout.near_fragment, container, false);
		mContext = getActivity();
		findView();
		initView();
		return view;
	}

	/**
	 * 方法描述：TODO
	 * 
	 * @author: why
	 * @time: 2014-10-10 下午6:36:02
	 */
	private void initView() {
		btback.setVisibility(btback.GONE);
		btshaixuan.setVisibility(btshaixuan.VISIBLE);
		btsearch.setVisibility(btsearch.VISIBLE);
		tvtitle.setText("附近");
		btshaixuan.setText("筛选");
		btsearch.setText("");
		UIUtils.setDrawableLeft(getActivity(), btsearch, R.drawable.f9_06);
		setvalue();
		new Thread() {
			public void run() {
				controller.mylocation_update();//更新本人经纬度
				controller.near();
				handler.sendEmptyMessage(1);
			};
		}.start();
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Nearlist result = (Nearlist) controller.getContext().getBusinessData("NearData");
				if(result.getLists().get(arg2).getType().equals("star")){
					Bundle bundle1 = new Bundle();
					bundle1.putString("userid", result.getLists().get(arg2).getId());
					IntentUtil.intent(mContext, bundle1,YirenActivity.class,false);
				}else if(result.getLists().get(arg2).getType().equals("scout")){
					Bundle bundle1 = new Bundle();
					bundle1.putString("userid", result.getLists().get(arg2).getId());
					IntentUtil.intent(mContext, bundle1,XingtanActivity.class,false);
				}else if(result.getLists().get(arg2).getType().equals("fans")){
					Bundle bundle1 = new Bundle();
					bundle1.putString("userid", result.getLists().get(arg2).getId());
					IntentUtil.intent(mContext, bundle1,FansActivity.class,false);
				}
			}
		});
		btshaixuan.setOnClickListener(this);
		btsearch.setOnClickListener(this);
		showMessageTwo(mContext, "现在去完善个人信息", "完成");
	}

	public void setvalue() {
		controller.getContext().addBusinessData("near.page", 1);
		controller.getContext().addBusinessData("near.page_per", 100);
		controller.getContext().addBusinessData("near.search", "");
		controller.getContext().addBusinessData("near.lat", lat);
		controller.getContext().addBusinessData("near.lng", lng);
		controller.getContext().addBusinessData("near.sex", sex);
		controller.getContext().addBusinessData("near.age_min", age_min);
		controller.getContext().addBusinessData("near.age_max", age_max);
		controller.getContext().addBusinessData("near.type", type);
		controller.getContext().addBusinessData("near.major", major);
	}

	private void showMessageTwo(final Context context, String message,
			final String toast) {
		final IOSStyleDialog dialog = new IOSStyleDialog(context,
				IOSStyleDialog.DIALOG_TWO);
		dialog.setmTitle("提示");
		dialog.setMessage(message);
		dialog.setLeft("取消", new OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.closeDialog();
			}
		});
		dialog.setRight("确认", new OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.closeDialog();
				IntentUtil.intent(mContext, YirenziliaobianjiActivity.class);
			}
		});
	}

	/**
	 * 方法描述：TODO
	 * 
	 * @author: why
	 * @time: 2014-10-10 下午6:36:00
	 */
	private void findView() {
		list = (ListView) view.findViewById(R.id.listView1);
		btback = (ImageButton) view.findViewById(R.id.main_top_left);
		btshaixuan = (TextView) view.findViewById(R.id.main_top_left2);
		btsearch = (TextView) view.findViewById(R.id.main_top_right);
		tvtitle = (TextView) view.findViewById(R.id.main_top_title);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.main_top_left2:
			LayoutInflater inflater = LayoutInflater.from(view.getContext());
			View popview = inflater.inflate(R.layout.near_shaixuan, null);
			final PopupWindow pop = new PopupWindow(popview,
					LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, false);
			pop.setBackgroundDrawable(new Background2());
			pop.setOutsideTouchable(true);
			pop.setFocusable(true);

			RadioButton rd1 = (RadioButton) popview.findViewById(R.id.rd1);
			RadioButton rd2 = (RadioButton) popview.findViewById(R.id.rd2);
			RadioButton rd3 = (RadioButton) popview.findViewById(R.id.rd3);
			RadioButton rd4 = (RadioButton) popview.findViewById(R.id.rd4);
			RadioButton rd5 = (RadioButton) popview.findViewById(R.id.rd5);
			RadioButton rd6 = (RadioButton) popview.findViewById(R.id.rd6);
			RadioButton rd7 = (RadioButton) popview.findViewById(R.id.rd7);
			RadioButton rd8 = (RadioButton) popview.findViewById(R.id.rd8);
			RadioButton rd9 = (RadioButton) popview.findViewById(R.id.rd9);
			RadioButton rd10 = (RadioButton) popview.findViewById(R.id.rd10);
			RadioButton rd11 = (RadioButton) popview.findViewById(R.id.rd11);
			RadioButton rd12 = (RadioButton) popview.findViewById(R.id.rd12);
			RadioButton rd13 = (RadioButton) popview.findViewById(R.id.rd13);
			RadioButton rd14 = (RadioButton) popview.findViewById(R.id.rd14);
			RadioButton rd15 = (RadioButton) popview.findViewById(R.id.rd15);
			RadioButton rd16 = (RadioButton) popview.findViewById(R.id.rd16);
			RadioButton rd17 = (RadioButton) popview.findViewById(R.id.rd17);
			RadioButton rd18 = (RadioButton) popview.findViewById(R.id.rd18);
			rd1.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					sex = "0";
				}
			});
			rd2.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					sex = "2";
				}
			});
			rd3.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					sex = "1";
				}
			});
			rd4.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					age_min = "0";
					age_max = "60";
				}
			});
			rd5.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					age_min = "10";
					age_max = "16";
				}
			});
			rd6.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					age_min = "16";
					age_max = "24";
				}
			});
			rd7.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					age_min = "24";
					age_max = "30";
				}
			});
			rd8.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					age_min = "30";
					age_max = "36";
				}
			});
			rd9.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					age_min = "36";
					age_max = "42";
				}
			});
			rd10.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					age_min = "42";
					age_max = "50";
				}
			});
			rd11.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					age_min = "50";
					age_max = "60";
				}
			});
			rd12.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					type = "all";
				}
			});
			rd13.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					type = "star";
				}
			});
			rd14.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					type = "scout";
				}
			});
			rd15.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					type = "fans";
				}
			});
			rd16.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					major = "singer";
				}
			});
			rd17.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					major = "model";
				}
			});
			rd18.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					major = "actor";
				}
			});
			search = "";
			lat = "";
			lng = "";
			sex = "";
			age_min = "";
			age_max = "";
			type = "";
			major = "";

			Button btok = (Button) popview.findViewById(R.id.btok);
			btok.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					new Thread() {
						public void run() {
							setvalue();
							controller.near();
							handler.sendEmptyMessage(1);
						};
					}.start();
					pop.dismiss();

				}
			});
			Button btcanel = (Button) popview.findViewById(R.id.btcanel);
			btcanel.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					pop.dismiss();

				}
			});
			pop.showAsDropDown(v);
			break;
		case R.id.main_top_right:
			IntentUtil.intent(this.mContext, SearchActivity.class);

			break;
		default:
			break;
		}
	}

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// super.handleMessage(msg);
			switch (msg.what) {
			case 1:
				adapter = new NearBaseAdapter(getActivity());
				list.setAdapter(adapter);
				adapter.notifyDataSetChanged();
				break;
			}
		}
	};
}
