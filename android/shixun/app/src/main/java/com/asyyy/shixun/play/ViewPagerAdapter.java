package com.asyyy.shixun.play;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.asyyy.shixun.R;

import java.util.List;

public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.ViewHolder> {

    private List<Integer> pic;

    ViewPagerAdapter(List<Integer> pic){
        this.pic = pic;
    }

    //加载布局视图
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_item,parent,false));
    }

    //绑定数据
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int i = position % 5;//有多少个页面就写多少，这里有5种颜色就写5，除以5取余数
       // holder.titleTv.setText("第" + i + "个页面");
        //holder.container.setBackgroundColor(pic.get(i));
        holder.iv_content.setImageResource(pic.get(i));
    }

    @Override
    public int getItemCount() {
        //实现伪无限轮播
        return Integer.MAX_VALUE;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        RelativeLayout container;
        ImageView iv_content;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            container = itemView.findViewById(R.id.container);
            iv_content = itemView.findViewById(R.id.iv_content);
        }
    }
}

