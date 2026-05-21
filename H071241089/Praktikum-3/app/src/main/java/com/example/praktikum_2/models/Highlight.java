package com.example.praktikum_2.models;

public class Highlight {
    private String title;
    private int imageResId;

    public Highlight(String title, int imageResId) {
        this.title = title;
        this.imageResId = imageResId;
    }

    public String getTitle() { return title; }
    public int getImageResId() { return imageResId; }
}