package com.example.tuprak3;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AddBookFragment extends Fragment {
    private ImageView ivCoverPreview;
    private ProgressBar progressBar;
    private Uri selectedImageUri = null;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final Handler mainHandler = new Handler(Looper.getMainLooper());

    private final ActivityResultLauncher<String> getContent = registerForActivityResult(
            new ActivityResultContracts.GetContent(), uri -> {
                if (uri != null) {
                    selectedImageUri = uri;
                    ivCoverPreview.setImageURI(uri);
                }
            });

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_book, container, false);

        EditText etTitle = view.findViewById(R.id.etTitle);
        EditText etAuthor = view.findViewById(R.id.etAuthor);
        EditText etYear = view.findViewById(R.id.etYear);
        EditText etBlurb = view.findViewById(R.id.etBlurb);
        EditText etGenre = view.findViewById(R.id.etGenre);
        Button btnPickImage = view.findViewById(R.id.btnPickImage);
        Button btnSave = view.findViewById(R.id.btnSave);
        ivCoverPreview = view.findViewById(R.id.ivCoverPreview);
        progressBar = view.findViewById(R.id.progressBar);

        btnPickImage.setOnClickListener(v -> getContent.launch("image/*"));

        btnSave.setOnClickListener(v -> {
            String title = etTitle.getText().toString().trim();
            String author = etAuthor.getText().toString().trim();
            String year = etYear.getText().toString().trim();
            String blurb = etBlurb.getText().toString().trim();
            String genre = etGenre.getText().toString().trim();

            if (title.isEmpty()) {
                Toast.makeText(getContext(), "Judul tidak boleh kosong", Toast.LENGTH_SHORT).show();
                return;
            }
            if (genre.isEmpty()) {
                Toast.makeText(getContext(), "Genre tidak boleh kosong", Toast.LENGTH_SHORT).show();
                return;
            }

            // Tampilkan ProgressBar dan nonaktifkan tombol simpan
            progressBar.setVisibility(View.VISIBLE);
            btnSave.setEnabled(false);

            executor.execute(() -> {
                // Simulasi pemrosesan data di background (misal: kompresi gambar atau hit database)
                try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }

                Book newBook = new Book(
                        UUID.randomUUID().toString(), 
                        title, author, year, blurb, genre, 
                        0.0, R.drawable.ic_book
                );

                if (selectedImageUri != null) {
                    newBook.setCoverUri(selectedImageUri.toString());
                }

                // Simpan buku ke repository
                BookRepository.getInstance().addBook(newBook);

                // Kembali ke main thread untuk update UI
                mainHandler.post(() -> {
                    if (isAdded()) {
                        progressBar.setVisibility(View.GONE);
                        btnSave.setEnabled(true);
                        Toast.makeText(getContext(), "Buku '" + title + "' berhasil ditambahkan ke genre " + genre, Toast.LENGTH_SHORT).show();

                        // Reset form
                        etTitle.setText(""); etAuthor.setText(""); etYear.setText("");
                        etBlurb.setText(""); etGenre.setText("");
                        ivCoverPreview.setImageResource(R.drawable.ic_camera);
                        selectedImageUri = null;
                    }
                });
            });
        });

        return view;
    }
}
