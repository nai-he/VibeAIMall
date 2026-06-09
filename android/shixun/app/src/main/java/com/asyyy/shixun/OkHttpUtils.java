package com.asyyy.shixun;

import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class OkHttpUtils {
    private static final String TAG = "OkHttpUtils";

    // 模拟器使用这个地址
    private static final String BASE_URL = "http://10.0.2.2:8080/api/";

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    // 后端服务未启动时快速失败，避免登录/注册按钮长时间停在加载状态。
    private static OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(3, TimeUnit.SECONDS)
            .readTimeout(3, TimeUnit.SECONDS)
            .writeTimeout(3, TimeUnit.SECONDS)
            .build();

    // POST请求
    public static void post(String url, String json, Callback callback) {
        String fullUrl = BASE_URL + url;
        Log.d(TAG, "=== 发送POST请求 ===");
        Log.d(TAG, "完整URL: " + fullUrl);
        Log.d(TAG, "请求数据: " + json);

        try {
            RequestBody body = RequestBody.create(json, JSON);
            Request request = new Request.Builder()
                    .url(fullUrl)
                    .post(body)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Accept", "application/json")
                    .addHeader("User-Agent", "VibeAIMall-Android-App")
                    .build();

            Log.d(TAG, "请求头: " + request.headers());

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.e(TAG, "❌ POST请求失败: " + e.getClass().getSimpleName() + ": " + e.getMessage());
                    e.printStackTrace();
                    callback.onFailure(call, e);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String responseData = response.body().string();
                    Log.d(TAG, "=== 收到POST响应 ===");
                    Log.d(TAG, "URL: " + fullUrl);
                    Log.d(TAG, "响应码: " + response.code());
                    Log.d(TAG, "响应消息: " + response.message());
                    Log.d(TAG, "响应数据: " + responseData);

                    // 重新创建response，因为body只能读取一次
                    Response newResponse = response.newBuilder()
                            .body(ResponseBody.create(responseData, JSON))
                            .build();

                    callback.onResponse(call, newResponse);
                }
            });
        } catch (Exception e) {
            Log.e(TAG, "❌ 创建POST请求异常: " + e.getMessage());
            callback.onFailure(null, new IOException("创建请求失败: " + e.getMessage()));
        }
    }

    // GET请求
    public static void get(String url, Callback callback) {
        String fullUrl = BASE_URL + url;
        Log.d(TAG, "=== 发送GET请求 ===");
        Log.d(TAG, "完整URL: " + fullUrl);

        try {
            Request request = new Request.Builder()
                    .url(fullUrl)
                    .get()
                    .addHeader("Accept", "application/json")
                    .addHeader("User-Agent", "VibeAIMall-Android-App")
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.e(TAG, "❌ GET请求失败: " + e.getClass().getSimpleName() + ": " + e.getMessage());
                    callback.onFailure(call, e);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String responseData = response.body().string();
                    Log.d(TAG, "=== 收到GET响应 ===");
                    Log.d(TAG, "URL: " + fullUrl);
                    Log.d(TAG, "响应码: " + response.code());
                    Log.d(TAG, "响应数据: " + responseData);

                    // 重新创建response
                    Response newResponse = response.newBuilder()
                            .body(ResponseBody.create(responseData, JSON))
                            .build();

                    callback.onResponse(call, newResponse);
                }
            });
        } catch (Exception e) {
            Log.e(TAG, "❌ 创建GET请求异常: " + e.getMessage());
            callback.onFailure(null, new IOException("创建请求失败: " + e.getMessage()));
        }
    }
}
