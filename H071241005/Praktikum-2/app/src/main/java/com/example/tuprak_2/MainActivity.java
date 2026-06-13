package com.example.tuprak_2;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rvHome = findViewById(R.id.rv_home);
        rvHome.setLayoutManager(new LinearLayoutManager(this));

        FeedAdapter adapter = new FeedAdapter(DataSource.getHomeFeeds());
        rvHome.setAdapter(adapter);

        // Contoh navigasi ke Profile (Jika ada tombol di layout)
        // findViewById(R.id.btn_profile).setOnClickListener(v -> {
        //     startActivity(new Intent(this, ProfileActivity.class));
        // });
    }
}