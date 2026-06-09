//package com.asyyy.shixun.user;
//
//import androidx.appcompat.app.AppCompatActivity;
//import android.content.Intent;
//import android.os.Bundle;
//import android.widget.TextView;
//import com.asyyy.shixun.MActivity;
//import com.asyyy.shixun.R;
//
//public class CustomerServiceActivity extends AppCompatActivity {
//
//    private TextView back_home;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_success); // 确保这个布局文件存在
//
//        back_home = findViewById(R.id.back_home); // 确保这个ID在布局文件中存在
//
//        back_home.setOnClickListener(v -> {
//            Intent intent = new Intent(CustomerServiceActivity.this, MActivity.class);
//            startActivity(intent);
//            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
//            finish();
//        });
//    }
//}