package com.example.tp2.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tp2.R;
import com.example.tp2.activities.ProfileActivity;
import com.example.tp2.models.Feed;

import java.util.ArrayList;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder> {

    private final Context context;
    private final ArrayList<Feed> listFeed;

    public FeedAdapter(Context context, ArrayList<Feed> listFeed) {
        this.context = context;
        this.listFeed = listFeed;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_feed, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Feed feed = listFeed.get(position);

        // Mengatur data secara dinamis ke komponen view
        holder.tvUsernameHeader.setText(feed.getUsername());
        holder.ivProfile.setImageResource(feed.getProfileImage());
        holder.ivFeed.setImageResource(feed.getFeedImage());
        holder.tvCaptionUser.setText(feed.getUsername());
        holder.tvCaption.setText(feed.getCaption());

        // Klik foto profil untuk pindah ke ProfileActivity
        holder.ivProfile.setOnClickListener(v -> {
            Intent intent = new Intent(context, ProfileActivity.class);
            intent.putExtra("EXTRA_USERNAME", feed.getUsername());
            intent.putExtra("EXTRA_PROFILE_IMAGE", feed.getProfileImage());
            context.startActivity(intent);
        });

        // Klik username atas untuk pindah ke ProfileActivity
        holder.tvUsernameHeader.setOnClickListener(v -> {
            Intent intent = new Intent(context, ProfileActivity.class);
            intent.putExtra("EXTRA_USERNAME", feed.getUsername());
            intent.putExtra("EXTRA_PROFILE_IMAGE", feed.getProfileImage());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return listFeed.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivProfile, ivFeed;
        TextView tvUsernameHeader, tvCaptionUser, tvCaption;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Di sini ID-nya sudah disamakan 100% dengan item_feed.xml milikmu
            ivProfile = itemView.findViewById(R.id.iv_profile);
            ivFeed = itemView.findViewById(R.id.iv_feed_image);
            tvUsernameHeader = itemView.findViewById(R.id.tv_username);
            tvCaptionUser = itemView.findViewById(R.id.tv_caption_username);
            tvCaption = itemView.findViewById(R.id.tv_caption);
        }
    }
}