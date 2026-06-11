package com.example.tuprak2;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class DetailPostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_post);

        String caption = getIntent().getStringExtra("POST_CAPTION");
        String username = getIntent().getStringExtra("POST_USERNAME");
        String imageUrl = getIntent().getStringExtra("IMAGE_URL");

        if (caption == null) caption = "Caption";
        if (username == null) username = "myprofile";

        TextView tvUsername = findViewById(R.id.tvUsername);
        TextView tvCaptionUsername = findViewById(R.id.tvCaptionUsername);
        TextView tvCaption = findViewById(R.id.tvCaption);
        ImageView imgProfile = findViewById(R.id.imgProfile);
        ImageView imgPost = findViewById(R.id.imgPost);
        
        if (tvUsername != null) tvUsername.setText(username);
        if (tvCaptionUsername != null) tvCaptionUsername.setText(username);
        if (tvCaption != null) tvCaption.setText(caption);

        if (imgPost != null && imageUrl != null) {
            Glide.with(this)
                 .load(imageUrl)
                 .placeholder(R.drawable.placeholder_image)
                 .error(R.drawable.placeholder_image)
                 .into(imgPost);
        }
        if (imgProfile != null) {
            Glide.with(this).load(R.drawable.placeholder_profile).circleCrop().into(imgProfile);
        }

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
    }
}
