package com.asyyy.shixun.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment {

    protected Context mContext; // 使用protected以便在子类中直接访问

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext(); // 初始化mContext为当前的上下文环境
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 确保子类实现了initView方法
        return initView();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // 确保子类实现了initData方法
        initData();
    }

    // 由子类实现特定的效果
    public abstract View initView();

    // 初始化数据，子类根据需要重写此方法
    public void initData() {
        // 在这里可以进行一些默认的数据初始化操作
    }
}