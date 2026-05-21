package com.example.praktikum_2;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.example.praktikum_2.fragments.HomeFragment;
import com.example.praktikum_2.fragments.PostFragment;
import com.example.praktikum_2.fragments.ProfileFragment;
import com.example.praktikum_2.models.Post;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Post> allPosts = new ArrayList<>();
    private List<Post> myPosts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDummyData();

        BottomNavigationView navView = findViewById(R.id.bottom_navigation);
        navView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            int itemId = item.getItemId();
            if (itemId == R.id.nav_home) {
                selectedFragment = new HomeFragment();
            } else if (itemId == R.id.nav_post) {
                selectedFragment = new PostFragment();
            } else if (itemId == R.id.nav_profile) {
                selectedFragment = new ProfileFragment();
            }

            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, selectedFragment)
                        .commit();
            }
            return true;
        });

        // Set default fragment
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new HomeFragment())
                    .commit();
        }
    }

    private void initDummyData() {
        // Initial dummy data for Home
        allPosts.add(new Post(R.drawable.posts_1, "batararezqi", R.drawable.avatar_1, "TEKNIK BERDUKA UNHAS NO EMPATHY", "1.234 likes", "8h"));
        allPosts.add(new Post(R.drawable.posts_2, "rarahaidirr", R.drawable.avatar_2, "Beautiful day at the campus! #Unhas", "856 likes", "12h"));
        allPosts.add(new Post(R.drawable.story_1, "_nnachaa", R.drawable.avatar_3, "Sunset vibes", "432 likes", "1d"));
        
        // Initial dummy data for Profile
        myPosts.add(new Post(R.drawable.posts_1, "Current User", R.drawable.avatar_6, "My latest project progress", "102 likes", "2d"));
        myPosts.add(new Post(R.drawable.posts_2, "Current User", R.drawable.avatar_6, "Throwback to last weekend", "56 likes", "5d"));
    }

    public List<Post> getAllPosts() {
        return allPosts;
    }

    public List<Post> getMyPosts() {
        return myPosts;
    }

    public void addPost(Post post) {
        myPosts.add(0, post);
        allPosts.add(0, post);
    }
}