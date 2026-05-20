package com.example.tp2.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tp2.R;
import com.example.tp2.activities.DetailFeedActivity;
import com.example.tp2.models.Feed;

import java.util.ArrayList;

public class ProfileFeedAdapter extends RecyclerView.Adapter<ProfileFeedAdapter.ViewHolder> {

    private final ArrayList<Feed> feeds;
    private final Context context;

    public ProfileFeedAdapter(Context context, ArrayList<Feed> feeds) {
        this.context = context;
        this.feeds = feeds;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_profile_feed, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Feed feed = feeds.get(position);

        holder.ivGridFeed.setImageResource(feed.getFeedImage());

        // Aksi klik untuk pindah ke halaman Detail Feed
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailFeedActivity.class);
            // Mengirim data feed yang diklik ke halaman detail
            intent.putExtra("EXTRA_FEED", feed);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return feeds.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivGridFeed;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivGridFeed = itemView.findViewById(R.id.iv_grid_feed);
        }
    }
}