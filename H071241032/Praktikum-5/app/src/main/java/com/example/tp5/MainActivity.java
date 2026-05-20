package com.example.tp5;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView tvWelcome;
    private Button btnLogout, btnSettings;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvWelcome = findViewById(R.id.tvWelcome);
        btnLogout = findViewById(R.id.btnLogout);
        btnSettings = findViewById(R.id.btnSettings);

        // Buka file SharedPreferences yang sama
        sharedPreferences = getSharedPreferences("TradePrefs", MODE_PRIVATE);

        // Ambil NIM dari SharedPreferences untuk ditampilkan di sapaan
        String nim = sharedPreferences.getString("registered_nim", "Trader");
        tvWelcome.setText("Halo, Trader " + nim + "!");

        // Aksi Tombol Settings (Untuk pindah ke halaman ubah tema)
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
            }
        });

        // Aksi Tombol Logout
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ubah status isLoggedIn menjadi false (data registrasi tidak dihapus)
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("isLoggedIn", false);
                editor.apply();

                Toast.makeText(MainActivity.this, "Berhasil Logout", Toast.LENGTH_SHORT).show();

                // Kembali ke halaman Login
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish(); // Tutup MainActivity agar tidak bisa di-back
            }
        });
    }
}