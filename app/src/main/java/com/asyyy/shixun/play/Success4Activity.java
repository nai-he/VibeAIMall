package com.asyyy.shixun.play;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.asyyy.shixun.MActivity;
import com.asyyy.shixun.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

public class Success4Activity extends AppCompatActivity {

    private TextView back_home;
    private ViewPager2 viewPager2;
    private TextView tv_desc; // 描述文本
    private LinearLayout layout_dot; // 指示器布局容器
    private List<Integer> pic = new ArrayList<>(); // 轮播图的资源ID列表
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lbt); // 确保这是正确的布局文件名

        back_home = findViewById(R.id.back_home);
        viewPager2 = findViewById(R.id.vp);
        tv_desc = findViewById(R.id.desc);
        layout_dot = findViewById(R.id.layout_dot);

        if (back_home == null || viewPager2 == null || tv_desc == null || layout_dot == null) {
            Log.e("Success4Activity", "One or more views are not found");
        }

        back_home.setOnClickListener(v -> {
            startActivity(new Intent(this, MActivity.class));
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            finish();
        });

        // 初始化图片
        initPictures();
        // 初始化文字下方的点
        initDot();

        // 添加适配器
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(pic);
        viewPager2.setAdapter(viewPagerAdapter);
        // 设置轮播图初始位置在500000000,以保证可以手动前翻，实现伪无限轮播
        viewPager2.setCurrentItem(500000000);
        // 注册轮播图的滚动事件监听器
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                int current = position % descs.length;
                // 当页面被选中的时候,改变描述文本
                tv_desc.setText(descs[current]);
                // 当页面被选中的时候，改变指示点
                changeDots(current);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager2.SCROLL_STATE_IDLE) {
                    mHandler.postDelayed(runnable, 5000); // 延时5秒，自动轮播图片
                } else {
                    mHandler.removeCallbacks(runnable); // 用户手指触摸页面，停止自动轮播图片
                }
            }
        });
    }

    // 初始化颜色
    private void initPictures() {
        pic.add(R.drawable.a100);
        pic.add(R.drawable.a102);
        pic.add(R.drawable.a103);
        pic.add(R.drawable.a104);
        pic.add(R.drawable.a105);
    }

    // 初始化文字下方的点
    private void initDot() {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(10, 10);
        layoutParams.setMargins(4, 4, 4, 4);
        for (int i = 0; i < pic.size(); i++) {
            View view = new View(this);
            view.setBackgroundResource(R.drawable.a301); // 确保这是正确的资源ID
            view.setLayoutParams(layoutParams);
            if (i == 0) { // 初始化后第一个点设置为红色
                view.setSelected(true);
            }
            layout_dot.addView(view);
        }
    }

    /**
     * 自动轮播图方法，实现无限轮播
     */
    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            int currentPosition = viewPager2.getCurrentItem();
            currentPosition++;
            viewPager2.setCurrentItem(currentPosition, true);
            mHandler.postDelayed(this, 5000); // 延时5秒，自动轮播图片
        }
    };

    // 选中对应的原点
    private void changeDots(int position) {
        for (int i = 0; i < layout_dot.getChildCount(); i++) {
            View view = layout_dot.getChildAt(i);
            view.setSelected(false);
        }
        layout_dot.getChildAt(position).setSelected(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mHandler.postDelayed(runnable, 5000); // 延时5秒，自动轮播图片
    }

    @Override
    protected void onPause() {
        super.onPause();
        mHandler.removeCallbacks(runnable); // 用户手指触摸页面，停止自动轮播图片
    }

    // ViewPagerAdapter 作为 Success4Activity 的内部类
    class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.ViewHolder> {
        private List<Integer> pic;

        ViewPagerAdapter(List<Integer> pic) {
            this.pic = pic;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lbt, parent, false);
            if (view == null) {
                Log.e("ViewPagerAdapter", "View is not inflated correctly");
            }
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            int i = position % pic.size();
            holder.iv_content.setImageResource(pic.get(i));
        }

        @Override
        public int getItemCount() {
            return Integer.MAX_VALUE; // 实现伪无限轮播
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            RelativeLayout container;
            ImageView iv_content;

            ViewHolder(@NonNull View itemView) {
                super(itemView);
                container = itemView.findViewById(R.id.container);
                iv_content = itemView.findViewById(R.id.iv_content);
                if (container == null || iv_content == null) {
                    Log.e("ViewPagerAdapter", "View IDs not found");
                }
            }
        }
    }

    private String[] descs = {"为梦想坚持", "我相信我是黑马", "黑马公开课", "Google/IO", "轻松1w+"};
}