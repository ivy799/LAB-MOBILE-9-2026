package com.example.tp02.model;

import android.net.Uri;

import com.example.tp02.R;

import java.util.ArrayList;
import java.util.List;

public class DataManager {

    private static DataManager instance;
    private List<Post> homeFeedPosts;
    private List<Post> profilePosts;
    private List<Story> stories;
    private User currentUser;
    private int postIdCounter = 100;

    private DataManager() {
        initData();
    }

    public static DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }

    private void initData() {
        currentUser = new User(
                "",
                "Kavern V.",
                "📍 Makassar\n✨ Living the moment\n🎓 Student",
                R.drawable.profile_main,
                5, 1204, 348
        );

        homeFeedPosts = new ArrayList<>();
        homeFeedPosts.add(new Post(1, "pempek2rb", R.drawable.profile_main, R.drawable.feed1, "finally found the key, mks unounhas", 234, "2h"));
        homeFeedPosts.add(new Post(2, "keondondong", R.drawable.profile2, R.drawable.k2, "mam masakan bunda", 189, "4h"));
        homeFeedPosts.add(new Post(3, "martinkangkung", R.drawable.profile3, R.drawable.k3, "cukur jabrik biar hatimu terobrak - abrik", 542, "6h"));
        homeFeedPosts.add(new Post(4, "petaicina", R.drawable.profile4, R.drawable.k4, "janana", 312, "8h"));
        homeFeedPosts.add(new Post(5, "anaksyahrini", R.drawable.profile5, R.drawable.k5, "yuarmaysyensyain", 421, "10h"));
        homeFeedPosts.add(new Post(6, "reimbayang", R.drawable.profile6, R.drawable.k6, "reibidi", 198, "12h"));
        homeFeedPosts.add(new Post(7, "wonimut", R.drawable.profile7, R.drawable.k7, "akuya mw di absen bg windah", 276, "14h"));
        homeFeedPosts.add(new Post(8, "yeontik", R.drawable.profile8, R.drawable.k8, "daftar master sep", 334, "1d"));
        homeFeedPosts.add(new Post(9, "antonlee_okele", R.drawable.profile9, R.drawable.k9, "can't sleep", 289, "1d"));
        homeFeedPosts.add(new Post(10, "nikiminaj", R.drawable.profile10, R.drawable.k10, "japanese blood", 156, "2d"));

        profilePosts = new ArrayList<>();
        profilePosts.add(new Post(12, "pempek2rb", R.drawable.profile_main, R.drawable.feed2, "otw beli gas jg hrs tetap keren", 189, "4h"));
        profilePosts.add(new Post(13, "pempek2rb", R.drawable.profile_main, R.drawable.feed3, "pap buat fens", 542, "6h"));
        profilePosts.add(new Post(14, "pempek2rb", R.drawable.profile_main, R.drawable.feed4, "spill tipis tipis tiris mengiris", 312, "8h"));
        profilePosts.add(new Post(15, "pempek2rb", R.drawable.profile_main, R.drawable.feed5, "dri angel mnapun i ttp cakep ok #fact", 421, "10h"));
        profilePosts.add(new Post(11, "pempek2rb", R.drawable.profile_main, R.drawable.feed1, "finally found the key, mks unounhas", 234, "2h"));

        stories = new ArrayList<>();
        stories.add(new Story(1, "01", R.drawable.story1, "pempek2rb"));
        stories.add(new Story(2, "02", R.drawable.story2, "pempek2rb"));
        stories.add(new Story(3, "03", R.drawable.story3, "pempek2rb"));
        stories.add(new Story(4, "04", R.drawable.story4, "pempek2rb"));
        stories.add(new Story(5, "05", R.drawable.story5, "pempek2rb"));
        stories.add(new Story(6, "06", R.drawable.story6, "pempek2rb"));
        stories.add(new Story(7, "07", R.drawable.story7, "pempek2rb"));
    }

    public List<Post> getHomeFeedPosts() { return homeFeedPosts; }
    public List<Post> getProfilePosts() { return profilePosts; }
    public List<Story> getStories() { return stories; }
    public User getCurrentUser() { return currentUser; }

    public void addNewPost(Uri imageUri, String caption) {
        postIdCounter++;
        Post newPost = new Post(postIdCounter, "pempek2rb", R.drawable.profile_main, imageUri, caption, 0, "Just now");
        profilePosts.add(0, newPost);

        Post homePost = new Post(postIdCounter + 500, "pempek2rb", R.drawable.profile_main, imageUri, caption, 0, "Just now");
        homeFeedPosts.add(0, homePost);

        currentUser.setPostCount(currentUser.getPostCount() + 1);
    }
}