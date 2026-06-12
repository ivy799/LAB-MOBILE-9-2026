package com.library.app.model;

import android.net.Uri;
import java.io.Serializable;
import java.util.List;

public class Book implements Serializable {
    private static int idCounter = 1;

    private int id;
    private String title;
    private String author;
    private int year;
    private String blurb;
    private String genre;
    private float rating;
    private String coverUrl;
    private Uri coverUri;
    private boolean liked;
    private List<String> reviews;

    // Constructor for dummy data (drawable/url cover)
    public Book(String title, String author, int year, String blurb, String genre, float rating, String coverUrl) {
        this.id = idCounter++;
        this.title = title;
        this.author = author;
        this.year = year;
        this.blurb = blurb;
        this.genre = genre;
        this.rating = rating;
        this.coverUrl = coverUrl;
        this.liked = false;
    }

    // Constructor for user-added books (URI cover)
    public Book(String title, String author, int year, String blurb, String genre, float rating, Uri coverUri) {
        this.id = idCounter++;
        this.title = title;
        this.author = author;
        this.year = year;
        this.blurb = blurb;
        this.genre = genre;
        this.rating = rating;
        this.coverUri = coverUri;
        this.liked = false;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }
    public String getBlurb() { return blurb; }
    public void setBlurb(String blurb) { this.blurb = blurb; }
    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }
    public float getRating() { return rating; }
    public void setRating(float rating) { this.rating = rating; }
    public String getCoverUrl() { return coverUrl; }
    public void setCoverUrl(String coverUrl) { this.coverUrl = coverUrl; }
    public Uri getCoverUri() { return coverUri; }
    public void setCoverUri(Uri coverUri) { this.coverUri = coverUri; }
    public boolean isLiked() { return liked; }
    public void setLiked(boolean liked) { this.liked = liked; }
    public List<String> getReviews() { return reviews; }
    public void setReviews(List<String> reviews) { this.reviews = reviews; }

    public boolean hasUriCover() { return coverUri != null; }
}
