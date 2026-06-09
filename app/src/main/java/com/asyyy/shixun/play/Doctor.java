package com.asyyy.shixun.play;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.asyyy.shixun.MActivity;
import com.asyyy.shixun.R;

import java.util.ArrayList;
import java.util.Locale;

public class Doctor extends AppCompatActivity {

    private TextView backHome;
    private EditText messageEditText;
    private Button sendButton;
    private ListView chatListView;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor);

        backHome = findViewById(R.id.back_home);
        messageEditText = findViewById(R.id.messageEditText);
        sendButton = findViewById(R.id.sendButton);
        chatListView = findViewById(R.id.chatListView);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new ArrayList<String>());
        chatListView.setAdapter(adapter);
        adapter.add("AI健康助手：我可以回答一般健康常识。重要提示：我不能替代专业医生诊断，症状严重或持续请及时就医。");

        backHome.setOnClickListener(v -> {
            startActivity(new Intent(this, MActivity.class));
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            finish();
        });

        sendButton.setOnClickListener(v -> {
            String message = messageEditText.getText().toString().trim();
            if (message.isEmpty()) {
                Toast.makeText(this, "请输入问题", Toast.LENGTH_SHORT).show();
                return;
            }
            adapter.add("您：" + message);
            adapter.add("AI健康助手：" + buildLocalResponse(message));
            adapter.notifyDataSetChanged();
            messageEditText.setText("");
            chatListView.smoothScrollToPosition(adapter.getCount() - 1);
        });
    }

    private String buildLocalResponse(String message) {
        String text = message.toLowerCase(Locale.ROOT);
        String advice;
        if (containsAny(text, "发烧", "发热", "高烧")) {
            advice = "建议先测量体温、补充水分并观察精神状态。如果体温较高、持续不退或伴随胸闷、呼吸困难，请及时就医。";
        } else if (containsAny(text, "头疼", "头痛", "偏头痛")) {
            advice = "可以先休息、减少屏幕刺激并补水。如果头痛突然剧烈、反复加重或伴随呕吐、肢体麻木，应尽快就医。";
        } else if (containsAny(text, "咳嗽", "嗓子", "咽喉")) {
            advice = "注意补水、保持空气湿度，减少辛辣刺激。若咳嗽超过数日、发热或胸痛，需要到医院检查。";
        } else if (containsAny(text, "睡眠", "失眠", "熬夜")) {
            advice = "建议固定作息、睡前减少咖啡因和手机使用，白天适度运动。长期失眠建议寻求专业帮助。";
        } else {
            advice = "建议描述症状持续时间、严重程度、是否伴随发热或疼痛，我可以继续帮你做一般健康信息整理。";
        }
        return advice + "\n重要提示：我是 AI 助手，不能替代专业医生诊断。";
    }

    private boolean containsAny(String text, String... keys) {
        for (String key : keys) {
            if (text.contains(key)) {
                return true;
            }
        }
        return false;
    }
}
