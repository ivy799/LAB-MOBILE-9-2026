package com.library.app.ui.favorites;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.library.app.R;
import com.library.app.adapter.BookAdapter;
import com.library.app.model.Book;
import com.library.app.model.BookRepository;
import com.library.app.ui.detail.DetailActivity;

import java.util.List;

public class FavoritesFragment extends Fragment implements BookAdapter.OnBookClickListener {

    private BookAdapter bookAdapter;
    private TextView tvEmpty;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorites, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvEmpty = view.findViewById(R.id.tv_empty);

        RecyclerView rvFavorites = view.findViewById(R.id.rv_favorites);
        rvFavorites.setLayoutManager(new LinearLayoutManager(getContext()));

        List<Book> likedBooks = BookRepository.getInstance().getLikedBooks();
        bookAdapter = new BookAdapter(getContext(), likedBooks, this);
        rvFavorites.setAdapter(bookAdapter);

        updateEmptyState(likedBooks);
    }

    @Override
    public void onResume() {
        super.onResume();
        List<Book> likedBooks = BookRepository.getInstance().getLikedBooks();
        bookAdapter.updateList(likedBooks);
        updateEmptyState(likedBooks);
    }

    private void updateEmptyState(List<Book> books) {
        tvEmpty.setVisibility(books.isEmpty() ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onBookClick(Book book) {
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_BOOK_ID, book.getId());
        startActivity(intent);
    }
}
