package com.example.tuprak2.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tuprak2.R;
import com.example.tuprak2.DetailPostActivity;
import com.example.tuprak2.model.PostModel;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class PostGridAdapter extends RecyclerView.Adapter<PostGridAdapter.PostGridViewHolder> {

    private Context context;
    private ArrayList<PostModel> postList;

    public PostGridAdapter(Context context, ArrayList<PostModel> postList) {
        this.context = context;
        this.postList = postList;
    }

    @NonNull
    @Override
    public PostGridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post_grid, parent, false);
        return new PostGridViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostGridViewHolder holder, int position) {
        PostModel post = postList.get(position);

        Glide.with(context)
             .load(post.getImageUrl())
             .placeholder(R.drawable.placeholder_image)
             .error(R.drawable.placeholder_image)
             .into(holder.imgPost);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailPostActivity.class);
            intent.putExtra("POST_CAPTION", post.getCaption());
            intent.putExtra("POST_USERNAME", post.getUsername());
            intent.putExtra("IMAGE_URL", post.getImageUrl());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public static class PostGridViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPost;

        public PostGridViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPost = itemView.findViewById(R.id.imgPost);
        }
    }
}
