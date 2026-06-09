package com.asyyy.shixun.cart;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class CartDBOpenHelper extends SQLiteOpenHelper {

    // 使用统一的数据库版本号
    private static final int DATABASE_VERSION = 5;

    public CartDBOpenHelper(@Nullable Context context) {
        super(context, "jdshop.db", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("CartDBOpenHelper", "Creating database tables");

        createCartTable(db);
        createOrdersTable(db);
        createCommentsTable(db);
        createRecommendLogsTable(db);

        Log.d("CartDBOpenHelper", "Database tables created successfully");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("CartDBOpenHelper", "Upgrading database from " + oldVersion + " to " + newVersion);

        // 如果旧版本没有订单表，就创建它
        if (oldVersion < 2) {
            createOrdersTable(db);
            Log.d("CartDBOpenHelper", "Added orders table in upgrade");
        }

        // 如果旧版本没有评论表，就创建它
        if (oldVersion < 3) {
            createCommentsTable(db);
            Log.d("CartDBOpenHelper", "Added comments table in upgrade");
        }

        if (oldVersion < 4) {
            createRecommendLogsTable(db);
            Log.d("CartDBOpenHelper", "Added recommend_logs table in upgrade");
        }

        if (oldVersion < 5) {
            try {
                db.execSQL("ALTER TABLE orders ADD COLUMN goods_id INTEGER DEFAULT 0");
                Log.d("CartDBOpenHelper", "Added goods_id column to orders");
            } catch (Exception e) {
                Log.d("CartDBOpenHelper", "orders.goods_id already exists or alter skipped");
            }
        }
    }

    private void createCartTable(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS cart(cart_id INTEGER PRIMARY KEY AUTOINCREMENT, g_id INTEGER, g_photo INTEGER, g_name TEXT, g_type TEXT, g_price REAL, g_num INTEGER, g_check TEXT)");
    }

    private void createOrdersTable(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS orders(order_id INTEGER PRIMARY KEY AUTOINCREMENT, order_number TEXT, goods_id INTEGER DEFAULT 0, goods_name TEXT, goods_type TEXT, total_price REAL, quantity INTEGER, status TEXT, create_time INTEGER, image_res_id INTEGER)");
    }

    private void createCommentsTable(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS comments(" +
                "comment_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "goods_id INTEGER, " +
                "user_name TEXT, " +
                "content TEXT, " +
                "rating REAL, " +
                "create_time TEXT)");
    }

    private void createRecommendLogsTable(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS recommend_logs(" +
                "log_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "input_text TEXT, " +
                "intent TEXT, " +
                "recommend_goods_id INTEGER, " +
                "action TEXT, " +
                "create_time INTEGER)");
    }
}
