package wangyinews.com.wangyinews.NewsJson.Picture;

/**
 * id
 * Created by Administrator on 2016/8/11.
 * "title": "奇妙化学反应:媲美好莱坞大片",
 "long_title": "中学实验媲美好莱坞大片：化学反应似绚丽舞蹈",
 * "link": "http://photo.sina.cn/album_5_453_70767.htm?ch=5&fromsinago=1",
 * "kpic": "http://l.sinaimg.cn/www/dy/slidenews/5_t160/2016_32/453_80232_158342.jpg/original.jpg",
 *  "intro": "漆黑的幕布前，白色雪花状的絮状物簌簌直下，升腾，旋转，跳着绚丽的舞蹈。",
 *    "pics"------------详细图集
 *
 */
public class PictureBean {

    private String id;
    private String title;
    private String long_title;
    private  String link;
    private String kpic;
    private String intro;
    private Pics pics;
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLong_title() {
        return long_title;
    }

    public void setLong_title(String long_title) {
        this.long_title = long_title;
    }

    public String getKpic() {
        return kpic;
    }

    public void setKpic(String kpic) {
        this.kpic = kpic;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public Pics getPics() {
        return pics;
    }

    public void setPics(Pics pics) {
        this.pics = pics;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
