package com.example.tp02.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.example.tp02.R;
import com.example.tp02.model.DataManager;

public class PostActivity extends AppCompatActivity {

    private ImageView imgSelected, btnBack, btnClose;
    private EditText etCaption;
    private Button btnSelectImage, btnShare;
    private Uri selectedImageUri = null;

    private final ActivityResultLauncher<Intent> imagePickerLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    selectedImageUri = result.getData().getData();
                    Glide.with(this).load(selectedImageUri).centerCrop().into(imgSelected);
                    imgSelected.setScaleType(ImageView.ScaleType.CENTER_CROP);
                }
            }
    );

    private final ActivityResultLauncher<String> permissionLauncher = registerForActivityResult(
            new ActivityResultContracts.RequestPermission(),
            isGranted -> {
                if (isGranted) {
                    openGallery();
                } else {
                    Toast.makeText(this, "Permission diperlukan untuk memilih foto", Toast.LENGTH_SHORT).show();
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        imgSelected = findViewById(R.id.imgSelected);
        etCaption = findViewById(R.id.etCaption);
        btnSelectImage = findViewById(R.id.btnSelectImage);
        btnShare = findViewById(R.id.btnShare);
        btnBack = findViewById(R.id.btnBack);
        btnClose = findViewById(R.id.btnClose);

        btnBack.setOnClickListener(v -> finish());
        btnClose.setOnClickListener(v -> finish());

        btnSelectImage.setOnClickListener(v -> checkPermissionAndOpenGallery());

        imgSelected.setOnClickListener(v -> checkPermissionAndOpenGallery());

        btnShare.setOnClickListener(v -> {
            String caption = etCaption.getText().toString().trim();
            if (selectedImageUri == null) {
                Toast.makeText(this, "Pilih foto terlebih dahulu", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(caption)) {
                Toast.makeText(this, "Tambahkan caption", Toast.LENGTH_SHORT).show();
                return;
            }
            DataManager.getInstance().addNewPost(selectedImageUri, caption);
            Toast.makeText(this, "Postingan berhasil dibagikan!", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, ProfileActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finish();
        });
    }

    private void checkPermissionAndOpenGallery() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_IMAGES) == PackageManager.PERMISSION_GRANTED) {
                openGallery();
            } else {
                permissionLauncher.launch(Manifest.permission.READ_MEDIA_IMAGES);
            }
        } else {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                openGallery();
            } else {
                permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE);
            }
        }
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        imagePickerLauncher.launch(intent);
    }
}