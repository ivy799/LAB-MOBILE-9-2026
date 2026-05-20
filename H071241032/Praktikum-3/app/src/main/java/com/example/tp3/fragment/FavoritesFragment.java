package com.example.tp3.fragment; // SESUAIKAN

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tp3.DetailActivity; // SESUAIKAN
import com.example.tp3.R; // SESUAIKAN
import com.example.tp3.adapter.BookAdapter;
import com.example.tp3.model.Book;
import com.example.tp3.utils.DataHelper;

import java.util.ArrayList;

public class FavoritesFragment extends Fragment {

    private RecyclerView rvFavorites;
    private TextView tvEmpty;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);

        rvFavorites = view.findViewById(R.id.rv_favorites);
        tvEmpty = view.findViewById(R.id.tv_empty_favorites);

        // Langsung panggil fungsi muat data saat tab diklik!
        loadFavoriteBooks();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        // Berjaga-jaga jika kembali dari DetailActivity tanpa ganti tab
        loadFavoriteBooks();
    }

    private void loadFavoriteBooks() {
        ArrayList<Book> favoriteBooks = new ArrayList<>();

        // Tarik ulang data buku yang dilike dari DataHelper
        for (Book book : DataHelper.bookList) {
            if (book.isLiked()) {
                favoriteBooks.add(book);
            }
        }

        // --- BANTUAN DEBUG ---
        // Menampilkan pesan singkat di bawah layar untuk mengecek apakah aplikasi membaca data favoritnya.
        // Toast.makeText(getContext(), "Total Favorit: " + favoriteBooks.size(), Toast.LENGTH_SHORT).show();

        // Buat adapter baru setiap kali diload agar fresh
        BookAdapter bookAdapter = new BookAdapter(favoriteBooks);
        rvFavorites.setLayoutManager(new LinearLayoutManager(getContext()));
        rvFavorites.setAdapter(bookAdapter);

        bookAdapter.setOnItemClickListener(book -> {
            Intent intent = new Intent(getActivity(), DetailActivity.class);
            intent.putExtra("BOOK_ID", book.getId());
            startActivity(intent);
        });

        // Tampilkan/Sembunyikan teks vs RecyclerView
        if (favoriteBooks.isEmpty()) {
            tvEmpty.setVisibility(View.VISIBLE);
            rvFavorites.setVisibility(View.GONE);
        } else {
            tvEmpty.setVisibility(View.GONE);
            rvFavorites.setVisibility(View.VISIBLE);
        }
    }
}