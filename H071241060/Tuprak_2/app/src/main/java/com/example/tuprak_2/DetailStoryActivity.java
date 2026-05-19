package com.example.tuprak_2;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.example.recycleview.model.Story;

public class DetailStoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_story);

        Story story = (Story) getIntent().getSerializableExtra("STORY");
        if (story == null) { finish(); return; }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(story.getTitle());
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        ImageView ivStory  = findViewById(R.id.iv_story_full);
        TextView  tvTitle  = findViewById(R.id.tv_story_title_detail);
        ImageView btnClose = findViewById(R.id.btn_close_story);

        tvTitle.setText(story.getTitle());
        Glide.with(this).load(story.getImageRes()).centerCrop().into(ivStory);

        btnClose.setOnClickListener(v -> finish());
        ivStory.setOnClickListener(v -> finish());
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
