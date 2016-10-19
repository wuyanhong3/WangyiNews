package wangyinews.com.wangyinews.NewsJson.Picture;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/8/11.
 *
 *
 *
 *   "pic": "http://r3.sinaimg.cn/10230/2016/0810/b9/e/78625309/auto.jpg",
 "alt": "中学实验媲美好莱坞大片：化学反应似绚丽舞蹈",
 "kpic": "http://l.sinaimg.cn/www/dy/slidenews/5_img/2016_32/453_80233_729870.jpg/original.jpg"
 */
public class PicsBean implements Parcelable {
    private String pic;
    private String alt;
    private String kpic;

    public PicsBean(Parcel in) {
        pic = in.readString();
        alt = in.readString();
        kpic = in.readString();
    }
    public PicsBean(){}
    public static final Creator<PicsBean> CREATOR = new Creator<PicsBean>() {
        @Override
        public PicsBean createFromParcel(Parcel in) {
            return new PicsBean(in);
        }

        @Override
        public PicsBean[] newArray(int size) {
            return new PicsBean[size];
        }
    };

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getKpic() {
        return kpic;
    }

    public void setKpic(String kpic) {
        this.kpic = kpic;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(pic);
        dest.writeString(alt);
        dest.writeString(kpic);
    }
}
