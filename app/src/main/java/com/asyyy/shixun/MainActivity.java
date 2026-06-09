package com.asyyy.shixun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 设置一个Handler来延迟1.5秒后切换到activity_main2布局
        new Handler().postDelayed(() -> {
            setContentView(R.layout.activity_main2);
            // 再次设置一个Handler来延迟1.5秒后切换到activity_main3布局
            new Handler().postDelayed(() -> {
                setContentView(R.layout.activity_main3);
                // 在切换到activity_main3布局后启动新的Activity并结束当前Activity
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
            }, 1500); // 延迟1.5秒（1500毫秒）
        }, 1500); // 延迟1.5秒（1500毫秒）
    }
}