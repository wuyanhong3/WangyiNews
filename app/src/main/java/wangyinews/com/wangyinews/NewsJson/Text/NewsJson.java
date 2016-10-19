package wangyinews.com.wangyinews.NewsJson.Text;

import java.util.List;

/**
 * Created by Administrator on 2016/7/29.
 * T1348647909107 头条
 * T1348648517839 娱乐
 * T1348649079062 体育
 * T1348648756099 财经
 * T1348649580692 科技
 *
 *
 */
public class NewsJson {

	private List<NewsBean> T1348647909107;
	private List<NewsBean> T1348648517839;
	private List<NewsBean> T1348649079062;
	private List<NewsBean> T1348648756099;
	private List<NewsBean> T1348649580692;
	private  List<NewsBean> data;
	public List<NewsBean> getData(String id){
		switch (id){
			case "T1348647909107":
				   data=getT1348647909107();
			        break;
			case "T1348648517839":
				data=getT1348648517839();
			break;
			case "T1348649079062":
				data=getT1348649079062();
			break;
			case "T1348648756099":
				data=getT1348648756099();
			break;
			case "T1348649580692":
			data=getT1348649580692();
			break;
		}
		return data;
	}
  	public List<NewsBean> getT1348649079062() {
		return T1348649079062;
	}

	public void setT1348649079062(List<NewsBean> t1348649079062) {
		T1348649079062 = t1348649079062;
	}

	public List<NewsBean> getT1348648517839() {
		return T1348648517839;
	}

	public void setT1348648517839(List<NewsBean> t1348648517839) {
		T1348648517839 = t1348648517839;
	}

	public List<NewsBean> getT1348648756099() {
		return T1348648756099;
	}

	public void setT1348648756099(List<NewsBean> t1348648756099) {
		T1348648756099 = t1348648756099;
	}

	public List<NewsBean> getT1348649580692() {
		return T1348649580692;
	}

	public void setT1348649580692(List<NewsBean> t1348649580692) {
		T1348649580692 = t1348649580692;
	}




	public List<NewsBean> getT1348647909107() {
		return T1348647909107;
	}

	public void setT1348647909107(List<NewsBean> t1348647909107) {
		T1348647909107 = t1348647909107;
	}





}
