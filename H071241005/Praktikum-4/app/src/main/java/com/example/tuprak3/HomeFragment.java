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
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HomeFragment extends Fragment {
    private RecyclerView rvBooks;
    private BookAdapter adapter;
    private List<Book> fullList;
    private SearchView searchView;
    private ChipGroup chipGroupGenre;
    private ProgressBar progressBar;
    private String selectedGenre = "Semua";
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final Handler mainHandler = new Handler(Looper.getMainLooper());

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        rvBooks = view.findViewById(R.id.rvBooks);
        searchView = view.findViewById(R.id.searchView);
        chipGroupGenre = view.findViewById(R.id.chipGroupGenre);
        progressBar = view.findViewById(R.id.progressBar);

        rvBooks.setLayoutManager(new LinearLayoutManager(getContext()));
        
        setupSearchView();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshData();
    }

    private void refreshData() {
        progressBar.setVisibility(View.VISIBLE);
        executor.execute(() -> {
            fullList = BookRepository.getInstance().getAllBooks();
            
            // Simulasi proses berat (opsional, untuk melihat loading)
            try { Thread.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }

            mainHandler.post(() -> {
                if (adapter == null) {
                    adapter = new BookAdapter(new ArrayList<>(fullList), book -> {
                        Intent intent = new Intent(getActivity(), DetailActivity.class);
                        intent.putExtra("BOOK_ID", book.getId());
                        startActivity(intent);
                    });
                    rvBooks.setAdapter(adapter);
                }

                setupGenreFilters();
                filterList(searchView.getQuery().toString(), selectedGenre);
                progressBar.setVisibility(View.GONE);
            });
        });
    }

    private void setupSearchView() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) { return false; }
            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText, selectedGenre);
                return true;
            }
        });
    }

    private void setupGenreFilters() {
        Set<String> genreSet = new HashSet<>();
        genreSet.add("Semua");
        for (Book b : fullList) {
            if (b.getGenre() != null && !b.getGenre().isEmpty()) {
                genreSet.add(b.getGenre());
            }
        }

        List<String> genreList = new ArrayList<>(genreSet);
        genreList.remove("Semua");
        java.util.Collections.sort(genreList);
        genreList.add(0, "Semua");

        chipGroupGenre.removeAllViews();
        for (String genre : genreList) {
            Chip chip = new Chip(getContext());
            chip.setText(genre);
            chip.setCheckable(true);
            
            if (genre.equals(selectedGenre)) {
                chip.setChecked(true);
            }

            chip.setOnClickListener(v -> {
                selectedGenre = genre;
                filterList(searchView.getQuery().toString(), selectedGenre);
            });
            
            chipGroupGenre.addView(chip);
        }
    }

    private void filterList(String text, String genre) {
        progressBar.setVisibility(View.VISIBLE);
        executor.execute(() -> {
            List<Book> filteredList = new ArrayList<>();
            for (Book b : fullList) {
                boolean matchesTitle = b.getTitle().toLowerCase().contains(text.toLowerCase());
                boolean matchesGenre = genre.equals("Semua") || b.getGenre().equalsIgnoreCase(genre);
                
                if (matchesTitle && matchesGenre) {
                    filteredList.add(b);
                }
            }
            
            // Simulasi proses delay pencarian
            try { Thread.sleep(300); } catch (InterruptedException e) { e.printStackTrace(); }

            mainHandler.post(() -> {
                if (adapter != null) {
                    adapter.updateData(filteredList);
                }
                progressBar.setVisibility(View.GONE);
            });
        });
    }
}
