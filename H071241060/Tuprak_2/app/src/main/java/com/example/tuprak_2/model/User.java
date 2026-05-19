package com.example.tuprak_2.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String userId;
    private String username;
    private String fullName;
    private String bio;
    private int profileImageRes;
    private int postCount;
    private int followerCount;
    private int followingCount;
    private List<Post> posts;
    private List<Story> highlights;

    public User(String userId, String username, String fullName, String bio,
                int profileImageRes, int postCount, int followerCount, int followingCount) {
        this.userId = userId;
        this.username = username;
        this.fullName = fullName;
        this.bio = bio;
        this.profileImageRes = profileImageRes;
        this.postCount = postCount;
        this.followerCount = followerCount;
        this.followingCount = followingCount;
        this.posts = new ArrayList<>();
        this.highlights = new ArrayList<>();
    }

    public String getUserId() { return userId; }
    public String getUsername() { return username; }
    public String getFullName() { return fullName; }
    public String getBio() { return bio; }
    public int getProfileImageRes() { return profileImageRes; }
    public int getPostCount() { return postCount; }
    public int getFollowerCount() { return followerCount; }
    public int getFollowingCount() { return followingCount; }
    public List<Post> getPosts() { return posts; }
    public List<Story> getHighlights() { return highlights; }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
        this.postCount = posts.size();
    }
    public void setHighlights(List<Story> highlights) { this.highlights = highlights; }
    public void addPost(Post post) {
        this.posts.add(0, post);
        this.postCount = posts.size();
    }

    public String getFormattedFollowers() {
        if (followerCount >= 1000000) return String.format("%.1fM", followerCount / 1000000.0);
        if (followerCount >= 1000) return String.format("%.1fK", followerCount / 1000.0);
        return String.valueOf(followerCount);
    }
}
