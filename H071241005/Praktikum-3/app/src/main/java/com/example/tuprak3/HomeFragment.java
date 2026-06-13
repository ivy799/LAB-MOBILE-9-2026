package com.example.tuprak3;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class HomeFragment extends Fragment {
    private RecyclerView rvBooks;
    private BookAdapter adapter;
    private List<Book> fullList;
    private SearchView searchView;
    private ChipGroup chipGroupGenre;
    private String selectedGenre = "Semua";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        rvBooks = view.findViewById(R.id.rvBooks);
        searchView = view.findViewById(R.id.searchView);
        chipGroupGenre = view.findViewById(R.id.chipGroupGenre);

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
        fullList = BookRepository.getInstance().getAllBooks();
        
        // Inisialisasi adapter jika belum ada
        if (adapter == null) {
            adapter = new BookAdapter(new ArrayList<>(fullList), book -> {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra("BOOK_ID", book.getId());
                startActivity(intent);
            });
            rvBooks.setAdapter(adapter);
        }

        // Perbarui Filter Genre dan List
        setupGenreFilters();
        filterList(searchView.getQuery().toString(), selectedGenre);
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
        // Ambil semua genre unik dari data buku
        Set<String> genreSet = new HashSet<>();
        genreSet.add("Semua");
        for (Book b : fullList) {
            if (b.getGenre() != null && !b.getGenre().isEmpty()) {
                genreSet.add(b.getGenre());
            }
        }

        List<String> genreList = new ArrayList<>(genreSet);
        // Urutkan agar "Semua" tetap di awal
        genreList.remove("Semua");
        java.util.Collections.sort(genreList);
        genreList.add(0, "Semua");

        chipGroupGenre.removeAllViews();
        for (String genre : genreList) {
            Chip chip = new Chip(getContext());
            chip.setText(genre);
            chip.setCheckable(true);
            
            // Set agar chip yang sedang dipilih tetap terpilih
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
        List<Book> filteredList = new ArrayList<>();
        for (Book b : fullList) {
            boolean matchesTitle = b.getTitle().toLowerCase().contains(text.toLowerCase());
            // Gunakan equalsIgnoreCase agar tidak sensitif huruf besar/kecil
            boolean matchesGenre = genre.equals("Semua") || b.getGenre().equalsIgnoreCase(genre);
            
            if (matchesTitle && matchesGenre) {
                filteredList.add(b);
            }
        }
        if (adapter != null) {
            adapter.updateData(filteredList);
        }
    }
}
