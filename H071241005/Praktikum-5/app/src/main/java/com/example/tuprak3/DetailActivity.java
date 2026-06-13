package com.example.tuprak3;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.tuprak3.R;

public class DetailActivity extends AppCompatActivity {
    private Book currentBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        String bookId = getIntent().getStringExtra("BOOK_ID");

        for (Book b : BookRepository.getInstance().getAllBooks()) {
            if (b.getId().equals(bookId)) { currentBook = b; break; }
        }

        if (currentBook == null) return;

        ImageView ivCover = findViewById(R.id.ivDetailCover);
        TextView tvTitle = findViewById(R.id.tvDetailTitle);
        TextView tvAuthor = findViewById(R.id.tvDetailAuthor);
        TextView tvBlurb = findViewById(R.id.tvDetailBlurb);
        TextView tvExtras = findViewById(R.id.tvDetailExtras);
        ImageButton btnLike = findViewById(R.id.btnLike);

        tvTitle.setText(currentBook.getTitle());
        tvAuthor.setText(currentBook.getAuthor());
        tvBlurb.setText(currentBook.getBlurb());
        tvExtras.setText("Terbit: " + currentBook.getYear() + " | Genre: " + currentBook.getGenre() + " | Rating: " + currentBook.getRating());

        if (currentBook.getCoverUri() != null) {
            ivCover.setImageURI(Uri.parse(currentBook.getCoverUri()));
        } else {
            ivCover.setImageResource(currentBook.getCoverResId());
        }

        updateLikeButton(btnLike);

        btnLike.setOnClickListener(v -> {
            currentBook.setLiked(!currentBook.isLiked());
            updateLikeButton(btnLike);
        });
    }

    private void updateLikeButton(ImageButton btn) {
        if (currentBook.isLiked()) {
            btn.setImageResource(R.drawable.ic_star_on);
        } else {
            btn.setImageResource(R.drawable.ic_star_off);
        }
    }
}
