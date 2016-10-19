package wangyinews.com.wangyinews.WangYiNews.TextNews;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.scxh.slider.library.SliderLayout;
import com.scxh.slider.library.SliderTypes.BaseSliderView;
import com.scxh.slider.library.SliderTypes.TextSliderView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import wangyinews.com.wangyinews.MyVolley.RequestManager;
import wangyinews.com.wangyinews.NewsJson.Text.NewsBean;
import wangyinews.com.wangyinews.NewsJson.Text.NewsJson;
import wangyinews.com.wangyinews.NewsJson.Text.NewsPictureBean;
import wangyinews.com.wangyinews.R;
import wangyinews.com.wangyinews.WangYiNews.ConnectionUtils;
import wangyinews.com.xlistview_warmtel_library.XListView;

/**
 * A simple {@link Fragment} subclass.
 *
 * 文本新闻列表
 *
 */
public class TextNewsFragment extends Fragment implements XListView.IXListViewListener,XListView.OnItemClickListener {
    private List<NewsBean> mNewsList=new ArrayList<>();
    private XListView newsListView;
    private ConnectionUtils connectionUtils;  //联网取json
    private ListAdapter listAdapter;
    private  int pageSize=20;
    public   int pageCount=0;
    private  int   pageTotal=5;
    private View v;  //listHead;
    private List<NewsBean> list;
    private String id="T1348647909107";  //新闻类型
    public static  final String BUNDLE_HTTP_ID="url";
    public static final String BUNDLE_TYPE="position";
    private int type=0;
    public String mUrl;
    public SliderLayout sliderLayout;
    private   IshowNews IshowNews;
     private StringRequest stringRequest;
    public static Fragment newInstance(String id,int type){
        Bundle bundle=new Bundle();
        bundle.putString(BUNDLE_HTTP_ID,id);
        bundle.putInt(BUNDLE_TYPE,type);
        TextNewsFragment textNewsFragment=new TextNewsFragment();
        textNewsFragment.setArguments(bundle);
        return  textNewsFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle=getArguments();
        View   view=inflater.inflate(R.layout.fragment_home_news_list, container, false);
                newsListView= (XListView) view.findViewById(R.id.fragment_news_list);
        View view1=inflater.inflate(R.layout.activity_test,null);
                sliderLayout= (SliderLayout) view1.findViewById(R.id.SliderLayout);
        if(bundle!=null){
                             id=bundle.getString(BUNDLE_HTTP_ID);
                             type=bundle.getInt(BUNDLE_TYPE);
        }

                 connectionUtils=new ConnectionUtils(getContext());
                 newsListView.addHeaderView(view1);   //添加listView头部
                 listAdapter=new ListAdapter(getContext());
                 newsListView.setAdapter(listAdapter);
                        setListData(pageCount);           //设置list数据
                 newsListView.setXListViewListener(this);
                 newsListView.setPullLoadEnable(true);
                 newsListView.setPullRefreshEnable(true);
                 newsListView.setOnItemClickListener(this);
                         return view;
    }

