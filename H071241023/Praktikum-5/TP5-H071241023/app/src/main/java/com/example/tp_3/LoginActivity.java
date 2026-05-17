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

public class LoginActivity extends AppCompatActivity {
    public static final String PREF_NAME        = "LibraryAppPrefs";
    public static final String KEY_IS_LOGGED_IN = "isLoggedIn";
    public static final String KEY_LOGGED_NIM   = "loggedNim";
    public static final String NIM_PREFIX       = "nim_";
    public static final String KEY_DARK_MODE    = "darkMode";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences prefs = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        applyDarkMode(prefs.getBoolean(KEY_DARK_MODE, false));

        super.onCreate(savedInstanceState);

        if (prefs.getBoolean(KEY_IS_LOGGED_IN, false)) {
            goToMain();
            return;
        }

        setContentView(R.layout.activity_login);

        TextInputEditText etNim      = findViewById(R.id.etNim);
        TextInputEditText etPassword = findViewById(R.id.etPassword);
        Button btnLogin              = findViewById(R.id.btnLogin);
        TextView tvGoRegister        = findViewById(R.id.tvGoRegister);

        btnLogin.setOnClickListener(v -> {
            String nim      = etNim.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (nim.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "NIM dan password tidak boleh kosong!", Toast.LENGTH_SHORT).show();
                return;
            }

            String savedPassword = prefs.getString(NIM_PREFIX + nim, null);
            if (savedPassword == null) {
                Toast.makeText(this, "NIM tidak ditemukan. Silakan daftar terlebih dahulu.", Toast.LENGTH_SHORT).show();
            } else if (!savedPassword.equals(password)) {
                Toast.makeText(this, "Password salah!", Toast.LENGTH_SHORT).show();
            } else {
                prefs.edit()
                        .putBoolean(KEY_IS_LOGGED_IN, true)
                        .putString(KEY_LOGGED_NIM, nim)
                        .apply();
                Toast.makeText(this, "Selamat datang, " + nim + "!", Toast.LENGTH_SHORT).show();
                goToMain();
            }
        });

        tvGoRegister.setOnClickListener(v ->
                startActivity(new Intent(this, RegisterActivity.class)));
    }

    private void goToMain() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    private void applyDarkMode(boolean isDark) {
        AppCompatDelegate.setDefaultNightMode(
                isDark ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO);
    }
}