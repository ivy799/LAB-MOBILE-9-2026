package com.example.tp_3.fragment;import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
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

public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private BookAdapter adapter;
    private SearchView searchView;

    public HomeFragment() {}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.rv_books);
        searchView = getActivity().findViewById(R.id.searchView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new BookAdapter(new ArrayList<>(DataBook.listBook));
        recyclerView.setAdapter(adapter);

        setupSearchView();

        return view;
    }

    private void setupSearchView() {
        searchView.setIconified(false);
        searchView.setQueryHint("Cari buku...");
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });
    }

    private void filterList(String text) {
        ArrayList<Book> filteredList = new ArrayList<>();

        for (Book book : DataBook.listBook) {
            if (book.getTitle().toLowerCase().contains(text.toLowerCase()) ||
                    book.getAuthor().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(book);
            }
        }

        adapter.setFilteredList(filteredList);
    }

    @Override
    public void onResume() {
        super.onResume();
        filterList(searchView.getQuery().toString());
        ((MainActivity) getActivity()).setSearchViewVisibility(View.VISIBLE);
    }
}