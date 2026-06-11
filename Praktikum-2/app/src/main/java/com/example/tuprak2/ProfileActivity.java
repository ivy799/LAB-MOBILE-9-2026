package com.example.tuprak2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tuprak2.adapter.HighlightAdapter;
import com.example.tuprak2.adapter.PostGridAdapter;
import com.example.tuprak2.data.DataDummy;
import com.example.tuprak2.model.PostModel;
import com.example.tuprak2.model.StoryModel;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {

    private RecyclerView rvHighlights, rvPosts;
    private HighlightAdapter highlightAdapter;
    private PostGridAdapter postAdapter;
    private TextView tvPostsCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);

        // Setup Highlights RecyclerView (Horizontal)
        rvHighlights = findViewById(R.id.rvHighlights);
        rvHighlights.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        
        ArrayList<StoryModel> stories = DataDummy.generateStories();
        highlightAdapter = new HighlightAdapter(this, stories);
        rvHighlights.setAdapter(highlightAdapter);

        // Setup Posts RecyclerView (Grid)
        rvPosts = findViewById(R.id.rvPosts);
        rvPosts.setLayoutManager(new GridLayoutManager(this, 3));
        
        tvPostsCount = findViewById(R.id.tvPostsCount);

        // Back button
        findViewById(R.id.btnBack).setOnClickListener(v -> finish());

        // Setup Bottom Navigation
        findViewById(R.id.navHome).setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        });

        findViewById(R.id.navAdd).setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, PostActivity.class);
            startActivity(intent);
        });

        // Set visual active state for Profile
        findViewById(R.id.navProfile).setAlpha(1.0f);
        findViewById(R.id.navHome).setAlpha(0.4f);
        findViewById(R.id.navAdd).setAlpha(0.4f);

    }

    @Override
    protected void onResume() {
        super.onResume();
        // Refresh grid and counts in case a new post was added
        ArrayList<PostModel> posts = DataDummy.generatePosts();
        postAdapter = new PostGridAdapter(this, posts);
        rvPosts.setAdapter(postAdapter);
        tvPostsCount.setText(String.valueOf(posts.size()));
    }
}
