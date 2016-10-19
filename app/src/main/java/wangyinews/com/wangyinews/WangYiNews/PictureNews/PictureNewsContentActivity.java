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
import android.widget.Toast;

import com.google.gson.Gson;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import wangyinews.com.wangyinews.NewsJson.Picture.DesPicsBean;
import wangyinews.com.wangyinews.NewsJson.Picture.DesPicture;
import wangyinews.com.wangyinews.NewsJson.Picture.DesPictures;
import wangyinews.com.wangyinews.NewsJson.Picture.PicsBean;
import wangyinews.com.wangyinews.R;
import wangyinews.com.wangyinews.WangYiNews.ConnectionUtils;

public class PictureNewsContentActivity extends AppCompatActivity implements PictureNewsFragment.InterShowPicture,View.OnClickListener {
      private ViewPager mPictureContent;
      private ImageButton back;
      private TextView title;
      private List<DesPicsBean> mList=new ArrayList<>();
      private String mTitle;
                MyPagerAdater myPagerAdater;
     private String mId;
              ConnectionUtils connectionUtils;
    private String mUrl="http://api.sina.cn/sinago/article.json?postt=hdpic_hdpic_toutiao_4&wm=b207&from=6042095012&chwm=12050_0001&oldchwm=12050_0001&imei=867064013906290&uid=802909da86d9f5fc&id=";
     private List<DesPicsBean> MnList;
    private String mURL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_news_content);
        initID();
        Intent intent=getIntent();
          connectionUtils=new ConnectionUtils(this);
       // mList=intent.getParcelableArrayListExtra("PICS");
          mTitle=intent.getStringExtra("TIL");
          mId=intent.getStringExtra("ID");
          mURL=mUrl+mId;
          myPagerAdater=new MyPagerAdater(getSupportFragmentManager());
          getUrl(mURL);
          title.setText(mTitle);
          back.setOnClickListener(this);
    }

  /**
   * 初始化控件
   * */
    private void initID(){
        mPictureContent= (ViewPager) findViewById(R.id.picture_news_content_ac_viewpager);
        back= (ImageButton) findViewById(R.id.picture_news_content_ac_back);
        title= (TextView) findViewById(R.id.picture_news_content_ac_title);
    }

    @Override
    public void SendPicture(List<PicsBean> list, String title) {
//             this.mList=list;
//            this.mTitle=title;

    }
/**
 * 返回键
 * */
    @Override
    public void onClick(View v) {
        finish();
    }
/**
 * 联网取图片列表
 *
 * **/
    private void getUrl(String url){
           connectionUtils.asycnConnect(url, ConnectionUtils.Method.GET, new ConnectionUtils.HttpConnectionInterface() {
               @Override
               public void execute(String content) {
                   if (content == null) {
                              Toast.makeText(PictureNewsContentActivity.this, "请求出错!", Toast.LENGTH_SHORT).show();
                                 return;
                   }
                                   MnList=setJson(content);
                   if(MnList.size()>0){
                             mPictureContent.setAdapter(myPagerAdater);
                     }else{
                             Toast.makeText(PictureNewsContentActivity.this, "请求出错!", Toast.LENGTH_SHORT).show();
                                   return;
                   }
               }
           });
    }

/**
 * 解析json
 * */
  private  List<DesPicsBean> setJson(String json){
      Gson gson=new Gson();
      DesPicture desPicture=gson.fromJson(json,DesPicture.class);
      DesPictures desPictures=desPicture.getData();
      List<DesPicsBean> list=desPictures.getPics();
          Log.v("pppppp",">>>>>>setJson"+list.size());
      for( DesPicsBean desPicsBean:list){
          DesPicsBean iDesPicsBean=new DesPicsBean();
          iDesPicsBean.setAlt(desPicsBean.getAlt());
          iDesPicsBean.setKpic(desPicsBean.getKpic());
          iDesPicsBean.setPic(desPicsBean.getPic());
          Log.v("pppppp",">>>>>>setJson>>>"+desPicsBean.getKpic());
          mList.add(iDesPicsBean);
      }
       return mList;
  }
/**
 *
 * Viewpager适配器
 * */
    class MyPagerAdater extends FragmentStatePagerAdapter{

        public MyPagerAdater(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return PictureNewsContentFragment.newInstance(MnList.get(position),position+1,MnList.size());
        }

        @Override
        public int getCount() {
            return MnList.size();
        }
    }
}
