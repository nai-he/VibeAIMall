package com.asyyy.shixun.cart;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.asyyy.shixun.MActivity;
import com.asyyy.shixun.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DingdanActivity extends AppCompatActivity {

    private TextView ding_money, ding_buy;
    private EditText u_address, u_name, u_phone;
    private CartDBOpenHelper dbOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dingdan);

        ding_money = findViewById(R.id.ding_money);
        ding_buy = findViewById(R.id.ding_buy);
        u_address = findViewById(R.id.u_address);
        u_name = findViewById(R.id.u_name);
        u_phone = findViewById(R.id.u_phone);
        dbOpenHelper = new CartDBOpenHelper(this);

        Intent intent = getIntent();
        double money = intent.getDoubleExtra("money", 0.0);
        ding_money.setText("合计:￥" + money);

        getUserInfo();

        ding_buy.setOnClickListener(v -> {
            String address = u_address.getText().toString().trim();
            String name = u_name.getText().toString().trim();
            String phone = u_phone.getText().toString().trim();

            if (address.isEmpty() || name.isEmpty() || phone.isEmpty()) {
                Toast.makeText(this, "请填写完整的收货信息", Toast.LENGTH_SHORT).show();
                return;
            }

            SharedPreferences sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("address", address);
            editor.putString("name", name);
            editor.putString("phone", phone);
            editor.apply();

            // 在删除购物车之前，先创建订单
            boolean orderCreated = createOrderFromCart();

            if (orderCreated) {
                Toast.makeText(this, "支付中...", Toast.LENGTH_LONG).show();
                new Handler().postDelayed(() -> {
                    startActivity(new Intent(this, SuccessActivity.class));
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    finish();
                }, 3000);
            } else {
                Toast.makeText(this, "创建订单失败，请重试", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getUserInfo(){
        File file = new File("/data/data/com.asyyy.shixun/shared_prefs/userInfo.xml");
        if (file.exists()){
            SharedPreferences sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);
            String address = sharedPreferences.getString("address","");
            String name = sharedPreferences.getString("name","");
            String phone = sharedPreferences.getString("phone","");

            u_address.setText(address);
            u_name.setText(name);
            u_phone.setText(phone);
        }
    }

    // 从购物车创建订单
    private boolean createOrderFromCart() {
        Log.d("DingdanActivity", "开始创建订单");

        // 获取购物车中选中的商品
        List<CartGoods> selectedGoods = getSelectedCartGoods();

        Log.d("DingdanActivity", "选中的商品数量: " + selectedGoods.size());

        if (selectedGoods.isEmpty()) {
            Toast.makeText(this, "没有选中的商品", Toast.LENGTH_SHORT).show();
            return false;
        }

        boolean allSuccess = true;

        // 为每个选中的商品创建订单
        for (CartGoods cartGoods : selectedGoods) {
            boolean success = createOrderRecord(cartGoods);
            if (!success) {
                allSuccess = false;
                Log.e("DingdanActivity", "创建订单记录失败: " + cartGoods.getG_name());
            } else {
                Log.d("DingdanActivity", "成功创建订单: " + cartGoods.getG_name());
            }
        }

        // 删除购物车中选中的商品
        if (allSuccess) {
            delCartAll();
            Log.d("DingdanActivity", "购物车商品已删除");
        }

        return allSuccess;
    }

    // 获取购物车中选中的商品
    private List<CartGoods> getSelectedCartGoods() {
        List<CartGoods> selectedGoods = new ArrayList<>();
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
        Cursor cursor = null;

        try {
            cursor = db.query("cart", null, "g_check=?", new String[]{"true"}, null, null, null);

            if (cursor != null && cursor.moveToFirst()) {
                do {
                    int g_id = cursor.getInt(cursor.getColumnIndexOrThrow("g_id"));
                    int g_photo = cursor.getInt(cursor.getColumnIndexOrThrow("g_photo"));
                    String g_name = cursor.getString(cursor.getColumnIndexOrThrow("g_name"));
                    String g_type = cursor.getString(cursor.getColumnIndexOrThrow("g_type"));
                    double g_price = cursor.getDouble(cursor.getColumnIndexOrThrow("g_price"));
                    int c_num = cursor.getInt(cursor.getColumnIndexOrThrow("g_num"));

                    selectedGoods.add(new CartGoods(g_id, g_photo, g_name, g_type, g_price, c_num, "true"));
                    Log.d("DingdanActivity", "找到选中商品: " + g_name);
                } while (cursor.moveToNext());
            } else {
                Log.d("DingdanActivity", "没有找到选中的商品");
            }
        } catch (Exception e) {
            Log.e("DingdanActivity", "获取选中商品错误: " + e.getMessage(), e);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return selectedGoods;
    }

    // 创建订单记录
    private boolean createOrderRecord(CartGoods cartGoods) {
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();

        try {
            ContentValues values = new ContentValues();
            String orderNumber = "ORDER" + System.currentTimeMillis();
            values.put("order_number", orderNumber);
            values.put("goods_id", cartGoods.getG_id());
            values.put("goods_name", cartGoods.getG_name());
            values.put("goods_type", cartGoods.getG_type());
            values.put("total_price", cartGoods.getG_price() * cartGoods.getC_num());
            values.put("quantity", cartGoods.getC_num());
            values.put("status", "待收货");
            values.put("create_time", System.currentTimeMillis());
            values.put("image_res_id", cartGoods.getG_photo());

            long result = db.insert("orders", null, values);

            if (result != -1) {
                Log.d("DingdanActivity", "订单创建成功: " + orderNumber);
                return true;
            } else {
                Log.e("DingdanActivity", "订单创建失败: " + orderNumber);
                return false;
            }
        } catch (Exception e) {
            Log.e("DingdanActivity", "创建订单记录异常: " + e.getMessage(), e);
            return false;
        }
    }

    //删除购物车中选中的商品
    private void delCartAll(){
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        try {
            int deletedRows = db.delete("cart", "g_check=?", new String[]{"true"});
            Log.d("DingdanActivity", "删除购物车商品数量: " + deletedRows);
        } catch (Exception e) {
            Log.e("DingdanActivity", "删除购物车商品错误: " + e.getMessage(), e);
        } finally {
            dbOpenHelper.close();
        }
    }
}
