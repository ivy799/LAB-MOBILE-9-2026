package com.example.tp2.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Feed implements Parcelable {
    private final int profileImage;
    private final String username;
    private final int feedImage;
    private final String caption;

    public Feed(int profileImage, String username, int feedImage, String caption) {
        this.profileImage = profileImage;
        this.username = username;
        this.feedImage = feedImage;
        this.caption = caption;
    }

    protected Feed(Parcel in) {
        profileImage = in.readInt();
        username = in.readString();
        feedImage = in.readInt();
        caption = in.readString();
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

    public int getProfileImage() {
        return profileImage;
    }

    public String getUsername() {
        return username;
    }

    public int getFeedImage() {
        return feedImage;
    }

    public String getCaption() {
        return caption;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(profileImage);
        dest.writeString(username);
        dest.writeInt(feedImage);
        dest.writeString(caption);
    }
}