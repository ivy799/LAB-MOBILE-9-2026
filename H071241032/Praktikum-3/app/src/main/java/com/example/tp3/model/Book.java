package com.example.tp3.model;

public class Book {
    private String id;
    private String title;
    private String author;
    private String year;
    private String blurb;
    private int coverResource; // Untuk dummy data (pakai icon bawaan dulu)
    private String coverUri;   // Untuk gambar dari galeri saat Add Book
    private boolean isLiked;

    // Atribut tambahan untuk Extra Point
    private double rating;
    private String genre;

    // Constructor
    public Book(String id, String title, String author, String year, String blurb, int coverResource, String coverUri, double rating, String genre) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.blurb = blurb;
        this.coverResource = coverResource;
        this.coverUri = coverUri;
        this.isLiked = false; // Default awal selalu false (belum dilike)
        this.rating = rating;
        this.genre = genre;
    }

    // === Getter dan Setter ===
    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getYear() { return year; }
    public String getBlurb() { return blurb; }
    public int getCoverResource() { return coverResource; }
    public String getCoverUri() { return coverUri; }

    public boolean isLiked() { return isLiked; }
    public void setLiked(boolean liked) { isLiked = liked; }

    public double getRating() { return rating; }
    public String getGenre() { return genre; }
}