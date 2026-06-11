package com.example.tuprak2;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tuprak2.adapter.FeedAdapter;
import com.example.tuprak2.data.DataDummy;
import com.example.tuprak2.model.FeedModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvFeed;
    private FeedAdapter feedAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        
        // Handle window insets securely
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.topBar), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0); // only top padding
            return insets;
        });

        // Setup Feed RecyclerView
        rvFeed = findViewById(R.id.rvFeed);
        rvFeed.setLayoutManager(new LinearLayoutManager(this));
        
        ArrayList<FeedModel> feeds = DataDummy.generateFeed();
        feedAdapter = new FeedAdapter(this, feeds);
        rvFeed.setAdapter(feedAdapter);

        // Setup Bottom Navigation
        findViewById(R.id.navHome).setAlpha(1.0f);
        findViewById(R.id.navProfile).setAlpha(0.4f);
        findViewById(R.id.navAdd).setAlpha(0.4f);

        findViewById(R.id.navAdd).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, PostActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.navProfile).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        });
    }
}