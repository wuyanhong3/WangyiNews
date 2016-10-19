package wangyinews.com.wangyinews.WangYiNews.TextNews;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageButton;

import wangyinews.com.wangyinews.R;

public class TextNewsContentActivity extends AppCompatActivity implements View.OnClickListener {
        private WebView mNewsContent;
        private ImageButton back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_news_content);
        initView();
        back.setOnClickListener(this);
           Intent intent=getIntent();
             String url=intent.getStringExtra("CONTENT");
            mNewsContent.loadUrl(url);

    }
    private void initView(){
        mNewsContent= (WebView) findViewById(R.id.text_news_content);
        back= (ImageButton) findViewById(R.id.text_news_back);
    }

    @Override
    public void onClick(View v) {
            finish();
    }
}
