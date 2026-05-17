package com.example.tp_3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import com.example.tp_3.data.DataBook;
import com.example.tp_3.fragment.AddBookFragment;
import com.example.tp_3.fragment.FavoritesFragment;
import com.example.tp_3.fragment.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences prefs;
    private boolean isDarkMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        prefs = getSharedPreferences(LoginActivity.PREF_NAME, MODE_PRIVATE);
        isDarkMode = prefs.getBoolean(LoginActivity.KEY_DARK_MODE, false);
        applyDarkMode(isDarkMode);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton btnDarkMode = findViewById(R.id.btnDarkMode);
        updateDarkModeIcon(btnDarkMode);
        btnDarkMode.setOnClickListener(v -> {
            isDarkMode = !isDarkMode;
            prefs.edit().putBoolean(LoginActivity.KEY_DARK_MODE, isDarkMode).apply();
            applyDarkMode(isDarkMode);
            recreate();
        });

        ImageButton btnLogout = findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(v -> {
            String nim = prefs.getString(LoginActivity.KEY_LOGGED_NIM, "");
            prefs.edit()
                    .putBoolean(LoginActivity.KEY_IS_LOGGED_IN, false)
                    .remove(LoginActivity.KEY_LOGGED_NIM)
                    .apply();
            Toast.makeText(this, "Sampai jumpa, " + nim + "!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });

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

    private void applyDarkMode(boolean dark) {
        AppCompatDelegate.setDefaultNightMode(
                dark ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO);
    }

    private void updateDarkModeIcon(ImageButton btn) {
        btn.setImageResource(isDarkMode ? R.drawable.ic_light_mode : R.drawable.ic_dark_mode);
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