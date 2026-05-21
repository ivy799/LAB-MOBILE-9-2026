package com.example.tp02.ui;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.tp02.R;
import com.example.tp02.model.DataManager;
import com.example.tp02.model.Post;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class FeedDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_detail);

        int postId = getIntent().getIntExtra("post_id", -1);

        ImageView btnBack = findViewById(R.id.btnBack);
        ImageView btnMore = findViewById(R.id.btnMore);
        CircleImageView imgProfile = findViewById(R.id.imgProfile);
        ImageView imgPost = findViewById(R.id.imgPost);
        ImageView btnLike = findViewById(R.id.btnLike);
        ImageView btnComment = findViewById(R.id.btnComment);
        ImageView btnShare = findViewById(R.id.btnShare);
        ImageView btnBookmark = findViewById(R.id.btnBookmark);
        TextView tvUsername = findViewById(R.id.tvUsername);
        TextView tvUsernameCaption = findViewById(R.id.tvUsernameCaption);
        TextView tvCaption = findViewById(R.id.tvCaption);
        TextView tvLikeCount = findViewById(R.id.tvLikeCount);
        TextView tvTimeAgo = findViewById(R.id.tvTimeAgo);

        Post post = findPostById(postId);

        if (post != null) {
            if (post.getProfileImageUri() != null) {
                Glide.with(this).load(post.getProfileImageUri()).into(imgProfile);
            } else {
                Glide.with(this).load(post.getProfileImageRes()).into(imgProfile);
            }

            if (post.getPostImageUri() != null) {
                Glide.with(this).load(post.getPostImageUri()).into(imgPost);
            } else {
                Glide.with(this).load(post.getPostImageRes()).centerCrop().into(imgPost);
            }

            tvUsername.setText(post.getUsername());
            tvUsernameCaption.setText(post.getUsername());
            tvCaption.setText(post.getCaption());
            tvLikeCount.setText(post.getLikeCount() + " likes");
            tvTimeAgo.setText(post.getTimeAgo());

            btnLike.setImageResource(post.isLiked() ? R.drawable.ic_heart_filled : R.drawable.ic_heart);
            btnBookmark.setImageResource(post.isBookmarked() ? R.drawable.ic_bookmark_filled : R.drawable.ic_bookmark);

            btnLike.setOnClickListener(v -> {
                post.setLiked(!post.isLiked());
                if (post.isLiked()) post.setLikeCount(post.getLikeCount() + 1);
                else post.setLikeCount(post.getLikeCount() - 1);
                btnLike.setImageResource(post.isLiked() ? R.drawable.ic_heart_filled : R.drawable.ic_heart);
                tvLikeCount.setText(post.getLikeCount() + " likes");
            });

            btnBookmark.setOnClickListener(v -> {
                post.setBookmarked(!post.isBookmarked());
                btnBookmark.setImageResource(post.isBookmarked() ? R.drawable.ic_bookmark_filled : R.drawable.ic_bookmark);
            });
        }

        btnBack.setOnClickListener(v -> finish());
        btnMore.setOnClickListener(v -> {});
        btnComment.setOnClickListener(v -> {});
        btnShare.setOnClickListener(v -> {});
    }

    private Post findPostById(int id) {
        List<Post> home = DataManager.getInstance().getHomeFeedPosts();
        for (Post p : home) {
            if (p.getId() == id) return p;
        }
        List<Post> profile = DataManager.getInstance().getProfilePosts();
        for (Post p : profile) {
            if (p.getId() == id) return p;
        }
        return null;
    }
}