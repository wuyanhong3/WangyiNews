package wangyinews.com.wangyinews.NewsJson.Text;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2016/8/8.
 */
public class NewsPictureBean implements Parcelable{

    private String imgsrc;
    public NewsPictureBean(){}
    protected NewsPictureBean(Parcel in) {
        imgsrc = in.readString();
    }

    public static final Creator<NewsPictureBean> CREATOR = new Creator<NewsPictureBean>() {
        @Override
        public NewsPictureBean createFromParcel(Parcel in) {
            return new NewsPictureBean(in);
        }

        @Override
        public NewsPictureBean[] newArray(int size) {
            return new NewsPictureBean[size];
        }
    };

    public String getImgsrc() {
        return imgsrc;
    }

    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(imgsrc);
    }
}
