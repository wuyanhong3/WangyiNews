package wangyinews.com.wangyinews.NewsJson.Text;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/7/29.
 *"postid": "BT2V2I4E0001124J",
 "url_3w": "http://news.163.com/16/0728/16/BT2V2I4E0001124J.html",
 "votecount": 4237,
 "replyCount": 5119,
 "skipID": "S1451880983492",
 "ltitle": "唐山大地震40周年 习近平考察唐山向逝者献花",
 "digest": "7月28日，习近平总书记到河北省唐山市考察。上午9时55分，习近平来到唐山地震遗址公园，在纪念墙前敬献花篮，深切缅怀40年前在那场大地震中罹难的同胞和在抗震救灾",
 "skipType": "special",
 "url": "http://3g.163.com/news/16/0728/16/BT2V2I4E0001124J.html",
 "specialID": "S1451880983492",
 "docid": "BT2V2I4E0001124J",
 "title": "唐山大地震40周年 习近平考察唐山向逝者献花",
 "source": "央视$",
 "imgextra": [
 {
 "imgsrc": "http://cms-bucket.nosdn.127.net/ab4fbc2b92c6419f9aa0bcbd77b0ec2720160808145431.jpeg"
 },
 {
 "imgsrc": "http://cms-bucket.nosdn.127.net/e11f5b98dd4b4db8a39a617ab19f27e720160808145430.jpeg"
 }
 ],
 "priority": 302,
 "lmodify": "2016-07-29 01:07:53",
 "boardid": "news_guonei8_bbs",
 "subtitle": "",
 "imgsrc": "http://cms-bucket.nosdn.127.net/0d5efe9fc8744632bb8cb62b0fa81e4920160728173055.png",
 "ptime": "2016-07-28 16:42:13"
 *
 *
 *
 */
public class NewsBean implements Parcelable {
	private String url_3w;
	private String ltitle;
	private String digest;
	private String imgsrc;
	private String lmodify;
	private String url;
    public NewsBean(){}
	public NewsBean(Parcel in) {
		url_3w = in.readString();
		ltitle = in.readString();
		digest = in.readString();
		imgsrc = in.readString();
		lmodify = in.readString();
		url = in.readString();
		title = in.readString();

	}

	public static final Creator<NewsBean> CREATOR = new Creator<NewsBean>() {
		@Override
		public NewsBean createFromParcel(Parcel in) {
			return new NewsBean(in);
		}

		@Override
		public NewsBean[] newArray(int size) {
			return new NewsBean[size];
		}
	};

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}


	private List<NewsPictureBean> imgextra;
	private String title;
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}



	public List<NewsPictureBean> getImgextra() {
		return imgextra;
	}
  	public void setImgextra(List<NewsPictureBean> list) {
		this.imgextra = list;
	}

	public String getLmodify() {
		return lmodify;
	}

	public void setLmodify(String lmodify) {
		this.lmodify = lmodify;
	}



	public String getLmodity() {
		return lmodify;
	}

	public void setLmodity(String lmodity) {
		this.lmodify = lmodity;
	}

	public String getUrl_3w() {
		return url_3w;
	}

	public void setUrl_3w(String url_3w) {
		this.url_3w = url_3w;
	}

	public String getLtitle() {
		return ltitle;
	}

	public void setLtitle(String ltitle) {
		this.ltitle = ltitle;
	}

	public String getDigest() {
		return digest;
	}

	public void setDigest(String digest) {
		this.digest = digest;
	}

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
		parcel.writeString(url_3w);
		parcel.writeString(ltitle);
		parcel.writeString(digest);
		parcel.writeString(imgsrc);
		parcel.writeString(lmodify);
		parcel.writeString(url);
		parcel.writeString(title);
	}
}
