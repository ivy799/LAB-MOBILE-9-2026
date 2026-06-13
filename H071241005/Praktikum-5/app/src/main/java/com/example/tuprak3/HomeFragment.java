package com.example.tuprak3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
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
    private TextView tvWelcome, tvRecentSearch;
    
    private String selectedGenre = "Semua";
    private SharedPreferences sharedPrefs;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final Handler mainHandler = new Handler(Looper.getMainLooper());

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        rvBooks = view.findViewById(R.id.rvBooks);
        searchView = view.findViewById(R.id.searchView);
        chipGroupGenre = view.findViewById(R.id.chipGroupGenre);
        progressBar = view.findViewById(R.id.progressBar);
        tvWelcome = view.findViewById(R.id.tvWelcome);
        tvRecentSearch = view.findViewById(R.id.tvRecentSearch);

        sharedPrefs = requireActivity().getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
        rvBooks.setLayoutManager(new LinearLayoutManager(getContext()));
        
        setupSearchView();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUIFromPrefs();
        refreshData();
    }

    private void updateUIFromPrefs() {
        String name = sharedPrefs.getString("name", "Pengguna");
        String lastSearch = sharedPrefs.getString("last_search", "");

        tvWelcome.setText("Halo, " + (name.isEmpty() ? "Pengguna" : name) + "!");
        
        if (!lastSearch.isEmpty()) {
            tvRecentSearch.setText("Terakhir dicari: \"" + lastSearch + "\"");
        } else {
            tvRecentSearch.setText("Mau baca apa hari ini?");
        }
    }

    private void refreshData() {
        progressBar.setVisibility(View.VISIBLE);
        executor.execute(() -> {
            fullList = BookRepository.getInstance().getAllBooks();
            try { Thread.sleep(300); } catch (InterruptedException e) { e.printStackTrace(); }

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
            public boolean onQueryTextSubmit(String query) {
                // Simpan pencarian terakhir saat user menekan enter
                if (!query.trim().isEmpty()) {
                    sharedPrefs.edit().putString("last_search", query).apply();
                    updateUIFromPrefs();
                }
                return false;
            }

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
            if (b.getGenre() != null) genreSet.add(b.getGenre());
        }

        List<String> genreList = new ArrayList<>(genreSet);
        java.util.Collections.sort(genreList);
        genreList.remove("Semua");
        genreList.add(0, "Semua");

        chipGroupGenre.removeAllViews();
        for (String genre : genreList) {
            Chip chip = new Chip(getContext());
            chip.setText(genre);
            chip.setCheckable(true);
            if (genre.equals(selectedGenre)) chip.setChecked(true);

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
                if (matchesTitle && matchesGenre) filteredList.add(b);
            }

            mainHandler.post(() -> {
                if (adapter != null) adapter.updateData(filteredList);
                progressBar.setVisibility(View.GONE);
            });
        });
    }
}
