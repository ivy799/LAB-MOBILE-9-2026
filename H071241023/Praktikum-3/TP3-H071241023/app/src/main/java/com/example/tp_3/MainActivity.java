package com.example.tp_3;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import com.example.tp_3.data.DataBook;
import com.example.tp_3.fragment.AddBookFragment;
import com.example.tp_3.fragment.FavoritesFragment;
import com.example.tp_3.fragment.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);

        loadFragment(new HomeFragment());

        bottomNav.setOnItemSelectedListener(item -> {
            Fragment fragment = null;

            if (item.getItemId() == R.id.nav_home) {
                fragment = new HomeFragment();
            } else if (item.getItemId() == R.id.nav_fav) {
                fragment = new FavoritesFragment();
            } else if (item.getItemId() == R.id.nav_add) {
                fragment = new AddBookFragment();
            }

            return loadFragment(fragment);
        });

        DataBook.initData();
    }
    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    public void setSearchViewVisibility(int visibility) {
        SearchView searchView = findViewById(R.id.searchView);
        if (searchView != null) {
            searchView.setVisibility(visibility);
        }
    }
}