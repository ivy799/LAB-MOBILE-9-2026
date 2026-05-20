package com.example.tp3.fragment; // SESUAIKAN DENGAN PACKAGE-MU

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tp3.DetailActivity; // SESUAIKAN
import com.example.tp3.R; // SESUAIKAN
import com.example.tp3.adapter.BookAdapter;
import com.example.tp3.model.Book;
import com.example.tp3.utils.DataHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HomeFragment extends Fragment {

    private RecyclerView rvBooks;
    private BookAdapter bookAdapter;
    private SearchView searchView;
    private Spinner spinnerGenre;
    private ProgressBar progressBar;
    private ArrayList<Book> displayList;

    private String currentSearchQuery = "";
    private String currentGenreFilter = "Semua Genre";

    // Komponen Background Thread
    private ExecutorService executorService;
    private Handler handler;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        rvBooks = view.findViewById(R.id.rv_books);
        searchView = view.findViewById(R.id.search_view);
        spinnerGenre = view.findViewById(R.id.spinner_genre);
        progressBar = view.findViewById(R.id.progress_bar_home);

        // Inisialisasi Executor dan Handler sesuai materi
        executorService = Executors.newSingleThreadExecutor();
        handler = new Handler(Looper.getMainLooper());

        setupRecyclerView();
        setupSpinner();
        setupSearchView();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        displayList = new ArrayList<>(DataHelper.bookList);
        Collections.reverse(displayList);
        applyFilters();
    }

    private void setupRecyclerView() {
        displayList = new ArrayList<>(DataHelper.bookList);
        Collections.reverse(displayList);

        bookAdapter = new BookAdapter(displayList);
        rvBooks.setLayoutManager(new LinearLayoutManager(getContext()));
        rvBooks.setAdapter(bookAdapter);

        bookAdapter.setOnItemClickListener(book -> {
            Intent intent = new Intent(getActivity(), DetailActivity.class);
            intent.putExtra("BOOK_ID", book.getId());
            startActivity(intent);
        });
    }

    private void setupSpinner() {
        String[] genres = {"Semua Genre", "Fiksi", "Sejarah", "Pengembangan Diri", "Sains", "Bisnis"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, genres);
        spinnerGenre.setAdapter(adapter);

        spinnerGenre.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentGenreFilter = genres[position];
                applyFilters();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
    }

    private void setupSearchView() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) { return false; }

            @Override
            public boolean onQueryTextChange(String newText) {
                currentSearchQuery = newText;
                applyFilters();
                return true;
            }
        });
    }

    private void applyFilters() {
        // Tampilkan loading dan sembunyikan daftar buku (Main Thread)
        progressBar.setVisibility(View.VISIBLE);
        rvBooks.setVisibility(View.GONE);

        // Jalankan proses pencarian di Background Thread
        executorService.execute(() -> {
            try {
                // Simulasi proses data yang berat agar loading terlihat berputar
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            ArrayList<Book> filteredList = new ArrayList<>();
            for (Book book : displayList) {
                boolean matchesSearch = book.getTitle().toLowerCase().contains(currentSearchQuery.toLowerCase());
                boolean matchesGenre = currentGenreFilter.equals("Semua Genre") ||
                        book.getGenre().toLowerCase().contains(currentGenreFilter.toLowerCase());

                if (matchesSearch && matchesGenre) {
                    filteredList.add(book);
                }
            }

            // Kembalikan hasil ke Main Thread untuk update UI
            handler.post(() -> {
                progressBar.setVisibility(View.GONE);
                rvBooks.setVisibility(View.VISIBLE);
                if (bookAdapter != null) {
                    bookAdapter.setFilteredList(filteredList);
                }
            });
        });
    }
}