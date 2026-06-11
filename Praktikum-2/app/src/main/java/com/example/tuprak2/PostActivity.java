package com.example.tuprak2;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.tuprak2.data.DataDummy;
import com.example.tuprak2.model.PostModel;

public class PostActivity extends AppCompatActivity {

    private EditText etCaption;
    private TextView btnShare;
    private ImageView imgPreview;
    
    // We start with a placeholder, then select random Picsum URLs
    private String currentSelectedImageUrl = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        etCaption = findViewById(R.id.etCaption);
        btnShare = findViewById(R.id.btnShare);
        imgPreview = findViewById(R.id.imgPreview);
        
        // Initial load placeholder
        Glide.with(this)
             .load(R.drawable.placeholder_image)
             .into(imgPreview);

        findViewById(R.id.btnCancel).setOnClickListener(v -> finish());
        
        // Setup mock image selection
        imgPreview.setOnClickListener(v -> {
            // Generate a random remote image URL using currentTimeMillis
            currentSelectedImageUrl = "https://picsum.photos/300?random=" + System.currentTimeMillis();
            
            Glide.with(this)
                 .load(currentSelectedImageUrl)
                 .placeholder(R.drawable.placeholder_image)
                 .error(R.drawable.placeholder_image)
                 .into(imgPreview);
                 
            Toast.makeText(this, "Image selected!", Toast.LENGTH_SHORT).show();
        });

        btnShare.setOnClickListener(v -> {
            String caption = etCaption.getText().toString();
            
            // If user hasn't selected an image, fallback to a random one
            if (currentSelectedImageUrl == null) {
                currentSelectedImageUrl = "https://picsum.photos/300?random=" + System.currentTimeMillis();
            }
            
            // Add to dummy data with the selected image URL
            PostModel newPost = new PostModel(currentSelectedImageUrl, "myprofile", caption);
            DataDummy.addPost(newPost);
            
            Toast.makeText(PostActivity.this, "Posted!", Toast.LENGTH_SHORT).show();
            
            // Go back
            finish();
        });
    }
}
