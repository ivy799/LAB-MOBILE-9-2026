package com.example.tp02.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tp02.R;
import com.example.tp02.adapter.GridSpacingItemDecoration;
import com.example.tp02.adapter.ProfileGridAdapter;
import com.example.tp02.adapter.StoryAdapter;
import com.example.tp02.model.DataManager;
import com.example.tp02.model.Post;
import com.example.tp02.model.Story;
import com.example.tp02.model.User;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {

    private RecyclerView rvHighlights, rvGrid;
    private ProfileGridAdapter gridAdapter;
    private StoryAdapter storyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        rvHighlights = findViewById(R.id.rvHighlights);
        rvGrid = findViewById(R.id.rvGrid);

        ImageView btnBack = findViewById(R.id.btnBack);
        ImageView btnMenu = findViewById(R.id.btnMenu);
        ImageView btnAdd = findViewById(R.id.btnAdd);
        ImageView btnHome = findViewById(R.id.btnHome);
        ImageView btnSearch = findViewById(R.id.btnSearch);
        ImageView btnReels = findViewById(R.id.btnReels);
        ImageView btnProfile = findViewById(R.id.btnProfile);

        CircleImageView imgProfile = findViewById(R.id.imgProfile);
        TextView tvUsernameTop = findViewById(R.id.tvUsernameTop);
        TextView tvUsername = findViewById(R.id.tvUsername);
        TextView tvFullName = findViewById(R.id.tvFullName);
        TextView tvBio = findViewById(R.id.tvBio);
        TextView tvPostCount = findViewById(R.id.tvPostCount);
        TextView tvFollowerCount = findViewById(R.id.tvFollowerCount);
        TextView tvFollowingCount = findViewById(R.id.tvFollowingCount);

        User user = DataManager.getInstance().getCurrentUser();
        Glide.with(this).load(user.getProfileImageRes()).into(imgProfile);
        tvUsernameTop.setText(user.getUsername());
        tvUsername.setText(user.getUsername());
        tvFullName.setText(user.getFullName());
        tvBio.setText(user.getBio());
        tvPostCount.setText(String.valueOf(user.getPostCount()));
        tvFollowerCount.setText(formatCount(user.getFollowerCount()));
        tvFollowingCount.setText(String.valueOf(user.getFollowingCount()));

        setupHighlights();
        setupGrid();

        btnBack.setOnClickListener(v -> finish());
        btnHome.setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });
        btnAdd.setOnClickListener(v -> startActivity(new Intent(this, PostActivity.class)));
        btnSearch.setOnClickListener(v -> {});
        btnReels.setOnClickListener(v -> {});
        btnProfile.setOnClickListener(v -> {});
        btnMenu.setOnClickListener(v -> {});
    }

    private String formatCount(int count) {
        if (count >= 1000) {
            return String.format("%.1fK", count / 1000.0);
        }
        return String.valueOf(count);
    }

    private void setupHighlights() {
        storyAdapter = new StoryAdapter(this, DataManager.getInstance().getStories(), (story, position) -> {
            Intent intent = new Intent(this, StoryDetailActivity.class);
            intent.putExtra("story_id", story.getId());
            intent.putExtra("story_position", position);
            startActivity(intent);
        });
        rvHighlights.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvHighlights.setAdapter(storyAdapter);
    }

    private void setupGrid() {
        gridAdapter = new ProfileGridAdapter(this, DataManager.getInstance().getProfilePosts(), (post, position) -> {
            Intent intent = new Intent(this, FeedDetailActivity.class);
            intent.putExtra("post_id", post.getId());
            startActivity(intent);
        });
        rvGrid.setLayoutManager(new GridLayoutManager(this, 3));
        rvGrid.addItemDecoration(new GridSpacingItemDecoration(3, 3));
        rvGrid.setAdapter(gridAdapter);
        rvGrid.setNestedScrollingEnabled(false);
    }

    @Override
    protected void onResume() {
        super.onResume();
        User user = DataManager.getInstance().getCurrentUser();
        TextView tvPostCount = findViewById(R.id.tvPostCount);
        tvPostCount.setText(String.valueOf(user.getPostCount()));
        if (gridAdapter != null) {
            gridAdapter.notifyDataSetChanged();
        }
    }
}