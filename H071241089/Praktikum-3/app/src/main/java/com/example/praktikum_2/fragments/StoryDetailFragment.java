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
import com.example.praktikum_2.models.User;

public class StoryDetailFragment extends Fragment {

    private static final String ARG_USERNAME = "username";
    private static final String ARG_IMAGE_RES = "image_res";
    private static final String ARG_PROFILE_RES = "profile_res";

    public static StoryDetailFragment newInstance(String username, int imageRes, int profileRes) {
        StoryDetailFragment fragment = new StoryDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_USERNAME, username);
        args.putInt(ARG_IMAGE_RES, imageRes);
        args.putInt(ARG_PROFILE_RES, profileRes);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_story_detail, container, false);

        ImageView ivStoryFull = view.findViewById(R.id.iv_story_full);
        ImageView ivProfile = view.findViewById(R.id.iv_story_profile);
        TextView tvUsername = view.findViewById(R.id.tv_story_username);
        ImageView btnClose = view.findViewById(R.id.btn_close_story);

        if (getArguments() != null) {
            tvUsername.setText(getArguments().getString(ARG_USERNAME));
            ivStoryFull.setImageResource(getArguments().getInt(ARG_IMAGE_RES));
            ivProfile.setImageResource(getArguments().getInt(ARG_PROFILE_RES));
        }

        View.OnClickListener profileClickListener = v -> {
            if (getActivity() != null) {
                User user = new User(getArguments().getString(ARG_USERNAME), getArguments().getInt(ARG_PROFILE_RES));
                // Remove the story fragment from backstack and replace with profile
                getActivity().getSupportFragmentManager().popBackStack();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, ProfileFragment.newInstance(user))
                        .addToBackStack(null)
                        .commit();
            }
        };

        ivProfile.setOnClickListener(profileClickListener);
        tvUsername.setOnClickListener(profileClickListener);

        btnClose.setOnClickListener(v -> {
            if (getActivity() != null) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        return view;
    }
}