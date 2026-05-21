package com.example.tp02.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tp02.R;
import com.example.tp02.model.Story;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.StoryViewHolder> {

    private Context context;
    private List<Story> stories;
    private OnStoryClickListener listener;

    public interface OnStoryClickListener {
        void onStoryClick(Story story, int position);
    }

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

        if (story.getCoverImageUri() != null) {
            Glide.with(context).load(story.getCoverImageUri()).centerCrop().into(holder.imgStory);
        } else {
            Glide.with(context).load(story.getCoverImageRes()).centerCrop().into(holder.imgStory);
        }

        holder.tvTitle.setText(story.getTitle());
        holder.itemView.setOnClickListener(v -> listener.onStoryClick(story, position));
    }

    @Override
    public int getItemCount() { return stories.size(); }

    public static class StoryViewHolder extends RecyclerView.ViewHolder {
        CircleImageView imgStory;
        TextView tvTitle;

        public StoryViewHolder(@NonNull View itemView) {
            super(itemView);
            imgStory = itemView.findViewById(R.id.imgStory);
            tvTitle = itemView.findViewById(R.id.tvTitle);
        }
    }
}