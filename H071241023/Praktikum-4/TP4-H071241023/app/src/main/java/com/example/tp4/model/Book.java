package com.example.tp4.model;

public class Book {
    public String title;
    public String author;
    public String year;
    public String desc;
    public int image;
    public boolean isLiked;
    public String imageUri;

    public Book(String title, String author, String year, String desc, int image) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.desc = desc;
        this.image = image;
        this.imageUri = null;
        this.isLiked = false;
    }

    public Book(String title, String author, String year, String desc, String imageUri) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.desc = desc;
        this.imageUri = imageUri;
        this.image = 0;
        this.isLiked = false;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getYear() {
        return year;
    }

    public String getDesc() {
        return desc;
    }

    public int getImage() {
        return image;
    }

    public String getImageUri() {
        return imageUri;
    }

    public boolean isLiked() {
        return isLiked;
    }


}
