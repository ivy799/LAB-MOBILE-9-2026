package com.example.tuprak_2.model;

import java.io.Serializable;

public class Story implements Serializable {
    private String storyId;
    private String userId;
    private String title;
    private int coverImageRes;
    private int imageRes;
    private boolean isSeen;

    public Story(String storyId, String userId, String title, int coverImageRes, int imageRes) {
        this.storyId = storyId;
        this.userId = userId;
        this.title = title;
        this.coverImageRes = coverImageRes;
        this.imageRes = imageRes;
        this.isSeen = false;
    }

    public String getStoryId() { return storyId; }
    public String getUserId() { return userId; }
    public String getTitle() { return title; }
    public int getCoverImageRes() { return coverImageRes; }
    public int getImageRes() { return imageRes; }
    public boolean isSeen() { return isSeen; }
    public void setSeen(boolean seen) { this.isSeen = seen; }
}
