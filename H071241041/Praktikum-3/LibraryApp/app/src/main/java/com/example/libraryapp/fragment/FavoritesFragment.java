package com.example.libraryapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.libraryapp.DetailActivity;
import com.example.libraryapp.R;
import com.example.libraryapp.adapter.BookAdapter;
import com.example.libraryapp.model.Book;
import com.example.libraryapp.util.BookRepository;

import java.util.List;

public class FavoritesFragment extends Fragment {

    private BookAdapter bookAdapter;
    private LinearLayout layoutEmpty;
    private RecyclerView rvFavorites;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorites, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvFavorites = view.findViewById(R.id.rv_favorites);
        layoutEmpty = view.findViewById(R.id.layout_empty);

        List<Book> liked = BookRepository.getInstance().getLikedBooks();
        bookAdapter = new BookAdapter(requireContext(), liked, book -> {
            Intent intent = new Intent(requireContext(), DetailActivity.class);
            intent.putExtra("book_id", book.getId());
            startActivity(intent);
        });
        rvFavorites.setLayoutManager(new LinearLayoutManager(requireContext()));
        rvFavorites.setAdapter(bookAdapter);

        updateEmptyState(liked);
    }

    @Override
    public void onResume() {
        super.onResume();
        List<Book> liked = BookRepository.getInstance().getLikedBooks();
        if (bookAdapter != null) {
            bookAdapter.updateList(liked);
            updateEmptyState(liked);
        }
    }

    private void updateEmptyState(List<Book> liked) {
        if (liked.isEmpty()) {
            layoutEmpty.setVisibility(View.VISIBLE);
            rvFavorites.setVisibility(View.GONE);
        } else {
            layoutEmpty.setVisibility(View.GONE);
            rvFavorites.setVisibility(View.VISIBLE);
        }
    }
}
