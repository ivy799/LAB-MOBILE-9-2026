package com.example.tuprak_2;

import java.io.Serializable;

public class Highlight implements Serializable {
    private String title;
    private int coverImage;

    public Highlight(String title, int coverImage) {
        this.title = title;
        this.coverImage = coverImage;
    }
    public String getTitle() { return title; }
    public int getCoverImage() { return coverImage; }
}
