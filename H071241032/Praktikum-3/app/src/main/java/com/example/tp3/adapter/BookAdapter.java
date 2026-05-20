package com.example.tp3.adapter; // SESUAIKAN DENGAN PACKAGE-MU

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tp3.R; // SESUAIKAN JIKA PERLU
import com.example.tp3.model.Book;
import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    private ArrayList<Book> bookList;
    private OnItemClickListener listener;

    // Interface untuk mendeteksi klik pada item buku
    public interface OnItemClickListener {
        void onItemClick(Book book);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    // Constructor Adapter
    public BookAdapter(ArrayList<Book> bookList) {
        this.bookList = bookList;
    }

    // Method untuk update data saat di-search atau filter
    public void setFilteredList(ArrayList<Book> filteredList) {
        this.bookList = filteredList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book book = bookList.get(position);

        holder.tvTitle.setText(book.getTitle());
        holder.tvAuthor.setText(book.getAuthor());
        holder.tvGenre.setText(book.getGenre() + " • ⭐ " + book.getRating());

        // Cek apakah pakai gambar dari Galeri (URI) atau Resource dummy
        if (book.getCoverUri() != null) {
            holder.ivCover.setImageURI(Uri.parse(book.getCoverUri()));
        } else {
            holder.ivCover.setImageResource(book.getCoverResource());
        }

        // Set klik listener
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(book);
            }
        });
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    // ViewHolder untuk menghubungkan elemen XML dengan Java
    public static class BookViewHolder extends RecyclerView.ViewHolder {
        ImageView ivCover;
        TextView tvTitle, tvAuthor, tvGenre;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            ivCover = itemView.findViewById(R.id.iv_book_cover);
            tvTitle = itemView.findViewById(R.id.tv_book_title);
            tvAuthor = itemView.findViewById(R.id.tv_book_author);
            tvGenre = itemView.findViewById(R.id.tv_book_genre);
        }
    }
}