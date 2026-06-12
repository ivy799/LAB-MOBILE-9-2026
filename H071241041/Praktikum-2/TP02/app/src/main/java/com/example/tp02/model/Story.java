package com.example.tp02.model;

import android.net.Uri;

public class Story {
    private int id;
    private String title;
    private int coverImageRes;
    private Uri coverImageUri;
    private String username;

    public Story(int id, String title, int coverImageRes, String username) {
        this.id = id;
        this.title = title;
        this.coverImageRes = coverImageRes;
        this.username = username;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public int getCoverImageRes() { return coverImageRes; }
    public Uri getCoverImageUri() { return coverImageUri; }
    public void setCoverImageUri(Uri uri) { this.coverImageUri = uri; }
    public String getUsername() { return username; }
}