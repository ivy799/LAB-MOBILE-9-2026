package com.example.tuprak2.model;

public class FeedModel {
    private String profileImageUrl;
    private String username;
    private String postImageUrl;
    private String caption;
    private int likes;
    private String timeAgo;

    public FeedModel(String profileImageUrl, String username, String postImageUrl, String caption, int likes, String timeAgo) {
        this.profileImageUrl = profileImageUrl;
        this.username = username;
        this.postImageUrl = postImageUrl;
        this.caption = caption;
        this.likes = likes;
        this.timeAgo = timeAgo;
    }

    public String getProfileImageUrl() { return profileImageUrl; }
    public void setProfileImageUrl(String profileImageUrl) { this.profileImageUrl = profileImageUrl; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPostImageUrl() { return postImageUrl; }
    public void setPostImageUrl(String postImageUrl) { this.postImageUrl = postImageUrl; }
    public String getCaption() { return caption; }
    public void setCaption(String caption) { this.caption = caption; }
    public int getLikes() { return likes; }
    public void setLikes(int likes) { this.likes = likes; }
    public String getTimeAgo() { return timeAgo; }
    public void setTimeAgo(String timeAgo) { this.timeAgo = timeAgo; }
}
