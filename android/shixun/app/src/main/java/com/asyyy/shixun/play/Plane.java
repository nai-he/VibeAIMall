package com.asyyy.shixun.play;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.asyyy.shixun.R;

public class Plane extends AppCompatActivity {
    private long time; //用于检测按两次 "再按一次退出游戏"

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(new hua(this)); //setContentView()跟swing的add()差不多吧，不过这里只能添加一个控件，默认铺满屏幕
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            long t = System.currentTimeMillis(); //获取系统时间
            if (t - time <= 500) {
                exit(); //如果500毫秒内按下两次返回键则退出游戏
            } else {
                time = t;
                Toast.makeText(getApplicationContext(), "再按一次退出游戏", Toast.LENGTH_SHORT).show();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void exit() {
        Plane.this.finish();
    }
}
