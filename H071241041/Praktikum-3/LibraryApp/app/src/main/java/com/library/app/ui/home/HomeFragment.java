package com.library.app.ui.home;

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

import com.library.app.R;
import com.library.app.adapter.BookAdapter;
import com.library.app.adapter.GenreFilterAdapter;
import com.library.app.model.Book;
import com.library.app.model.BookRepository;
import com.library.app.ui.detail.DetailActivity;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements BookAdapter.OnBookClickListener {

    private BookAdapter bookAdapter;
    private GenreFilterAdapter genreAdapter;
    private List<Book> filteredList;
    private String currentGenre = "All";
    private String currentQuery = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        filteredList = new ArrayList<>(BookRepository.getInstance().getAllBooks());

        // RecyclerView books
        RecyclerView rvBooks = view.findViewById(R.id.rv_books);
        rvBooks.setLayoutManager(new LinearLayoutManager(getContext()));
        bookAdapter = new BookAdapter(getContext(), filteredList, this);
        rvBooks.setAdapter(bookAdapter);

        // RecyclerView genre filter
        RecyclerView rvGenres = view.findViewById(R.id.rv_genres);
        rvGenres.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        List<String> genres = BookRepository.getInstance().getAllGenres();
        genreAdapter = new GenreFilterAdapter(getContext(), genres, genre -> {
            currentGenre = genre;
            applyFilter();
        });
        rvGenres.setAdapter(genreAdapter);

        // SearchView
        EditText etSearch = view.findViewById(R.id.et_search);
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                currentQuery = s.toString().trim();
                applyFilter();
            }
            @Override public void afterTextChanged(Editable s) {}
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        applyFilter(); // refresh like badges
    }

    private void applyFilter() {
        List<Book> all = BookRepository.getInstance().getAllBooks();
        filteredList.clear();
        for (Book book : all) {
            boolean matchesGenre = currentGenre.equals("All") || book.getGenre().equals(currentGenre);
            boolean matchesQuery = currentQuery.isEmpty()
                || book.getTitle().toLowerCase().contains(currentQuery.toLowerCase());
            if (matchesGenre && matchesQuery) {
                filteredList.add(book);
            }
        }
        bookAdapter.updateList(filteredList);
    }

    @Override
    public void onBookClick(Book book) {
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_BOOK_ID, book.getId());
        startActivity(intent);
    }
}
