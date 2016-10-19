package wangyinews.com.wangyinews.WangYiNews.VideoNews;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.warmtel.slidingmenu.lib.SlidingMenu;
import com.warmtel.slidingmenu.lib.app.SlidingActivity;

import java.util.Arrays;
import java.util.List;

import wangyinews.com.wangyinews.MyViewPager.ViewPagerIndicator;
import wangyinews.com.wangyinews.R;
import wangyinews.com.wangyinews.WangYiNews.LeftMenuFragment;

public class VideoActivity extends SlidingActivity implements View.OnClickListener,LeftMenuFragment.LeftMenuClick {
    private SlidingMenu slidingMenu;
    private String[] id={"V9LG4B3A0","V9LG4CHOR","V9LG4E6VR","00850FRB"};
    private List<String> tab= Arrays.asList("热点视频","娱乐视频","搞笑视频","精选视频");
    private ViewPagerIndicator newsTab;
    private ViewPager newsViewpager;
    private TextView mTitle;
    private ImageButton mBack;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        initID();
               Intent intent=getIntent();
                  Bundle bundle=intent.getBundleExtra("BUNDLE");
                  mTitle.setText(bundle.getString("TAB"));
                  mBack.setOnClickListener(this);
            MyVideoAdpater myVideoAdpater=new MyVideoAdpater(getSupportFragmentManager());
                newsViewpager.setAdapter(myVideoAdpater);
                 newsTab.setVisibleTabCount(4);
                 newsTab.setTabItemTitles(tab);
                 newsTab.setViewPager(newsViewpager,0);
              setLeftMenu();

    }
    private void initID(){
        newsTab= (ViewPagerIndicator) findViewById(R.id.video_news_indicator);
         newsViewpager= (ViewPager) findViewById(R.id.video_news_pager);
          mTitle= (TextView) findViewById(R.id.video_news_title);
          mBack= (ImageButton) findViewById(R.id.video_news_back);
    }

    @Override
    public void onClick(View v) {
         finish();
    }

    @Override
    public void OnClickLeftMenu(View v) {
        switch (v.getId()){
            case R.id.left_menu_video:
                slidingMenu.toggle();
                Log.v("ppppp","OnclickLeftMenu>>>>>>Video");
                break;
        }
    }

    class MyVideoAdpater extends FragmentStatePagerAdapter{
        public MyVideoAdpater(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return VideoFragment.newInstance(id[position]);
        }

        @Override
        public int getCount() {
            return id.length;
        }
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
}
