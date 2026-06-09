package com.asyyy.shixun.cart;
//订单数据模型类，包含订单 ID、编号、商品名等属性及对应的 getter 和 setter 方法。
import java.util.Date;

public class Order {
    private int orderId;
    private String orderNumber;
    private String goodsName;
    private double totalPrice;
    private int quantity;
    private String status; // 订单状态：待付款、已发货、已完成等
    private Date createTime;
    private String imageUrl;

    public Order(int orderId, String orderNumber, String goodsName, double totalPrice,
                 int quantity, String status, Date createTime, String imageUrl) {
        this.orderId = orderId;
        this.orderNumber = orderNumber;
        this.goodsName = goodsName;
        this.totalPrice = totalPrice;
        this.quantity = quantity;
        this.status = status;
        this.createTime = createTime;
        this.imageUrl = imageUrl;
    }

    // Getter 和 Setter 方法
    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }

    public String getOrderNumber() { return orderNumber; }
    public void setOrderNumber(String orderNumber) { this.orderNumber = orderNumber; }

    public String getGoodsName() { return goodsName; }
    public void setGoodsName(String goodsName) { this.goodsName = goodsName; }

    public double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Date getCreateTime() { return createTime; }
    public void setCreateTime(Date createTime) { this.createTime = createTime; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}