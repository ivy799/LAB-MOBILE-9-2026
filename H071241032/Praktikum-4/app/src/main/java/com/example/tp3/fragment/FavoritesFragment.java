package com.example.tp3.fragment; // SESUAIKAN DENGAN PACKAGE-MU

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

import com.example.tp3.DetailActivity; // SESUAIKAN
import com.example.tp3.R; // SESUAIKAN
import com.example.tp3.adapter.BookAdapter;
import com.example.tp3.model.Book;
import com.example.tp3.utils.DataHelper;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FavoritesFragment extends Fragment {

    private RecyclerView rvFavorites;
    private TextView tvEmpty;
    private ProgressBar progressBar;

    // Komponen Background Thread
    private ExecutorService executorService;
    private Handler handler;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);

        rvFavorites = view.findViewById(R.id.rv_favorites);
        tvEmpty = view.findViewById(R.id.tv_empty_favorites);
        progressBar = view.findViewById(R.id.progress_bar_favorites);

        // Inisialisasi Executor dan Handler sesuai materi
        executorService = Executors.newSingleThreadExecutor();
        handler = new Handler(Looper.getMainLooper());

        loadFavoriteBooks();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadFavoriteBooks();
    }

    private void loadFavoriteBooks() {
        // Tampilkan loading dan sembunyikan semua elemen UI (Main Thread)
        progressBar.setVisibility(View.VISIBLE);
        rvFavorites.setVisibility(View.GONE);
        tvEmpty.setVisibility(View.GONE);

        // Jalankan penarikan data favorit di Background Thread
        executorService.execute(() -> {
            try {
                // Simulasi proses mengambil data berat
                Thread.sleep(600);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            ArrayList<Book> favoriteBooks = new ArrayList<>();
            for (Book book : DataHelper.bookList) {
                if (book.isLiked()) {
                    favoriteBooks.add(book);
                }
            }

            // Kembalikan hasil ke Main Thread untuk update tampilan
            handler.post(() -> {
                progressBar.setVisibility(View.GONE);

                BookAdapter bookAdapter = new BookAdapter(favoriteBooks);
                rvFavorites.setLayoutManager(new LinearLayoutManager(getContext()));
                rvFavorites.setAdapter(bookAdapter);

                bookAdapter.setOnItemClickListener(book -> {
                    Intent intent = new Intent(getActivity(), DetailActivity.class);
                    intent.putExtra("BOOK_ID", book.getId());
                    startActivity(intent);
                });

                if (favoriteBooks.isEmpty()) {
                    tvEmpty.setVisibility(View.VISIBLE);
                    rvFavorites.setVisibility(View.GONE);
                } else {
                    tvEmpty.setVisibility(View.GONE);
                    rvFavorites.setVisibility(View.VISIBLE);
                }
            });
        });
    }
}