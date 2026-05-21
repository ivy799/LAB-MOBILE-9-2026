package com.example.praktikum_2.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.praktikum_2.R;
import com.example.praktikum_2.models.Highlight;

import java.util.List;

public class HighlightAdapter extends RecyclerView.Adapter<HighlightAdapter.ViewHolder> {

    private List<Highlight> highlights;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Highlight highlight);
    }

    public HighlightAdapter(List<Highlight> highlights, OnItemClickListener listener) {
        this.highlights = highlights;
        this.listener = listener;
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
        holder.tvTitle.setText(highlight.getTitle());
        holder.ivHighlight.setImageResource(highlight.getImageResId());
        holder.itemView.setOnClickListener(v -> listener.onItemClick(highlight));
    }

    @Override
    public int getItemCount() {
        return highlights.size();
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