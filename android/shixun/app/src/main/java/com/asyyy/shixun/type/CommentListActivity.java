package com.asyyy.shixun.type;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.asyyy.shixun.R;
import com.asyyy.shixun.cart.CartDBOpenHelper;
import com.asyyy.shixun.home.Comment;
import com.asyyy.shixun.home.Goods;
import java.util.ArrayList;
import java.util.List;

public class CommentListActivity extends AppCompatActivity {

    private static final String TAG = "CommentListActivity";

    private ImageView ivBack;
    private RecyclerView rvComments;
    private CommentAdapter commentAdapter;
    private List<Comment> commentList;
    private CartDBOpenHelper dbOpenHelper;
    private Goods currentGoods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_list);

        Log.d(TAG, "CommentListActivity 创建");

        initView();
        initData();
        loadCommentsFromDatabase();
        setListeners();
    }

    private void initView() {
        // 修复：使用正确的ID查找视图
        ivBack = findViewById(R.id.iv_back);
        rvComments = findViewById(R.id.rv_comments);

        // 设置RecyclerView
        commentList = new ArrayList<>();
        commentAdapter = new CommentAdapter(commentList);
        rvComments.setLayoutManager(new LinearLayoutManager(this));
        rvComments.setAdapter(commentAdapter);

        Log.d(TAG, "视图初始化完成");
    }

    private void initData() {
        // 获取传递的商品信息
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("goods_id")) {
            int goodsId = intent.getIntExtra("goods_id", 0);
            String goodsName = intent.getStringExtra("goods_name");
            String goodsType = intent.getStringExtra("goods_type");
            double goodsPrice = intent.getDoubleExtra("goods_price", 0.0);
            int goodsSales = intent.getIntExtra("goods_sales", 0);
            String goodsShop = intent.getStringExtra("goods_shop");
            int goodsPhoto = intent.getIntExtra("goods_photo", 0);

            currentGoods = new Goods(goodsId, goodsPhoto, goodsName, goodsType,
                    goodsPrice, goodsSales, goodsShop);

            Log.d(TAG, "加载商品信息: " + goodsName + ", ID: " + goodsId);
        } else {
            Log.e(TAG, "没有接收到商品信息");
        }
    }

    private void loadCommentsFromDatabase() {
        Log.d(TAG, "开始从数据库加载评论");

        commentList.clear();
        dbOpenHelper = new CartDBOpenHelper(this);

        try {
            // 如果有商品信息，只加载该商品的评论；否则加载所有评论
            String selection = null;
            String[] selectionArgs = null;

            if (currentGoods != null) {
                selection = "goods_id=?";
                selectionArgs = new String[]{String.valueOf(currentGoods.getG_id())};
                Log.d(TAG, "加载特定商品的评论，商品ID: " + currentGoods.getG_id());
            } else {
                Log.d(TAG, "加载所有评论");
            }

            Cursor cursor = dbOpenHelper.getReadableDatabase().query(
                    "comments",
                    new String[]{"comment_id", "goods_id", "user_name", "content", "rating", "create_time"},
                    selection,
                    selectionArgs,
                    null,
                    null,
                    "comment_id DESC"  // 按评论ID降序排列，最新的在前
            );

            Log.d(TAG, "查询到评论数量: " + cursor.getCount());

            int commentCount = 0;
            while (cursor.moveToNext()) {
                try {
                    int commentId = cursor.getInt(cursor.getColumnIndexOrThrow("comment_id"));
                    int goodsId = cursor.getInt(cursor.getColumnIndexOrThrow("goods_id"));
                    String userName = cursor.getString(cursor.getColumnIndexOrThrow("user_name"));
                    String content = cursor.getString(cursor.getColumnIndexOrThrow("content"));
                    float rating = cursor.getFloat(cursor.getColumnIndexOrThrow("rating"));
                    String createTime = cursor.getString(cursor.getColumnIndexOrThrow("create_time"));

                    Comment comment = new Comment(commentId, goodsId, userName, content, rating, createTime);
                    commentList.add(comment);
                    commentCount++;

                    Log.d(TAG, "加载评论: " + userName + " - " + content.substring(0, Math.min(20, content.length())) + "...");

                } catch (Exception e) {
                    Log.e(TAG, "解析评论数据失败: " + e.getMessage(), e);
                }
            }

            cursor.close();

            Log.d(TAG, "成功加载 " + commentCount + " 条评论");

            // 更新适配器
            commentAdapter.updateData(commentList);

            // 如果没有评论，显示提示
            if (commentList.isEmpty()) {
                Log.d(TAG, "没有找到评论数据");
                // 可以在这里添加一个提示视图
            }

        } catch (Exception e) {
            Log.e(TAG, "加载评论失败: " + e.getMessage(), e);
        } finally {
            if (dbOpenHelper != null) {
                dbOpenHelper.close();
            }
        }
    }

    private void setListeners() {
        // 返回按钮点击事件
        if (ivBack != null) {
            ivBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "返回按钮被点击");
                    finish();
                }
            });
        }

        // 评论项点击事件
        commentAdapter.setOnCommentClickListener(new CommentAdapter.OnCommentClickListener() {
            @Override
            public void onCommentClick(Comment comment) {
                Log.d(TAG, "评论被点击: " + comment.getUserName());
                // 这里可以显示评论详情，如果需要的话
                // showCommentDetail(comment);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "CommentListActivity 销毁");
    }
}