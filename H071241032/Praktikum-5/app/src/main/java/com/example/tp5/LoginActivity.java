package com.example.tp5;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class LoginActivity extends AppCompatActivity {

    private EditText etNim, etPassword;
    private Button btnLogin;
    private TextView tvGoToRegister; // Deklarasi TextView
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Hubungkan variabel dengan ID di XML
        etNim = findViewById(R.id.etNim);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvGoToRegister = findViewById(R.id.tvGoToRegister); // Inisialisasi TextView

        sharedPreferences = getSharedPreferences("TradePrefs", MODE_PRIVATE);

        // ==========================================
        // CEK DAN TERAPKAN TEMA SAAT APLIKASI DIBUKA
        // ==========================================
        boolean isDarkMode = sharedPreferences.getBoolean("dark_mode", false);
        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        // ==========================================

        // Cek jika sudah dalam posisi login, langsung lempar ke MainActivity
        if (sharedPreferences.getBoolean("isLoggedIn", false)) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }

        // Aksi agar teks Register bisa diklik
        tvGoToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pindah ke halaman RegisterActivity
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        // Aksi saat tombol MASUK diklik
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputNim = etNim.getText().toString().trim();
                String inputPassword = etPassword.getText().toString().trim();

                // Ambil data yang tersimpan saat registrasi
                String registeredNim = sharedPreferences.getString("registered_nim", "");
                String registeredPassword = sharedPreferences.getString("registered_password", "");

                if (inputNim.isEmpty() || inputPassword.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Isi NIM dan Password!", Toast.LENGTH_SHORT).show();
                } else if (registeredNim.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Akun belum terdaftar, silakan Register dulu!", Toast.LENGTH_SHORT).show();
                } else if (inputNim.equals(registeredNim) && inputPassword.equals(registeredPassword)) {
                    // Jika cocok, baru beri akses masuk
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("isLoggedIn", true);
                    editor.apply();

                    Toast.makeText(LoginActivity.this, "Login Berhasil!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish(); // Tutup halaman login
                } else {
                    // Jika salah
                    Toast.makeText(LoginActivity.this, "NIM atau Password salah!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}