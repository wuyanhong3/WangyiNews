package wangyinews.com.wangyinews.WangYiNews.VideoNews;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import wangyinews.com.wangyinews.NewsJson.Video.VideoBean;
import wangyinews.com.wangyinews.R;

public class PlayVideoActivity extends AppCompatActivity implements View.OnClickListener,SeekBar.OnSeekBarChangeListener{
       private ProgressBar mBuff;
       private TextView videoTitle;
       private TextView mCurrentTime,mTotalTime;
       private int mCurrenTimes;
       private ImageButton back;
       private SurfaceView videoSurface;
       private SeekBar mPlaySeek;
       private MediaPlayer videoPaly;
       private String mVideoUrl;
       private SurfaceHolder mSurfaceHolder;
       private int mVideoHeight,mVideoWidth;
       private int time;
       private String title;
       private boolean isFlage=true;
       private SimpleDateFormat  dateFormat=new SimpleDateFormat("mm:ss");
       private Handler handler=new Handler(){
           @Override
           public void handleMessage(Message msg) {
                 int iCurrent=msg.arg2;
                   mPlaySeek.setProgress(iCurrent);
                   mCurrentTime.setText(dateFormat.format(new Date(iCurrent)));
                 //  mBuff.setMax(100);
           }
       };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);
        inintID();
        Intent intent=getIntent();
        mVideoUrl=intent.getStringExtra("URL");
        // VideoBean videoBean=intent.getParcelableExtra("VB");
        time= Integer.parseInt(intent.getStringExtra("TIME"));
        title=intent.getStringExtra("TT");
        videoTitle.setText(title);
        mPlaySeek.setOnClickListener(this);                                             Log.v("pppppppp",">>>>>>>>>>>>inintVideo>>>>>"+mVideoUrl+">>>>>>"+title);
        back.setOnClickListener(this);
        mPlaySeek.setOnSeekBarChangeListener(this);
        mSurfaceHolder=videoSurface.getHolder();
        mSurfaceHolder.addCallback(new SurfaceHolder.Callback() {
             @Override
             public void surfaceCreated(SurfaceHolder holder) {
                        inintVideo();

                         mPlaySeek.setMax(time*1000);

                                                    Log.v("pppppp",">>>>>>>>surfaceCreated>>>>>videoPaly.getDuration():"+videoPaly.getDuration()+">>>maxTime:"+time);
               String  Time=dateFormat.format(new Date(time*1000));
                       mTotalTime.setText(Time);
                                                                                Log.v("pppppp",">>>>>>>>surfaceCreated>>>>>"+Time);

             }

             @Override
             public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                                                                               Log.v("pppppp",">>>>>>>>surfaceChange"+format);
             }

             @Override
             public void surfaceDestroyed(SurfaceHolder holder) {
                                                                                Log.v("pppppp",">>>>>>>>surfaceDestroyed");
                      if(videoPaly!=null){
                          videoPaly.release();
                           videoPaly=null;
                          isFlage=false;
                      }

             }
         });
        new updataSeekbar().start();
    }
    private void inintID(){
       // mBuff= (ProgressBar) findViewById(R.id.video_news_play_buffer);
        videoTitle= (TextView) findViewById(R.id.video_news_play_title);
        videoSurface=(SurfaceView) findViewById(R.id.video_news_play_surface);
        back= (ImageButton) findViewById(R.id.video_news_play_back);
        mPlaySeek= (SeekBar) findViewById(R.id.video_news_play_seekbar);
        mCurrentTime= (TextView) findViewById(R.id.video_news_play_current_time);
        mTotalTime= (TextView) findViewById(R.id.video_news_play_total_time);
    }
 private void inintVideo(){
        videoPaly=new MediaPlayer();
     try {
         videoPaly.setDataSource(mVideoUrl);
                                                    Log.v("ppppp",">>>>>>>>>>>>inintVideo>>>>"+mVideoUrl);
     } catch (IOException e) {
         Toast.makeText(this,"视频地址错误",Toast.LENGTH_SHORT).show();
         e.printStackTrace();
     }
     videoPaly.setDisplay(mSurfaceHolder);
     videoPaly.prepareAsync();
     videoPaly.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
         @Override
         public void onPrepared(MediaPlayer mp) {
             mVideoHeight=videoPaly.getVideoHeight();
             mVideoWidth=videoPaly.getVideoWidth();
             if(mVideoHeight!=0&&mVideoWidth!=0){
                           mSurfaceHolder.setFixedSize(mVideoWidth,mVideoHeight);
                            videoPaly.start();
                                                     Log.v("ppppp",">>>>>>>>>>>>onPrepared>>>>"+videoPaly.isPlaying());
             }
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
            //播放完成；
          //  onStopTrackingTouch(mPlaySeek);
                                                       Log.v("pppppp",">>>>>>>>onCompletion");
        }
    });
     videoPaly.setAudioStreamType(AudioManager.STREAM_MUSIC);
 }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                  mCurrenTimes=mPlaySeek.getProgress();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
                       videoPaly.seekTo(mCurrenTimes);
                                                         Log.v("pppppp",">>>>>>>>>>>>onStopTrackingTouch>>>"+mCurrenTimes+">>>currentPosition"+videoPaly.getCurrentPosition());

    }

    /**
     *
     * 更新进度条
     * */
    class updataSeekbar extends Thread {
        @Override
        public void run() {
            while (isFlage) {
                if(videoPaly!=null&&videoPaly.isPlaying()){
                int current = videoPaly.getCurrentPosition();
                  Message message=Message.obtain();
                          message.arg2=current;
                  handler.sendMessage(message);
                   SystemClock.sleep(1000);}
            }
        }
    }
    private boolean checkSDCard() {
        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }
/**
 *
 * back
 * */
    @Override
    public void onClick(View v) {
        finish();
    }
}
