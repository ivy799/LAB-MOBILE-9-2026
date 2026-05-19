package com.example.tuprak_2;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.example.recycleview.model.Post;

public class DetailFeedActivity extends AppCompatActivity {

    private Post post;
    private boolean isLiked;
    private int likeCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_feed);

        post = (Post) getIntent().getSerializableExtra("POST");
        if (post == null) { finish(); return; }

        isLiked   = post.isLiked();
        likeCount = post.getLikeCount();

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(post.getUsername());
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        bindViews();
    }

    private void bindViews() {
        ImageView ivProfile  = findViewById(R.id.iv_profile);
        ImageView ivPost     = findViewById(R.id.iv_post_full);
        ImageView ivLike     = findViewById(R.id.iv_like);
        ImageView ivComment  = findViewById(R.id.iv_comment);
        ImageView ivShare    = findViewById(R.id.iv_share);
        ImageView ivBookmark = findViewById(R.id.iv_bookmark);
        TextView tvUsername  = findViewById(R.id.tv_username);
        TextView tvCaption   = findViewById(R.id.tv_caption_full);
        TextView tvLikeCount = findViewById(R.id.tv_like_count);
        TextView tvComments  = findViewById(R.id.tv_comment_count);
        TextView tvTimeAgo   = findViewById(R.id.tv_time_ago);

        tvUsername.setText(post.getUsername());
        tvCaption.setText(post.getCaption());
        tvLikeCount.setText(likeCount + " suka");
        tvComments.setText(post.getCommentCount() + " komentar");
        tvTimeAgo.setText(post.getTimeAgo());

        if (post.getProfileImageRes() > 0)
            Glide.with(this).load(post.getProfileImageRes()).circleCrop().into(ivProfile);

        if (post.hasUri())
            Glide.with(this).load(post.getImageUri()).centerCrop().into(ivPost);
        else if (post.getImageRes() > 0)
            Glide.with(this).load(post.getImageRes()).centerCrop().into(ivPost);

        updateLikeIcon(ivLike, tvLikeCount);

        ivLike.setOnClickListener(v -> {
            isLiked = !isLiked;
            likeCount += isLiked ? 1 : -1;
            updateLikeIcon(ivLike, tvLikeCount);
        });
    }

    private void updateLikeIcon(ImageView ivLike, TextView tvLikeCount) {
        ivLike.setImageResource(isLiked
                ? R.drawable.ic_heart_filled
                : R.drawable.ic_heart_outline);
        tvLikeCount.setText(likeCount + " suka");
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
