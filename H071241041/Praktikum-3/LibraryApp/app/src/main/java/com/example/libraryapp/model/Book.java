package com.example.libraryapp.model;

import android.net.Uri;

public class Book {
    private static int idCounter = 1;

    private int id;
    private String title;
    private String author;
    private int year;
    private String blurb;
    private String genre;
    private float rating;
    private String review;
    private int coverResId;      // for dummy books (drawable resource)
    private Uri coverUri;        // for user-added books (gallery URI)
    private boolean liked;
    private long addedTime;

    // Constructor for dummy data
    public Book(String title, String author, int year, String blurb,
                String genre, float rating, String review, int coverResId) {
        this.id = idCounter++;
        this.title = title;
        this.author = author;
        this.year = year;
        this.blurb = blurb;
        this.genre = genre;
        this.rating = rating;
        this.review = review;
        this.coverResId = coverResId;
        this.liked = false;
        this.addedTime = System.currentTimeMillis() - (idCounter * 1000L);
    }

    // Constructor for user-added books
    public Book(String title, String author, int year, String blurb,
                String genre, float rating, String review, Uri coverUri) {
        this.id = idCounter++;
        this.title = title;
        this.author = author;
        this.year = year;
        this.blurb = blurb;
        this.genre = genre;
        this.rating = rating;
        this.review = review;
        this.coverUri = coverUri;
        this.liked = false;
        this.addedTime = System.currentTimeMillis();
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getYear() { return year; }
    public String getBlurb() { return blurb; }
    public String getGenre() { return genre; }
    public float getRating() { return rating; }
    public String getReview() { return review; }
    public int getCoverResId() { return coverResId; }
    public Uri getCoverUri() { return coverUri; }
    public boolean isLiked() { return liked; }
    public long getAddedTime() { return addedTime; }

    public void setLiked(boolean liked) { this.liked = liked; }
    public void setCoverUri(Uri coverUri) { this.coverUri = coverUri; }
    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }
    public void setYear(int year) { this.year = year; }
    public void setBlurb(String blurb) { this.blurb = blurb; }
    public void setGenre(String genre) { this.genre = genre; }
    public void setRating(float rating) { this.rating = rating; }
    public void setReview(String review) { this.review = review; }
    public void setAddedTime(long addedTime) { this.addedTime = addedTime; }
}
