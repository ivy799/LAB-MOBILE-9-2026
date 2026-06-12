package com.example.libraryapp;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.libraryapp.model.Book;
import com.example.libraryapp.util.BookRepository;
import com.google.android.material.chip.Chip;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DetailActivity extends AppCompatActivity {

    private Book book;
    private FloatingActionButton fabLike;
    private boolean isLiked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        int bookId = getIntent().getIntExtra("book_id", -1);
        book = BookRepository.getInstance().getBookById(bookId);
        if (book == null) {
            finish();
            return;
        }

        ImageView ivCover = findViewById(R.id.iv_detail_cover);
        TextView tvTitle = findViewById(R.id.tv_detail_title);
        TextView tvAuthor = findViewById(R.id.tv_detail_author);
        TextView tvYear = findViewById(R.id.tv_detail_year);
        TextView tvBlurb = findViewById(R.id.tv_detail_blurb);
        TextView tvReview = findViewById(R.id.tv_detail_review);
        RatingBar ratingBar = findViewById(R.id.detail_rating_bar);
        TextView tvRatingValue = findViewById(R.id.tv_rating_value);
        Chip chipGenre = findViewById(R.id.chip_genre);
        fabLike = findViewById(R.id.fab_like);
        ImageView ivBack = findViewById(R.id.iv_back);

        // Cover
        if (book.getCoverUri() != null) {
            ivCover.setImageURI(book.getCoverUri());
        } else if (book.getCoverResId() != 0) {
            ivCover.setImageResource(book.getCoverResId());
        } else {
            ivCover.setImageResource(R.drawable.cover_default);
        }

        tvTitle.setText(book.getTitle());
        tvAuthor.setText("by " + book.getAuthor());
        tvYear.setText("Terbit: " + book.getYear());
        tvBlurb.setText(book.getBlurb());
        tvReview.setText(book.getReview());
        ratingBar.setRating(book.getRating());
        tvRatingValue.setText(String.format("%.1f / 5.0", book.getRating()));
        chipGenre.setText(book.getGenre());

        isLiked = book.isLiked();
        updateFabIcon();

        fabLike.setOnClickListener(v -> {
            isLiked = !isLiked;
            book.setLiked(isLiked);
            updateFabIcon();
        });

        ivBack.setOnClickListener(v -> onBackPressed());
    }

    private void updateFabIcon() {
        fabLike.setImageResource(isLiked
                ? R.drawable.ic_heart_filled
                : R.drawable.ic_heart_outline);
    }
}
