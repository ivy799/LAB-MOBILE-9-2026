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

public class RegisterActivity extends AppCompatActivity {

    private EditText etRegNim, etRegPassword;
    private Button btnRegister;
    private TextView tvGoToLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etRegNim = findViewById(R.id.etRegNim);
        etRegPassword = findViewById(R.id.etRegPassword);
        btnRegister = findViewById(R.id.btnRegister);
        tvGoToLogin = findViewById(R.id.tvGoToLogin);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nim = etRegNim.getText().toString().trim();
                String password = etRegPassword.getText().toString().trim();

                if (nim.isEmpty() || password.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "NIM dan Password harus diisi!", Toast.LENGTH_SHORT).show();
                } else {
                    // Simpan data registrasi ke SharedPreferences
                    SharedPreferences sharedPreferences = getSharedPreferences("TradePrefs", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("registered_nim", nim);
                    editor.putString("registered_password", password);
                    editor.apply();

                    Toast.makeText(RegisterActivity.this, "Registrasi Sukses! Silakan Login.", Toast.LENGTH_SHORT).show();

                    // Pindah ke halaman Login
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                    finish();
                }
            }
        });

        // Tombol pindah ke Login jika sudah punya akun
        tvGoToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
            }
        });
    }
}