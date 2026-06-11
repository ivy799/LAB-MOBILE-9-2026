package com.example.tuprak2.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tuprak2.R;
import com.example.tuprak2.StoryDetailActivity;
import com.example.tuprak2.model.StoryModel;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class HighlightAdapter extends RecyclerView.Adapter<HighlightAdapter.HighlightViewHolder> {

    private Context context;
    private ArrayList<StoryModel> highlightList;

    public HighlightAdapter(Context context, ArrayList<StoryModel> highlightList) {
        this.context = context;
        this.highlightList = highlightList;
    }

    @NonNull
    @Override
    public HighlightViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_highlight, parent, false);
        return new HighlightViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HighlightViewHolder holder, int position) {
        StoryModel story = highlightList.get(position);

        Glide.with(context)
             .load(story.getImageUrl())
             .placeholder(R.drawable.placeholder_image)
             .error(R.drawable.placeholder_image)
             .circleCrop()
             .into(holder.imgHighlight);
             
        holder.tvTitle.setText(story.getTitle());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, StoryDetailActivity.class);
            intent.putExtra("STORY_TITLE", story.getTitle());
            intent.putExtra("IMAGE_URL", story.getImageUrl());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return highlightList.size();
    }

    public static class HighlightViewHolder extends RecyclerView.ViewHolder {
        ImageView imgHighlight;
        TextView tvTitle;

        public HighlightViewHolder(@NonNull View itemView) {
            super(itemView);
            imgHighlight = itemView.findViewById(R.id.imgHighlight);
            tvTitle = itemView.findViewById(R.id.tvTitle);
        }
    }
}
