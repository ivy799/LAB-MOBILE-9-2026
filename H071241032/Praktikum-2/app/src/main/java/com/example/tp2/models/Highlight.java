package com.example.tp2.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Highlight implements Parcelable {
    private final int imageRaw;
    private final String title;

    public Highlight(int imageRaw, String title) {
        this.imageRaw = imageRaw;
        this.title = title;
    }

    protected Highlight(Parcel in) {
        imageRaw = in.readInt();
        title = in.readString();
    }

    public static final Creator<Highlight> CREATOR = new Creator<Highlight>() {
        @Override
        public Highlight createFromParcel(Parcel in) {
            return new Highlight(in);
        }

        @Override
        public Highlight[] newArray(int size) {
            return new Highlight[size];
        }
    };

    public int getImageRaw() {
        return imageRaw;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(imageRaw);
        dest.writeString(title);
    }
}