package com.example.tp02.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tp02.R;
import com.example.tp02.model.Post;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.FeedViewHolder> {

    private Context context;
    private List<Post> posts;
    private OnPostClickListener listener;

    public interface OnPostClickListener {
        void onProfileClick(Post post);
        void onPostClick(Post post);
        void onLikeClick(Post post, int position);
        void onBookmarkClick(Post post, int position);
    }

    public FeedAdapter(Context context, List<Post> posts, OnPostClickListener listener) {
        this.context = context;
        this.posts = posts;
        this.listener = listener;
    }

    @NonNull
    @Override
    public FeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_feed, parent, false);
        return new FeedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedViewHolder holder, int position) {
        Post post = posts.get(position);

        if (post.getProfileImageUri() != null) {
            Glide.with(context).load(post.getProfileImageUri()).placeholder(R.drawable.profile_main).into(holder.imgProfile);
        } else {
            Glide.with(context).load(post.getProfileImageRes()).into(holder.imgProfile);
        }

        if (post.getPostImageUri() != null) {
            Glide.with(context).load(post.getPostImageUri()).into(holder.imgPost);
        } else {
            Glide.with(context).load(post.getPostImageRes()).centerCrop().into(holder.imgPost);
        }

        holder.tvUsername.setText(post.getUsername());
        holder.tvUsernameBelow.setText(post.getUsername());
        holder.tvCaption.setText(post.getCaption());
        holder.tvLikeCount.setText(post.getLikeCount() + " likes");
        holder.tvTimeAgo.setText(post.getTimeAgo());

        holder.btnLike.setImageResource(post.isLiked() ? R.drawable.ic_heart_filled : R.drawable.ic_heart);
        holder.btnBookmark.setImageResource(post.isBookmarked() ? R.drawable.ic_bookmark_filled : R.drawable.ic_bookmark);

        holder.imgProfile.setOnClickListener(v -> listener.onProfileClick(post));
        holder.tvUsername.setOnClickListener(v -> listener.onProfileClick(post));
        holder.imgPost.setOnClickListener(v -> listener.onPostClick(post));

        holder.btnLike.setOnClickListener(v -> {
            listener.onLikeClick(post, holder.getAdapterPosition());
            holder.btnLike.setImageResource(post.isLiked() ? R.drawable.ic_heart_filled : R.drawable.ic_heart);
            holder.tvLikeCount.setText(post.getLikeCount() + " likes");
        });

        holder.btnBookmark.setOnClickListener(v -> {
            listener.onBookmarkClick(post, holder.getAdapterPosition());
            holder.btnBookmark.setImageResource(post.isBookmarked() ? R.drawable.ic_bookmark_filled : R.drawable.ic_bookmark);
        });
    }

    @Override
    public int getItemCount() { return posts.size(); }

    public static class FeedViewHolder extends RecyclerView.ViewHolder {
        CircleImageView imgProfile;
        ImageView imgPost, btnLike, btnComment, btnShare, btnBookmark;
        TextView tvUsername, tvUsernameBelow, tvCaption, tvLikeCount, tvTimeAgo;

        public FeedViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProfile = itemView.findViewById(R.id.imgProfile);
            imgPost = itemView.findViewById(R.id.imgPost);
            btnLike = itemView.findViewById(R.id.btnLike);
            btnComment = itemView.findViewById(R.id.btnComment);
            btnShare = itemView.findViewById(R.id.btnShare);
            btnBookmark = itemView.findViewById(R.id.btnBookmark);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            tvUsernameBelow = itemView.findViewById(R.id.tvUsernameBelow);
            tvCaption = itemView.findViewById(R.id.tvCaption);
            tvLikeCount = itemView.findViewById(R.id.tvLikeCount);
            tvTimeAgo = itemView.findViewById(R.id.tvTimeAgo);
        }
    }
}