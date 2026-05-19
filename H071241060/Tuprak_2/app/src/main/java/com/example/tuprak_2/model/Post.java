package com.example.tuprak_2.model;

import android.net.Uri;
import java.io.Serializable;

public class Post implements Serializable {
    private String postId;
    private String userId;
    private String username;
    private int profileImageRes;
    private int imageRes;           // untuk drawable lokal
    private Uri imageUri;           // untuk gambar dari galeri
    private String caption;
    private int likeCount;
    private int commentCount;
    private String timeAgo;
    private boolean isLiked;

    // Constructor untuk dummy data (drawable lokal)
    public Post(String postId, String userId, String username, int profileImageRes,
                int imageRes, String caption, int likeCount, int commentCount, String timeAgo) {
        this.postId = postId;
        this.userId = userId;
        this.username = username;
        this.profileImageRes = profileImageRes;
        this.imageRes = imageRes;
        this.imageUri = null;
        this.caption = caption;
        this.likeCount = likeCount;
        this.commentCount = commentCount;
        this.timeAgo = timeAgo;
        this.isLiked = false;
    }

    // Constructor untuk post baru dari galeri
    public Post(String postId, String userId, String username, int profileImageRes,
                Uri imageUri, String caption) {
        this.postId = postId;
        this.userId = userId;
        this.username = username;
        this.profileImageRes = profileImageRes;
        this.imageRes = -1;
        this.imageUri = imageUri;
        this.caption = caption;
        this.likeCount = 0;
        this.commentCount = 0;
        this.timeAgo = "Baru saja";
        this.isLiked = false;
    }

    public String getPostId() { return postId; }
    public String getUserId() { return userId; }
    public String getUsername() { return username; }
    public int getProfileImageRes() { return profileImageRes; }
    public int getImageRes() { return imageRes; }
    public Uri getImageUri() { return imageUri; }
    public String getCaption() { return caption; }
    public int getLikeCount() { return likeCount; }
    public int getCommentCount() { return commentCount; }
    public String getTimeAgo() { return timeAgo; }
    public boolean isLiked() { return isLiked; }

    public void setLiked(boolean liked) { this.isLiked = liked; }
    public void setLikeCount(int count) { this.likeCount = count; }
    public boolean hasUri() { return imageUri != null; }
}
