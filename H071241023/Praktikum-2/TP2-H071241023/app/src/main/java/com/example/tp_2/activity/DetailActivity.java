package com.example.tp_2.activity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tp_2.R;
import com.example.tp_2.model.Feed;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView btnBack = findViewById(R.id.btnBack);
        ImageView imgDetailPost = findViewById(R.id.imgPost);
        TextView tvDetailUsername = findViewById(R.id.tvUsername);
        TextView tvCaption = findViewById(R.id.tvCaption);

        btnBack.setOnClickListener(v -> finish());

        Feed feed;

        if (android.os.Build.VERSION.SDK_INT >= 33) {
            feed = getIntent().getParcelableExtra("detail_feed", Feed.class);
        } else {
            feed = getIntent().getParcelableExtra("detail_feed");
        }

        if (feed != null) {
            Glide.with(this)
                    .load(Uri.parse(feed.getImageUri()))
                    .into(imgDetailPost);
            tvDetailUsername.setText(feed.getUsername());
            tvCaption.setText(feed.getCaption());
        }
    }
}
