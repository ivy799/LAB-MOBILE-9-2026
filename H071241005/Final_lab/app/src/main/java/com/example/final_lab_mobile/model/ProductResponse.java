package com.example.final_lab_mobile.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ProductResponse {
    @SerializedName("products")
    private List<Product> products;

    public List<Product> getProducts() { return products; }
}
