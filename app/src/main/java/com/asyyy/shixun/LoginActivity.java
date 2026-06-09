package com.asyyy.shixun;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button buttonLogin;
    private Button buttonRegister;
    private UserDBOpenHelper userDBOpenHelper;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonRegister = findViewById(R.id.buttonRegister);

        userDBOpenHelper = new UserDBOpenHelper(this);
        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginLocalUser();
            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }

    private void loginLocalUser() {
        String username = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "用户名和密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        User user = userDBOpenHelper.getUserByLogin(username, password);
        if (user == null) {
            Toast.makeText(this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
            return;
        }

        saveCurrentUser(user);
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, MActivity.class));
        finish();
    }

    private void saveCurrentUser(User user) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("current_username", user.getUsername());
        editor.putString("current_phone", user.getPhone());
        editor.putString("current_address", user.getAddress());
        editor.putString("current_name", user.getName());
        editor.putInt("current_user_id", user.getUser_id());
        editor.putString(user.getUsername(), user.getPassword());
        editor.apply();
    }
}
