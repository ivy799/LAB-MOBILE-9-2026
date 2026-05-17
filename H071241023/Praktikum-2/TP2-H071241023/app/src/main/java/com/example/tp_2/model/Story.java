package com.example.tp_2.model;

import android.os.Parcel;
import android.os.Parcelable;

// FIX: Story harus implement Parcelable agar bisa dikirim via intent ke StoryDetailActivity
// Sebelumnya Story tidak Parcelable sehingga getParcelableExtra("story_data") selalu null
public class Story implements Parcelable {

    private int image;
    private String name;

    public Story(int image, String name) {
        this.image = image;
        this.name = name;
    }

    protected Story(Parcel in) {
        image = in.readInt();
        name = in.readString();
    }

    public static final Creator<Story> CREATOR = new Creator<Story>() {
        @Override
        public Story createFromParcel(Parcel in) {
            return new Story(in);
        }

        @Override
        public Story[] newArray(int size) {
            return new Story[size];
        }
    };

    public int getImage()   { return image; }
    public String getName() { return name; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(image);
        dest.writeString(name);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}