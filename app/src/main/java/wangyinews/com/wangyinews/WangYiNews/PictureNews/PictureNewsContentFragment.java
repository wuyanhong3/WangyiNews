package wangyinews.com.wangyinews.WangYiNews.PictureNews;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import wangyinews.com.wangyinews.NewsJson.Picture.DesPicsBean;
import wangyinews.com.wangyinews.NewsJson.Picture.PicsBean;
import wangyinews.com.wangyinews.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PictureNewsContentFragment extends Fragment {
      private TextView mPage,mContet;
      private ImageView mPicture;
               DesPicsBean desPicsBean;
               int page,totalPage;
    public PictureNewsContentFragment() {
    }

  public static PictureNewsContentFragment newInstance(DesPicsBean desPicsBean, int page, int totalPage){
        Bundle bundle=new Bundle();
        bundle.putParcelable("PICTURE",desPicsBean);
        bundle.putInt("PAGE",page);
        bundle.putInt("TOPAGE",totalPage);
      PictureNewsContentFragment pictureNewsContentFragment=new PictureNewsContentFragment();
         pictureNewsContentFragment.setArguments(bundle);
      return pictureNewsContentFragment;
  }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle=getArguments();
        View   view= inflater.inflate(R.layout.fragment_picture_news_content, container, false);
               mPage= (TextView) view.findViewById(R.id.picture_news_content_page);
               mContet= (TextView) view.findViewById(R.id.picture_news_content_des);
               mPicture= (ImageView) view.findViewById(R.id.picture_news_content_image);
          if(bundle!=null){
                desPicsBean= bundle.getParcelable("PICTURE");
                page=bundle.getInt("PAGE");
                totalPage=bundle.getInt("TOPAGE");
          }
                    setMess();
        return   view;
    }
  private void setMess(){
      Glide.with(getContext()).load(desPicsBean.getKpic()).into(mPicture);
      Log.v("pppppp",desPicsBean.getPic());
      String iPage=page+"/"+totalPage;
      mPage.setText(iPage);
      mContet.setText(desPicsBean.getAlt());
  }
}
