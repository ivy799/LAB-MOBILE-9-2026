package com.library.app.auth;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.library.app.R;

public class RegisterActivity extends AppCompatActivity {

    private EditText etNim, etPassword, etConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etNim = findViewById(R.id.et_nim);
        etPassword = findViewById(R.id.et_password);
        etConfirmPassword = findViewById(R.id.et_confirm_password);
        Button btnRegister = findViewById(R.id.btn_register);
        TextView tvLogin = findViewById(R.id.tv_go_login);

        btnRegister.setOnClickListener(v -> attemptRegister());

        tvLogin.setOnClickListener(v -> {
            finish();
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        });
    }

    private void attemptRegister() {
        String nim = etNim.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String confirmPassword = etConfirmPassword.getText().toString().trim();

        if (nim.isEmpty()) {
            etNim.setError("NIM tidak boleh kosong");
            etNim.requestFocus();
            return;
        }
        if (nim.length() < 5) {
            etNim.setError("NIM minimal 5 karakter");
            etNim.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            etPassword.setError("Password tidak boleh kosong");
            etPassword.requestFocus();
            return;
        }
        if (password.length() < 6) {
            etPassword.setError("Password minimal 6 karakter");
            etPassword.requestFocus();
            return;
        }
        if (!password.equals(confirmPassword)) {
            etConfirmPassword.setError("Password tidak cocok");
            etConfirmPassword.requestFocus();
            return;
        }

        SharedPreferences prefs = getSharedPreferences("LibraryPrefs", MODE_PRIVATE);

        // Check if NIM already registered
        if (prefs.contains("user_" + nim)) {
            etNim.setError("NIM sudah terdaftar");
            return;
        }

        // Save user
        prefs.edit().putString("user_" + nim, password).apply();

        Toast.makeText(this, "Registrasi berhasil! Silahkan login 🌸", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish();
    }
}
