package com.asyyy.shixun.cart;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.asyyy.shixun.MyDialog;
import com.asyyy.shixun.R;
import com.asyyy.shixun.base.BaseFragment;
import com.asyyy.shixun.home.Goods;

import java.util.ArrayList;

public class CartFragment extends BaseFragment {

    private static final String TAG = "CartFragment";
    private ListView cart_list;
    private TextView cart_money, cart_check_all, cart_del, cart_buy;
    private CartDBOpenHelper dbOpenHelper;
    private boolean flag = true;
    private CartListAdapter cartListAdapter;

    @Override
    public View initView() {
        Log.d(TAG, "开始初始化购物车视图");
        try {
            View view = View.inflate(mContext, R.layout.fragment_cart, null);
            Log.d(TAG, "布局加载成功");

            cart_list = view.findViewById(R.id.cart_list);
            cart_money = view.findViewById(R.id.cart_money);
            cart_check_all = view.findViewById(R.id.cart_check_all);
            cart_del = view.findViewById(R.id.cart_del);
            cart_buy = view.findViewById(R.id.cart_buy);

            Log.d(TAG, "控件初始化状态:");
            Log.d(TAG, "cart_list: " + (cart_list != null));
            Log.d(TAG, "cart_money: " + (cart_money != null));

            if (cart_list == null || cart_money == null) {
                Log.e(TAG, "关键控件初始化失败，使用备用布局");
                return createFallbackView();
            }

            initListener();
            checkDatabaseStatus();
            refreshCartData();
            return view;
        } catch (Exception e) {
            Log.e(TAG, "初始化购物车视图失败: " + e.getMessage(), e);
            return createFallbackView();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "CartFragment onResume - 刷新数据");
        refreshCartData();
    }

    /**
     * 检查数据库状态
     */
    private void checkDatabaseStatus() {
        Log.d(TAG, "开始检查数据库状态");
        try {
            dbOpenHelper = new CartDBOpenHelper(mContext);
            SQLiteDatabase db = dbOpenHelper.getReadableDatabase();

            String dbPath = mContext.getDatabasePath("jdshop.db").getAbsolutePath();
            Log.d(TAG, "数据库文件路径: " + dbPath);

            Cursor cursor = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='cart'", null);
            boolean tableExists = cursor.moveToFirst();
            cursor.close();

            Log.d(TAG, "购物车表存在: " + tableExists);

            if (tableExists) {
                cursor = db.rawQuery("SELECT COUNT(*) FROM cart", null);
                if (cursor.moveToFirst()) {
                    int rowCount = cursor.getInt(0);
                    Log.d(TAG, "购物车表中的总行数: " + rowCount);
                }
                cursor.close();

                cursor = db.rawQuery("SELECT * FROM cart", null);
                Log.d(TAG, "购物车表所有数据 - 行数: " + cursor.getCount());

                String[] columnNames = cursor.getColumnNames();
                Log.d(TAG, "表结构 - 列名:");
                for (String columnName : columnNames) {
                    Log.d(TAG, "  - " + columnName);
                }

                while (cursor.moveToNext()) {
                    StringBuilder rowData = new StringBuilder();
                    for (String columnName : columnNames) {
                        int columnIndex = cursor.getColumnIndex(columnName);
                        String value = cursor.getString(columnIndex);
                        rowData.append(columnName).append("=").append(value).append(", ");
                    }
                    Log.d(TAG, "数据行: " + rowData.toString());
                }
                cursor.close();
            } else {
                Log.e(TAG, "购物车表不存在！");
            }

            dbOpenHelper.close();
        } catch (Exception e) {
            Log.e(TAG, "检查数据库状态失败: " + e.getMessage(), e);
        }
    }

