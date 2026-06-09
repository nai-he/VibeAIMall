package com.asyyy.shixun.cart;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.asyyy.shixun.R;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.OrderViewHolder> {

    public interface OnOrderActionListener {
        void onOrderAction(MyOrder order);
    }

    private Context context;
    private List<MyOrder> orderList;
    private SimpleDateFormat dateFormat;
    private OnOrderActionListener actionListener;

    public MyOrderAdapter(Context context, List<MyOrder> orderList, OnOrderActionListener actionListener) {
        this.context = context;
        this.orderList = orderList;
        this.actionListener = actionListener;
        this.dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_my_order, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        MyOrder order = orderList.get(position);

        holder.orderNumber.setText("订单号: " + order.getOrderNumber());
        holder.goodsName.setText(order.getGoodsName());
        holder.totalPrice.setText("¥" + order.getTotalPrice());
        holder.quantity.setText("数量: " + order.getQuantity());
        holder.status.setText(order.getStatus());
        holder.createTime.setText(dateFormat.format(order.getCreateTime()));
        holder.goodsImage.setImageResource(order.getImageResId());
        holder.status.setTextColor(statusColor(order.getStatus()));
        holder.actionButton.setText(actionText(order.getStatus()));
        holder.actionButton.setOnClickListener(v -> {
            if (actionListener != null) {
                actionListener.onOrderAction(order);
            }
        });
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    private int statusColor(String status) {
        if ("待付款".equals(status)) {
            return context.getResources().getColor(android.R.color.holo_red_dark);
        }
        if ("待收货".equals(status)) {
            return context.getResources().getColor(android.R.color.holo_blue_dark);
        }
        if ("待评价".equals(status)) {
            return context.getResources().getColor(android.R.color.holo_orange_dark);
        }
        if ("退款/售后".equals(status)) {
            return Color.parseColor("#7C3AED");
        }
        if ("已完成".equals(status)) {
            return context.getResources().getColor(android.R.color.holo_green_dark);
        }
        return context.getResources().getColor(android.R.color.black);
    }

    private String actionText(String status) {
        if ("待付款".equals(status)) {
            return "去付款";
        }
        if ("待收货".equals(status)) {
            return "确认收货";
        }
        if ("待评价".equals(status)) {
            return "去评价";
        }
        if ("退款/售后".equals(status)) {
            return "查看进度";
        }
        return "再次购买";
    }

    static class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView orderNumber, goodsName, totalPrice, quantity, status, createTime;
        ImageView goodsImage;
        Button actionButton;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            orderNumber = itemView.findViewById(R.id.tv_order_number);
            goodsName = itemView.findViewById(R.id.tv_goods_name);
            totalPrice = itemView.findViewById(R.id.tv_total_price);
            quantity = itemView.findViewById(R.id.tv_quantity);
            status = itemView.findViewById(R.id.tv_order_status);
            createTime = itemView.findViewById(R.id.tv_create_time);
            goodsImage = itemView.findViewById(R.id.iv_goods_image);
            actionButton = itemView.findViewById(R.id.btn_order_action);
        }
    }
}
