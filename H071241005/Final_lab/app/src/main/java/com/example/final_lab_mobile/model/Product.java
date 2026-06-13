package com.example.final_lab_mobile.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Product implements Serializable {
    private int id;
    private String title;
    private String description;
    private double price;
    private double rating;
    private String thumbnail;
    private String category;

    // Getters
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public double getPrice() { return price; }
    public double getRating() { return rating; }
    public String getThumbnail() { return thumbnail; }
    public String getCategory() { return category; }
}