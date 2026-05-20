package com.example.tp2.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tp2.R;
import com.example.tp2.activities.DetailStoryActivity;
import com.example.tp2.models.Highlight;

import java.util.ArrayList;

public class HighlightAdapter extends RecyclerView.Adapter<HighlightAdapter.ViewHolder> {

    private final ArrayList<Highlight> highlights;
    private final Context context;

    public HighlightAdapter(Context context, ArrayList<Highlight> highlights) {
        this.context = context;
        this.highlights = highlights;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_highlight, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Highlight highlight = highlights.get(position);

        holder.ivHighlight.setImageResource(highlight.getImageRaw());
        holder.tvHighlightTitle.setText(highlight.getTitle());

        // Aksi klik untuk pindah ke halaman Detail Story
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailStoryActivity.class);
            // Mengirim data highlight yang diklik ke halaman detail
            intent.putExtra("EXTRA_HIGHLIGHT", highlight);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return highlights.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivHighlight;
        TextView tvHighlightTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivHighlight = itemView.findViewById(R.id.iv_highlight);
            tvHighlightTitle = itemView.findViewById(R.id.tv_highlight_title);
        }
    }
}