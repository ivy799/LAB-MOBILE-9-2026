package com.example.tp02.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tp02.R;
import com.example.tp02.model.Post;
import com.example.tp02.ui.SquareImageView;

import java.util.List;

public class ProfileGridAdapter extends RecyclerView.Adapter<ProfileGridAdapter.GridViewHolder> {

    private Context context;
    private List<Post> posts;
    private OnGridItemClickListener listener;

    public interface OnGridItemClickListener {
        void onGridItemClick(Post post, int position);
    }

    public ProfileGridAdapter(Context context, List<Post> posts, OnGridItemClickListener listener) {
        this.context = context;
        this.posts = posts;
        this.listener = listener;
    }

    @NonNull
    @Override
    public GridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_profile_grid, parent, false);
        return new GridViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GridViewHolder holder, int position) {
        Post post = posts.get(position);

        if (post.getPostImageUri() != null) {
            Glide.with(context).load(post.getPostImageUri()).centerCrop().into(holder.imgGrid);
        } else {
            Glide.with(context).load(post.getPostImageRes()).centerCrop().into(holder.imgGrid);
        }

        holder.itemView.setOnClickListener(v -> listener.onGridItemClick(post, holder.getAdapterPosition()));
    }

    @Override
    public int getItemCount() { return posts.size(); }

    public static class GridViewHolder extends RecyclerView.ViewHolder {
        SquareImageView imgGrid;

        public GridViewHolder(@NonNull View itemView) {
            super(itemView);
            imgGrid = itemView.findViewById(R.id.imgGrid);
        }
    }
}