package com.example.tuprak2;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class StoryDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_detail);

        String title = getIntent().getStringExtra("STORY_TITLE");
        String imageUrl = getIntent().getStringExtra("IMAGE_URL");
        if (title == null) title = "Story";

        TextView tvStoryTitle = findViewById(R.id.tvStoryTitle);
        ImageView imgStory = findViewById(R.id.imgStory);
        
        tvStoryTitle.setText(title);
        
        if (imageUrl != null) {
            Glide.with(this)
                 .load(imageUrl)
                 .placeholder(R.drawable.placeholder_image)
                 .error(R.drawable.placeholder_image)
                 .into(imgStory);
        }

        findViewById(R.id.btnClose).setOnClickListener(v -> finish());
    }
}
