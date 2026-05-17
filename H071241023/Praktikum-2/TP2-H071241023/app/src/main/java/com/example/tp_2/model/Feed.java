package com.example.tp_2.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Feed implements Parcelable {

    private String imageUri;
    private String caption;
    private String username;
    private String profileImageUri;

    public Feed(String imageUri, String caption, String username, String profileImageUri) {
        this.imageUri = imageUri;
        this.caption = caption;
        this.username = username;
        this.profileImageUri = profileImageUri;
    }

    // FIX: profileImageUri sebelumnya tidak dibaca/ditulis ke Parcel
    // sehingga menyebabkan crash/data hilang saat intent ke DetailActivity
    protected Feed(Parcel in) {
        imageUri = in.readString();
        caption = in.readString();
        username = in.readString();
        profileImageUri = in.readString(); // ← FIX: tambah ini
    }

    public static final Creator<Feed> CREATOR = new Creator<Feed>() {
        @Override
        public Feed createFromParcel(Parcel in) {
            return new Feed(in);
        }

        @Override
        public Feed[] newArray(int size) {
            return new Feed[size];
        }
    };

    public String getImageUri()        { return imageUri; }
    public String getCaption()         { return caption; }
    public String getUsername()        { return username; }
    public String getProfileImageUri() { return profileImageUri; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(imageUri);
        dest.writeString(caption);
        dest.writeString(username);
        dest.writeString(profileImageUri); // ← FIX: tambah ini
    }

    @Override
    public int describeContents() {
        return 0;
    }
}