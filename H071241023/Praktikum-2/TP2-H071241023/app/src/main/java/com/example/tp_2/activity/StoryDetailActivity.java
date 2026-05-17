package com.example.tp_2.activity;


import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tp_2.R;
import com.example.tp_2.model.Story;

public class StoryDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_detail);

        ImageView imgStoryFull = findViewById(R.id.imgStoryFull);
        ImageView btnStoryClose = findViewById(R.id.btnStoryClose);
        TextView tvStoryUsername = findViewById(R.id.tvStoryUsername);

        btnStoryClose.setOnClickListener(v -> finish());

        Story story = getIntent().getParcelableExtra("story_data");

        if (story != null) {
            imgStoryFull.setImageResource(story.getImage());
            tvStoryUsername.setText(story.getName());
        }
    }
}
