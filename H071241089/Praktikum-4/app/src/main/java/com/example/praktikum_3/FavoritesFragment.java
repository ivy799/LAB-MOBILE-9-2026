package com.example.praktikum_3;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.stream.Collectors;

public class FavoritesFragment extends Fragment {

    private RecyclerView rvFavoriteBooks;
    private BookAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorites, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvFavoriteBooks = view.findViewById(R.id.rv_favorite_books);

        List<Book> favoriteBooks = getFavoriteBooks();
        adapter = new BookAdapter(favoriteBooks, book -> {
            Intent intent = new Intent(getActivity(), DetailActivity.class);
            intent.putExtra("EXTRA_BOOK", book);
            startActivity(intent);
        });

        rvFavoriteBooks.setLayoutManager(new LinearLayoutManager(getContext()));
        rvFavoriteBooks.setAdapter(adapter);
    }

    private List<Book> getFavoriteBooks() {
        return BookRepository.getBooks().stream()
                .filter(Book::isLiked)
                .collect(Collectors.toList());
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.setBooks(getFavoriteBooks());
    }
}
