package com.example.tuprak3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private SearchView searchView;
    private TextView tvEmptyState;
    
    private ArrayList<BookModel> fullList;
    private ArrayList<BookModel> filteredList;
    private BookAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.rv_books);
        searchView = view.findViewById(R.id.searchView);
        tvEmptyState = view.findViewById(R.id.tv_empty_state);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        // Init Data
        fullList = DataDummy.getBooks();
        filteredList = new ArrayList<>(fullList);

        // Setup Adapter
        adapter = new BookAdapter(getContext(), filteredList);
        recyclerView.setAdapter(adapter);

        // Search Listener
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.clearFocus();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return true;
            }
        });

        return view;
    }

    private void filter(String text) {
        filteredList.clear();

        if (text == null || text.trim().isEmpty()) {
            filteredList.addAll(fullList);
        } else {
            for (BookModel book : fullList) {
                if (book.getTitle().toLowerCase().contains(text.toLowerCase())) {
                    filteredList.add(book);
                }
            }
        }

        adapter.notifyDataSetChanged();

        if (filteredList.isEmpty()) {
            tvEmptyState.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            tvEmptyState.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        
        fullList = DataDummy.getBooks();
        
        String query = "";
        if (searchView != null && searchView.getQuery() != null) {
            query = searchView.getQuery().toString();
        }
        
        filter(query);
    }
}
