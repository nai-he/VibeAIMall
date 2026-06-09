package com.asyyy.shixun.ai;

import com.asyyy.shixun.BuildConfig;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AiRemoteAdvisor {
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private final OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(8, TimeUnit.SECONDS)
            .readTimeout(12, TimeUnit.SECONDS)
            .writeTimeout(8, TimeUnit.SECONDS)
            .build();

    public boolean isConfigured() {
        return hasText(BuildConfig.AI_API_KEY)
                && hasText(BuildConfig.AI_API_BASE_URL)
                && hasText(BuildConfig.AI_API_MODEL);
    }

    public String getModelName() {
        return BuildConfig.AI_API_MODEL;
    }

    public void requestShoppingAdvice(String userNeed, RecommendationResult result,
                                      ApiCallback callback) {
        if (!isConfigured()) {
            callback.onFailure("未配置 AI_API_KEY");
            return;
        }

        try {
            JSONObject body = new JSONObject();
            body.put("model", BuildConfig.AI_API_MODEL);
            body.put("temperature", 0.35);
            body.put("max_tokens", 700);
            body.put("stream", false);

            JSONArray messages = new JSONArray();
            messages.put(new JSONObject()
                    .put("role", "system")
                    .put("content", buildSystemPrompt()));
            messages.put(new JSONObject()
                    .put("role", "user")
                    .put("content", buildUserPrompt(userNeed, result)));
            body.put("messages", messages);

            Request request = new Request.Builder()
                    .url(buildChatUrl(BuildConfig.AI_API_BASE_URL))
                    .post(RequestBody.create(body.toString(), JSON))
                    .addHeader("Authorization", "Bearer " + BuildConfig.AI_API_KEY)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Accept", "application/json")
                    .addHeader("User-Agent", "VibeAIMall-Android-App")
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    callback.onFailure(e.getClass().getSimpleName() + ": " + e.getMessage());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String responseText = response.body() == null ? "" : response.body().string();
                    if (!response.isSuccessful()) {
                        callback.onFailure("HTTP " + response.code() + " " + response.message());
                        return;
                    }

                    try {
                        String answer = parseAnswer(responseText);
                        if (hasText(answer)) {
                            callback.onSuccess(answer.trim());
                        } else {
                            callback.onFailure("接口返回内容为空");
                        }
                    } catch (Exception e) {
                        callback.onFailure("解析响应失败：" + e.getMessage());
                    }
                }
            });
        } catch (Exception e) {
            callback.onFailure("创建请求失败：" + e.getMessage());
        }
    }

    private String buildSystemPrompt() {
        return "你是 Vibe智购AI 的电商导购助手。"
                + "你要基于客户端给定的商品匹配结果生成自然、可信、面试演示友好的导购回复。"
                + "不要编造不存在的商品、价格、库存或优惠。"
                + "回复用中文，控制在 180 字以内，最后提示用户可以说“就买这个”“买备选1”或“去购物车”。";
    }

    private String buildUserPrompt(String userNeed, RecommendationResult result) {
        StringBuilder builder = new StringBuilder();
        RecommendationProduct primary = result.getPrimaryProduct();
        builder.append("用户需求：").append(userNeed).append("\n");
        builder.append("客户端识别：").append(result.getIntent().getSummary()).append("\n");
        builder.append("主推商品：")
                .append(primary.getName())
                .append("，价格 ¥")
                .append(primary.getPrice())
                .append("，卖点：")
                .append(primary.getType())
                .append("，店铺：")
                .append(primary.getShop())
                .append("\n");
        builder.append("本地推荐理由：").append(result.getDecisionReason()).append("\n");
        builder.append("备选商品：");
        int index = 1;
        for (RecommendationProduct product : result.getAlternatives()) {
            builder.append("\n")
                    .append(index)
                    .append(". ")
                    .append(product.getName())
                    .append("，¥")
                    .append(product.getPrice())
                    .append("，")
                    .append(product.getType());
            index++;
        }
        return builder.toString();
    }

    private String parseAnswer(String responseText) throws Exception {
        JSONObject json = new JSONObject(responseText);
        JSONArray choices = json.optJSONArray("choices");
        if (choices == null || choices.length() == 0) {
            return "";
        }
        JSONObject choice = choices.getJSONObject(0);
        JSONObject message = choice.optJSONObject("message");
        if (message == null) {
            return choice.optString("text", "");
        }
        return message.optString("content", "");
    }

    private String buildChatUrl(String baseUrl) {
        String normalized = baseUrl == null ? "" : baseUrl.trim();
        while (normalized.endsWith("/")) {
            normalized = normalized.substring(0, normalized.length() - 1);
        }
        if (normalized.endsWith("/chat/completions")) {
            return normalized;
        }
        return normalized + "/chat/completions";
    }

    private boolean hasText(String value) {
        return value != null && value.trim().length() > 0;
    }

    public interface ApiCallback {
        void onSuccess(String answer);

        void onFailure(String message);
    }
}
