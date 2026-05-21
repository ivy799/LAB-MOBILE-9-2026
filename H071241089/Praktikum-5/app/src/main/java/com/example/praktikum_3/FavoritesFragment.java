package com.example.praktikum_3;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class FavoritesFragment extends Fragment {

    private RecyclerView rvFavoriteBooks;
    private ProgressBar progressBar;
    private BookAdapter adapter;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final Handler handler = new Handler(Looper.getMainLooper());

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorites, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvFavoriteBooks = view.findViewById(R.id.rv_favorite_books);
        progressBar = view.findViewById(R.id.progress_bar);

        adapter = new BookAdapter(new ArrayList<>(), book -> {
            Intent intent = new Intent(getActivity(), DetailActivity.class);
            intent.putExtra("EXTRA_BOOK", book);
            startActivity(intent);
        });

        rvFavoriteBooks.setLayoutManager(new LinearLayoutManager(getContext()));
        rvFavoriteBooks.setAdapter(adapter);

        loadFavoriteBooks();
    }

    private void loadFavoriteBooks() {
        progressBar.setVisibility(View.VISIBLE);
        executor.execute(() -> {
            try {
                // Simulasi proses pemrosesan data
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Log.e("FavoritesFragment", "Error loading favorites", e);
            }

            List<Book> favoriteBooks = BookRepository.getBooks().stream()
                    .filter(Book::isLiked)
                    .collect(Collectors.toList());

            handler.post(() -> {
                adapter.setBooks(favoriteBooks);
                progressBar.setVisibility(View.GONE);
            });
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        loadFavoriteBooks();
    }
}
