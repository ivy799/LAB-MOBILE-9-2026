package com.example.tp_3.fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.tp_3.MainActivity;
import com.example.tp_3.R;
import com.example.tp_3.data.DataBook;
import com.example.tp_3.model.Book;

public class AddBookFragment extends Fragment {

    public AddBookFragment() {}
    ImageView imgPreview;
    Uri imageUri;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_book, container, false);
        EditText title = view.findViewById(R.id.etTitle);
        EditText author = view.findViewById(R.id.etAuthor);
        EditText year = view.findViewById(R.id.etYear);
        EditText desc = view.findViewById(R.id.etDesc);
        Button btn = view.findViewById(R.id.btnAdd);
        imgPreview = view.findViewById(R.id.imgPreview);
        Button btnPick = view.findViewById(R.id.btnPickImage);

        btnPick.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("image/*");
            startActivityForResult(intent, 1);
        });

        btn.setOnClickListener(v -> {
            String t = title.getText().toString();
            String a = author.getText().toString();
            String y = year.getText().toString();
            String d = desc.getText().toString();

            if (t.isEmpty() || a.isEmpty() || imageUri == null) {
                Toast.makeText(getContext(), "Isi data dan pilih gambar!", Toast.LENGTH_SHORT).show();
                return;
            }

            Book newBook = new Book(t, a, y, d, imageUri.toString());
            DataBook.listBook.add(0, newBook);
            Toast.makeText(getContext(), "Buku ditambahkan!", Toast.LENGTH_SHORT).show();
            title.setText("");
            author.setText("");
            year.setText("");
            desc.setText("");
            imgPreview.setImageResource(android.R.color.transparent);
            imageUri = null;
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == Activity.RESULT_OK && data != null) {
            imageUri = data.getData();
            imgPreview.setImageURI(imageUri);

            getContext().getContentResolver().takePersistableUriPermission(imageUri,
                    Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).setSearchViewVisibility(View.GONE);
    }
}