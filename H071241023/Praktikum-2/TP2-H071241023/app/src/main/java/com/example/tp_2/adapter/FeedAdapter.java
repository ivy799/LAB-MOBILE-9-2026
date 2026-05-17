package com.example.tp_2.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tp_2.R;
import com.example.tp_2.activity.DetailActivity;
import com.example.tp_2.activity.ProfileActivity;
import com.example.tp_2.model.Feed;

import java.util.ArrayList;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder> {

    ArrayList<Feed> feeds;
    Context context;

    public FeedAdapter(ArrayList<Feed> feeds) {
        this.feeds = feeds;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_feed, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Feed feed = feeds.get(position);

        holder.tvUsername.setText(feed.getUsername());
        holder.tvCaption.setText(feed.getCaption());

        Glide.with(context)
                .load(Uri.parse(feed.getImageUri()))
                .into(holder.imgPost);

        Glide.with(context)
                .load(Uri.parse(feed.getProfileImageUri()))
                .into(holder.imgProfile);

        // Klik username/foto profil → ke ProfileActivity
        View.OnClickListener goToProfile = v -> {
            Intent intent = new Intent(context, ProfileActivity.class);
            context.startActivity(intent);
        };
        holder.tvUsername.setOnClickListener(goToProfile);
        holder.imgProfile.setOnClickListener(goToProfile);

        // FIX: klik gambar post → ke DetailActivity
        // Sebelumnya feed item tidak bisa diklik untuk lihat detail
        holder.imgPost.setOnClickListener(v -> {
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
        TextView tvUsername, tvCaption;
        ImageView imgPost, imgProfile;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            tvCaption  = itemView.findViewById(R.id.tvCaption);
            imgPost    = itemView.findViewById(R.id.imgPost);
            imgProfile = itemView.findViewById(R.id.imgProfile);
        }
    }
}