package com.example.tuprak_2;

import android.net.Uri;
import java.io.Serializable;

public class Post implements Serializable {
    private String username;
    private int profileImage;
    private int postImageResource = -1;
    private String postImageUri;
    private String caption;

    // Constructor untuk data dummy (Resource ID)
    public Post(String username, int profileImage, int postImageResource, String caption) {
        this.username = username;
        this.profileImage = profileImage;
        this.postImageResource = postImageResource;
        this.caption = caption;
    }

    // Constructor untuk upload baru (URI String)
    public Post(String username, int profileImage, String postImageUri, String caption) {
        this.username = username;
        this.profileImage = profileImage;
        this.postImageUri = postImageUri;
        this.caption = caption;
    }

    public String getUsername() { return username; }
    public int getProfileImage() { return profileImage; }
    public int getPostImageResource() { return postImageResource; }
    public String getPostImageUri() { return postImageUri; }
    public String getCaption() { return caption; }
}
