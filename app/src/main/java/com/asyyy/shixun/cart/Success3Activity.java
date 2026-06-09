package com.asyyy.shixun.cart;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.asyyy.shixun.MActivity;
import com.asyyy.shixun.R;

public class Success3Activity extends AppCompatActivity {

    private TextView back_home;
    private EditText userReview;
    private Button submitReview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success3);

        back_home = findViewById(R.id.back_home);
        userReview = findViewById(R.id.user_review);
        submitReview = findViewById(R.id.submit_review);

        back_home.setOnClickListener(v -> {
            startActivity(new Intent(this, MActivity.class));
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            finish();
        });

        submitReview.setOnClickListener(v -> {
            String review = userReview.getText().toString().trim();
            if (!review.isEmpty()) {
                // 处理用户评价，例如保存到数据库或发送到服务器
                saveUserReview(review);
                Toast.makeText(this, "Review submitted successfully!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Please enter a review", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveUserReview(String review) {
        // 这里你可以实现保存用户评价的逻辑，例如保存到数据库或发送到服务器
        // 示例代码：假设你有一个方法来保存评价
        // DatabaseHelper.saveReview(review);
    }
}
