package com.example.praktikum_2.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.praktikum_2.R;
import com.example.praktikum_2.models.Story;

import java.util.List;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.ViewHolder> {

    private List<Story> stories;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Story story);
    }

    public StoryAdapter(List<Story> stories, OnItemClickListener listener) {
        this.stories = stories;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_story, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Story story = stories.get(position);
        holder.tvUsername.setText(story.isYourStory() ? "Your story" : story.getUsername());
        holder.ivProfile.setImageResource(story.getProfileImageResId());
        
        if (story.isYourStory()) {
            holder.ivAdd.setVisibility(View.VISIBLE);
            holder.ivBorder.setVisibility(View.GONE);
        } else {
            holder.ivAdd.setVisibility(View.GONE);
            holder.ivBorder.setVisibility(View.VISIBLE);
        }

        View.OnClickListener clickListener = v -> {
            if (listener != null) {
                listener.onItemClick(story);
            }
        };

        holder.ivProfile.setOnClickListener(clickListener);
        holder.tvUsername.setOnClickListener(clickListener);
    }

    @Override
    public int getItemCount() {
        return stories.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivProfile, ivBorder, ivAdd;
        TextView tvUsername;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProfile = itemView.findViewById(R.id.iv_story_profile);
            ivBorder = itemView.findViewById(R.id.iv_story_border);
            ivAdd = itemView.findViewById(R.id.iv_add_story);
            tvUsername = itemView.findViewById(R.id.tv_story_username);
        }
    }
}