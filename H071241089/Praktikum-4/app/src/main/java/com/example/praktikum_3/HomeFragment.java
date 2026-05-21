package com.example.praktikum_3;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HomeFragment extends Fragment {

    private RecyclerView rvBooks;
    private BookAdapter adapter;
    private List<Book> allBooks;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvBooks = view.findViewById(R.id.rv_books);
        SearchView searchView = view.findViewById(R.id.search_view);

        allBooks = BookRepository.getBooks();
        adapter = new BookAdapter(allBooks, book -> {
            Intent intent = new Intent(getActivity(), DetailActivity.class);
            intent.putExtra("EXTRA_BOOK", book);
            startActivity(intent);
        });

        rvBooks.setLayoutManager(new LinearLayoutManager(getContext()));
        rvBooks.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterBooks(newText);
                return true;
            }
        });
    }

    private void filterBooks(String query) {
        List<Book> filteredList = allBooks.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
        adapter.setBooks(filteredList);
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }
}
