package com.example.tp2.activities;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tp2.R;
import com.example.tp2.models.Feed;

public class DetailFeedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_feed);

        ImageView ivProfile = findViewById(R.id.iv_detail_profile);
        TextView tvUsername = findViewById(R.id.tv_detail_username);
        ImageView ivFeed = findViewById(R.id.iv_detail_feed);
        TextView tvCaptionUser = findViewById(R.id.tv_detail_caption_user);
        TextView tvCaption = findViewById(R.id.tv_detail_caption);

        // Mengambil data Feed yang dikirim dari ProfileFeedAdapter
        Feed feed = getIntent().getParcelableExtra("EXTRA_FEED");

        if (feed != null) {
            ivProfile.setImageResource(feed.getProfileImage());
            tvUsername.setText(feed.getUsername());
            ivFeed.setImageResource(feed.getFeedImage());
            tvCaptionUser.setText(feed.getUsername());
            tvCaption.setText(feed.getCaption());
        }
    }
}