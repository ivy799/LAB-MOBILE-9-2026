package com.example.praktikum_3;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Book book = getIntent().getParcelableExtra("EXTRA_BOOK");
        if (book == null) {
            finish();
            return;
        }

        ImageView ivCover = findViewById(R.id.iv_detail_cover);
        TextView tvTitle = findViewById(R.id.tv_detail_title);
        TextView tvAuthor = findViewById(R.id.tv_detail_author);
        TextView tvYear = findViewById(R.id.tv_detail_year);
        TextView tvBlurb = findViewById(R.id.tv_detail_blurb);
        ImageButton btnLike = findViewById(R.id.btn_like);
        ImageButton btnBack = findViewById(R.id.btn_back);

        if (book.getImageUri() != null) {
            ivCover.setImageURI(book.getImageUri());
        } else {
            ivCover.setImageResource(book.getImageResId());
        }

        tvTitle.setText(book.getTitle());
        tvAuthor.setText(book.getAuthor());
        tvYear.setText(book.getYear());
        tvBlurb.setText(book.getBlurb());

        updateLikeButton(btnLike, book.isLiked());

        btnLike.setOnClickListener(v -> {
            book.setLiked(!book.isLiked());
            updateLikeButton(btnLike, book.isLiked());
            BookRepository.updateLikeStatus(book.getTitle(), book.isLiked());
        });

        btnBack.setOnClickListener(v -> finish());
    }

    private void updateLikeButton(ImageButton btn, boolean isLiked) {
        if (isLiked) {
            btn.setImageResource(android.R.drawable.btn_star_big_on);
        } else {
            btn.setImageResource(android.R.drawable.btn_star_big_off);
        }
    }
}
