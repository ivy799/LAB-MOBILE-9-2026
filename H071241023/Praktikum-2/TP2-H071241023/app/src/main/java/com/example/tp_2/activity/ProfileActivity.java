package com.example.tp_2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tp_2.R;
import com.example.tp_2.adapter.ProfileAdapter;
import com.example.tp_2.data.DummyData;
import com.example.tp_2.model.Story;

public class ProfileActivity extends AppCompatActivity {

    TextView disUsername;
    RecyclerView rvProfile;
    ProfileAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);

        disUsername = findViewById(R.id.disUsername);
        rvProfile   = findViewById(R.id.rv_profile_post);

        rvProfile.setLayoutManager(new GridLayoutManager(this, 3));

        // FIX: initData() dan initStory() harus dipanggil SEBELUM apapun yang menggunakan data
        // Sebelumnya filterPosts() dipanggil sebelum initData() sehingga selalu kosong
        DummyData.initData();
        DummyData.initStory();
        DummyData.initProfileData();

        adapter = new ProfileAdapter(DummyData.profileFeeds);
        rvProfile.setAdapter(adapter);

        // FIX: highlight story di profil sekarang bisa diklik ke StoryDetailActivity
        // Sebelumnya highlight hanya dekorasi, tidak ada listener
        LinearLayout highlight1 = findViewById(R.id.highlight1);
        LinearLayout highlight2 = findViewById(R.id.highlight2);

        if (highlight1 != null && DummyData.stories.size() > 0) {
            highlight1.setOnClickListener(v -> openStory(DummyData.stories.get(0)));
        }
        if (highlight2 != null && DummyData.stories.size() > 1) {
            highlight2.setOnClickListener(v -> openStory(DummyData.stories.get(1)));
        }

        ImageView btnAddTop = findViewById(R.id.post_add);
        ImageView navAdd    = findViewById(R.id.nav_add);
        ImageView navHome   = findViewById(R.id.nav_home);

        if (btnAddTop != null) {
            btnAddTop.setOnClickListener(v ->
                    startActivity(new Intent(this, AddPostActivity.class)));
        }
        if (navAdd != null) {
            navAdd.setOnClickListener(v ->
                    startActivity(new Intent(this, AddPostActivity.class)));
        }
        if (navHome != null) {
            navHome.setOnClickListener(v ->
                    startActivity(new Intent(this, MainActivity.class)));
        }
    }

    private void openStory(Story story) {
        Intent intent = new Intent(this, StoryDetailActivity.class);
        intent.putExtra("story_data", story);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }
}