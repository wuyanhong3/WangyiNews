package wangyinews.com.wangyinews.WangYiNews.VideoNews;


import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import wangyinews.com.wangyinews.NewsJson.Video.VideoBean;
import wangyinews.com.wangyinews.NewsJson.Video.VideoJson;
import wangyinews.com.wangyinews.NewsJson.Video.VideoTopic;
import wangyinews.com.wangyinews.R;
import wangyinews.com.wangyinews.WangYiNews.ConnectionUtils;
import wangyinews.com.xlistview_warmtel_library.XListView;

/**
 * A simple {@link Fragment} subclass.
 *
 * 热点视频：V9LG4B3A0
 * 娱乐视频：V9LG4CHOR
 * 搞笑视频：V9LG4E6VR
 * 精选视频：00850FRB
 *
 */
public class VideoFragment extends Fragment implements XListView.IXListViewListener,XListView.OnItemClickListener{
        private static final String VIDEO_TYPE="VIDEOTYPE";
        private XListView mListView;
        private String mHeadUrl="http://c.3g.163.com/nc/video/list/";
        private int mPage=1;
        private int mPageSize=10;
        private String id;
        private List<VideoBean> mList=new ArrayList<>();
        private List<VideoBean> dataList;
        private ConnectionUtils connectionUtils;
        private VideoListAdapter videoListAdapter;
        private MediaPlayer videoPaly;
        private SurfaceHolder mSurfaceHolder;
        private SurfaceView surfaceView;
        private  String url;
       private VideoView videoPlay;
    private int mVideoHeight,mVideoWidth;
    public VideoFragment() {
    }

     public static VideoFragment newInstance(String id){
               Bundle bundle=new Bundle();
               bundle.putString(VIDEO_TYPE,id);
               VideoFragment videoFragment=new VideoFragment();
               videoFragment.setArguments(bundle);
                  return videoFragment;
     }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
                    Bundle bundle=getArguments();
               View view=inflater.inflate(R.layout.fragment_video, container, false);

                 if(bundle!=null){
                     id=bundle.getString(VIDEO_TYPE);   //视频类型；
                 }
                    mListView= (XListView) view.findViewById(R.id.video_news_fragment_list);
                      mListView.setOnItemClickListener(this);
        mListView.setXListViewListener(this);
        mListView.setPullLoadEnable(true);
        mListView.setPullRefreshEnable(true);

