package com.example.tp2.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tp2.R;
// Dua baris di bawah ini adalah kuncinya (untuk menghilangkan tulisan merah)
import com.example.tp2.adapters.FeedAdapter;
import com.example.tp2.data.DataSource;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inisiasi RecyclerView
        RecyclerView rvHomeFeeds = findViewById(R.id.rv_home_feeds);

        // Menggunakan LinearLayoutManager agar list tersusun ke bawah (vertikal)
        rvHomeFeeds.setLayoutManager(new LinearLayoutManager(this));

        // Menghubungkan adapter dengan data dummy dari DataSource
        FeedAdapter adapter = new FeedAdapter(this, DataSource.getHomeFeeds());
        rvHomeFeeds.setAdapter(adapter);
    }
}