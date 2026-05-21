package com.example.praktikum_3;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;

public class AddBookFragment extends Fragment {

    private ImageView ivAddCover;
    private TextInputEditText etTitle, etAuthor, etYear, etBlurb;
    private Uri selectedImageUri;

    private final ActivityResultLauncher<Intent> galleryLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                    selectedImageUri = result.getData().getData();
                    ivAddCover.setImageURI(selectedImageUri);
                }
            });

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_book, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ivAddCover = view.findViewById(R.id.iv_add_cover);
        etTitle = view.findViewById(R.id.et_title);
        etAuthor = view.findViewById(R.id.et_author);
        etYear = view.findViewById(R.id.et_year);
        etBlurb = view.findViewById(R.id.et_blurb);
        Button btnSave = view.findViewById(R.id.btn_save_book);

        ivAddCover.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("image/*");
            galleryLauncher.launch(intent);
        });

        btnSave.setOnClickListener(v -> saveBook());
    }

    private void saveBook() {
        String title = etTitle.getText().toString();
        String author = etAuthor.getText().toString();
        String year = etYear.getText().toString();
        String blurb = etBlurb.getText().toString();

        if (title.isEmpty() || author.isEmpty() || year.isEmpty() || blurb.isEmpty() || selectedImageUri == null) {
            Toast.makeText(getContext(), "Please fill all fields and select a cover", Toast.LENGTH_SHORT).show();
            return;
        }

        Book newBook = new Book(title, author, year, blurb, selectedImageUri);
        BookRepository.addBook(newBook);

        Toast.makeText(getContext(), "Book added successfully", Toast.LENGTH_SHORT).show();
        
        // Reset fields
        etTitle.setText("");
        etAuthor.setText("");
        etYear.setText("");
        etBlurb.setText("");
        ivAddCover.setImageResource(android.R.drawable.ic_menu_gallery);
        selectedImageUri = null;
    }
}
