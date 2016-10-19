package wangyinews.com.wangyinews.WangYiNews.VideoNews;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import wangyinews.com.wangyinews.NewsJson.Video.VideoBean;
import wangyinews.com.wangyinews.R;

/**
 * Created by Administrator on 2016/9/25.
 */
public class VideoListAdapter extends BaseAdapter{
    private LayoutInflater layoutInflater;
    public SurfaceView surfaceView;
    public VideoView getVideoPlay() {
        return videoPlay;
    }

    public  VideoView videoPlay;
    private Context context;
    private List<VideoBean> list=new ArrayList<>();
    public VideoListAdapter(Context context){
        this.context=context;
        this.layoutInflater=LayoutInflater.from(context);
    }
   public void setList(List<VideoBean> list){
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
        HolderView holderView;
        if(convertView==null){
               convertView=layoutInflater.inflate(R.layout.video_list_adapterlayout,null);
               holderView=new HolderView();
               holderView.title= (TextView) convertView.findViewById(R.id.video_news_adapter_title);
               holderView.content= (TextView) convertView.findViewById(R.id.video_news_adapter_content);
               holderView.dataTime= (TextView) convertView.findViewById(R.id.video_news_adapter_time);
               holderView.videoImage= (ImageView) convertView.findViewById(R.id.video_news_adapter_image);
                 //holderView.videoPlay= (VideoView) convertView.findViewById(R.id.video_news_adapter_video);
                  // videoPlay= (VideoView) convertView.findViewById(R.id.video_news_adapter_video);
                           // surfaceView= (SurfaceView) convertView.findViewById(R.id.video_news_adapter_play_surface);
               holderView.videoTime= (TextView) convertView.findViewById(R.id.video_news_adapter_video_time);
                        convertView.setTag(holderView);

        }else{
                      //  surfaceView= (SurfaceView) convertView.findViewById(R.id.video_news_adapter_play_surface);
                     holderView= (HolderView) convertView.getTag();
        }
                           VideoBean videoBean= (VideoBean) getItem(position);
                 // surfaceView.getHolder().setFixedSize(400,500);
                   holderView.title.setText(videoBean.getTitle());
                   if(videoBean.getDescription()!=null){
                       holderView.content.setText(videoBean.getDescription());
                   }else{
                       holderView.content.setText(videoBean.getTitle());
                   }
                  holderView.dataTime.setText("时间:"+videoBean.getPtime());
                 // videoPlay.setMediaController(new MediaController(context));

                  Glide.with(context).load(videoBean.getCover()).into(holderView.videoImage);
                  holderView.videoTime.setText("时长:"+videoBean.getLength()+"s");
        return convertView;
    }
    class HolderView{
        TextView title,content,dataTime,videoTime;
        ImageView videoImage;
        //VideoView videoPlay;

    }
}
