package com.example.praktikum_2.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.praktikum_2.MainActivity;
import com.example.praktikum_2.R;
import com.example.praktikum_2.adapters.PostAdapter;
import com.example.praktikum_2.adapters.StoryAdapter;
import com.example.praktikum_2.models.Post;
import com.example.praktikum_2.models.Story;
import com.example.praktikum_2.models.User;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        MainActivity activity = (MainActivity) getActivity();
        if (activity == null) return view;

        // Stories
        RecyclerView rvStories = view.findViewById(R.id.rv_stories);
        rvStories.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        List<Story> stories = new ArrayList<>();
        stories.add(new Story("Your story", R.drawable.avatar_6, true));
        stories.add(new Story("batararezqi", R.drawable.avatar_1, false));
        stories.add(new Story("rarahaidirr", R.drawable.avatar_2, false));
        stories.add(new Story("_nnachaa", R.drawable.avatar_3, false));
        stories.add(new Story("divya.azz", R.drawable.avatar_4, false));
        stories.add(new Story("nafisahjee_", R.drawable.avatar_5, false));
        rvStories.setAdapter(new StoryAdapter(stories, story -> {
            if (story.isYourStory()) {
                // Navigate to Profile when "Your story" is clicked
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new ProfileFragment())
                        .addToBackStack(null)
                        .commit();
            } else {
                // Open full screen story for other users
                StoryDetailFragment storyFragment = StoryDetailFragment.newInstance(story.getUsername(), story.getProfileImageResId(), story.getProfileImageResId());
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, storyFragment)
                        .addToBackStack(null)
                        .commit();
            }
        }));

        // Posts
        RecyclerView rvHome = view.findViewById(R.id.rv_home);
        rvHome.setLayoutManager(new LinearLayoutManager(getContext()));
        rvHome.setNestedScrollingEnabled(false);

        PostAdapter adapter = new PostAdapter(activity.getAllPosts(), new PostAdapter.OnItemClickListener() {
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
                User user = new User(post.getUsername(), post.getProfileImageResId());
                ProfileFragment profileFragment = ProfileFragment.newInstance(user);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, profileFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
        rvHome.setAdapter(adapter);

        return view;
    }
}