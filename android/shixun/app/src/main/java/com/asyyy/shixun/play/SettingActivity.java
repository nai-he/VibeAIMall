package com.asyyy.shixun.play;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.asyyy.shixun.LoginActivity;
import com.asyyy.shixun.MActivity;
import com.asyyy.shixun.R;

public class SettingActivity extends AppCompatActivity {

    private TextView back_home2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tuichu);

        back_home2 = findViewById(R.id.back_home2);

        back_home2.setOnClickListener(v -> {
            startActivity(new Intent(this, LoginActivity.class));
            overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
            finish();
        });
    }
}
