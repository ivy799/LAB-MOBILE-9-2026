package com.example.tp02.model;

import android.net.Uri;

public class Post {
    private int id;
    private String username;
    private int profileImageRes;
    private Uri profileImageUri;
    private int postImageRes;
    private Uri postImageUri;
    private String caption;
    private int likeCount;
    private String timeAgo;
    private boolean isLiked;
    private boolean isBookmarked;

    public Post(int id, String username, int profileImageRes, int postImageRes, String caption, int likeCount, String timeAgo) {
        this.id = id;
        this.username = username;
        this.profileImageRes = profileImageRes;
        this.postImageRes = postImageRes;
        this.caption = caption;
        this.likeCount = likeCount;
        this.timeAgo = timeAgo;
        this.isLiked = false;
        this.isBookmarked = false;
    }

    public Post(int id, String username, int profileImageRes, Uri postImageUri, String caption, int likeCount, String timeAgo) {
        this.id = id;
        this.username = username;
        this.profileImageRes = profileImageRes;
        this.postImageUri = postImageUri;
        this.caption = caption;
        this.likeCount = likeCount;
        this.timeAgo = timeAgo;
        this.isLiked = false;
        this.isBookmarked = false;
    }

    public int getId() { return id; }
    public String getUsername() { return username; }
    public int getProfileImageRes() { return profileImageRes; }
    public Uri getProfileImageUri() { return profileImageUri; }
    public void setProfileImageUri(Uri uri) { this.profileImageUri = uri; }
    public int getPostImageRes() { return postImageRes; }
    public Uri getPostImageUri() { return postImageUri; }
    public String getCaption() { return caption; }
    public int getLikeCount() { return likeCount; }
    public String getTimeAgo() { return timeAgo; }
    public boolean isLiked() { return isLiked; }
    public void setLiked(boolean liked) { this.isLiked = liked; }
    public boolean isBookmarked() { return isBookmarked; }
    public void setBookmarked(boolean bookmarked) { this.isBookmarked = bookmarked; }
    public void setLikeCount(int likeCount) { this.likeCount = likeCount; }
}