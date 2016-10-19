package wangyinews.com.wangyinews.WangYiNews.TextNews;

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

import java.util.List;

import wangyinews.com.wangyinews.NewsJson.Text.NewsBean;
import wangyinews.com.wangyinews.NewsJson.Text.NewsPictureBean;
import wangyinews.com.wangyinews.R;

public class TextPictureActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton back;
    private ViewPager imagePager;
    private TextView title;
   private List<NewsPictureBean> imageList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_text_content);
        initView();
        Intent intent=getIntent();
        NewsBean newsBean=intent.getParcelableExtra("content");
        imageList =intent.getParcelableArrayListExtra("list");
        MyPager mypager=new MyPager(getSupportFragmentManager());
             imagePager.setAdapter(mypager);
             title.setText(newsBean.getTitle());
        Log.v("ppppn","TextPictureActivity"+ imageList.size()+">>>>>>"+newsBean.getTitle());
    }
   private void initView(){
       back= (ImageButton) findViewById(R.id.text_news_picture_back);
       imagePager= (ViewPager) findViewById(R.id.text_news_picture_viewpager);
       title= (TextView) findViewById(R.id.text_news_picture_title);

   }

    @Override
    public void onClick(View view) {

    }

    class MyPager extends FragmentStatePagerAdapter {

        public MyPager(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            return TextPictureFragment.newInstance(imageList.get(position).getImgsrc(), imageList.size(),position+1);
        }

        @Override
        public int getCount() {
            return imageList.size();
        }
    }
}
