package com.example.tp02.model;

public class User {
    private String username;
    private String fullName;
    private String bio;
    private int profileImageRes;
    private int postCount;
    private int followerCount;
    private int followingCount;

    public User(String username, String fullName, String bio, int profileImageRes, int postCount, int followerCount, int followingCount) {
        this.username = username;
        this.fullName = fullName;
        this.bio = bio;
        this.profileImageRes = profileImageRes;
        this.postCount = postCount;
        this.followerCount = followerCount;
        this.followingCount = followingCount;
    }

    public String getUsername() { return username; }
    public String getFullName() { return fullName; }
    public String getBio() { return bio; }
    public int getProfileImageRes() { return profileImageRes; }
    public int getPostCount() { return postCount; }
    public int getFollowerCount() { return followerCount; }
    public int getFollowingCount() { return followingCount; }
    public void setPostCount(int count) { this.postCount = count; }
}