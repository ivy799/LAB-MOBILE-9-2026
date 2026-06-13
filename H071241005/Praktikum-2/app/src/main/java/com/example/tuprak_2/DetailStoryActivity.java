package com.example.tuprak_2;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DetailStoryActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_story);

        Highlight highlight = (Highlight) getIntent().getSerializableExtra("highlight");

        if (highlight != null) {
            ImageView ivStoryFull = findViewById(R.id.iv_story_full);
            TextView tvStoryTitle = findViewById(R.id.tv_story_title);

            ivStoryFull.setImageResource(highlight.getCoverImage());
            tvStoryTitle.setText(highlight.getTitle());
        }
    }
}
