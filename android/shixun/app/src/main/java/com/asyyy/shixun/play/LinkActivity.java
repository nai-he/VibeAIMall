package com.asyyy.shixun.play;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.asyyy.shixun.R;


import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class LinkActivity extends AppCompatActivity {

    private EditText etPhoneNumber;
    private EditText etVerificationCode;
    private Button btnGetVerificationCode;
    private Button btnBindPhone;

    // 用于模拟验证码发送倒计时，实际应用中可根据后端逻辑调整
    private int countdownSeconds = 60;
    // 倒计时定时器
    private Timer countdownTimer;

    // 存储生成的手机验证码
    private String verificationCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link);

        etPhoneNumber = findViewById(R.id.et_phone_number);
        etVerificationCode = findViewById(R.id.et_verification_code);
        btnGetVerificationCode = findViewById(R.id.btn_get_verification_code);
        btnBindPhone = findViewById(R.id.btn_bind_phone);

        findViewById(R.id.link).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnGetVerificationCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNumber = etPhoneNumber.getText().toString().trim();
                if (phoneNumber.isEmpty()) {
                    Toast.makeText(LinkActivity.this, "请输入手机号", Toast.LENGTH_SHORT).show();
                    return;
                }

                // 启动倒计时
                startCountdown();
            }
        });

        btnBindPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNumber = etPhoneNumber.getText().toString().trim();
                String verificationCode = etVerificationCode.getText().toString().trim();

                if (phoneNumber.isEmpty()) {
                    Toast.makeText(LinkActivity.this, "请输入手机号", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (verificationCode.isEmpty()) {
                    Toast.makeText(LinkActivity.this, "请输入验证码", Toast.LENGTH_SHORT).show();
                    return;
                }

                // 模拟手机号绑定成功的操作，这里只是简单显示提示信息，实际可能涉及与后端交互等操作
                Toast.makeText(LinkActivity.this, "手机号绑定成功", Toast.LENGTH_SHORT).show();

                // 绑定成功后收起键盘
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm!= null) {
                    View currentFocus = getCurrentFocus();
                    if (currentFocus!= null) {
                        imm.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
                    }
                }

                // 清空手机号和验证码文本框内容
                etPhoneNumber.setText("");
                etVerificationCode.setText("");
            }
        });
    }

    private void startCountdown() {
        btnGetVerificationCode.setEnabled(false);
        countdownTimer = new Timer();

        countdownTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (countdownSeconds > 0) {
                            btnGetVerificationCode.setText(countdownSeconds + "秒后可重新获取");
                            countdownSeconds--;

                            // 添加判断，当倒计时到54秒时生成并发送验证码（这里模拟弹出显示）
                            if (countdownSeconds == 54) {
                                // 生成6位随机数字验证码
                                verificationCode = generateVerificationCode();
                                // 这里应该调用后端接口来发送验证码，此处仅模拟
                                Toast.makeText(LinkActivity.this, "验证码已发送：" + verificationCode, Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            btnGetVerificationCode.setEnabled(true);
                            btnGetVerificationCode.setText("获取验证码");
                            countdownSeconds = 60;
                            countdownTimer.cancel();

                            // 倒计时结束后弹出手机验证码（这里保留，可根据需求决定是否保留此功能）
                            showVerificationCode();
                        }
                    }
                });
            }
        }, 0, 1000);
    }

    private String generateVerificationCode() {
        StringBuilder codeBuilder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            codeBuilder.append(random.nextInt(10));
        }
        return codeBuilder.toString();
    }

    private void showVerificationCode() {
        Toast.makeText(LinkActivity.this, "手机验证码：" + verificationCode, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countdownTimer!= null) {
            countdownTimer.cancel();
        }
    }
}