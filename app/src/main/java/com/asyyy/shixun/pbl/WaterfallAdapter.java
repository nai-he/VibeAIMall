package com.asyyy.shixun.pbl;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.asyyy.shixun.R;

import java.util.List;
import java.util.Random;

public class WaterfallAdapter extends RecyclerView.Adapter<WaterfallAdapter.ViewHolder> {

    private Context mContext;
    private List<ImageInfo> mImageItemList;

    public WaterfallAdapter(Context context, List<ImageInfo> imageItemList) {
        mContext = context;
        mImageItemList = imageItemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 设置布局管理器为瀑布流布局
        View view = LayoutInflater.from(mContext).inflate(R.layout.p2, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ImageInfo imageInfo = mImageItemList.get(position);

        // 设置图片资源
        holder.imageView.setImageResource(imageInfo.getImageResId());

        // 确保图片按比例缩放以完整显示（根据需要调整）
        holder.imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

//        // 设置文本描述
//        holder.textView.setText(imageInfo.getDescription());
//
//        // 设置文本大小等样式，确保完整显示（可根据实际情况调整）
//        holder.textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);


        // 为每个Item设置随机高度，实现参差不齐效果
        int randomHeight = getRandomHeight();
        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
        layoutParams.height = randomHeight;
        holder.itemView.setLayoutParams(layoutParams);
    }

    @Override
    public int getItemCount() {
        return mImageItemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
        }
    }

    private int getRandomHeight() {
        // 这里生成一个随机的高度值，范围可根据实际需求调整
        Random random = new Random();
        return random.nextInt(400) + 100; // 生成100到400像素之间的随机高度，示例范围
    }
}