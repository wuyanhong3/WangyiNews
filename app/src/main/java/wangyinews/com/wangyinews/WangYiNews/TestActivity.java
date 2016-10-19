package wangyinews.com.wangyinews.WangYiNews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.scxh.slider.library.Indicators.PagerIndicator;
import com.scxh.slider.library.SliderLayout;
import com.scxh.slider.library.SliderTypes.BaseSliderView;
import com.scxh.slider.library.SliderTypes.TextSliderView;

import java.util.HashMap;

import wangyinews.com.wangyinews.R;

public class TestActivity extends AppCompatActivity {       //测试slider包
      private SliderLayout sliderLayout;
      private PagerIndicator pagerIndicator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        sliderLayout= (SliderLayout) findViewById(R.id.SliderLayout);
          pagerIndicator= (PagerIndicator) findViewById(R.id.custom_PagerIndicator);
        HashMap<String,String> http_url_maps = getData();

        for(String name : http_url_maps.keySet()){
            TextSliderView textSliderView = new TextSliderView(this);

            textSliderView
                    .description(name)
                    .image(http_url_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit);

            sliderLayout.addSlider(textSliderView);
        }

        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Right_Bottom);
    }

    private HashMap<String,String> getData(){
        HashMap<String,String> http_url_maps = new HashMap<String, String>();
        http_url_maps.put("习近平接受八国新任驻华大使递交国书", "http://img.my.csdn.net/uploads/201407/26/1406383291_6518.jpg");
        http_url_maps.put("天津港总裁出席发布会", "http://img.my.csdn.net/uploads/201407/26/1406383290_9329.jpg");
        http_url_maps.put("瑞海公司从消防鉴定到安评一路畅通无阻", "http://img.my.csdn.net/uploads/201407/26/1406383290_1042.jpg");
        http_url_maps.put("Airbnb高调入华 命运将如Uber一样吗？", "http://img.my.csdn.net/uploads/201407/26/1406383275_3977.jpg");

        return http_url_maps;
    }
    }

