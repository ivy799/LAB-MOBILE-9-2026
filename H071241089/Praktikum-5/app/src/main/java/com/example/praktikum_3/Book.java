package com.example.praktikum_3;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class Book implements Parcelable {
    private String title;
    private String author;
    private String year;
    private String blurb;
    private int imageResId; // For dummy books
    private Uri imageUri;   // For added books
    private boolean isLiked;

    public Book(String title, String author, String year, String blurb, int imageResId) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.blurb = blurb;
        this.imageResId = imageResId;
        this.isLiked = false;
    }

    public Book(String title, String author, String year, String blurb, Uri imageUri) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.blurb = blurb;
        this.imageUri = imageUri;
        this.isLiked = false;
    }

    protected Book(Parcel in) {
        title = in.readString();
        author = in.readString();
        year = in.readString();
        blurb = in.readString();
        imageResId = in.readInt();
        imageUri = in.readParcelable(Uri.class.getClassLoader());
        isLiked = in.readByte() != 0;
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getYear() { return year; }
    public String getBlurb() { return blurb; }
    public int getImageResId() { return imageResId; }
    public Uri getImageUri() { return imageUri; }
    public boolean isLiked() { return isLiked; }
    public void setLiked(boolean liked) { isLiked = liked; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(author);
        dest.writeString(year);
        dest.writeString(blurb);
        dest.writeInt(imageResId);
        dest.writeParcelable(imageUri, flags);
        dest.writeByte((byte) (isLiked ? 1 : 0));
    }
}
