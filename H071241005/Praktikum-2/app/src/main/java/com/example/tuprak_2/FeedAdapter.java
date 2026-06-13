package com.example.tuprak_2;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder> {
    private ArrayList<Post> listFeed;

    public FeedAdapter(ArrayList<Post> listFeed) {
        this.listFeed = listFeed;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_feed, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = listFeed.get(position);
        holder.tvUsername.setText(post.getUsername());
        holder.tvCaptionUser.setText(post.getUsername());
        holder.tvCaption.setText(post.getCaption());
        holder.ivProfPic.setImageResource(post.getProfileImage());

        // Handle image from Resource or Uri
        if (post.getPostImageResource() != -1) {
            holder.ivPostImage.setImageResource(post.getPostImageResource());
        } else if (post.getPostImageUri() != null) {
            holder.ivPostImage.setImageURI(Uri.parse(post.getPostImageUri()));
        }

        holder.layoutHeader.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), ProfileActivity.class);
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return listFeed.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvUsername, tvCaptionUser, tvCaption;
        ImageView ivProfPic, ivPostImage;
        LinearLayout layoutHeader;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUsername = itemView.findViewById(R.id.tv_username);
            tvCaptionUser = itemView.findViewById(R.id.tv_caption_username);
            tvCaption = itemView.findViewById(R.id.tv_caption);
            ivProfPic = itemView.findViewById(R.id.iv_prof_pic);
            ivPostImage = itemView.findViewById(R.id.iv_post_image);
            layoutHeader = itemView.findViewById(R.id.layout_header);
        }
    }
}
