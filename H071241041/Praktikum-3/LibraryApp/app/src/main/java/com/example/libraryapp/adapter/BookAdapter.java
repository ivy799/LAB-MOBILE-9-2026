package com.example.libraryapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.libraryapp.R;
import com.example.libraryapp.model.Book;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    public interface OnBookClickListener {
        void onBookClick(Book book);
    }

    private final Context context;
    private List<Book> bookList;
    private final OnBookClickListener listener;

    public BookAdapter(Context context, List<Book> bookList, OnBookClickListener listener) {
        this.context = context;
        this.bookList = bookList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_book, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book book = bookList.get(position);

        holder.tvTitle.setText(book.getTitle());
        holder.tvAuthor.setText(book.getAuthor());
        holder.tvYear.setText(String.valueOf(book.getYear()));
        holder.tvGenre.setText(book.getGenre());
        holder.ratingBar.setRating(book.getRating());

        if (book.getCoverUri() != null) {
            holder.ivCover.setImageURI(book.getCoverUri());
        } else if (book.getCoverResId() != 0) {
            holder.ivCover.setImageResource(book.getCoverResId());
        } else {
            holder.ivCover.setImageResource(R.drawable.cover_default);
        }

        holder.ivLike.setImageResource(book.isLiked()
                ? R.drawable.ic_heart_filled
                : R.drawable.ic_heart_outline);

        holder.itemView.setOnClickListener(v -> listener.onBookClick(book));
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public void updateList(List<Book> newList) {
        this.bookList = newList;
        notifyDataSetChanged();
    }

    static class BookViewHolder extends RecyclerView.ViewHolder {
        ImageView ivCover, ivLike;
        TextView tvTitle, tvAuthor, tvYear, tvGenre;
        RatingBar ratingBar;

        BookViewHolder(@NonNull View itemView) {
            super(itemView);
            ivCover = itemView.findViewById(R.id.iv_cover);
            ivLike = itemView.findViewById(R.id.iv_like);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvAuthor = itemView.findViewById(R.id.tv_author);
            tvYear = itemView.findViewById(R.id.tv_year);
            tvGenre = itemView.findViewById(R.id.tv_genre);
            ratingBar = itemView.findViewById(R.id.rating_bar);
        }
    }
}
