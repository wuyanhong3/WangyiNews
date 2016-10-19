package wangyinews.com.wangyinews.WangYiNews.PictureNews;

import android.content.Context;
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
import wangyinews.com.wangyinews.NewsJson.Picture.PictureBean;
import wangyinews.com.wangyinews.R;

/**
 * Created by Administrator on 2016/8/11.
 *
 * 图片新闻gridView适配器
 *
 */
public class PictureGridAdapter extends BaseAdapter {
    private List<PictureBean> list=new ArrayList<>();
    private LayoutInflater layoutInflater;
    private Context context;
    public PictureGridAdapter(Context context){
        this.context=context;
        layoutInflater=LayoutInflater.from(context);
    }
    public void setList( List<PictureBean> list){
        this.list=list;
        notifyDataSetChanged();
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
    public View getView(int position, View convertView, ViewGroup parent) {
        HolerView holerView;
        if(convertView==null){
            holerView=new HolerView();
            convertView=layoutInflater.inflate(R.layout.picture_gride_layout,null);
            holerView.picture= (ImageView) convertView.findViewById(R.id.gride_picture);
            holerView.title= (TextView) convertView.findViewById(R.id.gride_picture_title);
            convertView.setTag(holerView);
        }else{
            holerView= (HolerView) convertView.getTag();
        }
          PictureBean pictureBean=list.get(position);
        ImageLoader imageLoader= RequestManager.getImageLoader();
        imageLoader.get(pictureBean.getKpic(),ImageLoader.getImageListener(holerView.picture,R.mipmap.loading,R.mipmap.loadfail));
       // Glide.with(context).load(pictureBean.getKpic()).into(holerView.picture);
        holerView.title.setText(pictureBean.getTitle());
        return convertView;
    }
    class HolerView{
        ImageView picture;
        TextView title;
    }
}
