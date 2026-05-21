package com.example.praktikum_2.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.praktikum_2.R;
import com.example.praktikum_2.models.Post;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    private List<Post> posts;
    private OnItemClickListener listener;
    private boolean isGridLayout;

    public interface OnItemClickListener {
        void onItemClick(Post post);
        void onProfileClick(Post post);
    }

    public PostAdapter(List<Post> posts, OnItemClickListener listener) {
        this(posts, listener, false);
    }

    public PostAdapter(List<Post> posts, OnItemClickListener listener, boolean isGridLayout) {
        this.posts = posts;
        this.listener = listener;
        this.isGridLayout = isGridLayout;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layoutId = isGridLayout ? R.layout.item_post_grid : R.layout.item_post;
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = posts.get(position);
        
        if (isGridLayout) {
            if (post.getImageUri() != null) {
                holder.ivFeed.setImageURI(android.net.Uri.parse(post.getImageUri()));
            } else {
                holder.ivFeed.setImageResource(post.getImageResId());
            }
            holder.ivFeed.setOnClickListener(v -> listener.onItemClick(post));
        } else {
            holder.tvUsername.setText(post.getUsername());
            holder.tvUsernameCaption.setText(post.getUsername());
            holder.tvCaption.setText(post.getCaption());
            holder.tvLikes.setText(post.getLikes());
            holder.tvTime.setText(post.getTime());
            
            if (post.getImageUri() != null) {
                holder.ivFeed.setImageURI(android.net.Uri.parse(post.getImageUri()));
            } else {
                holder.ivFeed.setImageResource(post.getImageResId());
            }
            
            if (post.getProfileImageUri() != null) {
                holder.ivProfile.setImageURI(android.net.Uri.parse(post.getProfileImageUri()));
            } else {
                holder.ivProfile.setImageResource(post.getProfileImageResId());
            }

            if (holder.layoutHeader != null) {
                holder.layoutHeader.setOnClickListener(v -> listener.onProfileClick(post));
            }
            holder.ivFeed.setOnClickListener(v -> listener.onItemClick(post));
        }
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivProfile, ivFeed;
        TextView tvUsername, tvUsernameCaption, tvCaption, tvLikes, tvTime;
        View layoutHeader;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProfile = itemView.findViewById(R.id.iv_profile_post);
            ivFeed = itemView.findViewById(R.id.iv_feed_post);
            tvUsername = itemView.findViewById(R.id.tv_username_post);
            tvUsernameCaption = itemView.findViewById(R.id.tv_username_caption);
            tvCaption = itemView.findViewById(R.id.tv_caption_post);
            tvLikes = itemView.findViewById(R.id.tv_likes);
            tvTime = itemView.findViewById(R.id.tv_time);
            layoutHeader = itemView.findViewById(R.id.iv_profile_post);
        }
    }
}