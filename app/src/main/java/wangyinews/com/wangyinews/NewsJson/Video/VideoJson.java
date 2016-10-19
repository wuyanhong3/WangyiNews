package wangyinews.com.wangyinews.NewsJson.Video;

import android.provider.MediaStore;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2016/8/15.
 *
 *  "V9LG4B3A0": [
 {
 "topicImg": "http://vimg3.ws.126.net/image/snapshot/2016/2/E/8/VBG7GSLE8.jpg",
 "videosource": "新媒体",
 "mp4Hd_url": "http://flv2.bn.netease.com/videolib3/1608/15/YqITt6878/HD/YqITt6878-mobile.mp4",
 "topicDesc": "想知道娱乐圈的事情吗？",
 "topicSid": "VBFGBMQM5",
 "cover": "http://vimg1.ws.126.net/image/snapshot/2016/8/T/4/VBTIQ0CT4.jpg",
 "title": "李宇春献唱音乐节 薛之谦人气不输歌坛天后",
 "playCount": 7752,
 "replyBoard": "video_bbs",
 "videoTopic": {
 "alias": "想知道娱乐圈的事情吗？",
 "tname": "东星娱乐",
 "ename": "T1460515708477",
 "tid": "T1460515708477"
 },
 "sectiontitle": "",
 "replyid": "BTIQ0CT3008535RB",
 "description": "",
 "mp4_url": "http://flv2.bn.netease.com/videolib3/1608/15/YqITt6878/SD/YqITt6878-mobile.mp4",
 "length": 221,
 "playersize": 0,
 "m3u8Hd_url": "http://flv2.bn.netease.com/videolib3/1608/15/YqITt6878/HD/movie_index.m3u8",
 "vid": "VBTIQ0CT3",
 "m3u8_url": "http://flv2.bn.netease.com/videolib3/1608/15/YqITt6878/SD/movie_index.m3u8",
 "ptime": "2016-08-15 10:08:10",
 "topicName": "东星娱乐"
 },
 *
 *
 * 热点视频：V9LG4B3A0
 * 娱乐视频：V9LG4CHOR
 * 搞笑视频：V9LG4E6VR
 * 精选视频：00850FRB
 */
public class VideoJson {
    private List<VideoBean> V9LG4B3A0;
    private  List<VideoBean> V9LG4CHOR;
    private List<VideoBean>  V9LG4E6VR;
         @SerializedName("00850FRB")              //因为json  key值为含数字，需重命名
        private List<VideoBean> JXVIDEO;
    private List<VideoBean> videoBean;

    public List<VideoBean> getVideoBean(String id){   //根据id返回相应的list
        switch (id){
            case "V9LG4B3A0":
                videoBean=getV9LG4B3A0();
                break;
            case "V9LG4CHOR":
                videoBean=getV9LG4CHOR();
                break;
            case "V9LG4E6VR":
                videoBean=getV9LG4E6VR();
                break;
            case "00850FRB":
                videoBean=getJXVIDEO();
                break;
        }
        return videoBean;
    }
    public List<VideoBean> getJXVIDEO() {
        return JXVIDEO;
    }

    public void setJXVIDEO(List<VideoBean> JXVIDEO) {
        this.JXVIDEO = JXVIDEO;
    }
    public List<VideoBean> getV9LG4B3A0() {
        return V9LG4B3A0;
    }

    public void setV9LG4B3A0(List<VideoBean> v9LG4B3A0) {
        V9LG4B3A0 = v9LG4B3A0;
    }

    public List<VideoBean> getV9LG4CHOR() {
        return V9LG4CHOR;
    }

    public void setV9LG4CHOR(List<VideoBean> v9LG4CHOR) {
        V9LG4CHOR = v9LG4CHOR;
    }

    public List<VideoBean> getV9LG4E6VR() {
        return V9LG4E6VR;
    }

    public void setV9LG4E6VR(List<VideoBean> v9LG4E6VR) {
        V9LG4E6VR = v9LG4E6VR;
    }

    public List<VideoBean> getVideoBean() {
        return videoBean;
    }

    public void setVideoBean(List<VideoBean> videoBean) {
        this.videoBean = videoBean;
    }



}
