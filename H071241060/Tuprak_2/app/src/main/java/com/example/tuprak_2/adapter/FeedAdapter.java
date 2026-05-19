package com.example.tuprak_2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.recycleview.R;
import com.example.recycleview.model.Post;
import java.util.List;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.FeedViewHolder> {

    public interface OnItemClickListener {
        void onUserClick(String userId);
        void onPostClick(Post post);
        void onLikeClick(Post post, int position);
    }

    private Context context;
    private List<Post> posts;
    private OnItemClickListener listener;

    public FeedAdapter(Context context, List<Post> posts, OnItemClickListener listener) {
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

        holder.tvUsername.setText(post.getUsername());
        holder.tvUsernameBottom.setText(post.getUsername());
        holder.tvCaption.setText(post.getCaption());
        holder.tvLikeCount.setText(post.getLikeCount() + " suka");
        holder.tvCommentCount.setText(post.getCommentCount() + " komentar");
        holder.tvTimeAgo.setText(post.getTimeAgo());

        // Profile image
        if (post.getProfileImageRes() > 0) {
            holder.ivProfile.setImageResource(post.getProfileImageRes());
        }

        // Post image
        if (post.hasUri()) {
            Glide.with(context).load(post.getImageUri()).centerCrop().into(holder.ivPost);
        } else if (post.getImageRes() > 0) {
            Glide.with(context).load(post.getImageRes()).centerCrop().into(holder.ivPost);
        }

        // Like state
        holder.ivLike.setImageResource(post.isLiked()
                ? R.drawable.ic_heart_filled
                : R.drawable.ic_heart_outline);

        // Click listeners
        holder.ivProfile.setOnClickListener(v -> listener.onUserClick(post.getUserId()));
        holder.tvUsername.setOnClickListener(v -> listener.onUserClick(post.getUserId()));
        holder.ivPost.setOnClickListener(v -> listener.onPostClick(post));
        holder.ivLike.setOnClickListener(v -> {
            int pos = holder.getAdapterPosition();
            if (pos != RecyclerView.NO_ID) {
                listener.onLikeClick(post, pos);
            }
        });
    }

    @Override
    public int getItemCount() { return posts.size(); }

    public void addPost(Post post) {
        posts.add(0, post);
        notifyItemInserted(0);
    }

    public void notifyLikeChanged(int position) {
        notifyItemChanged(position);
    }

    static class FeedViewHolder extends RecyclerView.ViewHolder {
        ImageView ivProfile, ivPost, ivLike, ivComment, ivShare, ivBookmark;
        TextView tvUsername, tvUsernameBottom, tvCaption, tvLikeCount, tvCommentCount, tvTimeAgo;

        FeedViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProfile       = itemView.findViewById(R.id.iv_profile);
            ivPost          = itemView.findViewById(R.id.iv_post);
            ivLike          = itemView.findViewById(R.id.iv_like);
            ivComment       = itemView.findViewById(R.id.iv_comment);
            ivShare         = itemView.findViewById(R.id.iv_share);
            ivBookmark      = itemView.findViewById(R.id.iv_bookmark);
            tvUsername      = itemView.findViewById(R.id.tv_username);
            tvUsernameBottom = itemView.findViewById(R.id.tv_username_bottom);
            tvCaption       = itemView.findViewById(R.id.tv_caption);
            tvLikeCount     = itemView.findViewById(R.id.tv_like_count);
            tvCommentCount  = itemView.findViewById(R.id.tv_comment_count);
            tvTimeAgo       = itemView.findViewById(R.id.tv_time_ago);
        }
    }
}
