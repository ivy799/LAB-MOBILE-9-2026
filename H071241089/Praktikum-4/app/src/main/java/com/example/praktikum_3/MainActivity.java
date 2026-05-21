package com.example.praktikum_3;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.praktikum_3.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        androidx.navigation.fragment.NavHostFragment navHostFragment = (androidx.navigation.fragment.NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_content_main);
        NavController navController = navHostFragment.getNavController();
        
        // Connect BottomNavigationView with NavController
        NavigationUI.setupWithNavController(binding.bottomNavigation, navController);
        
        // Set up ActionBar with NavController
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.homeFragment, R.id.favoritesFragment, R.id.addBookFragment)
                .build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
    }

    @Override
    public boolean onSupportNavigateUp() {
        androidx.navigation.fragment.NavHostFragment navHostFragment = (androidx.navigation.fragment.NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_content_main);
        NavController navController = navHostFragment.getNavController();
        return NavigationUI.navigateUp(navController, new AppBarConfiguration.Builder(navController.getGraph()).build())
                || super.onSupportNavigateUp();
    }
}
