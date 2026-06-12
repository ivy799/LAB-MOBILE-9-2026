package com.library.app.ui.favorites;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.library.app.R;
import com.library.app.adapter.BookAdapter;
import com.library.app.model.Book;
import com.library.app.model.BookRepository;
import com.library.app.ui.detail.DetailActivity;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FavoritesFragment extends Fragment implements BookAdapter.OnBookClickListener {

    private BookAdapter bookAdapter;
    private TextView tvEmpty;
    private ProgressBar progressBar;

    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final Handler mainHandler = new Handler(Looper.getMainLooper());

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorites, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvEmpty = view.findViewById(R.id.tv_empty);
        progressBar = view.findViewById(R.id.progress_bar);

        RecyclerView rvFavorites = view.findViewById(R.id.rv_favorites);
        rvFavorites.setLayoutManager(new LinearLayoutManager(getContext()));
        bookAdapter = new BookAdapter(getContext(), List.of(), this);
        rvFavorites.setAdapter(bookAdapter);

        loadFavoritesInBackground();
    }

    @Override
    public void onResume() {
        super.onResume();
        // Reload setiap kali fragment tampil (misal balik dari DetailActivity)
        loadFavoritesInBackground();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        executor.shutdown();
    }

    /**
     * Muat daftar buku favorit di background thread,
     * tampilkan ProgressBar selama loading, update UI setelah selesai.
     */
    private void loadFavoritesInBackground() {
        mainHandler.post(() -> {
            progressBar.setVisibility(View.VISIBLE);
            tvEmpty.setVisibility(View.GONE);
        });

        executor.execute(() -> {
            // === BACKGROUND THREAD ===
            List<Book> likedBooks = BookRepository.getInstance().getLikedBooks();

            // Simulasi delay agar ProgressBar terlihat
            try { Thread.sleep(400); } catch (InterruptedException ignored) {}

            // === KEMBALI KE MAIN THREAD ===
            mainHandler.post(() -> {
                if (getActivity() == null || !isAdded()) return;
                bookAdapter.updateList(likedBooks);
                progressBar.setVisibility(View.GONE);
                updateEmptyState(likedBooks);
            });
        });
    }

    private void updateEmptyState(List<Book> books) {
        tvEmpty.setVisibility(books.isEmpty() ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onBookClick(Book book) {
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_BOOK_ID, book.getId());
        startActivity(intent);
    }
}
