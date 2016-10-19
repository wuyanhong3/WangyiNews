package wangyinews.com.wangyinews.WangYiNews.TextNews;


import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.bumptech.glide.Glide;

import wangyinews.com.wangyinews.MyVolley.RequestManager;
import wangyinews.com.wangyinews.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TextPictureFragment extends Fragment {


    public TextPictureFragment() {}
    public static TextPictureFragment newInstance(String url,int totalPager,int position){
        Bundle bundle=new Bundle();
        bundle.putString("url",url);
        bundle.putInt("total",totalPager);
        bundle.putInt("position",position);
        TextPictureFragment textPictureFragment=new TextPictureFragment();
         textPictureFragment.setArguments(bundle);
        return textPictureFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle=getArguments();
        String url=null;
        int totalPager=0,position=0;
        if(bundle!=null){
          url=bundle.getString("url");
          totalPager=  bundle.getInt("total");
           position= bundle.getInt("position");
        }
                         View view=inflater.inflate(R.layout.fragment_text_picture, container, false);
        ImageView image= (ImageView) view.findViewById(R.id.text_news_picture_image);
        TextView pager= (TextView) view.findViewById(R.id.text_news_picture_page);
        //ImageLoader imageLoader= RequestManager.getImageLoader();
        //imageLoader.get(url,ImageLoader.getImageListener(image,R.mipmap.loading,R.mipmap.loadfail));
        Glide.with(getContext()).load(url).into(image).onLoadFailed(null,getContext().getResources().getDrawable(R.mipmap.loading));
        pager.setText(position+""+"/"+totalPager+"");
        return view;
    }

}
