package wangyinews.com.wangyinews.WangYiNews;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.warmtel.slidingmenu.lib.app.SlidingActivity;

import wangyinews.com.wangyinews.R;
import wangyinews.com.wangyinews.WangYiNews.PictureNews.PictureNewsActivity;
import wangyinews.com.wangyinews.WangYiNews.TextNews.TextNewsMainActivity;
import wangyinews.com.wangyinews.WangYiNews.VideoNews.VideoActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class LeftMenuFragment extends Fragment implements View.OnClickListener {
    private      LeftMenuClick leftMenuClick;
    private     LinearLayout picture;
    private     LinearLayout video;
    private     LinearLayout text;

    public static Fragment newInstance(){
       LeftMenuFragment leftMenuFragment=new LeftMenuFragment();
       return leftMenuFragment;
   }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_left_menu, container, false);
             picture= (LinearLayout) view.findViewById(R.id.left_menu_picture);
             video= (LinearLayout) view.findViewById(R.id.left_menu_video);
             text= (LinearLayout) view.findViewById(R.id.left_menu_text);
               picture.setOnClickListener(this);
               video.setOnClickListener(this);
               text.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.left_menu_picture:
                Bundle bundle=new Bundle();
                bundle.putString("TAB","图片新闻");
                Intent intent=new Intent(getContext(),PictureNewsActivity.class).putExtra("BUNDLE",bundle);
                    startActivity(intent);
                      leftMenuClick.OnClickLeftMenu(picture);
               // leftMenuClick.OnClickLeftMenu(picture);
                break;
            case R.id.left_menu_video:
                Bundle bundle2=new Bundle();
                bundle2.putString("TAB","视频新闻");
                Intent intent1=new Intent(getContext(), VideoActivity.class).putExtra("BUNDLE",bundle2);
                startActivity(intent1);
                leftMenuClick.OnClickLeftMenu(video);
                break;
            case R.id.left_menu_text:
                //Toast.makeText(getContext(),"今日热点",Toast.LENGTH_SHORT).show();
                Intent intent2=new Intent(getContext(), TextNewsMainActivity.class);
                startActivity(intent2);
                leftMenuClick.OnClickLeftMenu(text);
                break;
        }
    }

      public    interface LeftMenuClick{
        void OnClickLeftMenu(View v);
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
             Log.v("pppp",""+context.getPackageName());
        if(context instanceof TextNewsMainActivity ){
            leftMenuClick= (TextNewsMainActivity)context;
        }
        if(context instanceof PictureNewsActivity ){
            leftMenuClick= (PictureNewsActivity)context;
        }
        if(context instanceof VideoActivity ){
            leftMenuClick= (VideoActivity)context;
        }

    }
}
