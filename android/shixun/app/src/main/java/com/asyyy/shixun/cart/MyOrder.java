package com.asyyy.shixun.cart;

import java.util.Date;

public class MyOrder {
    private int orderId;
    private int goodsId;
    private String orderNumber;
    private String goodsName;
    private double totalPrice;
    private int quantity;
    private String status;
    private Date createTime;
    private int imageResId;


    public MyOrder(int orderId, int goodsId, String orderNumber, String goodsName, double totalPrice,
                   int quantity, String status, Date createTime, int imageResId) {
        this.orderId = orderId;
        this.goodsId = goodsId;
        this.orderNumber = orderNumber;
        this.goodsName = goodsName;
        this.totalPrice = totalPrice;
        this.quantity = quantity;
        this.status = status;
        this.createTime = createTime;
        this.imageResId = imageResId;
    }

    // Getter 方法
    public int getOrderId() { return orderId; }
    public int getGoodsId() { return goodsId; }
    public String getOrderNumber() { return orderNumber; }
    public String getGoodsName() { return goodsName; }
    public double getTotalPrice() { return totalPrice; }
    public int getQuantity() { return quantity; }
    public String getStatus() { return status; }
    public Date getCreateTime() { return createTime; }
    public int getImageResId() { return imageResId; }

    public void setStatus(String status) {
        this.status = status;
    }
}
