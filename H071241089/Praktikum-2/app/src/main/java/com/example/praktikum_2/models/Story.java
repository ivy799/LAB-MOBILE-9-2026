package com.example.praktikum_2.models;

public class Story {
    private String username;
    private int profileImageResId;
    private boolean isYourStory;

    public Story(String username, int profileImageResId, boolean isYourStory) {
        this.username = username;
        this.profileImageResId = profileImageResId;
        this.isYourStory = isYourStory;
    }

    public String getUsername() { return username; }
    public int getProfileImageResId() { return profileImageResId; }
    public boolean isYourStory() { return isYourStory; }
}