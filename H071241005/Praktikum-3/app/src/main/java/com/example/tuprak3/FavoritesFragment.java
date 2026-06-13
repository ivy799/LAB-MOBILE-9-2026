package com.example.tuprak3;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class FavoritesFragment extends Fragment {
    private RecyclerView rvFavorites;
    private BookAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);
        rvFavorites = view.findViewById(R.id.rvFavorites);
        rvFavorites.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        // Memperbarui daftar setiap kali fragment ditampilkan
        List<Book> likedBooks = new ArrayList<>();
        for (Book b : BookRepository.getInstance().getAllBooks()) {
            if (b.isLiked()) likedBooks.add(b);
        }

        adapter = new BookAdapter(likedBooks, book -> {
            Intent intent = new Intent(getActivity(), DetailActivity.class);
            intent.putExtra("BOOK_ID", book.getId());
            startActivity(intent);
        });
        rvFavorites.setAdapter(adapter);
    }
}
