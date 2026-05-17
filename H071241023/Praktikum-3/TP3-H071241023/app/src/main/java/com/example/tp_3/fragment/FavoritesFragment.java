package com.example.tp_3.fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tp_3.MainActivity;
import com.example.tp_3.R;
import com.example.tp_3.adaptor.BookAdapter;
import com.example.tp_3.data.DataBook;
import com.example.tp_3.model.Book;

import java.util.ArrayList;

public class FavoritesFragment extends Fragment {

    RecyclerView recyclerView;

    public FavoritesFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_favorites, container, false);
        recyclerView = view.findViewById(R.id.fav);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        loadFav();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).setSearchViewVisibility(View.GONE);
        loadFav();
    }

    private void loadFav() {
        ArrayList<Book> favList = new ArrayList<>();

        for (Book b : DataBook.listBook) {
            if (b.isLiked) {
                favList.add(b);
            }
        }
        recyclerView.setAdapter(new BookAdapter(favList));
    }
}