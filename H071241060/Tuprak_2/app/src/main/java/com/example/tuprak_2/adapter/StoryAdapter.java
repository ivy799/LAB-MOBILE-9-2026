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
import com.example.recycleview.model.Story;
import java.util.List;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.StoryViewHolder> {

    public interface OnStoryClickListener {
        void onStoryClick(Story story);
    }

    private Context context;
    private List<Story> stories;
    private OnStoryClickListener listener;

    public StoryAdapter(Context context, List<Story> stories, OnStoryClickListener listener) {
        this.context = context;
        this.stories = stories;
        this.listener = listener;
    }

    @NonNull
    @Override
    public StoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_story, parent, false);
        return new StoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoryViewHolder holder, int position) {
        Story story = stories.get(position);

        holder.tvTitle.setText(story.getTitle());
        Glide.with(context).load(story.getCoverImageRes()).centerCrop().into(holder.ivCover);

        // ring warna berbeda jika sudah dilihat
        holder.ivRing.setImageResource(story.isSeen()
                ? R.drawable.bg_story_ring_seen
                : R.drawable.bg_story_ring);

        holder.itemView.setOnClickListener(v -> {
            story.setSeen(true);
            notifyItemChanged(position);
            listener.onStoryClick(story);
        });
    }

    @Override
    public int getItemCount() { return stories.size(); }

    static class StoryViewHolder extends RecyclerView.ViewHolder {
        ImageView ivCover, ivRing;
        TextView tvTitle;

        StoryViewHolder(@NonNull View itemView) {
            super(itemView);
            ivCover = itemView.findViewById(R.id.iv_story_cover);
            ivRing  = itemView.findViewById(R.id.iv_story_ring);
            tvTitle = itemView.findViewById(R.id.tv_story_title);
        }
    }
}
