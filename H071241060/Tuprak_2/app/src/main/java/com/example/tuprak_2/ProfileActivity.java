package com.example.tuprak_2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.recycleview.adapter.ProfileGridAdapter;
import com.example.recycleview.adapter.StoryAdapter;
import com.example.recycleview.model.DataDummy;
import com.example.recycleview.model.Post;
import com.example.recycleview.model.Story;
import com.example.recycleview.model.User;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    public static final String EXTRA_NEW_POST = "NEW_POST";
    public static final int REQUEST_POST = 100;

    private User currentUser;
    private ProfileGridAdapter gridAdapter;
    private RecyclerView rvHighlights, rvGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        String userId = getIntent().getStringExtra("USER_ID");
        loadUser(userId);
        setupToolbar();
        populateHeader();
        setupHighlights();
        setupGrid();
        setupPostButton();
    }

    private void loadUser(String userId) {
        List<User> users = DataDummy.getUsers();
        currentUser = users.get(0); // default
        for (User u : users) {
            if (u.getUserId().equals(userId)) {
                currentUser = u;
                break;
            }
        }
    }

    private void setupToolbar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(currentUser.getUsername());
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void populateHeader() {
        ImageView ivProfile     = findViewById(R.id.iv_profile_large);
        TextView tvFullName     = findViewById(R.id.tv_full_name);
        TextView tvBio          = findViewById(R.id.tv_bio);
        TextView tvPostCount    = findViewById(R.id.tv_post_count);
        TextView tvFollowers    = findViewById(R.id.tv_follower_count);
        TextView tvFollowing    = findViewById(R.id.tv_following_count);

        Glide.with(this).load(currentUser.getProfileImageRes()).circleCrop().into(ivProfile);
        tvFullName.setText(currentUser.getFullName());
        tvBio.setText(currentUser.getBio());
        tvPostCount.setText(String.valueOf(currentUser.getPostCount()));
        tvFollowers.setText(currentUser.getFormattedFollowers());
        tvFollowing.setText(String.valueOf(currentUser.getFollowingCount()));
    }

    private void setupHighlights() {
        rvHighlights = findViewById(R.id.rv_highlights);
        rvHighlights.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        StoryAdapter storyAdapter = new StoryAdapter(this, currentUser.getHighlights(),
                story -> {
                    Intent intent = new Intent(this, DetailStoryActivity.class);
                    intent.putExtra("STORY", story);
                    startActivity(intent);
                });
        rvHighlights.setAdapter(storyAdapter);
    }

    private void setupGrid() {
        rvGrid = findViewById(R.id.rv_grid);
        rvGrid.setLayoutManager(new GridLayoutManager(this, 3));
        rvGrid.setHasFixedSize(false);

        gridAdapter = new ProfileGridAdapter(this, currentUser.getPosts(),
                post -> {
                    Intent intent = new Intent(this, DetailFeedActivity.class);
                    intent.putExtra("POST", post);
                    startActivity(intent);
                });
        rvGrid.setAdapter(gridAdapter);
    }

    private void setupPostButton() {
        findViewById(R.id.btn_new_post).setOnClickListener(v -> {
            Intent intent = new Intent(this, PostActivity.class);
            intent.putExtra("USER_ID", currentUser.getUserId());
            startActivityForResult(intent, REQUEST_POST);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_POST && resultCode == RESULT_OK && data != null) {
            Post newPost = (Post) data.getSerializableExtra(EXTRA_NEW_POST);
            if (newPost != null) {
                currentUser.addPost(newPost);
                gridAdapter.addPost(newPost);
                // update post count
                TextView tvPostCount = findViewById(R.id.tv_post_count);
                tvPostCount.setText(String.valueOf(currentUser.getPostCount()));
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
