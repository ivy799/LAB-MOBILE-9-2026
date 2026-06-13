package com.example.tuprak_2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class AddPostActivity extends AppCompatActivity {
    private ImageView ivAddImage;
    private Uri selectedImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);

        ivAddImage = findViewById(R.id.iv_post_upload);
        EditText edtCaption = findViewById(R.id.edt_caption);
        Button btnShare = findViewById(R.id.btn_share);

        // Launcher untuk mengambil gambar dari galeri
        ActivityResultLauncher<Intent> galleryLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        selectedImageUri = result.getData().getData();
                        ivAddImage.setImageURI(selectedImageUri);
                    }
                }
        );

        ivAddImage.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            galleryLauncher.launch(intent);
        });

        btnShare.setOnClickListener(v -> {
            String caption = edtCaption.getText().toString();
            if (selectedImageUri == null) {
                Toast.makeText(this, "Silakan pilih gambar terlebih dahulu", Toast.LENGTH_SHORT).show();
                return;
            }

            // Tambahkan postingan baru menggunakan URI
            // Menggunakan R.drawable.profile sesuai dengan perubahan nama file gambar
            DataSource.profilePosts.add(0, new Post("my_profile", R.drawable.profile, selectedImageUri.toString(), caption));

            Toast.makeText(this, "Postingan berhasil diunggah!", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
