package com.asyyy.shixun.type;

import android.content.Context;
import android.content.Intent;

import com.asyyy.shixun.home.Goods;

public class ProductNavigator {
    public static void openDetail(Context context, Goods goods) {
        if (context == null || goods == null) {
            return;
        }

        Intent intent = new Intent(context, ChatActivity.class);
        intent.putExtra("goods_id", goods.getG_id());
        intent.putExtra("goods_name", goods.getG_name());
        intent.putExtra("goods_type", goods.getG_type());
        intent.putExtra("goods_price", goods.getG_price());
        intent.putExtra("goods_sales", goods.getG_sales());
        intent.putExtra("goods_shop", goods.getG_shop());
        intent.putExtra("goods_photo", goods.getG_photo());
        context.startActivity(intent);
    }
}
