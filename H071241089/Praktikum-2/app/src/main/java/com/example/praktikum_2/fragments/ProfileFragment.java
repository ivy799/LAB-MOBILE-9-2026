package com.example.praktikum_2.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.praktikum_2.MainActivity;
import com.example.praktikum_2.R;
import com.example.praktikum_2.adapters.HighlightAdapter;
import com.example.praktikum_2.adapters.PostAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.praktikum_2.MainActivity;
import com.example.praktikum_2.R;
import com.example.praktikum_2.adapters.HighlightAdapter;
import com.example.praktikum_2.adapters.PostAdapter;
import com.example.praktikum_2.models.Highlight;
import com.example.praktikum_2.models.Post;
import com.example.praktikum_2.models.User;

import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends Fragment {

    private static final String ARG_USER = "user";

    public static ProfileFragment newInstance(User user) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_USER, user);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        MainActivity activity = (MainActivity) getActivity();
        if (activity == null) return view;

        User user = null;
        if (getArguments() != null) {
            user = getArguments().getParcelable(ARG_USER);
        }

        // Set default user if none provided (e.g. from nav menu)
        if (user == null) {
            user = new User("Current User", R.drawable.avatar_6);
        }

        ImageView ivProfile = view.findViewById(R.id.iv_profile_main);
        TextView tvUsername = view.findViewById(R.id.tv_profile_name);

        ivProfile.setImageResource(user.getProfileImageResId());
        tvUsername.setText(user.getUsername());

        TextView tvPostCount = view.findViewById(R.id.tv_post_count);
        
        // Filter posts for this user
        List<Post> userPosts = new ArrayList<>();
        String targetUsername = user.getUsername();
        if (targetUsername.equals("Current User")) {
            userPosts = activity.getMyPosts();
        } else {
            for (Post p : activity.getAllPosts()) {
                if (p.getUsername().equals(targetUsername)) {
                    userPosts.add(p);
                }
            }
        }
        
        tvPostCount.setText(String.valueOf(userPosts.size()));

        // Highlights
        RecyclerView rvHighlights = view.findViewById(R.id.rv_highlights);
        rvHighlights.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        List<Highlight> highlights = new ArrayList<>();
        highlights.add(new Highlight("Unhas", R.drawable.story_1));
        highlights.add(new Highlight("Campus", R.drawable.story_2));
        highlights.add(new Highlight("Friends", R.drawable.story_3));
        highlights.add(new Highlight("Holiday", R.drawable.posts_1));
        highlights.add(new Highlight("Work", R.drawable.posts_2));

        rvHighlights.setAdapter(new HighlightAdapter(highlights, highlight -> {
            StoryDetailFragment storyDetailFragment = StoryDetailFragment.newInstance(highlight.getTitle(), highlight.getImageResId(), android.R.drawable.ic_menu_camera);
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, storyDetailFragment)
                    .addToBackStack(null)
                    .commit();
        }));

        // Profile Posts Grid
        RecyclerView rvProfilePosts = view.findViewById(R.id.rv_profile_posts);
        rvProfilePosts.setLayoutManager(new GridLayoutManager(getContext(), 3));
        
        // Add item decoration for gap
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.grid_spacing);
        rvProfilePosts.addItemDecoration(new GridSpacingItemDecoration(3, spacingInPixels, false));

        rvProfilePosts.setAdapter(new PostAdapter(userPosts, new PostAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Post post) {
                DetailFragment detailFragment = DetailFragment.newInstance(post);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, detailFragment)
                        .addToBackStack(null)
                        .commit();
            }

            @Override
            public void onProfileClick(Post post) {
                // Already on profile
            }
        }, true));

        return view;
    }

    // Item decoration for grid spacing
    public static class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {
        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(android.graphics.Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view);
            int column = position % spanCount;

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount;
                outRect.right = (column + 1) * spacing / spanCount;

                if (position < spanCount) {
                    outRect.top = spacing;
                }
                outRect.bottom = spacing;
            } else {
                outRect.left = column * spacing / spanCount;
                outRect.right = spacing - (column + 1) * spacing / spanCount;
                if (position >= spanCount) {
                    outRect.top = spacing;
                }
            }
        }
    }
}