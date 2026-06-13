package com.example.tuprak_2;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.ViewHolder> {
    private ArrayList<Highlight> listHighlight;

    public StoryAdapter(ArrayList<Highlight> listHighlight) {
        this.listHighlight = listHighlight;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_highlight, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Highlight highlight = listHighlight.get(position);
        holder.tvTitle.setText(highlight.getTitle());
        holder.ivHighlight.setImageResource(highlight.getCoverImage());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), DetailStoryActivity.class);
            intent.putExtra("highlight", highlight);
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return listHighlight.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivHighlight;
        TextView tvTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivHighlight = itemView.findViewById(R.id.iv_highlight);
            tvTitle = itemView.findViewById(R.id.tv_highlight_title);
        }
    }
}
