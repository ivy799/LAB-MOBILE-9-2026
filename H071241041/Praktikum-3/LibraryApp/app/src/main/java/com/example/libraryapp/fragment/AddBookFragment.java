package com.example.libraryapp.fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.libraryapp.R;
import com.example.libraryapp.model.Book;
import com.example.libraryapp.util.BookRepository;

import java.util.Arrays;
import java.util.List;

public class AddBookFragment extends Fragment {

    private ImageView ivCoverPreview;
    private EditText etTitle, etAuthor, etYear, etBlurb, etReview;
    private RatingBar ratingBar;
    private Spinner spinnerGenre;
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
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
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
        etReview = view.findViewById(R.id.et_review);
        ratingBar = view.findViewById(R.id.rating_bar_add);
        spinnerGenre = view.findViewById(R.id.spinner_genre);
        Button btnPickImage = view.findViewById(R.id.btn_pick_image);
        Button btnSave = view.findViewById(R.id.btn_save);

        setupGenreSpinner();

        btnPickImage.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            imagePickerLauncher.launch(intent);
        });

        btnSave.setOnClickListener(v -> saveBook());
    }

    private void setupGenreSpinner() {
        List<String> genres = Arrays.asList(
                "Fantasy", "Drama", "Romance", "Action", "Fiction",
                "Non-Fiction", "Self-Help", "Finance", "Dystopia",
                "Technology", "History", "Horror", "Mystery", "Thriller", "Biography"
        );
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                genres
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGenre.setAdapter(adapter);
    }

    private void saveBook() {
        String title = etTitle.getText().toString().trim();
        String author = etAuthor.getText().toString().trim();
        String yearStr = etYear.getText().toString().trim();
        String blurb = etBlurb.getText().toString().trim();
        String review = etReview.getText().toString().trim();
        float rating = ratingBar.getRating();
        String genre = spinnerGenre.getSelectedItem().toString();

        if (title.isEmpty() || author.isEmpty() || yearStr.isEmpty() || blurb.isEmpty()) {
            Toast.makeText(requireContext(), "Judul, penulis, tahun, dan blurb wajib diisi!", Toast.LENGTH_SHORT).show();
            return;
        }

        int year;
        try {
            year = Integer.parseInt(yearStr);
        } catch (NumberFormatException e) {
            Toast.makeText(requireContext(), "Tahun harus berupa angka!", Toast.LENGTH_SHORT).show();
            return;
        }

        Book newBook = new Book(title, author, year, blurb, genre, rating,
                review.isEmpty() ? "Belum ada review." : review, selectedImageUri);
        BookRepository.getInstance().addBook(newBook);

        Toast.makeText(requireContext(), "Buku \"" + title + "\" berhasil ditambahkan!", Toast.LENGTH_SHORT).show();
        clearForm();
    }

    private void clearForm() {
        etTitle.setText("");
        etAuthor.setText("");
        etYear.setText("");
        etBlurb.setText("");
        etReview.setText("");
        ratingBar.setRating(0);
        spinnerGenre.setSelection(0);
        selectedImageUri = null;
        ivCoverPreview.setImageResource(R.drawable.ic_add_photo);
        ivCoverPreview.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
    }
}
