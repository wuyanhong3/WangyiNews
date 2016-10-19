package wangyinews.com.wangyinews.WangYiNews.TextNews;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import wangyinews.com.wangyinews.MyVolley.RequestManager;
import wangyinews.com.wangyinews.NewsJson.Text.NewsBean;
import wangyinews.com.wangyinews.NewsJson.Text.NewsPictureBean;
import wangyinews.com.wangyinews.R;

/**
 * Created by Administrator on 2016/9/26.
 */
public class ListAdapter extends BaseAdapter {
    private static final int TYPE_ONE=0;  //item类型
    private static final int TYPE_TWO=1;
    private Context context;
    private LayoutInflater layoutInflater;
    private List<NewsBean> list=new ArrayList<>();
    public ListAdapter(Context context){
        this.context=context;
        layoutInflater=LayoutInflater.from(context);
    }
    public void setList(List<NewsBean> list){
        if(new TextNewsFragment().pageCount==0){
        this.list=list;
        }else{
            this.list.addAll(list);
        }
        Log.v("pppppp",">>>>>>>setList>>>>>pagCount"+new TextNewsFragment().pageCount);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (list.get(position).getImgextra()!=null){
            return TYPE_TWO;
        }
        return TYPE_ONE;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public  View getView(int position, View convertView, ViewGroup parent){
        if (getItemViewType(position)==TYPE_ONE){
            return getViewOne(position,convertView,parent);
        }else{
        return getViewTwo(position,convertView,parent);}
    }
    public View getViewTwo(int position, View convertView, ViewGroup parent){
       HolerViewTwo holerViewTwo;
        if(convertView==null){
            convertView=layoutInflater.inflate(R.layout.picture_news_layout,null);
            holerViewTwo=new HolerViewTwo();
            holerViewTwo.title= (TextView) convertView.findViewById(R.id.picture_news_title);
            holerViewTwo.picture1= (ImageView) convertView.findViewById(R.id.picture_news_1);
            holerViewTwo.picture2= (ImageView) convertView.findViewById(R.id.picture_news_2);
            holerViewTwo.picture3= (ImageView) convertView.findViewById(R.id.picture_news_3);
              convertView.setTag(holerViewTwo);
        }else{
            holerViewTwo= (HolerViewTwo) convertView.getTag();
        }
                       NewsBean newsBean= (NewsBean) getItem(position);
                       List<NewsPictureBean> pList=newsBean.getImgextra();
                       holerViewTwo.title.setText(newsBean.getTitle());
                       Glide.with(context).load(pList.get(0).getImgsrc()).into(holerViewTwo.picture1).onLoadFailed(null,context.getResources().getDrawable(R.mipmap.loading));
                       Glide.with(context).load(pList.get(1).getImgsrc()).into(holerViewTwo.picture2).onLoadFailed(null,context.getResources().getDrawable(R.mipmap.loading));
                       Glide.with(context).load(newsBean.getImgsrc()).into(holerViewTwo.picture3).onLoadFailed(null,context.getResources().getDrawable(R.mipmap.loading));

        return convertView;
    }
    class HolerViewTwo{
        private TextView title;
        private ImageView picture1,picture2,picture3;
    }
    public View getViewOne(int position, View convertView, ViewGroup parent) {
        HolerViewOne holerViewOne;
        if(convertView==null){
            convertView=layoutInflater.inflate(R.layout.list_adapter_layout,null);
            holerViewOne=new HolerViewOne();
            holerViewOne.picture= (ImageView) convertView.findViewById(R.id.news_picture);
            holerViewOne.title= (TextView) convertView.findViewById(R.id.news_title);
            holerViewOne.content= (TextView) convertView.findViewById(R.id.news_content);
            holerViewOne.time= (TextView) convertView.findViewById(R.id.news_time);
            convertView.setTag(holerViewOne);
        }else {
            holerViewOne = (HolerViewOne) convertView.getTag();
        }
         NewsBean newsBean= (NewsBean) getItem(position);
       // Glide.with(context).load(newsBean.getImgsrc()).into(holerViewOne.picture);
        ImageLoader imageLoader=RequestManager.getImageLoader();
        imageLoader.get(newsBean.getImgsrc(),ImageLoader.getImageListener(holerViewOne.picture,R.mipmap.loading,R.mipmap.loadfail));
        holerViewOne.title.setText(newsBean.getLtitle());
        holerViewOne.content.setText(newsBean.getDigest());
        holerViewOne.time.setText("时间："+newsBean.getLmodity());
        return convertView;
    }
    class HolerViewOne{
        public TextView title,content,time;
        public ImageView picture;
    }

}
