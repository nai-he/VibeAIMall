package com.asyyy.shixun.play;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Px;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.asyyy.shixun.R;

import java.util.ArrayList;
import java.util.List;

public class Lb1 extends AppCompatActivity {

    private String[] descs = {"新品发布", "好物享购", "价格实惠", "买到就是赚到", "不会后悔"};
    private ViewPager2 viewPager2;
    private TextView tv_desc;//描述文本
    private LinearLayout layout_dot;//指示器布局容器
    private List<Integer> pic = new ArrayList<>();   //轮播图的颜色
    private Handler mHandler = new Handler();
    private TextView back_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.lbt);
        //初始化控件
        viewPager2 = (ViewPager2) findViewById(R.id.vp);
        tv_desc = (TextView) findViewById(R.id.desc);
        layout_dot = (LinearLayout) findViewById(R.id.layout_dot);
        //初始化图片
        initPictures();
        //初始化文字下方的点
        initDot();

        //添加适配器
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(pic);
        viewPager2.setAdapter(viewPagerAdapter);
        //设置轮播图初始位置在500000000,以保证可以手动前翻，实现伪无限轮播，实际上当滑到0时，就不可以在左滑，所以需要设置很大的数字，来实现伪无限轮播
        viewPager2.setCurrentItem(500000000);
        //注册轮播图的滚动事件监听器
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {

            //当页面滚动时触发的时间
            public void onPageScrolled(int position, float positionOffset, @Px int positionOffsetPixels) {

            }

            //当页面被选中时触发的方法
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                int current = position % 5;//有多少个页面就写多少，这里有5种颜色就写5，除以5取余数
                //当页面被选中的时候,改变描述文本
                tv_desc.setText(descs[current]);
                //当页面被选中的时候，改变指示点
                changeDots(current);
            }

            public void onPageScrollStateChanged(@ViewPager2.ScrollState int state) {
                //当页面空闲状态被改变的时候
                if (state == viewPager2.SCROLL_STATE_IDLE) {
                    mHandler.postDelayed(runnable,5000);//延时5秒，自动轮播图片
                    Log.v("22222",  "页面空闲状态");
                } else {
                    Log.v("22222",  "页面空闲状态被改变");
                    mHandler.removeCallbacks(runnable);//用户手指触摸页面，停止自动轮播图片
                }
            }
        });
    }

    //初始化颜色
    private void initPictures(){
        pic.add(R.drawable.a101);
        pic.add(R.drawable.a102);
        pic.add(R.drawable.a103);
        pic.add(R.drawable.a104);
        pic.add(R.drawable.a301);
    }

    //初始化文字下方的点
    private void initDot() {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(10, 10);
        layoutParams.setMargins(4, 4, 4, 4);
        for (int i = 0; i < pic.size(); i++) {
            View view = new View(this);
            view.setBackgroundResource(R.drawable.wodedialog);
            view.setLayoutParams(layoutParams);
            if(i==0){//初始化后第一个点设置为红色
                view.setSelected(true);
            }
            layout_dot.addView(view);
        }
        Log.v("1212",  "打印layout_dot.getChildCount()= "+layout_dot.getChildCount());
        Log.v("1212",  "打印colors.size()= "+pic.size());
    }

    /**
     * 自动轮播图方法，实现无限轮播
     */
    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            //获得轮播图当前的位置
            int currentPosition = viewPager2.getCurrentItem();
            currentPosition++;
            viewPager2.setCurrentItem(currentPosition,true);
            mHandler.postDelayed(runnable,5000);//延时5秒，自动轮播图片
        }
    };

    //选中对应的原点
    private void changeDots(int position) {
        Log.v("11111",  "打印layout_dot.getChildCount()= "+layout_dot.getChildCount());
        //先把所有的点恢复为白色
        for (int i = 0; i < layout_dot.getChildCount(); i++) {
            View view = layout_dot.getChildAt(i);
            view.setSelected(false);
        }
        //获取当前被选中的条目 设置为选中状态
        layout_dot.getChildAt(position).setSelected(true);
    }

    /* 当应用被唤醒时，让轮播图开始轮播 */
    @Override
    protected void onResume() {
        super.onResume();
        mHandler.postDelayed(runnable,5000);//延时5秒，自动轮播图片
        Log.v("22222",  "当应用被唤醒时，让轮播图开始轮播 ");
    }

    /* 当应用被暂停时，让轮播图停止轮播 */
    @Override
    protected void onPause() {
        super.onPause();
        mHandler.removeCallbacks(runnable);
        Log.v("22222",  "当应用被暂停时，让轮播图停止轮播 ");
    }

}