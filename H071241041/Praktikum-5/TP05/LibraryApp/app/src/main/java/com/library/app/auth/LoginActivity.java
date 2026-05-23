package com.library.app.auth;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.library.app.MainActivity;
import com.library.app.R;

public class LoginActivity extends AppCompatActivity {

    private EditText etNim, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etNim = findViewById(R.id.et_nim);
        etPassword = findViewById(R.id.et_password);
        Button btnLogin = findViewById(R.id.btn_login);
        TextView tvRegister = findViewById(R.id.tv_go_register);

        btnLogin.setOnClickListener(v -> attemptLogin());

        tvRegister.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        });
    }

    private void attemptLogin() {
        String nim = etNim.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (nim.isEmpty()) {
            etNim.setError("NIM tidak boleh kosong");
            etNim.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            etPassword.setError("Password tidak boleh kosong");
            etPassword.requestFocus();
            return;
        }

        SharedPreferences prefs = getSharedPreferences("LibraryPrefs", MODE_PRIVATE);

        // Check dummy user
        boolean isDummyUser = nim.equals("2200001") && password.equals("password123");

        // Check registered users
        String savedPassword = prefs.getString("user_" + nim, null);
        boolean isRegisteredUser = savedPassword != null && savedPassword.equals(password);

        if (isDummyUser || isRegisteredUser) {
            prefs.edit()
                .putBoolean("is_logged_in", true)
                .putString("logged_nim", nim)
                .apply();

            Toast.makeText(this, "Selamat datang, " + nim + "! 🌸", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            finish();
        } else {
            Toast.makeText(this, "NIM atau Password salah 😢", Toast.LENGTH_SHORT).show();
        }
    }
}
