package wangyinews.com.wangyinews.WangYiNews.PictureNews;


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
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import wangyinews.com.wangyinews.NewsJson.Picture.Pics;
import wangyinews.com.wangyinews.NewsJson.Picture.PicsBean;
import wangyinews.com.wangyinews.NewsJson.Picture.PictureBean;
import wangyinews.com.wangyinews.NewsJson.Picture.PictureData;
import wangyinews.com.wangyinews.NewsJson.Picture.PictureJson;
import wangyinews.com.wangyinews.R;
import wangyinews.com.wangyinews.WangYiNews.ConnectionUtils;

/**
 * A simple {@link Fragment} subclass.
 *
 * 图片新闻缩略图Fragment
 *
 */
public class PictureNewsFragment extends Fragment implements AdapterView.OnItemClickListener {
      private GridView mPicture;
      private TextView error;
      private ConnectionUtils connectionUtils;
      private List<PictureBean> mList=new ArrayList<>();
      private PictureGridAdapter pictureGridAdapter;
      private String mUrl="http://api.sina.cn/sinago/list.json?channel=hdpic_toutiao&adid=4ad30dabe134695c3b7c3a65977d7e72&wm=b207&from=6042095012&chwm=12050_0001&oldchwm=&imei=867064013906290&uid=802909da86d9f5fc&p=";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
                Bundle bundle=getArguments();
                   if(bundle!=null){
                    mUrl=bundle.getString("URL");}
                       View view=inflater.inflate(R.layout.fragment_picture, container, false);
                                   mPicture= (GridView) view.findViewById(R.id.picture_news_grid);
                                   error= (TextView) view.findViewById(R.id.picture_news_error);
                                     error.setVisibility(View.GONE);
                                   connectionUtils=new ConnectionUtils(getContext());
                                   pictureGridAdapter =new PictureGridAdapter(getContext());
                                    mPicture.setAdapter(pictureGridAdapter);
                                          setData();
                                    mPicture.setOnItemClickListener(this);

                              return  view;
    }

   public static PictureNewsFragment newInstance(String url) {
        Bundle args = new Bundle();
        args.putString("URL",url);
        PictureNewsFragment fragment = new PictureNewsFragment();
                                                   Log.v("ppppp",">>>>>>>>>>>>>>>>ictureFragment");
        fragment.setArguments(args);
        return fragment;
    }
    private void setData(){
           connectionUtils.asycnConnect(mUrl, ConnectionUtils.Method.GET, new ConnectionUtils.HttpConnectionInterface() {
               @Override
               public void execute(String content) {

                   if(content==null||content.isEmpty()){
                       error.setVisibility(View.VISIBLE);
                           Log.v("pppj","error>>>>>>>");

                   }else{
                       error.setVisibility(View.GONE);  //获取数据正常，隐藏错误提示
                       mList=setJson(content);
                       pictureGridAdapter.setList(mList);
                          Log.v("pppj","content>>>>"+content);
                   }

                                                        Log.v("ppppp",">>>>>>>>>>>>>>>>ictureFragment"+">>>>>>>>>>.."+mList.size());
               }
           });
    }
       private  List<PictureBean> setJson(String json){

           Gson gson=new Gson();
           PictureJson pictureJson=gson.fromJson(json,PictureJson.class);
           PictureData pictureData=pictureJson.getData();
           List<PictureBean> beanList=pictureData.getList();
           for(PictureBean pictureBean:beanList){
                  PictureBean iPictureBean=new PictureBean();
                  iPictureBean.setId(pictureBean.getId());
                  iPictureBean.setIntro(pictureBean.getIntro());
                  iPictureBean.setKpic(pictureBean.getKpic());
                  iPictureBean.setLink(pictureBean.getLink());
                  iPictureBean.setLong_title(pictureBean.getLong_title());

                     List<PicsBean> picList=pictureBean.getPics().getList();
                     List<PicsBean> picInList=new ArrayList<>();
                     for(PicsBean picsBean:picList){
                        PicsBean iPicsBean=new PicsBean();
                        iPicsBean.setKpic(picsBean.getKpic());
                        iPicsBean.setAlt(picsBean.getAlt());
                        iPicsBean.setPic(picsBean.getPic());
                        picInList.add(iPicsBean);
                      }
                       Pics pics=new Pics();
                       pics.setList(picInList);


                   iPictureBean.setPics(pics);
                  iPictureBean.setTitle(pictureBean.getTitle());
                  mList.add(iPictureBean);
           }
           return  mList;

       }
     interface InterShowPicture{
         void SendPicture(List<PicsBean> list,String title);
     }
     InterShowPicture interShowPicture;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof InterShowPicture){
            interShowPicture= (PictureNewsContentActivity) context;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                  PictureBean pictureBean= (PictureBean) pictureGridAdapter.getItem(position);
                               List<PicsBean> list=pictureBean.getPics().getList();
                                   String alt=list.get(1).getAlt();
                                   String title=pictureBean.getTitle();
                                   String iId=pictureBean.getId();
        Intent intent=new Intent(getContext(),PictureNewsContentActivity.class);
          Bundle bundle=new Bundle();
         bundle.putParcelableArrayList("PICS", (ArrayList<? extends Parcelable>) list);
         bundle.putString("TIL",title);
         bundle.putString("ID",iId);
        intent.putExtras(bundle);
        startActivity(intent);
                                // Log.v("ppppp","PictureNewsFragment>>>>>>>>>>"+list.size()+"");
                                // interShowPicture.SendPicture(list,title );//item接口
    }
}
