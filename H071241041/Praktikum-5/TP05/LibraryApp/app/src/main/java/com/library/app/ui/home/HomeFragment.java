package com.library.app.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.library.app.R;
import com.library.app.adapter.BookAdapter;
import com.library.app.adapter.GenreFilterAdapter;
import com.library.app.model.Book;
import com.library.app.model.BookRepository;
import com.library.app.ui.detail.DetailActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HomeFragment extends Fragment implements BookAdapter.OnBookClickListener {

    private BookAdapter bookAdapter;
    private List<Book> filteredList;
    private String currentGenre = "All";
    private String currentQuery = "";
    private ProgressBar progressBar;

    // Background thread tools
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final Handler mainHandler = new Handler(Looper.getMainLooper());

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        filteredList = new ArrayList<>();
        progressBar = view.findViewById(R.id.progress_bar);

        // RecyclerView books
        RecyclerView rvBooks = view.findViewById(R.id.rv_books);
        rvBooks.setLayoutManager(new LinearLayoutManager(getContext()));
        bookAdapter = new BookAdapter(getContext(), filteredList, this);
        rvBooks.setAdapter(bookAdapter);

        // RecyclerView genre filter
        RecyclerView rvGenres = view.findViewById(R.id.rv_genres);
        rvGenres.setLayoutManager(
            new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        List<String> genres = BookRepository.getInstance().getAllGenres();
        GenreFilterAdapter genreAdapter = new GenreFilterAdapter(getContext(), genres, genre -> {
            currentGenre = genre;
            applyFilterInBackground();
        });
        rvGenres.setAdapter(genreAdapter);

        // Search field
        EditText etSearch = view.findViewById(R.id.et_search);
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                currentQuery = s.toString().trim();
                applyFilterInBackground();
            }
            @Override public void afterTextChanged(Editable s) {}
        });

        // Load initial data in background
        applyFilterInBackground();
    }

    @Override
    public void onResume() {
        super.onResume();
        applyFilterInBackground(); // refresh like badges
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        executor.shutdown();
    }

    /**
     * Jalankan proses filter di background thread,
     * tampilkan ProgressBar selama proses, update UI di main thread.
     */
    private void applyFilterInBackground() {
        // Tampilkan loading di main thread
        mainHandler.post(() -> progressBar.setVisibility(View.VISIBLE));

        // Simpan query & genre saat ini (thread-safe local copy)
        final String query = currentQuery;
        final String genre = currentGenre;

        executor.execute(() -> {
            // === BACKGROUND THREAD ===
            List<Book> all = BookRepository.getInstance().getAllBooks();
            List<Book> result = new ArrayList<>();

            for (Book book : all) {
                boolean matchesGenre = genre.equals("All") || book.getGenre().equals(genre);
                boolean matchesQuery = query.isEmpty()
                    || book.getTitle().toLowerCase().contains(query.toLowerCase());
                if (matchesGenre && matchesQuery) {
                    result.add(book);
                }
            }

            // Simulasi delay agar ProgressBar terlihat (opsional, bisa dihapus di production)
            try { Thread.sleep(300); } catch (InterruptedException ignored) {}

            // === KEMBALI KE MAIN THREAD ===
            mainHandler.post(() -> {
                if (getActivity() == null || !isAdded()) return;
                bookAdapter.updateList(result);
                progressBar.setVisibility(View.GONE);
            });
        });
    }

    @Override
    public void onBookClick(Book book) {
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_BOOK_ID, book.getId());
        startActivity(intent);
    }
}
