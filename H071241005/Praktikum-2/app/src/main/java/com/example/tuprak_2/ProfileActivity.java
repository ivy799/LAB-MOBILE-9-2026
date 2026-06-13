package com.example.tuprak_2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ProfileActivity extends AppCompatActivity {
    RecyclerView rvGrid;
    TextView tvPostCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Setup Highlights (Horizontal Scroll)
        RecyclerView rvHighlights = findViewById(R.id.rv_highlights);
        rvHighlights.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvHighlights.setAdapter(new StoryAdapter(DataSource.getHighlights()));

        // Setup Grid Posts
        rvGrid = findViewById(R.id.rv_profile_grid);
        rvGrid.setLayoutManager(new GridLayoutManager(this, 3));

        tvPostCount = findViewById(R.id.tv_post_count);

        // Tombol Add Post (Ikon + di pojok kiri atas)
        ImageView btnAddPostTop = findViewById(R.id.btn_add_post_top);
        btnAddPostTop.setOnClickListener(v -> {
            startActivity(new Intent(ProfileActivity.this, AddPostActivity.class));
        });

        // Tombol Edit Profil (Juga diarahkan ke Add Post sebagai fitur tambahan)
        Button btnEditProfile = findViewById(R.id.btn_edit_profile);
        btnEditProfile.setOnClickListener(v -> {
            startActivity(new Intent(ProfileActivity.this, AddPostActivity.class));
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Update jumlah postingan secara dinamis
        if (tvPostCount != null) {
            tvPostCount.setText(String.valueOf(DataSource.profilePosts.size()));
        }
        // Refresh grid setiap kali kembali ke halaman ini agar post baru muncul
        rvGrid.setAdapter(new ProfileGridAdapter(DataSource.profilePosts));
    }
}
