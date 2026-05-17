package com.example.tp_2.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tp_2.R;
import com.example.tp_2.activity.StoryDetailActivity;
import com.example.tp_2.model.Story;

import java.util.ArrayList;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.ViewHolder> {

    ArrayList<Story> stories;
    Context context; // FIX: tambah context untuk startActivity

    public StoryAdapter(ArrayList<Story> stories) {
        this.stories = stories;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext(); // FIX: simpan context di sini
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_story, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Story story = stories.get(position);

        holder.img.setImageResource(story.getImage());
        holder.name.setText(story.getName());

        // FIX: tambah click listener — sebelumnya story tidak bisa diklik sama sekali
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, StoryDetailActivity.class);
            intent.putExtra("story_data", story); // Story sekarang Parcelable
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return stories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img  = itemView.findViewById(R.id.imgStory);
            name = itemView.findViewById(R.id.tvName);
        }
    }
}