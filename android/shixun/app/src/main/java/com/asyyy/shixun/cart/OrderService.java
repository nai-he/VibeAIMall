package com.asyyy.shixun.cart;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.asyyy.shixun.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderService {
    public static final String STATUS_PENDING_PAY = "待付款";
    public static final String STATUS_PENDING_RECEIVE = "待收货";
    public static final String STATUS_PENDING_COMMENT = "待评价";
    public static final String STATUS_AFTER_SALE = "退款/售后";
    public static final String STATUS_COMPLETED = "已完成";

    private static final String TAG = "OrderService";

    public static List<MyOrder> loadOrders(Context context, String statusFilter) {
        ensureDemoOrdersIfEmpty(context);
        List<MyOrder> orders = new ArrayList<>();
        CartDBOpenHelper helper = null;
        Cursor cursor = null;
        try {
            helper = new CartDBOpenHelper(context);
            SQLiteDatabase db = helper.getReadableDatabase();
            if (STATUS_PENDING_RECEIVE.equals(statusFilter)) {
                cursor = db.query(
                        "orders",
                        null,
                        "status IN (?,?,?)",
                        new String[]{STATUS_PENDING_RECEIVE, "待发货", "已发货"},
                        null,
                        null,
                        "create_time DESC"
                );
            } else if (hasFilter(statusFilter)) {
                cursor = db.query(
                        "orders",
                        null,
                        "status=?",
                        new String[]{statusFilter},
                        null,
                        null,
                        "create_time DESC"
                );
            } else {
                cursor = db.query("orders", null, null, null, null, null, "create_time DESC");
            }

            while (cursor.moveToNext()) {
                int orderId = cursor.getInt(cursor.getColumnIndexOrThrow("order_id"));
                int goodsId = getInt(cursor, "goods_id", orderId);
                String orderNumber = cursor.getString(cursor.getColumnIndexOrThrow("order_number"));
                String goodsName = cursor.getString(cursor.getColumnIndexOrThrow("goods_name"));
                double totalPrice = cursor.getDouble(cursor.getColumnIndexOrThrow("total_price"));
                int quantity = cursor.getInt(cursor.getColumnIndexOrThrow("quantity"));
                String status = normalizeStatus(cursor.getString(cursor.getColumnIndexOrThrow("status")));
                long createTime = cursor.getLong(cursor.getColumnIndexOrThrow("create_time"));
                int imageResId = cursor.getInt(cursor.getColumnIndexOrThrow("image_res_id"));

                orders.add(new MyOrder(
                        orderId,
                        goodsId,
                        orderNumber,
                        goodsName,
                        totalPrice,
                        quantity,
                        status,
                        new Date(createTime),
                        imageResId
                ));
            }
        } catch (Exception e) {
            Log.e(TAG, "查询订单失败: " + e.getMessage(), e);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (helper != null) {
                helper.close();
            }
        }
        return orders;
    }

    public static void updateStatus(Context context, int orderId, String status) {
        CartDBOpenHelper helper = null;
        try {
            helper = new CartDBOpenHelper(context);
            ContentValues values = new ContentValues();
            values.put("status", status);
            helper.getWritableDatabase().update(
                    "orders",
                    values,
                    "order_id=?",
                    new String[]{String.valueOf(orderId)}
            );
        } catch (Exception e) {
            Log.e(TAG, "更新订单状态失败: " + e.getMessage(), e);
        } finally {
            if (helper != null) {
                helper.close();
            }
        }
    }

    public static void ensureDemoOrdersIfEmpty(Context context) {
        CartDBOpenHelper helper = null;
        Cursor cursor = null;
        try {
            helper = new CartDBOpenHelper(context);
            SQLiteDatabase db = helper.getReadableDatabase();
            cursor = db.rawQuery("SELECT COUNT(*) FROM orders", null);
            int count = 0;
            if (cursor.moveToFirst()) {
                count = cursor.getInt(0);
            }
            cursor.close();
            cursor = null;

            if (count > 0) {
                return;
            }

            SQLiteDatabase writeDb = helper.getWritableDatabase();
            insertDemoOrder(writeDb, 1, STATUS_PENDING_PAY, "iPad 2024 128G", "学习娱乐 / 轻办公", 2999.00, 1, R.drawable.ipad, 0);
            insertDemoOrder(writeDb, 9, STATUS_PENDING_RECEIVE, "小米移动电源 10000mAh", "轻便续航 / 双向快充", 79.00, 2, R.drawable.c101, 1);
            insertDemoOrder(writeDb, 11, STATUS_PENDING_COMMENT, "Sony 降噪耳机", "通勤降噪 / 长续航", 1299.00, 1, R.drawable.a601, 2);
            insertDemoOrder(writeDb, 21, STATUS_AFTER_SALE, "空气炸锅 5L", "少油烹饪 / 家用小厨电", 399.00, 1, R.drawable.a402, 3);
        } catch (Exception e) {
            Log.e(TAG, "初始化演示订单失败: " + e.getMessage(), e);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (helper != null) {
                helper.close();
            }
        }
    }

    public static String normalizeStatus(String status) {
        if ("待发货".equals(status) || "已发货".equals(status)) {
            return STATUS_PENDING_RECEIVE;
        }
        return status;
    }

    private static void insertDemoOrder(SQLiteDatabase db, int goodsId, String status, String goodsName,
                                        String goodsType, double price, int quantity, int imageResId, int offset) {
        ContentValues values = new ContentValues();
        values.put("order_number", "DEMO" + (System.currentTimeMillis() - offset * 1000L));
        values.put("goods_id", goodsId);
        values.put("goods_name", goodsName);
        values.put("goods_type", goodsType);
        values.put("total_price", price * quantity);
        values.put("quantity", quantity);
        values.put("status", status);
        values.put("create_time", System.currentTimeMillis() - offset * 24 * 60 * 60 * 1000L);
        values.put("image_res_id", imageResId);
        db.insert("orders", null, values);
    }

    private static boolean hasFilter(String statusFilter) {
        return statusFilter != null && statusFilter.trim().length() > 0;
    }

    private static int getInt(Cursor cursor, String column, int fallback) {
        int index = cursor.getColumnIndex(column);
        if (index == -1) {
            return fallback;
        }
        return cursor.getInt(index);
    }
}