                       videoListAdapter=new VideoListAdapter(getContext());
                          mListView.setAdapter(videoListAdapter);
                               connectionUtils=new ConnectionUtils(getContext());
                                 setListData(mPage);
                               //  onCreatSur(surfaceView,url);
                           return view;
    }
  /**
   * 联网取数据
   * */
   private void setListData(int page){
                 int Currentpage=page*mPageSize;
                 String iURL=mHeadUrl+id+"/n/"+Currentpage+"-"+mPageSize+".html";
                               Log.v("pppppp",">>>>>>>>>>>>>>VideoFragment>>>"+iURL);    //http://c.3g.163.com/nc/video/list/V9LG4CHOR/n/10-10.html
                       connectionUtils.asycnConnect(iURL, ConnectionUtils.Method.GET, new ConnectionUtils.HttpConnectionInterface() {
                                      @Override
                                          public void execute(String content) {
                                                   dataList=setVideoJson(content,id);

                                                   videoListAdapter.setList(dataList);
                                           }
                        });
   }
  /**
   * 解析VideoJson
   *
   * */
    private List<VideoBean> setVideoJson(String json,String id){
        Gson gson=new Gson();
        VideoJson videoJson=gson.fromJson(json,VideoJson.class);
        List<VideoBean> iList=videoJson.getVideoBean(id);
        for(VideoBean videoBean:iList){
            VideoBean iVideoBean=new VideoBean();
             iVideoBean.setCover(videoBean.getCover());
            iVideoBean.setMp4Hd_url(videoBean.getMp4Hd_url());
            iVideoBean.setMp4_url(videoBean.getMp4_url());
            iVideoBean.setTitle(videoBean.getTitle());
            iVideoBean.setDescription(videoBean.getDescription());
            iVideoBean.setPtime(videoBean.getPtime());
            iVideoBean.setLength(videoBean.getLength());
            iVideoBean.setTopicDesc(videoBean.getTopicDesc());
            iVideoBean.setTopicImg(videoBean.getTopicImg());
          /*  VideoTopic videoTopic=new VideoTopic();
               VideoTopic yVideoTopic=videoBean.getVideoTopic();
             //  videoTopic.setAlias(yVideoTopic.getAlias());
               videoTopic.setEname(yVideoTopic.getEname());
               videoTopic.setTid(yVideoTopic.getTid());
               videoTopic.setTname(yVideoTopic.getTname());
             iVideoBean.setVideoTopic(videoTopic);*/
              mList.add(iVideoBean);
        }
          return mList;
    }

    /**
     * 下拉刷新
     * */

    @Override
    public void onRefresh() {
          mPage=1;
            dataList.clear();
        setListData(mPage);
    }
    /**
     * 上拉添加更多；
     * */
    @Override
    public void onLoadMore() {
        if(++mPage<6){
            setListData(mPage);
        }else{
            Toast.makeText(getActivity(),"已是最后一页",Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              VideoBean videoBean= (VideoBean) videoListAdapter.getItem(position-1);
                       // videoPlay=videoListAdapter.getVideoPlay();
                    // surfaceView=videoListAdapter.surfaceView;
/*

        if(videoBean.getMp4Hd_url()!=null){
            url=videoBean.getMp4Hd_url();
        }else{
            url=videoBean.getMp4_url();
        }
                          onCreatSur(surfaceView,url);
                    Log.v("ppppp","OnItem>>>>>>>>>."+url);
*/

                 /* videoPlay.setMediaController(new MediaController(getContext()));
                 videoPlay.setVideoURI(Uri.parse(url));
                videoPlay.start();
                videoPlay.requestFocus();
                 Toast.makeText(getContext(),videoBean.getMp4Hd_url(),Toast.LENGTH_SHORT).show();*/

                    String url;
                      if(videoBean.getMp4Hd_url()!=null){
                          url=videoBean.getMp4Hd_url();
                      }else{
                          url=videoBean.getMp4_url();
                      }
                    String title=videoBean.getTitle();
                    String time=videoBean.getLength();

        Intent intent=new Intent(getContext(),PlayVideoActivity.class);
        Bundle bundle=new Bundle();
                //  bundle.putParcelable("VB",videoBean);
               bundle.putString("URL",url);
               bundle.putString("TT",title);
               bundle.putString("TIME",time);
                 intent.putExtras(bundle);
        Log.v("ppppppp",position+">>>>>>>+onItemClick>>>"+url+">>>>"+title);
          startActivity(intent);
    }
    private void inintVideo(String url){
        videoPaly=new MediaPlayer();
        try {
            videoPaly.setDataSource(url);
            Log.v("ppppp",">>>>>>>>>>>>inintVideo>>>>"+url);
        } catch (IOException e) {
            Toast.makeText(getContext(),"视频地址错误",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        videoPaly.setDisplay(mSurfaceHolder);
        videoPaly.prepareAsync();
        videoPaly.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mVideoHeight=videoPaly.getVideoHeight();
                mVideoWidth=videoPaly.getVideoWidth();
                /*if(mVideoHeight!=0&&mVideoWidth!=0){
                    mSurfaceHolder.setFixedSize(mVideoWidth,mVideoHeight);
                    videoPaly.start();
                    Log.v("ppppp",">>>>>>>>>>>>onPrepared>>>>"+videoPaly.isPlaying());
                }*/
                videoPaly.start();
            }
        });
        videoPaly.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mp, int percent) {          //缓冲流
                // 播放进度；
                // mBuff.setProgress(percent);
                int cPercent=percent*(videoPaly.getDuration())/100;
                //   mPlaySeek.setSecondaryProgress(percent);

                Log.v("pppppp",">>>>>>>>onBufferingUpdate>>>>"+cPercent+">>>current:"+videoPaly.getCurrentPosition());

            }
        });
        videoPaly.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                   surfaceView.setVisibility(View.VISIBLE);
                //播放完成；
                //  onStopTrackingTouch(mPlaySeek);
                Log.v("pppppp",">>>>>>>>onCompletion");
            }
        });
        videoPaly.setAudioStreamType(AudioManager.STREAM_MUSIC);
    }
    private void onCreatSur(SurfaceView surfaceView,final String url){
        mSurfaceHolder=surfaceView.getHolder();
        mSurfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                inintVideo(url);
                Log.v("pppppp",">>>>>>>>surfaceCreated>>>>>videoPaly.getDuration():"+videoPaly.getDuration());
            }
            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                Log.v("pppppp",">>>>>>>>surfaceChange"+format);
            }
            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                Log.v("pppppp",">>>>>>>>surfaceDestroyed>>>");
                if(videoPaly!=null){
                    videoPaly.release();
                    videoPaly=null;
                }
            }
        });
    }
}
