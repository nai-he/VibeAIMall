package com.asyyy.shixun;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button buttonRegister;
    private UserDBOpenHelper userDBOpenHelper;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonRegister = findViewById(R.id.buttonRegister);

        userDBOpenHelper = new UserDBOpenHelper(this);
        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerLocalUser();
            }
        });
    }

    private void registerLocalUser() {
        String username = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "用户名和密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        if (userDBOpenHelper.isUserExists(username)) {
            Toast.makeText(this, "该账号已注册，请直接登录", Toast.LENGTH_SHORT).show();
            return;
        }

        long userId = userDBOpenHelper.registerUser(username, password);
        if (userId == -1) {
            Toast.makeText(this, "注册失败，请重试", Toast.LENGTH_SHORT).show();
            return;
        }

        saveCurrentUser((int) userId, username, password);
        Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    private void saveCurrentUser(int userId, String username, String password) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("current_username", username);
        editor.putString("current_phone", "");
        editor.putString("current_address", "");
        editor.putString("current_name", "");
        editor.putInt("current_user_id", userId);
        editor.putString(username, password);
        editor.apply();
    }
}
