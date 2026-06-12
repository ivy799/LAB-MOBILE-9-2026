package com.library.app.ui.detail;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.library.app.R;
import com.library.app.model.Book;
import com.library.app.model.BookRepository;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_BOOK_ID = "extra_book_id";

    private Book book;
    private Button btnLike;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        int bookId = getIntent().getIntExtra(EXTRA_BOOK_ID, -1);
        book = BookRepository.getInstance().getBookById(bookId);

        if (book == null) {
            finish();
            return;
        }

        // Back button
        ImageView ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(v -> finish());

        // Cover
        ImageView ivCover = findViewById(R.id.iv_cover);
        if (book.hasUriCover()) {
            ivCover.setImageURI(book.getCoverUri());
        } else {
            int resId = getResources().getIdentifier(
                book.getCoverUrl(), "drawable", getPackageName()
            );
            if (resId != 0) {
                ivCover.setImageResource(resId);
            } else {
                ivCover.setImageResource(R.drawable.ic_book_placeholder);
            }
        }

        // Info
        TextView tvTitle = findViewById(R.id.tv_title);
        TextView tvAuthor = findViewById(R.id.tv_author);
        TextView tvYear = findViewById(R.id.tv_year);
        TextView tvGenre = findViewById(R.id.tv_genre);
        TextView tvBlurb = findViewById(R.id.tv_blurb);
        RatingBar ratingBar = findViewById(R.id.rating_bar);
        TextView tvRatingValue = findViewById(R.id.tv_rating_value);

        tvTitle.setText(book.getTitle());
        tvAuthor.setText(book.getAuthor());
        tvYear.setText(String.format("Tahun Terbit: %d", book.getYear()));
        tvGenre.setText(book.getGenre());
        tvBlurb.setText(book.getBlurb());
        ratingBar.setRating(book.getRating());
        tvRatingValue.setText(String.format("%.1f / 5.0", book.getRating()));

        // Like button
        btnLike = findViewById(R.id.btn_like);
        updateLikeButton();

        btnLike.setOnClickListener(v -> {
            book.setLiked(!book.isLiked());
            updateLikeButton();
        });
    }

    private void updateLikeButton() {
        if (book.isLiked()) {
            btnLike.setText("❤ Disukai");
            btnLike.setBackgroundColor(getColor(R.color.liked_color));
        } else {
            btnLike.setText("🤍 Suka");
            btnLike.setBackgroundColor(getColor(R.color.primary));
        }
    }
}
