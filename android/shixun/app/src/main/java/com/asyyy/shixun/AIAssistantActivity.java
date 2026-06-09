package com.asyyy.shixun;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.asyyy.shixun.ai.AiRemoteAdvisor;
import com.asyyy.shixun.ai.RecommendationEngine;
import com.asyyy.shixun.ai.RecommendationProduct;
import com.asyyy.shixun.ai.RecommendationResult;
import com.asyyy.shixun.ai.AiChatCommand;
import com.asyyy.shixun.ai.AiShoppingChatEngine;
import com.asyyy.shixun.cart.CartDBOpenHelper;
import com.asyyy.shixun.cart.CartService;

public class AIAssistantActivity extends AppCompatActivity {
    private EditText aiInput;
    private LinearLayout aiContent;
    private ScrollView aiScroll;
    private Button addToCartButton;
    private CartDBOpenHelper dbOpenHelper;
    private RecommendationEngine recommendationEngine;
    private AiShoppingChatEngine chatEngine;
    private AiRemoteAdvisor remoteAdvisor;
    private RecommendationResult currentResult;
    private String lastNeed = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ai_assistant);

        dbOpenHelper = new CartDBOpenHelper(this);
        recommendationEngine = new RecommendationEngine();
        chatEngine = new AiShoppingChatEngine();
        remoteAdvisor = new AiRemoteAdvisor();

        aiInput = findViewById(R.id.ai_input);
        aiContent = findViewById(R.id.ai_content);
        aiScroll = findViewById(R.id.ai_scroll);
        addToCartButton = findViewById(R.id.ai_add_cart);

        findViewById(R.id.ai_back).setOnClickListener(v -> finish());
        findViewById(R.id.ai_recommend).setOnClickListener(v -> handleChatInput());
        addToCartButton.setOnClickListener(v -> addCurrentSuggestionToCart());

        addWelcomeCard();

        String need = getIntent().getStringExtra("need");
        if (need != null && need.trim().length() > 0) {
            aiInput.setText(need.trim());
            handleChatInput();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dbOpenHelper != null) {
            dbOpenHelper.close();
        }
    }

    private void addWelcomeCard() {
        String apiState = remoteAdvisor.isConfigured()
                ? "当前已启用真实大模型 API：" + remoteAdvisor.getModelName() + "。\n"
                : "当前未配置 API Key，会自动使用端侧推荐，演示不会中断。\n";
        String text = "现在可以直接对话购物：\n" +
                apiState +
                "1. 说“预算3000学习平板”，我会推荐商品。\n" +
                "2. 继续说“就买这个”，我会把主推加入购物车。\n" +
                "3. 说“买备选1”，可以购买备选商品。\n" +
                "4. 说“去购物车”，可以直接查看购物车。\n\n" +
                "也可以一次说：买一个出差续航充电宝。";
        addCard("AI 已接入购物对话", text, "#F8FAFC", "#111827");
    }

    private void handleChatInput() {
        String message = aiInput.getText().toString().trim();
        if (message.isEmpty()) {
            Toast.makeText(this, "请输入购物需求或购买指令", Toast.LENGTH_SHORT).show();
            return;
        }

        addCard("你", message, "#ECFEFF", "#0F172A");
        aiInput.setText("");

        AiChatCommand command = chatEngine.parse(message, currentResult != null);
        if (command.is(AiChatCommand.ACTION_HELP)) {
            addCard("AI 助手", buildHelpText(), "#FFFFFF", "#111827");
            scrollToBottom();
            return;
        }
        if (command.is(AiChatCommand.ACTION_VIEW_CART)) {
            addCard("AI 助手", "好的，我带你去购物车。你可以继续勾选商品并结算。", "#FFFFFF", "#111827");
            scrollToBottom();
            openCart();
            return;
        }
        if (command.is(AiChatCommand.ACTION_ADD_TO_CART)) {
            addSelectedProductToCart(command.getTargetIndex(), "chat_added_to_cart");
            return;
        }
        if (command.is(AiChatCommand.ACTION_RECOMMEND_AND_ADD)) {
            recommendFromMessage(message);
            addSelectedProductToCart(command.getTargetIndex(), "chat_recommend_and_add");
            return;
        }

        recommendFromMessage(message);
    }

    private void recommendFromMessage(String need) {
        lastNeed = need;
        currentResult = recommendationEngine.recommend(need);

        addCard("AI 需求识别", currentResult.getIntent().getSummary(), "#FFFFFF", "#111827");
        addCard("推荐给你", buildAnswer(currentResult), "#FFFFFF", "#111827");
        requestRemoteAdvice(need, currentResult);
        logRecommendation("recommended");

        addToCartButton.setEnabled(true);
        addToCartButton.setText("加入购物车");
        aiInput.setText("");
        scrollToBottom();
    }

    private void requestRemoteAdvice(String need, RecommendationResult result) {
        if (!remoteAdvisor.isConfigured()) {
            return;
        }

        addCard("真实 API", "正在调用已配置的大模型接口生成导购回复...", "#FFFBEB", "#92400E");
        scrollToBottom();

        remoteAdvisor.requestShoppingAdvice(need, result, new AiRemoteAdvisor.ApiCallback() {
            @Override
            public void onSuccess(String answer) {
                runOnUiThread(() -> {
                    if (isFinishing()) {
                        return;
                    }
                    addCard("真实 API 回复", answer, "#F8FAFC", "#111827");
                    scrollToBottom();
                });
            }

            @Override
            public void onFailure(String message) {
                runOnUiThread(() -> {
                    if (isFinishing()) {
                        return;
                    }
                    addCard("API 已降级", "接口暂时没有返回成功结果，已保留端侧推荐。\n原因：" + message,
                            "#FFF7ED", "#9A3412");
                    scrollToBottom();
                });
            }
        });
    }

    private String buildHelpText() {
        return "你可以这样和我对话：\n" +
                "- “预算3000学习平板”：生成推荐。\n" +
                "- “买一个通勤降噪耳机”：推荐并自动加入购物车。\n" +
                "- “就买这个”：把当前主推加入购物车。\n" +
                "- “买备选1 / 买备选2”：购买备选方案。\n" +
                "- “去购物车”：打开购物车准备结算。";
    }

    private String buildAnswer(RecommendationResult result) {
        RecommendationProduct product = result.getPrimaryProduct();
        StringBuilder builder = new StringBuilder();
        builder.append("主推商品：").append(product.getName()).append("\n");
        builder.append("核心卖点：").append(product.getType()).append("\n");
        builder.append("参考价格：¥").append(product.getPrice()).append("\n");
        builder.append("店铺：").append(product.getShop()).append("\n\n");
        builder.append("为什么推荐：").append(result.getDecisionReason()).append("\n\n");
        builder.append("备选方案：\n");

        int index = 1;
        for (RecommendationProduct alternative : result.getAlternatives()) {
            builder.append(index)
                    .append(". ")
                    .append(alternative.getName())
                    .append("｜¥")
                    .append(alternative.getPrice())
                    .append("｜")
                    .append(alternative.getType())
                    .append("\n");
            index++;
        }

        builder.append("\n购买建议：").append(result.getNextStep());
        return builder.toString();
    }

    private void addCurrentSuggestionToCart() {
        if (currentResult == null) {
            Toast.makeText(this, "请先生成推荐", Toast.LENGTH_SHORT).show();
            return;
        }

        RecommendationProduct product = currentResult.getPrimaryProduct();
        CartService.CartResult cartResult = CartService.addToCart(this, product);
        if (cartResult.isSuccess()) {
            logRecommendation("added_to_cart", product);
            Toast.makeText(this, "已加入购物车：" + product.getName(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, cartResult.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void addSelectedProductToCart(int targetIndex, String action) {
        if (currentResult == null) {
            addCard("AI 助手", "我还没有推荐商品。你可以先说：预算3000学习平板。", "#FFFFFF", "#111827");
            scrollToBottom();
            return;
        }

        RecommendationProduct product = resolveProduct(targetIndex);
        CartService.CartResult cartResult = CartService.addToCart(this, product);
        if (cartResult.isSuccess()) {
            logRecommendation(action, product);
            addCard("AI 已加购", "已加入购物车：" + product.getName() + "\n你可以继续说“去购物车”完成结算。", "#F0FDF4", "#14532D");
        } else {
            addCard("AI 助手", cartResult.getMessage(), "#FFFFFF", "#111827");
        }
        scrollToBottom();
    }

    private RecommendationProduct resolveProduct(int targetIndex) {
        if (targetIndex <= 0) {
            return currentResult.getPrimaryProduct();
        }
        int alternativeIndex = targetIndex - 1;
        if (alternativeIndex >= 0 && alternativeIndex < currentResult.getAlternatives().size()) {
            return currentResult.getAlternatives().get(alternativeIndex);
        }
        return currentResult.getPrimaryProduct();
    }

    private void openCart() {
        Intent intent = new Intent(this, MActivity.class);
        intent.putExtra("tab", 3);
        startActivity(intent);
    }

    private void logRecommendation(String action) {
        if (currentResult == null) {
            return;
        }
        logRecommendation(action, currentResult.getPrimaryProduct());
    }

    private void logRecommendation(String action, RecommendationProduct product) {
        if (currentResult == null || product == null) {
            return;
        }
        try {
            ContentValues values = new ContentValues();
            values.put("input_text", lastNeed);
            values.put("intent", currentResult.getIntent().getSummary());
            values.put("recommend_goods_id", product.getId());
            values.put("action", action);
            values.put("create_time", System.currentTimeMillis());
            dbOpenHelper.getWritableDatabase().insert("recommend_logs", null, values);
        } catch (Exception ignored) {
            // 推荐日志不阻断主流程，避免影响面试演示。
        }
    }

    private void addCard(String title, String body, String bgColor, String textColor) {
        LinearLayout card = new LinearLayout(this);
        card.setOrientation(LinearLayout.VERTICAL);
        card.setPadding(dp(16), dp(14), dp(16), dp(14));

        GradientDrawable background = new GradientDrawable();
        background.setColor(Color.parseColor(bgColor));
        background.setCornerRadius(dp(8));
        background.setStroke(dp(1), Color.parseColor("#E5E7EB"));
        card.setBackgroundDrawable(background);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(0, 0, 0, dp(12));
        card.setLayoutParams(params);

        TextView titleView = new TextView(this);
        titleView.setText(title);
        titleView.setTextColor(Color.parseColor("#0F766E"));
        titleView.setTextSize(15);
        titleView.setTypeface(Typeface.DEFAULT_BOLD);

        TextView bodyView = new TextView(this);
        bodyView.setText(body);
        bodyView.setTextColor(Color.parseColor(textColor));
        bodyView.setTextSize(14);
        bodyView.setLineSpacing(dp(2), 1.0f);
        LinearLayout.LayoutParams bodyParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        bodyParams.setMargins(0, dp(8), 0, 0);
        bodyView.setLayoutParams(bodyParams);

        card.addView(titleView);
        card.addView(bodyView);
        aiContent.addView(card);
    }

    private void scrollToBottom() {
        aiScroll.post(() -> aiScroll.fullScroll(View.FOCUS_DOWN));
    }

    private int dp(int value) {
        return (int) (value * getResources().getDisplayMetrics().density + 0.5f);
    }
}
