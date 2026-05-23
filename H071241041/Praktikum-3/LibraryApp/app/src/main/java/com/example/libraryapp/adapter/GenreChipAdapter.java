package com.example.libraryapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.libraryapp.R;

import java.util.List;

public class GenreChipAdapter extends RecyclerView.Adapter<GenreChipAdapter.ChipViewHolder> {

    public interface OnGenreClickListener {
        void onGenreClick(String genre);
    }

    private final Context context;
    private final List<String> genres;
    private final OnGenreClickListener listener;
    private int selectedPosition = 0;

    public GenreChipAdapter(Context context, List<String> genres, OnGenreClickListener listener) {
        this.context = context;
        this.genres = genres;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ChipViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_genre_chip, parent, false);
        return new ChipViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChipViewHolder holder, int position) {
        String genre = genres.get(position);
        holder.tvGenre.setText(genre);

        if (position == selectedPosition) {
            holder.tvGenre.setBackgroundResource(R.drawable.chip_selected);
            holder.tvGenre.setTextColor(context.getResources().getColor(R.color.white, null));
        } else {
            holder.tvGenre.setBackgroundResource(R.drawable.chip_unselected);
            holder.tvGenre.setTextColor(context.getResources().getColor(R.color.primary, null));
        }

        holder.tvGenre.setOnClickListener(v -> {
            int prev = selectedPosition;
            selectedPosition = holder.getAdapterPosition();
            notifyItemChanged(prev);
            notifyItemChanged(selectedPosition);
            listener.onGenreClick(genre);
        });
    }

    @Override
    public int getItemCount() {
        return genres.size();
    }

    static class ChipViewHolder extends RecyclerView.ViewHolder {
        TextView tvGenre;

        ChipViewHolder(@NonNull View itemView) {
            super(itemView);
            tvGenre = itemView.findViewById(R.id.tv_genre_chip);
        }
    }
}
