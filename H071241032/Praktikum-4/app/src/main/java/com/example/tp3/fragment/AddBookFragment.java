package com.example.tp3.fragment; // SESUAIKAN DENGAN PACKAGE-MU

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tp3.R; // SESUAIKAN
import com.example.tp3.model.Book;
import com.example.tp3.utils.DataHelper;

public class AddBookFragment extends Fragment {

    private ImageView ivCover;
    private EditText etTitle, etAuthor, etYear, etGenre, etRating, etBlurb;
    private Button btnPickImage, btnSave;
    private String selectedImageUri = null; // Menyimpan lokasi gambar dari galeri

    // Fitur bawaan Android untuk membuka galeri dan menangkap gambar yang dipilih
    private final ActivityResultLauncher<String> pickImageLauncher = registerForActivityResult(
            new ActivityResultContracts.GetContent(),
            uri -> {
                if (uri != null) {
                    selectedImageUri = uri.toString();
                    ivCover.setImageURI(uri); // Tampilkan gambar di layar
                }
            }
    );

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_book, container, false);

        // Menghubungkan ID
        ivCover = view.findViewById(R.id.iv_add_cover);
        btnPickImage = view.findViewById(R.id.btn_pick_image);
        btnSave = view.findViewById(R.id.btn_save_book);

        etTitle = view.findViewById(R.id.et_add_title);
        etAuthor = view.findViewById(R.id.et_add_author);
        etYear = view.findViewById(R.id.et_add_year);
        etGenre = view.findViewById(R.id.et_add_genre);
        etRating = view.findViewById(R.id.et_add_rating);
        etBlurb = view.findViewById(R.id.et_add_blurb);

        // Aksi tombol pilih gambar
        btnPickImage.setOnClickListener(v -> {
            pickImageLauncher.launch("image/*"); // Buka galeri hanya untuk gambar
        });

        // Aksi tombol simpan
        btnSave.setOnClickListener(v -> saveBookData());

        return view;
    }

    private void saveBookData() {
        String title = etTitle.getText().toString().trim();
        String author = etAuthor.getText().toString().trim();
        String year = etYear.getText().toString().trim();
        String genre = etGenre.getText().toString().trim();
        String ratingStr = etRating.getText().toString().trim();
        String blurb = etBlurb.getText().toString().trim();

        // Validasi agar tidak ada kolom yang kosong
        if (title.isEmpty() || author.isEmpty() || year.isEmpty() || genre.isEmpty() || ratingStr.isEmpty() || blurb.isEmpty()) {
            Toast.makeText(getContext(), "Harap isi semua data!", Toast.LENGTH_SHORT).show();
            return;
        }

        double rating = Double.parseDouble(ratingStr);
        String newId = String.valueOf(System.currentTimeMillis()); // Buat ID unik berdasarkan waktu

        // Masukkan buku baru ke dalam DataHelper
        Book newBook = new Book(newId, title, author, year, blurb, R.mipmap.ic_launcher, selectedImageUri, rating, genre);
        DataHelper.bookList.add(newBook); // Menambah ke list

        Toast.makeText(getContext(), "Buku Berhasil Ditambahkan!", Toast.LENGTH_SHORT).show();

        // Kosongkan form setelah disimpan
        etTitle.setText("");
        etAuthor.setText("");
        etYear.setText("");
        etGenre.setText("");
        etRating.setText("");
        etBlurb.setText("");
        ivCover.setImageResource(R.mipmap.ic_launcher);
        selectedImageUri = null;
    }
}