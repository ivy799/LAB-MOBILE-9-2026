package com.example.tp3; // SESUAIKAN DENGAN PACKAGE-MU

import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tp3.model.Book;
import com.example.tp3.utils.DataHelper;

public class DetailActivity extends AppCompatActivity {

    private ImageView ivCover;
    private TextView tvTitle, tvAuthor, tvInfo, tvBlurb;
    private Button btnFavorite;
    private Book currentBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Menghubungkan variabel dengan ID di XML
        ivCover = findViewById(R.id.iv_detail_cover);
        tvTitle = findViewById(R.id.tv_detail_title);
        tvAuthor = findViewById(R.id.tv_detail_author);
        tvInfo = findViewById(R.id.tv_detail_info);
        tvBlurb = findViewById(R.id.tv_detail_blurb);
        btnFavorite = findViewById(R.id.btn_favorite);

        // Menangkap ID buku yang dikirim dari HomeFragment
        String bookId = getIntent().getStringExtra("BOOK_ID");
        if (bookId != null) {
            // Mencari buku di DataHelper berdasarkan ID
            currentBook = DataHelper.getBookById(bookId);
            if (currentBook != null) {
                displayBookDetails();
                setupFavoriteButton();
            }
        }
    }

    private void displayBookDetails() {
        tvTitle.setText(currentBook.getTitle());
        tvAuthor.setText(currentBook.getAuthor());
        // Menggabungkan Tahun, Genre, dan Rating
        tvInfo.setText(currentBook.getYear() + " • " + currentBook.getGenre() + " • ⭐ " + currentBook.getRating());
        tvBlurb.setText(currentBook.getBlurb());

        // Cek apakah gambar dari Galeri (URI) atau Resource dummy
        if (currentBook.getCoverUri() != null) {
            ivCover.setImageURI(Uri.parse(currentBook.getCoverUri()));
        } else {
            ivCover.setImageResource(currentBook.getCoverResource());
        }
    }

    private void setupFavoriteButton() {
        // Cek status awal saat halaman dibuka
        updateFavoriteButtonUI();

        // Logika ketika tombol diklik
        btnFavorite.setOnClickListener(v -> {
            // Ubah status like (jika true jadi false, jika false jadi true)
            currentBook.setLiked(!currentBook.isLiked());

            // Perbarui tampilan tombol
            updateFavoriteButtonUI();

            // Tampilkan pesan singkat (Toast)
            String message = currentBook.isLiked() ? "Ditambahkan ke Favorit" : "Dihapus dari Favorit";
            Toast.makeText(DetailActivity.this, message, Toast.LENGTH_SHORT).show();
        });
    }

    private void updateFavoriteButtonUI() {
        if (currentBook.isLiked()) {
            btnFavorite.setText("Hapus dari Favorit");
        } else {
            btnFavorite.setText("Tambahkan ke Favorit");
        }
    }
}