package com.example.tuprak3;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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
    private LinearLayout layoutEmpty;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final Handler mainHandler = new Handler(Looper.getMainLooper());

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);
        rvFavorites = view.findViewById(R.id.rvFavorites);
        progressBar = view.findViewById(R.id.progressBar);
        layoutEmpty = view.findViewById(R.id.layoutEmpty);
        rvFavorites.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadFavorites();
    }

    private void loadFavorites() {
        progressBar.setVisibility(View.VISIBLE);
        layoutEmpty.setVisibility(View.GONE);
        
        executor.execute(() -> {
            try { Thread.sleep(400); } catch (InterruptedException e) { e.printStackTrace(); }

            List<Book> likedBooks = new ArrayList<>();
            for (Book b : BookRepository.getInstance().getAllBooks()) {
                if (b.isLiked()) likedBooks.add(b);
            }

            mainHandler.post(() -> {
                if (getActivity() == null) return;
                
                if (likedBooks.isEmpty()) {
                    layoutEmpty.setVisibility(View.VISIBLE);
                } else {
                    layoutEmpty.setVisibility(View.GONE);
                }

                adapter = new BookAdapter(likedBooks, book -> {
                    Intent intent = new Intent(getActivity(), DetailActivity.class);
                    intent.putExtra("BOOK_ID", book.getId());
                    startActivity(intent);
                });
                rvFavorites.setAdapter(adapter);
                progressBar.setVisibility(View.GONE);
            });
        });
    }
}
