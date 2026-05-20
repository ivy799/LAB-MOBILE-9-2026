package com.example.tp2.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tp2.R;
import com.example.tp2.adapters.HighlightAdapter;
import com.example.tp2.adapters.ProfileFeedAdapter;
import com.example.tp2.data.DataSource;
import com.example.tp2.models.Feed;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle Bundle) {
        super.onCreate(Bundle);
        setContentView(R.layout.activity_profile);

        TextView tvUsername = findViewById(R.id.tv_profile_username);
        ImageView ivAvatar = findViewById(R.id.iv_profile_avatar);
        ImageView btnAddPost = findViewById(R.id.btn_add_post);
        RecyclerView rvHighlights = findViewById(R.id.rv_highlights);
        RecyclerView rvProfileFeeds = findViewById(R.id.rv_profile_feeds);

        TextView tvPostsCount = findViewById(R.id.tv_profile_posts);
        TextView tvFollowersCount = findViewById(R.id.tv_profile_followers);
        TextView tvFollowingCount = findViewById(R.id.tv_profile_following);

        String clickedUsername = getIntent().getStringExtra("EXTRA_USERNAME");
        int clickedProfileImage = getIntent().getIntExtra("EXTRA_PROFILE_IMAGE", -1);

        ArrayList<Feed> feedsToDisplay = new ArrayList<>();
        String currentProfileUser = "";

        if (clickedUsername != null && !clickedUsername.equals("didit_iqbal")) {
            currentProfileUser = clickedUsername;
            tvUsername.setText(clickedUsername);
            ivAvatar.setImageResource(clickedProfileImage);

            if (btnAddPost != null) btnAddPost.setVisibility(View.GONE);

            for (Feed f : DataSource.getHomeFeeds()) {
                if (f.getUsername().equals(clickedUsername)) {
                    feedsToDisplay.add(f);
                }
            }

            // Menerapkan angka Followers & Following unik untuk geng barumu
            switch (clickedUsername) {
                case "ryan":
                    tvFollowersCount.setText("1.4K\nFollowers");
                    tvFollowingCount.setText("480\nFollowing");
                    break;
                case "sammi":
                    tvFollowersCount.setText("920\nFollowers");
                    tvFollowingCount.setText("185\nFollowing");
                    break;
                case "puad":
                    tvFollowersCount.setText("615\nFollowers");
                    tvFollowingCount.setText("520\nFollowing");
                    break;
                case "yoga":
                    tvFollowersCount.setText("1.2K\nFollowers");
                    tvFollowingCount.setText("310\nFollowing");
                    break;
                case "najib":
                    tvFollowersCount.setText("3.8K\nFollowers");
                    tvFollowingCount.setText("95\nFollowing");
                    break;
                default:
                    tvFollowersCount.setText("500\nFollowers");
                    tvFollowingCount.setText("200\nFollowing");
                    break;
            }

        } else {
            currentProfileUser = "didit_iqbal";
            tvUsername.setText(currentProfileUser);
            ivAvatar.setImageResource(R.drawable.profil_didit);
            if (btnAddPost != null) btnAddPost.setVisibility(View.VISIBLE);

            DataSource.initProfileFeeds();
            feedsToDisplay = DataSource.profileFeeds;

            tvFollowersCount.setText("1.2K\nFollowers");
            tvFollowingCount.setText("300\nFollowing");
        }

        // Menghitung otomatis jumlah post berdasarkan list data
        tvPostsCount.setText(feedsToDisplay.size() + "\nPosts");

        rvHighlights.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvHighlights.setAdapter(new HighlightAdapter(this, DataSource.getHighlights(currentProfileUser)));

        rvProfileFeeds.setLayoutManager(new GridLayoutManager(this, 3));
        rvProfileFeeds.setAdapter(new ProfileFeedAdapter(this, feedsToDisplay));

        if (btnAddPost != null) {
            btnAddPost.setOnClickListener(v -> {
                Intent intent = new Intent(ProfileActivity.this, PostActivity.class);
                startActivity(intent);
            });
        }
    }
}