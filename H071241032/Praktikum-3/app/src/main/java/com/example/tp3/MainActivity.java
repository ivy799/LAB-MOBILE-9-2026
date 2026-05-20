package com.example.tp3;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.tp3.fragment.AddBookFragment;
import com.example.tp3.fragment.FavoritesFragment;
import com.example.tp3.fragment.HomeFragment;
import com.example.tp3.utils.DataHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Inisialisasi data dummy saat aplikasi pertama kali dibuka
        DataHelper.initDummyData();

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);

        // 2. Menampilkan HomeFragment sebagai halaman default awal
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new HomeFragment())
                    .commit();
        }

        // 3. Mengatur klik pada navigasi bawah
// 3. Mengatur klik pada navigasi bawah
        bottomNav.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            int itemId = item.getItemId();

            if (itemId == R.id.nav_home) {
                selectedFragment = new HomeFragment();
            } else if (itemId == R.id.nav_add) {
                // UBAH BARIS INI
                selectedFragment = new AddBookFragment();
            } else if (itemId == R.id.nav_favorites) {
                // UBAH BARIS INI JUGA
                selectedFragment = new FavoritesFragment();
            }

            // Ganti fragment yang ada di kontainer
            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, selectedFragment)
                        .commit();
            }
            return true;
        });
    }

    private class AddBookFragment extends Fragment {
    }
}