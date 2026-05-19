package com.example.tuprak_2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.recycleview.R;
import com.example.recycleview.model.Post;
import java.util.List;

public class ProfileGridAdapter extends RecyclerView.Adapter<ProfileGridAdapter.GridViewHolder> {

    public interface OnPostClickListener {
        void onPostClick(Post post);
    }

    private Context context;
    private List<Post> posts;
    private OnPostClickListener listener;

    public ProfileGridAdapter(Context context, List<Post> posts, OnPostClickListener listener) {
        this.context = context;
        this.posts = posts;
        this.listener = listener;
    }

    @NonNull
    @Override
    public GridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_grid_post, parent, false);
        return new GridViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GridViewHolder holder, int position) {
        Post post = posts.get(position);

        if (post.hasUri()) {
            Glide.with(context).load(post.getImageUri()).centerCrop().into(holder.ivPost);
        } else if (post.getImageRes() > 0) {
            Glide.with(context).load(post.getImageRes()).centerCrop().into(holder.ivPost);
        }

        holder.itemView.setOnClickListener(v -> listener.onPostClick(post));
    }

    @Override
    public int getItemCount() { return posts.size(); }

    public void addPost(Post post) {
        posts.add(0, post);
        notifyItemInserted(0);
    }

    static class GridViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPost;

        GridViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPost = itemView.findViewById(R.id.iv_grid_post);
        }
    }
}
