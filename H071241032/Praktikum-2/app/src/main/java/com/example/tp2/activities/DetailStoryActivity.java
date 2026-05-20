package com.example.tp2.activities;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tp2.R;
import com.example.tp2.models.Highlight;

public class DetailStoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_story);

        ImageView ivStoryFull = findViewById(R.id.iv_story_full);
        TextView tvStoryTitle = findViewById(R.id.tv_story_title);

        // Mengambil data Highlight yang dikirim dari HighlightAdapter
        Highlight highlight = getIntent().getParcelableExtra("EXTRA_HIGHLIGHT");

        if (highlight != null) {
            ivStoryFull.setImageResource(highlight.getImageRaw());
            tvStoryTitle.setText(highlight.getTitle());
        }
    }
}