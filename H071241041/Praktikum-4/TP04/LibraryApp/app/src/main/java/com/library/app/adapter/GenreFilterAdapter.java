package com.library.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.library.app.R;

import java.util.List;

public class GenreFilterAdapter extends RecyclerView.Adapter<GenreFilterAdapter.GenreViewHolder> {

    private final Context context;
    private final List<String> genres;
    private int selectedPosition = 0;
    private final OnGenreClickListener listener;

    public interface OnGenreClickListener {
        void onGenreClick(String genre);
    }

    public GenreFilterAdapter(Context context, List<String> genres, OnGenreClickListener listener) {
        this.context = context;
        this.genres = genres;
        this.listener = listener;
    }

    @NonNull
    @Override
    public GenreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_genre_chip, parent, false);
        return new GenreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GenreViewHolder holder, int position) {
        String genre = genres.get(position);
        holder.tvGenre.setText(genre);

        boolean isSelected = position == selectedPosition;
        holder.tvGenre.setSelected(isSelected);
        holder.tvGenre.setBackgroundResource(isSelected
            ? R.drawable.bg_genre_chip_selected
            : R.drawable.bg_genre_chip);

        holder.itemView.setOnClickListener(v -> {
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

    static class GenreViewHolder extends RecyclerView.ViewHolder {
        TextView tvGenre;

        GenreViewHolder(@NonNull View itemView) {
            super(itemView);
            tvGenre = itemView.findViewById(R.id.tv_genre_chip);
        }
    }
}
