package com.example.tp_2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tp_2.R;
import com.example.tp_2.adapter.FeedAdapter;
import com.example.tp_2.adapter.StoryAdapter;
import com.example.tp_2.data.DummyData;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvFeed, rvStory;
    FeedAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvFeed   = findViewById(R.id.rv_feed);
        rvStory  = findViewById(R.id.rv_story);
        ImageView navAdd     = findViewById(R.id.nav_add);
        ImageView navProfile = findViewById(R.id.nav_profile);

        // FIX: initData dan initStory keduanya dipanggil di sini
        DummyData.initData();
        DummyData.initStory();

        rvFeed.setLayoutManager(new LinearLayoutManager(this));
        rvStory.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        );

        adapter = new FeedAdapter(DummyData.feeds);
        rvFeed.setAdapter(adapter);
        rvStory.setAdapter(new StoryAdapter(DummyData.stories));

        navAdd.setOnClickListener(v ->
                startActivity(new Intent(this, AddPostActivity.class)));

        navProfile.setOnClickListener(v ->
                startActivity(new Intent(this, ProfileActivity.class)));
    }

    @Override
    protected void onResume() {
        super.onResume();
        // FIX: refresh agar post baru yang diupload langsung muncul di home feed
        adapter.notifyDataSetChanged();
    }
}