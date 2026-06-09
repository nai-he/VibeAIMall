package com.asyyy.shixun.cart;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.asyyy.shixun.ai.RecommendationProduct;
import com.asyyy.shixun.home.Goods;

public class CartService {
    public static CartResult addToCart(Context context, Goods goods) {
        if (goods == null) {
            return CartResult.fail("商品信息错误");
        }
        return addToCart(
                context,
                goods.getG_id(),
                goods.getG_photo(),
                goods.getG_name(),
                goods.getG_type(),
                goods.getG_price()
        );
    }

    public static CartResult addToCart(Context context, RecommendationProduct product) {
        if (product == null) {
            return CartResult.fail("推荐商品信息错误");
        }
        return addToCart(
                context,
                product.getId(),
                product.getPhoto(),
                product.getName(),
                product.getType(),
                product.getPrice()
        );
    }

    public static int getCartCount(Context context, int goodsId) {
        CartDBOpenHelper helper = null;
        Cursor cursor = null;
        try {
            helper = new CartDBOpenHelper(context);
            cursor = helper.getReadableDatabase().query(
                    "cart",
                    new String[]{"g_num"},
                    "g_id=?",
                    new String[]{String.valueOf(goodsId)},
                    null,
                    null,
                    null
            );
            if (cursor.moveToFirst()) {
                return cursor.getInt(cursor.getColumnIndexOrThrow("g_num"));
            }
        } catch (Exception ignored) {
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (helper != null) {
                helper.close();
            }
        }
        return 0;
    }

    private static CartResult addToCart(Context context, int goodsId, int photo, String name,
                                        String type, double price) {
        CartDBOpenHelper helper = null;
        try {
            helper = new CartDBOpenHelper(context);
            SQLiteDatabase db = helper.getWritableDatabase();
            int currentCount = getCartCount(context, goodsId);

            ContentValues values = new ContentValues();
            long result;
            int newCount;
            if (currentCount == 0) {
                values.put("g_id", goodsId);
                values.put("g_photo", photo);
                values.put("g_name", name);
                values.put("g_type", type);
                values.put("g_price", price);
                values.put("g_num", 1);
                values.put("g_check", "false");
                result = db.insert("cart", null, values);
                newCount = 1;
            } else {
                newCount = currentCount + 1;
                values.put("g_num", newCount);
                result = db.update(
                        "cart",
                        values,
                        "g_id=?",
                        new String[]{String.valueOf(goodsId)}
                );
            }

            if (result == -1) {
                return CartResult.fail("加入购物车失败");
            }
            return CartResult.success(name + "加入购物车成功", newCount);
        } catch (Exception e) {
            return CartResult.fail("加入购物车时出错：" + e.getMessage());
        } finally {
            if (helper != null) {
                helper.close();
            }
        }
    }

    public static class CartResult {
        private final boolean success;
        private final String message;
        private final int count;

        private CartResult(boolean success, String message, int count) {
            this.success = success;
            this.message = message;
            this.count = count;
        }

        public static CartResult success(String message, int count) {
            return new CartResult(true, message, count);
        }

        public static CartResult fail(String message) {
            return new CartResult(false, message, 0);
        }

        public boolean isSuccess() {
            return success;
        }

        public String getMessage() {
            return message;
        }

        public int getCount() {
            return count;
        }
    }
}
