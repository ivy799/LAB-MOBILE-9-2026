package com.example.tp3.fragment; // SESUAIKAN DENGAN PACKAGE-MU

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

public class HomeFragment extends Fragment {

    private RecyclerView rvBooks;
    private BookAdapter bookAdapter;
    private SearchView searchView;
    private Spinner spinnerGenre;
    private ArrayList<Book> displayList;

    // Menyimpan status pencarian dan filter saat ini
    private String currentSearchQuery = "";
    private String currentGenreFilter = "Semua Genre";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        rvBooks = view.findViewById(R.id.rv_books);
        searchView = view.findViewById(R.id.search_view);
        spinnerGenre = view.findViewById(R.id.spinner_genre);

        setupRecyclerView();
        setupSpinner();
        setupSearchView();

        return view;
    }

    // Karena di tugas dibilang "Buku terbaru tampil di urutan atas", kita balik urutannya di onResume
    @Override
    public void onResume() {
        super.onResume();
        displayList = new ArrayList<>(DataHelper.bookList);
        Collections.reverse(displayList); // Balik urutan agar yang baru diinput ada di atas
        applyFilters(); // Terapkan filter ulang setiap kali tab dibuka
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
        // Daftar kategori genre (bisa ditambah sesuai kebutuhan)
        String[] genres = {"Semua Genre", "Fiksi", "Sejarah", "Pengembangan Diri", "Sains", "Bisnis"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, genres);
        spinnerGenre.setAdapter(adapter);

        spinnerGenre.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentGenreFilter = genres[position];
                applyFilters(); // Jalankan filter setiap kali kategori diubah
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
                applyFilters(); // Jalankan filter setiap kali teks diketik
                return true;
            }
        });
    }

    // Fungsi gabungan: Menyaring berdasarkan Teks Pencarian DAN Pilihan Genre
    private void applyFilters() {
        ArrayList<Book> filteredList = new ArrayList<>();

        for (Book book : displayList) {
            // Cek apakah judul buku cocok dengan pencarian
            boolean matchesSearch = book.getTitle().toLowerCase().contains(currentSearchQuery.toLowerCase());

            // Cek apakah genre cocok (atau jika memilih "Semua Genre", maka selalu true)
            boolean matchesGenre = currentGenreFilter.equals("Semua Genre") ||
                    book.getGenre().toLowerCase().contains(currentGenreFilter.toLowerCase());

            // Jika buku memenuhi KEDUA syarat tersebut, masukkan ke daftar yang akan ditampilkan
            if (matchesSearch && matchesGenre) {
                filteredList.add(book);
            }
        }

        if (bookAdapter != null) {
            bookAdapter.setFilteredList(filteredList);
        }
    }
}