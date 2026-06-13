package com.example.tuprak_2;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ProfileGridAdapter extends RecyclerView.Adapter<ProfileGridAdapter.ViewHolder> {
    private ArrayList<Post> listPost;

    public ProfileGridAdapter(ArrayList<Post> listPost) {
        this.listPost = listPost;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = listPost.get(position);
        
        // Handle image from Resource or Uri
        if (post.getPostImageResource() != -1) {
            holder.ivGridImage.setImageResource(post.getPostImageResource());
        } else if (post.getPostImageUri() != null) {
            holder.ivGridImage.setImageURI(Uri.parse(post.getPostImageUri()));
        }

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), DetailFeedActivity.class);
            intent.putExtra("post", post);
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return listPost.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivGridImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivGridImage = itemView.findViewById(R.id.iv_grid_image);
        }
    }
}
