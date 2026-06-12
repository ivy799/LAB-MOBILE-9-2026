package com.example.libraryapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.libraryapp.DetailActivity;
import com.example.libraryapp.R;
import com.example.libraryapp.adapter.BookAdapter;
import com.example.libraryapp.adapter.GenreChipAdapter;
import com.example.libraryapp.model.Book;
import com.example.libraryapp.util.BookRepository;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private BookAdapter bookAdapter;
    private RecyclerView rvBooks;
    private RecyclerView rvGenres;
    private EditText etSearch;
    private String currentGenre = "All";
    private String currentQuery = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvBooks = view.findViewById(R.id.rv_books);
        rvGenres = view.findViewById(R.id.rv_genres);
        etSearch = view.findViewById(R.id.et_search);

        setupGenreFilter();
        setupBookList();
        setupSearch();
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshBooks();
    }

    private void setupGenreFilter() {
        List<String> genres = BookRepository.getInstance().getGenres();
        GenreChipAdapter chipAdapter = new GenreChipAdapter(
                requireContext(), genres, genre -> {
            currentGenre = genre;
            refreshBooks();
        });
        rvGenres.setLayoutManager(
                new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        rvGenres.setAdapter(chipAdapter);
    }

    private void setupBookList() {
        List<Book> books = BookRepository.getInstance().getAllBooks();
        bookAdapter = new BookAdapter(requireContext(), books, book -> {
            Intent intent = new Intent(requireContext(), DetailActivity.class);
            intent.putExtra("book_id", book.getId());
            startActivity(intent);
        });
        rvBooks.setLayoutManager(new LinearLayoutManager(requireContext()));
        rvBooks.setAdapter(bookAdapter);
    }

    private void setupSearch() {
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                currentQuery = s.toString();
                refreshBooks();
            }
            @Override public void afterTextChanged(Editable s) {}
        });
    }

    private void refreshBooks() {
        List<Book> base = BookRepository.getInstance().getBooksByGenre(currentGenre);
        if (currentQuery.isEmpty()) {
            bookAdapter.updateList(base);
        } else {
            List<Book> filtered = new ArrayList<>();
            String query = currentQuery.toLowerCase();
            for (Book b : base) {
                if (b.getTitle().toLowerCase().contains(query) ||
                    b.getAuthor().toLowerCase().contains(query)) {
                    filtered.add(b);
                }
            }
            bookAdapter.updateList(filtered);
        }
    }
}
