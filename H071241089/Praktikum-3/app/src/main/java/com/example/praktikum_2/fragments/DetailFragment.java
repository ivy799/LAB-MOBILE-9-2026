package com.example.praktikum_2.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.praktikum_2.R;
import com.example.praktikum_2.models.Post;

public class DetailFragment extends Fragment {

    private static final String ARG_POST = "post";
    private static final String ARG_TITLE = "title";
    private static final String ARG_IMAGE = "image";

    public static DetailFragment newInstance(Post post) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_POST, post);
        fragment.setArguments(args);
        return fragment;
    }

    public static DetailFragment newInstance(String title, int imageResId) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        args.putInt(ARG_IMAGE, imageResId);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        ImageView ivProfile = view.findViewById(R.id.iv_profile_post);
        ImageView ivFeed = view.findViewById(R.id.iv_feed_post);
        TextView tvUsername = view.findViewById(R.id.tv_username_post);
        TextView tvCaption = view.findViewById(R.id.tv_caption_post);

        if (getArguments() != null) {
            Post post = getArguments().getParcelable(ARG_POST);
            if (post != null) {
                if (post.getProfileImageUri() != null) {
                    ivProfile.setImageURI(android.net.Uri.parse(post.getProfileImageUri()));
                } else {
                    ivProfile.setImageResource(post.getProfileImageResId());
                }

                if (post.getImageUri() != null) {
                    ivFeed.setImageURI(android.net.Uri.parse(post.getImageUri()));
                } else {
                    ivFeed.setImageResource(post.getImageResId());
                }

                tvUsername.setText(post.getUsername());
                tvCaption.setText(post.getCaption());
            } else {
                String title = getArguments().getString(ARG_TITLE);
                int imageResId = getArguments().getInt(ARG_IMAGE);
                tvUsername.setText(title);
                ivFeed.setImageResource(imageResId);
                ivProfile.setVisibility(View.GONE);
                tvCaption.setVisibility(View.GONE);
            }
        }

        return view;
    }
}