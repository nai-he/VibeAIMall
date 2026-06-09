package com.asyyy.shixun.cart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.asyyy.shixun.MActivity;
import com.asyyy.shixun.R;

import java.util.ArrayList;
import java.util.List;

public class MyOrderActivity extends AppCompatActivity {

    public static final String EXTRA_STATUS_FILTER = "status_filter";

    private TextView backHome;
    private TextView orderTitle;
    private TextView orderFilterTip;
    private TextView emptyView;
    private RecyclerView orderRecyclerView;
    private MyOrderAdapter orderAdapter;
    private List<MyOrder> orderList = new ArrayList<>();
    private String statusFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);

        statusFilter = getIntent().getStringExtra(EXTRA_STATUS_FILTER);
        initView();
        setListeners();
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshOrders();
    }

    private void initView() {
        backHome = findViewById(R.id.back_home);
        orderTitle = findViewById(R.id.order_title);
        orderFilterTip = findViewById(R.id.order_filter_tip);
        emptyView = findViewById(R.id.order_empty_view);
        orderRecyclerView = findViewById(R.id.order_recycler_view);
        orderRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        if (hasFilter()) {
            orderTitle.setText(statusFilter);
            orderFilterTip.setText("当前筛选：" + statusFilter);
        } else {
            orderTitle.setText("我的订单");
            orderFilterTip.setText("全部订单｜支持状态流转演示");
        }
    }

    private void refreshOrders() {
        orderList = OrderService.loadOrders(this, statusFilter);
        orderAdapter = new MyOrderAdapter(this, orderList, this::handleOrderAction);
        orderRecyclerView.setAdapter(orderAdapter);
        updateEmptyView();
    }

    private void handleOrderAction(MyOrder order) {
        if (OrderService.STATUS_PENDING_PAY.equals(order.getStatus())) {
            OrderService.updateStatus(this, order.getOrderId(), OrderService.STATUS_PENDING_RECEIVE);
            Toast.makeText(this, "支付成功，订单进入待收货", Toast.LENGTH_SHORT).show();
            refreshOrders();
            return;
        }

        if (OrderService.STATUS_PENDING_RECEIVE.equals(order.getStatus())) {
            OrderService.updateStatus(this, order.getOrderId(), OrderService.STATUS_PENDING_COMMENT);
            Toast.makeText(this, "已确认收货，可以去评价", Toast.LENGTH_SHORT).show();
            refreshOrders();
            return;
        }

        if (OrderService.STATUS_PENDING_COMMENT.equals(order.getStatus())) {
            Intent intent = new Intent(this, OrderReviewActivity.class);
            intent.putExtra(OrderReviewActivity.EXTRA_ORDER_ID, order.getOrderId());
            intent.putExtra(OrderReviewActivity.EXTRA_GOODS_ID, order.getGoodsId());
            intent.putExtra(OrderReviewActivity.EXTRA_GOODS_NAME, order.getGoodsName());
            intent.putExtra(OrderReviewActivity.EXTRA_IMAGE_RES_ID, order.getImageResId());
            startActivity(intent);
            return;
        }

        if (OrderService.STATUS_AFTER_SALE.equals(order.getStatus())) {
            Toast.makeText(this, "售后进度：客服已受理，等待处理", Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(this, "已加入再次购买清单", Toast.LENGTH_SHORT).show();
    }

    private void updateEmptyView() {
        if (orderList.isEmpty()) {
            orderRecyclerView.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
            emptyView.setText(hasFilter() ? "暂无" + statusFilter + "订单" : "暂无订单");
        } else {
            orderRecyclerView.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
        }
    }

    private boolean hasFilter() {
        return statusFilter != null && statusFilter.trim().length() > 0;
    }

    private void setListeners() {
        backHome.setOnClickListener(v -> {
            startActivity(new Intent(this, MActivity.class));
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            finish();
        });
    }
}
