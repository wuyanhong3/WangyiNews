package wangyinews.com.wangyinews.NewsJson.Video;

/**
 * Created by Administrator on 2016/8/15.
 *
 * "videoTopic": {
 "alias": "想知道娱乐圈的事情吗？",
 "tname": "东星娱乐",
 "ename": "T1460515708477",
 "tid": "T1460515708477"
 },
 *
 */
public class VideoTopic {
    private String alias;
    private String tname;
    private String ename;
    private String tid;
    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

}
