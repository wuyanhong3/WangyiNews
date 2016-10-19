package wangyinews.com.wangyinews.WangYiNews.PictureNews;

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

/**
 * 图片缩略图activity
 *    精选图片：http://api.sina.cn/sinago/list.json?channel=hdpic_toutiao&adid=4ad30dabe134695c3b7c3a65977d7e72&wm=b207&from=6042095012&chwm=12050_0001&oldchwm=&imei=867064013906290&uid=802909da86d9f5fc&p=
 *     趣图列表：http://api.sina.cn/sinago/list.json?channel=hdpic_funny&adid=4ad30dabe134695c3b7c3a65977d7e72&wm=b207&from=6042095012&chwm=12050_0001&oldchwm=12050_0001&imei=867064013906290&uid=802909da86d9f5fc&p=
 *     美图列表：http://api.sina.cn/sinago/list.json?channel=hdpic_pretty&adid=4ad30dabe134695c3b7c3a65977d7e72&wm=b207&from=6042095012&chwm=12050_0001&oldchwm=12050_0001&imei=867064013906290&uid=802909da86d9f5fc&p=
 *     故事列表：http://api.sina.cn/sinago/list.json?channel=hdpic_story&adid=4ad30dabe134695c3b7c3a65977d7e72&wm=b207&from=6042095012&chwm=12050_0001&oldchwm=12050_0001&imei=867064013906290&uid=802909da86d9f5fc&p=
 *
 *
 * */
public class PictureNewsActivity extends SlidingActivity implements LeftMenuFragment.LeftMenuClick {
    private SlidingMenu slidingMenu;
     private TextView mNewsTab;
     private ImageButton mBack;
     private ViewPagerIndicator mIndictaor;
     private ViewPager mPicture;
     private String[] mUrl={"http://api.sina.cn/sinago/list.json?channel=hdpic_toutiao&adid=4ad30dabe134695c3b7c3a65977d7e72&wm=b207&from=6042095012&chwm=12050_0001&oldchwm=&imei=867064013906290&uid=802909da86d9f5fc&p=",
                              "http://api.sina.cn/sinago/list.json?channel=hdpic_funny&adid=4ad30dabe134695c3b7c3a65977d7e72&wm=b207&from=6042095012&chwm=12050_0001&oldchwm=12050_0001&imei=867064013906290&uid=802909da86d9f5fc&p=",
                                "http://api.sina.cn/sinago/list.json?channel=hdpic_pretty&adid=4ad30dabe134695c3b7c3a65977d7e72&wm=b207&from=6042095012&chwm=12050_0001&oldchwm=12050_0001&imei=867064013906290&uid=802909da86d9f5fc&p=",
                                 "http://api.sina.cn/sinago/list.json?channel=hdpic_story&adid=4ad30dabe134695c3b7c3a65977d7e72&wm=b207&from=6042095012&chwm=12050_0001&oldchwm=12050_0001&imei=867064013906290&uid=802909da86d9f5fc&p="};
     private List<String> Tab= Arrays.asList("热图","趣图","美图","故事");

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_left_news_main);
                     initId();
        Intent intent=getIntent();
        Bundle bundle=intent.getBundleExtra("BUNDLE");
        mNewsTab.setText((String) bundle.get("TAB"));
         MyPagerAdapter myPagerAdapter=new MyPagerAdapter(getSupportFragmentManager());
                  mIndictaor.setTabItemTitles(Tab);
                  mPicture.setAdapter(myPagerAdapter);
                  mIndictaor.setViewPager(mPicture,0);
                  mIndictaor.setVisibleTabCount(4);
                     setLeftMenu();
                     setBackClick();
    }
    private void initId(){
        mNewsTab= (TextView) findViewById(R.id.left_news_tab);
        mBack= (ImageButton) findViewById(R.id.left_content_back);
        mIndictaor= (ViewPagerIndicator) findViewById(R.id.picture_news_indicator);
        mPicture= (ViewPager) findViewById(R.id.picture_news_viewPager);
    }
    private void setBackClick(){
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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
            case R.id.left_menu_picture:
                slidingMenu.toggle();
                Log.v("ppppp","OnclickLeftMenu>>>>>>picture");
                break;
        }
    }

    class MyPagerAdapter extends FragmentStatePagerAdapter{
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return PictureNewsFragment.newInstance(mUrl[position]);
        }

        @Override
        public int getCount() {
            return mUrl.length;
        }
    }
}
