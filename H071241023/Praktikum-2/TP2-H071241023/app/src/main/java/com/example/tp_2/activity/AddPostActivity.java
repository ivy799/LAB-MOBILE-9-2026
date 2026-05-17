package com.example.tp_2.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tp_2.R;
import com.example.tp_2.data.DummyData;
import com.example.tp_2.model.Feed;

public class AddPostActivity extends AppCompatActivity {

    ImageView imgPreview;
    EditText etCaption;
    Uri selectedImage;
    View placeholderLayout;

    ActivityResultLauncher<String> pickImage =
            registerForActivityResult(new ActivityResultContracts.GetContent(), uri -> {
                if (uri != null) {
                    selectedImage = uri;
                    imgPreview.setImageURI(uri);
                    getContentResolver().takePersistableUriPermission(uri, Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    if (placeholderLayout != null) placeholderLayout.setVisibility(View.GONE);
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);

        imgPreview      = findViewById(R.id.imgPreview);
        etCaption       = findViewById(R.id.etCaption);
        placeholderLayout = findViewById(R.id.placeholderLayout);

        // Tap grid / gallery area → pilih foto
        Button btnPickImage = findViewById(R.id.btnPickImage);
        btnPickImage.setOnClickListener(v -> pickImage.launch("image/*"));

        // Tombol close → kembali
        ImageView btnClose = findViewById(R.id.btnClose);
        if (btnClose != null) btnClose.setOnClickListener(v -> finish());

        // Tombol Next → minta caption lalu upload
        TextView btnNext = findViewById(R.id.btnNext);
        btnNext.setOnClickListener(v -> {
            if (selectedImage == null) {
                Toast.makeText(this, "Pilih foto dulu!", Toast.LENGTH_SHORT).show();
                return;
            }
            showCaptionDialog();
        });
    }

    private void showCaptionDialog() {
        EditText input = new EditText(this);
        input.setHint("Tulis caption...");
        input.setPadding(40, 20, 40, 20);

        new AlertDialog.Builder(this)
                .setTitle("Tambah Caption")
                .setView(input)
                .setPositiveButton("Upload", (dialog, which) -> {
                    String caption    = input.getText().toString();
                    String profileUri = "android.resource://com.example.tp_2/" + R.drawable.post1;

                    DummyData.profileFeeds.add(0,
                            new Feed(selectedImage.toString(), caption, "bwakekoqq", profileUri));
                    DummyData.feeds.add(0,
                            new Feed(selectedImage.toString(), caption, "bwakekoqq", profileUri));

                    Toast.makeText(this, "Post berhasil!", Toast.LENGTH_SHORT).show();
                    finish();
                })
                .setNegativeButton("Batal", null)
                .show();
    }
}