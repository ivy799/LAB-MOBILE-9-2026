package com.example.tp4.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tp4.MainActivity;
import com.example.tp4.R;
import com.example.tp4.adaptor.BookAdapter;
import com.example.tp4.data.DataBook;
import com.example.tp4.model.Book;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FavoritesFragment extends Fragment {

    RecyclerView recyclerView;
    ProgressBar progressBar;

    public FavoritesFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_favorites, container, false);
        recyclerView = view.findViewById(R.id.fav);

        // pe
        progressBar = view.findViewById(R.id.progressBar);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        loadFav();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) requireActivity()).setSearchViewVisibility(View.GONE);
        loadFav();
    }

    // pe
    ExecutorService executor = Executors.newSingleThreadExecutor();
    Handler handler = new Handler(Looper.getMainLooper());

    private void loadFav() {
        // pe
        progressBar.setVisibility(View.VISIBLE);
        executor.execute(() -> {

            ArrayList<Book> favList = new ArrayList<>();

            for (Book b : DataBook.listBook) {
                if (b.isLiked) {
                    favList.add(b);
                }
            }

            // pe
            handler.post(() -> {
                recyclerView.setAdapter(new BookAdapter(favList));
                progressBar.setVisibility(View.GONE);
            });

        });
    }
}