package com.example.tp_3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.material.textfield.TextInputEditText;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences prefs = getSharedPreferences(LoginActivity.PREF_NAME, MODE_PRIVATE);
        boolean isDark = prefs.getBoolean(LoginActivity.KEY_DARK_MODE, false);
        AppCompatDelegate.setDefaultNightMode(
                isDark ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        TextInputEditText etNim             = findViewById(R.id.etRegNim);
        TextInputEditText etPassword        = findViewById(R.id.etRegPassword);
        TextInputEditText etConfirmPassword = findViewById(R.id.etRegConfirmPassword);
        Button btnRegister                  = findViewById(R.id.btnRegister);
        TextView tvGoLogin                  = findViewById(R.id.tvGoLogin);

        btnRegister.setOnClickListener(v -> {
            String nim      = etNim.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            String confirm  = etConfirmPassword.getText().toString().trim();

            if (nim.isEmpty() || password.isEmpty() || confirm.isEmpty()) {
                Toast.makeText(this, "Semua kolom harus diisi!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (nim.length() < 5) {
                Toast.makeText(this, "NIM minimal 5 digit!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (password.length() < 6) {
                Toast.makeText(this, "Password minimal 6 karakter!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!password.equals(confirm)) {
                Toast.makeText(this, "Password dan konfirmasi tidak cocok!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Cek apakah NIM sudah terdaftar
            String existingPassword = prefs.getString(LoginActivity.NIM_PREFIX + nim, null);
            if (existingPassword != null) {
                Toast.makeText(this, "NIM sudah terdaftar!", Toast.LENGTH_SHORT).show();
                return;
            }

            prefs.edit()
                    .putString(LoginActivity.NIM_PREFIX + nim, password)
                    .apply();

            Toast.makeText(this, "Akun berhasil dibuat! Silakan login.", Toast.LENGTH_SHORT).show();
            finish();
        });

        tvGoLogin.setOnClickListener(v -> finish());
    }
}