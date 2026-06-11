package com.example.tuprak2.model;

public class PostModel {
    private String imageUrl;
    private String username;
    private String caption;

    public PostModel(String imageUrl, String username, String caption) {
        this.imageUrl = imageUrl;
        this.username = username;
        this.caption = caption;
    }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getCaption() { return caption; }
    public void setCaption(String caption) { this.caption = caption; }
}
