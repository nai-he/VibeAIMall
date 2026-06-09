package com.asyyy.shixun.cart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.asyyy.shixun.MActivity;
import com.asyyy.shixun.R;

import java.util.ArrayList;
import java.util.List;

public class Success2Activity extends AppCompatActivity {

    private TextView back_home;
    private EditText messageEditText;
    private Button sendButton;
    private ListView chatListView;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success2);

        back_home = findViewById(R.id.back_home);
        messageEditText = findViewById(R.id.messageEditText);
        sendButton = findViewById(R.id.sendButton);
        chatListView = findViewById(R.id.chatListView);

        back_home.setOnClickListener(v -> {
            startActivity(new Intent(this, MActivity.class));
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            finish();
        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = messageEditText.getText().toString();
                if (!message.isEmpty()) {
                    // 处理发送消息的逻辑
                    sendMessage(message);
                    messageEditText.setText(""); // 清空输入框
                }
            }
        });

        // 初始化适配器
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new ArrayList<String>());
        chatListView.setAdapter(adapter);
    }

    private void sendMessage(String message) {
        // 将消息添加到适配器中
        String formattedMessage = "您好，请问有什么问题吗: " + message;
        String formattedMessage2 = "很高兴为您服务" ;
        adapter.add(formattedMessage);
        adapter.add(formattedMessage2);
        adapter.notifyDataSetChanged(); // 通知适配器数据已更改
    }
}