    /**
     * 更新列表
     * */
    private void setListData(int pageCount){
              int pageCurrent=20*pageCount;
        if(type==0){
                      mUrl="http://c.m.163.com/nc/article/headline/"+id+"/"+pageCurrent+"-"+pageSize+".html";
                                                                                                   Log.v("ppppp","mUrl>>0>>>>>>>"+mUrl);
        }else{
                      mUrl="http://c.m.163.com/nc/article/list/"+id +"/"+pageCurrent+ "-" +pageSize+ ".html";
                                                                                                   Log.v("ppppp","mUrl>>1>>>>>>>"+mUrl);
        }
           /* connectionUtils.asycnConnect(mUrl, ConnectionUtils.Method.GET, ConnectionUtils.Cache.FALSE, new ConnectionUtils.HttpConnectionInterface() {
            @Override
            public void execute(String content) {
                if (content == null) {
                    Toast.makeText(getActivity(), "请求出错!", Toast.LENGTH_SHORT).show();
                    return;
                }
                newsListView.stopLoadMore();
                newsListView.stopRefresh();
                newsListView.setRefreshTime("刚刚");
                list=setJson(content,id);
                       getSliderData();
                listAdapter.setList(list);
            }
        });*/
                     stringRequest=new StringRequest(Request.Method.GET, mUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response == null) {
                    Toast.makeText(getActivity(), "请求出错!", Toast.LENGTH_SHORT).show();
                    return;
                }
                newsListView.stopLoadMore();
                newsListView.stopRefresh();
                newsListView.setRefreshTime("刚刚");
                list=setJson(response,id);
                getSliderData();
                listAdapter.setList(list);
            }
        }, new Response.ErrorListener() {
                          @Override
                          public void onErrorResponse(VolleyError error) {
                              Toast.makeText(getActivity(), "请求出错!", Toast.LENGTH_SHORT).show();
                          }
                      });
        RequestManager.addRequest(stringRequest,this);
    }
    /**
     * 解析json
     *
     * */
    private  List<NewsBean>  setJson(String json,String id){
        Gson gson=new Gson();
        NewsJson newsJson=gson.fromJson(json,NewsJson.class);
        List<NewsBean> List=newsJson.getData(id);
        for(NewsBean newsBean:List){
            NewsBean newsBean1=new NewsBean();
              if(newsBean.getImgextra()!=null){
                                                 newsBean1.setTitle(newsBean.getTitle());
                                                                Log.v("ppppp",">>>>>>>setJson>>>"+newsBean.getImgextra().size()+"");
                     List<NewsPictureBean> nList=newsBean.getImgextra();
                     List<NewsPictureBean> list=new ArrayList<>();
                for(int i=0;i<nList.size();i++){
                          NewsPictureBean newsPictureBean=new NewsPictureBean();
                                          newsPictureBean.setImgsrc(nList.get(i).getImgsrc());
                                 list.add(newsPictureBean);
                }
                   newsBean1.setImgextra(list);
            }
            newsBean1.setUrl(newsBean.getUrl());
            newsBean1.setLtitle(newsBean.getLtitle());
            newsBean1.setDigest("    "+newsBean.getDigest());
            newsBean1.setImgsrc(newsBean.getImgsrc());
            newsBean1.setLmodity(newsBean.getLmodity());
            newsBean1.setUrl_3w(newsBean.getUrl_3w());
                     mNewsList.add(newsBean1);
        }
                     return mNewsList;
    }
    @Override
    public void onRefresh() {
          pageCount=0;
         // newsListView.removeHeaderView(v);
          list.clear();
                setListData(pageCount);
                                                    Log.v("pppppp",">>>>>>>>>"+"onRefresh"+mUrl+">>>pagCount"+pageCount);
    }
    @Override
    public void onLoadMore() {
        if(++pageCount<pageTotal){
                                    // newsListView.removeHeaderView(v);
                                     setListData(pageCount);
        }else{
                   Toast.makeText(getActivity(),"已是最后一页",Toast.LENGTH_LONG).show();
        }
                                            Log.v("ppppp",">>>>>>>>>"+"onLoadMore"+mUrl);
    }
    /**
     * 设置顶部图片新闻
     *
     * */
    public void  getSliderData(){
        HashMap<String,String> http_url_maps = getData();

                     sliderLayout.removeAllSliders();
                  for(String name : http_url_maps.keySet()){
                                  TextSliderView textSliderView = new TextSliderView(getContext());

                                     textSliderView
                                                   .description(name)
                                                   .image(http_url_maps.get(name))
                                                   .setScaleType(BaseSliderView.ScaleType.Fit);
                      sliderLayout.addSlider(textSliderView);
        }
                      sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Right_Bottom);

    }
    /**
     * 顶部图片新闻数据更新；
     *
     * */
    public HashMap<String,String> getData(){
                 HashMap<String,String> hashMap=new HashMap<>();
        if(hashMap!=null){
             hashMap.clear();
    }
        for (int i=0;i<5;i++){
                                 hashMap.put(mNewsList.get(i).getLtitle(),mNewsList.get(i).getImgsrc());
        }
                         return hashMap;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IshowNews){
            IshowNews=(TextNewsMainActivity)context;
        }
    }

    interface IshowNews{
        void showNewsContent( NewsBean newsBean,int position);
    }


    /**
     * 新闻劣列表Item点击浏览详细新闻；
     *
     * **/
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
          //  ListAdapter listAdapter= (ListAdapter) parent.getAdapter();
                     NewsBean newsBean= (NewsBean) listAdapter.getItem((position-2));
        if(listAdapter.getItemViewType(position-2)==0){
                               Log.v("ppppppp","onItemClick>>>>>>>>>"+newsBean.getLtitle()+"position"+(position-2));
                     Intent intent=new Intent(getContext(),TextNewsContentActivity.class);
                             intent.putExtra("CONTENT",newsBean.getUrl());
                              startActivity(intent);
        }else{
            List<NewsPictureBean> pList=newsBean.getImgextra();
            Log.v("ppppppp","onItemClick>>>>>>>>>"+pList.get(0).getImgsrc()+"position"+(position-2));
            Toast.makeText(getContext(),newsBean.getImgextra().size()+"",Toast.LENGTH_SHORT);
                  Intent intent2=new Intent(getContext(),TextPictureActivity.class);
                            intent2.putExtra("content",newsBean);
                              intent2.putParcelableArrayListExtra("list", (ArrayList<? extends Parcelable>) pList);
                              startActivity(intent2);
        }


    }
}
