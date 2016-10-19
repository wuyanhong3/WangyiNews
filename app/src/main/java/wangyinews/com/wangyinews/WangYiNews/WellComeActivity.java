package wangyinews.com.wangyinews.WangYiNews;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import wangyinews.com.wangyinews.R;
import wangyinews.com.wangyinews.WangYiNews.TextNews.TextNewsMainActivity;

/**
 * 欢迎界面
 * */

public class WellComeActivity extends AppCompatActivity implements View.OnClickListener {
     private TextView mTime;
     private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wecome);
        intent=new Intent(this, TextNewsMainActivity.class);
        mTime= (TextView) findViewById(R.id.wecome_new_time);
        mTime.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        startActivity(intent);
        finish();
    }
}
