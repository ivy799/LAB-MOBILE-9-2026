package com.example.tp02.ui;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.tp02.R;
import com.example.tp02.model.DataManager;
import com.example.tp02.model.Story;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class StoryDetailActivity extends AppCompatActivity {

    private ImageView imgStory;
    private TextView tvUsername, tvTimeAgo, tvTitle;
    private CircleImageView imgProfile;
    private ImageView btnClose;
    private ProgressBar progressBar;
    private Handler handler = new Handler();
    private Runnable progressRunnable;
    private int progress = 0;
    private static final int STORY_DURATION = 5000;
    private static final int INTERVAL = 50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_detail);

        int storyId = getIntent().getIntExtra("story_id", -1);
        int storyPosition = getIntent().getIntExtra("story_position", 0);

        imgStory = findViewById(R.id.imgStory);
        tvUsername = findViewById(R.id.tvUsername);
        tvTimeAgo = findViewById(R.id.tvTimeAgo);
        tvTitle = findViewById(R.id.tvTitle);
        imgProfile = findViewById(R.id.imgProfile);
        btnClose = findViewById(R.id.btnClose);
        progressBar = findViewById(R.id.progressBar);

        Story story = findStoryById(storyId);

        if (story != null) {
            if (story.getCoverImageUri() != null) {
                Glide.with(this).load(story.getCoverImageUri()).centerCrop().into(imgStory);
            } else {
                Glide.with(this).load(story.getCoverImageRes()).centerCrop().into(imgStory);
            }
            tvUsername.setText(story.getUsername());
            tvTitle.setText(story.getTitle());
            tvTimeAgo.setText("2h ago");
            Glide.with(this).load(DataManager.getInstance().getCurrentUser().getProfileImageRes()).into(imgProfile);
        }

        startProgressBar();

        btnClose.setOnClickListener(v -> finish());
    }

    private void startProgressBar() {
        progressBar.setMax(100);
        progress = 0;
        progressRunnable = new Runnable() {
            @Override
            public void run() {
                progress += (int) (100.0 / (STORY_DURATION / INTERVAL));
                progressBar.setProgress(progress);
                if (progress < 100) {
                    handler.postDelayed(this, INTERVAL);
                } else {
                    finish();
                }
            }
        };
        handler.postDelayed(progressRunnable, INTERVAL);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(progressRunnable);
    }

    private Story findStoryById(int id) {
        List<Story> stories = DataManager.getInstance().getStories();
        for (Story s : stories) {
            if (s.getId() == id) return s;
        }
        return null;
    }
}