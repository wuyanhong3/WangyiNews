package wangyinews.com.wangyinews.NewsJson.Video;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/8/15.
 *
 * {
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
 */
public class VideoBean implements Parcelable {

    private String description;
    private String topicImg;
    private String  mp4Hd_url;
    private String  title;
    private String   topicDesc;
    private String   cover;
    private VideoTopic  videoTopic;
    private String ptime;
    private String length;
    private String mp4_url;
    public String getMp4_url() {
        return mp4_url;
    }

    public void setMp4_url(String mp4_url) {
        this.mp4_url = mp4_url;
    }
   public VideoBean(){}
    protected VideoBean(Parcel in) {
        description = in.readString();
        topicImg = in.readString();
        mp4Hd_url = in.readString();
        title = in.readString();
        topicDesc = in.readString();
        cover = in.readString();
        ptime = in.readString();
        length = in.readString();
        mp4_url=in.readString();
    }

    public static final Creator<VideoBean> CREATOR = new Creator<VideoBean>() {
        @Override
        public VideoBean createFromParcel(Parcel in) {
            return new VideoBean(in);
        }

        @Override
        public VideoBean[] newArray(int size) {
            return new VideoBean[size];
        }
    };

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }


    public String getPtime() {
        return ptime;
    }

    public void setPtime(String ptime) {
        this.ptime = ptime;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTopicImg() {
        return topicImg;
    }

    public void setTopicImg(String topicImg) {
        this.topicImg = topicImg;
    }

    public String getMp4Hd_url() {
        return mp4Hd_url;
    }

    public void setMp4Hd_url(String mp4Hd_url) {
        this.mp4Hd_url = mp4Hd_url;
    }

    public String getTopicDesc() {
        return topicDesc;
    }

    public void setTopicDesc(String topicDesc) {
        this.topicDesc = topicDesc;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public VideoTopic getVideoTopic() {
        return videoTopic;
    }

    public void setVideoTopic(VideoTopic videoTopic) {
        this.videoTopic = videoTopic;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(description);
        dest.writeString(topicImg);
        dest.writeString(mp4Hd_url);
        dest.writeString(title);
        dest.writeString(topicDesc);
        dest.writeString(cover);
        dest.writeString(ptime);
        dest.writeString(length);
        dest.writeString(mp4_url);
    }
}
