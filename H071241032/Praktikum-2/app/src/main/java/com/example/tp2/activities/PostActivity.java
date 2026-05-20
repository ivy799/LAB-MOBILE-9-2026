package com.example.tp2.activities;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tp2.R;
import com.example.tp2.data.DataSource;
import com.example.tp2.models.Feed;

public class PostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        TextView tvShare = findViewById(R.id.tv_share);
        EditText etCaption = findViewById(R.id.et_caption);

        // Aksi ketika tombol "Share" di pojok kanan atas diklik
        tvShare.setOnClickListener(v -> {
            String captionText = etCaption.getText().toString();

            if (captionText.isEmpty()) {
                Toast.makeText(this, "Caption tidak boleh kosong!", Toast.LENGTH_SHORT).show();
            } else {
                // 1. Membuat objek Feed baru (menggunakan gambar bawaan sebagai simulasi)
                Feed newFeed = new Feed(
                        R.drawable.ic_launcher_background,
                        "Didit",
                        R.drawable.ic_launcher_foreground,
                        captionText
                );

                // 2. Memasukkan feed baru ke dalam DataSource (di posisi paling depan / index 0)
                DataSource.profileFeeds.add(0, newFeed);

                // 3. Menampilkan pesan sukses
                Toast.makeText(this, "Postingan berhasil diunggah!", Toast.LENGTH_SHORT).show();

                // 4. Menutup halaman ini dan kembali ke halaman Profile
                finish();
            }
        });
    }
}