package com.example.tuprak_2;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.recycleview.adapter.FeedAdapter;
import com.example.recycleview.model.DataDummy;
import com.example.recycleview.model.Post;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvFeed;
    private FeedAdapter feedAdapter;
    private List<Post> feedPosts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupRecyclerView();
    }

    private void setupRecyclerView() {
        feedPosts = DataDummy.getHomeFeed();
        rvFeed = findViewById(R.id.rv_feed);
        rvFeed.setLayoutManager(new LinearLayoutManager(this));
        rvFeed.setHasFixedSize(true);

        feedAdapter = new FeedAdapter(this, feedPosts, new FeedAdapter.OnItemClickListener() {
            @Override
            public void onUserClick(String userId) {
                openProfile(userId);
            }

            @Override
            public void onPostClick(Post post) {
                openDetailFeed(post);
            }

            @Override
            public void onLikeClick(Post post, int position) {
                toggleLike(post, position);
            }
        });

        rvFeed.setAdapter(feedAdapter);
    }

    private void openProfile(String userId) {
        Intent intent = new Intent(this, ProfileActivity.class);
        intent.putExtra("USER_ID", userId);
        startActivity(intent);
    }

    private void openDetailFeed(Post post) {
        Intent intent = new Intent(this, DetailFeedActivity.class);
        intent.putExtra("POST", post);
        startActivity(intent);
    }

    private void toggleLike(Post post, int position) {
        boolean nowLiked = !post.isLiked();
        post.setLiked(nowLiked);
        post.setLikeCount(post.getLikeCount() + (nowLiked ? 1 : -1));
        feedAdapter.notifyLikeChanged(position);
    }
}
