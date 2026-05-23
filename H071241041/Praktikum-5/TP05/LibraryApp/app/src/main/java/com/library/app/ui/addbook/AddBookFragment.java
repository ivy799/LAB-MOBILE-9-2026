package com.library.app.ui.addbook;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.library.app.R;
import com.library.app.model.Book;
import com.library.app.model.BookRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class AddBookFragment extends Fragment {

    private ImageView ivCoverPreview;
    private TextInputEditText etTitle, etAuthor, etYear, etBlurb, etRating;
    private AutoCompleteTextView acGenre;
    private Uri selectedImageUri = null;

    private final ActivityResultLauncher<Intent> imagePickerLauncher =
        registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                selectedImageUri = result.getData().getData();
                ivCoverPreview.setImageURI(selectedImageUri);
                ivCoverPreview.setScaleType(ImageView.ScaleType.CENTER_CROP);
            }
        });

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_book, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ivCoverPreview = view.findViewById(R.id.iv_cover_preview);
        etTitle = view.findViewById(R.id.et_title);
        etAuthor = view.findViewById(R.id.et_author);
        etYear = view.findViewById(R.id.et_year);
        etBlurb = view.findViewById(R.id.et_blurb);
        etRating = view.findViewById(R.id.et_rating);
        acGenre = view.findViewById(R.id.ac_genre);
        Button btnPickImage = view.findViewById(R.id.btn_pick_image);
        Button btnSave = view.findViewById(R.id.btn_save);

        // Genre dropdown
        List<String> genres = Arrays.asList("Fiction", "Fantasy", "Sci-Fi", "Mystery",
            "Self-Help", "Biography", "Classic", "Dystopia", "Romance", "Horror", "Other");
        ArrayAdapter<String> genreAdapter = new ArrayAdapter<>(
            requireContext(), android.R.layout.simple_dropdown_item_1line, genres);
        acGenre.setAdapter(genreAdapter);

        btnPickImage.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            imagePickerLauncher.launch(intent);
        });

        btnSave.setOnClickListener(v -> saveBook());
    }

    private void saveBook() {
        String title = Objects.requireNonNull(etTitle.getText()).toString().trim();
        String author = Objects.requireNonNull(etAuthor.getText()).toString().trim();
        String yearStr = Objects.requireNonNull(etYear.getText()).toString().trim();
        String blurb = Objects.requireNonNull(etBlurb.getText()).toString().trim();
        String genre = acGenre.getText().toString().trim();
        String ratingStr = Objects.requireNonNull(etRating.getText()).toString().trim();

        if (title.isEmpty() || author.isEmpty() || yearStr.isEmpty() || blurb.isEmpty() || genre.isEmpty()) {
            Toast.makeText(getContext(), "Semua field wajib diisi!", Toast.LENGTH_SHORT).show();
            return;
        }

        int year;
        try {
            year = Integer.parseInt(yearStr);
        } catch (NumberFormatException e) {
            Toast.makeText(getContext(), "Tahun terbit tidak valid!", Toast.LENGTH_SHORT).show();
            return;
        }

        float rating = 0f;
        if (!ratingStr.isEmpty()) {
            try {
                rating = Float.parseFloat(ratingStr);
                if (rating < 0 || rating > 5) {
                    Toast.makeText(getContext(), "Rating harus antara 0 dan 5!", Toast.LENGTH_SHORT).show();
                    return;
                }
            } catch (NumberFormatException e) {
                Toast.makeText(getContext(), "Rating tidak valid!", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        Book newBook;
        if (selectedImageUri != null) {
            newBook = new Book(title, author, year, blurb, genre, rating, selectedImageUri);
        } else {
            newBook = new Book(title, author, year, blurb, genre, rating, "ic_book_placeholder");
        }

        BookRepository.getInstance().addBook(newBook);
        Toast.makeText(getContext(), "Buku berhasil ditambahkan!", Toast.LENGTH_SHORT).show();
        clearForm();
    }

    private void clearForm() {
        etTitle.setText("");
        etAuthor.setText("");
        etYear.setText("");
        etBlurb.setText("");
        etRating.setText("");
        acGenre.setText("");
        selectedImageUri = null;
        ivCoverPreview.setImageResource(R.drawable.ic_add_photo);
        ivCoverPreview.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
    }
}
