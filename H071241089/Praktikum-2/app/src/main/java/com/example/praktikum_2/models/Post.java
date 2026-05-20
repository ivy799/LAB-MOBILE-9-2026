package com.example.praktikum_2.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Post implements Parcelable {
    private int imageResId;
    private String imageUri;
    private String username;
    private int profileImageResId;
    private String profileImageUri;
    private String caption;
    private String likes;
    private String time;

    public Post(int imageResId, String imageUri, String username, int profileImageResId, String profileImageUri, String caption, String likes, String time) {
        this.imageResId = imageResId;
        this.imageUri = imageUri;
        this.username = username;
        this.profileImageResId = profileImageResId;
        this.profileImageUri = profileImageUri;
        this.caption = caption;
        this.likes = likes;
        this.time = time;
    }

    // Constructor for resource IDs (backwards compatibility)
    public Post(int imageResId, String username, int profileImageResId, String caption) {
        this(imageResId, null, username, profileImageResId, null, caption, "0 likes", "Just now");
    }

    // Constructor for resource IDs with likes and time (MainActivity compatibility)
    public Post(int imageResId, String username, int profileImageResId, String caption, String likes, String time) {
        this(imageResId, null, username, profileImageResId, null, caption, likes, time);
    }
    
    // Constructor for URIs
    public Post(String imageUri, String username, int profileImageResId, String caption) {
        this(0, imageUri, username, profileImageResId, null, caption, "0 likes", "Just now");
    }

    protected Post(Parcel in) {
        imageResId = in.readInt();
        imageUri = in.readString();
        username = in.readString();
        profileImageResId = in.readInt();
        profileImageUri = in.readString();
        caption = in.readString();
        likes = in.readString();
        time = in.readString();
    }

    public static final Creator<Post> CREATOR = new Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel in) {
            return new Post(in);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };

    public int getImageResId() { return imageResId; }
    public String getImageUri() { return imageUri; }
    public String getUsername() { return username; }
    public int getProfileImageResId() { return profileImageResId; }
    public String getProfileImageUri() { return profileImageUri; }
    public String getCaption() { return caption; }
    public String getLikes() { return likes; }
    public String getTime() { return time; }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(imageResId);
        dest.writeString(imageUri);
        dest.writeString(username);
        dest.writeInt(profileImageResId);
        dest.writeString(profileImageUri);
        dest.writeString(caption);
        dest.writeString(likes);
        dest.writeString(time);
    }
}