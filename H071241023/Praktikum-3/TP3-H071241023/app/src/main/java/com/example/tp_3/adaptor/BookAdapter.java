package com.example.tp_3.adaptor;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tp_3.DetailActivity;
import com.example.tp_3.R;
import com.example.tp_3.model.Book;
import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {

    private ArrayList<Book> list;

    public BookAdapter(ArrayList<Book> list) {
        this.list = list;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView title, author;

        public ViewHolder(View v) {
            super(v);
            img = v.findViewById(R.id.imgBook);
            title = v.findViewById(R.id.title);
            author = v.findViewById(R.id.author);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_book, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Book book = list.get(position);
        holder.title.setText(book.getTitle());
        holder.author.setText(book.getAuthor());

        if (book.getImageUri() != null && !book.getImageUri().isEmpty()) {
            holder.img.setImageURI(Uri.parse(book.getImageUri()));
        } else {
            holder.img.setImageResource(book.getImage());
        }

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), DetailActivity.class);
            intent.putExtra("title", book.getTitle());
            intent.putExtra("author", book.getAuthor());
            intent.putExtra("year", book.getYear());
            intent.putExtra("desc", book.getDesc());
            intent.putExtra("image", book.getImage());
            intent.putExtra("imageUri", book.getImageUri());

            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setFilteredList(ArrayList<Book> filteredList) {
        this.list = filteredList;
        notifyDataSetChanged();
    }
}

