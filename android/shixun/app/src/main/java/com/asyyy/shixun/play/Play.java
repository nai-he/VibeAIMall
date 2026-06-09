package com.asyyy.shixun.play;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.asyyy.shixun.MActivity;
import com.asyyy.shixun.R;

public class Play extends AppCompatActivity {
    private Button btn_peach;
    private TextView tv_count;
    private int totalCount = 0;
    private TextView back_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play1); // 设置布局

        back_home = findViewById(R.id.back_home);
        back_home.setOnClickListener(v -> {
            startActivity(new Intent(this, MActivity.class));
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            finish();
        });

        init();
    }

    private void init() {
        btn_peach = findViewById(R.id.btn_peach);
        tv_count = findViewById(R.id.tv_count);
        if (btn_peach == null || tv_count == null) {
            Log.e("PlayActivity", "Button or TextView not found");
            return;
        }
        btn_peach.setOnClickListener(view -> {
            Intent intent = new Intent(Play.this, PeachActivity.class);
            startActivityForResult(intent, 1);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            int count = data.getIntExtra("count", 0); // 获取回传的数据
            totalCount += count;
            tv_count.setText("摘到" + totalCount + "个");
        } else {
            Log.e("PlayActivity", "Error in onPlay");
        }
    }
}