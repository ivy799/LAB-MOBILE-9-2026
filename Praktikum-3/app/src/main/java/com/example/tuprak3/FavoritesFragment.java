package com.example.tuprak3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FavoritesFragment extends Fragment {

    private RecyclerView recyclerView;
    private BookAdapter adapter;
    private ArrayList<BookModel> favoriteBooks;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);

        recyclerView = view.findViewById(R.id.rv_books_favorites);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        favoriteBooks = new ArrayList<>();
        adapter = new BookAdapter(getContext(), favoriteBooks);
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadFavorites();
    }

    private void loadFavorites() {
        if (favoriteBooks != null && adapter != null) {
            favoriteBooks.clear();
            ArrayList<BookModel> allBooks = DataDummy.getBooks();
            for (BookModel book : allBooks) {
                if (book.isFavorite()) {
                    favoriteBooks.add(book);
                }
            }
            adapter.notifyDataSetChanged();
        }
    }
}
