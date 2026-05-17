package com.example.tp_2.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tp_2.R;
import com.example.tp_2.activity.DetailActivity;
import com.example.tp_2.model.Feed;

import java.util.ArrayList;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ViewHolder> {

    ArrayList<Feed> feeds;
    Context context;

    public ProfileAdapter(ArrayList<Feed> feeds) {
        this.feeds = feeds;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_profile_grid, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Feed feed = feeds.get(position);

        // FIX: gunakan Glide bukan setImageURI
        // setImageURI tidak bisa load "android.resource://..." URI secara reliable
        // sehingga gambar profile grid tidak muncul
        Glide.with(context)
                .load(Uri.parse(feed.getImageUri()))
                .centerCrop()
                .into(holder.imgPost);

        // Klik → ke DetailActivity
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("detail_feed", feed);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return feeds.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPost;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPost = itemView.findViewById(R.id.imgPostGrid);
        }
    }
}