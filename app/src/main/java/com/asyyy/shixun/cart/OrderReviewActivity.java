package com.asyyy.shixun.cart;

import android.content.ContentValues;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.asyyy.shixun.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class OrderReviewActivity extends AppCompatActivity {
    public static final String EXTRA_ORDER_ID = "order_id";
    public static final String EXTRA_GOODS_ID = "goods_id";
    public static final String EXTRA_GOODS_NAME = "goods_name";
    public static final String EXTRA_IMAGE_RES_ID = "image_res_id";

    private int orderId;
    private int goodsId;
    private String goodsName;
    private int imageResId;
    private RatingBar ratingBar;
    private EditText contentInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_review);

        orderId = getIntent().getIntExtra(EXTRA_ORDER_ID, 0);
        goodsId = getIntent().getIntExtra(EXTRA_GOODS_ID, 0);
        goodsName = getIntent().getStringExtra(EXTRA_GOODS_NAME);
        imageResId = getIntent().getIntExtra(EXTRA_IMAGE_RES_ID, R.drawable.c101);

        findViewById(R.id.review_back).setOnClickListener(v -> finish());
        ImageView imageView = findViewById(R.id.review_goods_image);
        TextView nameView = findViewById(R.id.review_goods_name);
        ratingBar = findViewById(R.id.review_rating);
        contentInput = findViewById(R.id.review_content);

        imageView.setImageResource(imageResId);
        nameView.setText(goodsName == null ? "商品评价" : goodsName);
        findViewById(R.id.review_submit).setOnClickListener(v -> submitReview());
    }

    private void submitReview() {
        String content = contentInput.getText().toString().trim();
        if (content.isEmpty()) {
            Toast.makeText(this, "请输入评价内容", Toast.LENGTH_SHORT).show();
            return;
        }

        CartDBOpenHelper helper = null;
        try {
            helper = new CartDBOpenHelper(this);
            ContentValues values = new ContentValues();
            values.put("goods_id", goodsId);
            values.put("user_name", "订单用户");
            values.put("content", content);
            values.put("rating", ratingBar.getRating());
            values.put("create_time", currentTime());
            long result = helper.getWritableDatabase().insert("comments", null, values);
            if (result == -1) {
                Toast.makeText(this, "评价提交失败，请重试", Toast.LENGTH_SHORT).show();
                return;
            }

            OrderService.updateStatus(this, orderId, OrderService.STATUS_COMPLETED);
            Toast.makeText(this, "评价成功，订单已完成", Toast.LENGTH_SHORT).show();
            finish();
        } finally {
            if (helper != null) {
                helper.close();
            }
        }
    }

    private String currentTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(new Date());
    }
}