    /**
     * 刷新购物车数据
     */
    private void refreshCartData() {
        Log.d(TAG, "开始刷新购物车数据");
        try {
            ArrayList<CartGoods> cartGoods = listAllCartGoods();
            Log.d(TAG, "获取到购物车商品数量: " + cartGoods.size());

            if (cart_list != null) {
                if (cartListAdapter == null) {
                    cartListAdapter = new CartListAdapter(mContext, cartGoods);
                    cart_list.setAdapter(cartListAdapter);
                    Log.d(TAG, "创建新的适配器");
                } else {
                    cartListAdapter = new CartListAdapter(mContext, cartGoods);
                    cart_list.setAdapter(cartListAdapter);
                    cartListAdapter.notifyDataSetChanged();
                    Log.d(TAG, "更新现有适配器数据");
                }
            } else {
                Log.e(TAG, "cart_list 为null，无法设置适配器");
            }

            updateTotalMoney();

        } catch (Exception e) {
            Log.e(TAG, "刷新购物车数据失败: " + e.getMessage(), e);
            Toast.makeText(mContext, "加载购物车数据失败", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 更新合计金额
     */
    private void updateTotalMoney() {
        try {
            double money = 0.0;
            ArrayList<CartGoods> goods = listAllCartGoods();
            for (CartGoods g : goods){
                if ("true".equals(g.getC_checked())){
                    money += g.getC_num() * g.getG_price();
                }
            }
            if (cart_money != null) {
                cart_money.setText("合计:￥" + money);
            }
            Log.d(TAG, "更新合计金额: ￥" + money);
        } catch (Exception e) {
            Log.e(TAG, "更新合计金额失败: " + e.getMessage(), e);
        }
    }

    public void initData(){
        Log.d(TAG, "开始初始化数据");
        refreshCartData();
        super.initData();
    }

    private View createFallbackView() {
        Log.d(TAG, "创建备用视图");
        TextView textView = new TextView(mContext);
        textView.setText("购物车\n\n功能开发中...");
        textView.setTextSize(20);
        textView.setTextColor(Color.BLACK);
        textView.setGravity(Gravity.CENTER);
        textView.setBackgroundColor(Color.WHITE);
        return textView;
    }

    private void initListener(){
        Log.d(TAG, "初始化事件监听器");

        cart_check_all.setOnClickListener(v -> {
            try {
                if (flag){
                    dbOpenHelper = new CartDBOpenHelper(mContext); // 改为版本2
                    ContentValues values = new ContentValues();
                    values.put("g_check", "true");
                    dbOpenHelper.getWritableDatabase().update("cart", values, null, null);
                    dbOpenHelper.close();
                    flag = false;
                }else {
                    dbOpenHelper = new CartDBOpenHelper(mContext);// 改为版本2
                    ContentValues values = new ContentValues();
                    values.put("g_check", "false");
                    dbOpenHelper.getWritableDatabase().update("cart", values, null, null);
                    dbOpenHelper.close();
                    flag = true;
                }
                refreshCartData();
                Toast.makeText(mContext, "全选！！！", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Log.e(TAG, "全选操作失败: " + e.getMessage(), e);
                Toast.makeText(mContext, "操作失败，请重试", Toast.LENGTH_SHORT).show();
            }
        });

        cart_money.setOnClickListener(v -> {
            updateTotalMoney();
        });

        cart_del.setOnClickListener(v -> {
            try {
                MyDialog myDialog = new MyDialog(mContext, R.style.MyDialog);
                myDialog.setTitle("提示")
                        .setMessage("确认删除该订单吗？")
                        .setCancel("取消", dialog -> dialog.dismiss() )
                        .setConfirm("确认", dialog -> {
                            try {
                                ArrayList<CartGoods> goods = listAllCartGoods();
                                for (CartGoods g : goods){
                                    if ("true".equals(g.getC_checked())){
                                        delCartGoods(g.getG_id());
                                    }
                                }
                                refreshCartData();
                                Toast.makeText(mContext, "删除成功", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            } catch (Exception e) {
                                Log.e(TAG, "删除商品失败: " + e.getMessage(), e);
                                Toast.makeText(mContext, "删除失败", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            } catch (Exception e) {
                Log.e(TAG, "显示删除对话框失败: " + e.getMessage(), e);
            }
        });

        cart_buy.setOnClickListener(v -> {
            try {
                double money = 0.0;
                boolean isEmp = false;
                ArrayList<CartGoods> goods = listAllCartGoods();
                for (CartGoods g : goods){
                    if ("true".equals(g.getC_checked())){
                        isEmp = true;
                    }
                }
                if (!isEmp){
                    Toast.makeText(mContext, "您没有选中任何商品哦~", Toast.LENGTH_SHORT).show();
                }else {
                    for (CartGoods g : goods){
                        if ("true".equals(g.getC_checked())){
                            money += g.getC_num() * g.getG_price();
                        }
                    }
                    Intent intent = new Intent();
                    intent.putExtra("money",money);
                    intent.setClass(mContext,DingdanActivity.class);
                    startActivity(intent);
                }
            } catch (Exception e) {
                Log.e(TAG, "结算操作失败: " + e.getMessage(), e);
                Toast.makeText(mContext, "结算失败，请重试", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void delCartGoods(int g_id){
        try {
            dbOpenHelper = new CartDBOpenHelper(mContext);// 改为版本2
            int deletedRows = dbOpenHelper.getWritableDatabase().delete("cart", "g_id=?", new String[]{String.valueOf(g_id)});
            dbOpenHelper.close();
            Log.d(TAG, "删除商品ID: " + g_id + ", 删除行数: " + deletedRows);
            refreshCartData();
        } catch (Exception e) {
            Log.e(TAG, "删除商品失败: " + e.getMessage(), e);
        }
    }

    private ArrayList<CartGoods> listAllCartGoods(){
        ArrayList<CartGoods> cartGoods = new ArrayList<>();
        Cursor cursor = null;

        try {
            dbOpenHelper = new CartDBOpenHelper(mContext);// 改为版本2
            cursor = dbOpenHelper.getReadableDatabase().query("cart", null, null, null, null, null, null);

            Log.d(TAG, "数据库查询结果行数: " + cursor.getCount());

            while (cursor.moveToNext()){
                try {
                    int g_id_index = cursor.getColumnIndex("g_id");
                    int g_photo_index = cursor.getColumnIndex("g_photo");
                    int g_name_index = cursor.getColumnIndex("g_name");
                    int g_type_index = cursor.getColumnIndex("g_type");
                    int g_price_index = cursor.getColumnIndex("g_price");
                    int g_num_index = cursor.getColumnIndex("g_num");
                    int g_check_index = cursor.getColumnIndex("g_check");

                    if (g_id_index == -1 || g_photo_index == -1 || g_name_index == -1) {
                        Log.e(TAG, "列索引无效，可能表结构不匹配");
                        continue;
                    }

                    int g_id = cursor.getInt(g_id_index);
                    int g_photo = cursor.getInt(g_photo_index);
                    String g_name = cursor.getString(g_name_index);
                    String g_type = cursor.getString(g_type_index);
                    double g_price = cursor.getDouble(g_price_index);
                    int c_num = cursor.getInt(g_num_index);
                    String c_check = cursor.getString(g_check_index);

                    Log.d(TAG, "解析商品: ID=" + g_id + ", 名称=" + g_name + ", 价格=" + g_price + ", 数量=" + c_num);

                    cartGoods.add(new CartGoods(g_id, g_photo, g_name, g_type, g_price, c_num, c_check));

                } catch (Exception e) {
                    Log.e(TAG, "解析商品数据失败: " + e.getMessage(), e);
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "查询购物车商品失败: " + e.getMessage(), e);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (dbOpenHelper != null) {
                dbOpenHelper.close();
            }
        }

        Log.d(TAG, "最终返回商品数量: " + cartGoods.size());
        return cartGoods;
    }

    private void uptGoodsNum(int g_id, int g_num) {
        try {
            dbOpenHelper = new CartDBOpenHelper(mContext);// 改为版本2
            ContentValues values = new ContentValues();
            values.put("g_num", g_num);
            int updatedRows = dbOpenHelper.getWritableDatabase().update("cart", values, "g_id=?", new String[]{String.valueOf(g_id)});
            dbOpenHelper.close();
            Log.d(TAG, "修改商品数量: ID=" + g_id + ", 新数量=" + g_num + ", 更新行数: " + updatedRows);
            refreshCartData();
        } catch (Exception e) {
            Log.e(TAG, "修改商品数量失败: " + e.getMessage(), e);
        }
    }

    private void uptGoodsChecked(int g_id, String isChecked){
        try {
            dbOpenHelper = new CartDBOpenHelper(mContext);// 改为版本2
            ContentValues values = new ContentValues();
            values.put("g_check", isChecked);
            int updatedRows = dbOpenHelper.getWritableDatabase().update("cart", values, "g_id=?", new String[]{String.valueOf(g_id)});
            dbOpenHelper.close();
            Log.d(TAG, "修改商品选中状态: ID=" + g_id + ", 状态=" + isChecked + ", 更新行数: " + updatedRows);
            refreshCartData();
        } catch (Exception e) {
            Log.e(TAG, "修改商品选中状态失败: " + e.getMessage(), e);
        }
    }
}