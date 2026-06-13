package com.example.tuprak_2;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DetailFeedActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_feed);

        Post post = (Post) getIntent().getSerializableExtra("post");

        if (post != null) {
            ImageView ivProfPic = findViewById(R.id.iv_detail_prof_pic);
            TextView tvUsername = findViewById(R.id.tv_detail_username);
            ImageView ivPostImage = findViewById(R.id.iv_detail_post_image);
            TextView tvCaptionUsername = findViewById(R.id.tv_detail_caption_username);
            TextView tvCaption = findViewById(R.id.tv_detail_caption);

            ivProfPic.setImageResource(post.getProfileImage());
            tvUsername.setText(post.getUsername());
            tvCaptionUsername.setText(post.getUsername());
            tvCaption.setText(post.getCaption());

            // Handle image from Resource or Uri
            if (post.getPostImageResource() != -1) {
                ivPostImage.setImageResource(post.getPostImageResource());
            } else if (post.getPostImageUri() != null) {
                ivPostImage.setImageURI(Uri.parse(post.getPostImageUri()));
            }
        }
    }
}
