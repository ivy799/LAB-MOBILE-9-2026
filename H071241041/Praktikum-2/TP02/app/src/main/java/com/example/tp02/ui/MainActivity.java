package com.example.tp02.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tp02.R;
import com.example.tp02.adapter.FeedAdapter;
import com.example.tp02.model.DataManager;
import com.example.tp02.model.Post;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvFeed;
    private FeedAdapter feedAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvFeed = findViewById(R.id.rvFeed);

        ImageView btnCamera = findViewById(R.id.btnCamera);
        ImageView btnProfile = findViewById(R.id.btnProfile);
        ImageView btnHome = findViewById(R.id.btnHome);
        ImageView btnSearch = findViewById(R.id.btnSearch);
        ImageView btnAdd = findViewById(R.id.btnAdd);
        ImageView btnReels = findViewById(R.id.btnReels);

        setupFeed();

        btnProfile.setOnClickListener(v -> {
            startActivity(new Intent(this, ProfileActivity.class));
        });

        btnAdd.setOnClickListener(v -> {
            startActivity(new Intent(this, PostActivity.class));
        });

        btnHome.setOnClickListener(v -> {});
        btnSearch.setOnClickListener(v -> {});
        btnReels.setOnClickListener(v -> {});
    }

    private void setupFeed() {
        feedAdapter = new FeedAdapter(this, DataManager.getInstance().getHomeFeedPosts(), new FeedAdapter.OnPostClickListener() {
            @Override
            public void onProfileClick(Post post) {
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                intent.putExtra("username", post.getUsername());
                startActivity(intent);
            }

            @Override
            public void onPostClick(Post post) {
                Intent intent = new Intent(MainActivity.this, FeedDetailActivity.class);
                intent.putExtra("post_id", post.getId());
                startActivity(intent);
            }

            @Override
            public void onLikeClick(Post post, int position) {
                post.setLiked(!post.isLiked());
                if (post.isLiked()) {
                    post.setLikeCount(post.getLikeCount() + 1);
                } else {
                    post.setLikeCount(post.getLikeCount() - 1);
                }
            }

            @Override
            public void onBookmarkClick(Post post, int position) {
                post.setBookmarked(!post.isBookmarked());
            }
        });

        rvFeed.setLayoutManager(new LinearLayoutManager(this));
        rvFeed.setAdapter(feedAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (feedAdapter != null) {
            feedAdapter.notifyDataSetChanged();
        }
    }
}