package com.example.tuprak3;

import java.io.Serializable;

public class Book implements Serializable {
    private String id, title, author, year, blurb, genre;
    private int coverResId; // Untuk dummy data
    private String coverUri; // Untuk gambar dari galeri [cite: 372]
    private boolean isLiked;
    private double rating;

    public Book(String id, String title, String author, String year, String blurb, String genre, double rating, int coverResId) {
        this.id = id; this.title = title; this.author = author; this.year = year;
        this.blurb = blurb; this.genre = genre; this.rating = rating; this.coverResId = coverResId;
        this.isLiked = false;
    }

    // Getters and Setters
    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getYear() { return year; }
    public String getBlurb() { return blurb; }
    public String getGenre() { return genre; }
    public double getRating() { return rating; }
    public int getCoverResId() { return coverResId; }
    public String getCoverUri() { return coverUri; }
    public void setCoverUri(String coverUri) { this.coverUri = coverUri; }
    public boolean isLiked() { return isLiked; }
    public void setLiked(boolean liked) { isLiked = liked; }
}
