package com.example.tuprak3;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FavoritesFragment extends Fragment {
    private RecyclerView rvFavorites;
    private BookAdapter adapter;
    private ProgressBar progressBar;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final Handler mainHandler = new Handler(Looper.getMainLooper());

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);
        rvFavorites = view.findViewById(R.id.rvFavorites);
        progressBar = view.findViewById(R.id.progressBar);
        rvFavorites.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadFavorites();
    }

    private void loadFavorites() {
        // Tampilkan ProgressBar sebelum memulai proses background
        progressBar.setVisibility(View.VISIBLE);
        
        executor.execute(() -> {
            // Simulasi proses pemuatan data yang berat (opsional)
            try { Thread.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }

            List<Book> likedBooks = new ArrayList<>();
            for (Book b : BookRepository.getInstance().getAllBooks()) {
                if (b.isLiked()) likedBooks.add(b);
            }

            // Pindah kembali ke main thread untuk memperbarui UI
            mainHandler.post(() -> {
                if (getActivity() == null) return;
                
                adapter = new BookAdapter(likedBooks, book -> {
                    Intent intent = new Intent(getActivity(), DetailActivity.class);
                    intent.putExtra("BOOK_ID", book.getId());
                    startActivity(intent);
                });
                rvFavorites.setAdapter(adapter);
                
                // Sembunyikan ProgressBar setelah data selesai dimuat
                progressBar.setVisibility(View.GONE);
            });
        });
    }
}
