package com.timetalent.client.ui;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.timetalent.client.R;
import com.timetalent.client.ui.adapter.GuidePagerAdapter;
import com.timetalent.client.ui.login.LoginActivity;
import com.timetalent.client.ui.login.RegisterFirstActivity;
import com.timetalent.common.util.IntentUtil;


/******************************************
 * 类描述： 引导页
 * 类名称：GuideActivity  
 * @version: 1.0
 * @author: ghf
 * @time: 2014-9-20 下午5:32:13 
 ******************************************/
public class GuideActivity extends BaseActivity implements OnClickListener,OnPageChangeListener{
	private Context context = this;
	private GuidePagerAdapter adapter;
	private List<View> views;
	
	// 底部小点图片
    private ImageView[] dots;
 // 记录当前选中位置
    private int currentIndex;
    private ViewPager viewPager;
    private Drawable[] dotImages;
    private ImageButton dot_one,dot_two,dot_three;
    
    private static final int PAGE_ONE=0;
    private static final int PAGE_TWO=1;
    private static final int PAGE_THREE=2;
    
    private Button bt_guide_register;
    private Button bt_guide_login;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.guide_layout);
		// 初始化页面
        initViews();

        // 初始化底部小点
        initDots();
		
	}

	
	
	
	/**
	  * 方法描述：TODO
	  * @author: ghf
	  * @time: 2014-9-20 下午6:49:14
	  */
	private void initDots() {
		dotImages=new Drawable[]{getResources().getDrawable(R.drawable.unread_dot),
				getResources().getDrawable(R.drawable.read_dot)};
		setSelectedStatus(getCurrentIndex());
		
		
	}

	/**
	 * @param currentIndex : set the property currentIndex.
	 */
	public void setCurrentIndex(int currentIndex) {
		this.currentIndex = currentIndex;
	}
	/**
	 * @return currentIndex : return the property currentIndex.
	 */
	public int getCurrentIndex() {
		return currentIndex;
	}

	/**
	  * 方法描述：TODO
	  * @author: ghf
	  * @time: 2014-9-20 下午6:49:09
	  */
	private void initViews() {
		LayoutInflater inflater = LayoutInflater.from(this);
		 dot_one=(ImageButton) findViewById(R.id.dot_guide_imageView1);
		 dot_two=(ImageButton) findViewById(R.id.dot_guide_imageView2);
		 dot_three=(ImageButton) findViewById(R.id.dot_guide_imageView3);
        views = new ArrayList<View>();
        // 初始化引导图片列表
        views.add(inflater.inflate(R.layout.guide_page_one, null));
        views.add(inflater.inflate(R.layout.guide_page_two, null));
        views.add(inflater.inflate(R.layout.guide_page_three, null));
//        views.add(inflater.inflate(R.layout.guide_page_four, null));
        setCurrentIndex(0);
        // 初始化Adapter
        adapter=new GuidePagerAdapter(views, this);

        viewPager = (ViewPager) findViewById(R.id.guide_pager);
        viewPager.setAdapter(adapter);
        // 绑定回调
        viewPager.setOnPageChangeListener(this);

		
        bt_guide_register = (Button)findViewById(R.id.bt_guide_register);
        bt_guide_register.setOnClickListener(this);
        bt_guide_login = (Button)findViewById(R.id.bt_guide_login);
        bt_guide_login.setOnClickListener(this);
        
	}
	
	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		
	}

		
	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void onPageSelected(int arg0) {
		setCurrentIndex(arg0);
		setSelectedStatus(arg0);
		
	}




	
	/**
	  * 方法描述：TODO
	  * @author: ghf
	  * @time: 2014-9-21 上午10:30:07
	  */
	@SuppressLint("NewApi") private void setSelectedStatus(int position) {
		
		switch (position) {
		case PAGE_ONE:
			dot_one.setBackgroundDrawable(dotImages[1]);
			dot_two.setBackgroundDrawable(dotImages[0]);
			dot_three.setBackgroundDrawable(dotImages[0]);
			
			break;
		case PAGE_TWO:
			dot_one.setBackgroundDrawable(dotImages[0]);
			dot_two.setBackgroundDrawable(dotImages[1]);
			dot_three.setBackgroundDrawable(dotImages[0]);
			
			break;
		case PAGE_THREE:
			dot_one.setBackgroundDrawable(dotImages[0]);
			dot_two.setBackgroundDrawable(dotImages[0]);
			dot_three.setBackgroundDrawable(dotImages[1]);
			break;
		default:
			break;
		}		
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_guide_register:  
		   IntentUtil.intent(GuideActivity.this, RegisterFirstActivity.class);
			//DialogUtil.showError(GuideActivity.this, "dialog  Test");
			break;
		case R.id.bt_guide_login:
			IntentUtil.intent(GuideActivity.this, LoginActivity.class);
			break;
		default:
			break;
		}
	}

}
