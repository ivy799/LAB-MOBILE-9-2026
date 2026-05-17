package com.example.tp4;import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.tp4.data.DataBook;
import com.example.tp4.model.Book;

public class DetailActivity extends AppCompatActivity {
    ImageView img, imgLike;
    TextView title, author, year, desc, txtLike;
    View btnLike;
    Book book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        img = findViewById(R.id.imgDetail);
        title = findViewById(R.id.title);
        author = findViewById(R.id.author);
        year = findViewById(R.id.year);
        desc = findViewById(R.id.desc);

        imgLike = findViewById(R.id.imgLike);
        txtLike = findViewById(R.id.txtLike);
        btnLike = findViewById(R.id.btnLike);

        Intent i = getIntent();
        String t = i.getStringExtra("title");
        String a = i.getStringExtra("author");
        String y = i.getStringExtra("year");
        String d = i.getStringExtra("desc");
        String imageUri = i.getStringExtra("imageUri");
        int imgRes = i.getIntExtra("image", 0);

        title.setText(t);
        author.setText(a);
        year.setText(y);
        desc.setText(d);

        if (imageUri != null && !imageUri.isEmpty()) {
            Glide.with(this).load(Uri.parse(imageUri)).into(img);
        } else if (imgRes != 0) {
            Glide.with(this).load(imgRes).into(img);
        }

        for (Book b : DataBook.listBook) {
            if (b.title.equals(t)) {
                book = b;
                break;
            }
        }

        if (book != null) {
            updateLikeUI(book.isLiked);

            btnLike.setOnClickListener(v -> {
                book.isLiked = !book.isLiked;
                updateLikeUI(book.isLiked);

                if (book.isLiked) {
                    Toast.makeText(this, "Ditambahkan ke Favorite", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Dihapus dari Favorite", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void updateLikeUI(boolean isLiked) {
        if (isLiked) {
            imgLike.setImageResource(R.drawable.fill_heart);
            txtLike.setText("Liked");
        } else {
            imgLike.setImageResource(R.drawable.outline_heart);
            txtLike.setText("Like");
        }
    }
}