package com.example.tuprak_2;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.example.recycleview.model.DataDummy;
import com.example.recycleview.model.Post;
import com.example.recycleview.model.User;
import java.util.List;
import java.util.UUID;

public class PostActivity extends AppCompatActivity {

    private ImageView ivPreview;
    private EditText etCaption;
    private Uri selectedImageUri = null;
    private User currentUser;

    private final ActivityResultLauncher<Intent> galleryLauncher =
        registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                selectedImageUri = result.getData().getData();
                ivPreview.setImageURI(selectedImageUri);
                ivPreview.setScaleType(ImageView.ScaleType.CENTER_CROP);
            }
        });

    private final ActivityResultLauncher<String> permissionLauncher =
        registerForActivityResult(new ActivityResultContracts.RequestPermission(), granted -> {
            if (granted) openGallery();
            else Toast.makeText(this, "Izin diperlukan untuk memilih gambar", Toast.LENGTH_SHORT).show();
        });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        String userId = getIntent().getStringExtra("USER_ID");
        loadUser(userId);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Post Baru");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        ivPreview = findViewById(R.id.iv_preview);
        etCaption = findViewById(R.id.et_caption);
        Button btnChoose = findViewById(R.id.btn_choose_image);
        Button btnShare   = findViewById(R.id.btn_share);

        btnChoose.setOnClickListener(v -> checkPermissionAndOpenGallery());
        btnShare.setOnClickListener(v -> sharePost());
    }

    private void loadUser(String userId) {
        List<User> users = DataDummy.getUsers();
        currentUser = users.get(0);
        for (User u : users) {
            if (u.getUserId().equals(userId)) { currentUser = u; break; }
        }
    }

    private void checkPermissionAndOpenGallery() {
        String permission = Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU
                ? Manifest.permission.READ_MEDIA_IMAGES
                : Manifest.permission.READ_EXTERNAL_STORAGE;

        if (ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED) {
            openGallery();
        } else {
            permissionLauncher.launch(permission);
        }
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        galleryLauncher.launch(intent);
    }

    private void sharePost() {
        if (selectedImageUri == null) {
            Toast.makeText(this, "Pilih gambar terlebih dahulu!", Toast.LENGTH_SHORT).show();
            return;
        }
        String caption = etCaption.getText().toString().trim();
        if (TextUtils.isEmpty(caption)) {
            etCaption.setError("Tambahkan caption");
            etCaption.requestFocus();
            return;
        }

        Post newPost = new Post(
                UUID.randomUUID().toString(),
                currentUser.getUserId(),
                currentUser.getUsername(),
                currentUser.getProfileImageRes(),
                selectedImageUri,
                caption
        );

        Intent resultIntent = new Intent();
        resultIntent.putExtra(ProfileActivity.EXTRA_NEW_POST, newPost);
        setResult(RESULT_OK, resultIntent);
        Toast.makeText(this, "Postingan berhasil dibagikan!", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
