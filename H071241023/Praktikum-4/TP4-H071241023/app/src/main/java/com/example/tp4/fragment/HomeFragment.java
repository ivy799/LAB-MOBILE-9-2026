package com.example.tp4.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
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

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private BookAdapter adapter;
    private SearchView searchView;
    private ProgressBar progressBar;

    public HomeFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.rv_books);

        progressBar = view.findViewById(R.id.progressBar);

        searchView = requireActivity().findViewById(R.id.searchView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new BookAdapter(new ArrayList<>(DataBook.listBook));
        recyclerView.setAdapter(adapter);

        setupSearchView();

        return view;
    }

    // pe
    Handler searchHandler = new Handler(Looper.getMainLooper());
    Runnable searchRunnable;

    private void setupSearchView() {
        searchView.setIconified(false);
        searchView.setQueryHint("Cari buku...");
        searchView.clearFocus();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            // pe
            @Override
            public boolean onQueryTextChange(String newText) {

                if (searchRunnable != null) {
                    searchHandler.removeCallbacks(searchRunnable);
                }

                searchRunnable = () -> filterList(newText);

                searchHandler.postDelayed(searchRunnable, 300);

                return true;
            }
        });
    }

    // pe
    ExecutorService executor = Executors.newSingleThreadExecutor();
    Handler handler = new Handler(Looper.getMainLooper());

    private void filterList(String text) {

        progressBar.setVisibility(View.VISIBLE);

        executor.execute(() -> {

            ArrayList<Book> filteredList = new ArrayList<>();

            for (Book book : DataBook.listBook) {
                if (book.getTitle().toLowerCase().contains(text.toLowerCase()) ||
                        book.getAuthor().toLowerCase().contains(text.toLowerCase())) {
                    filteredList.add(book);
                }
            }

            handler.post(() -> {
                adapter.setFilteredList(filteredList);
                progressBar.setVisibility(View.GONE);
            });

        });
    }

    @Override
    public void onResume() {
        super.onResume();

        // pe
        if (searchView != null) {
            filterList(searchView.getQuery().toString());
        }

        ((MainActivity) requireActivity()).setSearchViewVisibility(View.VISIBLE);
    }
}