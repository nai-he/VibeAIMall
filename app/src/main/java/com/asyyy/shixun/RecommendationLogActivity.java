package com.asyyy.shixun;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.asyyy.shixun.cart.CartDBOpenHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class RecommendationLogActivity extends AppCompatActivity {
    private TextView summaryView;
    private TextView emptyView;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private final SimpleDateFormat dateFormat =
            new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendation_log);

        summaryView = findViewById(R.id.log_summary);
        emptyView = findViewById(R.id.log_empty);
        listView = findViewById(R.id.log_list);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new ArrayList<String>());
        listView.setAdapter(adapter);

        findViewById(R.id.log_back).setOnClickListener(v -> finish());
        findViewById(R.id.log_clear).setOnClickListener(v -> clearLogs());
        loadLogs();
    }

    private void loadLogs() {
        List<String> logs = new ArrayList<>();
        CartDBOpenHelper helper = null;
        Cursor cursor = null;
        try {
            helper = new CartDBOpenHelper(this);
            cursor = helper.getReadableDatabase().query(
                    "recommend_logs",
                    null,
                    null,
                    null,
                    null,
                    null,
                    "create_time DESC"
            );

            while (cursor.moveToNext()) {
                String input = cursor.getString(cursor.getColumnIndexOrThrow("input_text"));
                String intent = cursor.getString(cursor.getColumnIndexOrThrow("intent"));
                int goodsId = cursor.getInt(cursor.getColumnIndexOrThrow("recommend_goods_id"));
                String action = cursor.getString(cursor.getColumnIndexOrThrow("action"));
                long createTime = cursor.getLong(cursor.getColumnIndexOrThrow("create_time"));

                logs.add("需求：" + input + "\n"
                        + "识别：" + intent + "\n"
                        + "推荐商品ID：" + goodsId + "｜动作：" + actionText(action) + "\n"
                        + "时间：" + dateFormat.format(new Date(createTime)));
            }
        } catch (Exception e) {
            Toast.makeText(this, "加载推荐记录失败", Toast.LENGTH_SHORT).show();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (helper != null) {
                helper.close();
            }
        }

        adapter.clear();
        adapter.addAll(logs);
        adapter.notifyDataSetChanged();
        summaryView.setText("共 " + logs.size() + " 条推荐记录｜recommended 表示生成推荐，added_to_cart 表示产生加购动作");
        updateEmpty(logs.isEmpty());
    }

    private void clearLogs() {
        CartDBOpenHelper helper = null;
        try {
            helper = new CartDBOpenHelper(this);
            helper.getWritableDatabase().delete("recommend_logs", null, null);
            Toast.makeText(this, "推荐记录已清空", Toast.LENGTH_SHORT).show();
            loadLogs();
        } finally {
            if (helper != null) {
                helper.close();
            }
        }
    }

    private String actionText(String action) {
        if ("added_to_cart".equals(action)) {
            return "加入购物车";
        }
        if ("chat_added_to_cart".equals(action)) {
            return "对话加购";
        }
        if ("chat_recommend_and_add".equals(action)) {
            return "对话推荐并加购";
        }
        if ("recommended".equals(action)) {
            return "生成推荐";
        }
        return action;
    }

    private void updateEmpty(boolean empty) {
        listView.setVisibility(empty ? View.GONE : View.VISIBLE);
        emptyView.setVisibility(empty ? View.VISIBLE : View.GONE);
    }
}
