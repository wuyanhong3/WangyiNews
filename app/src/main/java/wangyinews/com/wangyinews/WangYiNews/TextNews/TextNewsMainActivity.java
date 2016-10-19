package wangyinews.com.wangyinews.WangYiNews.TextNews;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.warmtel.slidingmenu.lib.SlidingMenu;
import com.warmtel.slidingmenu.lib.app.SlidingActivity;

import java.util.Arrays;
import java.util.List;
import wangyinews.com.wangyinews.MyViewPager.ViewPagerIndicator;
import wangyinews.com.wangyinews.NewsJson.Text.NewsBean;
import wangyinews.com.wangyinews.R;
import wangyinews.com.wangyinews.WangYiNews.LeftMenuFragment;

/**
 *  1.头条新闻列表接口
 参数定义:
 int pageNo = 0; //页号 ，表示第几页,第一页从0开始
 int pageSize = 20; //页大小，显示每页多少条数据
 String news_type_id = "T1348647909107";  //新闻类型标识, 此处表示头条新闻
 Url请求地址: "http://c.m.163.com/nc/article/headline/"+ news_type_id +pageNo*pageSize+ "-"  +pageSize+ ".html"
 请求方式:Get
 例如: http://c.m.163.com/nc/article/headline/T1348647909107/0-20.html        //表示请求头条新闻第一页20条数据
 http://c.m.163.com/nc/article/headline/T1348647909107/20-20.html    //表示请求头条新闻第二页20条数据
 http://c.m.163.com/nc/article/headline/T1348647909107/40-20.html    //表示请求头条新闻第三页20条数据
 *
 *
 * 文本新闻activity
 *
 * */

public class TextNewsMainActivity extends SlidingActivity implements LeftMenuFragment.LeftMenuClick,TextNewsFragment.IshowNews {
	private SlidingMenu slidingMenu;
	 private TextView textView;
	private ViewPager mViewPager;
	private List<String> mDataUrl=Arrays.asList("T1348647909107", "T1348648517839", "T1348649079062", "T1348648756099", "T1348649580692");
	private List<String> mDatas = Arrays.asList("头条", "娱乐", "体育", "财经", "科技");


	private ViewPagerIndicator mIndicator;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		  setContentView(R.layout.activity_mainn);
		           getID();
		           Mypager mypager=new Mypager(getSupportFragmentManager());
		                   mIndicator.setTabItemTitles(mDatas);  //设置Tab上的标题
		                   mViewPager.setAdapter(mypager);
		                   //mViewPager.setOffscreenPageLimit(1);
		                   mIndicator.setViewPager(mViewPager,0);//设置关联的ViewPager
                           mIndicator.setVisibleTabCount(5);
		                     setLeftMenu();
	}
	private void getID() {
		mViewPager= (ViewPager) findViewById(R.id.news_Viewpager);
		mIndicator= (ViewPagerIndicator) findViewById(R.id.new_indicator);
		textView= (TextView) findViewById(R.id.mytext);
	}

	/**
	 * 左边侧滑菜单
	 * */
  private void setLeftMenu(){
	  setBehindContentView(R.layout.new_left_menulayout);
	  getSupportFragmentManager()
			  .beginTransaction()
			  .add(R.id.new_left_menu, LeftMenuFragment.newInstance())
			  .commit();
	  slidingMenu = getSlidingMenu();
	  slidingMenu.setSlidingEnabled(true);
	  slidingMenu.setBehindOffsetRes(R.dimen.sliding_menu_off_width);
	  slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
	  slidingMenu.setMode(SlidingMenu.LEFT);
  }
	@Override
	public void OnClickLeftMenu(View v) {
		switch (v.getId()){
			case R.id.left_menu_text:
				slidingMenu.toggle();
				Log.v("ppppp","OnclickLeftMenu>>>>>>Text");
				break;
		}



	}

	@Override
	public void showNewsContent(NewsBean newsBean, int position) {
		Log.v("pppppp",">>>>>>>>>>>>+showNewsContent"+position);
	}

	class Mypager extends FragmentStatePagerAdapter {

		public Mypager(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			Log.v("pppp","activity>>>>getItem>>>"+position);

			return TextNewsFragment.newInstance(mDataUrl.get(position),position);
		}

		@Override
		public int getCount() {
			return mDatas.size();
		}
	}

}
	/*
	*/
/**
 * 检查网络连接是否可用
 *//*

	public boolean isCheckConnection(){
		ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		if (networkInfo != null && networkInfo.isConnected()) {
			//网络连接可用
			return true;
		} else {
			//网络连接不可用!
			return false;
		}
	}
	public boolean isWiFiActive(Context inContext) {
		Context context = inContext.getApplicationContext();
		ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

		if (connectivity != null) {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].getTypeName().equals("WIFI") && info[i].isConnected()) {
						return true;
					}
				}
			}
		}
		return false;
	}
*/


