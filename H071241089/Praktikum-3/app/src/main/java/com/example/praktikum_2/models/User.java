package com.example.praktikum_2.models;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    private String username;
    private int profileImageResId;

    public User(String username, int profileImageResId) {
        this.username = username;
        this.profileImageResId = profileImageResId;
    }

    protected User(Parcel in) {
        username = in.readString();
        profileImageResId = in.readInt();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getUsername() { return username; }
    public int getProfileImageResId() { return profileImageResId; }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeInt(profileImageResId);
    }
}