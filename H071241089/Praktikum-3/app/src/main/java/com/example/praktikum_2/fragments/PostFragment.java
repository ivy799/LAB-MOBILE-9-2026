package com.example.praktikum_2.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.net.Uri;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.praktikum_2.MainActivity;
import com.example.praktikum_2.R;
import com.example.praktikum_2.models.Post;

public class PostFragment extends Fragment {

    private ImageView ivPreview;
    private EditText etCaption;
    private Uri selectedImageUri;

    private final ActivityResultLauncher<String> imagePickerLauncher = registerForActivityResult(
            new ActivityResultContracts.GetContent(),
            uri -> {
                if (uri != null) {
                    selectedImageUri = uri;
                    ivPreview.setImageURI(uri);
                    ivPreview.setVisibility(View.VISIBLE);
                }
            }
    );

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_post, container, false);

        ivPreview = view.findViewById(R.id.iv_preview);
        etCaption = view.findViewById(R.id.et_caption);
        Button btnSelectImage = view.findViewById(R.id.btn_select_image);
        Button btnUpload = view.findViewById(R.id.btn_upload);

        btnSelectImage.setOnClickListener(v -> imagePickerLauncher.launch("image/*"));

        btnUpload.setOnClickListener(v -> {
            String caption = etCaption.getText().toString();
            
            if (selectedImageUri == null) {
                Toast.makeText(getContext(), "Please select an image", Toast.LENGTH_SHORT).show();
                return;
            }
            
            if (caption.isEmpty()) {
                Toast.makeText(getContext(), "Please enter a caption", Toast.LENGTH_SHORT).show();
                return;
            }

            Post newPost = new Post(selectedImageUri.toString(), "Current User", R.drawable.avatar_6, caption);
            
            MainActivity activity = (MainActivity) getActivity();
            if (activity != null) {
                activity.addPost(newPost);
                Toast.makeText(getContext(), "Post Uploaded!", Toast.LENGTH_SHORT).show();
                
                // Clear fields
                etCaption.setText("");
                selectedImageUri = null;
                ivPreview.setImageURI(null);
                
                // Navigate to Profile to see the new post
                activity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new ProfileFragment())
                        .commit();
            }
        });

        return view;
    }
}
