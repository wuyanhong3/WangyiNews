package wangyinews.com.wangyinews.NewsJson.Picture;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2016/8/14.
 *
 *   "pics": [
 {
 "pic": "http://r3.sinaimg.cn/10230/2016/0814/da/3/85615302/auto.jpg",
 "alt": "凯旋级弹道导弹核潜艇是法国海基核力量的支柱，总共建造4艘，使用了许多先进的技术，凯旋级成为世界上造价最高的核潜艇，一艘艇的造价高达40亿欧元，换算过来相当于近300亿人民币。供图：北京时间",
 "kpic": "http://l.sinaimg.cn/www/dy/slidenews/1_img/2016_32/2841_721748_687302.jpg/original.jpg",
 "size": "640x429"
 },
 *
 *
 */
public class DesPicsBean  implements Parcelable{
   private String pic;
   private String alt;
    private String kpic;

    public DesPicsBean(){}
    public DesPicsBean(Parcel in) {
        pic = in.readString();
        alt = in.readString();
        kpic = in.readString();
    }

    public static final Creator<DesPicsBean> CREATOR = new Creator<DesPicsBean>() {
        @Override
        public DesPicsBean createFromParcel(Parcel in) {
            return new DesPicsBean(in);
        }

        @Override
        public DesPicsBean[] newArray(int size) {
            return new DesPicsBean[size];
        }
    };

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

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
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
