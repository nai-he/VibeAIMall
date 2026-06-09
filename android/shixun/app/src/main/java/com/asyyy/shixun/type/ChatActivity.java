package com.asyyy.shixun.type;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.app.AlertDialog;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.asyyy.shixun.R;
import com.asyyy.shixun.cart.CartDBOpenHelper;
import com.asyyy.shixun.cart.CartService;
import com.asyyy.shixun.home.Comment;
import com.asyyy.shixun.home.Goods;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ChatActivity extends AppCompatActivity {

    private TextView back_home;
    private ImageView goodsImage;
    private TextView goodsPrice;
    private TextView bottomPrice;
    private TextView detailGoodsName;
    private TextView detailGoodsType;
    private TextView detailGoodsShop;
    private TextView detailGoodsSales;
    private Button addToCartButton;
    private TextView viewMore;
    private TextView viewComments;
    private CartDBOpenHelper dbOpenHelper;
    private Goods currentGoods;

    private RecyclerView commentRecyclerView;
    private CommentAdapter commentAdapter;
    private List<Comment> commentList;

    private static final String TAG = "ChatActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.type_goods_detail);

        Log.d(TAG, "ChatActivity 创建");
        initView();
        initData();
        initComments();
        setListeners();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 每次回到页面时重新加载评论
        loadCommentsFromDB();
    }

    private void initView() {
        back_home = findViewById(R.id.back_home);

        // 初始化商品信息视图
        goodsImage = findViewById(R.id.type_goods);
        goodsPrice = findViewById(R.id.type_goods_tv);
        bottomPrice = findViewById(R.id.placeMoney);
        detailGoodsName = findViewById(R.id.detail_goods_name);
        detailGoodsType = findViewById(R.id.detail_goods_type);
        detailGoodsShop = findViewById(R.id.detail_goods_shop);
        detailGoodsSales = findViewById(R.id.detail_goods_sales);
        addToCartButton = findViewById(R.id.cart_buy);
        viewMore = findViewById(R.id.view_more);
        viewComments = findViewById(R.id.view_comments);

        // 初始化评论列表
        commentRecyclerView = findViewById(R.id.placeRecyclerView);
        commentList = new ArrayList<>();
        commentAdapter = new CommentAdapter(commentList);
        commentRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        commentRecyclerView.setAdapter(commentAdapter);

        // 设置评论点击监听器
        commentAdapter.setOnCommentClickListener(new CommentAdapter.OnCommentClickListener() {
            @Override
            public void onCommentClick(Comment comment) {
                showCommentDetail(comment);
            }
        });

        Log.d(TAG, "视图初始化完成");
    }

    private void initData() {
        // 获取传递的商品信息
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("goods_id")) {
            int goodsId = intent.getIntExtra("goods_id", 9);
            String goodsNameStr = intent.getStringExtra("goods_name");
            String goodsTypeStr = intent.getStringExtra("goods_type");
            double goodsPriceValue = intent.getDoubleExtra("goods_price", 79.00);
            int goodsSalesValue = intent.getIntExtra("goods_sales", 2800);
            String goodsShopStr = intent.getStringExtra("goods_shop");
            int goodsPhoto = intent.getIntExtra("goods_photo", R.drawable.c101);

            // 创建当前商品对象
            currentGoods = new Goods(goodsId, goodsPhoto, goodsNameStr, goodsTypeStr,
                    goodsPriceValue, goodsSalesValue, goodsShopStr);

            bindGoodsInfo();

            Log.d(TAG, "从Intent加载商品数据，ID: " + goodsId);
        } else {
            // 如果没有传递数据，创建默认的小米移动电源3商品
            currentGoods = new Goods(9, R.drawable.c101, "小米移动电源3 10000mAh",
                    "18W双向快充 质感合金壳体", 79.00, 2800, "小米官方旗舰店");

            bindGoodsInfo();

            Log.d(TAG, "使用默认商品数据");
        }
    }

    private void bindGoodsInfo() {
        if (currentGoods == null) {
            return;
        }

        String priceText = "¥" + currentGoods.getG_price();
        if (goodsImage != null) goodsImage.setImageResource(currentGoods.getG_photo());
        if (goodsPrice != null) goodsPrice.setText(priceText);
        if (bottomPrice != null) bottomPrice.setText(priceText);
        if (detailGoodsName != null) detailGoodsName.setText(currentGoods.getG_name());
        if (detailGoodsType != null) detailGoodsType.setText(currentGoods.getG_type());
        if (detailGoodsShop != null) detailGoodsShop.setText(currentGoods.getG_shop());
        if (detailGoodsSales != null) {
            detailGoodsSales.setText(currentGoods.getG_sales() + "条评价  99%好评");
        }
    }

    private void initComments() {
        // 从数据库加载评论
        loadCommentsFromDB();

        // 如果没有评论，添加一些测试评论
        if (commentList.isEmpty()) {
            addSampleComments();
        }
    }

    private void setListeners() {
        // 返回首页
        if (back_home != null) {
            back_home.setOnClickListener(v -> finish());
        }

        // 加入购物车
        if (addToCartButton != null) {
            addToCartButton.setOnClickListener(v -> {
                if (currentGoods != null) {
                    // 将商品添加到购物车数据库
                    addGoodsToCart(currentGoods);
                } else {
                    Toast.makeText(this, "商品信息错误", Toast.LENGTH_SHORT).show();
                }
            });
        }

        // 发表评论
        if (viewMore != null) {
            viewMore.setOnClickListener(v -> showCommentDialog());
        }

        // 查看评论
        if (viewComments != null) {
            viewComments.setOnClickListener(v -> openCommentList());
        }

        // 长按添加测试评论
        findViewById(android.R.id.content).setOnLongClickListener(v -> {
            addSampleComments();
            return true;
        });
    }

    private void showCommentDialog() {
        try {
            // 使用简单的布局创建对话框
            View dialogView = LayoutInflater.from(this).inflate(R.layout.comment_dialog, null);
            RatingBar ratingBar = dialogView.findViewById(R.id.dialog_rating);
            EditText commentEditText = dialogView.findViewById(R.id.dialog_comment);

            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setView(dialogView)
                    .setPositiveButton("发表", (dialogInterface, which) -> {
                        float rating = ratingBar.getRating();
                        String content = commentEditText.getText().toString().trim();

                        if (content.isEmpty()) {
                            Toast.makeText(this, "请输入评论内容", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        // 保存评论到数据库
                        saveCommentToDB(rating, content);
                    })
                    .setNegativeButton("取消", null)
                    .create();

            dialog.show();
        } catch (Exception e) {
            Log.e(TAG, "显示评论对话框失败: " + e.getMessage());
            Toast.makeText(this, "创建评论对话框失败", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 显示评论详情对话框
     */
    private void showCommentDetail(Comment comment) {
        try {
            View dialogView = LayoutInflater.from(this).inflate(R.layout.comment_detail_dialog, null);

            TextView userName = dialogView.findViewById(R.id.detail_user_name);
            TextView content = dialogView.findViewById(R.id.detail_content);
            RatingBar ratingBar = dialogView.findViewById(R.id.detail_rating);
            TextView createTime = dialogView.findViewById(R.id.detail_time);

            userName.setText("用户：" + comment.getUserName());
            content.setText(comment.getContent());
            ratingBar.setRating(comment.getRating());
            createTime.setText("评论时间：" + comment.getCreateTime());

            new AlertDialog.Builder(this)
                    .setView(dialogView)
                    .setPositiveButton("关闭", null)
                    .show();
        } catch (Exception e) {
            Log.e(TAG, "显示评论详情失败: " + e.getMessage());
        }
    }

    /**
     * 显示评论统计信息
     */
    private void showCommentStats() {
        if (commentList == null || commentList.isEmpty()) {
            Toast.makeText(this, "暂无评论，快来发表第一条评论吧！", Toast.LENGTH_LONG).show();
        } else {
            // 计算平均评分
            float totalRating = 0;
            for (Comment comment : commentList) {
                totalRating += comment.getRating();
            }
            float averageRating = totalRating / commentList.size();

            // 更新评论标题显示统计信息
            TextView commentTitle = findViewById(R.id.comment_title);
            if (commentTitle != null) {
                commentTitle.setText(String.format("最新评论(%d条) 平均评分: %.1f分",
                        commentList.size(), averageRating));
            }

            Log.d(TAG, "显示评论统计: " + commentList.size() + "条评论，平均分: " + averageRating);
        }
    }

    /**
     * 添加一些示例评论（用于测试）
     */
    private void addSampleComments() {
        // 只有在没有评论时才添加示例评论
        if (commentList.isEmpty()) {
            try {
                dbOpenHelper = new CartDBOpenHelper(this);

                // 添加几条示例评论
                String[] sampleComments = {
                        "这个充电宝很好用，充电速度快，容量也足够。",
                        "质量不错，外观漂亮，携带方便。",
                        "性价比很高，推荐购买！",
                        "充电时发热有点明显，但还能接受。",
                        "物流很快，第二天就收到了，很满意。"
                };

                String[] userNames = {"用户A", "用户B", "用户C", "用户D", "用户E"};
                float[] ratings = {5.0f, 4.5f, 5.0f, 4.0f, 5.0f};

                for (int i = 0; i < sampleComments.length; i++) {
                    ContentValues values = new ContentValues();
                    values.put("goods_id", currentGoods.getG_id());
                    values.put("user_name", userNames[i]);
                    values.put("content", sampleComments[i]);
                    values.put("rating", ratings[i]);
                    values.put("create_time", getSampleTime(i));

                    long result = dbOpenHelper.getWritableDatabase().insert("comments", null, values);
                    Log.d(TAG, "插入测试评论结果: " + result);
                }

                dbOpenHelper.close();

                // 重新加载评论
                loadCommentsFromDB();

                Toast.makeText(this, "已添加5条测试评论", Toast.LENGTH_SHORT).show();

            } catch (Exception e) {
                Log.e(TAG, "添加示例评论失败: " + e.getMessage(), e);
                Toast.makeText(this, "添加测试评论失败", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "已有评论，无需添加测试数据", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 生成示例评论的时间（用于测试）
     */
    private String getSampleTime(int offset) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
        long time = System.currentTimeMillis() - (offset * 24 * 60 * 60 * 1000L); // 每天偏移
        return sdf.format(new Date(time));
    }

    /**
     * 打开评论列表页面
     */
    private void openCommentList() {
        try {
            Intent intent = new Intent(this, CommentListActivity.class);
            // 传递商品信息
            intent.putExtra("goods_id", currentGoods.getG_id());
            intent.putExtra("goods_name", currentGoods.getG_name());
            intent.putExtra("goods_type", currentGoods.getG_type());
            intent.putExtra("goods_price", currentGoods.getG_price());
            intent.putExtra("goods_sales", currentGoods.getG_sales());
            intent.putExtra("goods_shop", currentGoods.getG_shop());
            intent.putExtra("goods_photo", currentGoods.getG_photo());

            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, "打开评论页面失败", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "打开评论页面失败: " + e.getMessage());
        }
    }

    private void saveCommentToDB(float rating, String content) {
        try {
            dbOpenHelper = new CartDBOpenHelper(this);

            ContentValues values = new ContentValues();
            values.put("goods_id", currentGoods.getG_id());
            values.put("user_name", "匿名用户");
            values.put("content", content);
            values.put("rating", rating);
            values.put("create_time", getCurrentTime());

            long result = dbOpenHelper.getWritableDatabase().insert("comments", null, values);
            dbOpenHelper.close();

            if (result != -1) {
                Toast.makeText(this, "评论发表成功", Toast.LENGTH_SHORT).show();
                // 重新加载评论
                loadCommentsFromDB();
            } else {
                Toast.makeText(this, "评论发表失败", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "发表评论时出错", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private void loadCommentsFromDB() {
        try {
            Log.d(TAG, "开始加载评论，商品ID: " + currentGoods.getG_id());

            commentList.clear();
            dbOpenHelper = new CartDBOpenHelper(this);

            Cursor cursor = dbOpenHelper.getReadableDatabase().query(
                    "comments",
                    new String[]{"comment_id", "goods_id", "user_name", "content", "rating", "create_time"},
                    "goods_id=?",
                    new String[]{String.valueOf(currentGoods.getG_id())},
                    null,
                    null,
                    "comment_id DESC LIMIT 3"  // 只加载最新3条评论
            );

            Log.d(TAG, "查询到评论数量: " + cursor.getCount());

            int commentCount = 0;
            while (cursor.moveToNext()) {
                int commentId = cursor.getInt(cursor.getColumnIndex("comment_id"));
                int goodsId = cursor.getInt(cursor.getColumnIndex("goods_id"));
                String userName = cursor.getString(cursor.getColumnIndex("user_name"));
                String content = cursor.getString(cursor.getColumnIndex("content"));
                float rating = cursor.getFloat(cursor.getColumnIndex("rating"));
                String createTime = cursor.getString(cursor.getColumnIndex("create_time"));

                Log.d(TAG, "加载评论: " + userName + " - " + content);

                Comment comment = new Comment(commentId, goodsId, userName, content, rating, createTime);
                commentList.add(comment);
                commentCount++;
            }

            cursor.close();
            dbOpenHelper.close();

            Log.d(TAG, "成功加载 " + commentCount + " 条评论");

            // 更新适配器
            commentAdapter.updateData(commentList);

            // 显示评论统计信息
            showCommentStats();

        } catch (Exception e) {
            Log.e(TAG, "加载评论失败: " + e.getMessage(), e);
            Toast.makeText(this, "加载评论失败", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
        return sdf.format(new Date());
    }

    // 修复加入购物车方法
    private void addGoodsToCart(Goods goods) {
        CartService.CartResult result = CartService.addToCart(this, goods);
        Toast.makeText(this, result.getMessage(), Toast.LENGTH_SHORT).show();
    }
    public void openCommentPage() {
        try {
            Intent intent = new Intent(this, CommentListActivity.class);
            startActivity(intent);
        } catch (Exception e) {
            Log.e("ChatActivity", "打开评论页面失败: " + e.getMessage());
            Toast.makeText(this, "打开评论页面失败", Toast.LENGTH_SHORT).show();
        }
    }
}
