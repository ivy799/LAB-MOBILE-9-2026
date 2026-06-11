package com.example.tuprak2.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tuprak2.R;
import com.example.tuprak2.ProfileActivity;
import com.example.tuprak2.model.FeedModel;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.FeedViewHolder> {

    private Context context;
    private ArrayList<FeedModel> feedList;

    public FeedAdapter(Context context, ArrayList<FeedModel> feedList) {
        this.context = context;
        this.feedList = feedList;
    }

    @NonNull
    @Override
    public FeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_feed, parent, false);
        return new FeedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedViewHolder holder, int position) {
        FeedModel feed = feedList.get(position);

        Glide.with(context)
            .load(feed.getProfileImageUrl())
            .placeholder(R.drawable.placeholder_profile)
            .error(R.drawable.placeholder_profile)
            .circleCrop()
            .into(holder.imgProfile);
            
        Glide.with(context)
            .load(feed.getPostImageUrl())
            .placeholder(R.drawable.placeholder_image)
            .error(R.drawable.placeholder_image)
            .into(holder.imgPost);
        
        holder.tvUsername.setText(feed.getUsername());
        holder.tvLikes.setText(feed.getLikes() + " likes");
        holder.tvCaptionUsername.setText(feed.getUsername());
        holder.tvCaption.setText(feed.getCaption());
        holder.tvTimeAgo.setText(feed.getTimeAgo());

        // Navigate to profile when clicking username or profile picture
        View.OnClickListener goToProfile = v -> {
            Intent intent = new Intent(context, ProfileActivity.class);
            context.startActivity(intent);
        };

        holder.imgProfile.setOnClickListener(goToProfile);
        holder.tvUsername.setOnClickListener(goToProfile);
        holder.tvCaptionUsername.setOnClickListener(goToProfile);
    }

    @Override
    public int getItemCount() {
        return feedList.size();
    }

    public static class FeedViewHolder extends RecyclerView.ViewHolder {
        ImageView imgProfile, imgPost;
        TextView tvUsername, tvLikes, tvCaptionUsername, tvCaption, tvTimeAgo;

        public FeedViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProfile = itemView.findViewById(R.id.imgProfile);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            imgPost = itemView.findViewById(R.id.imgPost);
            tvLikes = itemView.findViewById(R.id.tvLikes);
            tvCaptionUsername = itemView.findViewById(R.id.tvCaptionUsername);
            tvCaption = itemView.findViewById(R.id.tvCaption);
            tvTimeAgo = itemView.findViewById(R.id.tvTimeAgo);
        }
    }
}
