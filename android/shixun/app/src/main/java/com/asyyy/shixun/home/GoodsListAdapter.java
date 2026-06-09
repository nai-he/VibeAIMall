package com.asyyy.shixun.home;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.asyyy.shixun.R;
import com.asyyy.shixun.cart.CartService;
import com.asyyy.shixun.type.ProductNavigator;

import java.util.ArrayList;

public class GoodsListAdapter extends BaseAdapter {

    private static final String TAG = "GoodsListAdapter";
    private Context mContext;
    private ArrayList<Goods> goodsList;

    public GoodsListAdapter(Context mContext, ArrayList<Goods> goodsList) {
        this.mContext = mContext;
        this.goodsList = goodsList;
        Log.d(TAG, "适配器创建，商品数量: " + goodsList.size());
    }

    @Override
    public int getCount() {
        return goodsList.size();
    }

    @Override
    public Object getItem(int position) {
        return goodsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolder{
        ImageView goods_photo;
        TextView goods_name;
        TextView goods_price;
        TextView goods_sales;
        TextView goods_shop;
        ImageView goods_buy;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d(TAG, "getView 被调用，位置: " + position);

        ViewHolder holder;
        if (convertView == null){
            Log.d(TAG, "创建新的视图");
            try {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.goods_item, parent, false);
                holder = new ViewHolder();
                holder.goods_photo = convertView.findViewById(R.id.goods_item_photo);
                holder.goods_name = convertView.findViewById(R.id.goods_item_name);
                holder.goods_price = convertView.findViewById(R.id.goods_item_price);
                holder.goods_sales = convertView.findViewById(R.id.goods_item_sales);
                holder.goods_shop = convertView.findViewById(R.id.goods_item_shop);
                holder.goods_buy = convertView.findViewById(R.id.goods_item_buy);

                Log.d(TAG, "goods_photo: " + (holder.goods_photo != null));
                Log.d(TAG, "goods_name: " + (holder.goods_name != null));
                Log.d(TAG, "goods_buy: " + (holder.goods_buy != null));

                convertView.setTag(holder);
            } catch (Exception e) {
                Log.e(TAG, "创建视图失败: " + e.getMessage(), e);
                TextView errorView = new TextView(mContext);
                errorView.setText("商品项加载错误");
                return errorView;
            }
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        try {
            // 设置商品信息
            final Goods goods = goodsList.get(position); // 添加final修饰符
            holder.goods_photo.setBackgroundResource(goods.getG_photo());
            holder.goods_name.setText(goods.getG_name());
            String price = "¥" + goods.getG_price();
            holder.goods_price.setText(price);
            String sales = goods.getG_sales() + "条评价  99%好评";
            holder.goods_sales.setText(sales);
            holder.goods_shop.setText(goods.getG_shop());
            convertView.setOnClickListener(v -> ProductNavigator.openDetail(mContext, goods));

            // 修复购物车图标点击事件 - 恢复数据库操作
            if (holder.goods_buy != null) {
                holder.goods_buy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d(TAG, "购物车图标被点击 - 开始添加商品: " + goods.getG_name());
                        try {
                            addToCartSafe(goods); // 调用实际的数据库操作方法
                        } catch (Exception e) {
                            Log.e(TAG, "购物车点击异常: " + e.getMessage(), e);
                            Toast.makeText(mContext, "添加失败，请重试", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                Log.d(TAG, "购物车点击监听器设置成功");
            } else {
                Log.e(TAG, "goods_buy 为 null，无法设置点击事件");
            }
        } catch (Exception e) {
            Log.e(TAG, "设置商品信息失败: " + e.getMessage(), e);
        }

        return convertView;
    }

    /**
     * 安全地添加商品到购物车
     */
    private void addToCartSafe(Goods goods) {
        CartService.CartResult result = CartService.addToCart(mContext, goods);
        Toast.makeText(mContext, result.getMessage(), Toast.LENGTH_SHORT).show();
        Log.d(TAG, result.getMessage() + "，当前数量: " + result.getCount());
    }
}
